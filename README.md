# Distributed-topics
分布式专题

什么是分布式
	将多台计算机通过网络连接起来协同计算，形成超级大型服务器（由多个计算机共同完成一件事）


	1. 任务分解？
	
	2. 节点通信？

分布式和集群的关系？
电商平台：  用户、 商品、订单、 交易
分布式： 一个业务拆分成多个子系统，部署在不同的服务器上
集群：  同一个业务，部署在多个服务器上
	
	
计算机的发展史

1.1946年  情人节

2.1964  IBM  SYSTEM/360

超强的计算能力、高可靠性

3.X86  CPU
4.RISC CPU 小型机


软件架构往集中式发展，成为当时软件架构的主流

分布式架构的发展

1.时机成熟 
	PC机的性能不断提升
2.企业必须要做
去IOE

IBM小型机、Oracle、EMC存储设备


PC               MySQL    

2013年5月17号，最后一台IMB小型机下线

![CPU](https://github.com/lwx57280/Distributed-topics/blob/master/images-folder/2020030601.png)

单机计算机的架构—》分布式计算机架构


架构的发展演变过程

Iamp

BAT
	
什么是大型网站
	1. 访问量（tps、qps）
	2. 数据量（存储数据量）


电商平台发展

![电商平台发展](README/2020030602.png)



电商平台的发展
用户、 商品、订单

容器： tomcat   ；jsp/servlet
数据库存储： mysql

网络层面的知识：tcp/udp


第一版应用

​	![第一版应用](README/2020030603.png)

第二版 单击负载越来越高，数据库服务器和应用服务器分离

​	![第二版](README/2020030604.png)

第三版  应用服务器做集群
	
	![第三版](README/2020030605.png)
	
	
	
1 .session 

cookie 存储的是JSESSIONID
ConcurrentMap   key JSESSIONID  values session



解决session跨域共享问题
	1. session sticky
	
	2. session replication
	3. Session 集中存储
		a. 存储在db、存储在缓存服务器（redis）
	4. Cookie(主流)
		access_token(userid/token/timestamp)    在服务器端intercepter 拦截器，拦截获取access_token，解析access_token，然后拿到userId,然后判断User有效期是否在有效范围之内
		
		soa架构和微服务架构


​	
​	
1. 如何做请求转发？
	1. 通过负载均衡转发

第四版  数据库的高性能操作


​	![第四版](README/2020030606.png)
​	
	1. 数据库读写分离怎么操作？
	2. 数据库的数据同步？
	3. 数据库路由 mycat？

电商平台最多的操作： 搜索商品

​	![**电商平台最多的操作**](README/2020030607.png)



问题： 
	1. 搜索引擎的索引数据怎么去做同步，实时增量同步？ 还是定时全量同步？

第六版 解决访问量持续增高，引入缓存机制
（页面级缓存、前端可以用CDN服务器）

用户量是没有上限的
缓存、 限流、 降级

![第六版](README/2020030608.png)



第8️版本 数据库的水平/垂直拆分（数据库本身有IO瓶颈）
	    针对每个数据库配置同步规则


		单表建议不超过500W或1000W之间，不要超过1000W,因为数据量太大以后会造成单表IO性能问题
		
		优化数据库瓶颈：分库分表（按业务维度拆分）


![第8️版本](README/2020030609.png)			


第9版本  应用按服务拆分，拆分后怎么查询?

	按域名访问

![第9版本](README/20200306010.png)
