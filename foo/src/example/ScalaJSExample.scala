package example
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import org.scalajs.dom.{html, window, document}
import scala.util.Random

case class Point(x: Int, y: Int) {
  def +(p: Point) = Point(x + p.x, y + p.y)
  def /(d: Int) = Point(x / d, y / d)
}

@JSExportTopLevel("ScalaJSExample")
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    val winWidth  = Math.max(document.documentElement.clientWidth, window.innerWidth.toInt)
    val winHeight = Math.max(document.documentElement.clientHeight, window.innerHeight.toInt)

    ctx.canvas.width = winWidth
    ctx.canvas.height = winHeight

    var count = 0
    var p = Point(0, 0)
    val br = Point(winWidth, winHeight)
    val corners = Seq(br, Point(0, br.y), Point(br.x/2, 0))

    def clear() = {
      ctx.fillStyle = "black"
      ctx.fillRect(0, 0, br.x, br.y)
    }

    def run = for (i <- 0 until 10){
      if (count % 3000 == 0) clear()
      count += 1
      p = (p + corners(Random.nextInt(3))) / 2

      val height = 512.0 / (255 + p.y)
      val r = (p.x * height).toInt
      val g = ((255-p.x) * height).toInt
      val b = p.y
      ctx.fillStyle = s"rgb($g, $r, $b)"

      ctx.fillRect(p.x, p.y, 1, 1)
    }

    dom.window.setInterval(() => run, 50)
  }
}
