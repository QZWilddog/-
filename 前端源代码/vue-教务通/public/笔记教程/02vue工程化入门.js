// Vue 的项目结构
// 文件夹：
//.vscode .vscode 文件夹在你的 Vue 项目（或任何其他类型的项目）中用于存放与 Visual Studio Code 编辑器相关的配置文件。这些配置文件可以帮助你定制化开发环境，提供更好的编码体验，以及确保团队成员之间的开发环境一致性。
// node_modules/: 存放项目的所有依赖包 模块，由 npm 或 yarn 自动生成和管理
// public/: 静态文件目录，里面的文件不会被 Webpack 处理，最终会原样复制到打包目录下
//   -favicon.ico: 网站的图标。

// src/: 源代码目录，存放应用的主要代码
//   -assets/: 存放静态资源，如图像、字体等
//   -components/: 存放 Vue 组件，每个组件都是一个独立的 .vue 文件
//   App.vue: 应用的根组件，定义具体要显示的内容    通常包含其他组件的引用和布局
//   main.js: 入口文件 定义要渲染的根组件，创建 Vue 实例并挂载到指定的 DOM 元素上 定义那块区域要显示内容
//   views:视图 存放视图组件
// 文件：
// index.html: 主页入口文件 默认首页 定义实现基本框架 引入main.js 入口文件，定义一个空的根节点，Vue 实例将挂载在根节点下
// .gitignore: Git 忽略文件列表，指定哪些文件和目录不被包含在版本控制中
// package.json: 项目的依赖、包括项目名 版本号 依赖包等
// README.md: 项目的说明文件，通常用于描述项目、如何安装和使用等信息
// vue.config.js: Vue的配置文件，用于修改默认配置 如端口号 配置代理等

// 文件解释：
// 其中*.vue是Vue项目中的组件文件，在Vue项目中也称为单文件组件（SFC）。
// Vue 的单文件组件会将一个组件的逻辑(JS)，模板(HTML) 和样式(CSS) 封装在同一个文件里（*.vue）

// 启动项目
// - 方式一：命令行
// 启动项目，我们可以在命令行中执行命令：npm run dev，就可以启动Vue项目了。 vite底层构建工具+vue3

// API风格- Vue的组件有两种不同的风格：
// 组合式API:
//   - 组合式API：是Vue3提供的一种基于函数的组件编写方式，通过使用函数来组织和复用组件的逻辑。它提供了一种更灵活、更可组合的方式来编写组件。代码形式如下：
//   <script setup>
//   import { ref, onMounted } from 'vue';
//   const count = ref(0); //声明响应式变量
//   function increment(){ //声明函数
//     count.value++;}
//   onMounted(() => { //声明钩子函数
//     console.log('Vue Mounted....');
//   })
//   </script>
//    -scoped属性：它的CSS只会影响当前组件元素
//   - setup语法糖：是一个标识，告诉Vue需要进行一些处理，让我们可以更简洁的使用组合式API。
//  语法糖”（Syntactic sugar）是计算机科学领域的一个术语，指的是在编程语言里添加的某种语法。这种语法不会影响语言的功能，但能让代码的编写更加简洁、易读，提升编码的效率和舒适度
//   - ref()：接收一个内部值，返回一个响应式的ref对象，此对象只有一个指向内部值的属性 value。
// - onMounted()：在组合式API中的钩子方法，注册一个回调函数，在组件挂载完成后执行。   与选项式API钩子函数调用有区别前面都加on 且要从vue模块中导入。

// 选项式API:  尽管在 ApiDemo01.vue 里没有显式地使用 export default 语句，但 Vue 单文件组件在编译后会默认导出一个组件选项对象，因此你可以使用默认导入的方式引入组件。
// 可以用包含多个选项的对象来描述组件的逻辑，如：data，methods，mounted等。选项定义的属性都会暴露在函数内部的this上，它会指向当前的组件实例。
// <script>
// export default{
//    data() {
//       return {
//          count: 0
//       }
//    },
//    methods: {
//       increment: function(){
//          this.count++
//       }
//    },
//    mounted() {
//       console.log('vue mounted.....');
//    }
// }
// </script>
// 在Vue中的组合式API使用时，是没有this对象的，this对象是undefined。

// 如果用到axios等第三方库，需要安装axios的依赖：npm install axios

// 在Vue里，组件可以通过属性（props）来接收父组件传递的数据。父组件利用 v-bind 指令把
// 数据传递给子组件，子组件则通过 props 选项来定义接收的属性。

// package.jsonpackage.json依赖文件解释
// {
//   // 项目名称
//   "name": "vue-project1",
//   // 项目版本号
//   "version": "0.0.0",
//   // 定义项目的脚本命令
//   "scripts": {
//     // 启动开发服务器
//     "dev": "vite",
//     // 构建生产环境代码
//     "build": "vite build",
//     // 预览生产环境构建结果，端口为 4173
//     "preview": "vite preview --port 4173"
//   },
//   // 项目运行时依赖的第三方库
//   "dependencies": {
//     // 用于发送 HTTP 请求的库，版本要求为 1.8.2 及以上
//     "axios": "^1.8.2",
//     // 实用的 JavaScript 工具库，版本要求为 4.17.21 及以上
//     "lodash": "^4.17.21",
//     // Vue.js 框架，版本要求为 3.2.38 及以上
//     "vue": "^3.2.38"
//   },
//   // 项目开发时依赖的第三方库
//   "devDependencies": {
//     // Vite 的 Vue 插件，版本要求为 3.0.3 及以上
//     "@vitejs/plugin-vue": "^3.0.3",
//     // Vite 构建工具，版本要求为 3.0.9 及以上
//     "vite": "^3.0.9"
//   }
// }
