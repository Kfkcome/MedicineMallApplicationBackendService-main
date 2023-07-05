package pers.ervinse.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.ervinse.domain.User;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * 生成 token
     *
     * @param user token 包含的信息
     * @return token
     */
    public String generateToken(User user) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 1000);
        return Jwts.builder()
                .claim("userId", user.getUserID())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析 token
     *
     * @param token token
     * @return token 中包含的信息
     */
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public User parseTokenToEmployee(String token) {
        Claims claims = parseToken(token);
        User user = new User();
        user.setUserID(Integer.valueOf(claims.get("userId").toString()));
        return user;
    }

    /**
     * 从 token 中获取指定的值
     *
     * @param token token
     * @param key   值的 key
     * @param clazz 值的类型
     * @return 指定的值
     */
    public <T> T getClaimFromToken(String token, String key, Class<T> clazz) {
        Claims claims = parseToken(token);
        Object value = claims.get(key);
        return value == null ? null : clazz.cast(value);
    }
}
