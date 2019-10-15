package personal.wxh

import java.time.LocalDateTime

import personal.wxh.json.{JsObject, Json, JsonWriter, JsonWriterInstance}

object Main extends App {
  class Person(val name: String, val birthday: LocalDateTime)
  final case class Coder(override val name: String,
                         override val birthday: LocalDateTime,
                         code: String)
      extends Person(name, birthday)

  object MyJsonWriterInstance extends JsonWriterInstance {
    implicit def personWriter: JsonWriter[Person] =
      p =>
        JsObject(
          Map[String, Json](
            "name" -> Json(p.name),
            "birthday" -> Json(p.birthday)
          )
      )

    implicit def coderWriter: JsonWriter[Coder] =
      c =>
        JsObject(
          Map[String, Json](
            "name" -> Json(c.name),
            "birthday" -> Json(c.birthday),
            "code" -> Json(c.code)
          )
      )
  }
  import MyJsonWriterInstance._
  val coder = Coder("monkey", LocalDateTime.now(), "scala")
  // 因为JsonWriter是逆变的, 所有这里的personWriter是coderWriter的子类, 可以用personWriter来处理coder
  println(Json(coder))

}
