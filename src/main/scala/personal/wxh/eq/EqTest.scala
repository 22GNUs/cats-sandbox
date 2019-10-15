package personal.wxh.eq

import cats.Eq
import personal.wxh.print.Cat

/**
  *@author wangxinhua
  *@since 1.0
  */
object EqTest extends App {
  import cats.instances.option._
  import cats.syntax.eq._

  implicit val catEq: Eq[Cat] = Eq.instance[Cat] { (c1, c2) =>
    c1.name == c2.name
  }

  Option.empty[Cat] === Option(Cat("Mia", 2, "yellow"))
}
