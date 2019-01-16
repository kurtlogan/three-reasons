
def mutateString1(s: String): String = ???

def mutateString2(s: String): String = s

def mutateString3(s: String): String = s.toLowerCase

def mutateString4(s: String): String = "Hello world"

def mutateString5(s: String): String = s.reverse

def function1[A](value: A): A = ???

def function2[A](value: A): A = value

def flatMap1[A, B](fa: Option[A], f: A => Option[B]): Option[B] = ???

def flatMap2[A, B](fa: Option[A], f: A => Option[B]): Option[B] = None

def flatMap3[A, B](fa: Option[A], f: A => Option[B]): Option[B] = fa match {
  case Some(value) => f(value)
  case None        => None
}

def flatMap4(fa: Option[Int], f: Int => Option[String]): Option[String] = ???

