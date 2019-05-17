server:
  port: 8001
  
eureka:
  client:         #客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://eureka-7001.com:7001/eureka/
      
mybatis:
  config-location: classpath:mybatis/mybatis.cfg.xml    # mybatis配置文件所在路径
  type-aliases-package: com.jmx.vo                      # 定义所有操作类的别名所在包
  mapper-locations:                                     # 所有的mapper映射文件
  - classpath:mybatis/mapper/**/*.xml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource    # 配置当前要使用的数据源的操作类型
    driver-class-name: org.gjt.mm.mysql.Driver      # 配置MySQL的驱动程序类
    url: jdbc:mysql://localhost:3307/jmx8001?useUnicode=true&characterEncoding=utf-8       # 数据库连接地址
    username: root                                  # 数据库用户名
    password: 123456                                # 数据库连接密码
    dbcp2:                                          # 进行数据库连接池的配置
      min-idle: 5                                   # 数据库连接池的最小维持连接数    
      initial-size: 5                               # 初始化提供的连接数
      max-total: 5                                  # 最大的连接数
      max-wait-millis: 200                          # 等待连接获取的最大超时时间
  application:                                      
    name: provider-8001                             # Eureka注册中心服务名
    
    
    
--------------------- 
作者：hsrlzg 
来源：CSDN 
原文：https://blog.csdn.net/hsrlzg/article/details/79388121 
版权声明：本文为博主原创文章，转载请附上博文链接！