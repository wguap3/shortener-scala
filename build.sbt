ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"
ThisBuild / version      := "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "shortener-scala",
    libraryDependencies ++= Seq(
      // ZIO
      "dev.zio" %% "zio"         % "2.1.1",

      // zio-http - HTTP сервер, проще чем http4s
      "dev.zio" %% "zio-http"    % "3.0.0-RC4",

      // zio-json - JSON сериализация
      "dev.zio" %% "zio-json"    % "0.6.2",

      // PostgreSQL (пока не используем но пусть будет)
      "org.postgresql" % "postgresql" % "42.7.3",
    )
  )
