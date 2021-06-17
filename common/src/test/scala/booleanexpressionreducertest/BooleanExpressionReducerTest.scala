package booleanexpressionreducertest

import booleanexpression.{And, BooleanExpression, False, Not, Or, True, Variable}
import booleanexpressionreducer.BooleanExpressionReducer
import org.scalatest.FunSuite

class BooleanExpressionReducerTest extends FunSuite {
  // Initialize the test cases
  val test_cases: Map[String, (BooleanExpression, BooleanExpression)] = Map(
    "Reduce by identity law (True ^ P)" -> (And(True, Variable("P")), Variable("P")),
    "Reduce by identity law (False ^ P)" -> (And(False, Variable("P")), False),
    "Reduce by identity law (True v P)" -> (Or(True, Variable("P")), True),
    "Reduce by identity law (False v P)" -> (Or(False, Variable("P")), Variable("P")),
    "Involution law (~(~P) = P)" -> (Not(Not(Variable("P"))), Variable("P")),
    "Complement law (P ^ ~P = False)" -> (And(Variable("P"), Not(Variable("P"))), False),
    "Complement law (P v ~P = True)" -> (Or(Variable("P"), Not(Variable("P"))), True),
    "Absorptive law (P ^ (P v Q) = P)" -> (And(Variable("P"), Or(Variable("P"), Variable("Q"))), Variable("P")),
    "Absorptive law (P v (P ^ Q) = P)" -> (Or(Variable("P"), And(Variable("P"), Variable("Q"))), Variable("P")),
    "Miscellaneous (composition from previous ones) 1" -> (And(True, And(False, Variable("P"))), False),
    "Misc 2" -> (And(Not(Not(Variable("P"))), Or(Not(Not(Variable("P"))), Variable("Q"))), Variable("P")),
    "Misc 3" -> (Or(And(Variable("P"), Or(Variable("P"), Variable("Q"))), And(And(Variable("P"), Or(Variable("P"), Variable("Q"))), Variable("Q"))), Variable("P"))
  )

  for (test_case <- test_cases) {
    test(test_case._1) {
      assert(BooleanExpressionReducer.reduce(test_case._2._1) === test_case._2._2)
    }
  }
}
