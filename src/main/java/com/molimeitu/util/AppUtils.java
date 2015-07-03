package com.molimeitu.util;

import android.content.Context;
import android.content.pm.PackageManager;

import com.molimeitu.MoLiMeiTuApp;
import com.molimeitu.R;

/**
 * 项目名称：LianfengApp
 * 类描述：AppUtils
 * 创建人：lianfeng
 * 创建时间：2015/6/30 15:33
 * 修改人：lianfeng
 * 修改时间：2015/6/30 15:33
 * 修改备注：
 * 版本：V1.0
 */
public class AppUtils {
    private static Context mContext = MoLiMeiTuApp.getInstance();

    private static boolean isFirstGet = true;

    private static boolean isUserLogin = false;

    public static String getAppName() {
        return mContext.getResources().getString(R.string.app_name);
    }

    /**
     * 获取版本名称
     * @return
     */
    public static String getVersionName() {
        String versionName = "";
        try {
            versionName = mContext.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            return versionName;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取版本号
     * @return
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = mContext.getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            return versionCode;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取包名
     * @return
     */
    public static String getPackageName() {
        if (mContext != null) {
            return mContext.getPackageName();
        }
        return "lf.parking";
    }
}
