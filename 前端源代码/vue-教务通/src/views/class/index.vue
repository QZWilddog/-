<script setup>
// 导入ref响应数据 watch侦听对象
import { ref, onMounted, watch } from "vue";
import {
  queryPageApi,
  delApi,
  addApi,
  queryByIdApi,
  updateApi,
} from "@/api/class";
import { queryAllApi as queryAllEmpApi } from "@/api/emp";
// 引入消息提示框ElMessage 引入消息弹出框ElMessageBox
import { ElMessage, ElMessageBox } from "element-plus";

// 搜索菜单栏
const searchClazz = ref({
  name: "",
  date: [],
  begin: "",
  end: "",
});

// 侦听函数
watch(
  () => searchClazz.value.date,
  (newVal, oldVal) => {
    if (newVal.length == 2) {
      searchClazz.value.begin = newVal[0];
      searchClazz.value.end = newVal[1];
    } else {
      searchClazz.value.begin = "";
      searchClazz.value.end = "";
    }
  },
  { deep: true }
); // 不加深度侦听 只有当数组的引用发生变化才触发侦听

//  分页查询班级信息
const queryPage = async () => {
  const result = await queryPageApi(
    searchClazz.value.name,
    searchClazz.value.begin,
    searchClazz.value.end,
    pagination.value.currentPage,
    pagination.value.pageSize
  );

  if (result.code) {
    tableData.value = result.data.rows;
    pagination.value.total = result.data.total;
    ElMessage.success("成功");
  } else {
    ElMessage.error("错误");
  }
};

// 清除按钮
const clear = () => {
  searchClazz.value = {
    name: "",
    date: [],
    begin: "",
    end: "",
  };
  queryPage();
};

// 表格
const tableData = ref([]);

//删除按钮
const delById = async (id) => {
  ElMessageBox.confirm(
    // confirm 框 提示框
    "您确定要删除这条信息嘛",
    "提示",
    {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    }
  )
    .then(async () => {
      const result = await delApi(id);
      if (result.code) {
        ElMessage.success("成功");
        queryPage();
      }
    })
    .catch(() => {
      ElMessage.info("您已取消操作");
    });
};

// 查询所有员工就是班主任的数据
const emps = ref([]);
const queryAllEmp = async () => {
  let result = await queryAllEmpApi();
  if (result.code) {
    emps.value = result.data;
  }
};

// 分页组件
const pagination = ref({ currentPage: 1, pageSize: 10, total: 0 });

//每页展示记录数发生变化时触发
const handleSizeChange = (pageSize) => {
  pagination.value.pageSize = pageSize;
  queryPage();
};
//当前页码发生变化时触发
const handleCurrentChange = (currentPage) => {
  pagination.value.currentPage = currentPage;
  queryPage();
};

// 新增班级按钮功能区域实现

// dialog数据
//学科列表数据
const subjects = ref([
  { name: "Java", value: 1 },
  { name: "前端", value: 2 },
  { name: "大数据", value: 3 },
  { name: "Python", value: 4 },
  { name: "Go", value: 5 },
  { name: "嵌入式", value: 6 },
]);

const clazz = ref({
  id: "", // 修改班级用的到
  name: "",
  room: "",
  beginDate: "",
  endDate: "",
  masterId: "",
  subject: "",
});
const dialogFormVisible = ref(false); //控制新增/修改的对话框的显示与隐藏
const formLabelWidth = ref(80); //form表单label的宽度
const title = ref("");

// 新增班级
const addClass = () => {
  dialogFormVisible.value = true;
  title.value = "新增班级";
  clearClazz();
};

// 编辑班级按钮
const updateClazz = async (id) => {
  clearClazz();
  dialogFormVisible.value = true;
  title.value = "更改班级";
  const result = await queryByIdApi(id);
  if (result.code) {
    clazz.value = result.data;
  }
};

// 保存按钮
const save = async (clazzForm) => {
  if (!clazzForm) return;
  // 校验表单结果validate
  clazzForm.validate(async (valid) => {
    if (valid) {
      let result;
      if (clazz.value.id) {
        result = await updateApi(clazz.value); // 有id更改
      } else {
        result = await addApi(clazz.value); // 无id增加
      }

      if (result.code) {
        dialogFormVisible.value = false;
        ElMessage.success("成功");
        queryPage();
      } else {
        ElMessage.error(result.code);
      }
    } else {
      console.log("valid:" + valid);
      return false;
    }
  });
};

//清空表单
const clearClazz = () => {
  clazz.value = {
    id: "",
    name: "",
    room: "",
    beginDate: "",
    endDate: "",
    subject: "",
    masterId: "",
  };
};

// dialog表单校验
// 用于给元素或组件注册引用信息
const clazzFromRef = ref();

const rules = ref({
  name: [
    { required: true, message: "班级名称不能为空", trigger: "blur" },
    { min: 3, max: 30, message: "班级名称长度为3-30字", trigger: "blur" },
  ],
  beginDate: [
    {
      type: "date",
      required: true,
      message: "开课时间不能为空",
      trigger: "change",
    },
  ],
  endDate: [
    {
      type: "date",
      required: true,
      message: "结课时间不能为空",
      trigger: "change",
    },
  ],
  subject: [{ required: true, message: "学科不能为空", trigger: "change" }],
});

// 重置表单校验resetFields   校验表单结果validate  重置表单到初始状态，清除用户输入的内容以及错误提示。
const resetForm = (clazzForm) => {
  if (!clazzForm) return;
  clazzForm.resetFields();
};

// 钩子函数
onMounted(() => {
  queryPage();
  queryAllEmp();
});
</script>

<template>
  <!-- 标题 -->
  <div class="metitle">班级管理</div>
  <!--  搜索菜单栏 -->
  <div class="sousuo">
    <el-form :inline="true" :model="searchClazz" class="demo-form-inline">
      <el-form-item label="班级名称">
        <el-input
          v-model="searchClazz.name"
          placeholder="请输入班级名称"
          clearable
        />
      </el-form-item>
      <!-- 日期选择器 -->
      <el-form-item label="结课时间">
        <el-date-picker
          v-model="searchClazz.date"
          type="daterange"
          range-separator="至"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          value-format="YYYY-MM-DD"
        />
        <!--type="daterange" 日期范围 range-separator="To"开始和结束用什么字连接 value-format绑定的格式 默认date -->
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="queryPage">查询</el-button>
        <el-button type="info" @click="clear">清除</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 新增班级按钮 -->
  <div>
    <el-button
      type="success"
      @click="
        addClass();
        resetForm(clazzFromRef);
      "
      >+新增班级</el-button
    >
  </div>
  <!-- 表格 -->
  <div class="biaoge">
    <el-table :data="tableData" border style="width: 100%" fit>
      <!--设置type-->
      <el-table-column type="index" label="序号" width="55" align="center" />
      <el-table-column
        prop="name"
        label="班级名称"
        width="200"
        align="center"
      />
      <el-table-column
        prop="room"
        label="班级教室"
        width="100"
        align="center"
      />
      <el-table-column
        prop="masterName"
        label="班主任"
        align="center"
        width="100"
      />
      <el-table-column
        prop="beginDate"
        label="开课时间"
        align="center"
        width="150"
      />
      <el-table-column
        prop="endDate"
        label="结课时间"
        align="center"
        width="150"
      />
      <el-table-column prop="status" label="状态" align="center" width="130" />
      <el-table-column prop="updateTime" label="最后修改时间" align="center" />
      <el-table-column prop="" label="操作" align="center">
        <template #default="scoped">
          <el-button
            type="primary"
            size="small"
            @click="
              updateClazz(scoped.row.id);
              resetForm(clazzFromRef);
            "
            ><el-icon><Pointer /></el-icon>编辑</el-button
          >
          <el-button type="danger" size="small" @click="delById(scoped.row.id)"
            ><el-icon><Delete /></el-icon>删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 分页条 -->
  <el-pagination
    v-model:current-page="pagination.currentPage"
    v-model:page-size="pagination.pageSize"
    :page-sizes="[10, 20, 30, 40]"
    :background="true"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pagination.total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
  <!-- dialog对话框 dialogFormVisible是否展示对话框  rules 绑定表单验证规则 ref引用表单对象-->
  <el-dialog v-model="dialogFormVisible" :title="title" width="35%">
    <el-form :model="clazz" :rules="rules" ref="clazzFromRef">
      <el-form-item label="班级名称" :label-width="formLabelWidth" prop="name">
        <el-input
          v-model="clazz.name"
          autocomplete="off"
          placeholder="请输入班级名称"
        />
      </el-form-item>

      <el-form-item label="班级教室" :label-width="formLabelWidth">
        <el-input
          v-model="clazz.room"
          autocomplete="off"
          placeholder="请输入班级教室"
        />
      </el-form-item>

      <el-form-item
        label="开课时间"
        :label-width="formLabelWidth"
        prop="beginDate"
      >
        <el-date-picker
          value-format="YYYY-MM-DD"
          v-model="clazz.beginDate"
          type="date"
          placeholder="请选择开课时间"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item
        label="结课时间"
        :label-width="formLabelWidth"
        prop="endDate"
      >
        <el-date-picker
          value-format="YYYY-MM-DD"
          v-model="clazz.endDate"
          type="date"
          placeholder="请选择结课时间"
          style="width: 100%"
        />
      </el-form-item>

      <el-form-item label="班主任" :label-width="formLabelWidth">
        <el-select
          v-model="clazz.masterId"
          placeholder="请选择班主任"
          style="width: 100%"
        >
          <el-option
            v-for="emp in emps"
            :key="emp.id"
            :label="emp.name"
            :value="emp.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="学科" :label-width="formLabelWidth" prop="subject">
        <el-select
          v-model="clazz.subject"
          placeholder="请选择学科"
          style="width: 100%"
        >
          <el-option
            v-for="sub in subjects"
            :key="sub.value"
            :label="sub.name"
            :value="sub.value"
          />
        </el-select>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <!--而是为 @click 事件绑定了多个操作 你可以通过分号（;）分隔来执行多个 JavaScript 语句-->
        <el-button
          type="info"
          @click="
            dialogFormVisible = false;
            resetForm(clazzFromRef);
          "
          >取消</el-button
        >
        <el-button type="primary" @click="save(clazzFromRef)">保存</el-button>
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
</style>
