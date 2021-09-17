package com.example.zk_lock.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 * @author wls
 * @date 2021/1/15 0015 14:50
 * @desc 参数校验异常处理
 **/
@Slf4j
@RestControllerAdvice
public class ValidatorGlobalExceptionHandler {
    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public String handleValidatedException(Exception e) {
        if (e instanceof MissingServletRequestParameterException) {
            return MessageFormat.format("缺少参数{0}", ((MissingServletRequestParameterException) e).getParameterName());

        }

        if (e instanceof ConstraintViolationException) {
            // 单个参数校验异常
            Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) e).getConstraintViolations();
            if (CollUtil.isNotEmpty(sets)) {
                StringBuilder sb = new StringBuilder();
                sets.forEach(error -> {
                    if (error instanceof FieldError) {
                        sb.append(((FieldError) error).getField()).append(":");
                    }
                    sb.append(error.getMessage()).append(";");
                });
                String msg = sb.toString();
                return msg.substring(0, msg.length() - 1);
            }

        }

        if (e instanceof BindException) {
            // get请求的对象参数校验异常
            List<ObjectError> errors = ((BindException) e).getBindingResult().getAllErrors();
            String msg = getValidExceptionMsg(errors);
            return msg;
        }


        if (e instanceof MethodArgumentNotValidException) {
            // post请求的对象参数校验异常
            List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            return getValidExceptionMsg(errors);
        }
        return null;
    }

    private String getValidExceptionMsg(List<ObjectError> errors) {
        if (CollUtil.isNotEmpty(errors)) {
            StringBuilder sb = new StringBuilder();
            errors.forEach(error -> {
                if (error instanceof FieldError) {
                    sb.append(((FieldError) error).getField()).append(":");
                }
                sb.append(error.getDefaultMessage()).append(";");
            });
            String msg = sb.toString();
            msg = msg.substring(0, msg.length() - 1);
            return msg;
        }
        return null;
    }

}
