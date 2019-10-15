package personal.wxh.print

import cats.Show

/**
  * @author wangxinhua
  * @since 1.0
  */
object ShowTest extends App {
  implicit val catShow: Show[Cat] =
    cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."

  import cats.syntax.show._
  println(Cat("Mia", 2, "black").show)
}
