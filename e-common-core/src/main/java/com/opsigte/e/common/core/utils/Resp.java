package com.opsigte.e.common.core.utils;

import com.opsigte.e.common.core.enums.ErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;


/**
 * <p> @ClassName: <i>Resp<T></i></p>
 * <p> @Description: <i>Restful统一JSON响应封装类</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/7/25 17:20</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
@Data
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = 1468749391542344308L;
    private final static String SUCCESS_CODE = "200";

    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    /**
     * 其他内容
     */
    private Map<String, Object> ext;

    public Resp(){
        this.code = SUCCESS_CODE;
        this.message = "成功";
    }

    public Resp(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Resp(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Resp(String code, String message, T data, Map<String, Object> ext) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.ext = ext;
    }

    public static <T>Resp<T> success(){
        return new Resp<T>(SUCCESS_CODE,"Successful",null);
    }

    public static <T>Resp<T> success(T result){
        return new Resp<T>(SUCCESS_CODE,"Successful",result);
    }

    public static <T>Resp<T> success(String message, T result){
        return new Resp<T>(SUCCESS_CODE,message,result);
    }

    public static <T>Resp<T> success(String message, T result, Map<String, Object> extra){
        return new Resp<T>(SUCCESS_CODE,message,result, extra);
    }


    public static <T>Resp<T> fail(){
        return new Resp<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage());
    }

    public static <T>Resp<T> fail(T result){
        return new Resp<T>(ErrorCode.SYSTEM_ERROR.getCode(),ErrorCode.SYSTEM_ERROR.getMessage(),result);
    }

    public <T>Resp<T> fail(String message, T result){
        return new Resp<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result);
    }

    public <T>Resp<T> fail(String message, T result, Map<String, Object> extra){
        return new Resp<T>(ErrorCode.SYSTEM_ERROR.getCode(),message,result, extra);
    }

    public static <T>Resp<T> fail(ErrorCode errorCode){
        return new Resp<T>(errorCode.getCode(),errorCode.getMessage());
    }

    public static <T>Resp<T> fail(ErrorCode errorCode, T result){
        return new Resp<T>(errorCode.getCode(),errorCode.getMessage(),result);
    }

    public static <T>Resp<T> fail(ErrorCode errorCode, String message, T result){
        return new Resp<T>(errorCode.getCode(),message,result);
    }

    public static <T>Resp<T> fail(ErrorCode errorCode, String message, T result, Map<String, Object> extra){
        return new Resp<T>(errorCode.getCode(),message,result, extra);
    }

    public static <T>Resp<T> result(String statusCode, String message){
        return new Resp<T>(statusCode,message);
    }

    public static <T>Resp<T> result(String statusCode, String message, T result){
        return new Resp<T>(statusCode,message,result);
    }

    public static <T>Resp<T> result(String statusCode, String message, T result, Map<String, Object> extra){
        return new Resp<T>(statusCode,message,result, extra);
    }

}
