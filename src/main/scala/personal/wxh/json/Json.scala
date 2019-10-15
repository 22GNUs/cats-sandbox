package personal.wxh.json

import java.time.LocalDateTime

/**
  *@author wangxinhua
  *@since 1.0
  */
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
final case class JsLocalDateTime(get: LocalDateTime) extends Json
case object JsNone extends Json

object Json {
  def apply[A: JsonWriter](value: A): Json =
    implicitly[JsonWriter[A]].write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json = w.write(value)
  }
}

/**
  * 把某个类型的值序列化为Json
  */
trait JsonWriter[-A] {
  def write(value: A): Json
}
