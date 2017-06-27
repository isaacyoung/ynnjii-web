package com.ynnjii.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 项目名称：车到山前后台
 * 类描述：
 * 创建人：yzh
 * 创建时间：2017/6/12
 * 备注：
 */
public class PropertyUtils {
    private final Logger logger = LogManager.getLogger(PropertyUtils.class);
    private Properties props;

    public void loadProps() {
        loadProps("config.properties");
    }

    public void loadProps(String config) {
        logger.info("开始加载 config.properties.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtils.class.getClassLoader().getResourceAsStream(config);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("config.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("config.properties文件流关闭出现异常");
            }
        }
        logger.info("加载 config.properties 完成...........");
    }

    public String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

}
