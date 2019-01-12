# 3 Reasons to love functional programming

---

# But first

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
   - Number the of inhabitants of a type
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

### Laws of sum types


---

---

#Equational reasoning


---

#Type based reasoning
