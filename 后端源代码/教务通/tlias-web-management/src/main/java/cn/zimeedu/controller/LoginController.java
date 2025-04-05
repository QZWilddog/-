package cn.zimeedu.controller;

import cn.zimeedu.pojo.Emp;
import cn.zimeedu.pojo.LoginInfo;
import cn.zimeedu.pojo.Result;
import cn.zimeedu.service.EmpService;
import cn.zimeedu.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录请求: {}", emp);

        LoginInfo loginInfo = empService.login(emp);

        if (loginInfo != null){
            return Result.success(loginInfo);
        }

        return Result.error("用户账号密码错误");

    }

}
