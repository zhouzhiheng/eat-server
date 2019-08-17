package com.opsigte.e.common.core.filter;

import com.alibaba.dubbo.rpc.*;
import com.opsigte.e.common.core.utils.StringUtil;
import com.opsigte.e.common.core.utils.TraceIdUtil;

/**
 *<p> @ClassName: <i>TraceFilter</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/17 18:32</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class TraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment("traceId");
        if (!StringUtil.isEmpty(traceId) ) {
            // *) 从RpcContext里获取traceId并保存
            TraceIdUtil.setTraceId(traceId);
        } else {
            // *) 交互前重新设置traceId, 避免信息丢失
            RpcContext.getContext().setAttachment("traceId", TraceIdUtil.getTraceId());
        }
        // *) 实际的rpc调用
        return invoker.invoke(invocation);
    }
}
