package booleanexpressionreducer

import booleanexpression.{And, BooleanExpression, False, Not, Or, True, Variable}

object BooleanExpressionReducer {
  private def absorptiveLaw(e1: BooleanExpression,
    e2: BooleanExpression,
    e11: BooleanExpression,
    e12: BooleanExpression,
    f: (BooleanExpression, BooleanExpression) => BooleanExpression):BooleanExpression = {
    e2 match {
      case `e11` => e2
      case `e12` => e2
      case _ => f(e1, e2)
    }
  }
  def reduce(e: BooleanExpression): BooleanExpression = {

    e match {
      case True => True
      case False => False
      case Variable(symbol) =>
        Variable(symbol)
      case Not(e) =>
        val r_e = reduce(e)
        r_e match {
          case True => False
          case False => True
          case Not(e1) => e1                        // Involution law (~(~e1) = e1)
          case _ => Not(r_e)
        }
      case And(e1, e2) =>
        val r_e1 = reduce(e1)
        val r_e2 = reduce(e2)
        r_e1 match {
          case True => r_e2                         // Identity law (True ^ r_e2 = r_e2)
          case False => False                       // Identity law (False ^ r_e2 = False)
          case `r_e2` => r_e1                       // Idempotent law (r_e1 ^ r_e1 = r_e1)
          case Not(`r_e2`) => False                 // Complement law (r_e1 ^ ~r_e1 = False)
          case Or(r_e11, r_e12) =>                  // Absorptive law ((r_e2 v x) ^ r_e2 = r_e2)
            absorptiveLaw(r_e1, r_e2, r_e11, r_e12, And)
          case _ =>
            r_e2 match {
              case True => r_e1                     // Identity law (r_e1 ^ True = r_e1)
              case False => False                   // Identity law (r_e1 ^ False = False)
              case Not(`r_e1`) => False             // Complement law (r_e1 ^ ~r_e1 = False)
              case Or(r_e21, r_e22) =>              // Absorptive law (r_e1 ^ (r_e1 v x) = r_e1)
                absorptiveLaw(r_e2, r_e1, r_e21, r_e22, And)
              case _ => And(r_e1, r_e2)
            }
        }
      case Or(e1, e2) =>
        val r_e1 = reduce(e1)
        val r_e2 = reduce(e2)
        r_e1 match {
          case True => True                         // Identity law (True v r_e2 = True)
          case False => r_e2                        // Identity law (False v r_e2 = r_e2)
          case `r_e2` => r_e1                       // Idempotent law (r_e1 v r_e1 = r_e1)
          case Not(`r_e2`) => True                  // Complement law (~r_e2 v r_e2 = True)
          case And(r_e11, r_e12) =>                 // Absorptive law ((r_e2 ^ x) v r_e2 = r_e2)
            absorptiveLaw(r_e1, r_e2, r_e11, r_e12, Or)
          case _ =>
            r_e2 match {
              case True => True                     // Identity law (r_e1 v True = True)
              case False => r_e1                    // Identity law (r_e1 v False = r_e1)
              case Not(`r_e1`) => True              // Complement law (r_e1 v ~r_e1 = True)
              case And(r_e21, r_e22) =>             // Absorptive law (r_e1 v (r_e1 ^ x) = r_e2)
                absorptiveLaw(r_e2, r_e1, r_e21, r_e22, Or)
              case _ => Or(r_e1, r_e2)
            }
        }
    }
  }
}
