package com.etoak.common.web.context;

import com.etoak.common.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * 登录信息
 */
public class LoginContext {

    public static String getUserId(){
        HttpServletRequest request = getRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Map<String, Object> claimsMap = JwtUtil.parse(token);
        return claimsMap.get("id").toString();
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }
}
