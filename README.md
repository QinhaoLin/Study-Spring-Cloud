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

# 四、服务消费者Ribbon和feign实战和注册中心高可用

## 4.1 常用的服务间调用方式讲解  
简介：讲解常用的服务间的调用方式  

- RPC:  
远程过程调用，像调用本地服务(方法)一样调用服务器的服务  
支持同步、异步调用  
客户端和服务器之间建立TCP连接，可以一次建立一个，也可以多个调用复用一次链接  
PRC数据包小  
protobuf  
thrift  
RPC主要问题：编解码，序列化，链接，丢包，协议  


- Rest(Http):  
http请求，支持多种协议和功能  
开发方便成本低  
http数据包大  
java开发：HttpClient，URLConnection  

## 4.2 微服务调用方式之Ribbon实战 订单服务调用商品服务
简介：实战电商项目，订单服务调用商品服务获取商品信息  

1. 创建order_service项目
2. 开发伪下单接口
3. 使用Ribbon （类似httpClient，URLconnection）  
启动类增加注解
```
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```
4. 根据名称进行调用商品，获取商品详情

## 4.3 高级篇幅之Ribbon负载均衡源码分析实战
简介：讲解Ribbon服务间调用负载均衡源码分析  
1. 完善下单接口
2. 分析 ==@LoadBalanced==  
    1. 首先从Euraka注册中心获取服务列表，获取provider的服务列表  
    2. IRule通过一定的策略选择其中一个节点  
    3. 再返回给restTemplate调用

## 4.4 高级篇副之服务调用之负载均衡策略调整实战
简介：实战调整默认负载均衡策略实战

自定义负载均衡策略：  

在配置文件里面，自定义负载均衡策略  
```
# 自定义负载均衡策略
product-service:
  ribbon:
	NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```
策略选择：  
1. 如果每个机器配置一样，则建议不修改Ribbon策略（推荐）
2. 如果部分机器配置好，则可以改为 WeightedResponseTimeRule 按权重分配  
 

## 4.5 微服务调用方式Feign 实战 订单调用商品服务
简介：改造电商项目 订单服务 调用商品服务获取商品信息  
Feign：伪RPC客户端（本质还是使用http）  
官方文档：https://cloud.spring.io/spring-cloud-openfeign/  

1. 使用feign步骤（新旧版本依赖名称不一样，旧版本没有open）
加入依赖
```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
启动类增加 ==@EnableFeignClients==  
增加一个接口 并 ==@FeignClient(name="product-service")==

2. 编码实战
3. 注意点：
    1. 路径
    2. Http方法必须对应
    3. 参数使用了@requestBody，调用方应该使用@PostMapping
    4. 多个参数的时候，通过(@RequestParam("id") int id)方式调用

## 4.6 Feign核心源码解读和服务调用方式Ribbon和Feign选择
简介：讲解Feign核心源码解读和服务间的调用方式Ribbon、Feign选择
1. Ribbon和Feign两个的区别和选择
选择Feign  
默认集成了Ribbon  
写起来思路更加清晰和方便  
采用注解方式进行配置，配置熔断等方式方便  

2. 服务间调用超时配置  
默认optons readtimeout是60，但是由于hystrix默认是1秒超时
```
# 修改服务调用超时时间
feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: basic
```
模拟接口响应慢，线程睡眠新的方式
```
TimeUnit.SECONDS.sleep(1); // 线程睡眠1秒
```

# 五、互联网架构服务降级熔断Hystrix实践
## 5.1 分布式核心知识之熔断、降级讲解
简介：系统负载过高，突发流量或者网络等各种异常情况介绍，常用的解决方案  

1. 熔断：  

类似保险丝，熔断服务，为了防止整个系统故障，包含自己和下游服务  

下单服务->商品服务、用户服务（出现异常->触发熔断）

2. 降级：  

抛弃一些非核心的接口和数据  

旅行箱的例子：只带核心物品，抛弃非核心的，等有条件的时候再去携带这些物品

3. 熔断和降级互相交集：  

相同点：  
1. 从可用性和可靠性触发，为了防止系统崩溃
2. 最终让用户体验到的是某些功能暂时不能用


不同点：  
1. 服务熔断一般是由下游服务故障导致的，而服务降级一般是从整体系统负荷考虑，由调用方控制

## 5.2 Netflix开源组件断路器Hystrix介绍
简介：介绍Hystrix基础知识和使用场景  

文档地址：  
https://github.com/Netflix/Hystrix  
https://github.com/Netflix/Hystrix/wiki  

1. 什么是Hystrix？
    1. Hystrix对应的中文名字是“豪猪”
    2. Hystrix

2. 为什么要用？  
在一个分布式系统里，一个服务依赖多个服务，可能存在某个服务调用失败，比如超时、异常等，如何能够保证在一个依赖出现问题的情况下，不会导致整体服务失败，通过Hystrix就可以解决  
https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.0.3.RELEASE/single/spring-cloud-netflix.html#_circuit_breaker_hystrix_clients

3. 提供了熔断、隔离、Fallback、cache、监控等功能

4. 熔断后怎么处理？
出现错误之后可以 Fallback 错误的处理信息  

兜底数据

## 5.3 Feign结合Hystrix断路器开发实战《上》
简介：讲解Spring Cloud整合断路器的使用，用户服务异常情况

1. 加入依赖  
注意：网上新旧版本问题，所以要以官网为主，不然部分注解会丢失  
```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```
2. 增加注解
启动类里面增加注解 ==@EnableCircuitBreaker==  

注解越来越多->==@SpringCloudApplication==注解

3. API接口编码实战
熔断->降级  
    1. 最外层api使用，好比异常处理（网络异常，参数或者内部调用问题）  
    api方法上增加 ==@HystrixCommand(fallbackMethod = "saveOrderFail")==
    编写fallback方法实现，方法签名一定要和api方法签名一致（注意点！！！）


补充： 修改maven仓库地址  
pom.xml中修改
```

```

## 5.4 Feign结合Hystrix断路器开发实战《下》
简介：讲解Spring Cloud整合断路器的使用，用户异常情况
1. Feign结合Hystrix
    1. 开启Feign支持Hystrix（注意：一定要开启，旧版本默认支持，新版本默认关闭）
    2. ==@FeignClient(name="xxx", fallback=xxx.class)==，class需要继承当前FeignClient的类
```
feign:
  hystrix:
    enabled: true
```             

## 5.5 熔断降级服务异常报警通知实战
简介：完善服务熔断处理，报警机制处理  

1. 加入redis依赖
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

2. 配置redis链接信息
```
redis:
  database: 3
  host: 127.0.0.1
  password: xxxxxx
  port: 1
  timeout: 3000
```
3. 使用
```
// 注意方法签名一定要和api方法一致
private  Object saveOrderFail(int userId, int productId, HttpServletRequest request){
    // 监控报警
    String saveOrderKey = "save-order";
    final String ip = request.getRemoteAddr();
    String sendValue = redisTemplate.opsForValue().get(saveOrderKey);
    // 新开一个线程异步处理
    new Thread(()->{
        if (StringUtils.isBlank(sendValue)){
            System.out.println("紧急短信，用户下单失败，请立刻查找原因，ip地址是="+ip);
            // 发送一个 http 请求，调用短信服务 TODO

            redisTemplate.opsForValue().set(saveOrderKey,"save-order-fail", 20, TimeUnit.SECONDS);
        }else {
            System.out.println("已经发送过短信，20秒内不重复发送");
        }
    }).start();

    Map<String, Object> msg = new HashMap<>();
    msg.put("code", -1);
    msg.put("msg", "抢购人数太多，您被挤出来了，稍等重试");
    return msg;
}
```

## 5.6 高级篇幅之深入源码剖析Hystrix降级策略
简介：源码分析Hystrix降级策略和调整  

1. 查看默认讲解策略 HystrixCommandProperties
    1. execution.isolation.strategy 隔离策略  
        THREAD 线程池隔离（默认）  
        SEMAPHORE 信号量  
            信号量适用于接口并发量高的情况，如每秒数千次调用的情况，导致线程开销过高，通常只适用于非网络调用，执行速度快
    2. execution.isolation.thread.timeoutInMilliseconds 超时时间  
        默认 1000毫秒
    3. execution.timeout.enabled 是否开启超时限制 （一定不要禁用，建议修改超时时间）
    4. execution.isolation.semaphore.maxConcurrentRequests 隔离策略为信号量的时候，如果达到最大并发数时，后续请求会被拒绝，默认是10

官方文档：  
https://github.com/Netflix/Hystrix/wiki/Configuration  

2. 调整策略
    超时时间调整
```
hystrix:
  command:
    default:
      excution:
        isolation:
          thread:
            timeoutInMilliseconds: 4000
```

## 5.7 断路器Dashboard监控仪表盘实战
简介：讲解断路器Dashboard基础使用和查看  

1. 加入依赖
```
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

2. 启动类增加注解  
==@EnableHystrixDashboard==
3. 配置文件增加endpoint（新版本默认是不开放所有的）
```
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

4. 访问入口  
http://localhost:8781/hystrix  
Hystrix Dashboard输入：http://localhost:8781/actuator/hystrix.stream  

参考资料  
默认开启监控配置  
https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/reference/htmlsingle/#boot-features-security-actuator  

配置文件类：  
spring-configuration-metadata.json

## 5.8 断路器监控仪表参数讲解和模拟
简介：讲解断路器监控仪表盘参数和模拟熔断  

1. 仪表盘采集技术：sse server-send-event推送到前端

