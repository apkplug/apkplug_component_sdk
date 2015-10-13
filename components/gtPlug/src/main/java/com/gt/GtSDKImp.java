package com.gt;

import java.util.Map;


import android.content.Context;
import android.content.Intent;
import com.geetest.sdk.GeetestLib;
import com.gt.plug.GtSDK;
import com.gt.plug.PlugGtListener;

public class GtSDKImp implements GtSDK{

	private Context mContext=null;
	public static String mCaptcha_id;
	public static String mUrl;
	
	public static PlugGtListener mGtListener=null;
	public static GeetestLib mGeetestLib = new GeetestLib();
	
	
	public GtSDKImp(Context context) {
		mContext=context;
	}

	public void setCaptchaId(String captcha_id){

		mCaptcha_id=captcha_id;

	}
	public void GtTest(String url,PlugGtListener plugGtListener){
		mGtListener=plugGtListener;
		mUrl=url;
		mContext.startActivity(new Intent(mContext, TransparentActivity.class));
		
	}

	@Override
	public int version() {
		// TODO Auto-generated method stub
		return 1;
	}

}
