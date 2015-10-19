package com.plug;
import com.apkplug.component.udesksdk.Udesk;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class SimpleBundle implements BundleActivator
{
	private ServiceRegistration mReg = null;
    private Udesk mUdeskImp=null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("BundleId为："+context.getBundle().getBundleId()+"的"+context.getBundle().getName()+"插件已经启动了");
        mUdeskImp=new UdeskImp(context.getAndroidContext());
        mReg=context.registerService(Udesk.class.getName(),  mUdeskImp, null);


    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
