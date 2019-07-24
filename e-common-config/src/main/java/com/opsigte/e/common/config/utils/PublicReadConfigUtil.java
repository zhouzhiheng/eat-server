package com.opsigte.e.common.config.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @Project: com.opsigte.e.common.config.utils
 * @Class: PublicReadConfigUtil
 * @Description: 加载 common_config.properties配置文件
 * @Author: opsigte
 * @Date: 2019/7/23 22:46
 * @version 1.0.0
 */
public class PublicReadConfigUtil {

    private static final Logger log = LoggerFactory.getLogger(PublicReadConfigUtil.class);

    private static Properties properties = new Properties();

    private PublicReadConfigUtil() {
    }

    public static String readConfig(String key) {
        return (String)properties.get(key);
    }

    static {
        try {
            properties.load(PublicReadConfigUtil.class.getClassLoader().getResourceAsStream("common_config.properties"));
        } catch (Exception var1) {
            log.error("加载public_system.properties文件异常" + var1);
        }

    }
}
