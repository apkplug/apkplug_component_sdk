package com.apkplug.component;


public class ComponentFactory {
	private final String TAG="ComponentFactory";
	private static ComponentFactory _instance=null;
	private ComponentsInfo mComponentInfoManager=null;

	synchronized public static ComponentFactory getInstance(){
	    if(_instance==null){
	    _instance=new ComponentFactory();
	    }
	    return _instance;
	} 
	private ComponentFactory(){

		mComponentInfoManager=new ComponentsInfoManager();

	}

	public ComponentsInfo getComponentInfoManager() {
		return mComponentInfoManager;
	}
	public ComponentInfo getComponent(String componentName){

		return mComponentInfoManager.getComponentInfo(componentName);
	}


}
