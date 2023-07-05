package lectures.part2oop

object CaseClasses extends App {

  /*
    equal, hashCode, toString
  */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 37)
  println(jim.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim)

  // 3. equal and hashCode implemented Out Of The Box (OOTB)
  val jim2 = new Person("Jim", 37)
  println(jim == jim2)

  // 4. Case classes have handy copy method
  val jim3 = jim.copy(age = 40)
  println(jim3)

  // 5. Case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. Case classes are serializable
  // Akka - messages are in general Case classes

  // 7. Case classes have extractor patterns = can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

}
