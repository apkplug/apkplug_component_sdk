package com.activity;

import android.app.Application;

import com.apkplug.component.ComponentManager;
import com.apkplug.component.ComponentsInfoManager;

import org.apkplug.app.FrameworkFactory;

/**
 * Created by qinfeng on 15/10/27.
 */
public class MyApplication  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try
        {
            FrameworkFactory.getInstance().start(null, this);
            ComponentManager.getInstance().onInit(FrameworkFactory.getInstance().getFrame().getSystemBundleContext());

            ComponentsInfoManager.onInit(getApplicationContext());
        }
        catch (Exception ex)
        {
            System.err.println("Could not create : " + ex);
            ex.printStackTrace();

        }

    }

}
