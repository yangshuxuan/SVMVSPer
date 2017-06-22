/**
  * Created by Administrator on 2017/6/2.
  */
package adups
abstract class Node{
  val name:String
}
case object beginNode extends Node{
  val name = "BEGIN"
}
case class Transfer(delay:Int,prve:Node)
case class GeneralNode(dealTime:Int,prevs:Array[Transfer],name:String) extends Node
case class PathNode(needTime:Int,node:Node)
object AssembleLineOpt {
  val cacheResult = collection.mutable.HashMap.empty[Node,List[PathNode]]
  def findOptPath(node:Node):List[PathNode]={
    if(cacheResult.contains(node))
      cacheResult(node)
    else {
      val p =node match {
        case `beginNode` => List(PathNode(0, beginNode))
        case GeneralNode(dealTime, prevs, _) => {
          prevs.map { case Transfer(delay, prev) => {
            val e = findOptPath(prev)
            PathNode(e.head.needTime + delay + dealTime, node) :: e
          }
          }.minBy { case x :: xs => x.needTime }
        }
      }
      cacheResult += (node -> p)
      p
    }
  }

  def main(args: Array[String]) {
    val a1 = GeneralNode(5,Array(Transfer(8,beginNode)),"A1")
    val a2 = GeneralNode(3,Array(Transfer(7,beginNode)),"A2")
    val b1 = GeneralNode(4,Array(Transfer(0,a1),Transfer(0,a2)),"B1")
    val b2 = GeneralNode(3,Array(Transfer(4,a1),Transfer(0,a2)),"B2")
    val end = GeneralNode(0,Array(Transfer(4,b1),Transfer(7,b2)),"END")
    val solution = findOptPath(end)
    val pathMsg = solution.map{case PathNode(_,node) => node.name}.reverse.mkString("->")
    println(pathMsg)
  }

}
