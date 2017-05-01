#配置例子来自于spark 2.1.0版本
##local单机模式   其中[*] 可以设置线程的开启数
    修改 
    conf/spark-env.sh
    ./bin/spark-submit --class org.apache.spark.examples.JavaWordCount --master local[*] ./examples/jars/spark-examples_2.11-2.1.0.jar README.md

##standalone 模式
需要的配置
###slaves 文件
    copy conf/slaves.template to slaves 将localhost修改为slave的名称
    copy conf/spark-env.sh.template to spark-env.sh 
###添加以下配置
    export JAVA_HOME=/usr/local/java #java路径
    export SPARK_MASTER=smaster #主节点
    export SPARK_MASTER_PORT=7077 #端口号
    export SPARK_WORK_CORS=1 #工作线程核数
    export SPARK_WORK_INSTANCES=1 #工作线程实例数
    export SPARK_WORK_MEMORY=256m #工作线程运行时占用内存
   
###提交任务
    ./bin/spark-submit --class org.apache.spark.examples.JavaSparkPi --master spark://master:7077 --executor-memory 1g --total-executor-cores 1 ./examples/jars/spark-examples_2.11-2.1.0.jar 100
	
###standalone HA zookeeper 部署
1.需要配置相应的zookeeper 并启动
2.conf/spark-env.sh 中配置
    export SPARK_DAEMON_JAVA_OPTS="-Dspark.deploy.recoveryMode=ZOOKEEPER -Dspark.deploy.zookeeper.url=192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181 -Dspark.deploy.zookeeper.dir=/spark"
3.在主节点中启动所有主从节点
	${SPARK_HOME}/sbin/start-all.sh
4.在其它从节点中分别启动
    ${SPARK_HOME}/sbin/start-master.sh
	

    

