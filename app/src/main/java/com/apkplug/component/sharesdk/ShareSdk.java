package com.apkplug.component.sharesdk;

import com.apkplug.component.Base;

/**
 * Created by qinfeng on 15/10/19.
 */
public abstract  class ShareSdk extends Base {
    public abstract void start();
    public int version(){
        return 1;
    }
}
