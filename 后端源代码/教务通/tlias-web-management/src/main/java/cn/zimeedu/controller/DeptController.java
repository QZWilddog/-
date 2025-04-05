package cn.zimeedu.controller;


import cn.zimeedu.anno.Log;
import cn.zimeedu.pojo.Dept;
import cn.zimeedu.pojo.Result;
import cn.zimeedu.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  // 定义一个日志记录器
@RequestMapping("/depts") // 可以基于这个注解来抽取公共请求路径  这个可以加载类与方法上 一个完整的请求路径= 类上 + 方法上
@RestController  // 标识当前类是一个控制器类 也就是请求处理类  这是个组合注解 相当于 @Controller（将类交给IOC容器） + @ResponseBody（将方法的返回值直接作为响应数据响应给前端 如果返回是一个对象或者集合会转为json数据格式返回）
public class DeptController {

    // 定义一个日志记录器  日志对象 面向接口  固定代码  如果有lombox框架可以直接使用注解
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired // 注解表示自动注入 业务逻辑层对象
    private DeptService deptService;

    // 返回值是返回给前端的数据类型，所有数据都要封装到Result对象中为统一响应结果封装类 当前项目要统一返回一个结果就是result
    // @RequestMapping 是 Spring 框架中用于处理 HTTP 请求映射的注解
    // 因为遵循RESTful风格，只处理get请求所以使用GetMapping注解  get查询（查询参数） post新增 put修改 delete删除
    @GetMapping// 当前方法处理的是哪一个请求，所以要指定一个请求路径
    public Result list(){
//        System.out.println("调用了查询全部部门数据的方法");
        // 记录日志
        log.info("调用了查询全部部门数据的方法");

        List<Dept> deptList = deptService.findAll();

        return Result.success(deptList);  // 统一响应结果封装类 里面三个方法 调用统一响应结果封装类中的静态方法 这里需要返回参数
    }

    // 新增部门
    @PostMapping
    @Log // 自定义注解 为aop切面类使用 记录日志到数据库
    public Result insert(@RequestBody Dept dept){
//        System.out.println("新增了部门：" + dept);
        // 记录日志  lombok中提供了{}占位符方法
        log.info("新增了部门：{}", dept);
        deptService.insert(dept);
        return Result.success();
    }

    // 修改部门  路径参数：通过请求URL直接传递参数，使用{}来表示该路径参数 需要使用@PathVariable注解来获取路径参数的值。 1.查询回显  2.修改数据
    // 1. 查询回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){ // 如果路径参数名与controller方法形参名称一致，@PathVariable注解的value属性是可以省略的
//        System.out.println("根据ID查询部门：" + id);
        log.info("根据ID查询部门：{}", id);
        Dept dept = deptService.getById(id);

        return Result.success(dept);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Dept user){
//        System.out.println("修改了用户：" + user);

        log.info("修改了部门：{}", user);
        deptService.updateById(user);
        return Result.success();
    }

    // 删除部门- - 如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam） （推荐）
    @DeleteMapping
    @Log
    public Result delete(Integer id){
//        System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id);
        deptService.delete(id);
//        this.list();
        return Result.success();
    }

//    // 删除部门- - 方案二：通过Spring提供的 @RequestParam 注解，将请求参数绑定给方法形参
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id", required = false) Integer idInt){
//        System.out.println("根据ID删除部门：" + idInt);
//        return Result.success();
//    }

//    // 删除部门- 方案一：通过原始的 HttpServletRequest 对象获取请求参数
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int idInt = Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门：" + idInt);
//        return Result.success();
//    }
}
//    @RequestMapping：这是一个通用的注解，能用于映射所有类型的HTTP请求。可以在类级别和方法级别使用。
//    @GetMapping：专门用于映射 GET 请求，是 @RequestMapping(method = RequestMethod.GET) 的快捷方式。
//    @PostMapping：专门用于映射 POST 请求，是 @RequestMapping(method = RequestMethod.POST) 的快捷方式。
//    @PutMapping：专门用于映射 PUT 请求，是 @RequestMapping(method = RequestMethod.PUT) 的快捷方式。
//    @DeleteMapping：专门用于映射 DELETE 请求，是 @RequestMapping(method = RequestMethod.DELETE) 的快捷方式
