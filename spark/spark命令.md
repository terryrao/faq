#��������������spark 2.1.0�汾
#local����ģʽ   ����[*] ���������̵߳Ŀ�����
�޸� conf/spark-env.sh
 ./bin/spark-submit --class org.apache.spark.examples.JavaWordCount --master local[*] ./examples/jars/spark-examples_2.11-2.1.0.jar README.md

#standalone ģʽ
��Ҫ������
1. slaves �ļ�
copy conf/slaves.template to slaves ��localhost�޸�Ϊslave������
copy conf/spark-env.sh.template to spark-env.sh 
�����������
export JAVA_HOME=/usr/local/java #java·��
export SPARK_MASTER=spark_master #���ڵ�
export SPARK_MASTER_PORT=7077 #�˿ں�
export SPARK_WORK_CORS=1 #�����̺߳���
export SPARK_WORK_INSTANCES=1 #�����߳�ʵ����
export SPARK_WORK_MEMORY=256m #�����߳�����ʱռ���ڴ�

