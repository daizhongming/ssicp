package com.zhilianhua.ssicp.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static final String USER_NAME_KEY = "userName";
    public static final String NICK_NAME_KEY = "nickName";
    public static final String TOKEN_KEY = "token";

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    public static String getClientIp() {
        HttpServletRequest request = getRequest();
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    public static String getToken() {
        HttpServletRequest request = getRequest();
        return request.getHeader(TOKEN_KEY);
    }

    public static String getUserName() {
        HttpServletRequest request = getRequest();
        return request.getHeader(USER_NAME_KEY);
    }

    public static String getNickName() {
        HttpServletRequest request = getRequest();
        return request.getHeader(NICK_NAME_KEY);
    }
}
