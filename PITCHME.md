# 3 Reasons to love functional programming

---

```scala
def updateSet(set: Set[java.net.URL], url: Set[java.net.URL]): Set[java.net.URL] =
  set ++ url
```

---

# Algebraic reasoning

---

# Algebraic data types (ADTs)

---

algebraic data types are types composed from other types

they are algebraic because they form an algebra which means they have 3 things 
   - data types
   - operations
   - laws
   
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
   - Identity - `x + 0 = x` | `x * 1 = x`
   - Associative - `a + b + c = a + (b + c)` | `a * b * c = a * (b * c)`
   - Commutative - `a + b = b + a` | `a * b = b * a`
   - Distributive - `(a * b) + (a * c) = a(b + c)`
   
these are the same laws used for sum and product in number algebra

---

# But first

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

   - Cardinality of Boolean is 2
   - Cardinality of Byte is 256
   - Cardinality of (Boolean, Byte) is 512

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
A * 0 = A
```

Left identity
```
0 * A = A
```

---

### Laws: Identity

#### Unit type

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
def toTuple(triplet: (Boolean, String, Int)): (Boolean, (String, Int)) =
  (triplet._1, (triplet._2, triplet._3))
```

---

### Laws: Associative

```scala
def toTriplet(tuple: (Boolean, (String, Int))): (Boolean, String, Int) =
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

(Sometimes called co-product)

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

   - Cardinality of `Boolean` is 2
   - Cardinality of `Unit` is 1
   - Cardinality of `Either[Boolean, Unit]` is 3   

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

#### Nothing type

   - Bottom type in scala
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
def assoc1(either: Either[Boolean, Either[String, Int]]): Either[Either[Boolean, String], Int] =
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
def assoc2(either: Either[Either[Boolean, String], Int]): Either[Boolean, Either[String, Int]] =
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

#Equational reasoning


---

#Type based reasoning
