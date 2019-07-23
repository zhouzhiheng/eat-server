package com.opsigte.e.common.core.page;

import lombok.Data;

import java.io.Serializable;

/**
 * @Project: com.opsigte.eat.common.core.page
 * @Class: PageParam
 * @Description: 分页入参
 * @Author: opsigte
 * @Date: 2019/7/16 16:09
 * @version 1.0.0
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
