package com.zhilianhua.ssicp.utils;

import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {
    public static ResponseEntity success() {
        return ResponseEntity.ok().build();
    }

    public static ResponseEntity success(Object object) {
        return ResponseEntity.ok(object);
    }

    public static ResponseEntity error() {
        return ResponseEntity.status(500).build();
    }

    public static ResponseEntity error(Object object) {
        return ResponseEntity.status(500).body(object);
    }
}
