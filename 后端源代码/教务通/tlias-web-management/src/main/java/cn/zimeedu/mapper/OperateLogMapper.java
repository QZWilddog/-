package cn.zimeedu.mapper;

import cn.zimeedu.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper   //  在Spring Boot启动的时候，MyBatis会扫描带有 @Mapper 注解的接口，并且为这些接口创建对应的代理对象
public interface OperateLogMapper {
    
    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);
    
}
