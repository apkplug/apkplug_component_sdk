package com.apkplug.plug;
import com.apkplug.component.maketionsdk.MaketionCard;
import com.apkplug.plug.imp.MaketionCardImp;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;



public class SimpleBundle implements BundleActivator
{
	private MaketionCard mMaketionCard=null;
	private ServiceRegistration mReg = null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("你好我是"+context.getBundle().getName()+"插件,我已经启动了 我的BundleId为："+context.getBundle().getBundleId());
    	mMaketionCard=new MaketionCardImp(context.getAndroidContext());
    	mReg=context.registerService(MaketionCard.class.getName(), mMaketionCard, null);
    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
