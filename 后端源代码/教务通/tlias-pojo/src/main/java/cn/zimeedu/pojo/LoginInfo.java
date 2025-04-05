package cn.zimeedu.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    // 封装登陆结果

    private Integer id;
    private String username;
    private String name;
    private String token;
}
