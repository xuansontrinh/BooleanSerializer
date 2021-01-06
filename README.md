# Boolean (De-)Serializer
App to (de-)serialize boolean expressions' representations

## Warmup session (irrelevant with the main task)
Implement the below function in Scala:
```
f(x) = f(x-1)+f(x-1)
f(0) = 1
```


The following is the naive solution based on the function's description:
```scala
def warmupFun(x: Int): Int = {
    x match {
        case y if y < 0 => -1
        case 0 => 1
        case _ => warmupFun(x - 1) + warmupFun(x - 1)
    }
}
```
This function has the time complexity O(2^x) and space complexity O(x) where x is the positive integer parameter of the function. This is due to the fact that this function solves the same sub-problems many times.

There are three ways to properly deal with this problem. One is to use memoization, i.e. to store the results of the sub-problems for saving processing time => O(x) time and space complexity. Another way is to suppress the two sub-problems into one because they are the same:

```scala
def warmupFunImproved(x: Int): Int = {
    x match {
        case y if y < 0 => -1
        case 0 => 1
        case _ => 2 * warmupFunImproved(x - 1)
    }
}
```
With this implementation, the time and complexity of the function is also reduced to linear O(x) but because of the use of recursion, the space complexity is still O(x). The third solution is to use a `for` loop instead of recursion. This can help the space complexity to reduce to constant O(1).

```scala
def warmupFunImproved2(x: Int): Int = {
    x match {
          case y if y < 0 => -1
          case 0 => 1
          case _ =>
                var res = 1
                for (m <- 1 to x) {
                    res = res * 2
                }
                res
    }
}
```

___Note___: As can be seen, this function's goal is to calculate the power of 2. Therefore, we can use the function `scala.math.pow` to work on the problem in O(log(x)) time and O(1) space.

## Scala Boolean (De-)Serializer
### Main Features
- [x] Serialization: Recursively converts the boolean expression to JSON string.
- [x] Deserialization: Recursively converts the JSON string back to the boolean expression.
- [x] Reduction: Recursively simplifies the boolean expression to a simpler version using some logic laws such as identity law, involution law, etc.
### Testing
- With the case of (de-)serialization, the test cases are presented to test the assertion:
```scala
assert(e === Serializer.deserialize(Serializer.serialize(e)))
```
, where the expression is serialized into a JSON string and then the JSON string is deserialized back and compared with the original expression.

- With the case of boolean expression reducer, it is tested against the logic laws that were used to create the reducer.
### Source code
All the source code files are presented in the `src/main/scala` directory, where:

- The definition of the boolean expression representation is in the package `booleanexpression`.
- The (de-)serializer is in the package `serializer`.
- The boolean expression reducer is in the package `booleanexpressionreducer`.

All the test files are presented in the `src/test` directory, where:

- The (de-)serializer tests are in the package `serializertest`.
- The boolean expression reducer tests are in the package `booleanexpressionreducertest`.