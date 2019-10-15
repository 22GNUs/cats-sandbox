package personal.wxh.print

/**
@author wangxinhua
@since 1.0
  */
final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit def catInstance: Printable[Cat] =
    cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat."
}
