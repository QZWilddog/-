//createApp用于创建一个新的Vue应用实例  ref用于创建响应式数据
import { createApp } from "vue";
// 应用的根组件，定义具体要显示的内容
import App from "./App.vue";
// 引入路由
import router from "./router";
// 引入element-plus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
// 引入element-plus的国际化
import zhCn from "element-plus/es/locale/lang/zh-cn";
// 引入element-plus的图标
import * as ElementPlusIconsVue from "@element-plus/icons-vue";

import "@/assets/css/main.css";
import "@/assets/css/all.css";

const app = createApp(App);

app.use(router); // use方法安装对应的插件 使用这个路由
app.use(ElementPlus, { locale: zhCn }); //use方法安装对应的插件 使用这个组件库

// 注册element-plus的图标 从@element-plus/icons-vue中导入所有图标并进行全局注册
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component); // 是 Vue 3 中用于全局注册组件的方法。
}

app.mount("#app"); // 挂载到id为app的元素上
