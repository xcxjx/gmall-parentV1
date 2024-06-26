server:
  port: 8206
mybatis-plus:
  mapper-locations: classpath:mapper/*Mapper.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: root
    url: jdbc:mysql://192.168.252.206:3306/gmall_product
  redis:
    host: 192.168.252.206
    password:
    database: 0
    port: 6379
  zipkin:
    base-url: http://192.168.252.206:9411
  rabbitmq:
    host: 192.168.252.206
    port: 5672
    username: guest
    password: guest