package es.weso.rdfshape.server.api

import cats.effect._
import com.typesafe.scalalogging.LazyLogging
import es.weso.rdfshape.server.api.APIDefinitions._
import es.weso.rdfshape.server.api.QueryParams.{UrlCodeParam, UrlParam}
import org.http4s._
import org.http4s.client.Client
import org.http4s.dsl.Http4sDsl
import org.mongodb.scala._
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.result.{InsertOneResult, UpdateResult}

import java.net.{MalformedURLException, URL}
import java.time.Instant
import java.util.Calendar
import java.util.concurrent.TimeUnit
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Promise}
import scala.util.Random

class PermalinkService(client: Client[IO])
    extends Http4sDsl[IO]
    with LazyLogging {

  lazy val mongoClient: MongoClient = MongoClient(mongoConnectionString)
  lazy val db: MongoDatabase        = mongoClient.getDatabase(mongoDatabase)
  lazy val collection: MongoCollection[Document] =
    db.getCollection(collectionName)

  // Utils for url generation
  val random: Random.type = Random
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {

    // Insert a reference to the permalink in DB
    case GET -> Root / `api` / "permalink" / "generate" :?
        UrlParam(url) =>
      // Store only query path and query params
      val urlObj  = new URL(url)
      val urlPath = s"${urlObj.getPath}?${urlObj.getQuery}"

      val existingUrlCode = retrieveUrlCode(urlPath)
      if(existingUrlCode.isDefined) {
        Ok(existingUrlCode.get.toString)
      } else {
        try {
          val urlCode = Instant.now.getEpochSecond.toString + random.nextInt(10)

          // Create DB entry
          val doc: Document = Document(
            // "_id" -> Autogenerated,
            "longUrl" -> urlPath,
            "urlCode" -> urlCode.toLong,
            "date"    -> Calendar.getInstance().getTime
          )

          // Insert doc
          val observable: Observable[InsertOneResult] =
            collection.insertOne(doc)
          observable.subscribe(new Observer[InsertOneResult] {
            override def onSubscribe(subscription: Subscription): Unit =
              subscription.request(1)
            override def onNext(result: InsertOneResult): Unit =
              logger.info(s"Created permalink: $url => $urlCode")
            override def onError(e: Throwable): Unit = {
              logger.error(s"Permalink creation failed: ${e.getMessage}")
              InternalServerError(
                s"Could not generate the permalink for url: $url"
              )
            }

            override def onComplete(): Unit =
              logger.info("Permalink processing completed.")
          })

          Created(urlCode)

        } catch {
          case _: MalformedURLException =>
            BadRequest(s"Invalid URL provided for shortening: $url")
          case _: Exception =>
            InternalServerError(
              s"Could not generate the permalink for url: $url"
            )
        }

      }

    // Retrieve a URL given the link
    case GET -> Root / `api` / "permalink" / "get" :?
        UrlCodeParam(urlCode) =>
      try {
        val code    = urlCode.toLong
        val promise = Promise[IO[Response[IO]]]()

        // Fetch document in database
        val observable: SingleObservable[Document] =
          collection.find(equal("urlCode", code)).first()
        observable.subscribe(new Observer[Document] {

          override def onSubscribe(subscription: Subscription): Unit =
            subscription.request(1)
          override def onNext(result: Document): Unit = {
            val longUrl = result.getString("longUrl")
            val urlCode = result.getLong("urlCode")

            logger.info(s"Retrieved original url: $urlCode => $longUrl")
            promise.success(Ok(longUrl))

            // Refresh use date of the link
            updateUrl(urlCode)
          }
          override def onError(e: Throwable): Unit = {
            logger.error(s"Original url recovery failed: ${e.getMessage}")
            promise.success(
              BadGateway(s"Original url recovery failed for code: $urlCode")
            )
          }
          override def onComplete(): Unit = {
            if(!promise.isCompleted) {
              logger.warn(s"Could not find the original url for code: $urlCode")
              promise.success(
                NotFound(s"Could not find the original url for code: $urlCode")
              )
            }
            logger.info("Permalink processing completed.")
          }
        })

        val result = Await.result(promise.future, Duration(8, TimeUnit.SECONDS))
        result

      } catch {
        case _: NumberFormatException =>
          BadRequest(s"Invalid permalink code: $urlCode")
        case _: Exception =>
          InternalServerError(
            s"Could not execute the request for the permalink with code: $urlCode"
          )
      }
  }
  // DB credentials. Access is limited to application needs.
  private val mongoUser = sys.env.getOrElse("MONGO_USER", "rdfshape-user")
  private val mongoPassword =
    sys.env.getOrElse("MONGO_PASSWORD", "rdfshape-user")
  private val mongoDatabase = sys.env.getOrElse("MONGO_DATABASE", "rdfshape")
  private val collectionName =
    sys.env.getOrElse("MONGO_COLLECTION", "permalinks")
  private val mongoConnectionString =
    s"mongodb+srv://$mongoUser:$mongoPassword@cluster0.pnja6.mongodb.net/$mongoDatabase" +
      "?retryWrites=true&w=majority"

  private def retrieveUrlCode(urlPath: String): Option[Long] = {

    val promise = Promise[Option[Long]]()

    // Fetch document in database
    val observable: SingleObservable[Document] =
      collection.find(equal("longUrl", urlPath)).first()
    observable.subscribe(new Observer[Document] {
      override def onSubscribe(subscription: Subscription): Unit =
        subscription.request(1)
      override def onNext(result: Document): Unit = {
        val urlCode = result.getLong("urlCode")

        logger.info(s"Retrieved permalink: $urlPath => $urlCode")
        promise.success(Option(urlCode))

        // Refresh use date of the link
        updateUrl(urlCode)
      }
      override def onError(e: Throwable): Unit = {
        logger.error(s"Permalink recovery failed: ${e.getMessage}")
        promise.success(None)
      }
      override def onComplete(): Unit = {
        if(!promise.isCompleted) {
          logger.error(s"Could not find the permalink for url: $urlPath")
          promise.success(None)
        }
        logger.info("Permalink processing completed.")
      }
    })

    val result = Await.result(promise.future, Duration(8, TimeUnit.SECONDS))
    result
  }

  private def updateUrl(code: Long): Unit = {
    logger.debug(s"URL code to update: $code")
    // Update date of document in database
    val observable: SingleObservable[UpdateResult] = collection.updateOne(
      equal("urlCode", code),
      set("date", Calendar.getInstance().getTime)
    )

    observable.subscribe(new Observer[UpdateResult] {
      override def onSubscribe(subscription: Subscription): Unit =
        subscription.request(1)
      override def onNext(result: UpdateResult): Unit = {
        logger.debug(s"Refreshed date of permalink: $code")
      }
      override def onError(e: Throwable): Unit = ()
      override def onComplete(): Unit          = ()
    })
  }
}

object PermalinkService {
  def apply(client: Client[IO]): PermalinkService = new PermalinkService(client)
}
