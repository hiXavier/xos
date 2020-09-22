package com.xavier.xos.api.domain;

/**
 * @Author wuyanfeng
 * @Description
 * @Date 2019/8/12 11:42
 */
public enum ResultEnum {
    /**
     * 一切OK
     */
    SUCCESS(1, "成功"),
    /**
     * 客户端专用
     */
    SUCCESS_CLIENT(0, "成功"),
    FAILURE_CLIENT(1, "失败"),
    /**
     * code以1开头，错误来源于用户
     * 101xx
     */
    USER_REGISTER_ERROR(10001, "用户端错误"),
    USER_NAME_CHECK_ERROR(10110, "用户名校验失败"),
    USER_NAME_EXIST(10111, "用户名已存在"),

    /**
     * 102XX-二级宏观错误码
     */
    USER_LOGIN_ERROR(10200, "用户登录异常"),


    /**
     * 103XX-二级宏观错误码
     */
    USER_PERMISSION_ERROR(10300, "访问权限异常"),
    USER_NO_PERMISSION(10301, "当前用户暂无权限"),
    USER_QUERY_EMPTY(10301, "暂无数据"),


    /**
     * 104XX-二级宏观错误码
     */
    USER_PARAMETER_ERROR(10400, "用户请求参数错误"),
    USER_PARAMETER_ESSENTIAL(10410, "请求必填参数为空"),
    USER_PARAMETER_DUPLICATE(10411, "数据已存在"),

    /**
     * 105XX-二级宏观错误码
     */
    USER_REQUEST_ERROR(10500, "用户请求服务异常"),
    USER_REQUEST_REPEAT(10506, "用户请求重复"),


    /**
     * code以2开头，错误来源于当前系统
     */


    /**
     * code以3开头，错误来源于第三方
     */
    ;

    ResultEnum(Integer value, String msg) {
        this.code = value;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private Integer code;
    private String msg;


}
