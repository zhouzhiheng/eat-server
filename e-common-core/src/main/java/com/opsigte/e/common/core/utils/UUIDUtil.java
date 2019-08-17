package com.opsigte.e.common.core.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

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
}
