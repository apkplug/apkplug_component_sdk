package com.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apkplug.component.ComponentFactory;
import com.apkplug.component.ComponentManager;
import com.apkplug.component.ServerCallback;
import com.apkplug.component.maketionsdk.MaketionCard;
import com.apkplug.component.maketionsdk.PlugMkxBackUpload;
import com.demo.apkplug_component_sdk.R;
@SuppressLint("NewApi")
public class MaketionActivity extends Activity {
	
	private TextView info=null;
	private MaketionCard mMaketionCard;
	public static final String pname = "abcxyz1111111@163.com";
	public static final String pkey = "F681EA2B819A5927B2E765D1E4AA6AFA";
	public static final String psign = "a76e83486d7fc3f4f33bc25bb142c88d21a97263ea7e4df1105d24e553d66671569c7cb38a4edf033b462f582903888aa734176016099ea389570e2c8c4a7ff3";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maketion);
		
		ComponentManager.getInstance().searchComponent(
				ComponentFactory.getInstance().getComponent("maketionSdk"), //从组件工厂中获取指定的组件ComponentUid
				new ServerCallback<MaketionCard>(){
			@Override
			public void onSuccess(MaketionCard service) {
				//成功获取到了组件的服务
				Toast.makeText(MaketionActivity.this, "MaketionCardSDK服务获取成功！", Toast.LENGTH_SHORT).show();
				mMaketionCard=service;
				
			}

			@Override
			public void onFailure(int errorNo, String strMsg) {
				Toast.makeText(MaketionActivity.this, strMsg, Toast.LENGTH_SHORT).show();
			}});
		
		
		info =(TextView)this.findViewById(R.id.info);
		Button maketionsdk_authenticate_btn=(Button)findViewById(R.id.maketionsdk_authenticate_btn);
		maketionsdk_authenticate_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				mMaketionCard.authenticateAccount(pkey, psign, pname);
			}
		});
		
		Button maketionsdk_take_pic_btn=(Button)findViewById(R.id.maketionsdk_take_pic_btn);
		maketionsdk_take_pic_btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				mMaketionCard.takepic(new PlugMkxBackUpload() {
					
					@Override
					public void onBack(int code, String errInfo, String uuid, int status) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), code+errInfo+uuid, Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
		
	}

 	@Override
    public void onStart()
    {
        super.onStart();
        //ApkplugAnalyticsAgent.getInstance(null).onStart();
    }

    @Override
    public void onStop()
    {
    	//ApkplugAnalyticsAgent.getInstance(null).onStop();
        super.onStop();
    }
}
