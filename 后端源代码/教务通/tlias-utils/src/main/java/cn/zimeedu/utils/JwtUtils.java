package cn.zimeedu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY = "emltZWVkdQ=="; // // 创建一个 JwtBuilder对象 指定加密算法和密钥 密钥要是base64编码的字符串 zimeedu
    private static final long EXPIRATION_TIME = 24 * 3600 * 1000;  // 12个小时的时间戳类型

    /**
     * 生成JWT令牌
     * @param claims 令牌中包含的信息
     * @return 生成的JWT令牌字符串
     */

    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder() //构建JWT令牌
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 指定签名算法（加密算法，密钥）
                .addClaims(claims)  // 添加自定义信息  是一个map集合
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 指定有效期（指定日期时间类型 ）
                .compact();  // 生成令牌
    }

    /**
     * 解析JWT令牌
     * @param token 要解析的JWT令牌字符串
     * @return 包含令牌信息的Claims对象
     * @throws Exception 如果令牌无效或者已过期，抛出异常
     * */

    public static Claims parseToken(String token) throws Exception {
        // 创建 JwtParser 对象，用于解析 JWT 令牌
        return Jwts.parser() // 解析
                // 设置解析 JWT 令牌时使用的签名密钥，确保令牌的完整性和真实性
                .setSigningKey(SECRET_KEY)
                // 解析传入的 JWT 令牌字符串，验证签名并返回 Jws<Claims> 对象
                .parseClaimsJws(token)
                // 从 Jws<Claims> 对象中提取出 JWT 令牌的负载部分，即 Claims 对象
                .getBody(); // Jws<Claims>是自定义信息部分 本质是一个Map集合 {"id":"1","username":"Tom" “Exp”：12323 }

    }

}
