# SpringCloudDemo
一个SpringCloudDemo，版本是 Dalston.SR1

# hosts 文件配置
eureka service 测试用: 
127.0.0.1       myeurekaservice7011
127.0.0.1       myeurekaservice7012
127.0.0.1       myeurekaservice7013


# 项目结构
EurekaService 是单机版的Eureka 服务端实例
EurekaService_Cowd7011~ 7012是集群版的Eureka 服务端实例
ServiceConsumer 是一个微服务消费者，使用了ribbon 客户端负载均衡技术
ServiceProvider_8081~8083 是微服务提供者集群

# database.sql
项目的数据库初始化脚本
 
