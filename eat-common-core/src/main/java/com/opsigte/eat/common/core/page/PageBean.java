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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

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
