package es.weso.server
import cats.effect._
import cats.implicits._
import fs2.Stream
import org.http4s._
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.{CORS, Logger}

import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration._

object Server {

/*  def context[F[_]: Sync]: F[SSLContext] =
    SSLHelper.loadContextFromClasspath(SSLHelper.keystorePassword, SSLHelper.keyManagerPassword)

  def builder[F[_]: ConcurrentEffect: ContextShift: Timer](port: Int): F[BlazeServerBuilder[F]] =
   context.map { sslContext =>
     BlazeServerBuilder[F](global).bindHttp(port)
       .withSslContext(sslContext)
  } */

  def routesService[F[_]: ConcurrentEffect](blocker: Blocker, client: Client[F])(implicit T: Timer[F], C: ContextShift[F]): HttpRoutes[F] =
    CORS (
      SchemaService[F](blocker,client).routes <+>
      APIService[F](blocker, client).routes <+>
      DataService[F](blocker, client).routes <+>
      ShExService[F](blocker,client).routes <+>
      ShapeMapService[F](blocker,client).routes <+>
      WikidataService[F](blocker, client).routes <+>
      EndpointService[F](blocker,client).routes <+>
      PermalinkService[F](blocker,client).routes

    ) <+>
    WebService[F](blocker).routes <+>
    DataWebService[F](blocker, client).routes <+>
    StaticService[F](blocker).routes <+>
    LinksService[F](blocker).routes

/*  def context[F[_]: Sync] =
    ssl.loadContextFromClasspath(ssl.keystorePassword, ssl.keyManagerPassword)

  def builder[F[_]: ConcurrentEffect: ContextShift: Timer]: F[BlazeServerBuilder[F]] =
    context.map { sslContext =>
      BlazeServerBuilder[F](global)
        .bindHttp(8443)
        .withSslContext(sslContext)
    } */
        
  def stream[F[_]: ConcurrentEffect](blocker:Blocker, port: Int, ip: String)(implicit T: Timer[F], C: ContextShift[F]): Stream[F, Nothing] = {
    for {
      client <- BlazeClientBuilder[F](global).withRequestTimeout(5.minute)
        //      .withSslContext(SSLContext.getDefault)
        .stream
      app = (
        // HelloService[F](blocker).routes
	      // HSTS( 
          routesService[F](blocker,client) 
        // )
      ).orNotFound
      finalHttpApp = Logger.httpApp(true, false)(app)
      /* b <- Stream.eval(builder(port))
      exitCode <- b.withHttpApp(finalHttpApp).serve  */
      exitCode <- BlazeServerBuilder[F](global)
        .bindHttp(port,ip)
        .withIdleTimeout(10.minutes)
        .withHttpApp(finalHttpApp)
        .serve
    } yield exitCode
    }.drain

}