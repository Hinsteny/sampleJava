package com.hinsteny;

/**
 * @author Hinsteny
 * @date 2017-10-28
 * @copyright: 2017 All rights reserved.
 */
public enum Status {

    SUCCESS("S0000", "成功"),
    EXCEPTION("E0000", "系统异常"),
    EXCEPTION_NET("E0001", "网络请求异常"),
    EXCEPTION_DATA("E0002", "请求数据异常"),
    FAILED("F9999", "失败");


    private String code;
    private String msg;

    Status(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                "} " + super.toString();
    }
}
