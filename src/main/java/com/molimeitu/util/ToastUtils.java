/** 
 * @项目名称：ParkingApp   
 * @文件名：ToastUtils.java    
 * @版本信息：
 * @日期：2015-3-26    
 * @Copyright 2015 www.517na.com Inc. All rights reserved.         
 */
package com.molimeitu.util;

import android.content.Context;
import android.widget.Toast;


/**    
 *     
 * @项目名称：ParkingApp    
 * @类名称：ToastUtils    
 * @类描述：    
 * @创建人：lianfeng    
 * @创建时间：2015-3-26 下午4:10:44    
 * @修改人：lianfeng    
 * @修改时间：2015-3-26 下午4:10:44    
 * @修改备注：    
 * @version     
 *     
 */
public class ToastUtils {
	
	public static void showToast(Context context, String msgContent) {
		Toast.makeText(context, msgContent, Toast.LENGTH_SHORT).show();
	}
}
