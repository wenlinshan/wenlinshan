package com.example.spring.response.exception;

import com.example.spring.response.constant.ResultCode;

/**
 * 自定义义务异常
 * @author wls
 */
public class BizException extends RuntimeException {

    public ResultCode resultCode;

    public BizException(String message) {
        super(message);
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
