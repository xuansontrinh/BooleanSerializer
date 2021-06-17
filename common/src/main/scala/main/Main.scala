package main

import booleanexpression.{And, BooleanExpression, False, Not, Or, True, Variable}
import play.api.libs.json._
import serializer.Serializer
import booleanexpressionreducer.BooleanExpressionReducer
import warmup.WarmUpTask

object Main extends App {
  val e1: BooleanExpression = Not(And(And(False, Variable("P")), Or(True, Variable("Q"))))
  val e2: BooleanExpression = And(Variable("P"), Or(Variable("Q"), Variable("P")))
  val s = Serializer.serialize(e1)

  println(e1)
  println(BooleanExpressionReducer.reduce(e1))
  println(Serializer.serialize(e1))
  println(Serializer.deserialize(Serializer.serialize(e1)))
  println(e1 == Serializer.deserialize(Serializer.serialize(e1)))
  println(BooleanExpressionReducer.reduce(e2))
  println("---------------------")
  println(WarmUpTask.warmupFunImproved2(1))
}
