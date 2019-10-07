package com.opsigte.e.gateway.jwt.manager;


import com.opsigte.e.gateway.jwt.model.TokenModel;

/**
 * <p> @ClassName: <i>TokenManager</i></p>
 * <p> @Description: <i>对Token进行操作的接口</i></p>
 * <p> @Author: <i>zzh</i></p>
 * <p> @Created date: <i>2019/10/7 13:27</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
public interface TokenManager {


    /**
     * 创建一个token关联上指定用户
     * @param userId 加密的内容
     * @param signingKey 密钥
     * @param expiration 此token过期时间
     * @return
     */
    public TokenModel createTokenModel(String userId, String signingKey, Long expiration);

    /**
     * 检查token是否有效
     * @param model
     * @return 是否有效
     */
    public boolean checkToken(TokenModel model);


    /**
     * 从字符串中解析token 对象
     * @param authentication 加密后的字符串
     * @return
     */
    public TokenModel getToken(String authentication);


    /**
     * 清除token
     * @param userId 登录用户的id
     */
    public void deleteToken(String userId);

}
