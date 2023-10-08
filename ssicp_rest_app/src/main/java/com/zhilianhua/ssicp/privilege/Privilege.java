package com.zhilianhua.ssicp.privilege;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Privilege {
    String value();
}
