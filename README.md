## 总纲

实践出真知；使用最新的框架，学习最新的知识；学习、总结、借鉴，择善而从；

创建时间：2022年05月07日，使用的版本情况如下：

* Gradle 版本：7.2
* AGP 版本：7.1.2
* Kotlin 版本：1.5.30
* Java 版本：8 & 11
* Android SDK：31

其它原则：
1. 优先使用 Kotlin 编码

## 代码提交 Commit 规范

* feat：引入新功能(feature)
* fix：修补bug
* docs：添加或更新文档(documentation)
* style： 格式调整（不影响代码运行的变动，空格，花括号换行）
* perf: 性能提升的代码更改(performance)
* refactor：重构（即不是新增功能，也不是修改bug的代码变动）
* test：增加测试代码用例
* build：依赖调整，影响构建系统、或者外部依赖第三方库的更改
* ci：持续集成CI、CD的配置文件或者脚本的更改
* chore：不属于之上范畴的其他不修改源文件及测试文件的其他杂项更改

## Project 建立原则

### 命名分类原则
* module，表示一些具有业务逻辑的功能模块
* library，表示一些功能模块、工具之类的封装，业务无关性

### 新建模块

1. 直接复制 tpl-library、tpl-application 文件夹，同时改名符合要求即可，前缀为 app-、module-、library- 之一
2. 修改根包名，前缀统一是：win.techflowing.android
3. 修改 AndroidManifest.xml 文件内的 package，和根包名保持一致
4. sync 项目，会自动 include project
5. 每个 module 都在 README.md 中写明模块作用等说明


## 相关引用

* [Kotlin 编码规范](https://www.kotlincn.net/docs/reference/coding-conventions.html)
* [Android KTX](https://developer.android.com/kotlin/ktx?hl=zh-cn)
* [配色方案](https://www.materialpalette.com/blue/cyan)