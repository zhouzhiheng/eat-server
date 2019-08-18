package com.opsigte.e.common.core.utils;

/**
 *<p> @ClassName: <i>TraceIdUtil</i></p>
 *<p> @Description: <i>dubbo服务 traceId链路追踪工具类</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/17 18:18</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class TraceIdUtil {

    private static final ThreadLocal<String> TRACEABLE = new ThreadLocal<String>();


    /**
     * 必须经过rpc调用后，才可以取值。如 controller中就获取不到在 WebLogAop中存入的值
     * @return
     */
    public static String getTraceId(){
        return TRACEABLE.get();
    }

    public static void setTraceId(String traceId){
        TRACEABLE.set(traceId);
    }

    public static void clear(){
        TRACEABLE.remove();
    }


}
