package cn.zimeedu.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 阿里云 OSS 配置属性类  实体类  并交给IOC容器管理
 * 用于从配置文件中加载与阿里云 OSS 相关的配置信息。
 * 通过 @ConfigurationProperties 注解，自动将配置文件中的属性值绑定到该类的成员变量中。
 */
@Data  // 生成构造方法 get set toString 等方法
@Component  // 声明bean的基础注解 不属于三层架构，用此注解  交给IOC容器管理
@ConfigurationProperties(prefix = "aliyun.oss")  // 从配置文件中获取值  prefix为前缀 成员变量要与配置文件中的key一致
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String reqion;
}
