import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


val future = Future { println("one") }

for {
  f1 <- future
  f2 <- future
} yield (f1, f2)


for {
  f1 <- Future { println("one") }
  f2 <- Future { println("one") }
} yield (f1, f2)



val some = Some("one")

for {
  s1 <- some
  s2 <- some
} yield (s1, s2)


for {
  s1 <- Some("one")
  s2 <- Some("one")
} yield (s1, s2)