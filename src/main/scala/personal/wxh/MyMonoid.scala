package personal.wxh

trait Semigroup[A] {
  def combine(a: A, b: A): A
}

trait MyMonoid[A] extends Semigroup[A] {
  def empty: A
}

object MyMonoid {
  def apply[A](implicit m: MyMonoid[A]): MyMonoid[A] = m
}

object MonoidInstances {
  implicit val intMonoid: MyMonoid[Int] = new MyMonoid[Int] {
    override def empty: Int = 0
    override def combine(a: Int, b: Int): Int = a + b
  }
}

object MonoidSyntax {
  implicit class MonoidOps[A](value: A) {
    // 从上下文中获取monoid实例
    def toMonoid(implicit m: MyMonoid[A]): MyMonoid[A] = MyMonoid.apply[A]

    def |+|(other: A)(implicit m: MyMonoid[A]): A = m.combine(value, other)
  }
}
