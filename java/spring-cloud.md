#微服务九大特性
##1.服务组件化
##2.按业务组织团队
##3.对产品的整个生命周期负责
##4.智能端点与哑管道
两种调用方式

    1）使用 http 的 RESTful 或者更轻量级的消息发送协议
    2）通过轻量级消息总线上传递消息，类似于 rabbitMQ 等一些提供可靠异步交换的中间件。

##5.去中心化治理
通过采用轻量级的契约定义接口，使得服务能采用不能的技术实现

##6.去中心化管理数据
强调最终一致性

##7.基础设施自动化
    
    自动化测试
    自动化部署
##8.容错设计
快速检出故障并尽可能自动恢复服务，通常我们希望每个服务都有自己的监控和日志记录
##9.演进式设计

#spring cloud 组件
##1.spring cloud config 
配置管理工具
##2.spring cloud netflix
    
    Eureka   服务治理组件 包含注册中心 服务注册与服务发现
    Hystrix  容错管理组件 实现断路器模式，帮助服务依赖中出现的延迟和为故障提供强大的容错能力
    Ribbon   客户端负载均衡的服务调用组件
    Feign    基于 Ribbon 和 Hystrix 的声明式服务调用组件
    Zuul     网关组件，提供智能路由、访问过滤等功能
    Archaius 外部化组件配置
    
##3. Spring cloud Bus
  
事件消息组件，用于传播集群中的状态变化或者事件，以触发后续的处理

##4.Spring Cloud Cluster

针对Zookeeper、Redis、Consul、HazelCast的选举算法和通用状态模式的实现

##5.Spring Cloud CloudFoundry 

与Pivotal CloudFoundry 的整合支持

##6.Spring cloud Consul

服务发现与管理工具

##7.Spring Cloud Stream 

通过 Redis、Rabbit、Kafka实现的消费微服务，可以通过简单的声明式模型来发送和接收消息

##8.Spring Cloud Security

安全工具包 提供在 Zuul 代理中对 OAuth2客户端请求的中继器

##9.Spring Cloud Sleuth 

Spring Cloud 的分布式跟踪实现，可以完美整合 Zipkin

##10.Spring Cloud Zookeeper  

基于Zookeeper的服务发现与配置管理组件

##11.Spring Cloud Starters

Spring Cloud 的基础组件，它是基于Spring boot 风格项目的基础依赖模块


#Spring Boot 基础知识

可以在 http://start.ppring.io 中获取 demo的配置项目模板

application.properties 中可以配置随机数
${random}
    # 随机字符串
com.test.value=${random.value}
com.test.value=${random.int}  # 随机int 
com.test.value=${random.long } # 随机int
com.test.value=${random.int(10)}# 随机int10以内整数
com.test.value=${random.int[10,20]} #10-20随机数





