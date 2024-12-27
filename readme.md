# TrionesAdminBackend(SpringBoot)
该工程是TrionesAdmin项目的SpringBoot 服务端实现。
- Spring boot 3.x
- JDK 17+

## 工程结构
本工程分为 
> infrastructure 基础设施层 \
> core 核心业务层 \
> interfaces 接口层 \
> serve 服务入口

## 安装
1. 下载源码
2. 该工程使用postgresql数据库，并使用liqubase作为数据库结构同步工具
   1. 在数据库中创建一个数据库。
   2. 在db模块下，复制liquibase-example.properties 到 liquibase.properties，并设置其中的参数
   3. 通过maven插件，在idea中，直接执行liquibase:update ,或者在db 模块下 通过控制台命令执行 mvn liquibase:update 将数据结构同步到新的数据库上。
   4. 由于，地区数据的初始化耗时较久，在当前的 changelog-master文件中将 数据初始化的文件引入，先注释了。可以自己选择打开注释，或者直接通过sql 文件进行导入。
3. 工程配置文件
   在serve模块下，复制application-example.properties为自己的环境配置，默认使用application-default.properties作为本地环境配置文件，将相关信息改成自己的即可。

---

#### 互相吹捧，共同进步

<div style="width: 100%;text-align: center;">
   <img src="images/shuque_wx.jpg" width="200px" alt="">
</div>
