package com.plug;

import android.content.Context;
import android.content.Intent;

import com.apkplug.component.easemobimsdk.EaseMobIM;
import com.easemob.chatuidemo.activity.SplashActivity;

/**
 * Created by qinfeng on 15/10/8.
 */
public class EaseMobIMImp extends EaseMobIM {

    private Context mContext=null;

    public EaseMobIMImp(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void start() {
        mContext.startActivity(new Intent(mContext,SplashActivity.class));
    }

    @Override
    public int versionInPlug() {
        return 1;
    }
}
