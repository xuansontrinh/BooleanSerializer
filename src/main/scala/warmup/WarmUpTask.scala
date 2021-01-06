package warmup

object WarmUpTask {
  /*
   * This warmup function is to calculate the power of 2, i.e. f(0) = 1, f(1) = 2, f(2) = 4
   * The big-O time complexity is O(2^x), space complexity O(x) where x is the positive integer parameter of the function
   */
  def warmupFun(x: Int): Int = {
    x match {
      case y if y < 0 => -1
      case 0 => 1
      case _ => warmupFun(x - 1) + warmupFun(x - 1)
    }
  }

  /*
   * This improved version reduces the time complexity to O(x) but space is still O(x)
   */
  def warmupFunImproved(x: Int): Int = {
    x match {
      case y if y < 0 => -1
      case 0 => 1
      case _ => 2 * warmupFunImproved(x - 1)
    }
  }

  /*
   * This further improved the function above and achieves the space O(1)
   */
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
}
