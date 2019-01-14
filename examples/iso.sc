
def toBoolean(tuple: (Boolean, Unit)): Boolean =
  tuple._1

def toTuple(boolean: Boolean): (Boolean, Unit) =
  (boolean, ())

def toTuple(triplet: (Boolean, String, Int)): (Boolean, (String, Int)) =
  (triplet._1, (triplet._2, triplet._3))

def toTriplet(tuple: (Boolean, (String, Int))): (Boolean, String, Int) =
  (tuple._1, tuple._2._1, tuple._2._2)

def swap[A, B](tuple: (A, B)): (B, A) =
  (tuple._2, tuple._1)

def fromEither(either: Either[Nothing, Boolean]): Boolean =
  either match {
    case Left(nothing)  => nothing
    case Right(boolean) => boolean
  }

def toEither(boolean: Boolean): Either[Nothing, Boolean] =
  Right(boolean)

def assoc1(either: Either[Boolean, Either[String, Int]]): Either[Either[Boolean, String], Int] =
  either match {

    case Left(boolean)  => Left(Left(boolean))

    case Right(either2) => either2 match {

      case Left(string) => Left(Right(string))

      case Right(int)   => Right(int)
    }
  }

def assoc2(either: Either[Either[Boolean, String], Int]): Either[Boolean, Either[String, Int]] =
  either match {

    case Left(either2) => either2 match {

      case Left(boolean) => Left(boolean)

      case Right(string) => Right(Left(string))
    }
    case Right(int) => Right(Right(int))
  }

def swap[A, B](either: Either[A, B]): Either[B, A] =
  either match {
    case Left(a)  => Right(a)
    case Right(b) => Left(b)
  }