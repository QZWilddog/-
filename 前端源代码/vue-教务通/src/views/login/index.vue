<script setup>
import { ref } from "vue";
import { loginApi } from "@/api/login";
// 获取当前路由实例  它返回一个响应式的当前路由位置对象。
import { useRouter } from "vue-router";
// 引入消息提示框ElMessage 只能在vue组件文件中使用
import { ElMessage } from "element-plus";

// 数据模型
let loginForm = ref({ username: "", passwod: "" });
const userData = ref({});
// 获取当前路由实例   这个对象包含了关于当前路由的所有信息，如路径、参数、查询参数等。
const router = useRouter();

// 用户登陆
const loginUser = async () => {
  const result = await loginApi(loginForm.value);

  if (result.code) {
    // 存储当前员工信息
    userData.value = result.data;
    // 存储当前员工信息里面有（JWT令牌） localStorage它提供了一个存储接口，允许你在用户的浏览器中以键值对的形式持久化保存数据 里面只能存string类型
    // setItem:添加或更新存储中的数据项。如果指定的键已经存在，则更新其对应的值。   JavaScript 提供了一个内置的 JSON 对象，该对象包含了两个主要方法：JSON.parse() 和 JSON.stringify()。这两个方法分别用于将 JSON 字符串转换为 JavaScript 对象和将 JavaScript 对象转换为 JSON 字符串。
    localStorage.setItem("loginUser", JSON.stringify(result.data));

    ElMessage.success(`登陆成功 欢迎您${userData.value.name}`);
    // push跳转页面操作要基于路由
    router.push("/index");
  } else {
    ElMessage.error(result.msg);
  }
};

// 重置表单
const clear = () => {
  loginForm.value = { username: "", passwod: "" };
};
</script>

<template>
  <div id="container">
    <div class="login-form">
      <el-form label-width="80px">
        <p class="title">教务通Plus</p>
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input
            type="password"
            v-model="loginForm.password"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button class="button" type="primary" @click="loginUser"
            >登 录</el-button
          >
          <el-button class="button" type="info" @click="clear">重 置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
#container {
  padding: 10%;
  height: 410px;
  background-image: url("../../assets/bg1.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

.login-form {
  max-width: 400px;
  padding: 30px;
  margin: 0 auto;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  background-color: white;
}

.title {
  font-size: 30px;
  font-family: "楷体";
  text-align: center;
  margin-bottom: 30px;
  font-weight: bold;
}

.button {
  margin-top: 30px;
  width: 120px;
}
</style>
