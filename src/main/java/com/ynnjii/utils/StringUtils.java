package com.ynnjii.utils;

import java.util.List;

/**
 * 项目名称：网升OA
 * 类描述：字符串
 * 创建人：yzh
 * 创建时间：2017/5/25
 * 备注：
 */
public class StringUtils {

    /**
     * 判断空
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.length() == 0;
    }

    /**
     * 判断空
     * @param list
     * @return
     */
    public static boolean isNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }

}
