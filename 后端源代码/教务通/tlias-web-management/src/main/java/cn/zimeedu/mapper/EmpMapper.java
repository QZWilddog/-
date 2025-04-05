package cn.zimeedu.mapper;


import cn.zimeedu.pojo.Emp;
import cn.zimeedu.pojo.EmpQueryParam;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 员工信息

@Mapper
public interface EmpMapper {

    // 统计员工性别人数
    @MapKey("name")
    List<Map<String, Object>> countEmpSexData();

    // 统计员工职位人数
    @MapKey("pos")                            //  @MapKey 的设计初衷就是用某一列的值作为 Map 的键。你只需要指定一个字段作为键即可，其他字段会自动成为值的一部分。   "班主任": {"pos": "班主任", "num": 5}, 所以这里是误报 因为我这里返回的是List集合
    List<Map<String, Object>> countEmpJobData();  // 如果返回值是一个Map集合 需要用@MapKey注解指定Map的key  这里返回是List 是插件误报

    // 基于PageHelper分页插件  不用管分页语句   pagehelper插件已经处理了分页语句 所以不用传递分页参数
    public Page<Emp> page(EmpQueryParam empQueryParam);

    // 使用xml配置sql xml里面也要在标签内写入这个配置
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 获取到生成的主键 -- 主键返回   @Options 注解用来对SQL语句的执行行为进行配置，它能让开发者在方法层面精确控制MyBatis的一些特性，比如主键生成、缓存行为、结果集类型等。
    void insert(Emp emp);

    // 根据ID批量删除员工的基本信息
    void deleteByIds(List<Integer> ids);

    // 查询回显
    Emp getById(Integer id);

    // 根据id更新员工基本信息
    void updateById(Emp emp);

    Emp selectByUsernameAndPassword(Emp emp);

    // 查询所有员工信息
    List<Emp> list();

    // =============原始的分页查询============================
//    // 查询总记录数
//    public Long count();
//
//    // 查询分页数据
//    public List<Emp> list(Integer statr, Integer pageSize);

    // 基于springboot官方骨架创建的项目 这里传递多个形参 可以直接写形参名 不用注解  基于官方会保留字节码名
    // 如果传入多个参数 要为每个参数起名字
    // 明：基于官方骨架创建的springboot项目中，接口编译时会保留方法形参名，@Param注解可以省略 (#{形参名})。 因为有配置maven插件 configuration编译插件
}
