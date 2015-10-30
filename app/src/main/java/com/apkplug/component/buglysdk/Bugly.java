package com.apkplug.component.buglysdk;

import com.apkplug.component.Base;

/**
 * Created by qinfeng on 15/10/22.
 */
public abstract class Bugly extends Base{
    public  abstract void start(String appid);
    public int  version(){
        return 1;
    }
}
