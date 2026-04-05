package shortner.routes

import shortner.service.UrlService
import zio.http.{Response, Routes}
import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}
import shortner.service.UrlService
import zio._
import zio.http._
import zio.json._

case class ShortenRequest(originalUrl: String)

case class ShortenResponse(shortUrl: String)

object ShortenRequest {
  implicit val decoder: JsonDecoder[ShortenRequest] = DeriveJsonDecoder.gen
}

object ShortenResponse {
  implicit val encoder: JsonEncoder[ShortenResponse] = DeriveJsonEncoder.gen
}

class UrlRoutes(service: UrlService) {
  val routes: Routes[Any, Response] = Routes(

    Method.POST / "shorten" -> handler { (req: Request) =>
      for {
        body <- req.body.asString
          .orElseFail(Response.text("Bad request").status(Status.BadRequest))
        request <- ZIO.fromEither(body.fromJson[ShortenRequest])
          .orElseFail(Response.text("Invalid JSON").status(Status.BadRequest))
        url <- service.shorten(request.originalUrl)
          .orElseFail(Response.text("Server error").status(Status.InternalServerError))
        response = ShortenResponse(s"http://localhost:8080/${url.shortCode}")
      } yield Response.json(response.toJson)
    },

    Method.GET / string("code") -> handler { (code: String, _: Request) =>
      for {
        url <- service.resolve(code)
          .orElseFail(Response.text("Server error").status(Status.InternalServerError))
        response = url match {
          case Some(u) => Response.redirect(URL.decode(u.originalUrl).toOption.get)
          case None => Response.text("Not found").status(Status.NotFound)
        }
      } yield response
    }
  )
}