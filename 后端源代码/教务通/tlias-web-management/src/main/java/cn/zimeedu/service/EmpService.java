package cn.zimeedu.service;

import cn.zimeedu.pojo.Emp;
import cn.zimeedu.pojo.EmpQueryParam;
import cn.zimeedu.pojo.LoginInfo;
import cn.zimeedu.pojo.PageResult;

import java.util.List;


public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    // 新增员工
    void save(Emp emp);
    // 批量删除
    void delete(List<Integer> ids);

    // 查询回显
    Emp getInfo(Integer id);

    // 修改员工
    void update(Emp emp);

    LoginInfo login(Emp emp);

    // 查询全部员工数据
    List<Emp> list();


    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页条数
     * */

//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);  //@DateTimeFormat注解用于将前端传递的日期字符串转换为LocalDate对象 指定传递过来的日期格式

}
