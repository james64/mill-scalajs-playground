import mill._
import mill.scalalib._
import mill.scalajslib._

object foo extends ScalaJSModule {
  def scalaVersion = "2.12.4"
  def scalaJSVersion = "0.6.22"

  def ivyDeps = Agg(
    ivy"org.scala-js::scalajs-dom::1.0.0"
  )
}
