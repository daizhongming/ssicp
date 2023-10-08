package com.zhilianhua.ssicp.utils;

import java.lang.reflect.Method;

public class ReflectUtils {
    private static final String CLASS_METHOD_JOINER = ".";

    public static String getInterfaceId(Method method) {
        return method.getDeclaringClass().getName() + CLASS_METHOD_JOINER + method.getName();
    }
}
