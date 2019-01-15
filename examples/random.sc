
// Interesting ...
Long.MaxValue + 1 == Long.MinValue

val l = 0xb5ad4eceda1ce2a9L

def random(seed: Int): Int =
  ((seed * seed) * 0xb5ad4eceda1ce2a9L).toInt


random(1)
random(2)
random(3)
random(4)
random(5)
random(6)
random(7)
random(8)
random(9)
random(10)
random(random(13))