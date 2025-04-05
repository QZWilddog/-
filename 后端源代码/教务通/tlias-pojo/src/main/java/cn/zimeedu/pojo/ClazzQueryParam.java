package cn.zimeedu.pojo;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//优化思路：定义一个实体类，来封装这几个请求参数。 【需要保证，前端传递的请求参数和实体类的属性名是一样的】
// 班级分页查询前端参数接收实体类
@Data
public class ClazzQueryParam {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //@DateTimeFormat注解用于将前端传递的日期字符串转换为LocalDate对象 指定传递过来的日期格式
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 日期等应用类型 默认值是null 所以这里只是指定格式
    private LocalDate end;
    private Integer page = 1;
    private Integer pageSize = 10;
}
