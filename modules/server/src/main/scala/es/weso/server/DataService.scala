package es.weso.server

import cats._
import cats.effect._
import cats.implicits._
import es.weso.rdf.jena.{Endpoint, RDFAsJenaModel}
import es.weso.rdf.streams.Streams
import es.weso.schema._
import es.weso.server.ApiHelper._
import results._
import es.weso.server.Defaults.{availableDataFormats, availableInferenceEngines, defaultActiveDataTab, defaultDataFormat, defaultInference}
import es.weso.server.QueryParams._
import es.weso.server.format._
import es.weso.server.utils.Http4sUtils._
import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import fs2._
import org.http4s._
import org.http4s.circe._
import org.http4s.client.Client
import org.http4s.dsl.Http4sDsl
import org.http4s.headers._
import org.http4s.multipart.Multipart
import org.http4s.server.staticcontent.{ResourceService, resourceService}
import org.log4s.getLogger
import scala.concurrent.duration._
import APIDefinitions._
import cats.data.EitherT
// import es.weso.html
import es.weso.rdf.RDFReader
import es.weso.rdf.nodes.IRI
import org.http4s.dsl.io.Ok
import es.weso.utils.IOUtils._
import es.weso.utils.FUtils._
import es.weso.server.utils.OptEitherF._
import scala.util.Try
import es.weso.rdf.RDFReasoner

class DataService[F[_]:ConcurrentEffect: Timer](blocker: Blocker,
                                               client: Client[F])(implicit cs: ContextShift[F])
  extends Http4sDsl[F] {

  private val relativeBase = Defaults.relativeBase
  private val logger = getLogger

  val routes = HttpRoutes.of[F] {

    // Input RDF data formats include html-microdata, turtle, json-ld...
    case GET -> Root / `api` / "data" / "formats" / "input" => {
      val formats = DataFormat.availableFormats.map(_.name)
      val json = Json.fromValues(formats.map(Json.fromString(_)))
      Ok(json)
    }

    // Output RDF data conversion formats
    case GET -> Root / `api` / "data" / "formats" / "output" => {
      val formats = DataFormats.availableFormats.map(_.name)
      val json = Json.fromValues(formats.map(Json.fromString(_)))
      Ok(json)
    }

    case GET -> Root / `api` / "data" / "formats" / "default" => {
      val dataFormat = DataFormat.default.name
      Ok(Json.fromString(dataFormat))
    }

    case GET -> Root / `api` / "data" / "inferenceEngines" => {
      val inferenceEngines = Defaults.availableInferenceEngines
      val json = Json.fromValues(inferenceEngines.map(Json.fromString(_)))
      Ok(json)
    }

    case GET -> Root / `api` / "data" / "inferenceEngines" / "default" => {
      val defaultInferenceEngine = Defaults.defaultInference
      Ok(Json.fromString(defaultInferenceEngine))
    }

    case GET -> Root / `api` / "data" / "visualize" / "formats" => {
      val formats = DataConverter.availableGraphFormatNames ++
        List(
          "DOT", // DOT is not a visual format but can be used to debug
          "JSON" // JSON is the format that can be used by Cytoscape
        )
      val json = Json.fromValues(formats.map(Json.fromString(_)))
      Ok(json)
    }

    case req@GET -> Root / `api` / "dataUrl" / "info" :?
      OptDataURLParam(optDataUrl) +&
        DataFormatParam(optDataFormat) => {
      val dataFormat = dataFormatOrDefault(optDataFormat)
      optDataUrl match {
        case None => errJson(s"Must provide a dataUrl")
        case Some(dataUrl) => for {
           data <- client.expect[String](dataUrl)
           result <- io2f(dataInfoFromString(data, dataFormat))
           r <- Ok(result).map(_.withContentType(`Content-Type`(MediaType.application.json)))
        } yield r
      }
    }

    case req@POST -> Root / `api` / "data" / "info" => {
      req.decode[Multipart[F]] { m =>
        val partsMap = PartsMap(m.parts)
        for {
          dataParam <- DataParam.mkData(partsMap, relativeBase)
          (resource,dp) = dataParam
          dataFormat = dataFormatOrDefault(dp.dataFormat.map(_.name))
          response <- dp.data match {
              case Some(data) => {
               val x:F[Response[F]] = for {
                  r <- io2f(dataInfoFromString(data, dataFormat))
                  ok <- Ok(r)
                } yield ok 
               x 
              }
           case None => {
             def action(rdf: RDFReasoner): IO[Json] = for {
               // str <- rdf.serialize(dataFormat)
               r <- dataInfo(rdf,None,dp.dataFormat)
             } yield r
             val resp: IO[Json] = resource.use(rdf => action(rdf))
             val x: F[Response[F]] = for {
               json <- io2f(resp)
               ok <- Ok(json)
             } yield ok
             x
           } 
          }
        } yield response
      }
    }

    case req@GET -> Root / `api` / "data" / "info" :?
      OptDataParam(optData) +&
      OptDataURLParam(optDataURL) +&
      CompoundDataParam(optCompoundData) +&
      DataFormatParam(maybeDataFormat) +&
      InferenceParam(optInference) +&
      OptEndpointParam(optEndpoint) +&
      OptActiveDataTabParam(optActiveDataTab) => {

      val either: Either[String, Option[DataFormat]] = for {
        df <- maybeDataFormat.map(DataFormat.fromString(_)).sequence
      } yield df

      val r: F[Response[F]] = either.fold(
        str => errJson(str), 
        optDataFormat => {
          val dp =
            DataParam(optData, optDataURL, None, optEndpoint,
              optDataFormat, optDataFormat, optDataFormat,
              None, //no dataFormatFile
              optInference,
              None, optActiveDataTab,
              optCompoundData)
          for {
            dataParam <- io2f(dp.getData(relativeBase))
            (maybeStr,res) = dataParam
            json <- io2f(res.use(rdf => dataInfo(rdf,maybeStr,optDataFormat)))
            ok <- Ok(json)            
          } yield ok
        }
      ) 
      r
    } 

    case req @ POST -> Root / `api` / "data" / "convert" => {
      println(s"POST /api/data/convert, Request: $req")
      req.decode[Multipart[F]] { m =>
        val partsMap = PartsMap(m.parts)
        for {
          dataParam <- DataParam.mkData(partsMap, relativeBase)
          (resourceRdf, dp) = dataParam
          targetFormat = dp.targetDataFormat.getOrElse(defaultDataFormat).name
          dataFormat = dp.dataFormat.getOrElse(defaultDataFormat)
          result <- io2f(resourceRdf.use(rdf => 
            DataConverter.rdfConvert(rdf, dp.data, dataFormat, targetFormat))
          )
          ok <- Ok(result.toJson)
        } yield ok
      }
    }

    case req @ GET -> Root / `api` / "data" / "convert" :?
      DataParameter(data) +&
      DataFormatParam(optDataFormat) +&
      CompoundDataParam(optCompoundData) +&
      TargetDataFormatParam(optResultDataFormat) => {
      for {
        eitherDataFormat <- either2ef[DataFormat,F](DataFormat.fromString(optDataFormat.getOrElse(defaultDataFormat.name))).value
        result <- eitherDataFormat.fold(
          e => BadRequest(e),
          dataFormat => for {
            r <- io2f(DataConverter.dataConvert(data,dataFormat,optCompoundData,optResultDataFormat.getOrElse(defaultDataFormat.name)))
            ok <- Ok(r.toJson)
          } yield ok
        )
      } yield result
    }

    case req @ POST -> Root / `api` / "data" / "query" => {
      println(s"POST /api/data/query, Request: $req")
      req.decode[Multipart[F]] { m =>
        val partsMap = PartsMap(m.parts)
        for {
          dataParam <- DataParam.mkData(partsMap, relativeBase)
          (resourceRdf, dp) = dataParam
          maybePair <- SparqlQueryParam.mkQuery(partsMap)
          resp <- maybePair match {
            case Left(err) => errJson(s"Error obtaining Query data $err")
            case Right((queryStr,qp)) => {
                  pprint.log(qp);
                  val optQueryStr = qp.query.map(_.str)
                  for {
                   json <- io2f(resourceRdf.use(rdf => rdf.queryAsJson(optQueryStr.getOrElse(""))))
                   v <- Ok(json)
                  } yield v
                }
            }
        } yield resp
      }
    }

    case req @ POST -> Root / `api` / "data" / "extract" => {
      println(s"POST /api/data/extract, Request: $req")
      req.decode[Multipart[F]] { m =>
        val partsMap = PartsMap(m.parts)
        for {
          maybeData <- DataParam.mkData(partsMap, relativeBase).attempt
          schemaEngine <- partsMap.optPartValue("schemaEngine")
          optSchemaFormatStr <- partsMap.optPartValue("schemaFormat")
          inference <- partsMap.optPartValue("inference")
          label <- partsMap.optPartValue("labelName")
          optBaseStr <- partsMap.optPartValue("base")
          nodeSelector <- partsMap.optPartValue("nodeSelector")
          schemaFormat <- optEither2f(optSchemaFormatStr, SchemaFormat.fromString)
          response <- maybeData match {
            case Left(err) => for {
              res <- io2f(DataExtractResult.fromMsg(s"Error obtaining data: ${err.getMessage}").toJson)
              ok <- Ok(res)
            } yield ok 
            // Ok(DataExtractResult.fromMsg(s"Error obtaining data: $err").toJson)
            case Right((resourceRdf, dp)) => for {
              d <- io2f(resourceRdf.use(rdf => dataExtract(rdf, dp.data, dp.dataFormatValue, nodeSelector, inference, schemaEngine, schemaFormat, label, None)))
              json <- io2f(d.toJson)
              ok <- Ok(json)
             } yield ok
          }
        } yield response
      }
    }

  } 

  private def parseInt(s: String): Either[String, Int] =
    Try(s.toInt).map(Right(_)).getOrElse(Left(s"$s is not a number"))

  private def errJson(msg: String): F[Response[F]] =
    Ok(Json.fromFields(List(("error",Json.fromString(msg)))))

}

object DataService {
  def apply[F[_]: ConcurrentEffect: ContextShift: Timer](blocker: Blocker, client: Client[F]): DataService[F] =
    new DataService[F](blocker, client)
}
