package com.activity;

import android.app.Application;

import com.apkplug.component.ComponentManager;
import com.apkplug.component.ComponentsInfoManager;

import org.apkplug.app.FrameworkFactory;
import org.apkplug.app.FrameworkInstance;

/**
 * Created by qinfeng on 15/10/27.
 */
public class MyApplication  extends Application {
    private FrameworkInstance frame;

    @Override
    public void onCreate() {
        super.onCreate();

        try
        {

            frame = FrameworkFactory.getInstance().start(null, this);
            ComponentManager.getInstance().onInit(frame.getSystemBundleContext());
            ComponentsInfoManager.onInit(getApplicationContext());

        }
        catch (Exception ex)
        {
            System.err.println("Could not create : " + ex);
            ex.printStackTrace();

        }

    }

}
