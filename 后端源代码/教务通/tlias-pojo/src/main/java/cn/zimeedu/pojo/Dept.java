package cn.zimeedu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * 部门 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

//mybatis规则:
// 实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装
// 如果实体类名 和 数据库表查询返回字段名不一致，不能自动封装 返回null