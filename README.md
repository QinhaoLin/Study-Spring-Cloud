# Study-Spring-Cloud
Spring Cloud 学习

# 一、学习路线
## 1.1 微服务架构
## 1.2 技术选型
1. 所需的基础：
- Spring Boot 2.1.3 
- IDEA 
- JDK8 
- Maven 
- Spring Boot基础
- Linux

2. 理解掌握并开发Spring Cloud里面主流框架和组件的基本使用，还要部分源码原理的理解

3. 掌握学习的技巧和决解问题的思路

# 二、架构演进和分布式系统基础知识
## 2.1 传统架构演进到分布式架构
简介：讲解单机应用和分布式应用架构演进基础知识
    
LVS+keepalive做Nginx高可用，负载分发
    
1. 单体应用
开发速度慢  
启动时间长  
依赖庞大  
等等  
    
2. 微服务
易开发、理解和维护  
独立的部署和启动  
等等  
    
3. 不足：
分布式系统->分布式事务问题  
需要管理多个服务->服务治理  

## 2.2 微服务核心基础
简介：  
讲解微服务核心知识：  
网关、服务注册发现、配置中心、链路追踪、负载均衡器、熔断

1. APIGatway网关：  
    路由转发+过滤器  
    /api/v1/pruduct/     商品服务  
    /api/v1/order/       订单服务  
    /api/v1/user/        用户服务  

路由转发：通过模块进行区分转发  
过滤器：用户登录、权限校验  

2. 服务注册发现：调用和被调用方的信息维护  
调用和被调用方都会先在注册中心里面声明  
注册中心：维护调用方和被调用方的信息

3. 配置中心：管理配置，动态更新  


4. 链路追踪：分析调用链路耗时  
例子：下单->查询商品服务获取商品价格->查询用户信息->保存数据库  
耗时分析，分析整个链路中哪个节点慢了

5. 负载均衡器：分发负载  


6. 熔断：保护自己和被调用方  
服务模块之间调用慢、阻塞或者无响应的时候，调用失败次数很高，在一定的时间内不会调用服务

## 2.3 常见的微服务框架
consumer：调用方  
provider：被调用方  
一个接口一般会充当两个角色（不是同时充当）

1、dubbo：zookeeper+dubbo+springmvc+springboot  
官方地址：http://dubbo.apache.org/#!/?lang=zh-cn
配套  
    通信方式：rpc  
    注册中心：zookeeper/redis  
    配置中心：diamond

2、springcloud：全家桶+轻松嵌入第三方组件（Netfix 奈飞）   
官网：http://projects.spring.io/spring-cloud/
配套  
    通信方式：http restful  
    注册中心：eruka/consul  
    配置中心：config  
    断路器：hystrix  
    网关：zuul  
    分布式追踪系统：sleuth+zipkin  
    
学习资料：https://blog.csdn.net/zhangweiwei2020/article/details/78646252


## 2.4 微服务下电商项目基础模块设计
简介：微服务下电商项目基础模块设计，分离几个模块，围绕这个基础项目进行练习  
1. 用户服务
    1. 用户信息接口
    2. 登录接口

2. 商品服务
    1. 商品列表
    2. 商品详情

3. 订单服务
    1. 我的订单
    2. 下单接口
