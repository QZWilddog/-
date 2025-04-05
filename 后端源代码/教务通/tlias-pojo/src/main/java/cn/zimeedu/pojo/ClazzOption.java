package cn.zimeedu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzOption {
    // 班级统计数据公用对象类

    private List<Object> clazzList; // 班级列表
    private List<Object> dataList; // 数据列表
}
