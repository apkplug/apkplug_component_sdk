package com.plug;

import cn.udesk.saas.demo.MainActivity;
import android.content.Context;
import android.content.Intent;


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
        mContext.startActivity(new Intent(mContext,MainActivity.class));
    }
}
