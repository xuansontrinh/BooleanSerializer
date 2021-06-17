import sbt.Keys.libraryDependencies
import sbt._

object Common {
  def identity(moduleID: ModuleID) = moduleID
  val playJson: ModuleID = "com.typesafe.play" %% "play-json" % "2.9.1"
//  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % "3.2.0" % Test
  val scalariformVer: String = "0.2.10"
  val scalariform: ModuleID = "org.scalariform" %% "scalariform" % scalariformVer
  val tmp: ModuleID = "org.scalariform" %% "scalariform" % "0.2.1"
  lazy val commonSettings = Seq(
    libraryDependencies += "dev.zio" %% "zio-test-magnolia" % "1.0.7",
    libraryDependencies ++= defaultLibDeps ++ Seq(scalariform % Test),
//    libraryDependencies += "org.scalatest" % "scalatest" % "3.0.8"
//    libraryDependencies += "scalatest"
    //libraryDependencies ++= scalariformtest
  )
  val son = "Son"
  val isE = "is"
  val defaultLibDeps: Seq[ModuleID] = Seq(
    playJson
//    identity(scalaTest)
//    scalaTest
  )
}
