package com.zhilianhua.ssicp.exception;

import com.zhilianhua.ssicp.utils.RequestUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handler(BusinessException exception) {
        log.error("GlobalExceptionHandler:{}", exception);
        return ResponseEntityUtils.error(exception.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity handler(MissingRequestHeaderException exception) {
        log.error("GlobalExceptionHandler:{}", exception);
        String headerName = exception.getHeaderName();
        switch (headerName) {
            case RequestUtils.USER_NAME_KEY:
                return ResponseEntityUtils.error("请重新登录");
            default:
                return ResponseEntityUtils.error(exception.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handler(MethodArgumentNotValidException exception) {
        log.error("GlobalExceptionHandler:{}", exception);
        String message = exception.getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntityUtils.error(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handler(Exception exception) {
        log.error("GlobalExceptionHandler:{}", exception);
        return ResponseEntityUtils.error("服务器内部异常");
    }
}
