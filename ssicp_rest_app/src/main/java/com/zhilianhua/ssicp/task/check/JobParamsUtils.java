package com.zhilianhua.ssicp.task.check;

import com.alibaba.fastjson2.JSON;
import org.quartz.JobDataMap;

public class JobParamsUtils<T> {

    public static <T> T parse(JobDataMap jobDataMap, Class<T> tClass) {
        T t = JSON.parseObject(JSON.toJSONString(jobDataMap), tClass);
        if (t instanceof JobParamsChecker) {
            ((JobParamsChecker) t).check();
        }
        return t;
    }

    public static <T> void check(JobDataMap jobDataMap, Class<T> tClass) {
        T t = JSON.parseObject(JSON.toJSONString(jobDataMap), tClass);
        if (t instanceof JobParamsChecker) {
            ((JobParamsChecker) t).check();
        }
    }
}
