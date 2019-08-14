package com.opsigte.e.common.core.security;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

/**
 * <p> @ClassName: <i>PasswordManage</i></p>
 * <p> @Description: <i>密码加密管理</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/14 11:12</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
public class PasswordManage {
    public static final int SALT_BYTE_SIZE = 24;

	/*public static void main(String[] args) {
		try {
			boolean bo = verifyPassword("7D9CB2E9A604CEAD565F37E005314073", "sha1:64000:18:Z2oyCWAC80L4jB8gwGshy64Wmm6vg5GI:9GBIPA7m743AxbACktUeUzq3");
			System.out.println(bo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

    /**
     * @Title: encrypt
     * @Description: TODO 密码加密Hash
     * @param @param password
     * @param @return
     * @param @throws Exception    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String encrypt(String password) throws Exception{
        try {
            return PasswordStorage.createHash(password);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @Title: verifyPassword
     * @Description: TODO 验证密码
     * @param @param password  密码
     * @param @param correctHash 原始加密Hash
     * @param @return
     * @param @throws Exception    设定文件
     * @return boolean    返回类型
     * @throws
     */
    public static boolean verifyPassword(String password, String correctHash) throws Exception {
        return PasswordStorage.verifyPassword(password, correctHash);
    }


    /**
     * @Title: encrypt
     * @Description: TODO 将Hash MD5加密
     * @param @param hash
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String MD5(String hash, String salt) throws Exception {
        try {
            return AbstractMd5Encoder.MD5(new StringBuffer().append(hash).append(salt).toString());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @Title: getSalt
     * @Description: TODO 获取随机密钥(盐)
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public static String randomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return toBase64(salt);
    }

    private static String toBase64(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }
}
