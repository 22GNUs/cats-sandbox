package personal.wxh.print

import scala.language.postfixOps

/**
@author wangxinhua
@since 1.0
  */
trait Printable[A] {
  def format(from: A): String
}

object Printable {
  def format[A: Printable](from: A): String =
    implicitly[Printable[A]].format(from)

  def print[A: Printable](from: A): Unit =
    println(implicitly[Printable[A]].format(from))
}

object PrintableInstances {
  implicit def stringInstance: Printable[String] = s => s
  implicit def intInstance: Printable[Int] = _ toString
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit p: Printable[A]): String = p.format(value)
    def print(implicit p: Printable[A]): Unit = println(p.format(value))
  }
}

object PrintableTest extends App {

  import PrintableSyntax._
  Cat("Mia", 1, "white") print
}
