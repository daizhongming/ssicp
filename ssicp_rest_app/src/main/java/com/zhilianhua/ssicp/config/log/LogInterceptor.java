package com.zhilianhua.ssicp.config.log;

import com.zhilianhua.ssicp.async.HttpInterceptorLogAsyncSaver;
import com.zhilianhua.ssicp.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(1)
public class LogInterceptor {
    @Autowired
    private HttpInterceptorLogAsyncSaver httpInterceptorLogAsyncSaver;

    @Pointcut("execution(public * com.zhilianhua.ssicp.controller*..*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMappingPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMappingPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMappingPointcut() {
    }

    @Pointcut("controllerPointcut() && (postMappingPointcut() || putMappingPointcut() || deleteMappingPointcut())")
    public void finalPointcut() {
    }

    @Around("finalPointcut()")
    public Object aroundControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("LogInterceptor start");
        long start = System.currentTimeMillis();
        Object response = null;
        Throwable error = null;
        try {
            response = proceedingJoinPoint.proceed();
            return response;
        } catch (Throwable e) {
            error = e;
            throw e;
        } finally {
            // 设置花费时间
            httpInterceptorLogAsyncSaver.save(System.currentTimeMillis() - start,
                    RequestUtils.getClientIp(),
                    RequestUtils.getUserName(),
                    RequestUtils.getNickName(),
                    ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod(),
                    proceedingJoinPoint.getArgs(),
                    response,
                    error
            );
            log.debug("LogInterceptor end");
        }
    }
}
