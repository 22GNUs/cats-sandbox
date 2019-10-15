package personal.wxh

import scala.language.higherKinds

// F[_] 表示有一个类型参数的类型
trait MyFunctor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object FunctorSyntax {
  implicit class FunctorOps[F[_], A](src: F[A]) {
    def fmap[B](func: A => B)(implicit functor: MyFunctor[F]): F[B] =
      functor.map(src)(func)
  }
}

object FunctorInstances {
  implicit val optionFunctor = new MyFunctor[Option] {
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa map f
  }
}

/**
 * notes:
 * Option(1) fmap {_ + 1} 可以通过编译
 * Some(1) fmap {_ + 1} 则不行
 * 必须的时候需要通过泛型方法封装一层帮助编译器做类型推导
 */
