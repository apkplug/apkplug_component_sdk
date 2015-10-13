package com.apkplug.plug;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.apkplug.component.maketionsdk.IMaketionCardSDK;
import com.apkplug.plug.imp.MaketionCardSDKImp;



public class SimpleBundle implements BundleActivator
{
	private IMaketionCardSDK mIMaketionCardSDK=null;
	private ServiceRegistration mReg = null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("你好我是"+context.getBundle().getName()+"插件,我已经启动了 我的BundleId为："+context.getBundle().getBundleId());
    	mIMaketionCardSDK=new MaketionCardSDKImp(context.getAndroidContext());
    	mReg=context.registerService(IMaketionCardSDK.class.getName(), mIMaketionCardSDK, null);
    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
