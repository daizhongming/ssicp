package com.zhilianhua.ssicp.async;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.FileInfo;
import com.zhilianhua.ssicp.entity.db.Log;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.LogService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/***
 * 拦截器中日志保存异步处理
 */
@Component
@Slf4j
public class HttpInterceptorLogAsyncSaver {
    @Autowired
    private LogService logService;

    @Async
    public void save(long costTime, String ip, String userName, String nickName, Method method, Object[] requestParams, Object response, Throwable error) {
        Log logEntity = new Log();
        // 设置ip
        logEntity.setIp(ip);
        // 设置用户相关
        logEntity.setCreateUserName(userName);
        logEntity.setCreateUserNickName(nickName);
        // 设置操作相关
        ReflectEntity reflect = reflect(method);
        logEntity.setClassTitle(reflect.classTitle);
        logEntity.setMethodTitle(reflect.methodTitle);
        // 设置请求响应
        List<Object> requestList = Arrays.stream(requestParams).map(arg -> {
            if (arg instanceof MultipartFile) {
                MultipartFile arg1 = (MultipartFile) arg;
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(arg1.getOriginalFilename());
                fileInfo.setSize(arg1.getSize());
                return fileInfo;
            }
            return arg;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        logEntity.setRequest(JSON.toJSONString(requestList));
        logEntity.setResponse(Objects.nonNull(response) ? JSON.toJSONString(response) : null);
        logEntity.setError(Objects.nonNull(error) ? error.getMessage() : null);
        // 设置花费时间
        logEntity.setCostTime(costTime);
        logService.add(logEntity);
    }

    @Data
    class ReflectEntity {
        private String classTitle;
        private String methodTitle;
    }

    public ReflectEntity reflect(Method method) {
        ReflectEntity reflectEntity = new ReflectEntity();
        Privilege methodAnnotation = method.getAnnotation(Privilege.class);
        reflectEntity.setMethodTitle(Objects.nonNull(methodAnnotation) ? methodAnnotation.value() : method.getName());
        Class<?> clazz = method.getDeclaringClass();
        Privilege classAnnotation = clazz.getAnnotation(Privilege.class);
        reflectEntity.setClassTitle(Objects.nonNull(classAnnotation) ? classAnnotation.value() : clazz.getSimpleName());
        return reflectEntity;
    }
}
