package cn.zimeedu.service;

import cn.zimeedu.pojo.Clazz;
import cn.zimeedu.pojo.ClazzQueryParam;
import cn.zimeedu.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    // 分页查询
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    // 保存班级数据
    void save(Clazz clazz);

    //
    void delete(Integer id);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    List<Clazz> list();
}
