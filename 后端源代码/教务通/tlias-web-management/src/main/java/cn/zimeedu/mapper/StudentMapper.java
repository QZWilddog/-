package cn.zimeedu.mapper;

import cn.zimeedu.pojo.Student;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// 它的作用是告诉 MyBatis 和 Spring 框架，这个接口需要被扫描并动态生成实现类，以便与数据库交互。
// 使用 @Mapper 注解可以明确标识某个接口是一个 MyBatis 的 Mapper 接口
@Mapper // 标识接口为 MyBatis 的映射器（Mapper）接口
public interface StudentMapper {
    // 分页查询
    Page<Student> page(String name, Integer degree, Integer clazzId);
    //删除学员信息
    void delById(List<Integer> ids);
    //添加学员信息
    void add(Student student);
    //根据id查询学员
    Student getInfo(Integer id);
    //修改学员
    void update(Student student);
    // 处理学员信息
    void updateScore(Integer id, Integer score);
    //统计各个班级人数
    @MapKey("clazz")            //  @MapKey 的设计初衷就是用某一列的值作为 Map 的键。你只需要指定一个字段作为键即可，其他字段会自动成为值的一部分。   "班主任": {"pos": "班主任", "num": 5}, 所以这里是误报 因为我这里返回的是List集合
    List<Map<String, Object>> getStudentCountData();   // 如果返回值是一个Map集合 需要用@MapKey注解指定Map的key  这里返回是List 是插件误报
    // 统计学员学历
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();
}
