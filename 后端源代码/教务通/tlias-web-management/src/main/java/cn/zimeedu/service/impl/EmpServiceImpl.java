package cn.zimeedu.service.impl;

import cn.zimeedu.mapper.EmpExprMapper;
import cn.zimeedu.mapper.EmpMapper;
import cn.zimeedu.pojo.*;
import cn.zimeedu.service.EmpLogService;
import cn.zimeedu.service.EmpService;
import cn.zimeedu.utils.CurrentHolder;
import cn.zimeedu.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// 员工工作经历不用单独创建，因为他是员工信息的附属信息 可以一起创建
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;  // 案例 记录事务到数据库中 无论什么异常都要记录日志

    // 修改员工
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        // 先根据员工ID删除老的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        // 在添加
        List<EmpExpr> exprList = emp.getExprList();
        // 过滤掉某些成员变量为空的对象     filter() 方法 对流中的元素进行过滤，只保留满足指定条件的元素
        exprList = exprList.stream()
                .filter(empExpr -> {
                    // 假设需要检查的成员变量为 var1 和 var2
                    return empExpr.getBegin() != null && empExpr.getJob() != null && empExpr.getCompany() != null;
                })
                .collect(Collectors.toList()); //  把流中的元素收集到一个 List 集合中 Collectors.toList() 方法

        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insecrtBatch(exprList);
        }

    }

    @Override
    public LoginInfo login(Emp emp) {
        // 调用mapper接口，根据用户名和密码查询员工信息  login是一个业务操作，而且mapper接口只是用户数据库查询 所以要做到见名知意 就叫 selectByUsernameAndPassword
        Emp e =  empMapper.selectByUsernameAndPassword(emp);
        // 判断是否存在这个员工，如果存在组装成功信息
        if (e != null){
            log.info("登陆成功，员工信息为：{}", e);
            // 生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());  // 这个是唯一用户标识 其它无所谓
            claims.put("usernmae", e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        // 不存在返回null
        return null;
    }

    // 查询回显
    @Override
    public Emp getInfo(Integer id) {

        return empMapper.getById(id);

    }

    // 删除员工
    @Transactional(rollbackFor = {Exception.class}) // 添加事务控制  rollbackFor指定需要回滚的异常类型  出现任何异常都要回滚，否则默认回滚运行时异常
    @Override
    public void delete(List<Integer> ids){
        // 1.批量删除员工基本信息  起名字简明之意
        empMapper.deleteByIds(ids);
        // 2. 批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    // 新增员工
    @Override
    @Transactional(rollbackFor = {Exception.class}) // 添加事务控制  rollbackFor指定需要回滚的异常类型
    public void save(Emp emp) {
        try {
            // 保存这个员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 保存这个员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) { //  CollectionUtils 是Spring框架提供的一个工具类，位于 org.springframework.util 包下。它提供了一系列静态方法，用于操作和处理集合对象，旨在简化集合操作的代码，提高开发效率。下面详细介绍
                // 遍历emp集合 给这个员工的员工经历赋值逻辑外键 emp_id
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                System.out.println(exprList);
                empExprMapper.insecrtBatch(exprList);  // Batch 表示批量插入 因为命名要见名知意
            }
        } finally {
            // 记录新增员工日志   无论什么异常都要记录日志(因为这个方法被事务控制了，所以发生异常 事务会回滚这里也回滚) 所以在这个在一个新的事务中运行 所以这个service方法单独被事务控制
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }


    }
    // 分页查询
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        Page<Emp> p = empMapper.page(empQueryParam);

        return new PageResult<Emp>(p.getTotal(), p.getResult()); // 返回总记录数和结果列表方法
    }
    // 查询全部员工数据
    @Override
    public List<Emp> list(){
        List<Emp> empList = empMapper.list();

        return empList;
    }


//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize , String name, Integer gender, LocalDate begin, LocalDate end) {
////======================基于PageHelper分页插件==========================
//        //1. 设置分页参数
//        PageHelper.startPage(page, pageSize);
//
//        //2. 执行查询  这里调用mapper方法时，会对查询的sql进行拦截 进行改写 改写成调用方法的sql语句并执行 然后返回结果
//        Page<Emp> p = empMapper.list(name, gender, begin, end);  //  pagehelper改写了sql   并使mapper返回Page类型 所以可以强转
//
////        Page<Emp> p = (Page<Emp>) empList;  //  因为page继承了 ArrayList ArrayList实现了List 所以可以强转
//
//        //3. 封装结果
//        return new PageResult<Emp>(p.getTotal(), p.getResult());// 返回总记录数和结果列表方法

//=====================原始的分页查询===============================
//        //1. 获取总记录数
//        Long total = empMapper.count();
//
//        //2. 获取结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.list(start, pageSize);
//
//        //3. 封装结果
//        return new PageResult<Emp>(total, empList);
}


