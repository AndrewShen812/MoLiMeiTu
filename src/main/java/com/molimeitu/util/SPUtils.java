/** 
 * @项目名称：ParkingApp   
 * @文件名：SharedPreferenceUtils.java    
 * @版本信息：
 * @日期：2015-4-1    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.molimeitu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @项目名称：ParkingApp
 * @类名称：SharedPreferenceUtils
 * @类描述：文件存储工具类
 * @创建人：lianfeng
 * @创建时间：2015-4-1 上午9:36:23
 * @修改人：lianfeng
 * @修改时间：2015-4-1 上午9:36:23
 * @修改备注：
 * @version
 * 
 */
public class SPUtils {
    /**
     * 登陆缓存文件
     */
	public static final String LOGIN_CACHE = "login_cache";

	private SharedPreferences mSp = null;

	private Editor mEdit = null;

	private static SPUtils mInstance;

	public static SPUtils getNewInstance(Context mContext, String filename) {
		if (null != mInstance) {
			mInstance = null;
		}
		mInstance = new SPUtils(mContext, filename);

		return mInstance;
	}

	private SPUtils(Context mContext, String filename) {
		this.mSp = mContext.getSharedPreferences(filename, Context.MODE_MULTI_PROCESS);
		this.mEdit = this.mSp.edit();
	}

	// 保存
	public void setValue(String key, String value) {
		mEdit.putString(key, value);
		mEdit.commit();
	}

	public void setValue(String key, int value) {
		mEdit.putInt(key, value);
		mEdit.commit();
	}

	public void setValue(String key, boolean value) {
		mEdit.putBoolean(key, value);
		mEdit.commit();
	}

	public void setValue(String key, float value) {
		mEdit.putFloat(key, value);
		mEdit.commit();
	}

	public void setValue(String key, long value) {
		mEdit.putLong(key, value);
		mEdit.commit();
	}
	
	// 获取
	public String getValue(String key, String defValue) {
		return mSp.getString(key, defValue);
	}

	public int getValue(String key, int defValue) {
		return mSp.getInt(key, defValue);
	}

	public boolean getValue(String key, boolean defValue) {
		return mSp.getBoolean(key, defValue);
	}

	public float getValue(String key, float defValue) {
		return mSp.getFloat(key, defValue);
	}

	public long getValue(String key, long defValue) {
		return mSp.getLong(key, defValue);
	}
}
