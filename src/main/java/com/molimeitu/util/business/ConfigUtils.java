package com.molimeitu.util.business;

import android.content.Context;

import com.molimeitu.consts.AppConfig;
import com.molimeitu.util.Base64Utils;
import com.molimeitu.util.SPUtils;

/**
 * 项目名称：LianfengApp
 * 类描述：UserUtils
 * 创建人：lianfeng
 * 创建时间：2015/7/3 16:06
 * 修改人：lianfeng
 * 修改时间：2015/7/3 16:06
 * 修改备注：
 * 版本：V1.0
 */
public class ConfigUtils {

    public static void setUserInfo(Context context, String userInfo) throws Exception {
        SPUtils sp = SPUtils.getNewInstance(context, AppConfig.APP_CACHE_FILE);
        sp.setValue(AppConfig.KEY_USER_INFO, Base64Utils.encode(userInfo.getBytes()));
    }

    public static String getUserInfo(Context context) throws Exception {
        SPUtils sp = SPUtils.getNewInstance(context, AppConfig.APP_CACHE_FILE);
        return new String(Base64Utils.decode(sp.getValue(AppConfig.KEY_USER_INFO, "")));
    }

    public static void setOrderInfo(Context context, String orderInfo) throws Exception {
        SPUtils sp = SPUtils.getNewInstance(context, AppConfig.APP_CACHE_FILE);
        sp.setValue(AppConfig.KEY_ORDER_INFO, Base64Utils.encode(orderInfo.getBytes()));
    }

    public static String getOrderInfo(Context context) throws Exception {
        SPUtils sp = SPUtils.getNewInstance(context, AppConfig.APP_CACHE_FILE);
        return new String(Base64Utils.decode(sp.getValue(AppConfig.KEY_ORDER_INFO, "")));
    }
}
