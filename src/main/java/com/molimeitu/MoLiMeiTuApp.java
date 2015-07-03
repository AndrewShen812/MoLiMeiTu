package com.molimeitu;

import android.app.Application;

/**
 * 项目名称：LianfengApp
 * 类描述：MoLiMeiTuApp
 * 创建人：lianfeng
 * 创建时间：2015/7/3 10:24
 * 修改人：lianfeng
 * 修改时间：2015/7/3 10:24
 * 修改备注：
 * 版本：V1.0
 */
public class MoLiMeiTuApp extends Application {

    private static MoLiMeiTuApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static MoLiMeiTuApp getInstance() {
        return mInstance;
    }
}
