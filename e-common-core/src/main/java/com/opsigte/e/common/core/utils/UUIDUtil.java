package com.opsigte.e.common.core.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * <p> @ClassName: <i>UUIDUtil</i></p>
 * <p> @Description: <i>UUID生成工具类</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/17 18:26</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
public class UUIDUtil {

    /**
     * TODO 根据当前时间生成20长度的订单号yyyyMMddHHmmssSSS+3
     * @return
     * String
     */
    public static String orderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(Calendar.getInstance().getTime()) + (new Random().nextInt(900) + 100);
    }

    /**
     * 使用 UUID生成唯一的 TraceId
     * @return
     */
    public static String generatorUUID(){
        return UUID.randomUUID().toString();
    }


    /**
     * 去掉UUID中的 '-',返回 32位的UUID，主要是为了调试方便复制
     * @return
     */
    public static String generatorTraceId(){
        String uuid = generatorUUID();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }
}
