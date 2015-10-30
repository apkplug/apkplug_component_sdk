package com.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import org.apkplug.app.PropertyInstance;

/**
 * 默认的配置文件
 * @author 梁前武
 * www.apkplug.com
 * v2.0.0
 */
public class MyProperty implements PropertyInstance{
	private   Context context;
	public MyProperty(Context context){
		this.context=context;
	    //这是默认方式 与百度推送不兼容
		this.setProperty(Start_Service_for_Init, "true");
		// 不启用异常隔离机制
		this.setProperty("Open_Crash_Container", "false");
	}
	public String getProperty(String key) {
		// TODO Auto-generated method stub
		SharedPreferences sharedata = PreferenceManager.getDefaultSharedPreferences(this.context);
		String data = sharedata.getString(key, null);
		return data;
	}
	public void setProperty(String key, String v) {
		// TODO Auto-generated method stub
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.context); 
		Editor edit = settings.edit();
		edit.putString(key, v);
		edit.commit();
	}
	public String[] AutoInstall() {
		// TODO Auto-generated method stub
		return null;
	}
	public String[] AutoStart() {
		return null;
	}

	@Override
	public boolean Debug() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
