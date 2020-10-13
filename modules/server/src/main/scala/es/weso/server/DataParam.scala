package es.weso.server

import java.net.URI

import Defaults._

import cats.data.EitherT
import cats.arrow.FunctionK
import cats.implicits._
import cats.effect._
import es.weso.html2rdf.HTML2RDF
import es.weso.rdf.RDFReasoner
import es.weso.rdf.jena._
import es.weso.rdf.nodes.IRI
import es.weso.server.format._
import org.log4s.getLogger
import es.weso.utils.IOUtils._
import es.weso.server.merged.CompoundData

case class DataParam(data: Option[String],
                     dataURL: Option[String],
                     dataFile: Option[String],
                     maybeEndpoint: Option[String],
                     dataFormatValue: Option[DataFormat],
                     dataFormatTextarea: Option[DataFormat],
                     dataFormatUrl: Option[DataFormat],
                     dataFormatFile: Option[DataFormat],
                     inference: Option[String],
                     targetDataFormat: Option[DataFormat],
                     activeDataTab: Option[String],
                     compoundData: Option[String]
                    ) {
  private[this] val logger = getLogger

  sealed abstract class DataInputType {
    val id: String
  }
  case object dataUrlType extends DataInputType {
    override val id = "#dataUrl"
  }
  case object dataFileType extends DataInputType {
    override val id = "#dataFile"
  }
  case object dataEndpointType extends DataInputType {
    override val id = "#dataEndpoint"
  }
  case object dataTextAreaType extends DataInputType {
    override val id = "#dataTextArea"
  }
  case object compoundDataType extends DataInputType {
    override val id = "#compoundData"
  }

  def parseDataTab(tab: String): Either[String, DataInputType] = {
    logger.debug(s"parseDataTab: tab = $tab")
    val inputTypes = List(dataUrlType,dataFileType,dataEndpointType,dataTextAreaType)
    inputTypes.find(_.id == tab) match {
      case Some(x) => Right(x)
      case None => Left(s"Wrong value of tab: $tab, must be one of [${inputTypes.map(_.id).mkString(",")}]")
    }
  }

  val dataFormat: Option[DataFormat] = { 
    val dataTab = parseDataTab(activeDataTab.getOrElse(defaultActiveDataTab)) 
    pprint.log(dataTab)
    dataTab match {
     case Right(`dataUrlType`) => dataFormatUrl orElse dataFormatValue
     case Right(`dataFileType`) => dataFormatFile orElse dataFormatValue
     case Right(`dataTextAreaType`) => dataFormatTextarea orElse dataFormatValue
     case _ => dataFormatValue
    }
  }

  private def applyInference(rdf: Resource[IO,RDFReasoner],
                             inference: Option[String],
                             dataFormat: Format
                            ): Resource[IO,RDFReasoner] = 
    extendWithInference(rdf, inference) 

  private def extendWithInference(resourceRdf: Resource[IO,RDFReasoner],
                                  optInference: Option[String]
                                 ): Resource[IO,RDFReasoner] = {
    logger.debug(s"############# Applying inference $optInference")
    optInference match {
      case None => resourceRdf
      case Some(str) => Resource.liftF(resourceRdf.use(rdf => rdf.applyInference(str)))
    } 
    /*.fold(
      msg => Left(s"Error applying inference to RDF: $msg"),
      (newRdf: RDFReasoner) => Right(newRdf)
    ) */
  }

  /**
    * get RDF data from data parameters
    * @return a pair where the first value can be Some(string)
    *         if it has string representation and the second parameter
    *         is the RDF data
    */
  def getData(relativeBase: Option[IRI]
  ): IO[(Option[String], Resource[IO,RDFReasoner])] = {
    val base = relativeBase.map(_.str)
    println(s"ActiveDataTab: $activeDataTab")
    val inputType = activeDataTab match {
      case Some(a) => parseDataTab(a)
      case None if compoundData.isDefined => Right(compoundDataType)
      case None if data.isDefined => Right(dataTextAreaType)
      case None if dataURL.isDefined => Right(dataUrlType)
      case None if dataFile.isDefined => Right(dataFileType)
      case None if maybeEndpoint.isDefined => Right(dataEndpointType)
      case None => Right(dataTextAreaType)
    }
    println(s"Input type: $inputType")
    val x: IO[(Option[String],Resource[IO,RDFReasoner])] = inputType match {
      case Right(`dataUrlType`) => {
        dataURL match {
          case None => err(s"Non value for dataURL")
          case Some(dataUrl) => {
            val dataFormat = dataFormatUrl.getOrElse(DataFormat.default)
            for {
              rdf <- rdfFromUri(new URI(dataUrl), dataFormat,base)
              // newRdf <- Resource.liftF(applyInference(rdf, inference, dataFormat))
              // eitherStr <- Resource.liftF(newRdf.serialize(dataFormat.name,None).attempt)
              // optStr = eitherStr.toOption
            } yield (None , rdf)

/*            rdfFromUri(new URI(dataUrl), dataFormat,base) match {
              case Left(str) => err(s"Error obtaining $dataUrl with $dataFormat: $str")
              case Right(rdf) => io2es(
                for { 
                  newRdf <- applyInference(rdf, inference, dataFormat)
                  eitherStr <- newRdf.serialize(dataFormat.name,None).attempt
                  optStr = eitherStr.toOption
                } yield (optStr, newRdf)
              ) 
            } */
          } 
        }
      }
      case Right(`dataFileType`) => {
        dataFile match {
          case None => err(s"No value for dataFile")
          case Some(dataStr) =>
            val dataFormat: Format = dataFormatFile.getOrElse(DataFormat.default)
            /*io2es(RDFAsJenaModel.fromString(dataStr, dataFormat.name, iriBase).use(rdf => for {
              iriBase <- mkBase(base)
              newRdf <- extendWithInference(rdf, inference)
              eitherStr <- newRdf.serialize(dataFormat.name,None).attempt
              optStr = eitherStr.toOption              
            } yield (optStr,newRdf))) */
            for {
              iriBase <- mkBase(base)
            } yield (None,RDFAsJenaModel.fromString(dataStr, dataFormat.name, iriBase))
        }
      }

      case Right(`dataEndpointType`) => {
        maybeEndpoint match {
          case None => err(s"No value for endpoint")
          case Some(endpointUrl) => {
            for {
              endpoint <- Endpoint.fromString(endpointUrl)
              // newRdf <- extendWithInference(endpoint, inference)
            } yield (None,Resource.pure[IO,RDFReasoner](endpoint))
          }
        }
      }
      case Right(`dataTextAreaType`) => {
        pprint.log(s"Obtaining data from textArea")
        pprint.log(data)
        data match {
          case None => IO((None, RDFAsJenaModel.empty))
          case Some(data) => {
            val dataFormat = dataFormatTextarea.getOrElse(dataFormatValue.getOrElse(DataFormat.default))
            for {
              rdf <- rdfFromString(data, dataFormat, base)
              // newRdf <- io2es(extendWithInference(rdf, inference))
              // eitherStr <- io2es(newRdf.serialize(dataFormat.name,None).attempt)
              // optStr = eitherStr.toOption
            } yield (None,rdf)
          }}}

      case Right(`compoundDataType`) => { 
       println(s"###Compound data") 
       for { 
         cd <- IO.fromEither(CompoundData.fromString(compoundData.getOrElse("")).leftMap(s => new RuntimeException(s)))
       } yield (None,cd.toRDF) 
      }
      case Right(other) => err(s"Unknown value for activeDataTab: $other")

      case Left(msg) => err(msg)
    }
    x 
  }


  private def rdfFromString(str: String,
                            format: Format,
                            base: Option[String]
                           ): IO[Resource[IO,RDFReasoner]] = {
    pprint.log(format)
    format.name match {
      case f if HTML2RDF.availableExtractorNames contains f => IO(HTML2RDF.extractFromString(str,f)) /*for {
        eitherRdf <- 
      } yield eitherRdf */
      case _ => for {
        baseIri <- mkBase(base)
      } yield RDFAsJenaModel.fromChars(str,format.name,baseIri)
    }
  }

  private def rdfFromUri(uri: URI,
                         format: Format,
                         base: Option[String]
                        ): IO[Resource[IO,RDFReasoner]] = {
    format.name.toLowerCase match {
      case f if HTML2RDF.availableExtractorNames contains f =>
        IO(HTML2RDF.extractFromUrl(uri.toString, f))
      case _ => for {
       baseIri <- mkBase(base)
      } yield RDFAsJenaModel.fromURI(uri.toString, format.name, baseIri) 
    }
  }

  private def mkBaseIri(maybeBase: Option[String]): Either[String, Option[IRI]] = maybeBase match {
    case None => Right(None)
    case Some(str) => IRI.fromString(str).map(Some(_))
  }

   private def mkBase(base: Option[String]): IO[Option[IRI]] = base match {
    case None => IO(None)
    case Some(str) => IRI.fromString(str).fold(e => 
      IO.raiseError(new RuntimeException(s"Cannot get IRI from $str")),
      (iri: IRI) => IO(Some(iri))
    )
  }


}

object DataParam {
  private[this] val logger = getLogger

  private[server] def mkData[F[_]:Effect](
     partsMap: PartsMap[F],
     relativeBase: Option[IRI]
    ): ESF[(Resource[F,RDFReasoner],DataParam),F] = {

    val r: ESF[(Resource[F,RDFReasoner], DataParam),F] = for {
      dp <- f2es(mkDataParam[F](partsMap))
      pair <- io2esf(dp.getData(relativeBase))
    } yield {
      val (optStr, rdf) = pair
      (cnvResource(rdf), dp.copy(data = optStr))
    }
    r
  }

  private def cnvResource[A, F[_]: Effect](r: Resource[IO,A]):Resource[F,A] = r.mapK(cnv)
  private def cnv[A,F[_]: Effect]: FunctionK[IO,F] = new FunctionK[IO,F] {
    def apply[A](ioa: IO[A]): F[A] = io2f(ioa)
  }

  private def getDataFormat[F[_]](name: String, partsMap: PartsMap[F])(implicit F: Effect[F]): F[Option[DataFormat]] = for {
    maybeStr <- partsMap.optPartValue(name)
  } yield maybeStr match {
    case None => None
    case Some(str) => DataFormat.fromString(str).fold(
      err => {
        pprint.log(s"Unsupported dataFormat for ${name}: $str")
        None
      },
      df => Some(df)
    )
  }

  private[server] def mkDataParam[F[_]:Effect](partsMap: PartsMap[F]
  ): F[DataParam] = for {
    data <- partsMap.optPartValue("data")
    compoundData <- partsMap.optPartValue("compoundData")
    dataURL <- partsMap.optPartValue("dataURL")
    dataFile <- partsMap.optPartValue("dataFile")
    endpoint <- partsMap.optPartValue("endpoint")
    dataFormatTextArea <- getDataFormat("dataFormatTextArea", partsMap)
    dataFormatUrl <- getDataFormat("dataFormatUrl",partsMap)
    dataFormatFile <- getDataFormat("dataFormatFile", partsMap)
    dataFormatValue <- getDataFormat("dataFormat", partsMap)
    inference <- partsMap.optPartValue("inference")
    targetDataFormat <- getDataFormat("targetDataFormat",partsMap)
    activeDataTab <- partsMap.optPartValue("rdfDataActiveTab")
  } yield {
    pprint.log(data)
    pprint.log(compoundData)
    pprint.log(dataFormatValue)
    pprint.log(dataFormatTextArea)
    pprint.log(dataFormatUrl)
    pprint.log(dataFormatFile)
    pprint.log(dataURL)
    pprint.log(endpoint)
    pprint.log(activeDataTab)
    pprint.log(targetDataFormat)
    val endpointRegex = "Endpoint: (.+)".r
    val finalEndpoint = endpoint.fold(data match {
      case None => None
      case Some(str) => str match {
        case endpointRegex(ep) => Some(ep)
        case _ => None
      }
    })(Some(_))
    val finalActiveDataTab = activeDataTab /* finalEndpoint match {
      case Some(endpoint) =>
        if (endpoint.length > 0) Some("#dataEndpoint")
        else activeDataTab
      case None => activeDataTab
    } */
    pprint.log(finalEndpoint)

    val dp = DataParam(data,dataURL,dataFile,finalEndpoint,dataFormatValue,
      dataFormatTextArea,dataFormatUrl,dataFormatFile,
      inference,targetDataFormat,finalActiveDataTab,compoundData
    )
    pprint.log(dp)
    dp
  }

 
  private[server] def empty: DataParam =
    DataParam(None,None,None,None,None,None,None,None,None,None,None,None)


}