package com.activity;

import org.apkplug.Bundle.OSGIServiceAgent;
import org.apkplug.Bundle.Throwable.ApkplugThrowable;
import org.apkplug.Bundle.Throwable.ApkplugThrowableListener;
import org.apkplug.app.FrameworkFactory;
import org.apkplug.app.FrameworkInstance;
import org.osgi.framework.BundleContext;

import com.apkplug.component.ComponentManager;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ProxyApplication extends Application {


	private  static Context mContext;
	private FrameworkInstance frame=null;
	public FrameworkInstance getFrame() {
		return frame;
	}

	public void onCreate() {   

		Log.e("ProxyApplication", this.getApplicationContext().getPackageName());
		 super.onCreate();
		mContext=getApplicationContext();

		 try
	        {
			 	frame=FrameworkFactory.getInstance().start(null, this,new DefaultProperty(this));
				final BundleContext context =frame.getSystemBundleContext();
				ComponentManager.getInstance().onInit(context);
				OSGIServiceAgent<ApkplugThrowableListener> agent=new OSGIServiceAgent<ApkplugThrowableListener>(context,ApkplugThrowableListener.class);
				try {
					agent.getService().registerOnThrowableListener(
							new ApkplugThrowable(){
								@Override
								public void onThrowable(Throwable e) {
									//出现未捕获的异常是，apkplug框架将回调本函数
									e.printStackTrace();
									Toast.makeText(ProxyApplication.this, "出现异常了:"+e.getMessage(), Toast.LENGTH_LONG).show();
								}
						
					});
				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

				
	        }
	        catch (Exception ex)
	        {
	            System.err.println("Could not create : " + ex);
	            ex.printStackTrace();

	        }

	}
	public static Context getContext() {
		return mContext;
	}
	

}
