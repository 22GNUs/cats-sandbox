package personal.wxh

object SuperAdds extends App {
  import cats.kernel.Monoid
  import cats.syntax.semigroup._
  def add[A: Monoid](items: List[A]): A = items.reduce(_ |+| _)

  import cats.instances.option._
  import cats.instances.int._
  println(add(List(Some(1), None)))

}
