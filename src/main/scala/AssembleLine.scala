/**
  * Created by Administrator on 2017/6/2.
  */
object AssembleLine {
  /*val cache = collection.mutable.HashMap.empty[(Int,Int),NodeOpt]
  case class NodeOpt(delay:Int,line:Int,level:Int,path:List[NodeOpt])
  val endLevel = 5
  val t1 = Array(1,2,3,4)
  val t2 = Array(3,8,6,7)
  val d1 = Array(3,8,6,3)
  val d2 = Array(2,8,9,3)
  def findOptPath(enter1:Int,enter2:Int): Unit ={

  }
  def optEquip(line:Int,level:Int):NodeOpt={
    cache.get((line,level)) match{
      case Some(t) => t
      case None => {
        if(level==0){
          NodeOpt(0,line,level,List.empty[NodeOpt])
        }else {


          val left = optEquip(0, level - 1)
          val right = optEquip(1, level - 1)
          val h = if (line == 0) {
            if (left.delay <= right.delay + t2(level - 1)) {
              NodeOpt(left.delay + d1(level), line, level, left :: left.path)
            } else {
              NodeOpt(right.delay + d1(level) + t2(level - 1), line, level, right :: left.path)
            }
          } else {
            if (left.delay <= right.delay + t2(level - 1)) {
              NodeOpt(left.delay + d1(level), line, level, left :: left.path)
            } else {
              NodeOpt(right.delay + d1(level) + t2(level - 1), line, level, right :: left.path)
            }
          }
          cache += ((line, level) -> h)
          h
        }
      }
    }

  }
  def result(): NodeOpt ={
    val left = optEquip(0,endLevel)
    val right = optEquip(1,endLevel)
    if(left.delay <= right.delay) left else right
  }
*/

}
