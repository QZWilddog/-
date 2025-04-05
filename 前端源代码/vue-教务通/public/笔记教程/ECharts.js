// ECharts:一个基于js的开源可视化图表库
// (1) 使用 npm 安装:
//   npm install echarts --save     --save 参数时，安装的包会被记录到 package.json 文件的 dependencies 字段中。
//     如果不--save 运行 npm install 时不会自动安装这个包。

// 在绘图前我们需要为 ECharts 准备一个定义了高宽的 DOM 容器。在刚才的例子 </head> 之后，添加：

// <body>
//   <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
//   <div id="main" style="width: 600px;height:400px;"></div>
// </body>



// {
//   "name": "vue-tlias-management",  // 项目的名称，表示这是一个叫“vue-tlias-management”的项目。
//   "version": "0.0.0",              // 项目的版本号，目前是初始版本（0.0.0）。
//   "scripts": {                     // 定义了一些常用的命令，方便开发者快速运行任务。
//     "dev": "vite",                 // 启动开发环境的命令，运行后可以实时预览项目。
//     "build": "vite build",         // 打包项目的命令，用于将代码打包成生产环境可用的文件。
//     "preview": "vite preview --port 4173",  // 预览打包后的项目，运行在指定端口（4173）上。
//     "lint": "eslint . --ext .vue,.js,.jsx,.cjs,.mjs --fix --ignore-path .gitignore"  
//                                     // 检查代码质量的命令，自动修复一些常见的代码问题，并忽略.gitignore中定义的文件。
//   },
//   "dependencies": {                // 生产环境依赖：项目运行时需要的库或工具。
//     "@element-plus/icons-vue": "^2.3.1",  // Element Plus 的图标组件库，提供各种常用的图标。
//     "axios": "^1.7.2",             // 一个用于发送 HTTP 请求的库，常用来与后端 API 交互。
//     "echarts": "^5.6.0",           // 一个强大的数据可视化库，用来生成各种图表（如柱状图、折线图等）。
//     "element-plus": "^2.4.4",      // 一个基于 Vue 3 的 UI 组件库，提供了很多现成的 UI 组件（如按钮、表格等）。
//     "vue": "^3.2.38",              // Vue.js 核心库，这是项目的框架基础。
//     "vue-router": "^4.1.5"         // Vue 的路由管理库，用来实现页面之间的跳转和导航。
//   },
//   "devDependencies": {             // 开发环境依赖：仅在开发阶段需要的工具或库。
//     "@rushstack/eslint-patch": "^1.1.4",  // 一个 ESLint 补丁工具，用于解决某些配置问题。
//     "@vitejs/plugin-vue": "^3.0.3",      // Vite 的 Vue 插件，支持 Vue 单文件组件的开发。
//     "@vue/eslint-config-prettier": "^7.0.0",  // Vue 的 ESLint 配置，结合 Prettier 实现代码格式化。
//     "eslint": "^8.22.0",           // 一个代码检查工具，帮助发现代码中的潜在问题。
//     "eslint-plugin-vue": "^9.3.0", // 专门用于检查 Vue 代码的 ESLint 插件。
//     "prettier": "^2.7.1",          // 一个代码格式化工具，让代码风格更统一、更美观。
//     "vite": "^3.0.9"               // 一个快速的前端构建工具，用来启动开发服务器和打包项目。
//   }
// }