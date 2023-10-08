package com.zhilianhua.ssicp.config.authorization;

import com.zhilianhua.ssicp.utils.JwtUtils;
import com.zhilianhua.ssicp.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

@Component
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(RequestUtils.TOKEN_KEY);
        MyHttpServletRequestWrapper MyHttpServletRequestWrapper = new MyHttpServletRequestWrapper(request);
        if (StringUtils.isNoneBlank(token)) {
            Optional<JwtUtils.JwtUser> jwtUser = JwtUtils.getUser(token);
            jwtUser.ifPresent(user -> {
                MyHttpServletRequestWrapper.setHeader(RequestUtils.USER_NAME_KEY, user.getUserName());
                MyHttpServletRequestWrapper.setHeader(RequestUtils.NICK_NAME_KEY, user.getNickName());
            });
        }
        filterChain.doFilter(MyHttpServletRequestWrapper, servletResponse);
    }

    static class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final Map<String, String> modifiedHeaders;

        public MyHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            modifiedHeaders = new HashMap<>();
        }

        public void setHeader(String name, String value) {
            modifiedHeaders.put(name, value);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> headerNames = Collections.list(super.getHeaderNames());
            headerNames.addAll(modifiedHeaders.keySet());
            return Collections.enumeration(headerNames);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = modifiedHeaders.get(name);
            if (headerValue != null) {
                return headerValue;
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> headerValues = Collections.list(super.getHeaders(name));
            String modifiedValue = modifiedHeaders.get(name);
            if (modifiedValue != null) {
                headerValues.add(modifiedValue);
            }
            return Collections.enumeration(headerValues);
        }
    }
}
