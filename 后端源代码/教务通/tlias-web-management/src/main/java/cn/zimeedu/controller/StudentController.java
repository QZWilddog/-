package cn.zimeedu.controller;

import cn.zimeedu.anno.Log;
import cn.zimeedu.pojo.PageResult;
import cn.zimeedu.pojo.Result;
import cn.zimeedu.pojo.Student;
import cn.zimeedu.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 声明日志记录器
@RestController // 声明控制类   相当于 @Controller（将类交给IOC容器） + @ResponseBody（将方法的返回值直接作为响应数据响应给前端 如果返回是一个对象或者集合会转为json数据格式返回）
@RequestMapping("/students") //请求映射 可以基于这个注解来抽取公共请求路径  这个可以加载类与方法上 一个完整的请求路径= 类上 + 方法上
public class StudentController {

    @Autowired // 用于自动装配构Bean  注入的是对象的实例 所以不能静态
    private StudentService studentService;

    // 分页查询
    @GetMapping
    public Result page(String name,
                              Integer degree,
                              Integer clazzId,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询{}{}{}",name, degree, clazzId);
        PageResult<Student> pageResult = studentService.page(name, degree, clazzId, page, pageSize);

        return Result.success(pageResult);
    }

    // 删除学员
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    @DeleteMapping("/{ids}")
    public Result delById(@PathVariable List<Integer> ids){    // 推荐集合因为集合操作数据更加方便 Spring Boot Spring 内置的类型转换器 的自动类型转换机制。 在某些 Spring Boot 版本中（尤其是较新的版本），Spring 提供了对路径变量的 隐式类型转换支持。
        log.info("删除{}学员",ids.toString());
        studentService.delById(ids);
        return Result.success();
    }
    //添加学员
    @PostMapping
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result add(@RequestBody Student student){
        log.info("添加学员：{}", student);
        studentService.add(student);
        return Result.success();
    }
    // 根据ID查询学员
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询学员{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    // 修改学员
    @PutMapping
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result update(@RequestBody Student student){
        log.info("修改学员{}", student);
        studentService.update(student);
        return Result.success();
    }
    // 违纪处理
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    @PutMapping("/violation/{id}/{score}")
    public Result updateScore(@PathVariable Integer id,@PathVariable Integer score){
        log.info("处理学员违纪分数id:{}score:{}", id, score);
        studentService.updateScore(id, score);
        return Result.success();
    }
}
