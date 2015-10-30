package com.apkplug.component;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.apkplug.app.DefaultProperty;
import org.apkplug.app.FrameworkFactory;
import org.apkplug.app.FrameworkInstance;
import org.apkplug.app.PropertyInstance;

public abstract class ApkplugApplication extends Application {


	private  static Context mContext;

	public void onCreate() {   

		Log.e("ProxyApplication", this.getApplicationContext().getPackageName());
		 super.onCreate();
		mContext=getApplicationContext();

		 try
	        {
			 	FrameworkFactory.getInstance().start(null, this, getApkplugProperty());

	        }
	        catch (Exception ex)
	        {
	            System.err.println("Could not create : " + ex);
	            ex.printStackTrace();

	        }



	}
	public FrameworkInstance getFrame(){
		return FrameworkFactory.getInstance().getFrame();
	}
	public static Context getContext() {
		return mContext;
	}
	public PropertyInstance getApkplugProperty()
	{
		return new DefaultProperty(this);
	}

}
