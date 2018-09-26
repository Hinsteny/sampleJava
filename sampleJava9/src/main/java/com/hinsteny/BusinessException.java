package com.hinsteny;

/**
 * @author Hinsteny
 * @date 2017-10-28
 * @copyright: 2017 All rights reserved.
 */
public class BusinessException extends Exception {

    /**
     * 异常码
     */
    String code;

    /**
     * 异常信息
     */
    String message;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                "} " + super.toString();
    }
}
