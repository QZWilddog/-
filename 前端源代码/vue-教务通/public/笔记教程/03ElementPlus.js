// Element：是饿了么公司前端开发团队提供的一套基于 Vue3 的网站组件库，用于快速构建网页。
// Element 提供了很多组件（组成网页的部件）供我们使用。例如 超链接、按钮、图片、表格等等。

// 参照官方文档，安装ElementPlus的组件库（在当前工程的目录下）
// npm install element-plus@2.4.4 --save
// 3). 在main.js 中引入ElementPlus组件库 （参照官方文档），最终 main.js 中代码如下

// import { createApp } from 'vue'
// import App from './App.vue'
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'
// const app = createApp(App)
// app.use(ElementPlus)  use 是 Vue 应用实例的一个方法，用于安装插件。  当我们调用 app.use(ElementPlus) 时，ElementPlus 插件会被初始化，它会注册所有的组件和指令到 Vue 应用中，这样在整个应用里就可以直接使用 ElementPlus 提供的组件了
// app.mount('#app')

// 在 <script setup> 里定义的变量和函数会自动暴露给模板使用。对于基本类型数据（如 number、string），它们本身不是响应式的；但对于对象和数组，Vue 3 会自动将其转换为响应式数据，
// 何时需要使用 ref 或 reactive
// 虽然 Vue 3 会自动处理对象和数组的响应式，但在某些情况下，你可能还是需要使用 ref 或 reactive：
// 基本类型数据：如果你需要让基本类型数据（如 number、string）成为响应式数据，就需要使用 ref。
// 显式控制：当你想明确控制数据的响应式行为时，使用 ref（声明响应式数据） 或 reactive（专门声明响应式对象） 会更清晰。
