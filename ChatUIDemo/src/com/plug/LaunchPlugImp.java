package com.plug;

import android.content.Context;
import android.content.Intent;

import com.easemob.chatuidemo.activity.SplashActivity;

/**
 * Created by qinfeng on 15/10/8.
 */
public class LaunchPlugImp implements LaunchPlug {

    private Context mContext=null;

    public LaunchPlugImp(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    public void launchPlug() {
        mContext.startActivity(new Intent(mContext,SplashActivity.class));
    }
}
