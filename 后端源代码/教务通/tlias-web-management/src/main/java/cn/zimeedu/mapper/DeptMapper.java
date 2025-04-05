package cn.zimeedu.mapper;

import cn.zimeedu.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

// 操作部门表数据的Mapper接口 本来需要实现类
// 但是使用MyBatis的注解开发方式，不需要实现类，只需要声明接口即可
@Mapper
public interface DeptMapper {

    /**
     * 配置结果集映射，用于处理数据库字段名和实体类属性名不一致的情况。
     * 当使用 MyBatis 进行查询时，如果数据库表的字段名与实体类的属性名不匹配，
     * 可以通过 @Results 和 @Result 注解来显式指定映射关系，以便 MyBatis 能够正确地将查询结果封装到实体类对象中。
     *
     * 这里使用 @Results 注解来定义一组结果映射，其中包含多个 @Result 注解。
     * 每个 @Result 注解表示一个字段到属性的映射关系。
     */
//    @Results({
//            /**
//             * 定义数据库表中的 `create_time` 字段与实体类中的 `createTime` 属性的映射关系。
//             * `column` 属性指定数据库表中的字段名。
//             * `property` 属性指定实体类中的属性名。
//             */
//            @Result(column = "create_time",property = "createTime"),
//            /**
//             * 定义数据库表中的 `update_time` 字段与实体类中的 `updateTime` 属性的映射关系。
//             * `column` 属性指定数据库表中的字段名。
//             * `property` 属性指定实体类中的属性名。
//             */
//            @Result(column = "update_time",property = "updateTime")
//    })

    List<Dept> findAll();

    void delete(Integer id);

    void insert(Dept dept);

    Dept getById(Integer id);

    void updateById(Dept user);
}

//mybatis规则:
// 实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装
// 如果实体类名 和 数据库表查询返回字段名不一致，不能自动封装 返回null