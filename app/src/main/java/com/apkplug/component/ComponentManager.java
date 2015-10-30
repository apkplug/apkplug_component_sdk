package com.apkplug.component;

import org.apkplug.Bundle.InstallBundler;
import org.apkplug.Bundle.OSGIServiceAgent;
import org.apkplug.Bundle.installCallback;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class ComponentManager{
	private static ComponentManager _instance=null;

	synchronized public static ComponentManager getInstance(){
	    if(_instance==null){
	    _instance=new ComponentManager();
	    }
	    return _instance;
	}
	private ComponentManager(){

	}
	private BundleContext bContext;
	private InstallBundler ib;
	public void onInit(BundleContext bContext){

		this.bContext=bContext;
		ib=new InstallBundler(bContext);

	}
	public void onDestroy(){

	}
	public void searchComponent(String componentName ,final ServerCallback callback) {



		searchComponent(ComponentFactory.getInstance().getComponent(componentName), callback);


	}
	public void searchComponent(final ComponentInfo componentInfo ,final ServerCallback callback){
		if (componentInfo==null) {
			callback.onFailure(0, "没有此插件");
		}else {
			callback.doGetClass();
			final OSGIServiceAgent agent=new OSGIServiceAgent(bContext,callback.clazz,OSGIServiceAgent.real_time);
			try {
				Base service = (Base) agent.getService();
//				if (service.version() >=service.versionInPlug()) {
//					Log.e(""," 插件sdk版本低于宿主sdk版本");
//					}
				if (service != null) {
					callback.onSuccess(service);
					return;
				}
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Bundle[] bs=bContext.getBundles();
			for(int i=0;i<bs.length;i++){
				if(bs[i].getSymbolicName().equals(componentInfo.getSymbolicName())){
					try {
						bs[i].start();
					} catch (BundleException e) {
						e.printStackTrace();
						//callback.onFailure(-1, ""+e.getMessage());
					}
					//再次查询支付Service
					try {
						Object service=agent.getService();
						if(service!=null){
							callback.onSuccess(service);
							return;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
			}

			if(componentInfo.getAssetsPath()!=null){
				ib.installForAssets(
						componentInfo.getAssetsPath(),
						componentInfo.getVersion(),
						null,
						new installCallback(){
							@Override
							public void callback(int status, Bundle bundle) {
								if(bundle!=null){
									try {
										bundle.start();
									} catch (BundleException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									//再次查询支付Service
									try {
										Object service=agent.getService();
										if(service!=null){
											callback.onSuccess(service);
											return;
										}
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
						});
			}

		}
		//callback.onFailure(0, "无法获取服务");
	}


}
