import sbt._
import sbt.StringUtilities._

class AtomicMapProject(info : ProjectInfo) extends DefaultProject(info) {

  override def javaCompileOptions =
    JavaCompileOption("-Xlint:unchecked") ::
    JavaCompileOption("-source") :: JavaCompileOption("1.6") ::
    super.javaCompileOptions.toList

  val specs = "org.scala-tools.testing" %% "specs" % "1.6.7" % "test"
}