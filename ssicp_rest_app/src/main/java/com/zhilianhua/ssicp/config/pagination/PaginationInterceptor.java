package com.zhilianhua.ssicp.config.pagination;

import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor implements Interceptor {
    private static final String PAGE_SQL_NAME_SUFFIX = "Count";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("PaginationInterceptor");
        Object[] args = invocation.getArgs();
        Executor executor = (Executor) invocation.getTarget();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        String id = mappedStatement.getId();
        if (getMethodResultType(id) != PageResultEntity.class) {
            return invocation.proceed();
        }
        MappedStatement countMappedStatement = mappedStatement.getConfiguration().getMappedStatement(id + PAGE_SQL_NAME_SUFFIX);
        long total = (long) executor.query(countMappedStatement, args[1], (RowBounds) args[2], (ResultHandler) args[3]).get(0);
        if (total == 0L) Arrays.asList(PageResultEntity.EMPTY);
        PageResultEntity pageResultEntity = PageResultEntity.build().total(total).data((List) invocation.proceed());
        return Arrays.asList(pageResultEntity);
    }

    // 根据方法名和参数类型获取方法对象
    private Class getMethodResultType(String methodId) {
        try {
            String className = methodId.substring(0, methodId.lastIndexOf("."));
            String methodName = methodId.substring(methodId.lastIndexOf(".") + 1);
            Class<?> clazz = Class.forName(className);
            Optional<Method> any = Arrays.stream(clazz.getDeclaredMethods()).filter(method -> method.getName().equals(methodName)).findAny();
            if (any.isPresent()) {
                return any.get().getReturnType();
            }
        } catch (Exception e) {
        }
        throw BusinessException.instance("mybatis拦截器异常");
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }
}
