一： 不适合微服务的业务形态
系统中包含很多的强事务场景
访问压力不大 可用性要求不高

康威定律（团队内的沟通成本）
1eureka总结
@EnableEurekaServer  服务端
@EnableDiscoveryClient  客户端

心跳检测 健康检查 负载均衡

eureka 生产环境中最少2台
三台eureka 服务器 两两注册 （1注册23 ，2注册13, 3注册12）
两台eureka 服务器 互相注册 （1注册2 2注册1）  eureka.client.serviceUrl.defaultZone= 对方的ip和端口
一台eureka 服务器 eureka.client.serviceUrl.defaultZone = 自己的ip和端口
eureka集群：（服务端 下面两个都配置为false）
register-with-eureka: false
fetch-registry: false

二：服务拆分

x 水平拆分
y 功能拆分 （单一职责  松耦合 高内聚）（关注点分离：按职责、按通用性、按粒度级别）
z 数据分区

三 应用通信
1 springcloud中服务间两种restful调用方式
RestTemplate
Feign
2 RestTemplate 的三种使用方式

3 ribbon 客户端负载均衡器
RestTemplate  Feign zuul中均有用到
4 Ribbon
serverList
IRule
ServerListFilter

5 feign
声明式Rest客户端（伪RPC）
采用基于接口的注解

@RequestParam @PathVariable 可以使用@GetMapping 注解
@RequestBody只能使用@PostMapping 注解 
-------------------------------
@PathVariable("xxx")
通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“) 
@RequestMapping(value=”user/{id}/{name}”)

//使用@RequestParam时，URL是这样的：http://host:port/path?参数名=参数值
//使用@PathVariable时，URL是这样的：http://host:port/path/参数值

四 统一配置中心

远端git -> config-sever <->本地git    =》微服务客户端（添加 config-client依赖）

config-sever 从远端git 拉取配置 发送给微服务客户端
或者 从本地git 拉取配置 发送给微服务客户端

1  远端git仓库配置 yml
/{name}-{profiles}.yml
/{label}/{name}-{profiles}.yml

name 服务名称
profiles 环境
label 分支{branch}

config-sever 会合并git仓库的yml文件 

@RefreshScope   //spring cloud提供的一种特殊的scope实现，用来实现配置、实例热加载。

//TODO  
本地暂时没有安装Rabbitmq ，config-server 可以使用 bus-amqp 依赖Rabbitmq 完成 git配置的热更新（需要用到@RefreshScope 注解）
git 远端服务器使用webHooks  可以实现提交配置到git （webHooks 配置有 bus-refresh 的地址 发送请求 ） 跟新 config-server 和 config-client的 config配置  

**不停止服务器  一键化更新这个项目的所有配置**


五 消息与异步

MQ的应用场景： 
异步处理
流量消峰
日志处理
应用解耦

六：服务网关

1 常用网关方案
Nginx +lua
Tyk （Go语言开发 开源网关）
spring cloud zuul 

2 路由+过滤器 = Zuul

3 Zuul中的4种过滤器 api

前置(Pre)
后置(Post)
路由(Route)
错误(Error)

注意：（映射server-id 全为小写  切记！ 切记！切记！）

    2019-05-18 20:07:29.170  INFO 16032 --- [on(4)-127.0.0.1] c.c.c.ConfigServicePropertySourceLocator : Located environment: name=api-zuul, profiles=[default], label=null, version=6ce5c07e5fd990d6cfbad1eceb98fda7a5e554b5, state=null
    2019-05-18 20:07:35.947  INFO 16032 --- [nio-5555-exec-1] o.s.c.n.zuul.web.ZuulHandlerMapping      : Mapped URL path [/organization/**] onto handler of type [class org.springframework.cloud.netflix.zuul.web.ZuulController]
    2019-05-18 20:07:35.947  INFO 16032 --- [nio-5555-exec-1] o.s.c.n.zuul.web.ZuulHandlerMapping      : Mapped URL path [/config/**] onto handler of type [class org.springframework.cloud.netflix.zuul.web.ZuulController]
    2019-05-18 20:07:35.947  INFO 16032 --- [nio-5555-exec-1] o.s.c.n.zuul.web.ZuulHandlerMapping      : Mapped URL path [/eruekaclient/**] onto handler of type [class org.springframework.cloud.netflix.zuul.web.ZuulController]
    2019-05-18 20:07:35.947  INFO 16032 --- [nio-5555-exec-1] o.s.c.n.zuul.web.ZuulHandlerMapping      : Mapped URL path [/eruekaclienttwo/**] onto handler of type [class org.springframework.cloud.netflix.zuul.web.ZuulController]

4 zuul 配置路由的三种方式

通过服务发现自动映射路由
使用服务发现手动映射路由
使用静态URLs手动映射路由

zuul:
  #  ignored-services 忽略某些服务 ( ignored-services: '*' 忽略Zuul默认给的服务，只留下手动配置的（即rutes配置下的路由）)
  ignored-services: '*'
  # 不同服务的路由的开头都加上一个/api的前缀 （可以用这种方式实现 访问路径最后为 http://localhost:5555/api/organizationservice/vi/getMsg/1）
  prefix: /api
  # routes 下可以自定义映射路由（key: value形式）
  routes:
    organizationservice: /organization/**  
    licenseservice: /license/**
    organizationstatic: 
          path: /organizationstatic/**
          url: http://localhost:11000
  
写法二：

zuul:
  #  ignored-services 忽略某些服务 ( ignored-services: '*' 忽略Zuul默认给的服务，只留下手动配置的（即rutes配置下的路由）)
  ignored-services: '*'
  # 不同服务的路由的开头都加上一个/api的前缀 （可以用这种方式实现 访问路径最后为 http://localhost:5555/api/organizationservice/vi/getMsg/1）
  prefix: /api
  # routes 下可以自定义映射路由（key: value形式）
  routes:
    organizationstatic: 
      path: /organizationstatic/**
      url: http://localhost:11000
      serviceId: organizations
      sensitiveHeaders: 

【此时：
organizationstatic是zuul 内部用来识别服务器的关键字
path: /organizationstatic/**    organizationstatic服务的静态路由
url: http://localhost:11000      已经启动organizationstatic服务实例的静态URL，zuul可直接访问，而不是通过eureka进行访问
serviceId     服务id
sensitiveHeaders    （默认值 Cookie,Set-Cookie,Authorization）敏感headers也支持全局设置 zuul.sensitiveHeaders. 如果在单个路由中设置 sensitiveHeaders 会覆盖全局 sensitiveHeaders 设置.
】



