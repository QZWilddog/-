package cn.zimeedu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
* 班级实体类
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private Integer id; //ID
    private String name; //班级名称
    private String room; //班级教室
    private LocalDate beginDate; //开课时间
    private LocalDate endDate; //结课时间
    private Integer masterId; //班主任
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    // 班主任名称
    private String masterName;
    // 课程状态
    private String status;
    // 学科
    private Integer subject;
}


//mybatis规则:
// 实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装
// 如果实体类名 和 数据库表查询返回字段名不一致，不能自动封装 返回null