# 3 Reasons to love functional programming

---

# Algebraic reasoning

---

# Algebraic data types (ADT)

---

Algebraic data types are types composed from other types

They are algebraic because they form an algebra which means they have 3 things 
   - Data types
   - Operations
   - Laws
   
---

## Data Types
   - Primitive types like Int, String, Boolean etc
   - Other ADTs
   - Functions
   
---

## Operations
   - Sum / Coproduct (+)
   - Product (*)

---

## Laws
   - Identity
   - Associative
   - Commutative
   - Distributive
   
These are the same laws used for sum and product in number algebra

---

## Cardinality
   - Number of inhabitants of a type
   - Type is a set
   - Values are inhabitants
   
---

## Isomorphism
   - Equality for ADTs
   - Function from type A to type B
   - Function from type B to type A
   - No loss of information
    
```scala
def swap[A, B](tuple: (A, B)): (B, A) = (tuple._2, tuple._1)
```   

---

# Product types

---

## Product operations

   - \*
   - Both, All, Every
   
---

## In Scala

```scala
(String, Int)
``` 

---

## In Scala

```
case class Person(name: String, age: Int)
```

---

### Cardinality of product types

Cardinality of a product type is the product of the cardinality of the composed types

---

### Cardinality of product types

```scala
(Boolean, Byte)
```

---

### Cardinality of product types

   - Cardinality of __Boolean__ is 2
   - Cardinality of __Byte__ is 256
   - Cardinality of __(Boolean, Byte)__ is 512

---

### Cardinality of product types

```scala
(true, -128)
(false, -128)
(true, -127)
(false, -127)
// ...
(true, 126)
(false, 126)
(true, 127)
(false, 127)

```

---

### Laws: Identity

Right identity
```
A * 1 = A
```

Left identity
```
1 * A = A
```

---

### Laws: Identity

### Unit type

   - Unit is a type with only 1 inhabitant
   - You can always construct a Unit value
   - Unit is the identity type for product types

---

### Laws: Identity

```scala
(Boolean, Unit)
```

---

### Laws: Identity

```scala
(Boolean, Unit)
```

```scala
def toBoolean(tuple: (Boolean, Unit)): Boolean =
  tuple._1
```

---

### Laws: Identity

```scala
(Boolean, Unit)
```

```scala
def toTuple(boolean: Boolean): (Boolean, Unit) =
  (boolean, ())
```

---

### Laws: Associative

```
A * B * C = A * (B * C)
```

---

### Laws: Associative

```scala
(Boolean, String, Int)
```
is isomorphic to
```scala
(Boolean, (String, Int))
```

---

### Laws: Associative 

```scala
def toTuple(triplet: (Boolean, String, Int))
  : (Boolean, (String, Int)) =
(triplet._1, (triplet._2, triplet._3))
```

---

### Laws: Associative

```scala
def toTriplet(tuple: (Boolean, (String, Int)))
  : (Boolean, String, Int) =
(tuple._1, tuple._2._1, tuple._2._2)
```

---

### Laws: Commutative

```
A * B = B * A
```

---

### Laws: Commutative

```scala
(Boolean, String)
```
is isomorphic to
```scala
(String, Boolean)
```

---

### Laws: Commutative

```scala
def swap[A, B](tuple: (A, B)): (B, A) =
  (tuple._2, tuple._1)
```

---

# Sum types

(Sometimes called coproduct)

---

## Sum operations

   - \+ 
   - Or, One of, Either

---

## In scala

```scala
Either[String, Int]
```

---

## In scala

```scala
sealed trait Option[+A]

case class   Some[A](a: A) extends Option[A]
case object  None extends Option[Nothing]   
```

---

### Cardinality of sum types

Cardinality of a sum type is the sum of the cardinality of composed types

---

### Cardinality of sum types

```scala
Either[Boolean, Unit]
```

---

### Cardinality of sum types

   - Cardinality of __Boolean__ is 2
   - Cardinality of __Unit__ is 1
   - Cardinality of __Either[Boolean, Unit]__ is 3   

---

### Cardinality of sum types

```scala
Right(())
Left(true)
Left(false)
```

---

### Laws: Identity

Right identity
```
A + 0 = A
```

Left identity
```
0 + A = A
```

---

### Laws: Identity

### Nothing type

   - Bottom type in Scala
   - Nothing extends Everything
   - Cardinality is 0
   - Identity type for Sum types
   - Doesn't change cardinality

---

### Laws: Identity

```scala
Either[Nothing, Boolean]
```

---

### Laws: Identity

```scala
Either[Nothing, Boolean]
```

```scala
def fromEither(either: Either[Nothing, Boolean]): Boolean =
  either match {
    case Left(nothing)  => nothing
    case Right(boolean) => boolean
  } 
```

---

### Laws: Identity

```scala
Either[Nothing, Boolean]
```

```scala
def toEither(boolean: Boolean): Either[Nothing, Boolean] =
  Right(boolean) 
```

---

### Laws: Associative

```
A + (B + C) = A + B + C
```

---

### Laws: Associative

```scala
Either[Boolean, Either[String, Int]]
```
is isomorphic to
```scala
Either[Either[Boolean, String], Int]
```

---

### Laws: Associative

```scala
def assoc1(either: Either[Boolean, Either[String, Int]])
  : Either[Either[Boolean, String], Int] =
either match {
  
  case Left(boolean) => Left(Left(boolean))
    
  case Right(either2) => either2 match {
    
    case Left(string) => Left(Right(string))
      
    case Right(int) => Right(int)
  }
}
```

---

### Laws: Associative

```scala
def assoc2(either: Either[Either[Boolean, String], Int])
  : Either[Boolean, Either[String, Int]] =
either match {
      
  case Left(either2) => either2 match {
        
    case Left(boolean) => Left(boolean)
        
    case Right(string) => Right(Left(string))
  }
    
  case Right(int) => Right(Right(int))
}
```

---

### Laws: Commutative

```
A + B = B + A
```

---

### Laws: Commutative

```scala
Either[Boolean, String]
```
is isomorphic to
```scala
Either[String, Boolean]
```

---

### Laws: Commutative

```scala
def swap[A, B](either: Either[A, B]): Either[B, A] =
  either match {
    case Left(a)  => Right(a)
    case Right(b) => Left(b)
  }
```

---

### Why

   - Very powerful
   - Better express domain logic
   - Gives us a way to talk about these things in an abstract way
   - Refactor based on laws
   - Fearless refactoring
   
---

### Example refactoring

```scala
Either[HttpError, Option[User]]
```

---

### Example refactoring

```
HttpError + (User + 1) 
```

---

### Example refactoring

```
HttpError + (User + 1) = HttpError + (1 + User) 
```

---

### Example refactoring

```
HttpError + (User + 1) =
HttpError + (1 + User) =
(HttpError + 1) + User 
```

---

### Example refactoring

```scala
Either[Option[HttpError], User]
```

---

# Equational reasoning

---

# Pure Functions

---

### Functions in Scala

```scala
val f1: Int => String
val f2: Function[Int, String]
```

Functions in Scala are complete objects and can be assigned to variables 

---

### Methods in Scala

```scala
class A {
  def m(i: Int): String
}
```

Methods are part of a class and have a name and signature

---

## Math functions

   - Pure functions are like math functions
   - Can accept multiple arguments
   - Mapping from set __A__ to set __B__
   - __EVERY__ __A__ in __A__ maps to __1__ element in __B__ 

---

## Properties

   - Total
   - Deterministic
   - Free from side effects

If your function doesn't satisfy these properties you are not doing functional programming

---

## Totality

   - Every input must have a valid output
   - Easy to reason about
   - Return types don't lie

---

## Examples - Non-total

```scala
def divide1(a: Int, b: Int): Int = a / b
```

```scala
def parseInt1(s: String): Int = s.toInt
```

---

## Examples - Total

```scala
def divide2(a: Int, b: Int): Either[Exception, Int] =
  if(b == 0) Left(new ArithmeticException("Divide by zero"))
  else Right(a / b)
```

---

## Examples - Total

```scala
import scala.util.Try

def parseInt2(s: String): Option[Int] =
  Try(s.toInt).toOption
```

---

## Totality

   - We have expanded the output to incorporate the effect
   - Effects like exceptional behaviour or nullability
   - Enforces we handle effect
   - Makes intent explicit

---

## Determinism

   - Every input has the same output
   - Memoizable
   - Easy to test

---

## Examples - Non-Deterministic

```scala
def random1(): Int = scala.util.Random.nextInt
```

```scala
import java.time.LocalDateTime
def inAnHour1(): LocalDateTime = LocalDateTime.now.plusHours(1)
```

---

## Examples - Deterministic

```scala
def random2(seed: Int): (Int, Int) = {
  val r = ((seed * seed) * 0xb5ad4eceda1ce2a9L).toInt
  (r, r)
}
```

---

## Examples - Deterministic

```scala
import java.time.LocalDateTime
def inAnHour2(now: LocalDateTime): LocalDateTime =
  now.plusHours(1)
```

---

## Determinism

   - Provide seed values that determine result
   - Same output for input
   - Super easy to test

---

## No side effects

   - Function only evaluates output
   - If it doesn't affect the output it's a side effect
   - Easy to refactor
   - Invert control to the caller

---

## Example - Side effecting code

```scala
def welcomeMsg1(): Unit = {
    println("Welcome to the help page!")
    println("To list commands, type `commands`.")
    println("For help on a command, type `help <command>`")
    println("To exit the help page, type `exit`.")
}
```

---

## Example - No Side effects

```scala
def welcomeMsg2[A](
  println: String => A,
  combine: (A, A) => A): A =
List(
  println("Welcome to the help page!"),
  println("To list commands, type `commands`."),
  println("For help on a command, type `help <command>`"),
  println("To exit the help page, type `exit`.")
).reduce(combine)
```

---

## No side effects

   - Caller determines how to interpret
   - Very flexible
   - Super easy to test
   
---

## Referential transparency

   - Property of pure functional programming
   - Enables equational reasoning
   - Can substitute function call or value with implementation
   - Doesn't change meaning of program

---

## Examples - Referentially transparent

```scala
val one = 1
(one, one)
```
evaluates to
```scala
(1, 1)
```

---

## Examples - Referentially opaque

```scala
val doPrint = println("one")
(doPrint, doPrint)
```
evaluates to
```scala
// Console: one

((), ())
```

---

## Examples - Referentially opaque

```scala
(println("one"), println("one"))
```
evaluates to
```scala
// Console: one
// Console: one

((), ())
```

---

## Referentially transparent

   - Makes refactoring easy
   - You can __always__ inline functions and values
   - Easy to reason about
   - Reason using substitution
   
---

## Substitution

```scala
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}
```

---

## Substitution

```scala
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}
```

```scala
append(List(1, 2, 3), 4)
```

---

## Substitution

```scala
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}
```

```scala
append(List(1, 2, 3), 4)
1 :: append(List(2, 3), 4)
```

---

## Substitution

```scala
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}
```

```scala
append(List(1, 2, 3), 4)
1 :: append(List(2, 3), 4)
1 :: 2 :: append(List(3), 4)
```

---

## Substitution

```scala
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}
```

```scala
append(List(1, 2, 3), 4)
1 :: append(List(2, 3), 4)
1 :: 2 :: append(List(3), 4)
1 :: 2 :: 3 :: append(List(), 4)
```

---


## Substitution

```scala
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}
```

```scala
append(List(1, 2, 3), 4)
1 :: append(List(2, 3), 4)
1 :: 2 :: append(List(3), 4)
1 :: 2 :: 3 :: append(List(), 4)
1 :: 2 :: 3 :: 4 :: Nil
```

---

# Type based reasoning

---

## Scalazzi subset

   - Subset of scala
   - No java.lang.Object methods
   - No runtime type reflection
   - No nulls
   - No exceptions
   - scalafix conf enforces

---

## Polymorphic functions

   - Means many forms
   - Parameterised over some generic type
   - Not possible in scala
   - Can use polymorphic methods
   
---

## Monomorphic code

   - Code has only one form
   - Implementation is not constrained
   - Numerous ways to implement type signature
   - Numerous incorrect implementations
   
---

## Polymorphic code

   - Enables code reuse
   - Constrains the implementation
   - Few ways to implement type signature
   - Fewer ways to implement incorrectly
   - Greater reasoning capabilities
   
---

## Example - monomorphic code

```scala
def mutateString1(s: String): String = ???
``` 
   
---

## Example - monomorphic code

```scala
def mutateString2(s: String): String = s
```

---

## Example - monomorphic code
 
```scala
def mutateString3(s: String): String = s.toLowerCase
```

---

## Example - monomorphic code

```scala
def mutateString4(s: String): String = "Hello world"
```

---

## Example - monomorphic code

```scala
def mutateString5(s: String): String = s.reverse
```

---

## Example - polymorphic code

```scala
def function1[A](value: A): A = ???
```

---

## Example - polymorphic code

```scala
def function2[A](value: A): A = value
```

---

## Real world example

```scala
def flatMap1[A, B](fa: Option[A], f: A => Option[B])
  : Option[B] = ???
```

---

## Real world example
### Incorrect implementation

```scala
def flatMap2[A, B](fa: Option[A], f: A => Option[B])
  : Option[B] = None
```

---

## Real world example
### Correct implementation

```scala
def flatMap3[A, B](fa: Option[A], f: A => Option[B])
  : Option[B] =
fa match {
  case Some(value) => f(value)
  case None        => None
}
```

---

## Real world example
### Monomorphic

```scala
def flatMap4(fa: Option[Int], f: Int => Option[String])
  : Option[String] = ???
```

---

# Adding behaviour

---

## Typeclasses

Data types with operations on those types and laws governing the operations

   - Ad-hoc polymorphism
   - Interface that defines some behaviour
   - Add behaviour to polymorphic functions
   - Eq, Ord, Show, Semigroup
   
---

## Monad typeclass

```scala
trait Monad[F[_]] {
  
  def pure[A](value: A): F[A]
  
  def flatMap[A, B](fa: F[A], f: A => F[B]): F[B]
  
  def map[A, B](fa: F[A], f: A => B): F[B] =
    flatMap(fa, (a: A) => pure(f(a)))
}
```

---

## Implement zip in terms of Monad

```scala
def zip1[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] = ???
```

---

## Implement zip in terms of Monad

```scala
def zip2[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] =
  fa.flatMap(a => fb.map(b => (a, b)))
```

---

## In Summary

Functional programming gives us very powerful ways to reason about our code

   - Algebraic reasoning
   - Equational reasoning
   - type based reasoning  

---

Questions