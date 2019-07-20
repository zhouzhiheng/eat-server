package com.opsigte.eat.common.core.page;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Project: com.opsigte.eat.common.core.page
 * @Class: PageBean
 * @Description: 分页对象
 * @Author: opsigte
 * @Date: 2019/7/16 23:01
 * @version 1.0.0
 */
@Data
public class PageBean<T> implements Serializable {
    private static final long serialVersionUID = 2046352945524278277L;

    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 请求数量
     */
    private Integer numPerPage;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总数量
     */
    private Long total;
    /**
     * 数据
     */
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息
     */
    public static <T> PageBean<T> restPage(List<T> list) {
        PageBean<T> result = new PageBean<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setCurrentPage(pageInfo.getPageNum());
        result.setNumPerPage(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }
}
