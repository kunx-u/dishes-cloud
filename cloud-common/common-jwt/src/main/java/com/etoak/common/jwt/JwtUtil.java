package com.etoak.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {


    /**
     * 256bit = 32字符 * 8bit
     */
    private static String SECRET_KEY = "11111111111111111111111111111111";

    private static Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private static long EXPIRE = 100*60*60*12;
    /**
     * 创建JWT
     * @param claimsMap 自定义
     * @return
     */
    public static String create(Map<String,Object> claimsMap){
        Date now = new Date();
        return Jwts.builder()
                .signWith(key) //签名密钥
                .setIssuedAt(now)//签发时间
                .setExpiration(new Date(now.getTime() + EXPIRE)) // 过期时间
                .setClaims(claimsMap) //自定义payload
                .compact();
    }
}
