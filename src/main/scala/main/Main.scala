package main

import booleanexpression.{And, BooleanExpression, False, Not, Or, True, Variable}
import play.api.libs.json._
import serializer.Serializer

object Main extends App {
  val f: BooleanExpression = And(False, Variable("P"))
  val t: BooleanExpression = Or(True, Variable("Q"))
  val n: BooleanExpression = Not(And(f, t))
  val s = Serializer.serialize(n)

  println(n)
  println(Serializer.serialize(n))
  println(Serializer.deserialize(Serializer.serialize(n)))
  println(n == Serializer.deserialize(Serializer.serialize(n)))
}
