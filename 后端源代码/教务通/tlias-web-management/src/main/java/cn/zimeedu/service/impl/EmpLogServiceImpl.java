package cn.zimeedu.service.impl;

import cn.zimeedu.mapper.EmpLogMapper;
import cn.zimeedu.pojo.EmpLog;
import cn.zimeedu.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    //记录新增员工日志
    @Transactional(propagation = Propagation.REQUIRES_NEW)  // 在一个新的事务中运行 所以这个方法单独被事务控制
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}