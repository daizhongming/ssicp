package com.zhilianhua.ssicp.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public static BusinessException instance(String message) {
        return new BusinessException(message);
    }
}
