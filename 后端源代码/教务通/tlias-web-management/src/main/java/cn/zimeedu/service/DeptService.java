package cn.zimeedu.service;

import cn.zimeedu.pojo.Dept;

import java.util.List;

// 部门 业务层接口
public interface DeptService {

    /**
     * 查询所有部门数据
     * */
    List<Dept> findAll();

    void delete(Integer id);

    void insert(Dept dept);

    Dept getById(Integer id);

    void updateById(Dept user);
}
