package com.activity;

import com.apkplug.component.ApkplugApplication;
import com.apkplug.component.ComponentManager;

/**
 * Created by qinfeng on 15/10/27.
 */
public class MyApplication  extends ApkplugApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().onInit(getFrame().getSystemBundleContext());
    }


}
