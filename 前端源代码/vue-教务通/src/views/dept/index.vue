<script setup>
// 引入vue的ref声明响应式数据和onMounted钩子函数
import { ref, onMounted } from "vue";
// 引入部门操作api
import {
  queryAllApi,
  addApi,
  queryByIdApi,
  updateApi,
  deleteApi,
} from "@/api/dept";
// 引入消息提示框ElMessage 引入消息弹出框ElMessageBox
import { ElMessage, ElMessageBox } from "element-plus";

// 数据模型
const deptList = ref([]);
const dept = ref({ name: "" });
// axios请求
const search = async () => {
  //  js独有这里赋值的是函数本身  如果是调用函数()这样返回的是函数的返回值
  const res = await queryAllApi();
  if (res.code) {
    // 1代表成功 0代表失败   js隐式转换是主要的
    deptList.value = res.data;
  }
};

// 钩子函数
onMounted(() => {
  search();
});

// Dialog对话框
const dialogFormVisible = ref(false);
const formTitle = ref("");

// 新增部门
const addDept = () => {
  dialogFormVisible.value = true;
  formTitle.value = "新增部门";
  // 重置表单的校验规则-提示信息
  if (deptFormRef.value) {
    deptFormRef.value.resetFields(); // 重置表单的校验规则-提示信息
  }
};

// 表单校验
const deptFormRef = ref(); // 表单引用 拿到表单对象

// 保存部门
const save = async () => {
  // 表单校验
  if (!deptFormRef.value) return;
  deptFormRef.value.validate(async (valid) => {
    // valid 是否校验通过
    if (valid) {
      let result; // 提升作用域 不然是局部变量
      if (dept.value.id) {
        // 有id 修改
        result = await updateApi(dept.value);
      } else {
        // 没有id 新增
        result = await addApi(dept.value);
      }

      if (result.code) {
        // 提示信息
        ElMessage.success("成功");
        // 清空表单
        dept.value = "";
        // 关闭对话框
        dialogFormVisible.value = false;
        // 重新查询
        search();
      } else {
        // 提示信息
        ElMessage.error(result.msg);
      }
    } else {
      // 不通过
      ElMessage.error("表单校验不通过");
    }
  });
};

// 表单验证
const rules = ref({
  // 表单验证规则
  name: [
    // 规则 required 必填 message错误提示信息 trigger 触发时机 blur 失去焦点开始校验
    { required: true, message: "部门名称不能为空", trigger: "blur" },
    //  min 最小长度 max 最大长度
    { min: 2, max: 5, message: "长度要在 2 to 10", trigger: "blur" },
  ],
});

// 编辑部门
const edit = async (id) => {
  formTitle.value = "编辑部门";
  if (deptFormRef.value) {
    deptFormRef.value.resetFields(); // 重置表单的校验规则-提示信息
  }

  const result = await queryByIdApi(id);
  if (result.code) {
    dialogFormVisible.value = true;
    dept.value = result.data; // ref创建的响应式对象是可变的。当你给ref对象的value赋值时，实际上是用新对象替换了原对象。当你通过queryByIdApi获取后端数据并赋值给dept.value时 这里的dept.value被新的result.data对象替换了，result.data是后端返回的完整对象，包含了所有属性，所以看起来就像自动扩充了属性。
  }
};

// 删除部门
const delById = async (id) => {
  ElMessageBox.confirm("您确定要删除该部门嘛", "删除部门", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning", // 显示样式
  })
    .then(async () => {
      // 确认
      const result = await deleteApi(id);

      if (result.code) {
        ElMessage.success("删除成功");
        // 重新查询
        search();
      } else {
        ElMessage.error(result.msg);
      }
    }) // 取消
    .catch(() => {
      ElMessage.info("您已取消删除");
    });
};
</script>

<template>
  <div class="metitle">部门管理</div>
  <div class="container">
    <el-button type="primary" @click="addDept">新增部门</el-button>
  </div>

  <!-- 表格区域 -->
  <div class="container">
    <el-table :data="deptList" border style="width: 100%">
      <el-table-column type="index" label="序号" width="100" align="center" />
      <el-table-column prop="name" label="学工部" width="260" align="center" />
      <el-table-column
        prop="updateTime"
        label="最后操作时间"
        width="300"
        align="center"
      />
      <el-table-column prop="" label="操作" align="center">
        <template #default="scoped">
          <!--scope 是一个对象，它包含了当前行的数据和其他相关信息  它代表默认插槽。在 <el-table-column> 中，#default 插槽会为表格的每一行渲染内容。-->
          <el-button type="primary" size="small" @click="edit(scoped.row.id)"
            ><el-icon><Pointer /></el-icon>编辑</el-button
          >
          <el-button type="danger" size="small" @click="delById(scoped.row.id)"
            ><el-icon><Delete /></el-icon>删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- Dialog对话框 -->
  <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="dept" :rules="rules" ref="deptFormRef">
      <!--表单区域  ref="deptFormRef"注册表单引用 就是引用这个表单对象 这个对象就是这个表单项 用于将 <el-form> 组件绑定到 Vue 组件里名为 deptFormRef 的引用上-->
      <el-form-item label="部门名称" label-width="80px" prop="name">
        <!--表单项-->
        <el-input v-model="dept.name" placeholder="请输入部门名称" />
        <!--输入框-->
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </div>
    </template>
  </el-dialog>
  <!-- 消息弹出框 -->
</template>

<style scoped>
.container {
  margin: 20px 0;
}
</style>
