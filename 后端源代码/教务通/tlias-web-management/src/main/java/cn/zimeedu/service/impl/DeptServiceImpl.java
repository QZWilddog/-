package cn.zimeedu.service.impl;

import cn.zimeedu.mapper.DeptMapper;
import cn.zimeedu.pojo.Dept;
import cn.zimeedu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service // 将Service层的实现类交给IOC 容器管理
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {

        return deptMapper.findAll();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
    }

    @Override
    public void insert(Dept dept) {
        // 数据库没有默认的值，所以需要 补 全基础属性 createTime updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        // 调用mapper层的方法插入数据
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {


        return deptMapper.getById(id);
    }

    @Override
    public void updateById(Dept user) {
        // 补 全基础属性
        user.setUpdateTime(LocalDateTime.now());
        // 调用mapper层的方法
        deptMapper.updateById(user);
    }
}
