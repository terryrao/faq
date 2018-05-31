# zipkin sender

教程：
https://yq.aliyun.com/articles/78128?201758

一般的教程都有说明不再展开，记录一下踩过的坑。

如果服务里引用了 amq 包，原来可以发送日志到 zipkin server 的行为就会突然消失

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
    
这个时候需要手动指定 zipkin sender 在 application.properties 中添加如下配置

    spring.zipkin.sender.type=web

重启动后又会发送日志到 zipkin server 中。下面是官方文档：
https://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/2.0.0.M9/multi/multi__sending_spans_to_zipkin.html