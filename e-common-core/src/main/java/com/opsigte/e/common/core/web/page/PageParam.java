package com.opsigte.e.common.core.web.page;

import lombok.Data;

import java.io.Serializable;



/**
 * <p> @ClassName: <i>PageParam<T></i></p>
 * <p> @Description: <i>统一分页入参</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/7/25 17:20</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
@Data
public class PageParam implements Serializable {
    private static final long serialVersionUID = 6296784387756421561L;

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 请求数量
     */
    private int pageSize;

    public PageParam() {
    }

    public PageParam(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
