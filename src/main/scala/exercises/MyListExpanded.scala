package exercises

/**
 * MyList into Generic
 */
/*
  Exercise
  1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3. MyList:
      - map(transformer) => MyList
      - filter(predicate) => MyList
      - flatMap(transformer from A to MyList[B]) => MyList[B]

      class EvenPredicate extends MyPredicate[Int]
      class StringToIntTransformer extends MyTransformer[String, Int]

      [1,2,3].map(n * 2) = [2,4,6]
      [1,2,3,4].filter(n % 2) = [2,4]
      [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
*/
abstract class MyListExpanded[+A] {

  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation
  */

  def head: A
  def tail: MyListExpanded[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListExpanded[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyListExpanded[B]
  def flatMap[B](transformer: MyTransformer[A, MyListExpanded[B]]): MyListExpanded[B]
  def filter(predicate: MyPredicate[A]): MyListExpanded[A]

  def ++[B >: A](list: MyListExpanded[B]): MyListExpanded[B]

}

case object Empty extends MyListExpanded[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyListExpanded[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyListExpanded[B] = new Cons(element, Empty)

  override def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListExpanded[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyListExpanded[B]]): MyListExpanded[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyListExpanded[Nothing] = Empty

  def ++[B >: Nothing](list: MyListExpanded[B]): MyListExpanded[B] = list
}

  case class Cons[+A](h: A, t: MyListExpanded[A]) extends MyListExpanded[A] {
  override def head: A = h
  override def tail: MyListExpanded[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyListExpanded[B] = new Cons(element, this)

  override def printElements: String =
    if(t.isEmpty) "" + h
    else s"$h ${t.printElements}"

  /*
    [1,2,3].filter(n % 2 == 0) =
      [2,3].filter(n % 2 == 0) =
      = new Cons(2, [3].filter(n % 2 == 0))
      = new Cons(2, Empty.filter(n % 2 == 0))
      = new Cons(2, Empty)
  */
  def filter(predicate: MyPredicate[A]): MyListExpanded[A] =
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6,Empty))))
  */
  def map[B](transformer: MyTransformer[A, B]): MyListExpanded[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  /*
    [1,2] ++ [3,4]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
  */
  def ++[B >: A](list: MyListExpanded[B]): MyListExpanded[B] = new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
  */
  def flatMap[B](transformer: MyTransformer[A, MyListExpanded[B]]): MyListExpanded[B] =
    transformer.transform(h) ++ t.flatMap(transformer)
}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(elem: A): B
}

object ListExpandedTest extends App {
  val listOfIntegers: MyListExpanded[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonelistOfIntegers: MyListExpanded[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyListExpanded[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyListExpanded[String] = new Cons("Hello", new Cons("I Love you", new Cons("Want U Tell Your Name?", Empty)))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  // Using anonymous class
  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListExpanded[Int]] {
    override def transform(elem: Int): MyListExpanded[Int] = new Cons(elem, new Cons(elem +1, Empty))
  }).toString)

  // case classes implements equals by default :)ß
  println(clonelistOfIntegers == listOfIntegers)
}
