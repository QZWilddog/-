package cn.zimeedu.service.impl;

import cn.zimeedu.mapper.EmpMapper;
import cn.zimeedu.mapper.StudentMapper;
import cn.zimeedu.pojo.ClazzOption;
import cn.zimeedu.pojo.JobOption;
import cn.zimeedu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        // 调用mapper接口 获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        // 组装结果，并返回   这里 map 的作用是将 list 中的每个 Map<String, Object> 转换成 "pos" 键对应的值，最终生成一个新的 List<Object>，其中只包含从每个 Map 中提取出来的 "pos" 值。
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList =  list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList, dataList);

    }

    @Override
    public List<Map<String, Object>> getEmpSexData() {

        return empMapper.countEmpSexData();
    }

    @Override
    public ClazzOption getStudentCountData(){
        List<Map<String, Object>> list = studentMapper.getStudentCountData();

        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new ClazzOption(clazzList,dataList);
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData(){

        return studentMapper.getStudentDegreeData();
    }
}
