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
    
# 三、springcloud核心组件注册中心
## 3.1 什么是微服务的注册中心
简介：什么是注册中心，常用的注册中心有哪些  
理解注册中心：服务管理，核心是有个服务注册表，心跳机制动态维护  
服务提供者provider：启动的时候向注册中心上报自己的网络信息  
服务消费者consumer：启动的时候向注册中心上报自己的网络信息，拉取provider的相关网络信息  

为什么要用：  
微服务应用和机器越来越多，调用方需要知道接口的网络地址，如果靠配置文件的方式去控制网络地址，对于动态新增机器，维护带来很大问题  

主流注册中心：  
zookeeper、Eureka、consul、etcd等



## 3.2 分布式应用知识CAP理论知识
简介：讲解分布式核心知识CAP理论  

CAP定理：  
指的是在一个分布式系统中，Consistency（一致性）、Availability（可用性）、Partitiontolerance（分区容错性），三者不可同时获得。  

一致性（C）：在分布式系统中的所有数据备份，在同一时刻是否同样的值。（所有节点在同一时间的数据完全一致，越多节点，数据同步越耗时）  

可用性（A）：负载过大后，集群整体是否还能响应客户端的读写请求。（服务一直可用，而且是正常响应时间）  

分区容错性（P）：分区容忍性，就是高可用性，一个节点崩了，并不影响其它的节点（100个节点，挂了几个，不影响服务，越多机器越好）  

CAP理论就是说在分布式存储系统中，最多只能实现上面的两点。而由于当前的网络硬件肯定会出现延迟丢包等问题，所以分区容忍性是我们必须需要实现的。所以我们只能在一致性和可用性之间进行权衡


## 3.3 分布式系统CAP原理常见面试题和注册中心选择
简介：讲解CAP原则在面试中回答和注册中心选择  
C A 满足的情况下，P不能满足的原因：  
数据同步（C一致性）需要时间，也要正常的时间内响应（A可用性），那么机器数量就要少，所以P分区容错性就不满足  

C P 满足的情况下，A不能满足的原因：  
数据同步（C一致性）需要时间，机器数量也多（P分区容错性），但是同步数据需要时间，所以不能再正常时间内响应，所以A可用性就不满足  

A P 满足的情况下，C不能满足的原因：  
机器数量越多（P分区容错性），正常的时间内响应（A可用性），那么数据就不能及时同步到其它节点，所以C一致性不满足  

注册中心选择：  
Zookeeper：CP设计，保证了一致性，集群搭建的时候，某个节点失效，则会进行选举行的leader，或者半数以上节点不可用，则无法提供服务，因此可用性没法满足  

Eureka：AP原则，无主从节点，一个节点挂了，自动切换其它节点可以使用，去中心化  

结论：  
分布式系统中P，肯定要满足，所以只能在CA中二选一  
没有最好的选择，最好的选择是根据业务场景来进行架构设计  

如果要求一致性，则选择Zookeeper，如金融行业  
如果要求可用性，则Eureka，如电商系统


## 3.4 SpringCloud微服务核心组件Eureka介绍和闭源后影响
简介：  
SpringCloud体系介绍  
官方地址：http://projects.spring.io/spring-cloud/

## 3.5 服务注册和发现Eureka Server搭建实践
简介：使用IDEA搭建Eureka服务中心Server端并启动，项目基本骨架介绍  

官方文档：http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#spring-cloud-eureka-server  

第一步：创建项目  
第二步: 添加注解 ==@EnableEurekaServer==  
第三步：增加配置application.yml  
```
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
  #声明自己是个服务端
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
第四步：访问注册中心页面  
http://localhost:8761/



## 3.6 服务注册和发现之Eureka Client搭建商品服务实践
简介：搭建用商品服务，并将服务注册到注册中心  

1. 创建一个SpirngBoot应用，增加服务注册和发现依赖  
2. 模拟商品信息，存储在内存中  
3. 开发商品列表接口，商品详情接口  
4. 配置文件加入注册中心地址  
使用eureka客户端，官方文档：http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#netflix-eureka-client-starter  


## 3.7 Eurka服务注册中心配置控制台问题处理
简介：讲解服务注册中心管理后台，（后续还会细讲）  

问题：eureka管理后台出现一串红色字体：是警告，说明有服务上线率低  
> EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
	
关闭检查方法：eureka服务端配置文件加入  
```
server:
    	enable-self-preservation: false
```
注意：自我保护模式禁止关闭，默认是开启状态true  

问题二：为什么只加一个注册中心地址，就可以注册，而在启动类中不用加@EnableEurekaClient  
```
By having spring-cloud-starter-netflix-eureka-client on the classpath, your application automatically registers with the Eureka Server. Configuration is required to locate the Eureka server, as shown in the following example:
```    
