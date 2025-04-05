package cn.zimeedu.mapper;


import cn.zimeedu.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    // 批量插入员工工作经历信息
    void insecrtBatch(List<EmpExpr> exprList);

    // 根据ID批量删除员工工作经历的基本信息
    void deleteByEmpIds(List<Integer> emdIds);
}
