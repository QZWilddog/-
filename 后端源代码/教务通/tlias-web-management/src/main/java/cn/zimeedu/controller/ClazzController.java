package cn.zimeedu.controller;

import cn.zimeedu.anno.Log;
import cn.zimeedu.pojo.*;
import cn.zimeedu.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  // 定义一个日志记录器
//@Controller // 主要用于标记控制器层类。它不仅标识了该类是一个Spring Bean，还暗示了其主要职责是处理HTTP请求。
@RestController // 标识当前类是一个控制器类 也就是请求处理类  这是个组合注解 相当于 @Controller（将类交给IOC容器） + @ResponseBody（将方法的返回值直接作为响应数据响应给前端 如果返回是一个对象或者集合会转为json数据格式返回）
@RequestMapping("/clazzs") //请求映射 可以基于这个注解来抽取公共请求路径  这个可以加载类与方法上 一个完整的请求路径= 类上 + 方法上
public class ClazzController {

    @Autowired // 用于自动装配构Bean
    private ClazzService clazzService;

    // 分页查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){  //  声明与前端参数一样名称的参数，就可以省略  注解设置默认值  但是如果要默认值等操作就要@RequestParam(defaultValue = "10")  而且参数多  所以可以用一个实体类对象封装
        log.info("分页查询{}", clazzQueryParam); // 根据logback.xml配置文件配置可以控制输出到 控制台 文本文件 等

        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);  // 分页查询 返回分页结果类

        return Result.success(pageResult);
    }
    // 保存班级数据
    @PostMapping
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result save(@RequestBody Clazz clazz){
        log.info("保存班级数据{}", clazz);
        clazzService.save(clazz);

        return Result.success();
    }

    // 根据ID删除班级
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    @DeleteMapping("/{id}")   // 映射 HTTP DELETE 请求到处理器方法的注解 {id} 是一个路径变量（也称为 URI 变量） 它允许你从请求的 URL 中提取参数值并将其传递给控制器的方法
    public Result delete(@PathVariable Integer id){
        log.info("删除班级id为:{}",id);
        clazzService.delete(id);

        return Result.success();
    }

    // 根据ID查询班级 查询回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){  // 获取路径参数
        log.info("根据ID查询班级：{}", id);
        Clazz clazz =  clazzService.getById(id);
        return Result.success(clazz);
    }

    // 修改班级
    @PutMapping
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result update(@RequestBody Clazz clazz){
        log.info("更改班级数据{}", clazz);

        clazzService.update(clazz);

        return Result.success();
    }

    // 查询所有班级
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");

        List<Clazz> clazzList  = clazzService.list();

        return Result.success(clazzList);
    }

}
