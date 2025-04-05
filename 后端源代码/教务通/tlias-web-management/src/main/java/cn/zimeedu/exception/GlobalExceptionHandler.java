package cn.zimeedu.exception;


import cn.zimeedu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice  // 声明当前类是一个全局异常处理器 @ControllerAdvice + @ResponseBody  处理异常的方法返回值会转换为json后再响应给前端
public class GlobalExceptionHandler {
    // 定义全局异常处理器 处理异常         在类上加上一个注解@RestControllerAdvice，加上这个注解就代表我们定义了一个全局异常处理器。
    @ExceptionHandler // 声明当前方法是一个异常处理方法
    /**
     * 处理通用异常的方法
     *
     * @param e 捕获到的异常对象
     * @return 返回一个包含错误信息的 Result 对象
     */
    public Result handleException(Exception e){
        // 记录错误日志，包含异常信息
        log.error("全局异常处理器捕获到异常",e);
        // 返回一个错误结果，提示服务器异常
        return Result.error("服务器异常，请稍后再试 Exception");
    }

    /**
     * 处理 DuplicateKeyException 异常的方法
     *
     * @param e 捕获到的 DuplicateKeyException 异常对象
     * @return 返回一个包含错误信息的 Result 对象
     */
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){   // 如果该记录的主键或唯一约束字段值已经存在于数据库中，则会抛出这个异常。
        // 记录错误日志，提示程序出现 DuplicateKeyException 异常
        log.error("程序出错了！DuplicateKeyException");
        // 获取异常信息
        String message = e.getMessage();
        // 查找 "Duplicate entry" 在异常信息中的位置
        int index = message.indexOf("Duplicate entry");
        // 截取从 "Duplicate entry" 开始的错误信息
        String errMsg = message.substring(index);
        // 按空格分割错误信息
        String[] s = errMsg.split(" ");

        // 返回一个错误结果，提示重复的条目已存在
        return Result.error(s[2] + "已存在");
    }

}
