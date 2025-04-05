// vue介绍：是一款用于构建用户界面的渐进式的JavaScript框架（官方网站：https://cn.vuejs.org）。
// 构建用户界面：构建用户界面是指，在Vue中，可以基于数据渲染出用户看到的界面。
// 渐进式：渐进式中的渐进呢，字面意思就是 "循序渐进"。Vue生态中的语法呢是非常多的，比如声明式渲染、组件系统、客户端路由（VueRouter）、状态管理（Vuex、Pinia）、构建工具（Webpack、Vite）等等。
// 所谓渐进，指的是我们使用Vue框架呢，我们不需要把所有的组件、语法全部学习完毕才可以使用Vue。 而是，我们学习一点就可以使用一点了
// 框架：就是一套完整的项目解决方案，用于快速构建项目 。- 优点：大大提升前端项目的开发效率 。- 缺点：需要理解记忆框架的使用规则

// Vue本质：基于数据渲染出用户看的页面  即：数据驱动视图
// Vue开发准备工作：
// - 准备一个html文件，并在其中引入Vue模块 （参考官方文档，复制过来即可）【注意：模块化的js，引入时，需要设置 type="module"】
import {
  createApp,
  ref,
} from "https://unpkg.com/vue@3/dist/vue.esm-browser.js"; // 从指定的URL导入Vue 3的createApp和ref函数 // createApp用于创建一个新的Vue应用实例 // ref用于创建响应式数据
// - 创建Vue程序的应用实例，控制视图的元素（HTML元素）).mount('#app')接管那个元素 接管那个区域
// - 准备元素（div），交给Vue控制
createApp({
  // 使用createApp函数创建一个新的Vue应用实例
  // data选项是一个函数，返回一个对象
  // 这个对象包含了应用的响应式数据
  data() {
    // 传入一个包含data选项的对象作为参数
    return {
      // 定义一个名为m的属性，初始值为'hello wrld'
      m: "hello wrld", // 通过插值表达式{{m}}在模板中使用m的值
    };
  },
  // 使用mount方法将Vue应用挂载到id为app的DOM元素上
}).mount("#app"); // CSS选择器，选择id为app的元素

// 差值表达式中的语法：
// {{}} 可以用三元表达式
// {{}} 可以用过滤器
// {{}} 可以用计算属性
// {{}} 可以用方法
// {{}} 可以用指令

// 4. Vue指令
// v-for	列表渲染，遍历容器的元素或者对象的属性
// 作用：列表渲染，遍历容器的元素或者对象的属性
// 语法：<tr v-for="(item,index) in items" :key="item.id">{{item}}</tr>
// 参数：
// - items 为遍历的数组
// - item 为遍历出来的元素
// - index 为索引/下标，从0开始 ；可以省略，省略index语法： v-for = "item in items"
// key：
// - 作用：给元素添加的唯一标识，便于vue进行列表项的正确排序复用，提升渲染性能
// - 推荐使用id作为key（唯一），不推荐使用index作为key（会变化，不对应）
// 注意：遍历的数组，必须在data中定义； 要想让哪个标签循环展示多次，就在哪个标签上使用 v-for 指令。

// v-bind	为HTML标签绑定属性值，如设置 href , css样式等
// 作用：动态为HTML标签绑定属性值，如设置href，src，style样式等。
// 语法：v-bind:属性名="属性值" <img v-bind:src="item.image" width="30px">
// 简化：:属性名="属性值" <img :src="item.image" width="30px">
// 注意：v-bind 所绑定的数据，必须在data中定义/或基于data中定义的数据而来。
// 不能使用差值表达式  必须在data中定义/或基于data中定义的数据而来。
//   利用 <template #default="{ data }"> 定义了一个默认插槽，并且接收从子组件传递过来的 data。
// 子组件里使用 <slot :data="data"></slot> 把 data 传递给插槽。
// 2. 在子组件中直接使用 props 显示数据
// 子组件也能够直接使用 props 接收传递过来的数据并显示。
/* <el-form> 是 Element UI 提供的表单组件，它内部对 :model 做了特殊处理，把单向绑定和事件监听结合起来，借助表单内部表单项的 v-model 指令实现双向绑定 */
// 本质还是单项数据绑定  但是通过响应是对象与表单项的v-model 指令实现双向绑定  从而看起来是双向绑定了

// 作用：这两类指令，都是用来控制元素的显示与隐藏的
// v-if/v-else-if/v-else	条件性的渲染某元素，判定为true时渲染,否则不渲染
// v-if：
// - 语法：v-if="表达式"，表达式值为 true，显示；false，隐藏
// - 原理：基于条件判断，来控制创建或移除元素节点（条件渲染）
// - 场景：要么显示，要么不显示，不频繁切换的场景
// - 其它：可以配合 v-else-if / v-else 进行链式调用条件判断
// 注意：v-else-if必须出现在v-if之后，可以出现多个； v-else 必须出现在v-if/v-else-if之后 。
// v-model 一般用于表单输入元素，实现双向数据绑定。也就是用户输入会更新数据，数据变化也会更新输入框内容。而 v-bind 是单向绑定，只是把数据传递给组件或者 HTML 元素。

// v-show	根据条件展示某元素，区别在于切换的是display属性的值
// - 语法：v-show="表达式"，表达式值为 true，显示；false，隐藏
// - 原理：基于CSS样式display来控制显示与隐藏
// - 场景：频繁切换显示隐藏的场景

// v-model	在表单元素上创建双向->数据模型和视图页面（数据绑定-> 设置和获取表单元素的值)
// - 作用：在表单元素上使用，双向数据绑定。可以方便的 获取 或 设置 表单项数据
// - 语法：v-model="变量名"
// - 这里的双向数据绑定，是指 Vue中的数据变化，会影响视图中的数据展示 。 视图中的输入的数据变化，也会影响Vue的数据模型 。
// 双向绑定 页面内容发生变化会影响数据模型，数据模型发生变化也会影响页面内容
// 注意：v-model 中绑定的变量，必须在data中定义。

// v-on	为HTML标签绑定事件 添加事件监听
// 作用：为html标签绑定事件（添加时间监听）
// 语法：
// - v-on:事件名="方法名"
// - 简写为 @事件名="…"
// - <input type="button" value="点我一下试试" v-on:click="handle">
// - <input type="button" value="点我一下试试" @click="handle">

// v-slot 是 Vue 用来定义插槽的指令，default 代表默认插槽。当组件提供了多个插槽时，default 插槽一般是没有具名的那个插槽。
// 槽（Slot）是一种强大的组件化特性，它允许你在组件的模板中预留一个位置，这个位置可以由使用该组件的父组件来填充具体的内容。插槽的主要作用体现在以下几个方面：

// Vue中createapp常用的方法

// data 方法 声明响应式数据
// 用于定义组件的数据。返回一个对象，对象里的属性会成为组件实例的数据。

// 2. methods 方法
// 用来定义组件的方法，这些方法可以在模板里调用。
// 注意： methods函数中的this指向Vue实例，可以通过this获取到data中定义的数据。

// 3. computed 方法
// 用于定义计算属性，计算属性的值会依据依赖的数据自动更新。

// 4. watch 方法
// 用于监听数据的变化，当数据变化时执行相应的回调函数。

// 5. mount 方法
// 把应用实例挂载到DOM元素上。

// 6. directive 方法
// 用于注册全局指令。

// 7. component 方法
// 用于注册全局组件。

// 生命周期:只一个对象在内存中创建到销毁的过程，这个过程分为8个阶段 创建 Vue 到销毁 Vue 对象的整个过程及各个阶段对应的钩子函数
// vue的8个声明周期,每触发一个生命周期，就会执行一次对应的函数(叫做生命周期方法 也叫钩子函数)定义好不用自己调用,会自动调用
// 1. beforeCreate    组件实例创建之前
// 2. created         组件实例创建完成
// 3. beforeMount     组件挂载之前
// 4. mounted         组件挂载完成,通常发送请求 获取数据  挂载完成，Vue初始化成功，HTML页面渲染成功。以后我们一般用于页面初始化自动的ajax请求后台数据
// 5. beforeUpdate    组件更新之前
// 6. updated         组件更新完成
// 7. beforeUnmount   组件卸载之前
// 8. unmounted       组件卸载完成
// 在选项式API和组合式API中 调用钩子函数的方式不同 前面加on

// 通过 slot(插槽) 可以获取到 row, column, $index 和 store（table 内部的状态管理）的数据，用法参考 demo。

{
  /* <body>
  <div id="app">
     {{user}} <!-- 这里的语法存在问题。在JavaScript里，逗号运算符会返回最后一个表达式的值，-->
    <form action="" class="f1">
      <label for="nama">姓名：</label>
      <input type="text" name="nama" id="nama" placeholder="请输入姓名" v-model="user.name">

      <label for="gender">性别</label>
      <select name="gender" id="gender" v-model="user.gender">
        <option value=""></option>
        <option value="1">男</option>
        <option value="2">女</option>
      </select>

      <label for="p">职位：</label>
      <select name="p" id="p" v-model="user.p">
        <option value=""></option>
        <option value="张三">张三</option>
        <option value="李四">李四</option>
        <option value="王五">王五</option>
        <option value="赵六">赵六</option>
        <option value="田七">田七</option>
        <option value="王八">王八</option>
        <option value="吴九">吴九</option>
      </select>
      <!-- 简写 -->
      <input type="button" value="提交" @click="f1" >  
      <input type="button" value="清除" v-on:click="f2">
      <!-- <input type="button" v-on:click="f1()"> 第一种  -->
    </form>
    <table>
      <tr>
        <th>id</th>
        <th>name</th>
        <th>age</th>
        <th>照片</th>
      </tr>
      <tr v-for="(e, index) in arrlist" :key="e.name">
        <td>{{index+1}}</td> <!-- 差值表达式不能用在标签内-->
        <td>{{e.name}}</td>
        <td>
          {{e.age >= 20 ? "成年" : "未成年"}}
          <!-- - 场景：要么显示，要么不显示，不频繁切换的场景 原理：基于条件判断，来控制创建或移除元素节点（条件渲染 -->
          <!-- <span v-if="e.age < 20">年龄保密</span> 
          <span v-else>{{e.age}}岁</span> -->
          <!-- - 原理：基于CSS样式display来控制显示与隐藏 --><!-- - 场景：频繁切换显示隐藏的场景 -->
          <span v-show="e.age < 20">年龄保密</span>
          <span v-show="e.age >= 20">{{e.age}}岁</span>
        </td>
        <td class="im"><img v-bind:src="e.img" alt="{{e.name}}"></td> 
      </tr>
    </table>
  </div>
  
  <script type="module">
    import { createApp, ref } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js';

    createApp({
        data() {
          return {
            user:{
              name: '',
              gender: '',
              p: ''
            },
            arrlist: [
              {"name":"张三", "age":18, "img":'./images/1.jpg'},
              {"name":"李四", "age":19, "img":'./images/2.png'},
              {"name":"王五", "age":20, "img":'./images/3.jpg'},
              {"name":"赵六", "age":21, "img":'./images/4.jpg'},
              {"name":"田七", "age":22, "img":'./images/5.webp'},
              {"name":"王八", "age":23, "img":'./images/6.jpg'},
              {"name":"吴九", "age":24, "img":'./images/7.jpg'},
              
            ]
          }
        },
        methods: {
          f1(){
            
          },
          f2(){
            this.user.name = '';
            this.user.gender = '';
            this.user.p = '';
          }
        },
      }
    ).mount('#app');
  </script> */
}
