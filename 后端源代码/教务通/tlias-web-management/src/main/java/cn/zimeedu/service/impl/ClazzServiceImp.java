package cn.zimeedu.service.impl;

import cn.zimeedu.mapper.ClazzMapper;
import cn.zimeedu.pojo.Clazz;
import cn.zimeedu.pojo.ClazzQueryParam;
import cn.zimeedu.pojo.PageResult;
import cn.zimeedu.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service  // 通过使用 @Service，可以明确表明这个类属于业务逻辑层。
public class ClazzServiceImp implements ClazzService {

    @Autowired // 自动装配
    private ClazzMapper clazzMapper;


    @Override  // 分页查询
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 设置分页参数    它的作用是设置当前查询的分页参数，包括当前页码（page）和每页显示的记录数（pageSize）。
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 调用Mapper方法查询数据  返回值类型 Page<Clazz> 是 MyBatis 分页插件提供的一个封装类，它不仅包含了查询结果（List<Clazz>），还包含了分页相关的元数据（如总记录数、总页数等）。
        Page<Clazz> page = clazzMapper.list(clazzQueryParam);

        // 判断是否开课
        LocalDate now = LocalDate.now(); // 获取当前日期  默认格式 2025-03-21
        // 从page对象中获取查询结果列表
        List<Clazz> clazzList = page.getResult();
        // 遍历列表  判断日期  设置状态
        clazzList.stream().forEach(clazz -> {
            if (clazz.getBeginDate().isAfter(now)){
                clazz.setStatus("未开课");
            }else if(clazz.getEndDate().isBefore(now)){
                clazz.setStatus("已结课");
            }else {
                clazz.setStatus("已开课");
            }
        });

//        page.getTotal()：获取符合条件的总记录数。  page.getResult()：获取当前页的具体记录列表。
        return new PageResult<>(page.getTotal(), clazzList);
    }
    // 保存班级数据
    @Transactional(rollbackFor = Exception.class) // 开启事务管理  任何异常都回滚事务  默认只有运行时异常 RuntimeException才会回滚
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.insert(clazz);
    }

    // 根据ID删除班级
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id){
        clazzMapper.delById(id);
    }

    // 根据Id查询班级  查询回显
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Clazz getById(Integer id){
        Clazz clazz = clazzMapper.getById(id);

        return clazz;
    }

    // 修改班级信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Clazz clazz){
        // 修改更新日期
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    // 查询所有班级信息
    @Override
    public List<Clazz> list(){

        return clazzMapper.listAll();
    }
}
