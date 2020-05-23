## 项目介绍

Universer 项目，基于 Android，为作者边学习边积累而成，包含一些封装的类库、工具方法、架构设计等，为 多App、多Module 结构，包含多个App 模块以及众多的Module 组件模块

## 项目结构

#### Universe 主仓库
> https://github.com/techflowing/Universe

包含：

1. Universe项目的核心配置，包含 gradle配置模板、混淆配置模板等
2. app-sample 工程，包含一些使用示例
3. m-sample 模块，为 Module 示例，新建 Module 时可直接copy此 Module
4. local-maven 本地 maven 仓库，一些 module 会直接发布 AAR 到此

#### Lego 组件仓库
> https://github.com/techflowing/Universe-Lego

Module 组件仓库，包含一些功能独立的模块，Module 命名以 `m-` 为前缀

#### Gradle-Plugin 仓库
> https://github.com/techflowing/Universe-Gradle-Plugin

Gradle 插件仓库，包含一些 Gradle Plugin 的实现，Module 命名以 `p-` 为前缀

## 更多使用说明

参考：[项目详细说明](http://techflowing.cn/wiki/detail/7)
