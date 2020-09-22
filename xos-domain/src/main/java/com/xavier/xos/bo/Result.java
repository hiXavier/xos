package com.xavier.xos.bo;

import com.xavier.xos.enumeration.ResultEnum;

import java.io.Serializable;

/**
 * @Author wuyanfeng
 * @Description 自定义的返回结果类
 * @Date 2019/8/12 11:16
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 7911619720129407579L;
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;
    public static final String MESSAGE = "成功";

    private Integer code;
    private String message;
    private T data;


    public Result() {
        code = SUCCESS;
        message = MESSAGE;
    }

    public Result(T data) {
        code = SUCCESS;
        this.data = data;
    }

    public Result(T data, ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
        this.data = data;
    }

    public Result(T data, Integer code, String message) {
        code = SUCCESS;
        this.data = data;
    }

    public Result(Throwable e) {
        code = ERROR;
        this.message = e.getMessage();
    }

    public boolean success() {
        return code == SUCCESS;
    }

    public Result<T> getResultFromEnum(T t, ResultEnum resultEnum) {
        return new Result(t, resultEnum);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setEnum(ResultEnum resultEnum) {
        this.setCode(resultEnum.getCode());
        this.setMessage(resultEnum.getMsg());
    }

    public static Result get(ResultEnum resultEnum) {
        Result result = new Result();
        result.setEnum(resultEnum);
        return result;
    }

    public void setSuccessData(T data) {
        this.setEnum(ResultEnum.SUCCESS);
        this.setData(data);
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + ", data=" + data + "]";
    }

}
