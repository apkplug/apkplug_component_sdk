package com.apkplug.component;

import android.util.Log;

public class ComponentInfo {

	private String plugName;
	private String symbolicName;
	private String appid;
	private String assetsPath;
	private String version;
	private Class  osgiServer;


	private String osgiServerClassName;


	public String getPlugName() {
		return plugName;
	}

	public String getSymbolicName() {
		return symbolicName;
	}

	public String getAppid() {
		return appid;
	}

	public String getAssetsPath() {
		return assetsPath;
	}

	public String getVersion() {
		return version;
	}

	public Class getOsgiServer() {
		return osgiServer;
	}

	public String getOsgiServerClassName() {
		return osgiServerClassName;
	}

	public ComponentInfo(){Log.e("","construct");};
	public ComponentInfo(String plugName, String symbolicName, String version, String className, String appid, String assetsPath){
		this.plugName=plugName;
		this.appid=appid;
		osgiServerClassName=className;
		this.version=version;
		this.symbolicName=symbolicName;
		this.assetsPath=assetsPath;

		
	}

	public void setOsgiServer() {
		try {
			this.osgiServer=Class.forName(this.osgiServerClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "ComponentInfo{" +
				"plugName='" + plugName + '\'' +
				", symbolicName='" + symbolicName + '\'' +
				", appid='" + appid + '\'' +
				", assetsPath='" + assetsPath + '\'' +
				", version='" + version + '\'' +
				", osgiServer=" + osgiServer +
				", osgiServerClassName='" + osgiServerClassName + '\'' +
				'}';
	}
}
