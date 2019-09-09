package com.opsigte.e.common.core.web.response;

/**
 * <p> @ClassName: <i>ErrorCode<T></i></p>
 * <p> @Description: <i>统一返回状态码和message</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/7/25 17:20</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
public enum ErrorCode {
    SYSTEM_ERROR(500, "系统错误"),
    RPC_ERROR(501, "rpc调用失败"),

    NO_HANDLER_MAPPING_ERROR(404, "路径不存在"),
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
