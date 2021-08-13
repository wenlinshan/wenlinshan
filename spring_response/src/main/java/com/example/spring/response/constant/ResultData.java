package com.example.spring.response.constant;

import lombok.Data;

/**
 * 自定义结果包装类
 *
 * @author wls
 */
@Data
public class ResultData<T> {

    private int status;
    private String message;
    private T data;
    private long timestamp;


    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 有结果返回值操作成功
     *
     * @param data 数据
     * @param <T>  泛型
     * @return 包装结果
     */
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ResultCode.RC200.getCode());
        resultData.setMessage(ResultCode.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    /**
     * 无结果返回值操作成功
     *
     * @param <T>  泛型
     * @return 包装结果
     */
    public static <T> ResultData<T> success() {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ResultCode.RC200.getCode());
        resultData.setMessage(ResultCode.RC200.getMessage());
        return resultData;
    }

    /**
     * 有状态码的失败
     *
     * @param code    状态码
     * @param message 失败消息
     * @param <T>     泛型
     * @return 包装失败
     */
    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

    /**
     * 无状态码的失败
     *
     * @param message 失败消息
     * @param <T>     泛型
     * @return 包装失败
     */
    public static <T> ResultData<T> fail( String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ResultCode.RC999.getCode());
        resultData.setMessage(message);
        return resultData;
    }

}