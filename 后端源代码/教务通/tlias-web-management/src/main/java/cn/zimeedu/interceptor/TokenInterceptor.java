package cn.zimeedu.interceptor;

import cn.zimeedu.utils.CurrentHolder;
import cn.zimeedu.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component  // 他是Spring中的一个组件 所以要交给IOC 容器管理
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {  // 是 Spring 框架中用于实现请求拦截功能的一个核心接口
    // 令牌校验的拦截器
    //目标资源方法执行前执行。 返回true：放行    返回false：不放行    preHandle 方法中被使用。handler 是 Object 类型，它通常代表即将执行的处理程序对象
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1. 获取请求url。
//        String url = request.getRequestURL().toString();
//
//        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
//        if(url.contains("login")){ //登录请求
//            log.info("登录请求 , 直接放行");
//            return true;
//        }

        //3. 获取请求头中的令牌（token）。
        String token = request.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(token == null || token.isEmpty()){ //jwt为空
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5. 解析token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.parseInt(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);// 将ID存入当前线程存储区域
            log.info("当前登陆的员工ID为{} 存入ThreadLocal", empId);
        } catch (Exception e) {
            log.info("解析令牌非法, 返回错误结果");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        //6. 放行。
        log.info("令牌合法, 放行");
        return true;


    }

    //目标资源方法执行后执行
    @Override   // 主要用于封装控制器方法处理完请求后返回给视图的数据和视图信息
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 目标资源执行完后 移除当前线程的 ThreadLocal 中存储的员工 ID
        CurrentHolder.remove();
    }
}
