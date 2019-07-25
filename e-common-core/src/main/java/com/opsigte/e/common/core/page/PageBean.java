package com.opsigte.e.common.core.page;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * <p> @ClassName: <i>PageBean<T></i></p>
 * <p> @Description: <i>统一返回分页对象</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/7/25 17:20</i></p>
 * <p> @Version: <i>V1.0</i> </p>
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
