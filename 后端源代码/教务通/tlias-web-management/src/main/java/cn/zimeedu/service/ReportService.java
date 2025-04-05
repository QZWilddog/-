package cn.zimeedu.service;

import cn.zimeedu.pojo.ClazzOption;
import cn.zimeedu.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    //统计各个员工职位人数
    JobOption getEmpJobData();
    // 统计员工各个性别人数
    List<Map<String, Object>> getEmpSexData();
    //统计班级学员人数
    ClazzOption getStudentCountData();
    // 统计学员学历
    List<Map<String, Object>> getStudentDegreeData();
}
