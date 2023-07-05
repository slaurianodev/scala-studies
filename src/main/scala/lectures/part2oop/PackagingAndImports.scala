package lectures.part2oop

import playground.{Sith, Jedi as Master}

import java.sql
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package member are accessible bye their simple name
  val writer = new Writer("Sergio", "Lauriano", 2023)

  // import package
  val luke = new Master

  // package are in hierarchy
  // matching folder structure

  // Scala specific
  // package object

  sayHello
  println(HAN_SOLO_PARSECS_KM)

  // imports
  val anakin = new Sith

  // 1. use full qualified name
  // 2. use aliasing the import
  val date = new Date(2023, 1,1)
  val sqlDate = new SqlDate(2023, 1, 1)

  // default imports
  // java.lang - String, object, exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
