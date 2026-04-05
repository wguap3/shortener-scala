package shortner.model
import java.time.Instant

case class Url(
              id:Long,
              originalUrl: String,
              shortCode: String,
              createAt: Instant,
              clickCount: Long = 0
              )
