package cn.zimeedu.config;

import cn.zimeedu.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// 注册拦截器，指定拦截规则：
@Configuration  // 标记这个类是一个 Spring 配置类，Spring 启动时会加载它
public class WebConfig implements WebMvcConfigurer {

    // 自动注入自定义的拦截器对象（TokenInterceptor），用于实现拦截逻辑
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，指定拦截规则：
        // 1. 拦截所有请求路径（"/**" 表示匹配所有路径）
        // 2. 排除 "/login" 路径，不对其进行拦截（例如登录接口不需要验证）
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")         // 拦截所有请求
                .excludePathPatterns("/login"); // 排除登录接口
    }
}