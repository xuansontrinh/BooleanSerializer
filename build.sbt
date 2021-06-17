import Common.commonSettings
import sbt.Keys.libraryDependencies
//name := "BooleanSerializer"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

//libraryDependencies += "org.scala-sbt" %% "zinc" % "1.5.7"

//libraryDependencies += "org.scala-lang" % "scala-compiler" % ""

//libDependencies += "specs"

//val scalariform: ModuleID = "org.scalariform" %% "scalariform" % ""
//val scalariformtest: Seq[ModuleID] = Seq("org.scalariform" %% "scalariform" % "0.2.10")

lazy val backend = (project in file("."))
  .settings(
    name := "backend",
//    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    commonSettings
  )


lazy val common = (project in file("common"))
  .settings(
    name := "common",
    scalaVersion := "3.0.0",
    //    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
//    commonSettings,
//    libraryDependencies += Common.scalaTest
//    commonSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
  )
