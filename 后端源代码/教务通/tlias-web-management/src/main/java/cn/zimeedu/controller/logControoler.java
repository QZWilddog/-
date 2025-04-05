package cn.zimeedu.controller;

import cn.zimeedu.mapper.LogMapper;
import cn.zimeedu.pojo.OperateLog;
import cn.zimeedu.pojo.PageResult;
import cn.zimeedu.pojo.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j // 定义一个日志记录器
@RequestMapping("/log")
@RestController// 标识当前类是一个控制器类 也就是请求处理类  这是个组合注解 相当于 @Controller（将类交给IOC容器） + @ResponseBody（将方法的返回值直接作为响应数据响应给前端 如果返回是一个对象或者集合会转为json数据格式返回）
public class logControoler {
    @Autowired
    LogMapper logMapper;

    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize){
        // 设置分页参数给pagehelper插件
        PageHelper.startPage(page, pageSize);

        Page<OperateLog> pageList = logMapper.page();

       return Result.success(new PageResult<>(pageList.getTotal(), pageList.getResult()));  // getResult() 方法用于获取当前页的数据列表。
    }
}
