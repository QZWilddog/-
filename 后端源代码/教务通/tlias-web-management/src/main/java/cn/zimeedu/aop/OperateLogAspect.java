package cn.zimeedu.aop;

import cn.zimeedu.mapper.OperateLogMapper;
import cn.zimeedu.pojo.OperateLog;
import cn.zimeedu.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

//  将对教务通系统数据的增删改查记录到数据库中
@Slf4j
@Aspect // 声明当前类是一个切面类
@Component // 声明当前类是spring中一个bean
public class OperateLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(cn.zimeedu.anno.Log)")  //环绕通知，此注解标注的通知方法在目标方法前、后都被执行
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 执行方法
        Object result = joinPoint.proceed();

        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 解析JWT令牌获取用户ID

        // 构建日志实体
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(CurrentHolder.getCurrentId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);

        // 记录日志
        log.info("记录操作日志{}", olog);

        operateLogMapper.insert(olog);

        return result;
    }

}
