/**
  * Created by Administrator on 2017/5/24.
  */
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.GeneralizedLinearModel
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.regression._
import org.apache.spark.mllib.util.{DataValidators, Loader, Saveable}
object PerceptronModel{
  implicit class VectorMaker(thisVector: Vector) {

    def +(thatVector: Vector) ={
      val size = thisVector.size
      require(size == thatVector.size)
      Vectors.dense((for(i <- 0 until size) yield thisVector(i) + thatVector(i)).toArray)
    }
    def unary_- ={
      val size = thisVector.size
      Vectors.dense((for(i <- 0 until size) yield -thisVector(i)).toArray)
    }
    def -(thatVector: Vector) ={
      this.+(-thatVector)
    }
    def innerMult(thatVector: Vector) ={
      val size = thisVector.size
      require(size == thatVector.size)
      (for(i <- 0 until size) yield thisVector(i) * thatVector(i)).foldLeft(0.0)(_ + _)
    }

  }
  def sign(v:Double)={
    if(v>=0) 1.0 else 0.0
  }
  def train(input: RDD[LabeledPoint], numIterations: Int): PerceptronModel={
    val size = input.first.features.size
    var w = Vectors.zeros(size)
    var b =0.0
    var flag = true
    while(flag) {
      flag = false
      input.collect().foreach {
        case LabeledPoint(label, features) => if (sign(w.innerMult(features) + b) != label) {
          if (label > 0) {
            w = w + features
            b = b + 1.0
          } else {
            w = w - features
            b = b - 1.0
          }
          flag = true
        }
      }
    }

    new PerceptronModel(w,b)
  }

  def main(args: Array[String]) {
    println("hi")
    println(Vectors.dense(1.0,2.0) + (-Vectors.dense(1.0,2.0)))
    println(Vectors.dense(1.0,2.0).innerMult(Vectors.dense(1.0,2.0)))
  }
}
class PerceptronModel(override val weights:Vector,override val intercept:Double)extends GeneralizedLinearModel(weights, intercept)  with Serializable {
  //import PerceptronModel._
  override protected def predictPoint(
                                       dataMatrix: Vector,
                                       weightMatrix: Vector,
                                       intercept: Double) = {
    val size = weightMatrix.size
    val margin = (for(i <- 0 until size) yield weightMatrix(i) * dataMatrix(i)).foldLeft(0.0)(_ + _)+intercept
    if(margin >=0.0) 1.0 else 0.0
  }
}
