package com.zhilianhua.ssicp.utils;

import com.alibaba.fastjson2.JSON;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class DateUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";

    private static final String HOUR_AND_MINUTE_CRON_EXPRESSION_TEMPLATE = "0 %d %d * * ?";

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_TIME);
        return sdf.format(date);
    }

    // 最近n个月
    public static Date monthsBefore(int m, Date stop) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stop);
        calendar.add(Calendar.MONTH, -1 * m);
        return calendar.getTime();
    }

    // 最近n天
    public static Date daysBefore(int m, Date stop) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stop);
        calendar.add(Calendar.DAY_OF_YEAR, -1 * m);
        return calendar.getTime();
    }

    public static String parseCronExpressions(String inspectionTimes) {
        return JSON.parseArray(inspectionTimes, String.class).stream().map(inspectionTime -> {
            String[] split = inspectionTime.split(":");
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            return String.format(HOUR_AND_MINUTE_CRON_EXPRESSION_TEMPLATE, minute, hour);
        }).collect(Collectors.joining(";"));
    }
}
