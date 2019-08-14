package com.opsigte.e.common.core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p> @ClassName: <i>MD5Encoder</i></p>
 * <p> @Description: <i>MD5 编码/解码</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/14 11:26</i></p>
 * <p> @Version: <i>V1.0</i> </p>
 */
abstract class AbstractMd5Encoder {

    private static final String KEY_ALGORITHM = "MD5";

    /**
     * TODO MD5加密，返回32位加密结果
     * @param sourceStr
     * @return
     * @throws Exception
     * String
     */
    public static String MD5(String sourceStr) throws Exception{
        if ("".equals(sourceStr) || sourceStr.isEmpty()) {
            return "";
        }
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_ALGORITHM);
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        return result;
    }
}
