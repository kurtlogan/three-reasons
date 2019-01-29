
def append[A](list: List[A], x: A): List[A] = list match {
  case Nil    => x :: Nil
  case h :: t => h :: append(t, x)
}


// append(List(1, 2, 3), 4)
// 1 :: append(List(2, 3), 4)
// 1 :: 2 :: append(List(3), 4)
// 1 :: 2 :: 3 :: append(List(), 4)
// 1 :: 2 :: 3 :: 4 :: Nil