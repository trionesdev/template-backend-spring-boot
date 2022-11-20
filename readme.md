### 工程结构
本工程分为 
> infrastructure 基础设施层 \
> core 核心业务层 \
> rest-backend 后端rest接口层 \
> serve 服务入口

目录结构示意图： https://mm.edrawsoft.cn/app/editor/kSKPIaylZDFnqleGtfXCV9wA1Nmq7tk4

### 说明
本工程，默认为backend工程，一般情况下，只需要该工程即可。如果有微服务需求，则根据实际情况，调整。
例如，需要添加一个pay的服务。则在新的工程下，将第一级的backend改为pay即可。 ms.triones.backend -> ms.triones.pay

## 基础配置
#### 配置swagger
在infrastructure 模块下 ms.triones.infrastructure.conf.swagger.SwaggerConfiguration 进行配置
#### 配置jackson
位置 ms.triones.infrastructure.conf.jackson
#### 配置mybatis-plus
位置 ms.triones.infrastructure.conf.mybatisplus