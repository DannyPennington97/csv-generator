name := "univocity-test"

version := "0.1"

scalaVersion := "2.12.12"

resolvers += "sonatype" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += Resolver.jcenterRepo

val AkkaVersion = "2.6.9"

libraryDependencies ++= Seq(
  "com.univocity" % "univocity-parsers" % "2.9.1",
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.2.3",
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
)

Global / concurrentRestrictions := Seq(
  Tags.limit(Tags.CPU, 2),
  Tags.limitAll( 15 )
)
