package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length)
  // this will crash with Null Pointer Exception

  //1. throwing exceptions

//  val weirdValue: String = throw new NullPointerException

  // throwable classes extends the Throwable class
  // Exception and Error are the major Throwable subtype

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will execute no matter what
    println("finally")
  }

  println(potentialFail)

  // 3. How to define your onw exceptions
  class MyException extends Exception
  val exception = new MyException

  throw exception
}


