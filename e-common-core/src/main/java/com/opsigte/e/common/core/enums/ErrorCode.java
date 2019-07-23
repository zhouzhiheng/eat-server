package com.opsigte.e.common.core.enums;

/**
 * @Project: com.opsigte.e.common.core.enums
 * @Class: ErrorCode
 * @Description: 统一返回状态码和message
 * @Author: opsigte
 * @Date: 2019/7/23 22:50
 * @version 1.0.0
 */
public enum ErrorCode {
    SYSTEM_ERROR(500, "系统错误"),
    ;

    private final Integer value;
    private final String message;

    ErrorCode (int value,String message) {
        this.value = value;
        this.message = message;
    }
    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String getCode() {
        return value.toString();
    }

    public static ErrorCode getByCode(Integer value) {
        for (ErrorCode _enum : values()) {
            if (_enum.getValue() == value) {
                return _enum;
            }
        }
        return null;
    }
}
