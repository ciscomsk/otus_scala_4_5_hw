package homeworks.collections

import homeworks.HomeworksUtils.TaskSyntax

import scala.util.matching.Regex

object task_seq_riddle extends App {

  /**
   * Рассмотрим последовательность с числами:
   *
   * 1
   * 1 1
   * 2 1
   * 1 2 1 1
   * 1 1 1 2 2 1
   * 3 1 2 2 1 1
   * ...........
   *
   * 1. Реализуйте функцию генерирующую след последовательность из текущей
   * */

  def nextLine(currentLine: List[Int]): List[Int] = {
    val pattern: Regex = "(\\d)\\1*".r

    val currentString: String =
      currentLine
        .map(_.toString)
        .reduce(_ concat _)

    pattern
      .findAllIn(currentString)
      .toList
      .flatMap(str => List(str.length, str.head.toString.toInt))
  }

  /**
   * 2. Реализуйте ленивый список, который генерирует данную последовательность
   * Hint: см. LazyList.cons
   *
   * lazy val funSeq: LazyList[List[Int]]  ...
   *
   */

  //v1.1
  def ll(head: List[Int] = List(1)): LazyList[List[Int]] =
    head #:: ll(nextLine(head))

  lazy val funSeq1: LazyList[List[Int]] = ll()

  // v1.2
  lazy val funSeq: LazyList[List[Int]] = LazyList.cons(hd = List(1), tl = funSeq.map(nextLine))

}