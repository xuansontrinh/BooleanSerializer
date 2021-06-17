package serializertest

import booleanexpression.{And, BooleanExpression, False, Not, Or, True, Variable}
import org.scalatest.FunSuite
import serializer.Serializer

/*
 * The class SerializerTest is to test functionalities of the Serializer, i.e. (de-)serialization
 */
class SerializerTest extends FunSuite {

  // Initialize the test cases from basic to more complex
  val test_cases: Map[String, BooleanExpression] = Map(
    "Serialize/Deserialize True" -> True,
    "Serialize/Deserialize False" -> False,
    "Serialize/Deserialize Single Variable" -> Variable("P"),
    "Serialize/Deserialize Not" -> Not(Variable("P")),
    "Serialize/Deserialize Or" -> Or(Variable("P"), Variable("Q")),
    "Serialize/Deserialize And" -> And(Variable("P"), Variable("Q")),
    "Serialize/Deserialize Composite" -> Not(Or(And(And(True, Variable("P")), Or(False, Variable("Q"))), Variable("R")))
  )
  for (test_case <- test_cases) {
    test(test_case._1) {
      assert(test_case._2 === Serializer.deserialize(Serializer.serialize(test_case._2)))
    }
  }
}
