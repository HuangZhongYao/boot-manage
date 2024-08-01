<p align="center">
  <a href="https://github.com/HuangZhongYao/boot-manage-ui">
    <img alt="Boot Admin Logo" width="200" src="./docs/images/logo.png">
  </a>
</p>
<p align="center">
  <a href="./LICENSE">
    <img alt="MIT License" src="https://badgen.net/github/license/zclzone/vue-naive-admin"/>
  </a>
  <a href="https://github.com/huangZhongYao/boot-manage" >
    <img alt="GitHub" width="20" src="./docs/images/github.svg" >
  </a>
  <a href="https://gitee.com/smog_huang/boot-manage" >
    <img alt="Gitee" width="20" src="./docs/images/gitee.svg" >
  </a>
</p>

# 🍊 Boot Admin 后端 🍊
Boot Admin 后端项目SpringBoot单体架构版

## 简介
Boot Admin 是一款极简风格的后台管理模板。  
前端使用 Vite + Vue3 + Naive UI + Pinia + Unocss + 无 Typescript降低门槛。

## 设计理念

## 技术栈
- <img width="20" src="./docs/images/java.svg" > Java 17
- <img width="20" src="./docs/images/springboot.svg" > SpringBoot 3.2.x
- <img width="20" src="./docs/images/mybatis.svg" > Mybatis-Plus
- <img width="20" src="./docs/images/sa-token.png" > Sa-Token (相比SpringSecurity轻量、功能强大、API使用简单)
- JWT
- <img width="20" src="./docs/images/swagger.svg" > knife4j-ui + Swagger 3
- <img width="20" src="./docs/images/redis.svg" > Redis
- <img width="20" src="./docs/images/mysql.svg" > MySQL 8

## 特性
- 😋 遵守Restful API风格 。
- 😀 使用当前最新技术 Java17 + SpringBoot 3.x 。 
- 😉 封装参数验证注解 ，基于`validation`封装常用非空、手机号、车牌号、邮箱、中文、非中文、IP、MAC... 常见类型数据参数验证, 减少90%校验参数代码。 源码中几乎很少看到判断参数的代码。
- 😄 统一返回值包装 ， 通过`ResponseBodyAdvice + 全局异常处理` 进行统一返回值包装处理。代码中再也无需手写 `R.ok(data) 和 R.error(msg,code)`等类似代码
- 🍗 封装通用DTO ， 对于基础字段无需重复定义只需继承 `BaseXXXDTO`。
- 🍔 封装对象转换工具 ， VO、DTO、Entity之间互相转换一行代码搞定，结合函数式接口还可以在转换时添加逻辑。
- ⏲  LocalDateTime ， 日期类型使用Java8 更安全的LocalDateTime， 配置jackson消息转换器解决Java8 LocalDateTime序列化时`'T'`。
- 💻 代码生成 ， 根据表一键生成前后端CRUD代码减少70%重复劳动专注业务逻辑。
  
## 安装

## 前端

Boot Admin 提供一套Java最新技术开发的后端代码，简易上手、效率高SpringBoot单体架构版，提供RABC、代码生成、系统基础等接口所需的一些基础接口  。  
技术栈:
- Java 17
- SpringBoot 3.2.x
- Mybatis-Plus
- Sa-Token
- JWT
- Redis
- MySQL 8

源码
- 源码 GitHub: [boot-manage-ui | github](https://github.com/HuangZhongYao/boot-manage-ui)
- 源码 Gitee: [boot-manage-ui | gitee](https://gitee.com/smog_huang/boot-manage-ui)

## 文档

- 项目文档:

## 版权说明

本项目使用 `MIT协议`，默认授权给任何人，被授权人可免费地无限制的使用、复制、修改、合并、发布、发行、再许可、售卖本软件拷贝、并有权向被供应人授予同等的权利，但必须满足以下条件:

- 复制、修改和发行本项目代码需包含原作者的版权及许可信息，包括但不限于文件头注释、协议等

简单说作者只想保留源码中注释版权，没有任何其他限制。
