package personal.wxh.json

import java.time.LocalDateTime

/**
  *@author wangxinhua
  *@since 1.0
  */
trait JsonWriterInstance {

  implicit def stringWriter: JsonWriter[String] = JsString(_)

  implicit def localDateTimeWriter: JsonWriter[LocalDateTime] =
    JsLocalDateTime(_)

  implicit def optionWriter[A: JsonWriter]: JsonWriter[Option[A]] = {
    case Some(v) => implicitly[JsonWriter[A]].write(v)
    case None    => JsNone
  }
}
