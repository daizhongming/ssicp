//package com.zhilianhua.ssicp.config.authorization;
//
//import com.zhilianhua.ssicp.privilege.GroupEnum;
//import com.zhilianhua.ssicp.privilege.PrimitiveExceptionEnum;
//import com.zhilianhua.ssicp.privilege.Privilege;
//import com.zhilianhua.ssicp.service.PrivilegeService;
//import com.zhilianhua.ssicp.service.UserService;
//import com.zhilianhua.ssicp.utils.ReflectUtils;
//import com.zhilianhua.ssicp.utils.RequestUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Objects;
//
//@Aspect
//@Component
//@Slf4j
//@Order(2)
//public class AuthorizationInterceptor {
//    @Autowired
//    private PrivilegeService privilegeService;
//    @Autowired
//    private UserService userService;
//
//    @Before("execution(* com.zhilianhua.ssicp.controller.*.*(..))")
//    public void beforeControllerMethod(JoinPoint joinPoint) {
//        log.debug("AuthorizationInterceptor start");
//        // 获取当前请求的上下文
//        String userName = RequestUtils.getUserName();
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        Privilege annotation = method.getAnnotation(Privilege.class);
//        // 公开接口直接放行
//        if (Objects.nonNull(annotation) && annotation.group() != GroupEnum.PUBLIC) {
//            // 获取Header信息
//            if (StringUtils.isBlank(userName)) {
//                throw PrimitiveExceptionEnum.JWT_ERROR.getException();
//            }
//            // root用户直接放行
//            if (!StringUtils.equals(userService.getRootName(), userName)) {
//                String interfaceId = ReflectUtils.getInterfaceId(method);
//                if (!privilegeService.hasPrivilege(userName, interfaceId)) {
//                    // 无权访问
//                    throw PrimitiveExceptionEnum.NO_PRIMITIVE.getException();
//                }
//            }
//        }
//    }
//}
