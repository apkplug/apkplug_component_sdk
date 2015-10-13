package com.plug;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class SimpleBundle implements BundleActivator
{
	//private IMaketionCardSDK mIMaketionCardSDK=null;

	private ServiceRegistration mReg = null;
    private LaunchPlugImp mLaunchPlugImp=null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("BundleId为："+context.getBundle().getBundleId()+"的"+context.getBundle().getName()+"插件已经启动了");
        mLaunchPlugImp=new LaunchPlugImp(context.getAndroidContext());
        mReg=context.registerService(LaunchPlug.class.getName(),  mLaunchPlugImp, null);


    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
