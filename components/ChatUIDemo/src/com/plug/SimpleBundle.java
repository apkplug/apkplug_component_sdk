package com.plug;

import com.apkplug.component.easemobimsdk.EaseMobIM;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class SimpleBundle implements BundleActivator
{


	private ServiceRegistration mReg = null;
    private EaseMobIM mEaseMobIMImp=null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("BundleId为："+context.getBundle().getBundleId()+"的"+context.getBundle().getName()+"插件已经启动了");
        mEaseMobIMImp=new EaseMobIMImp(context.getAndroidContext());
        mReg=context.registerService(EaseMobIM.class.getName(),  mEaseMobIMImp, null);


    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
