<script setup>
  import { onMounted} from 'vue'
  // 导入reportAPI
  import {queryEmpJobDataApi,queryEmpGenderDataApi} from '@/api/report'
  // 引入消息提示框ElMessage
  import { ElMessage} from "element-plus"

  // 从 ECharts 库中导入所有核心功能。    全部会导致打包文件变大 影响Web性能
  // import * as echarts from 'echarts';

  // 导入 ECharts 的核心功能模块  echarts/core 是 ECharts 的核心库，包含初始化图表等基础功能
  import * as echarts from 'echarts/core';
  // 按需导入 ECharts 组件（如标题、提示框、图例等） 这些组件用于控制图表的不同部分，比如网格、提示框、图例和标题
  import { 
    GridComponent,    // 网格组件：控制图表主体区域的位置和大小
    TooltipComponent, // 提示框组件：当鼠标悬停时显示数据详情
    LegendComponent,  // 图例组件：标识不同数据系列（如柱状图的不同颜色）
    TitleComponent    // 标题组件：为图表添加主标题和副标题
  } from 'echarts/components';
  //按需导入 ECharts 图表类型（如柱状图、饼图等）
  import { 
    BarChart, // 柱状图：用于展示分类数据的对比
    PieChart  // 饼图：用于展示数据的比例分布
  } from 'echarts/charts';
  // 导入 Canvas 渲染器，用于在网页上绘制图表
  import { CanvasRenderer } from 'echarts/renderers';
  // 导入其他 ECharts 功能模块，如标签布局等 LabelLayout 是一个辅助功能，用于优化标签布局（比如避免文字重叠）
  import { LabelLayout } from 'echarts/features';
  
  // 注册所有需要的组件和图表类型到 ECharts 中只有注册过的模块才能在图表中使用
  echarts.use([
    GridComponent,    // 注册网格组件
    BarChart,         // 注册柱状图
    CanvasRenderer,   // 注册 Canvas 渲染器
    TooltipComponent, // 注册提示框组件
    LegendComponent,  // 注册图例组件
    LabelLayout,      // 注册标签布局功能
    PieChart,         // 注册饼图
    TitleComponent    // 注册标题组件
  ]);

  //获取职位统计报表
  const loadJobChart = async () => {
    const result = await queryEmpJobDataApi()
    if(result.code){
      initJobChart(result.data.jobList, result.data.dataList)      
      ElMessage.success("获取职位统计报表成功")
    }else{
      ElMessage.error(result.msg)
    }
  }
  //获取性别统计报表
  const loadGenderChart = async () => {
    const result = await queryEmpGenderDataApi()
    if(result.code){
      initGenderChart(result.data)
      ElMessage.success("获取性别统计报表成功") 
    }else{
      ElMessage.error(result.msg)
    }

  }

  // 统计职位-柱状图
  function initJobChart(jobList, dataList) {
    // 基于准备好的 DOM 元素（ID 为 'container1'），初始化一个 ECharts 实例。
    var myChart = echarts.init(document.getElementById('container1'));

    // 使用 setOption 方法绘制图表，并设置图表的各项配置。
    myChart.setOption({
      // 配置图表的标题部分。
      title: {
        text: '员工职位统计', // 主标题内容，显示在图表顶部。
        subText: '', // 副标题内容（这里为空，暂时不显示副标题）。
        textStyle: {
          fontSize: 20 // 设置主标题的字体大小为 20px。
        },
        left: 'center' // 设置主标题水平居中显示。
      },

      // 配置图表的网格部分，控制图表主体区域的位置和大小。
      grid: {
        left: '3%', // 图表距离容器左侧的距离为 3%。
        right: '4%', // 图表距离容器右侧的距离为 4%。
        bottom: '3%', // 图表距离容器底部的距离为 3%。
        containLabel: true // 确保坐标轴上的标签不会超出网格范围。
      },

      // 配置提示框组件（鼠标悬停时显示的提示信息）。
      tooltip: {}, // 默认情况下，提示框会自动根据数据生成。

      // 配置 X 轴，用于显示分类数据（如职位名称）。
      xAxis: {
        data: jobList // X 轴的数据来源是一个数组（jobList），通常是职位名称列表。
      },

      // 配置 Y 轴，默认是数值轴，用于显示人数等数据。
      yAxis: {}, // Y 轴的配置可以留空，ECharts 会自动生成合适的刻度。

      // 配置数据系列（series），定义图表的类型和数据。
      series: [
        {
          name: '人数', // 数据系列的名称，通常用于图例显示。
          type: 'bar', // 图表类型为柱状图（bar）。
          data: dataList, // 数据来源是一个数组（dataList），通常是每个职位对应的人数。

          // 配置柱状图的样式。
          itemStyle: {
            // 设置柱状图的颜色渐变效果。
            color: new echarts.graphic.LinearGradient(
              1, 0, 1, 1, // 渐变方向，从左上角 (0, 0) 到右下角 (1, 1)。
              [
                { offset: 0, color: '#dddd55' }, // 渐变起点颜色
                { offset: 1, color: '#daaf61' } // 渐变终点颜色
              ]
            )
          }
        }
      ]
    });
  }

  // 统计性别-饼状图
  function initGenderChart(genderDataList) {
    // 基于准备好的dom，初始化echarts实例
    const myChart = echarts.init(document.querySelector("#container2"))
    let option = { // 定义一个名为 option 的对象，用于存储图表的配置项。
      title: { // 配置图表的标题部分。
        text: '员工性别统计', // 主标题内容。
        textStyle: { // 设置主标题的文本样式。
          fontSize: 20 // 主标题字体大小为 20px。
        },
        left: 'center' // 主标题水平居中显示。
      },
      grid: { // 配置图表的绘图区域（网格）。
        left: '3%', // 绘图区域距离左侧 3% 的位置。
        right: '4%', // 绘图区域距离右侧 4% 的位置。
        bottom: '3%', // 绘图区域距离底部 3% 的位置。
        containLabel: true // 确保坐标轴的标签不会超出绘图区域。
      },
      tooltip: { // 配置提示框（鼠标悬停时显示的信息）。
        trigger: 'item' // 提示框触发方式：针对数据项（如饼图的每一块）。
      },
      legend: { // 配置图例（用于标识不同数据系列）。
        top: '10%', // 图例距离顶部 10% 的位置。
        left: 'center' // 图例水平居中显示。
      },
      series: [ // 配置数据系列（图表的核心部分，包含实际的数据和样式）。
        {
          name: '性别', // 数据系列的名称（如“性别”）。
          type: 'pie', // 图表类型：饼图。
          radius: ['40%', '70%'], // 饼图的内外半径，形成一个环形图。
          avoidLabelOverlap: false, // 不避免标签重叠（适用于环形图）。
          top: '5%', // 数据系列距离顶部 5% 的位置。
          itemStyle: { // 配置每个扇区的样式。
            borderRadius: 10, // 扇区圆角半径为 5px。
            borderColor: '#fff', // 扇区边框颜色为白色。
            borderWidth: 2 // 扇区边框宽度为 2px。
          },
          label: { // 配置扇区上的文字标签。
            show: false, // 默认不显示标签。
            position: 'center' // 标签显示在中心位置。
          },
          emphasis: { // 配置高亮状态下的样式（如鼠标悬停时）。
            label: { // 高亮状态下标签的样式。
              show: true, // 显示标签。
              fontSize: 40, // 标签字体大小为 20px。
              fontWeight: 'bold' // 标签字体加粗。
            }
          },
          data: genderDataList // 数据源，genderDataList 是一个数组，包含饼图的具体数据。
        }
      ]
    };
    // 绘制图表
    myChart.setOption(option);
    
  }

  //钩子函数
  onMounted(() => {
    loadGenderChart()
    loadJobChart()
  })

</script>

<template>
    <div class="metitle">员工信息统计</div>
  <!-- ECharts 需要一个有明确宽高的 DOM 元素来渲染图表 -->
   <!-- 员工职位统计 -->
  <div class="report_container" id="container1"></div>
  <!-- 员工性别统计 -->
  <div class="report_container" id="container2"></div>
</template>

<style scoped>
  .report_container {
      width: 49%; /* 设置容器宽度为父元素宽度的 49%，即占据大约一半的空间 */
      height: 90%; /* 设置容器高度为父元素高度的 90%，让内容区域更紧凑 */
      float: left; /* 让容器浮动到左侧，这样其他元素可以排列在它的旁边 */
      margin-left: 5px; /* 在容器左侧设置 5px 的外边距，避免与其他元素贴得太近 */
  }

  #container1 {
      border-right: 3px dashed #ccc; /* 在容器右侧添加一条 1px 宽的虚线边框，颜色为浅灰色（#ccc） */
  }
</style>
