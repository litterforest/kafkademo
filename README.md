# kafkademo
kafka练习使用与sprint boot集成配置和代码实现

<!-- spring cloud stream kafka支持 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-kafka</artifactId>
</dependency>
<!-- spring cloud stream kafka支持 -->

#生产者
cloud:
    stream:
      bindings:
        output:
          destination: xhg_heartbeat_err_topic
          producer:
            # 分区索引表达式
            partitionKeyExpression: payload.hashCode() % 3
            partitionCount: 4
      kafka:
        binder:
          brokers: 10.101.10.144:9092
          
#消息者
spring:
  cloud:
    stream:
      bindings:
        input:
          destination: xhg_heartbeat_err_topic
          group: myGroup
          consumer:
            partitioned: true
            # 开启两个实例
            instanceCount: 2
            # 实例编号从0开始
            instanceIndex: 0
            # 每个实例起动多少个consumer
            concurrency: 4
      kafka:
        binder:
          brokers: 10.101.10.144:9092
