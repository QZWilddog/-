<script setup>
// // 导入ref响应数据 组件挂载完成后触发的钩子函数 watch侦听对象
import { ref, onMounted } from "vue";
import {
  pageApi,
  deleteByIdApi,
  addApi,
  queryInfoApi,
  updateApi,
  handleViolationApi,
} from "@/api/stu";
import { queryAllApi } from "@/api/class";
// 引入消息提示框ElMessage 引入消息弹出框ElMessageBox
import { ElMessage, ElMessageBox } from "element-plus";

//学历列表
const degreeList = ref([
  { name: "初中", value: 1 },
  { name: "高中", value: 2 },
  { name: "大专", value: 3 },
  { name: "本科", value: 4 },
  { name: "硕士", value: 5 },
  { name: "博士", value: 6 },
]);
//性别
const genders = ref([
  { name: "男", value: 1 },
  { name: "女", value: 2 },
]);
// 是否在院校内的学员
const whethers = ref([
  { name: "是", value: 1 },
  { name: "否", value: 0 },
]);
//搜索菜单栏
// 搜索栏searchStu数据模型
const searchStu = ref({
  name: "",
  degree: "",
  clazzId: "",
});
// 班级全部列表
const classList = ref([]);
//查询班级列表
const searchClassList = async () => {
  const result = await queryAllApi();
  if (result.code) {
    classList.value = result.data;
  } else {
    ElMessage.error("错误");
  }
};

// 分页查询按钮
const page = async () => {
  const result = await pageApi(
    searchStu.value.name,
    searchStu.value.degree,
    searchStu.value.clazzId,
    currentPage.value,
    pageSize.value
  );
  if (result.code) {
    tableData.value = result.data.rows;
    total.value = result.data.total;
    ElMessage.success("成功");
  } else {
    ElMessage.error("失败");
  }
};

// 清除按钮
const clear = () => {
  searchStu.value = {
    name: "",
    degree: "",
    clazzId: "",
  };
  page();
};

//表格
//表格列数据模型
const tableData = ref([]);
// 表格Id列表
const ids = ref([]);
//事件监听器用于监听表格的选择变化情况    用户选择或取消选择某行时，这个事件就会被触发。
const handleSelectionChange = (students) => {
  ids.value = students.map((student) => student.id); // map 用于对数组中的每个元素执行一个回调函数，并返回一个新的数组。
};
//编辑
const updateStu = async (id) => {
  dialogFormVisible.value = true;
  title.value = "更改学员信息";
  const result = await queryInfoApi(id);
  if (result.code) {
    stu.value = result.data;
    ElMessage.success("成功");
  } else {
    ElMessage.error(result.msg);
  }
};

//根据ID删除记录
const delById = (id) => {
  ElMessageBox.confirm("您确认删除此数据吗?", "删除学生", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(async () => {
      const result = await deleteByIdApi(id);
      if (result.code) {
        page();
      } else {
        ElMessage.error(result.msg);
      }
    })
    .catch(() => {
      ElMessage.error("取消删除");
    });
};

//批量删除学生
const delByIds = () => {
  if (ids.value.length == 0) {
    ElMessage.error("您没有选择任何学员");
    return;
  }
  ElMessageBox.confirm("您确认删除这一批数据吗?", "删除学生", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(async () => {
      const result = await deleteByIdApi(ids.value.join(",")); // 于将数组的所有元素连接成一个字符串，并用指定的分隔符分隔。
      if (result.code) {
        page();
      } else {
        ElMessage.error(result.msg);
      }
    })
    .catch(() => {
      ElMessage.error("取消删除");
    });
};

// 增加学生按钮
const addStu = () => {
  dialogFormVisible.value = true;
  title.value = "新增学生";
};
//学生违纪按钮
const openViolation = (id) => {
  ElMessageBox.prompt("请输入违纪扣分", "提示", {
    // prompt提示框
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    inputPattern: /[0-9]{1}/, //	输入框的校验表达式
    inputErrorMessage: "数据无效",
    type: "warning",
  })
    .then(async (val) => {
      const result = await handleViolationApi(id, val.value);
      if (result.code) {
        ElMessage.success("处理成功");
        page();
      } else {
        ElMessage.error(result.msg);
      }
    })
    .catch(() => {
      ElMessage.info("取消操作");
    });
};

//分页条
const currentPage = ref(1); // 默认展示第几页
const pageSize = ref(10); // 默认每页展示几条
const total = ref(0); // 一共多少条数据

//每页展示的记录数变化时触发
const handleSizeChange = (vla) => {
  pageSize.value = vla;
  page();
};
//页码记录数发生变化时触发
const handleCurrentChange = (vla) => {
  currentPage.value = vla;
  page();
};

// dialog对话框
const dialogFormVisible = ref(false); //显示对话框
const formLabelWidth = ref(100); //form表单label的宽度
const title = ref("");
//学院信息
const stu = ref({
  name: "",
  no: "", //学号
  gender: "",
  phone: "",
  degree: "", // 学历
  clazzId: "", // 班级ID
  idCard: "", //身份证号
  isCollege: "", // 是否是院校学生
  address: "", // 联系地址
  graduationDate: "", //毕业时间
});
//清空对话框数据
const clearStu = () => {
  stu.value = {
    name: "",
    no: "", //学号
    gender: "",
    phone: "",
    degree: "", // 学历
    clazzId: "", // 班级ID
    idCard: "", //身份证号
    isCollege: "", // 是否是院校学生
    address: "", // 联系地址
    graduationDate: "", //毕业时间
  };
};

// 表单校验   Form属性名rules	表单验证规则  验证规则
const rules = ref({
  name: [
    // 规则 required 必填 message错误提示信息 trigger 触发时机 blur 失去焦点开始校验
    { required: true, message: "姓名名称不能为空", trigger: "blur" },
    { min: 2, max: 30, message: "姓名名称长度为2-20字", trigger: "blur" }, //  min 最小长度 max 最大长度
  ],
  no: [
    { required: true, message: "请输入学号", trigger: "blur" },
    {
      pattern: /^[0-9]{10}$/,
      message: "学号长度应在10个字符",
      trigger: "blur",
    }, // ^：表示字符串的开始 [0-9]：表示数字字符。{11}：表示长度为 11。$：表示字符串的结束。
  ],
  gender: [{ required: true, message: "请选择性别", trigger: "blur" }],
  phone: [
    { required: true, message: "请输入电话", trigger: "blur" },
    {
      pattern: /^[0-9]{11}$/,
      message: "电话号码应为11位数字",
      trigger: "blur",
    }, // 用正则表达式校验手机号
  ],
  idCard: [
    { required: true, message: "请输入身份证号", trigger: "blur" },
    {
      pattern: /^[0-9]{18}$/,
      message: "身份证号应为18位数字",
      trigger: "blur",
    },
  ],
  isCollege: [{ required: true, message: "请选择", trigger: "blur" }],
  clazzId: [{ required: true, message: "请选择", trigger: "blur" }],
});
//引用表单 用于给元素或组件注册引用信息 并清除验证数据
const stuFormRef = ref();
// 重置表单校验resetFields   校验表单结果validate  重置表单到初始状态，清除用户输入的内容以及错误提示。
const resetForm = (stuForm) => {
  if (!stuForm) return;
  stuForm.resetFields();
};
//保存按钮
const save = async (stuForm) => {
  //表单校验
  if (!stuForm) return;
  stuForm.validate(async (valid) => {
    if (valid) {
      let result;
      if (stu.value.id) {
        result = await updateApi(stu.value);
      } else {
        result = await addApi(stu.value);
      }

      if (result.code) {
        dialogFormVisible.value = false;
        page();
      } else {
        ElMessage.error(result.msg);
      }
    } else {
      return false;
    }
  });
};

// 钩子函数
onMounted(() => {
  searchClassList();
  page();
});
</script>

<template>
  <!-- 标题 -->
  <div class="metitle">学员管理</div>

  <!-- 搜索菜单栏   :inline当设置为 true 时，它会将表单内的元素以行内（inline）的方式排列，而不是默认的块级（block）布局。 -->
  <div class="sousuo">
    <el-form :inline="true" :model="searchStu" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input
          v-model="searchStu.name"
          placeholder="请输入学生姓名"
          clearable
        />
      </el-form-item>

      <el-form-item label="最高学历">
        <el-select v-model="searchStu.degree" placeholder="请选择" clearable>
          <el-option
            v-for="degree in degreeList"
            :key="degree.value"
            :label="degree.name"
            :value="degree.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="所属班级">
        <el-select v-model="searchStu.clazzId" placeholder="请选择" clearable>
          <el-option
            v-for="clazz in classList"
            :key="clazz.id"
            :label="clazz.name"
            :value="clazz.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="page">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>
  <!-- 新增学员 批量删除学员 -->
  <div>
    <el-button
      type="success"
      @click="
        addStu();
        resetForm(stuFormRef);
      "
      >+新增学员</el-button
    >
    <el-button type="danger" @click="delByIds()">-批量删除</el-button>
  </div>
  <!-- 表格 -->
  <div class="biaoge">
    <!--@selection-change 事件监听器用于监听表格的选择变化情况。 用户选择或取消选择某行时，这个事件就会被触发。-->
    <el-table
      :data="tableData"
      border
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="35" />
      <el-table-column prop="name" label="姓名" width="100" align="center" />
      <el-table-column prop="no" label="学号" width="130" align="center" />
      <el-table-column
        prop="clazzName"
        label="班级"
        width="160"
        align="center"
      />
      <el-table-column label="性别" width="70" align="center">
        <template #default="scope">
          {{ scope.row.gender == 1 ? "男" : "女" }}
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="130" align="center" />
      <el-table-column label="最高学历" width="90" align="center">
        <template #default="scope">
          <span v-if="scope.row.degree == 1">初中</span>
          <span v-if="scope.row.degree == 2">高中</span>
          <span v-if="scope.row.degree == 3">大专</span>
          <span v-if="scope.row.degree == 4">本科</span>
          <span v-if="scope.row.degree == 5">硕士</span>
          <span v-if="scope.row.degree == 6">博士</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="violationCount"
        label="违纪次数"
        width="85"
        align="center"
      />
      <el-table-column
        prop="violationScore"
        label="违纪扣分"
        width="85"
        align="center"
      />
      <el-table-column
        prop="updateTime"
        label="最后修改时间"
        width="170"
        align="center"
      />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button
            size="small"
            type="primary"
            @click="
              updateStu(scope.row.id);
              resetForm(stuFormRef);
            "
            >编辑</el-button
          >
          <el-button
            size="small"
            type="warning"
            @click="openViolation(scope.row.id)"
            >违纪</el-button
          >
          <el-button size="small" type="danger" @click="delById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
  <!-- 分页条  layout="total, sizes, prev, pager, next, jumper"：这是一个字符串，指定了分页组件布局的组成部分。 total：显示总共有多少条记录。 sizes：提供一个下拉菜单， prev：上一页按钮。pager：页码导航器 next：下一页按钮。 jumper：输入框，-->
  <div>
    <el-pagination
      v-model:current-page="currentPage"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    /><!--@size-change每页展示的记录数变化时触发 @current-change页码记录数发生变化时触发-->
  </div>

  <!-- dialog对话框 dialogFormVisible是否展示对话框  rules 绑定表单验证规则 ref引用表单对象  ref 是 Vue 提供的一个特殊属性，用于获取 DOM 元素或组件实例的引用。-->
  <el-dialog v-model="dialogFormVisible" :title="title" width="50%">
    <!-- autocomplete="off"禁用表单自动补全 -->
    <el-form :model="stu" autocomplete="off" ref="stuFormRef" :rules="rules">
      <el-row>
        <el-col :span="12">
          <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
            <el-input v-model="stu.name" placeholder="请输入学员姓名" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学号" :label-width="formLabelWidth" prop="no">
            <el-input v-model="stu.no" placeholder="请输入学员学号" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item
            label="性别"
            :label-width="formLabelWidth"
            prop="gender"
          >
            <el-select
              v-model="stu.gender"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option
                v-for="gender in genders"
                :key="gender.value"
                :label="gender.name"
                :value="gender.value"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item
            label="手机号"
            :label-width="formLabelWidth"
            prop="phone"
          >
            <el-input v-model="stu.phone" placeholder="请输入手机号" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item
            label="身份证号"
            :label-width="formLabelWidth"
            prop="idCard"
          >
            <el-input v-model="stu.idCard" placeholder="请输入身份证号" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item
            label="是否在院校"
            :label-width="formLabelWidth"
            prop="isCollege"
          >
            <el-select
              v-model="stu.isCollege"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option
                v-for="whether in whethers"
                :key="whether.value"
                :label="whether.name"
                :value="whether.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item
            label="联系地址"
            :label-width="formLabelWidth"
            prop="address"
          >
            <el-input v-model="stu.address" placeholder="请输入联系地址" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item
            label="最高学历"
            :label-width="formLabelWidth"
            prop="degree"
          >
            <el-select
              v-model="stu.degree"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option
                v-for="degree in degreeList"
                :key="degree.value"
                :label="degree.name"
                :value="degree.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item
            label="毕业时间"
            :label-width="formLabelWidth"
            prop="graduationDate"
          >
            <el-date-picker
              v-model="stu.graduationDate"
              type="date"
              placeholder="请选择毕业时间"
              value-format="YYYY-MM-DD"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item
            label="所属班级"
            :label-width="formLabelWidth"
            prop="clazzId"
          >
            <el-select
              v-model="stu.clazzId"
              placeholder="请选择"
              style="width: 100%"
            >
              <el-option
                v-for="clazz in classList"
                :key="clazz.id"
                :label="clazz.name"
                :value="clazz.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button type="info" @click="dialogFormVisible = false"
          >取消</el-button
        >
        <el-button type="primary" @click="save(stuFormRef)">保存</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.demo-form-inline .el-input {
  --el-input-width: 220px;
}

.demo-form-inline .el-select {
  --el-select-width: 220px;
}

.sousuo {
  margin-top: 15px;
}

.biaoge {
  margin: 20px 0;
}

.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
