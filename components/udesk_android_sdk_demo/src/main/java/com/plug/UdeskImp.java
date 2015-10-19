package com.plug;

import android.content.Context;
import android.content.Intent;

import com.apkplug.component.udesksdk.Udesk;

import cn.udesk.saas.demo.MainActivity;


/**
 * Created by qinfeng on 15/10/8.
 */
public class UdeskImp extends Udesk {

    private Context mContext=null;

    public UdeskImp(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void start() {
        mContext.startActivity(new Intent(mContext,MainActivity.class));
    }

    @Override
    public int versionInPlug() {
        return 1;
    }
}
