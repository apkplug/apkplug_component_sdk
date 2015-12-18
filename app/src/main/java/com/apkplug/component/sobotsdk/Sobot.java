package com.apkplug.component.sobotsdk;

import com.apkplug.component.Base;

/**
 * Created by qinfeng on 15/11/4.
 */
public abstract class Sobot extends Base{
    public abstract void start();
    public int version(){
        return 1;
    }
}
