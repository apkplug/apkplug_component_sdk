package com.gt;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.gt.plug.GtSDK;

public class SimpleBundle implements BundleActivator
{
	//private IMaketionCardSDK mIMaketionCardSDK=null;
	private GtSDK mGtSDK=null;
	private ServiceRegistration mReg = null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("BundleId为："+context.getBundle().getBundleId()+"的"+context.getBundle().getName()+"插件已经启动了");
    	mGtSDK=new GtSDKImp(context.getAndroidContext());
    	mReg=context.registerService(GtSDK.class.getName(), mGtSDK, null);

    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
