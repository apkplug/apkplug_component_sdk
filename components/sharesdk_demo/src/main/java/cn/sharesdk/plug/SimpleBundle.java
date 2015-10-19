package cn.sharesdk.plug;

import com.apkplug.component.sharesdk.ShareSdk;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class SimpleBundle implements BundleActivator
{

	private ServiceRegistration mReg = null;
    private ShareSdk mShareSdkImp=null;

    public void start(BundleContext context) throws Exception
    {
    	System.err.println("BundleId为："+context.getBundle().getBundleId()+"的"+context.getBundle().getName()+"插件已经启动了");
        mShareSdkImp=new ShareSdkImp(context.getAndroidContext());
        mReg=context.registerService(ShareSdk.class.getName(),  mShareSdkImp, null);


    }
   
    public void stop(BundleContext context)
    {

    	mReg.unregister();
    }

}
