package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahaahahahahaha")
  }

  /*
    The compiler make this behind the scenes
    equivalent with:

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahaahahahahaha")

    val funnyAnimal: Animal = new AnonymousClasses$$anon$1
    }
  */
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  // always use the same arguments of the super class
  val jim = new Person("Jim"){
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }

  jim.sayHi

  // Rules
  // - pass in required constructor arguments if needed
  // - implement all abstract fields/methods

  
}
