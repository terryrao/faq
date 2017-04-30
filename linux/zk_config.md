 cd ${ZOOKEEPER}/conf 
 cp zoo_sample.cfg zoo.cfg
 vim zoo.cfg
	#The number of milliseconds of each tick
	tickTime=2000
	# The number of ticks that the initial 
	# synchronization phase can take
	initLimit=10
	# The number of ticks that can pass between 
	# sending a request and getting an acknowledgement
	syncLimit=5
	# the directory where the snapshot is stored.
	# do not use /tmp for storage, /tmp here is just 
	# example sakes.
	dataDir=/var/lib/zookeeper
	# the port at which the clients will connect
	clientPort=2181
	# the maximum number of client connections.
	# increase this if you need to handle more clients
	#maxClientCnxns=60
	#
	# Be sure to read the maintenance section of the 
	# administrator guide before turning on autopurge.
	#
	# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
	#
	# The number of snapshots to retain in dataDir
	#autopurge.snapRetainCount=3
	# Purge task interval in hours
	# Set to "0" to disable auto purge feature
	#autopurge.purgeInterval=1
	# server
	server.1=192.168.0.111:2888:3888
	server.2=192.168.0.112:2888:3888
	server.3=192.168.0.113:2888:3888
	
 将此配置文件复制到各个节点

 #vim /etc/profile 此步可以省略
 # export ZOOKEEPER=/usr/local/zookeeper 此步可以省略

#启动zookeeper 
    ${ZOOKEEPER}/bin/zkServer.sh start
    ${ZOOKEEPER}/bin/zkServer.sh status

如果没有启动起来使用以下命令查看
    cat zookeeper.out

如果报以下异常 zookeeper myid file is missing 可以手工创建一个Myid

     echo 1 > ${dataDir}/myid

再依次启动各个节点




