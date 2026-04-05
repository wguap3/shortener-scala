package shortner.repository

import shortner.model.Url
import zio.{Task, ZIO}

import java.util.concurrent.ConcurrentHashMap

class InMemoryUrlRepository extends UrlRepository{
  private val storage = new ConcurrentHashMap[String,Url]()

  override def save(url: Url): Task[Url] =
    ZIO.attempt{
      storage.put(url.shortCode,url)
      url
    }

  override def findByCode(shortCode: String): Task[Option[Url]] =
    ZIO.attempt{
      Option(storage.get(shortCode))
    }

  override def incrementClick(shortCode: String): Task[Unit] =
    ZIO.attempt{
      storage.computeIfPresent(shortCode,(_,url) => url.copy(clickCount = url.clickCount + 1))
      ()
    }
}

