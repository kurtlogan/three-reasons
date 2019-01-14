
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