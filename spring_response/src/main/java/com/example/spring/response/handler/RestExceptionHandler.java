package com.example.spring.response.handler;

import com.example.spring.response.constant.ResultCode;
import com.example.spring.response.constant.ResultData;
import com.example.spring.response.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局统一异常处理
 * @author wls
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 自定义异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResultData<String> exception(BizException e) {
        log.error("全局异常信息 e={}", e.getMessage(), e);
        return ResultData.fail(e.getMessage());
    }
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error("全局异常信息 e={}", e.getMessage(), e);
        return ResultData.fail(ResultCode.RC500.getCode(),ResultCode.RC500.getMessage());
    }

}