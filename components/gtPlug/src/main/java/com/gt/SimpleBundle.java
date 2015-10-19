package com.gt;
import com.apkplug.component.geetestsdk.GeeTest;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SimpleBundle implements BundleActivator
{

	private GeeTest mGeeTest=null;
	private ServiceRegistration mReg = null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("BundleId为："+context.getBundle().getBundleId()+"的"+context.getBundle().getName()+"插件已经启动了");
        mGeeTest=new GeeTestImp(context.getAndroidContext());
    	mReg=context.registerService(GeeTest.class.getName(), mGeeTest, null);

    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
