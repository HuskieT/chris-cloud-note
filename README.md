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