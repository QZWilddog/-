<script setup>
import { ref, onMounted } from "vue";
// 引入消息提示框ElMessage 引入消息弹出框ElMessageBox
import { ElMessageBox, ElMessage } from "element-plus";
// 获取当前路由实例  它返回一个响应式的当前路由位置对象。
import { useRouter } from "vue-router";

let loginName = ref("");
// 获取当前路由实例   这个对象包含了关于当前路由的所有信息，如路径、参数、查询参数等。
const router = useRouter();
// 获取当前localStorage存储区域的令牌
const token = () => {
  const loginUser = JSON.parse(localStorage.getItem("loginUser"));
  if (loginUser && loginUser.name) {
    loginName.value = loginUser.name;
  }
};

const logout = () => {
  // 弹出确认框
  ElMessageBox.confirm("您确认要退出登录嘛", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      ElMessage.success("退出成功");
      // 删除localStorage存储区域的令牌
      localStorage.removeItem("loginUser");
      // 跳转页面
      router.push("/login");
    })
    .catch(() => {
      ElMessage.info("您已取消退出");
    });
};

onMounted(() => {
  token();
});
</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <span class="title">教务通Plus</span>
        <span class="right_tool">
          <a href="">
            <el-icon><EditPen /></el-icon> 修改密码 &nbsp;&nbsp;&nbsp; |
            &nbsp;&nbsp;&nbsp;
          </a>
          <a href="javascript:void(0)" @click="logout">
            <!--如果href没数据就是让页面刷新 javascript:void(0) 或者:;将超链接变成死链接 因为要实现按钮的动作 -->
            <el-icon><SwitchButton /></el-icon> 退出登录 【{{ loginName }}】
          </a>
        </span>
      </el-header>

      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="200px" class="aside">
          <!-- 左侧菜单栏 -->
          <el-menu router>
            <!--是否启用 vue-router 模式。 启用该模式会在激活导航时以 index 作为 path 进行路由跳转 -->
            <!-- 首页菜单 -->
            <el-menu-item index="/index">
              <!--index 属性有着重要的用途，它主要用于标识菜单项，是每个菜单项的唯一标识符-->
              <el-icon><House /></el-icon>首页
            </el-menu-item>

            <!-- 班级管理菜单 -->
            <el-sub-menu index="/manage">
              <template #title>
                <el-icon><HomeFilled /></el-icon>班级学员管理
              </template>

              <el-menu-item index="/class"
                ><el-icon><HelpFilled /></el-icon>班级管理</el-menu-item
              >
              <el-menu-item index="/stu"
                ><el-icon><UserFilled /></el-icon>学院管理</el-menu-item
              >
            </el-sub-menu>
            <!-- 系统信息管理菜单 -->
            <el-sub-menu index="/system">
              <template #title>
                <el-icon><Pointer /></el-icon>系统信息管理
              </template>

              <el-menu-item index="/dept">
                <el-icon><HelpFilled /></el-icon>部门管理
              </el-menu-item>
              <el-menu-item index="/emp">
                <el-icon><UserFilled /></el-icon>员工管理
              </el-menu-item>
            </el-sub-menu>
            <!-- 数据统计管理菜单 -->
            <el-sub-menu index="/report">
              <template #title>
                <el-icon><View /></el-icon>数据统计管理
              </template>

              <el-menu-item index="/empReport"
                ><el-icon><HelpFilled /></el-icon>员工信息统计</el-menu-item
              >
              <el-menu-item index="/stuReport"
                ><el-icon><UserFilled /></el-icon>学员信息统计</el-menu-item
              >
              <el-menu-item index="/log"
                ><el-icon><HelpFilled /></el-icon>日志信息统计</el-menu-item
              >
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <!-- main区域 -->
        <el-main>
          <router-view></router-view>
          <!--嵌套路由-->
          <!--使用router-view组件渲染路由视图 动态视图组件-->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.header {
  background-image: linear-gradient(
    to right,
    #00547d,
    #007fa4,
    #00aaa0,
    #00d072,
    #a8eb12
  );
}

.title {
  color: white;
  font-size: 40px;
  font-family: 楷体;
  line-height: 60px;
  font-weight: bolder;
}

.right_tool {
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}

.aside {
  width: 220px;
  border-right: 1px solid #ccc;
  height: 730px;
}
</style>
