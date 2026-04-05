package shortner

import shortner.repository.InMemoryUrlRepository
import shortner.routes.UrlRoutes
import shortner.service.UrlService
import zio._
import zio.http._

object Main extends ZIOAppDefault {

  override def run =
    for {
      repository <- ZIO.succeed(new InMemoryUrlRepository())
      service    <- ZIO.succeed(new UrlService(repository))
      routes     =  new UrlRoutes(service).routes

      _ <- Server.serve(routes.toHttpApp).provide(Server.defaultWithPort(8080))
    } yield ()
}