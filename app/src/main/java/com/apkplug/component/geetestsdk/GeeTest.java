package com.apkplug.component.geetestsdk;

import com.apkplug.component.Base;

public abstract class GeeTest extends Base {
	public abstract void setCaptcha(String captchaid);
	public abstract void geeTest(String url,PlugGtListener plugGtListener);
	public int  version(){
		return 1;
	}
}
