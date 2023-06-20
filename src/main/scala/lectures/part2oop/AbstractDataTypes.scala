package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat: Unit = println("crunch crunch")
  }

  // traits (is like an interface)
  // unlike abstract, they can be inherited along classes
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeatl: String = "fresh meat"
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "croc"
    def eat: Unit = "nomnomnom"
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by same class
  // 3 - traits = behavior, abstract = "thing"

  // Scala's Type Hierarchy
  //                      scala.Any
  // scala.AnyVal  ------^        ^------ scala.AnyRef
  //      |                                   |
  // Int, Unit,                        String, List,
  // Boolean, Float...                 Set...
  //        |                              |
  //        |                          scala.Null
  //        ^------ scala.Nothing   ------^
}
