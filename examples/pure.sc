import scala.util.Try

def divide1(a: Int, b: Int): Int = a / b

def parseInt1(s: String): Int = s.toInt

def divide2(a: Int, b: Int): Either[Exception, Int] =
  if(b == 0) Left(new ArithmeticException("Divide by zero "))
  else Right(a / b)

def parseInt2(s: String): Option[Int] =
  Try(s.toInt).toOption

def random1(): Int = scala.util.Random.nextInt

import java.time.LocalDateTime
def inAnHour1(): LocalDateTime = LocalDateTime.now.plusHours(1)

def random2(seed: Int): Int =
  ((seed * seed) * 0xb5ad4eceda1ce2a9L).toInt

import java.time.LocalDateTime
def inAnHour2(now: LocalDateTime): LocalDateTime = now.plusHours(1)

def welcomeMsg1(): Unit = {
  println("Welcome to the help page!")
  println("To list commands, type `commands`.")
  println("For help on a command, type `help <command>`")
  println("To exit the help page, type `exit`.")
}

def welcomeMsg2[A](println: String => A, combine: (A, A) => A): A =
  List(
    println("Welcome to the help page!"),
    println("To list commands, type `commands`."),
    println("For help on a command, type `help <command>`"),
    println("To exit the help page, type `exit`.")
  ).reduce(combine)



