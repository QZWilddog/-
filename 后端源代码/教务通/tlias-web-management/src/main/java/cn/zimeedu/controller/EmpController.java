package cn.zimeedu.controller;

import cn.zimeedu.anno.Log;
import cn.zimeedu.pojo.Emp;
import cn.zimeedu.pojo.EmpQueryParam;
import cn.zimeedu.pojo.PageResult;
import cn.zimeedu.pojo.Result;
import cn.zimeedu.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


// 员工管理Controller
@Slf4j  // 定义一个日志记录器
@RequestMapping("/emps") // 可以基于这个注解来抽取公共请求路径  这个可以加载类与方法上 一个完整的请求路径= 类上 + 方法上
@RestController  // 标识当前类是一个控制器类 也就是请求处理类  这是个组合注解 相当于 @Controller（将类交给IOC容器） + @ResponseBody（将方法的返回值直接作为响应数据响应给前端 如果返回是一个对象或者集合会转为json数据格式返回）
public class EmpController {
    @Autowired
    private EmpService empService;  // 调用service就注入service

    // 修改员工
    @PutMapping
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result update(@RequestBody Emp emp){
        log.info("修改了员工：{}", emp);
        empService.update(emp);

        return Result.success();
    }

    // 查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){

        log.info("查询回显：{}", id);

        Emp emp =  empService.getInfo(id);

        return Result.success(emp);
    }

    // 删除员工
    @DeleteMapping()
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result delete(@RequestParam List<Integer> ids){  // @RequestParam  如果通过集合这种复杂类型接收参数 不能省略
        log.info("删除员工{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping// 新增员工
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result save(@RequestBody Emp emp){  //  因为这里前端返回的json格式 所以要加
        log.info("新增员工{}", emp);

        empService.save(emp);

        return Result.success();
    }

    // 分页查询  参数用对象接收
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){  //  声明与前端参数一样名称的参数，就可以省略  注解设置默认值
        log.info("分页查询{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);

        return Result.success(pageResult);
    }

    @GetMapping("list")
    public Result list(){
        log.info("查询全部员工信息");
        List<Emp> empList =  empService.list();
        return Result.success(empList);
    }

//    // 分页查询
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,  //@DateTimeFormat注解用于将前端传递的日期字符串转换为LocalDate对象 指定传递过来的日期格式
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){  //  声明与前端参数一样名称的参数，就可以省略  注解设置默认值
//        log.info("分页查询{} {} {} {} {} {}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize,name, gender, begin, end);
//
//        return Result.success(pageResult);

}
