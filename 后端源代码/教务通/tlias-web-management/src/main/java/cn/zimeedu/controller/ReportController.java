package cn.zimeedu.controller;


import cn.zimeedu.pojo.ClazzOption;
import cn.zimeedu.pojo.JobOption;
import cn.zimeedu.pojo.Result;
import cn.zimeedu.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    // 统计图形统计报表类
    @Autowired
    private ReportService reportService;

    // 统计员工性别
    @GetMapping("/empGenderData")
    public Result getEmpSexData(){
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpSexData();
        return Result.success(genderList);
    }
    // 统计员工各个职位人数
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption =  reportService.getEmpJobData();

        return Result.success(jobOption);
    }
    //统计各个班级学员人数
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级学员人数");

        ClazzOption clazzOption = reportService.getStudentCountData();

        return Result.success(clazzOption);
    }

    //统计学员学历
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学员学历");

        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();

        return Result.success(degreeList);
    }
}
