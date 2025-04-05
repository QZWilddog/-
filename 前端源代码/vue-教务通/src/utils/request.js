import axios from "axios";
import { ElMessage } from "element-plus";
import router from "@/router/index"; // 获得路由实例对象  那个获得方法只能在vue组件中使用 这个是js文件

// 创建axios实例对象
const request = axios.create({
  baseURL: "/api", // 基础路径  发起请求时前缀并没有指定协议 ip 端口部分 则默认访问 只指定了资源访问路径 默认访问的是当前前端服务器
  timeout: 500000, // 请求超时时间 测试阶段长
});

// axios请求发送之前 requset请求拦截器 对请求数据进行处理  .use() 方法用于注册一个拦截器 它可以接受两个参数请求成功和请求失败
request.interceptors.request.use(
  (request) => {
    // 获取JWT令牌 localStorage.getItem('loginUser')从浏览器存储空间获取存储的loginUser对应的值是个字符串用json转成js对象
    const loginUser = JSON.parse(localStorage.getItem("loginUser"));
    if (loginUser && loginUser.token) {
      request.headers.token = loginUser.token;
    }
    return request;
  },
  (error) => {
    return Promise.reject(error); // 创建并返回一个新的 Promise 对象，该对象立即被拒绝，并携带指定的拒绝原因 reason  就是控制台里报红的那个信息
  }
);

// axios的响应成功后 response 响应拦截器  对响应结果进行处理 .use() 方法用于注册一个拦截器 它可以接受两个参数响应成功和响应失败
request.interceptors.response.use(
  (response) => {
    // 成功回调
    return response.data;
  },
  (error) => {
    // 失败回调

    if (error.response.status == 401) {
      // 提示信息
      ElMessage.error("登录超时，请求重新登录");
      //跳转到登录页面
      router.push("/login");
    } else {
      ElMessage.error("接口访问异常");
    }
    return Promise.reject(error); // 创建并返回一个新的 Promise 对象，该对象立即被拒绝，并携带指定的拒绝原因 reason  就是控制台里报红的那个信息
  }
);

export default request; // 导出axios实例对象 这是默认导出
