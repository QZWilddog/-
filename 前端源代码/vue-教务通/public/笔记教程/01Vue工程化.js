// Vue是一款用于构建用户界面的渐进式JavaScript框架
// Vue本质：基于数据渲染出用户看的页面  即：数据驱动视图  热部署

// Vue核心包开发 局部 模块改造 || Vue 核心包+Vue插件工程化开发  整站开发


// 个人开发模式存在如下问题：
// - 不规范：每次开发都是从零开始，比较麻烦
// - 难复用：多个页面中的组件共用性不好
// - 难维护：js、图片等资源没有规范化的存储目录，没有统一的标准，不方便维护

// 前端工程化方式的开发：这四个就叫前端工程化   统一规范 提高复用 便于维护管理  现在用的最多的就是Vue工程化开发
// - 模块化：将项目划分模块，单独开发维护 提高效率 将js和css等，做成一个个可复用模块
// - 组件化：将页面各个组成部分封装为一个一个组件，提高复用 我们将UI组件，css样式，js行为封装成一个个的组件，便于管理
// - 规范化：我们提供一套标准的规范的目录、接口和编码规范，所有开发人员遵循这套规范
// - 自动化：项目的构建，测试，部署全部都是自动完成

// Vue工程化：   脚手架：就是快速构建项目结构的工具

// 环境准备：
//   - 介绍：create-vue是Vue官方提供的最新的脚手架工具，用于快速生成一个工程化的Vue项目。
//   - create-vue提供了如下功能：
//     - 统一的目录结构
//     - 本地调试
//     - 热部署
//     - 单元测试
//     - 集成打包上线
//   - 而要想使用create-vue来创建vue项目，则必须安装依赖环境：NodeJS

//   NodeJS: 一个免费的、开源的、跨平台的JavaScript运行环境。
//   npm: NodeJS的包管理器，用于安装和管理NodeJS的包。类似maven 可以连接远程仓库下载需要的包比如：axios、vue-router、vuex等。
//   配置npm的全局安装路径 使用 管理员身份 运行命令行，在命令行中，执行如下指令：
// npm config set prefix "D:\develop\NodeJS"
// 注意：D:\develop\NodeJS  这个目录是NodeJS的安装目录 !!!!!
// 默认从远程仓库下载会很慢切换到国内的镜像源：淘宝镜像源 npm config set registry https://registry.npmmirror.com
//                                                       JDK：Java开发工具包  包含JRE，同时包含Java开发工具包
//                                                       JRE：Java运行环境 包含JVM，同时包含Java运行环境
//                                                       JVM：Java虚拟机，用于运Java程序
//                                                       三者的关系：JDK包含JRE，JRE包含JVM  。

// Vue项目创建
// 创建一个工程化的Vue项目，执行命令：npm create vue@3.3.4   符号后面的数字表示版本号
// 详细步骤说明：
// - Project name：------------------》项目名称，默认值：vue-project，可输入想要的项目名称。
// - Add TypeScript? ----------------》是否加入TypeScript组件？默认值：No。
// - Add JSX Support? --------------》是否加入JSX支持？默认值：No。
// - Add Vue Router...--------------》是否为单页应用程序开发添加Vue Router路由管理组件？默认值：No。
// - Add Pinia ...----------------------》是否添加Pinia组件来进行状态管理？默认值：No。
// - Add Vitest ...---------------------》是否添加Vitest来进行单元测试？默认值：No。
// - Add an End-to-End ...-----------》是否添加端到端测试？默认值No。
// - Add ESLint for code quality? ---》是否添加ESLint来进行代码质量检查？默认值：No。
// 提示：执行上述指令，将会安装并执行 create-vue，它是 Vue 官方的项目脚手架工具
// 项目创建完成以后，进入vue-project01 项目目录，执行命令安装当前项目的依赖：npm install

// 为什么要执行npm install？
// - 当使用npm create vue@3.3.4创建Vue项目时，脚手架会生成一个package.json文件，该文件记录了项目的元数据和依赖信息。
// npm install命令会根据package.json文件中的dependencies和devDependencies字段，从npm仓库下载并安装这些依赖项到项目的node_modules目录中。

// 3. 组件化开发
// Vue.js 采用组件化开发的思想，每个 .vue 组件都是一个独立的模块，有自己的状态和行为。组件之间可以相互嵌套和组合，形成复杂的应用界面。这种方式使得代码的维护和扩展变得更加方便。

// main.js：除了引入依赖，还承担着初始化 Vue 应用的任务。它会创建 Vue 实例，安装插件，挂载应用到 DOM 节点等，更侧重于应用的启动和初始化。
// main.js：主要作用于 Vue 应用的运行阶段，是 Vue 应用启动的入口。
