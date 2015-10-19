package com.apkplug.component.easemobimsdk;

import com.apkplug.component.Base;

public abstract class EaseMobIM extends Base {
    public  abstract void start();
    public int  version(){
        return 1;
    }

}
