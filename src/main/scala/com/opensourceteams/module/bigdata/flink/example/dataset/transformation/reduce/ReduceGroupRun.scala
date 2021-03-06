package com.opensourceteams.module.bigdata.flink.example.dataset.transformation.reduce

import org.apache.flink.api.scala.{ExecutionEnvironment, _}


/**
  * 相当于按key进行分组,然后对组内的元素进行的累加操作，求和操作
  */
object ReduceGroupRun {

  def main(args: Array[String]): Unit = {


    val env = ExecutionEnvironment.getExecutionEnvironment

    val dataSet = env.fromElements("a","b","c","a","c","d","f","g","f")

    /**
      * (a,1)
      * (b,1)
      * (c,1)
      * (a,1)
      * (c,1)
      * (d,1)
      * (f,1)
      * (g,1)
      */

    val dataSet2 = dataSet.map((_,1)).groupBy(0).reduce((x,y) => {
      println(s"x=${x}  y=${y} ")
      (x._1,x._2 + y._2)
    }
    )



    dataSet2.print()

  }

}
