package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x = 42
  println(x)

  // VALS ARE IMMUTABLE (CONSTANT)

  // Compiler can infer types, isn't necessary declare it.

  val aString: String = "Howdy"
  val anotherString = "See ya"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 1234567980123456789L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4

  aVariable = 5 // variables have side effects in FP (Functional Programming)
  

}
