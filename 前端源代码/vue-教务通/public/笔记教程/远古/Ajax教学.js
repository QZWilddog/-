// CDN（Content Delivery Network）就是内容分发网络，可以把它想象成一个遍布各地的“快递驿站网络”。
// jsDelivr是一个开源的、免费的公共CDN（内容分发网络）服务，专门用于托管和加速前端开源项目的静态文件，比如JavaScript库、CSS框架、字体文件等。
// 5. Ajax
// 5.1 概述
// 我们前端页面中的数据，如下图所示的表格中的员工信息，应该来自于后台，那么我们的后台和前端是互不影响的2个程序，
// 那么我们前端应该如何从后台获取数据呢？因为是2个程序，所以必须涉及到2个程序的交互，所以这就需要用到我们接下来学习的Ajax技术。

// Ajax: 全称Asynchronous JavaScript And XML，异步的JavaScript和XML。其作用有如下2点：
// - 与服务器进行数据交换：通过Ajax可以给服务器发送请求，并获取服务器响应的数据。
// - 异步交互：可以在不重新加载整个页面的情况下，与服务器交换数据并更新部分网页的技术，如：搜索联想、用户名是否可用的校验等等。

// 我们详细的解释一下Ajax技术的2个作用：
// - 与服务器进行数据交互
// 如下图所示前端资源被浏览器解析，但是前端页面上缺少数据，前端可以通过Ajax技术，向后台服务器发起请求，后台服务器接受到前端的请求，
// 从数据库中获取前端需要的资源，然后响应给前端，前端在通过我们学习的vue技术，可以将数据展示到页面上，这样用户就能看到完整的页面了。
// 此处可以对比JavaSE中的网络编程技术来理解。

// - 异步交互：可以在不重新加载整个页面的情况下，与服务器交换数据并更新部分网页的技术。
// 如下图所示，当我们再百度搜索java时，下面的联想数据是通过Ajax请求从后台服务器得到的，在整个过程中，
// 我们的Ajax请求不会导致整个百度页面的重新加载，并且只针对搜索栏这局部模块的数据进行了数据的更新，
// 不会对整个页面的其他地方进行数据的更新，这样就大大提升了页面的加载速度，用户体验高。

// 同步请求：浏览器页面在发送请求给服务器，在服务器处理请求的过程中，浏览器页面不能做其他的操作。只能等到服务器响应结束后才能，浏览器页面才能继续做其他的操 作
// 异步请求： 浏览器页面发送请求给服务器，在服务器处理请求的过程中，浏览器页面还可以做其他的操作。

// 5.3 Axios
// 使用原生的Ajax请求的代码编写起来还是比较繁琐的，所以接下来我们学习一门更加简单的发送Ajax请求的技术Axios 。
// Axios是对原生的AJAX进行封装，简化书写。

// Axios的使用比较简单，主要分为2步：

// 1. 引入Axios文件（jsDelivr CDN）
// <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
// 2. 点击按钮时，使用Axios发送请求
//GET请求
//     document.querySelector('#getData').onclick = function() {
//       axios({
//         url:'https://mock.apifox.cn/m1/3083103-0-default/emps/list',
//         method:'get'
//       }).then(function(res) {
//         console.log(res.data);
//       }).catch(function(err) {
//         console.log(err);
//       })
//     }
//POST请求
//     document.querySelector('#postData').onclick = function() {
//       axios({
//         url:'https://mock.apifox.cn/m1/3083103-0-default/emps/update',
//         method:'post'
//       }).then(function(res) {
//         console.log(res.data);
//       }).catch(function(err) {
//         console.log(err);
//       })
//     }
// 知识小贴士：在使用axios时，在axios之后，输入 thenc 会自动生成成功及失败回调函数结构 。

// 请求方法别名
// Axios还针对不同的请求，提供了别名方式的api，具体格式如下：
// axios.请求方式(url [, data [, config]])
// 方法	                              描述
// axios.get(url [, config])	          发送get请求
// axios.delete(url [, config])	      发送delete请求
// axios.post(url [, data[, config]])	发送post请求
// axios.put(url [, data[, config]])	  发送put请求

// get请求代码改写成如下：
// axios.get("https://mock.apifox.cn/m1/3083103-0-default/emps/list").then(result => { 不会立即执行 只有请求成功了 才能回调函数 不便于维护和管理
//     console.log(result.data);
// })
// post请求改写成如下：
// axios.post("https://mock.apifox.cn/m1/3083103-0-default/emps/update","id=1").then(result => {
//     console.log(result.data);
// })

// axios中提供的.then(function(){....}).catch(function(){....})，这种回调函数的写法，会使得代码的可读性和维护性变差。
//  而为了解决这个问题，我们可以使用两个关键字，分别是：async、await。
// 可以通过async、await可以让异步变为同步操作。async就是来声明一个异步方法，await是用来等待异步任务执行。
// 代码修改前：
// search() {
//     //基于axios发送异步请求，请求https://web-server.itheima.net/emps/list，根据条件查询员工列表
//     axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`).then(res => {
//       this.empList = res.data.data
//     })
//   },

// 代码修改后：
//   async search() {
//     //基于axios发送异步请求，请求https://web-server.itheima.net/emps/list，根据条件查询员工列表
//     const result = await axios.get(`https://web-server.itheima.net/emps/list?name=${this.searchForm.name}&gender=${this.searchForm.gender}&job=${this.searchForm.job}`);
//     this.empList = result.data.data;
//   },
// 修改后，代码就变成同步操作了，一行一行的从前往后执行。 在前端项目开发中，经常使用这两个关键字配合，使得代码的可读性和可维护性变高。
