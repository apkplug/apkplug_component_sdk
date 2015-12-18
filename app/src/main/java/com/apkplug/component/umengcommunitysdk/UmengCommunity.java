package com.apkplug.component.umengcommunitysdk;

import com.apkplug.component.Base;

/**
 * Created by qinfeng on 15/11/4.
 */
public abstract class UmengCommunity extends Base{
    public abstract void start();
    public int version(){
        return 1;
    }
}
