package com.opsigte.e.common.core.web.filter;

import com.alibaba.dubbo.rpc.*;
import com.opsigte.e.common.core.constant.CommonConstant;
import com.opsigte.e.common.core.utils.StringUtil;
import com.opsigte.e.common.core.utils.TraceIdUtil;
import org.slf4j.MDC;

/**
 *<p> @ClassName: <i>TraceFilter</i></p>
 *<p> @Description: <i>自定义一个dubbo filter，每rpc一次就将traceId设置到 RpcContext的setAttachment中</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/17 18:32</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class TraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment(CommonConstant.TRACEIDKEY);
        if (!StringUtil.isEmpty(traceId) ) {
            // *) 从RpcContext里获取traceId并保存
            TraceIdUtil.setTraceId(traceId);
            // 注意：此处需要同时设置到MDC中，不然此次rpc如果在不同项目中，这个项目的日志中就不会拿到traceId
            MDC.put(CommonConstant.TRACEIDKEY, traceId);
        } else {
            // *) 交互前重新设置traceId, 避免信息丢失
            RpcContext.getContext().setAttachment(CommonConstant.TRACEIDKEY, TraceIdUtil.getTraceId());
        }
        // *) 实际的rpc调用
        return invoker.invoke(invocation);
    }
}
