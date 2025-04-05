package cn.zimeedu.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 与aop配合 标识那些方法的操作 需要记录日志到数据库中
@Target(ElementType.METHOD) // 修饰注解的注解 叫元注解  定义注解生效的范围 在方法上生效
@Retention(RetentionPolicy.RUNTIME) // 定义注解什么时候生效
public @interface Log {  // 表示方法的作用 给2.3.2 @annotation 注解使用
}
