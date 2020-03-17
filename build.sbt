name := "aecor-postgres-journal"

inThisBuild(
  Seq(
    organization := "com.doomoolmori",
    crossScalaVersions := Seq("2.13.1", "2.12.10")
  )
)

lazy val kindProjectorVersion = "0.11.0"
lazy val aecorVersion = "0.19.0"
lazy val doobieVersion = "0.8.8"
lazy val catsEffectVersion = "2.1.0"

lazy val scalaCheckVersion = "1.14.0"
lazy val scalaTestVersion = "3.1.0"
lazy val catsVersion = "2.1.0"
lazy val circeVersion = "0.13.0"
lazy val logbackVersion = "1.2.3"
lazy val catsTaglessVersion = "0.11"

resolvers ++= Seq(
  Resolver.sonatypeRepo("public")
)

libraryDependencies ++= Seq(
  "io.aecor" %% "core" % aecorVersion,
  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-hikari" % doobieVersion,
  "org.typelevel" %% "cats-effect" % catsEffectVersion,
  "org.scalacheck" %% "scalacheck" % scalaCheckVersion % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "org.tpolecat" %% "doobie-scalatest" % doobieVersion % Test,
  "org.typelevel" %% "cats-testkit" % catsVersion % Test,
  "org.typelevel" %% "cats-tagless-macros" % catsTaglessVersion % Test,
  "io.circe" %% "circe-core" % circeVersion % Test,
  "io.circe" %% "circe-generic" % circeVersion % Test,
  "io.circe" %% "circe-parser" % circeVersion % Test,
  "ch.qos.logback" % "logback-classic" % logbackVersion % Test
)

addCommandAlias("fmt", "; compile:scalafmt; test:scalafmt; scalafmtSbt")

scalacOptions ++= Seq(
  "-J-Xss16m",
  "-Xsource:2.13"
)
addCompilerPlugin("org.typelevel" %% "kind-projector" % kindProjectorVersion cross CrossVersion.full)

parallelExecution in Test := false

scalacOptions in (Compile, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings")

licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT"))

// publishing
sbtPlugin := true
publishMavenStyle := true
publishArtifact in Test := false
bintrayOrganization := Some("doomoolmori")
bintrayRepository := "maven"
bintrayPackage := "aecor-postgres-journal"