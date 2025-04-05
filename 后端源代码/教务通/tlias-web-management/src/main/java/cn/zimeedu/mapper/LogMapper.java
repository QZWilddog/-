package cn.zimeedu.mapper;

import cn.zimeedu.pojo.OperateLog;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper  // 标识接口为 MyBatis 的映射器（Mapper）接口
public interface LogMapper {

    @Select("select operate_log.*, emp.name as operateEmpName from operate_log left join emp on operate_log.operate_emp_id = emp.id order by operate_log.operate_time desc")
    Page<OperateLog> page();
}
