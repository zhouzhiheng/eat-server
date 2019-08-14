package com.opsigte.e.user.api.entity;

import com.opsigte.e.common.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p> @ClassName: <i>EUserEntity</i></p>
 * <p> @Description: <i>e_user实体类</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/14 15:00</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EUserEntity extends BaseEntity implements Serializable {

    /**
     * 用户账号id，从100544开始自增的数字
     */
    private Integer accountId;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 登录密码
     */
    private String loginPassword;

    /**
     * 支付密码
     */
    private String payPassword;

    /**
     * 用户状态。1-正常，2-冻结
     */
    private Boolean status;

    /**
     * 用户昵称，字母加数字组合，默认自动生成
     */
    private String nickName;

    /**
     * 用户头像id
     */
    private Integer headImgId;

    /**
     * 用户头像地址
     */
    private String headImgUrl;

    /**
     * 开关。第一位代表是否需要支付密码，1-需要，0-不需要；
     */
    private String isSettingSwitch;

    /**
     * 注册来源，1-平台注册；2-邀请注册；3-QQ；4-微信；5-支付宝
     */
    private Boolean source;

    /**
     * 邀请码，大写字母加数字，由系统生成。
     */
    private String promoCode;

    private static final long serialVersionUID = 1L;


}