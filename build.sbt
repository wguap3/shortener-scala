ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.7"
ThisBuild / version      := "0.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "shortener-scala",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"         % "2.1.1",
      "dev.zio" %% "zio-streams" % "2.1.1",

      "org.http4s" %% "http4s-ember-server" % "0.23.26",
      "org.http4s" %% "http4s-dsl"          % "0.23.26",
      "org.http4s" %% "http4s-circe"        % "0.23.26",

      "io.circe" %% "circe-generic" % "0.14.7",

      "dev.zio" %% "zio-interop-cats" % "23.1.0.2",

      "org.postgresql" % "postgresql" % "42.7.3",
    )
  )
