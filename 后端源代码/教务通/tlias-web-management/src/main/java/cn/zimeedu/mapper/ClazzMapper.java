package cn.zimeedu.mapper;

import cn.zimeedu.pojo.Clazz;
import cn.zimeedu.pojo.ClazzQueryParam;
import cn.zimeedu.pojo.Emp;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 操作部门表数据的Mapper接口 本来需要实现类
// 但是使用MyBatis的注解开发方式，不需要实现类，只需要声明接口即可
@Mapper  // 标识接口为 MyBatis 的映射器（Mapper）接口
public interface ClazzMapper {

    // 基于PageHelper分页插件 不用管分页语句   pagehelper插件已经处理了分页语句 所以不用传递分页参数
    Page<Clazz> list(ClazzQueryParam clazzQueryParam);
    // 插入班级数据
    void insert(Clazz clazz);
    // 删除班级
    void delById(Integer id);
    // 查询回显 根据ID查询班级
    Clazz getById(Integer id);
    // 修改班级数据
    void update(Clazz clazz);
    // 查询所有班级信息
    List<Clazz> listAll();
}
