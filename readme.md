## 工程结构
本工程分为 
> infrastructure 基础设施层 \
> core 核心业务层 \
> rest-backend 后端rest接口层 \
> serve 服务入口

目录结构示意图： https://mm.edrawsoft.cn/app/editor/kSKPIaylZDFnqleGtfXCV9wA1Nmq7tk4

## 说明
本工程，默认为backend工程，一般情况下，只需要该工程即可。如果有微服务需求，则根据实际情况，调整。
例如，需要添加一个pay的服务。则在新的工程下，将第一级的backend改为pay即可。 ms.triones.backend -> ms.triones.pay

## 基础配置
### 配置swagger
在infrastructure 模块下 ms.triones.infrastructure.conf.swagger.SwaggerConfiguration 进行配置
### 配置jackson
位置 ms.triones.infrastructure.conf.jackson
### 配置mybatis-plus
位置 ms.triones.infrastructure.conf.mybatisplus


## 依赖
### 云服务依赖
#### 添加依赖管理
```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.moensun.spring.boot</groupId>
            <artifactId>moensun-cloud-integration-spring-boot-starters</artifactId>
            <version>1.0-SNAPSHOT</version>
            <type>pom</type>
            <scope>import</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-dependencies</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
</dependencyManagement>
```

##### 添加阿里云依赖
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-cloud-integration-aliyun-spring-boot-starter</artifactId>
</dependency>
```

##### 添加华为云依赖
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-cloud-integration-huaweicloud-spring-boot-starter</artifactId>
</dependency>
```

##### 添加腾讯云服务依赖
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-cloud-integration-tencentcloud-spring-boot-starter</artifactId>
</dependency>
```

##### 添加七牛云依赖
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-cloud-integration-qiniu-spring-boot-starter</artifactId>
</dependency>
```

##### 添加其他依赖
包含 minio
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-cloud-integration-other-spring-boot-starter</artifactId>
</dependency>
```

### 微信集成
#### 添加依赖管理
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-weixin-spring-boot-starters</artifactId>
    <version>1.0-SNAPSHOT</version>
    <type>pom</type>
    <scope>import</scope>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

#### 公众号集成
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-weixin-offiaccount-spring-boot-starter</artifactId>
</dependency>
```
#### 小程序集成
```
<dependency>
    <groupId>com.moensun.spring.boot</groupId>
    <artifactId>moensun-weixin-miniprogram-spring-boot-starter</artifactId>
</dependency>
```