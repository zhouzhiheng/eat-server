package com.opsigte.e.common.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> @ClassName: <i>BaseEntity</i></p>
 * <p> @Description: <i>基础实体类,包含各实体公用属性 </i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/2 16:06</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -3288580916798489604L;

    /**
     * 主键id，自增
     */
    private Long id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据库版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updatedTime;


}
