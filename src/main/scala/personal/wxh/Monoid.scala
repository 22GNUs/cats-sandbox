package personal.wxh

trait Semigroup[A] {
  def combine(a: A, b: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit m: Monoid[A]) = m
}

object MonoidInstances {
  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0
    override def combine(a: Int, b: Int): Int = a + b
  }
}

object MonoidSyntax {
  implicit class MonoidOps[A](value: A) {
    // 从上下文中获取monoid实例
    def toMonoid(implicit m: Monoid[A]): Monoid[A] = Monoid.apply[A]

    def |+|(other: A)(implicit m: Monoid[A]): A = m.combine(value, other)
  }
}
