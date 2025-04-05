package cn.zimeedu.service.impl;

import cn.zimeedu.mapper.StudentMapper;
import cn.zimeedu.pojo.PageResult;
import cn.zimeedu.pojo.Student;
import cn.zimeedu.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service //  声明业务类
public class StudentServiceImpl implements StudentService {

    // 自动装配
    @Autowired
    private StudentMapper studentMapper;

    // 分页查询
    @Override
    public PageResult<Student> page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        Page<Student> studentList = studentMapper.page(name, degree, clazzId);

        return new PageResult<>(studentList.getTotal(), studentList.getResult());
    }
    //删除学员
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delById(List<Integer> ids){
        studentMapper.delById(ids);
    }
    //添加学员
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Student student){
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }
    //根据id查询学员
    @Override
    public Student getInfo( Integer id){
        return studentMapper.getInfo(id);
    }
    // 修改学员
    @Override
    public void update(Student student){
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }
    //处理学员违纪
    @Override
    public void updateScore(Integer id, Integer score){
        studentMapper.updateScore(id, score);
    }
}
