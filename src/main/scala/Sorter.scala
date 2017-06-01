
import scala.reflect.ClassTag

/**
  * Created by Administrator on 2017/5/31.
  */
object Sorter {
  def MergerSort[T:Ordering:ClassTag](arr: Array[T],maxLevel:Int = 0) ={
    val tempArr = new Array[T](arr.size)
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
    def merger(src:Array[T],dest:Array[T],begin:Int,mid:Int,end:Int){
      var j = mid + 1
      var i = begin
      for(h <- begin to end){
        if(i<=mid && j<=end){
          if (implicitly[Ordering[T]].lteq(src(i),src(j))){
            dest(h) = src(i)
            i += 1
          }else{
            dest(h) = src(j)
            j += 1
          }
        }else if(i<=mid){
          dest(h) = src(i)
          i += 1
        }else{
          dest(h) = src(j)
          j += 1
        }
      }


    }
    def mergerSortPart(begin: Int, end: Int,level:Int) {

      if(level == maxLevel){
        popSort(begin,end)
      }else {
        val mid = (begin + end) / 2
        mergerSortPart(begin,mid,level+1)
        mergerSortPart(mid+1,end,level+1)
        val (src,dest) = if((maxLevel - level)% 2 ==1) (arr,tempArr) else (tempArr,arr)
        merger(src,dest,begin,mid,end)

      }
    }
    mergerSortPart(0,arr.size - 1,0)
    if(maxLevel%2 == 1)
      tempArr.copyToArray(arr)
  }

  def main(args: Array[String]) {
    val k = Array(5,3,1,6,9,4,8,7,6)
    MergerSort(k,10)
    k.foreach(print)
  }
}
