trait Monad[F[_]] {

  def pure[A](value: A): F[A]

  def flatMap[A, B](fa: F[A], f: A => F[B]): F[B]

  def map[A, B](fa: F[A], f: A => B): F[B] =
    flatMap(fa, (a: A) => pure(f(a)))
}

object Ops {

  implicit class MonadOps[F[_], A](val fa: F[A]) extends AnyVal {

    def flatMap[B](f: A => F[B])(implicit M: Monad[F]): F[B] = M.flatMap(fa, f)

    def map[B](f: A => B)(implicit M: Monad[F]): F[B] = M.map(fa, f)


  }
}

import Ops._

def zip1[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] = ???

def zip2[F[_]: Monad, A, B](fa: F[A], fb: F[B]): F[(A, B)] = fa.flatMap(a => fb.map(b => (a, b)))
