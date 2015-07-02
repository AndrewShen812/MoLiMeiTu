package com.molimeitu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 项目名称：LianfengApp
 * 类描述：TimeUtils
 * 创建人：lianfeng
 * 创建时间：2015/7/2 16:09
 * 修改人：lianfeng
 * 修改时间：2015/7/2 16:09
 * 修改备注：
 * 版本：V1.0
 */
public class TimeUtils {

    /**
     * 获取当前时间
     * @param form 时间格式，可为空。默认格式："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getCurrentDateTime(String form) {
        String localform = form;
        if (StringUtils.isEmpty(localform)) {
            localform = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat time = new SimpleDateFormat(localform, Locale.getDefault());

        return time.format(new Date());
    }
}
