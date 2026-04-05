package shortner.repository

import shortner.model.Url
import zio.Task

trait UrlRepository {
  def save(url : Url) : Task[Url]
  def findByCode(shortCode: String): Task[Option[Url]]
  def incrementClick(shortCode: String): Task[Unit]
}
