package com.opsigte.e.common.config.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * @Project: com.opsigte.e.common.config.utils
 * @Class: PublicConfigUtil
 * @Description: 加载 common_config.properties配置文件
 * @Author: opsigte
 * @Date: 2019/7/23 22:46
 * @version 1.0.0
 */
public class PublicConfigUtil {
    private static final Log LOG = LogFactory.getLog(PublicConfigUtil.class);
    private static Properties properties = new Properties();

    private PublicConfigUtil() {
    }

    public static String readConfig(String key) {
        return (String)properties.get(key);
    }

    static {
        try {
            properties.load(PublicConfigUtil.class.getClassLoader().getResourceAsStream("common_config.properties"));
        } catch (Exception var1) {
            LOG.error("加载public_system.properties文件异常" + var1);
        }

    }
}
