<script setup>
  // 导入ref响应数据 钩子函数
  import {ref, onMounted} from "vue"
  // 导入消息提示框
  import { ElMessage } from "element-plus"
  // 导入api请求
  import {pageApi} from '@/api/log'

  // 表格数据
  const logData = ref([])
  
  //分页查询
  const page = async () => {
    const result = await pageApi(pagination.value.currentPage, pagination.value.pageSize)
    if(result.code){
      logData.value = result.data.rows
      pagination.value.total = result.data.total
      ElMessage.success("成功")
    }else{
      ElMessage.error(result.msg)
    }
  }

  //分页条数据
  const pagination = ref({currentPage:1, pageSize:10, total:0 })

  // 分页条每页展示的记录数变化时触发
  const handleSizeChange = (pageSize) => {
    pagination.value.pageSize = pageSize
    page()
  }

  //页码记录数发生变化时触发
  const handleCurrentChange = (currentPage) => {
    pagination.value.currentPage =currentPage
    page()
  }

  onMounted(() => {
    page()
  })
</script>

<template>
  <div class="metitle">日志管理</div>
  <!-- 表格 -->
  <div class="biaoge">
    <el-table :data="logData" border style="width: 100%">
      <el-table-column prop="operateEmpName" label="操作人" width="80px" align="center"/>
      <el-table-column prop="operateTime" label="操作时间" width="150px" align="center"/>
      <el-table-column prop="className" label="类名" width="300px" align="center"/>
      <el-table-column prop="methodName" label="方法名" width="100px" align="center"/>
      <el-table-column prop="costTime" label="操作耗时" width="100px" align="center"/>
      <el-table-column prop="methodParams" label="请求参数" width="280px" align="center" show-overflow-tooltip="true"/>
      <el-table-column prop="returnValue" label="返回 值"  align="center"/>
    </el-table>
  </div>
  <!-- 分页条 @size-change每页展示的记录数变化时触发 @current-change页码记录数发生变化时触发 -->
   <div class="fenye">
    <el-pagination
      v-model:current-page="pagination.currentPage"
      v-model:page-size="pagination.pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :background= "true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
   </div>
</template>

<style scoped>
  .biaoge {
    margin: 15px 0;
  }
</style>
