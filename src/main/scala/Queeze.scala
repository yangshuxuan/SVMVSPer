/**
  * Created by Administrator on 2017/5/27.
  */
object Queeze {
  //首先要考虑的是如何表示信息，如果表示不合适，后面可以再修改
  //每个排列可以用List表示，每个位置用坐标表示（x,y)
  //所有排列可以用List[List[(Int,Int)]]
  //用n表示纵横的行列数
  val size=4
  def apply(n:Int):Traversable[List[(Int,Int)]]={
    if(n==0) Traversable(List[(Int,Int)]())
    else
      for{
        i <- 1 to size
        p=(n,i)
        j <- Queeze(n-1)
        if safe(p,j)
      } yield p :: j
  }
  def safe(p:(Int,Int),v:List[(Int,Int)])={
     v.forall(t => t._2 != p._2 && (t._1 - p._1).abs != (t._2 -p._2).abs)

  }

  def main(args: Array[String]) {
    val r = Queeze(1)
    r.foreach(println)
  }
}
