// 那什么是JavaScript呢 ?
// JavaScript（简称：JS） 是一门跨平台、面向对象的脚本语言，是用来控制网页行为的，实现人机交互效果。JavaScript 和 Java 是完全不同的语言，不论是概念还是设计。但是基础语法类似。
// - 组成：
//   - ECMAScript: 规定了JS基础语法核心知识，包括变量、数据类型、流程控制、函数、对象等。
//   - BOM：浏览器对象模型，用于操作浏览器本身，如：页面弹窗、地址栏操作、关闭窗口等。
//   - DOM：文档对象模型，用于操作HTML文档，如：改变标签内的内容、改变标签内字体样式等。

// alert 方法的本质：alert 是 BOM（浏览
// 器对象模型）提供的一个方法，它属于 window 对象（在全局作用域下可以省略 window 直接调用）

// 弹出一个警告框，显示消息 "Задание 2"
// window.alert("Задание 2");

// let a = 10;  // 具有块级作用域，即变量只在声明它的代码块内有效。
// var a1 = 20; // 早期用，不严谨 作用域为函数作用域，如果不在函数内声明，则为全局作用域。
// const B = 20;
// 输入到控制台
// console.log(a);console.log(B);
// 输入到body区域 不常用
// document.write("Задание 2");

// 基本数据类型
// Number：表示数字，包含整数和浮点数。const sum = (a, b) => a + b；NaN 即 “Not a Number” 的缩写，它代表一个非数字的值。当数学运算无法得到一个有效的数字结果时，就会返回 NaN。
// String：表示文本，用单引号、双引号或者反引号包裹。例如：let str = 'hello';。
// Boolean：只有两个值，true 和 false，用于逻辑判断。例如：let isDone = false;。
// Null：只有一个值 null，表示一个空对象指针。例如：let emptyObj = null;。
// Undefined：变量已声明但未赋值，或者函数没有返回值时，值为 undefined。例如：let unassigned;。
// Symbol：ES6 引入，代表独一无二的值。例如：let sym = Symbol('key');。
// BigInt：ES2020 引入，用于表示任意大的整数。例如：let bigNum = 123456789012345678901234567890n;。
// ----------------------------------------------------------------------------------------------------
// 引用数据类型
// Object：是最基础的引用类型，可用来存储键值对。例如：let person = { name: 'John', age: 30 };。
// Array：用于存储有序的数据集合。例如：let arr = [1, 2, 3];。
// Function：可执行的代码块。例如：function add(a, b) { return a + b; }。
// 类型判断
// typeof：能判断基本数据类型，但对 null 会返回 object，对函数返回 function，其他引用类型都返回 object。
// instanceof：用于判断对象是否是某个构造函数的实例。
// Object.prototype.toString.call：能精准判断所有数据类型。
// 模板字符串拼接 使用 反引号+${}
// console.log(`adfa${'asdf'}fasf`);

// 具名函数 函数是可重复使用的代码块，用于执行特定任务。
// let a = 1,b = 2;
// function sum(a,b){
//   console.log(a+b);
// }
// 箭头函数 -匿名函数
// let sum = function(a,b){
//   console.log(a+b);
// }
// (a, b) => a + b; 简洁体式 let sum = (a, b) => a + b{};块体
// console.log(sum(a,b));
// sum(a,b);

// 自定义对象
// let 对象名 = {
//   属性名1: 属性值1,
//   属性名2: 属性值2,
//   属性名3: 属性值3,
//   方法名称: function(形参列表){}
//   方法名称() => {}
// };
// let user = {
//   name: "Tom",
//   age: 10,
//   gender: "男",
// 方法名称: function(形参列表){}
// 方法名称() => {}
//   sing(){    --对象里叫方法 不是用箭头函数
//        console.log(`${this.name}唱歌`);   -- 不使用箭头函数 this指向user对象 箭头函数中this指向父级
//    }
// }
// console.log(user.name);
// user.sing();

// json 文本格式{"key":任意基本类型,"key":数组,"key":value}JSON 中的键必须用双引号包裹，单引号是不允许的。JSON 中不允许使用注释。
// let person = {
//   name: 'itcast',
//   age: 18,
//   gender: '男'
// }
// console.log(JSON.stringify(person)); js --> json  作用就是将js对象，转换为json格式的字符串
// console.log(JSON.parse('{"name":"itcast","age":18,"gender":"男"}').name); json --> js  用就是将json格式的字符串，转为js对象。

// 1.2.5 流程控制
// 在JS中，当然也存在对应的流程控制语句。常见的流程控制语句如下：
// - if ... else if ... else ...
// - switch
// - for
// - while
// - do ... while

// 1.2.6 JS DOM
// DOM：Document Object Model 文档对象模型。也就是 JavaScript 将 HTML 文档的各个组成部分封装为对象。
// DOM 其实我们并不陌生，之前在学习 XML 就接触过，只不过 XML 文档中的标签需要我们写代码解析，而 HTML 文档是浏览器解析。封装的对象分为
// - Document：整个文档对象
// - Element：元素对象
// - Attribute：属性对象
// - Text：文本对象
// - Comment：注释对象

// - 我们可以通过如下两种方式来获取DOM元素。
//   1. 根据CSS选择器来获取DOM元素，获取到匹配到的第一个元素：document.querySelector('CSS选择器');  现在推荐
//   2. 根据CSS选择器来获取DOM元素，获取匹配到的所有元素：document.querySelectorAll('CSS选择器');
//          注意：获取到的所有元素，会封装到一个NodeList节点集合中，是一个伪数组（有长度、有索引的数组，但没有push、pop等数组方法）

// DOM 文档对象模型 DOM树
// 1.获取DOM对象，2.调用DOM对象中属性或方法
// 1. 通过 ID 获取元素 2. 通过标签名获取元素 3. 通过类名获取元素 4. 通过 CSS 选择器获取元素 5. 通过名称获取元素name 6. 通过属性获取元素

// 获取
const element = document.getElementById("myElement");
// 获取属性
const attrValue = element.getAttribute("attributeName");
console.log(attrValue);
// 设置属性
element.setAttribute("attributeName", "newValue");
// 获取文本内容
const textContent = element.textContent;
console.log(textContent);
// 设置文本内容
element.textContent = "New text content";
// 获取HTML内容
const htmlContent = element.innerHTML;
console.log(htmlContent);
// 设置HTML内容
element.innerHTML = "<p>New HTML content</p>";
// 获取样式属性
const color = element.style.color;
// 设置样式属性
element.style.color = "red";

// let h1 = document.querySelector('#h1');
// 暂停3秒
// setTimeout(() => {h1.innerHTML = "DOM";h1.style.color = "red";}, 3000); 默认为id
// h1.innerHTML = "DOM";

// 事件监听   当一个 DOM 元素未显式设置 onclick 属性时，其默认值为 null。
// 1. 事件源 2. 事件类型 3. 事件处理程序
// 事件源.addEventListener(事件类型, 事件处理函数程序); addEventListener添加事件监听
// document.querySelector('#b1').addEventListener('click', () => {console.log("点击了事件");}); 可以多次绑定同一事件
// 早期写法 document.querySelector('#b1').onclick = () => {console.log("点击了事件");}  如果多次绑定同一事件，后面的会覆盖前面的
// JavaScript对于事件的绑定还提供了另外2种方式（早期版本）： 1). 通过html标签中的事件属性进行绑定 onclick="on()"  2). 通过DOM中Element元素的事件属性进行绑定document.querySelector('#btn1').onclick
// addEventListener 与 on事件 区别:
// - on方式会被覆盖，addEventListener 方式可以绑定多次，拥有更多特性，推荐使用 addEventListener .

// 常见事件：
// - click：鼠标点击事件 -mouseenter：鼠标移入事件 -mouseleave：鼠标移出事件 -mousedown：鼠标按下事件 -mouseup：鼠标抬起事件 -mousemove：鼠标移动事件 -keydown：键盘按下事件 -keyup：键盘抬起事件 -keypress：键盘按下抬起事件 -submit：表单提交事件 -change：表单元素值改变事件 -focus：表单元素获得焦点事件 -blur：表单元素失去焦点
// - keydown 键盘按下事件 - keyup 键盘抬起事件 - keypress 键盘按下抬起事件
// - focus 表单元素获得焦点事件 - blur 表单元素失去焦点事件  焦点事件
// - input 用户输入时触发 submit 表单提交事件  表单事件

// 导入模块 模块化js
// import {sum, sub} from "./01.js"

// 直接导出函数、变量 export
// 在文件末尾统一导出 export { sum, PI }; 每个模块只能有一个默认导出，使用 export default 语法。

// import { pr } from "./01.js";
// pr();
// 假设 ./module.js 模块中有多个导出项
// import * as module from './module.js';
{
  /* <script src=></script> 这种方式是在 HTML 文件中引入外部 JavaScript 库的常用方法 */
}

//此处可使用 window.carName
// 使用 import 语句时，路径分为相对路径和包名两种情况
