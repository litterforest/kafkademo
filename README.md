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
      kafka:
        binder:
          brokers: 10.101.10.144:9092
