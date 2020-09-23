package com.xavier.xos.web.handler;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/23 15:01
 */

import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author wuyanfeng
 * @Description
 * @Date 2020/1/20 11:20
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    Result<Object> handleException(DuplicateKeyException e) {
        Result<Object> result = Result.get(ResultEnum.USER_PARAMETER_DUPLICATE);
        log.error("DuplicateKeyException-{}", e.getMessage());
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    Result<Object> handleException(HttpMessageNotReadableException e) {
        Result<Object> result = Result.get(ResultEnum.USER_PARAMETER_ERROR);
        log.error("HttpMessageNotReadableException-{}", e.getMessage());
        return result;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    Result<Object> handleException(MethodArgumentNotValidException e) {
        Result<Object> result = Result.get(ResultEnum.USER_PARAMETER_ERROR);
        log.error("MethodArgumentNotValidException-{}", e.getBindingResult().getFieldError().getDefaultMessage());
        result.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
        return result;
    }

    @ExceptionHandler(AccessDeniedException.class)
    Result<Object> handleException(AccessDeniedException e) {
        return Result.get(ResultEnum.USER_NO_PERMISSION);
    }

    @ExceptionHandler(Exception.class)
    Result<Object> handleException(Exception e) {
        Result<Object> result = Result.get(ResultEnum.USER_REQUEST_ERROR);
        log.error("exception-{}", e);
        return result;
    }

}
