organization := "at.lnet"
name := "scala-ua-2018"
version := "1.0.0"
scalaVersion := "2.11.12"

scalacOptions := {
  val generalOpts = Seq(
    "-encoding", "utf8", "-feature", "-deprecation",
    "-Ywarn-unused", "-Ywarn-dead-code", "-Ywarn-unused-import", "-Ywarn-nullary-override")

  val specificOpts = CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 12)) =>
      Seq("-opt:l:method")

    case _ =>
      Seq()
 }
  generalOpts ++ specificOpts
}

libraryDependencies += "at.irian.bibi" %% "document-classifier-core" % "1.0.0"
libraryDependencies += "at.irian.bibi" %% "document-classifier-service" % "1.0.0"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.2.1"

excludeDependencies += "asm" % "asm"
excludeDependencies += "xpp3" % "xpp3"
excludeDependencies += "ch.qos.logback"
excludeDependencies += "com.typesafe.akka" %% "akka-http-experimental"
excludeDependencies += "commons-logging" % "commons-logging"
excludeDependencies += "org.slf4j" % "log4j-over-slf4j"
excludeDependencies += "org.mongodb" % "mongo-java-driver"
excludeDependencies += "org.sonatype.sisu.inject" % "cglib"

