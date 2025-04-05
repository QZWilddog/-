package cn.zimeedu.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//优化思路：定义一个实体类，来封装这几个请求参数。 【需要保证，前端传递的请求参数和实体类的属性名是一样的】
// 部门分页查询前端参数接收实体类
@Data
public class EmpQueryParam {

    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
    private String name; //姓名
    private Integer gender; //性别
    @DateTimeFormat(pattern = "yyyy-MM-dd") //@DateTimeFormat注解用于将前端传递的日期字符串转换为LocalDate对象 指定传递过来的日期格式
    private LocalDate begin; //入职开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //入职结束时间

}