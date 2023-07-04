package exercises

/**
 * MyList into Generic
 */
abstract class MyListGeneric[+A] {

  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation
  */

  def head: A
  def tail: MyListGeneric[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListGeneric[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object EmptyGeneric extends MyListGeneric[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyListGeneric[B] = new ConsGeneric(element, EmptyGeneric)

  override def printElements: String = ""
}

class ConsGeneric[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
  override def head: A = h
  override def tail: MyListGeneric[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyListGeneric[B] = new ConsGeneric(element, this)

  override def printElements: String =
    if(t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object ListGenericTest extends App {
  val listOfIntegers: MyListGeneric[Int] = new ConsGeneric(1, new ConsGeneric(2, new ConsGeneric(3, EmptyGeneric)))
  val listOfStrings: MyListGeneric[String] = new ConsGeneric("Hello", new ConsGeneric("I Love you", new ConsGeneric("Want U Tell Your Name?", EmptyGeneric)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
}
