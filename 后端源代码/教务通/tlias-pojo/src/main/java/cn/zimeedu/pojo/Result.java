package cn.zimeedu.pojo;

import lombok.Data;

/**
 * 后端统一返回结果
 * 这种设计模式被称为“静态工厂方法模式”  封装对象创建过程：  提高可读性：  灵活性：  隐藏实现细节：
 */
@Data
public class Result {

    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据

    // 成功返回结果 无参数
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    // 成功返回结果 有参数
    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    // 失败返回结果
    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}