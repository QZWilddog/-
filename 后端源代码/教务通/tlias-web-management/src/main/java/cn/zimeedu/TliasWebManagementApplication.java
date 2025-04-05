package cn.zimeedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

//@Import(TokenParser.class) //导入的类会被Spring加载到IOC容器中  @Import(HeaderConfig.class) //导入配置类  @Import(MyImportSelector.class) //导入ImportSelector接口实现类
@ServletComponentScan  // 开启了SpringBoot对Servlet组件的支持  会自动扫描Servlet提供的注解 使组件生效
@SpringBootApplication  // 注解标识当前类为springboot项目的启动类 具备组件扫描的功能 但是默认扫描的是启动类所在包及其子包  这个注解组合了@Configuration配置类 @ComponentScan组件扫描 @EnableAutoConfiguration开启自动配置
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }


}
