package com.gt;

import android.content.Context;
import android.content.Intent;

import com.apkplug.component.geetestsdk.GeeTest;
import com.apkplug.component.geetestsdk.PlugGtListener;
import com.geetest.sdk.GeetestLib;


public class GeeTestImp extends GeeTest{

	private Context mContext=null;
	public static String mCaptcha_id;
	public static String mUrl;
	
	public static PlugGtListener mGtListener=null;
	public static GeetestLib mGeetestLib = new GeetestLib();
	
	
	public GeeTestImp(Context context) {
		mContext=context;
	}

	public void setCaptcha(String captcha_id){

		mCaptcha_id=captcha_id;

	}
	public void geeTest(String url,PlugGtListener plugGtListener){
		mGtListener=plugGtListener;
		mUrl=url;
		mContext.startActivity(new Intent(mContext, TransparentActivity.class));
		
	}

	@Override
	public int versionInPlug() {
		// TODO Auto-generated method stub
		return 1;
	}

}
