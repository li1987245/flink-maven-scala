package com.opensourceteams.module.bigdata.flink.example.dataset.transformation.rightOuterJoin

import org.apache.flink.api.scala.{ExecutionEnvironment, _}


object Run {

  def main(args: Array[String]): Unit = {


    val env = ExecutionEnvironment.getExecutionEnvironment

    val dataSet = env.fromElements(("a",3),("b",1),("c",5),("a",1),("c",1),("d",1),("f",2),("g",5))
    val dataSet2 = env.fromElements(("g",1),("f",1))


    //全外连接
    val dataSet3 = dataSet.rightOuterJoin(dataSet2).where(0).equalTo(0){
      (x,y) => {
        var count = 0;
        if(x != null ){
          count = x._2
        }
        (x._1,y._2 + count)
      }
    }




    dataSet3.print()

  }

}
