#配置单机部署(BASE ON VERSION 2.8.0)
##1.免密设置

##2.配置JAVA_HOME(1.7以上版本)

##3.下载并解压hadoop

##4. install ssh rsync

##5.修改etc/hadoop/hadoop.env.sh （单机只需要配置这个即可）

    export JAVA_HOME=$JAVA_HOME
	

集群需要
    export HADOOP_PID_DIR=/var/lib/hadoop/pid
	export HADOOP_LOG_DIR=/var/lib/hadoop/logs
	
	
	
##5.设置core-site.xml

    <configuration>
        <property>
            <name>fs.defaultFS</name>
            <value>hdfs://localhost:9000</value>
        </property>
    </configuration>

##6.设置etc/hadoop/hdfs-site.xml: 伪分布式到以下可以使用

	<configuration>
		<property>
			<name>dfs.replication</name>
			<value>1</value>
		</property>
		<property>
			<name>dfs.replication</name>
			<value>1</value>
		</property>
	</configuration>
	
以下是启动测试：

	bin/hdfs namenode -format
	sbin/start-dfs.sh
	
查看NameNode - http://localhost:50070/

Make the HDFS directories required to execute MapReduce jobs：
     bin/hdfs dfs -mkdir /user
	 bin/hdfs dfs -mkdir /user/<username>
	 bin/hdfs dfs -put etc/hadoop input
	 bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.8.0.jar grep input output 'dfs[a-z.]+'
	 
查看结果：

    bin/hdfs dfs -get output output
	cat output/*
	
或者

    bin/hdfs dfs -cat output/*
	sbin/stop-dfs.sh
	
##7.在单点上使用yarn配置
###1)configuure etc/hadoop/mapred-site.xml

	<configuration>
	    <property>
		    <name>mapreduce.framework.name</name>
		    <value>yarn</value>
	    </property>
    </configuration>
	
###2)etc/hadoop/yarn-site.xml

	<configuration>
		<property>
			<name>yarn.nodemanager.aux-services</name>
			<value>mapreduce_shuffle</value>
		</property>
	</configuration>
	sbin/start-yarn.sh
	
查看结果：

    ResourceManager - http://localhost:8088/
	
	
#集群部署

##1.vim /etc/profile
    
    export HADOOP_HOME=/usr/local/hadoop
    export HADOOP_MAPRED_HOME=$HADOOP_HOME
    export HADOOP_COMMON_HOME=$HADOOP_HOME
    export HADOOP_HDFS_HOME=$HADOOP_HOME
    export YARN_HOME=$HADOOP_HOME
    export HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
    export YARN_CONF_DIR=$HADOOP_HOME/etc/Hadoop
    
让设置生效

    souce /etc/profile
	
##2.master节点设置slave

    vim ${HADOOP_HOME}/etc/hadoop/slaves
	master
	slave1
	slave2
	
##3.Master slaves 所有节点共同设置
 
###1)vim etc/hadoop/core-site.xml

    <configuration>
		<property>
			<name>fs.defaultFS</name>
			<value>hdfs://master:9000</value>
		</property>
		<property>
			<name>hadoop.tmp.dir</name>
			<value>/var/lib/hadoop-root/tmp</value> <!--需要手工建立 -->
		</property>
    </configuration>
	
###2)vim etc/hadoop/hdfs-site.xml

    <configuration>
		<property>
			<name>dfs.replication</name>
			<value>1</value>
		</property>
		
		 <property>
            <name>dfs.namenode.name.dir</name>
            <value>/var/lib/hadoop-root/namenode</value> <!--需要自己手动创建该目录-->
        </property>
		
		<property>
        <name>dfs.datanode.data.dir</name>
        <value>/var/lib/hadoop/tmp/datanode</value>
    </property>
	</configuration>

###3)vim etc/hadoop/mapred-site.xml

	<configuration>
		<property>
			<name>mapreduce.framework.name</name>
			<value>yarn</value>
		</property>
		<property>
            <name>mapreduce.jobhistory.address</name>
            <value>master:10020</value>
        </property>
	</configuration>
	
###4) vim etc/hadoop/yarn-site.xml
	
	<property>
        <name>yarn.resourcemanager.hostname</name>
        <value>master</value>
    </property>
	
	 <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
	
	<property>
        <name>yarn.nodemanager.aux-services.mapreduce_shuffle.class</name>
        <value>org.apache.hadoop.mapred.ShuffleHandler</value>
    </property>
	
###5).启动测试
    
	bin/hdfs namenode -format
	sbin/start-dfs.sh
	sbin/start-yarn.sh
	sbin/mr-jobhistory-daemon.sh 

创建hdfs

    hdfs dfs -mkdir /user
	hdfs dfs -mkdir /user/root
	
	hdfs dfs -put etc/hadoop /user/root/input
	hdfs dfs -ls /user/root/input
	hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.8.0.jar grep /user/test22/input output 'dfs[a-z.]+'
	



	




