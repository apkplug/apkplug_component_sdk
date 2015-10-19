package com.apkplug.component.udesksdk;

import com.apkplug.component.Base;

public abstract class Udesk extends Base {
    public abstract void start();
    public int  version(){
        return 1;
    }
}
