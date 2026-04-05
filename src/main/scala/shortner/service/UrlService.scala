package shortner.service

import shortner.model.Url
import shortner.repository.UrlRepository
import zio.{Task, ZIO}

import java.time.Instant

class UrlService(repository: UrlRepository) {
  def shorten(originalUrl: String) : Task[Url] =
    for{
      shortCode <- ZIO.succeed(generateCode())

      url = Url(
        id = scala.util.Random.nextLong(),
        originalUrl = originalUrl,
        shortCode = shortCode,
        createAt = Instant.now()
      )

      saved <- repository.save(url)
    }yield saved

  def resolve(shortCode: String): Task[Option[Url]] =
    for{
      _ <- repository.incrementClick(shortCode)

      url <- repository.findByCode(shortCode)
    }yield url

  private def generateCode(): String =
    scala.util.Random.alphanumeric
      .take(6)
      .mkString
}
