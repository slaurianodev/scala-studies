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

object Empty extends MyListGeneric[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyListGeneric[B] = new Cons(element, Empty)

  override def printElements: String = ""
}

class Cons[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
  override def head: A = h
  override def tail: MyListGeneric[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyListGeneric[B] = new Cons(element, this)

  override def printElements: String =
    if(t.isEmpty) "" + h
    else s"$h ${t.printElements}"
}

object ListTest extends App {
  val listOfIntegers: MyListGeneric[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfStrings: MyListGeneric[String] = new Cons("Hello", new Cons("I Love you", new Cons("Want U Tell Your Name?", Empty)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
}
