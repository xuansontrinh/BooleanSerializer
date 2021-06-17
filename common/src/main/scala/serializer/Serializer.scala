package serializer

import play.api.libs.json._
import booleanexpression.{And, BooleanExpression, False, Not, Or, True, Variable}

/*
 * Object to perform (de-)serialization of boolean expressions' representations
 */
object Serializer {

  /*
   * Recursive helper function for converting BooleanExpression type to JsValue type
   */
  private def serializeUtil(e: BooleanExpression):JsValue = {
    e match {
      case True => JsObject(Seq("True" -> JsNull))
      case False => JsObject(Seq("False" -> JsNull))
      case Variable(symbol) => JsObject(Seq("Variable" -> JsString(symbol)))
      case Not(e) => JsObject(Seq("Not" -> serializeUtil(e)))
      case Or(e1, e2) => JsObject(Seq("Or" -> JsArray(IndexedSeq(serializeUtil(e1), serializeUtil(e2)))))
      case And(e1, e2) => JsObject(Seq("And" -> JsArray(IndexedSeq(serializeUtil(e1), serializeUtil(e2)))))
    }
  }

  /*
   * Main serialization function that calls the serializeUtil function and stringifies the result
   */
  def serialize(e: BooleanExpression):String = Json.stringify(serializeUtil(e))

  /*
   * Recursive helper function for converting valid JsValue type to BooleanExpression type
   */
  private def deserializeUtil(s: JsValue):BooleanExpression = try {
    s match {
      case JsObject(seq) => seq.keys.head match {
          case "True" => True
          case "False" => False
          case "Not" => Not(deserializeUtil(seq.values.head))
          case "Or" => Or(deserializeUtil(seq.values.head(0)), deserializeUtil(seq.values.head(1)))
          case "And" => And(deserializeUtil(seq.values.head(0)), deserializeUtil(seq.values.head(1)))
          case "Variable" => Variable(seq.values.head.as[String])
      }

    }
  } catch {
      case e: Throwable => {
        println(s"Problem when deserializing JsValue $s to BooleanExpression")
        println(s"The Throwable problem is as follows $e")
        False
      }
  }
  /*
   * Main serialization function that parses the Json string and calls the deserializeUtil function
   */
  def deserialize(s: String):BooleanExpression = deserializeUtil(Json.parse(s))
}
