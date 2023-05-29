package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // EXPRESSION
  println(x)

  println(2 + 3 * 4)
  // common expressions types
  //+ - * / & | ^ << >> >>>

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ... remember side effects!
  println(aVariable)

  // Instructions (DO something) x Expressions (give me the VALUE of something)

  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }
  // AVOID use loops in Scala

  // EVERYTHING in Scala is an Expression!

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // Examples of side effects: println(), whiles, reassigning

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }

}
