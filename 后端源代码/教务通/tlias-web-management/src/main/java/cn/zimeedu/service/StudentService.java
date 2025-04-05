package cn.zimeedu.service;

import cn.zimeedu.pojo.PageResult;
import cn.zimeedu.pojo.Student;

import java.util.List;

public interface StudentService {
    //分页查询
    PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);
    //删除学员
    void delById(List<Integer> ids);
    //添加学员
    void add(Student student);
    //id查询学员
    Student getInfo(Integer id);
    // 修改学员
    void update(Student student);
    // 处理学员违纪信息
    void updateScore(Integer id, Integer score);
}
