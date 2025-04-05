<script setup>
// 导入ref响应数据 watch侦听对象
import { ref, watch, onMounted } from "vue";
import {
  queryPageApi,
  saveEmp,
  queryInfoApi,
  updateApi,
  deleteApi,
} from "@/api/emp";
import { queryAllApi as queryAllDeptApi } from "@/api/dept";

// 引入消息提示框ElMessage 引入消息弹出框ElMessageBox
import { ElMessage, ElMessageBox } from "element-plus";

// 搜索表单数据模型 表格数据模型
const searchEmp = ref({ name: "", gender: "", date: [], begin: "", end: "" });
// 员工列表
const empList = ref([]);
// 部门数据
const depts = ref([]);
// 员工id列表
const selectedIds = ref([]);

// 分页条
const currentPage = ref(1); // 默认查询第几页页码
const pageSize = ref(10); // 每页展示记录数
const total = ref(0); // 总记录数

// dialog对话框数据

// 控制弹窗
const dialogVisible = ref(false); // 显示弹出
const dialogTitle = ref("新增员工"); //  弹窗标题

//新增/修改表单
const employeeFormRef = ref();
// 整个表单数据绑定模型
const employee = ref({
  username: "",
  name: "",
  gender: "",
  phone: "",
  job: "",
  salary: "",
  deptId: "",
  entryDate: "",
  image: "",
  exprList: [],
});

// 表单引用对象
const empFormRef = ref();

// 职位列表数据
const jobs = ref([
  { name: "班主任", value: 1 },
  { name: "讲师", value: 2 },
  { name: "学工主管", value: 3 },
  { name: "教研主管", value: 4 },
  { name: "咨询师", value: 5 },
  { name: "其他", value: 6 },
]);
// 性别列表数据
const genders = ref([
  { name: "男", value: 1 },
  { name: "女", value: 2 },
]);

// JWT令牌
let token = ref("");

//函数

//新增员工
const addEmp = () => {
  dialogVisible.value = true;
  dialogTitle.value = "新增员工";
  employee.value = {
    username: "",
    name: "",
    gender: "",
    phone: "",
    job: "",
    salary: "",
    deptId: "",
    entryDate: "",
    image: "",
    exprList: [],
  };
  // 重置表单的校验规则-提示信息
  if (empFormRef.value) {
    empFormRef.value.resetFields();
  }
};

// 获取JWT令牌
const getToken = () => {
  const loginUser = JSON.parse(localStorage.getItem("loginUser"));

  if (loginUser && loginUser.token) {
    token.value = loginUser.token;
  }
};

// 侦听函数
watch(
  () => searchEmp.value.date,
  (newVal, oldVal) => {
    if (newVal.length == 2) {
      searchEmp.value.begin = newVal[0];
      searchEmp.value.end = newVal[1];
    } else {
      {
        searchEmp.value.begin = "";
        searchEmp.value.end = "";
      }
    }
  },
  { deep: true }
);

// 函数
//查询员工列表
const search = async () => {
  const result = await queryPageApi(
    searchEmp.value.name,
    searchEmp.value.gender,
    searchEmp.value.begin,
    searchEmp.value.end,
    currentPage.value,
    pageSize.value
  );
  if (result.code) {
    empList.value = result.data.rows;
    total.value = result.data.total;
  } else {
    ElMessage.error(result.msg);
  }
};
// 查询所有部门
const queryAllDepts = async () => {
  const result = await queryAllDeptApi();
  if (result.code) {
    depts.value = result.data;
  } else {
    ElMessage.error(result.msg);
  }
};

//清空
const clear = () => {
  searchEmp.value = { name: "", gender: "", date: [], begin: "", end: "" };
  search();
};
// 分页条函数
// 每页展示的记录数变化时触发
const handleSizeChange = (val) => {
  search();
};
//页码记录数发生变化时触发
const handleCurrentChange = (val) => {
  search();
};
// 图片上传成功后触发
const handleAvatarSuccess = (response) => {
  employee.value.image = response.data;
};
// 文件上传之前触发
const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== "image/jpeg" && rawFile.type !== "image/png") {
    ElMessage.error("只支持上传图片");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 10) {
    ElMessage.error("只能上传10M以内图片");
    return false;
  }
  return true;
};

//添加工作经历
const addExprItem = () => {
  employee.value.exprList.push({
    company: "",
    job: "",
    begin: "",
    end: "",
    exprDate: [],
  });
};
//删除工作经历
const delExprItem = (index) => {
  employee.value.exprList.splice(index, 1);
};

// 工作经历侦听函数
watch(
  () => employee.value.exprList,
  (newVal, oldVal) => {
    if (employee.value.exprList && employee.value.exprList.length > 0) {
      employee.value.exprList.forEach((expr) => {
        expr.begin = expr.exprDate[0];
        expr.end = expr.exprDate[1];
      });
    }
  },
  { deep: true }
); // 不加深度侦听 只有当数组的引用发生变化才触发侦听

// 保存/修改员工
const save = async () => {
  empFormRef.value.validate(async (vaild) => {
    // Element Plus 中 <el-form> 组件提供的一个方法，用于对表单进行验证。这个方法非常有用，因为它允许你在提交表单之前确保所有必填字段都已填写并且满足指定的验证规则。
    if (vaild) {
      let result;
      if (employee.value.id) {
        result = await updateApi(employee.value);
      } else {
        result = await saveEmp(employee.value);
      }

      if (result.code) {
        ElMessage.success("成功");
        dialogVisible.value = false;
        search();
      } else {
        ElMessage.error(result.msg);
      }
    } else {
      ElMessage.error("表单校验不通过");
    }
  });
};

//表单校验规则   Form属性名rules	表单验证规则  验证规则
const rules = ref({
  username: [
    // 规则 required 必填 message错误提示信息 trigger 触发时机 blur 失去焦点开始校验
    { required: true, message: "请输入用户名", trigger: "blur" },
    //  min 最小长度 max 最大长度
    {
      min: 2,
      max: 20,
      message: "用户名长度应在2到20个字符之间",
      trigger: "blur",
    },
  ],
  name: [
    { required: true, message: "请输入姓名", trigger: "blur" },
    {
      min: 2,
      max: 10,
      message: "姓名长度应在2到10个字符之间",
      trigger: "blur",
    },
  ],
  gender: [{ required: true, message: "请选择性别", trigger: "change" }],
  phone: [
    { required: true, message: "请输入手机号", trigger: "blur" },
    // pattern 正则表达式 在js中正则要用//围起来 ^以什么开始 $以什么结束
    /***
     * 以1开始 第二个3-9之间 \d{9}$ 表示接下来的字符必须是由恰好 9 个数字组成
     */
    {
      pattern: /^1[3-9]\d{9}$/,
      message: "请输入有效的手机号",
      trigger: "blur",
    },
  ],
});

// 编辑 修改员工
const edit = async (id) => {
  const result = await queryInfoApi(id);

  if (result.code) {
    employee.value = result.data;
    dialogVisible.value = true;
    dialogTitle.value = "修改员工";

    // 对工作经历进行处理
    let exprList = employee.value.exprList;
    if (exprList && exprList.length > 0) {
      exprList.forEach((expr) => {
        expr.exprDate = [expr.begin, expr.end];
      });
    }
  }
};
// 删除员工
const delByApi = async (id) => {
  // 弹出确认框   第一个参数：消息内容 第二个参数：标题 第三个参数：配置对象 confirmButtonText: 确认按钮上显示的文字 cancelButtonText: 取消按钮上显示的文字  type: 设置对话框的类型，会影响其图标和颜色
  ElMessageBox.confirm("您确认删除改员工信息嘛？", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(async () => {
      const result = await deleteApi(id);
      if (result.code) {
        ElMessage.success("成功删除");
        search();
      }
    })
    .catch(() => {
      // 失败
      ElMessage.info("您已取消删除");
    });
};
//批量删除
const deleeteByIds = async () => {
  // 弹出确认框   第一个参数：消息内容 第二个参数：标题 第三个参数：配置对象 confirmButtonText: 确认按钮上显示的文字 cancelButtonText: 取消按钮上显示的文字  type: 设置对话框的类型，会影响其图标和颜色
  if (selectedIds.value && selectedIds.value.length > 0) {
    ElMessageBox.confirm("您确认删除改员工信息嘛？", "提示", {
      confirmButtonText: "确认",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(async () => {
        const result = await deleteApi(selectedIds.value);
        if (result.code) {
          ElMessage.success("成功删除");
          search();
        }
      })
      .catch(() => {
        // 失败
        ElMessage.info("您已取消删除");
      });
  } else {
    ElMessage.info("您没有选择任何数据");
  }
};

// 选择项发生变化是触发该事件
const handleSelectionChange = (selection) => {
  // vla（数组）： 当前选中的记录
  selectedIds.value = selection.map((item) => item.id); // map遍历数组，然后对每一个元素进行处理 结果封装到一个新数组中 返回一个新数组
};

// 钩子函数
onMounted(() => {
  search(); // 查询员工列表数据
  queryAllDepts(); // 查询所有部门列表数据
  getToken(); // 获取token
});
</script>

<template>
  <div class="metitle">员工管理</div>
  <!-- 搜索菜单栏 -->
  <div class="container">
    <el-form :inline="true" :model="searchEmp" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input
          v-model="searchEmp.name"
          placeholder="请输入员工姓名"
          clearable
        />
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="searchEmp.gender" placeholder="请选择">
          <el-option label="男" value="1" />
          <el-option label="女" value="2" />
        </el-select>
      </el-form-item>
      <el-form-item label="入职日期">
        <el-date-picker
          v-model="searchEmp.date"
          type="daterange"
          range-separator="到"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="YYYY-MM-DD"
        />
        <!--type="daterange" 日期范围 range-separator="To"开始和结束用什么字连接 value-format绑定的格式 默认date -->
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 功能按钮 -->
  <div class="container">
    <el-button type="primary" @click="addEmp">新增员工</el-button>
    <el-button type="danger" @click="deleeteByIds">批量删除</el-button>
  </div>
  <!-- 表格 -->
  <div class="container">
    <!--@selection-change：选择项发生变化是触发该事件-->
    <el-table
      :data="empList"
      border
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!--复选框 绑定事件为批量删除提供数据-->

      <el-table-column prop="name" label="姓名" width="120" align="center" />
      <el-table-column label="性别" width="120" align="center">
        <!--自定义模板-->
        <template #default="scope">
          <!--定义插槽scope 获取父组件传递下来的所有属性-->
          {{ scope.row.gender == 1 ? "男" : "女" }}
          <!--scope.row代表当前行的数据对象-->
        </template>
      </el-table-column>
      <el-table-column label="头像" width="120" align="center">
        <template #default="scope">
          <img :src="scope.row.image" height="40px" />
        </template>
      </el-table-column>
      <el-table-column
        prop="deptName"
        label="所属部门"
        width="120"
        align="center"
      />
      <el-table-column label="职位" width="120" align="center">
        <template #default="scope">
          <span v-if="scope.row.job == 1">班主任</span>
          <span v-else-if="scope.row.job == 2">讲师</span>
          <span v-else-if="scope.row.job == 3">学工主管</span>
          <span v-else-if="scope.row.job == 4">教研主管</span>
          <span v-else>咨询师</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="entryDate"
        label="入职日期"
        width="180"
        align="center"
      />
      <el-table-column
        prop="updateTime"
        label="最后操作时间"
        width="200"
        align="center"
      />
      <el-table-column prop="" label="操作" width="180" align="center">
        <template #default="scoped">
          <!--scope 是一个对象，它包含了当前行的数据和其他相关信息  它代表默认插槽。在 <el-table-column> 中，#default 插槽会为表格的每一行渲染内容。-->
          <el-button type="primary" size="small" @click="edit(scoped.row.id)"
            ><el-icon><Pointer /></el-icon>编辑</el-button
          >
          <el-button type="danger" size="small" @click="delByApi(scoped.row.id)"
            ><el-icon><Delete /></el-icon>删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 分页条 -->
  <div class="container">
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <!--@size-change每页展示的记录数变化时触发 @current-change页码记录数发生变化时触发-->
  </div>

  <!-- 新增员工/修改员工的对话框  ref 属性用于给元素或组件注册引用信息。这个引用可以让你直接访问该元素或组件实例，从而能够在脚本中直接操作它们。-->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <!--rules 绑定表单验证规则 ref引用表单对象-->
    <el-form
      ref="empFormRef"
      :model="employee"
      label-width="80px"
      :rules="rules"
    >
      <!-- 基本信息 -->
      <!-- 第一行 :gutter="20"行内列间距 :span="12"这个区域占多少宽总共24  prop :model 的键名-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="用户名" prop="username">
            <el-input
              v-model="employee.username"
              placeholder="请输入员工用户名，2-20个字"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input
              v-model="employee.name"
              placeholder="请输入员工姓名，2-10个字"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select
              v-model="employee.gender"
              placeholder="请选择性别"
              style="width: 100%"
            >
              <el-option
                v-for="g in genders"
                :key="g.value"
                :label="g.name"
                :value="g.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input
              v-model="employee.phone"
              placeholder="请输入员工手机号"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="职位">
            <el-select
              v-model="employee.job"
              placeholder="请选择职位"
              style="width: 100%"
            >
              <el-option
                v-for="job in jobs"
                :key="job.value"
                :label="job.name"
                :value="job.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="薪资">
            <el-input
              v-model="employee.salary"
              placeholder="请输入员工薪资"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第四行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="所属部门">
            <el-select
              v-model="employee.deptId"
              placeholder="请选择部门"
              style="width: 100%"
            >
              <el-option
                v-for="dept in depts"
                :key="dept.id"
                :label="dept.name"
                :value="dept.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="入职日期">
            <el-date-picker
              v-model="employee.entryDate"
              type="date"
              style="width: 100%"
              placeholder="选择日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第五行 -->
      <el-row :gutter="20">
        <el-col :span="24">
          <el-form-item label="头像">
            <!--el-upload 文件上传组件 action="/api/upload"服务器请求地址 :show-file-list是否展示文件上传列表  :on-success文件上传之后调用的函数  :before-upload文件上传之前调用的函数-->
            <!-- v-bind为属性动态绑定值 v-model为表单表格等展示内容的标签进行双向数据绑定  查询El文档 headers为设置上传请求头 里面绑定一个对象Object -->
            <el-upload
              class="avatar-uploader"
              action="/api/upload"
              :headers="{ token: token }"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="employee.image" :src="employee.image" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
            </el-upload>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 工作经历 -->
      <!-- 第六行 -->
      <el-row :gutter="10">
        <el-col :span="24">
          <el-form-item label="工作经历">
            <el-button type="success" size="small" @click="addExprItem"
              >+ 添加工作经历</el-button
            >
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第七行 ...  工作经历 -->
      <el-row
        :gutter="3"
        v-for="(expr, index) in employee.exprList"
        :key="index"
      >
        <el-col :span="10">
          <el-form-item size="small" label="时间" label-width="80px">
            <el-date-picker
              v-model="expr.exprDate"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item size="small" label="公司" label-width="60px">
            <el-input
              v-model="expr.company"
              placeholder="请输入公司名称"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item size="small" label="职位" label-width="60px">
            <el-input v-model="expr.job" placeholder="请输入职位"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="2">
          <el-form-item size="small" label-width="1px">
            <el-button type="danger" @click="delExprItem(index)"
              >- 删除</el-button
            >
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 10px 0;
}

.avatar {
  height: 40px;
}
.avatar-uploader .avatar {
  width: 78px;
  height: 78px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 78px;
  height: 78px;
  text-align: center;
  /* 添加灰色的虚线边框 */
  border: 1px dashed var(--el-border-color);
}
</style>
