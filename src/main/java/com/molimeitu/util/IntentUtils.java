package com.molimeitu.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.Settings;

import com.molimeitu.R;


/**
*   描述：          界面跳转
*   @creator       genie@517na.com     
*   @create-time   2014-3-13   下午6:10:47     
*   @revision      1.0      
*/ 
public final class IntentUtils {
	
	/** 拨打电话  */
	public static void call(Context context , String phoneNum) {
		Uri uri = Uri.parse("tel:"+phoneNum);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/** 进入发短信界面 */
	public static void sendSMS(Context context , String content) {
		Uri uri = Uri.parse("smsto:");
		Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
		intent.putExtra("sms_body", content);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	/** 跳转到网络设置 */
	public static void settingNetwork(Context mContext) {
		Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
		mContext.startActivity(intent);
	}
	
	/** 
     * 打开GPS 
     * @param context 
     */  
    public static void openGPS(Context context) {  
    	Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }  
    
    /**
     * @description 为程序创建桌面快捷方式
     * @date 2015-6-15
     * @param context 上下文
     * @param backClass
     * @param iconResId 图标资源ID
     */
    public static  void addShortcut(Activity context,Class<? extends Activity> backClass, int iconResId){
    	Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    		
    	//快捷方式的名称
    	shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getString(R.string.app_name));
    	shortcutIntent.putExtra("duplicate", false); //不允许重复创建
       
    	//指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
    	//注意: ComponentName的第二个参数必须加上点号(.)，否则快捷方式无法启动相应程序
    	shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, context.getString(R.string.app_name));
		Parcelable icon = Intent.ShortcutIconResource.fromContext(context, iconResId);
		shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		
		//下面两个属性是为了当应用程序卸载时桌面 上的快捷方式会删除
		Intent intent = new Intent(context, backClass);
		intent.setAction("android.intent.action.MAIN");  
        intent.addCategory("android.intent.category.LAUNCHER");
        
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        context.sendBroadcast(shortcutIntent);
    }
    
    /**
     * 
     * @description 判断是否已经存在快捷方式
     * @date 2015年4月27日 
     * @param 
     * @return boolean
     * @Exception
     */
	public static boolean hasShortcut(Context context) {
		boolean isInstallShortcut = false;
		final ContentResolver cr = context.getContentResolver();
		final String AUTHORITY = "com.android.launcher.settings";
		final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
		Cursor c = cr.query(CONTENT_URI, new String[] { "title", "iconResource" }, "title=?", new String[] { context.getString(R.string.app_name)
				.trim() }, null);
		if (c != null && c.getCount() > 0) {
			isInstallShortcut = true;
		}
		return isInstallShortcut;
	}

    /**
     * 调用系统联系人
     * @param requestCode
     */
    public static void openContact(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        activity.startActivityForResult(intent, requestCode);
    }
}
