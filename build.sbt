name := "solr-scala-client"

organization := "com.eyewyre.solr.scala"

version := "0.0.12-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions += "-feature"

resolvers += "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"

resolvers += "Local Maven Repository" at "file:///"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  "org.apache.solr" % "solr-solrj" % "4.5.1" % "compile",
  "com.ning" % "async-http-client" % "1.7.16" % "compile",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "org.mockito" % "mockito-core" % "1.9.0" % "test",
  "commons-logging" % "commons-logging" % "1.1.3" % "runtime"
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq(
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
      )
    case _ => libraryDependencies.value
  }
}

publishTo := {
  val archiva = "http://repo.eyewyre.com:8000/repository/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at archiva + "snapshots")
  else
    Some("releases"  at archiva + "releases")
}

EclipseKeys.withSource := true
