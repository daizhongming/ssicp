package com.zhilianhua.ssicp.utils;

import java.util.UUID;

public class IdUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("\\-", "");
    }
}
