package com.zhilianhua.ssicp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateTests {
    @Test
    public void testDate() {
        LocalDate now = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            log.debug("now:{}", now.minusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
    }
    @Test
    public void test(){
        Date date = new Date();
        long l = date.toInstant().toEpochMilli();
        log.debug("l:{}",l);
        log.debug("l:{}",date.getTime());
    }
}
