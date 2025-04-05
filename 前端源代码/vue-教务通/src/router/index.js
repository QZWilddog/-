// 从 vue-router 库中导入 createRouter 和 createWebHistory 这两个函数。这两个函数在 Vue.js 项目里对构建路由系统至关重要。
import { createRouter, createWebHistory } from "vue-router";

import ClassView from "@/views/class/index.vue";
import DeptView from "@/views/dept/index.vue";
import EmpView from "@/views/emp/index.vue";
import StuView from "@/views/stu/index.vue";
import IndexView from "@/views/index/index.vue";
import EmpReportView from "@/views/report/emp/index.vue";
import StuReportView from "@/views/report/stu/index.vue";
import LogView from "@/views/log/index.vue";
import LoginView from "@/views/login/index.vue";
import LayoutView from "@/views/layout/index.vue";

// 调用 createRouter 函数创建一个新的路由实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // 配置路由表信息  name名字不能重复因为是根据name来匹配的 path 路由路径 component 组件
    {
      path: "/", // 父路由
      name: "", //
      component: LayoutView, // // 应该渲染的组件
      redirect: "/index", // 重定向
      children: [
        // 子路由 直接拼接在父路由后面
        { path: "index", name: "index", component: IndexView },
        { path: "class", name: "class", component: ClassView },
        { path: "dept", name: "dept", component: DeptView },
        { path: "emp", name: "emp", component: EmpView },
        { path: "stu", name: "stu", component: StuView },
        { path: "empReport", name: "empReport", component: EmpReportView },
        { path: "stuReport", name: "stuReport", component: StuReportView },
        { path: "log", name: "log", component: LogView },
      ],
    },

    { path: "/login", name: "login", component: LoginView },

    // {
    //   path: '/',    路由路径
    //   name: 'home',  路由名字
    //   component: HomeView  对应的组件    官方样例 从顶部导入
    // },
    // {
    //   path: '/about',
    //   name: 'about',
    //   component: () => import('../views/AboutView.vue')  创建函数导入
    // }
  ],
});

export default router;
