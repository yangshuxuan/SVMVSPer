
import scala.reflect.ClassTag

/**
  * Created by Administrator on 2017/5/31.
  */
object SomeSortOpt {


  def MergerSort[T:Ordering:ClassTag](arr: Array[T],limit:Int = 1) ={
    def popSort(begin:Int,end:Int): Unit ={
      val dest = new Array[T](end - begin + 1)
      for{
        i <- begin to end
        j <- (i+1) to end
        if implicitly[Ordering[T]].gt(arr(i),arr(j))
      }{
        val m = arr(i)
        arr(i) = arr(j)
        arr(j) = m
      }

    }
    def merger(begin:Int,mid:Int,end:Int){
      var dest = new Array[T](end - begin + 1)
      var j = mid + 1
      var i = begin
      for(h <- 0 to (end - begin)){
        if(i<=mid && j<=end){
          if (implicitly[Ordering[T]].lteq(arr(i),arr(j))){
            dest(h) = arr(i)
            i += 1
          }else{
            dest(h) = arr(j)
            j += 1
          }
        }else if(i<=mid){
          dest(h) = arr(i)
          i += 1
        }else{
          dest(h) = arr(j)
          j += 1
        }

      }
      dest.copyToArray(arr,begin)

    }
    def mergerSortPart(begin: Int, end: Int) {

      if(end - begin <= limit){
        popSort(begin,end)
      }else {
        val mid = (begin + end) / 2
        mergerSortPart(begin,mid)
        mergerSortPart(mid+1,end)
        merger(begin,mid,end)

      }
    }
    mergerSortPart(0,arr.size - 1)



  }

  def main(args: Array[String]) {
    val k = Array(5,3,1,6,9,4,8,7,6)
    MergerSort(k)
    k.foreach(print)
  }

}
