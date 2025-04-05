//axios机制
// axios拦截器 request拦截器  response拦截器

// 程序优化axios：
//   1.请求处理工具类：request.js    主要解决axios请求回来数据复杂问题还有请求路径不便于维护问题
//   2. 与服务器异步交互逻辑，封装到一个单独的api   维护需要请求的路径

// URL优化处理
// // 创建axios实例对象
// const request = axios.create({
//   baseURL: '/api',  // 基础路径  发起请求时前缀并没有指定协议 ip 端口部分 则默认访问 只指定了资源访问路径 默认访问的是当前前端服务器
//   timeout: 500000   // 请求超时时间 测试阶段长
// })

// ref问题：
// ref创建的响应式对象是可变的。当你给ref对象的value赋值时，实际上是用新对象替换了原对象。当你通过queryByIdApi
// 获取后端数据并赋值给dept.value时 这里的dept.value被新的result.data对象替换了，result.data是后端返回的完整
// 对象，包含了所有属性，所以看起来就像自动扩充了属性。

// vue机制
// watch() 侦听：
// - 作用：侦听一个或多个响应式数据源，并在数据源变化时调用所给的回调函数。
// - 用法：
//   - 导入watch函数
//   - 执行watch函数，传入要侦听的响应式数据源（ref对象）和回调函数：
// 第三个可选参数，常见两个选项：
// - deep（boolean）：是否深度侦听，默认浅层侦听。
// - immediate（boolean）：是否在侦听创建时立即触发回调函数。

// 获取当前路由实例  组合式API 选项式 $router  只能在vue组件文件中使用 它返回一个响应式的当前路由位置对象。
// 这个对象包含了关于当前路由的所有信息，如路径、参数、查询参数等。
// import { useRadio } from 'vue-router'
// 当你在使用 Vue.js，特别是通过 Vue CLI 或 Vite 等现代前端构建工具创建的应用时，默认情况下，你的应用会被配置为一个单页面应用（SPA, Single Page Application）。这意味着无论你访问应用中的哪个路由路径，比如 http://localhost:5173/login，实际上都是由同一个 HTML 文件（通常是 index.html）来处理的。以下是为什么 URL 不包含具体的文件类型（如 .html）的原因：

// EL 机制：
/* <template #default="scope">：这是Vue中的作用域插槽语法。#default是简写形式，等价于v-slot:default。它表示你想要替换默认
的单元格内容模板。
在使用 <el-table> 和 <el-table-column> 时，scope 实际上是由 <el-table> 组件（即父组件）提供给 <el-table-column> 组件（即子组件）的上下文信息。这个 scope 对象并不是直接从父级 Vue 组件传递下来的属性集合，而是 <el-table> 根据其数据源生成并传递给每个 <el-table-column> 的特定于当前行的数据。
更精确的解释
<el-table>: 父组件，它绑定了数据源（如 empList），并且为每个 <el-table-column> 提供了渲染所需的数据上下文。
<el-table-column>: 子组件，定义了表格中的列，并且可以通过作用域插槽接收来自 <el-table> 的数据上下文 (scope) 来自定义单元格的内容。
scope 包含的信息
当你在 <el-table-column> 中使用 <template #default="scope"> 时，scope 对象通常包含以下内容：
row: 当前行的数据对象，允许你访问当前行的所有字段。
column: 当前列的信息，包括列的一些配置信息如 label, prop 等。
$index: 当前行的索引值，基于0开始。
在这个上下文中，scope.row代表当前行的数据对象。这里的scope.row.gender访问的是当前行数据对象中的gender字段。在某些情况下，你可能需要对数据进行一些处理或格式化之后再展示，这时使用作用域插槽（如<template #default="scope">）就变得非常有用。 */
// 当你在 <el-table-column> 中使用 <template #default="scope"> 来定义一个作用域插槽时，你实际上是在告诉 Vue 你想要手动控制该列的每一行如何渲染。这意味着你接管了该列单元格内容的生成逻辑，因此不需要再通过 prop 属性来指定数据字段。
// prop  在Element UI的表格组件中，prop属性用于直接绑定和显示数据字段，它适用于那些可以直接从数据对象中提取并展示的情况 的作用就是指定当前表格列绑定到数据源对象的哪一个属性上

// 这里不直接使用prop的原因是因为你需要根据gender字段的值来决定显示“男”还是“女”。这种需求超出了简单地将一个数据字段映射到表格列的能力。通过使用作用域插槽，你可以访问当前行的数据(scope.row)，并对这些数据进行自定义处理。

// Layout网格布局
// 在 Element Plus 的栅格系统中，<el-row> 确实代表一个完整的行容器，而 <el-col> 则用于定义该行中的列。默认情况下，Element Plus 使用的是一个 24 列的栅格系统。这意味着一行（即 <el-row>）被分为 24 个等宽的列。
// 在你的代码片段中，<el-row> 和 <el-col> 是 Element Plus 组件库中的布局组件，用于创建响应式的网格布局。

// 表单数据校验：
//   1.定义数据模型校验规则
//   2.将数据模型绑定到标签
//   3.定义表单引用 发送请求时做表单校验通过放行

// 浏览器机制：
// 浏览器提供了多种存储机制来帮助开发者在客户端保存数据，包括 localStorage、sessionStorage 和 Cookies。每种存储方式都有其特定的用途和特点，下面分别介绍它们的基本概念以及用户是否可以随意修改这些存储中的值。

// 1. LocalStorage
// 定义：localStorage 是一种持久化的存储方式，它允许你在一个域名下永久保存键值对数据，除非用户手动清除浏览器缓存或使用代码删除。
// 特点：
// 数据没有过期时间。
// 在同一个域名下的所有页面都可以访问。  键值对都是字符串
// 存储容量一般为5MB左右（不同浏览器可能有所不同）。
// 安全性：虽然 localStorage 的数据是与特定域关联的，并且不能跨域访问，但是存储的数据是以明文形式存在的，任何拥有访问权限的脚本都可以读取或修改这些数据。因此，敏感信息不应存储在 localStorage 中。
// 用户能否修改：用户可以直接通过浏览器提供的开发者工具查看并修改 localStorage 中的数据。
// 2. SessionStorage
// 定义：sessionStorage 类似于 localStorage，但它只在当前会话期间有效。关闭浏览器标签或窗口后，存储的数据将被清除。
// 特点：
// 数据仅在当前会话内有效。
// 同一浏览器的不同标签页之间不共享 sessionStorage。
// 适合用于临时保存不需要长期保留的信息。
// 安全性：如同 localStorage，sessionStorage 中的数据也是以明文形式存储的，任何有权访问该页面的脚本都可以对其进行操作。
// 用户能否修改：用户同样可以通过浏览器的开发者工具查看和修改 sessionStorage 中的数据。
// 3. Cookies
// 定义：Cookies 是最早被引入的客户端存储技术之一，主要用于服务器端识别用户。每次HTTP请求都会自动携带相关的 Cookies 到服务器。
// 特点：
// 可以设置过期时间；如果不设置，则默认为会话级别的 Cookie，在关闭浏览器时失效。
// 可以指定作用路径和域名范围。
// 支持加密传输（通过设置 Secure 标志），确保仅在 HTTPS 连接中发送。
// 容量较小，通常每个域名下最多4KB。
// 安全性：尽管 Cookies 提供了诸如 HttpOnly 标记等安全特性来防止JavaScript直接访问，但如果没有启用 HttpOnly，则 JavaScript 仍可读取和写入 Cookies。此外，即使启用了 HttpOnly，如果网站存在XSS漏洞，攻击者仍然可能间接获取到 Cookies。
// 用户能否修改：用户可以通过浏览器设置查看和编辑 Cookies，不过一些高级选项如 HttpOnly 和 Secure 标志增加了额外的安全层，限制了某些类型的访问。
// 总结
// 关于用户是否能随意修改：对于 localStorage 和 sessionStorage 来说，用户可以直接通过浏览器的开发者工具查看和修改其中的内容。而对于 Cookies，虽然用户也能查看和编辑，但如果设置了 HttpOnly 标志，则JavaScript无法直接访问，从而提供了一定程度的安全保障。
// 最佳实践：鉴于上述风险，重要的是不要在这些存储机制中保存任何敏感信息（如密码、个人身份信息等）。如果确实需要存储敏感数据，应考虑使用更安全的方法，比如服务器端会话管理结合加密技术。

// Webpack 加载器的作用
//   Webpack 加载器（Loader）是 Webpack 的一个重要特性，用于对项目中的非 JavaScript 文件进行预处理。加载器可以将这些文件转换为模块，然后添加到你的依赖图中。简单来说，加载器让你能够以模块的方式引入各种类型的文件，而不仅仅是 JavaScript 文件。

//   在 Webpack 构建的 Vue 或其他前端项目中，“绝对路径”指的是相对于项目的根目录（即包含 index.html 文件的目录）

// nginx机制：
//   conf配置：
//     location 指令：用于定义如何处理不同 URI 请求。

//     在nginx中：
//     Nginx 支持以下几种 location 匹配方式：
//       精确匹配 (=)：精确匹配用于当请求的 URI 完全等于指定的路径时。
//       前缀匹配（无修饰符或 ^~）： 匹配以指定字符串开头的所有 URI。^~ 修饰符确保一旦匹配成功，就不继续检查正则表达式。
//       正则表达式匹配 (~ 或 ~*)：使用正则表达式来匹配请求的 URI。。~ 表示区分大小写的匹配，而 ~* 表示不区分大小写的匹配。

//       优先级：精确匹配 前缀匹配 正则表达式匹配 普通前缀匹配

//       在 Nginx 配置中： 不是直接移除 然后用这个捕获的内容替换整个匹配的部分
//       rewrite 指令用于修改请求的 URI。它允许你根据特定的模式（通常是一个正则表达式）重写请求的 URI，并可以选择性地停止进一步处理或重定向。
//       break: 标志位，表示一旦执行完当前的重写规则后，立即停止处理其他重写规则，并继续处理当前请求。
//       /$1 不是正则表达式，而是重写后的 URI 模板。这里的 $1 是一个引用，指的是在正则表达式中捕获的第一个分组（即 (.*)）匹配到的内容  前面/是普通的/ 用于替换后前面$1的前缀。

// linux部署：
//    前端部署：Vue.js 应用通过 npm run build 或类似命令打包成静态文件，这些文件可以由任何 Web 服务器（例如 Nginx）直接提供给客户端浏览器，无需 Node.js 环境。
