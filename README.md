### 说明：
* 基于springboot搭建的分布式服务基本框架

* 项目介绍
  
    ​	
    
    |     工程名      |                 描述                 |
    | :-------------: | :----------------------------------: |
    | e-common-parent |      父工程，主要定义了jar版本       |
    |    e-common     |       项目公用的jar包依赖工程        |
    |  e-common-core  | 存放项目公用的工具以及错误码，常量等 |
    | e-common-config |        存放项目公用的配置文件        |
    |    e-gateway    |               项目网关               |
    |   e-user-api    |           用户相关接口定义           |
    | e-user-service  |       用户相关接口实现（服务）       |
    |   e-order-api   |           订单相关接口定义           |
    | e-order-service |       订单相关接口实现（服务）       |
    |   e-cache-api   |        redis缓存相关接口定义         |
    | e-cache-service |    redis缓存相关接口实现（服务）     |
    
    
    
* 技术：
    ```
        dubbo:分布式服务
        zookeeper：为分布式应用提供一致性服务的中间件
        lombok:自动生成setter/getter，简化JavaBean
        pagehelper：mybatis分页插件
        redis：缓存中间件
    ```

* 使用：

  * 将 **e-common-parent** , **e-common** ,**e-common-core**  , **e-common-config**  maven install 到本地 

  * 分别启动zookeeper服务和Redis服务
  * 修改 **e-common-config** 中的配置信息 **application-redis_config.properties** **application-service_config.properties**
  * 分别启动 **e-user-service** ,**e-order-service** ,**e-cache-service**,**e-gateway**

