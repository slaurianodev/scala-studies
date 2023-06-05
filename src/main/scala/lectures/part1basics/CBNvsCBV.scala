package lectures.part1basics

/**
 * Called By Name (CBN)
 * x
 * Called By Value (CBV)
 */
object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = { // Value is computed before call. Same value used everywhere.
    println("by value: " + x)
    println("by value: " + x)
  }

  def calledByName(x: => Long): Unit = { // Expression is passed literally. Expression is evaluated at every use within.
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

//  printFirst(infinite(), 34) don't do that
printFirst(34, infinite())
}
