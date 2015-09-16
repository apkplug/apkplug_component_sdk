package com.activity;

import com.demo.apkplug_component_sdk.R;
import com.apkplug.component.ComponentFactory;
import com.apkplug.component.ComponentManager;
import com.apkplug.component.ServerCallback;
import com.gt.plug.GtSDK;
import com.gt.plug.PlugGtListener;


import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

public class TestActivity extends Activity {

	private GtSDK mGtSDK=null;
	private final String TAG="TestActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		ComponentManager.getInstance().searchComponent(
				ComponentFactory.getInstance().getComponent("GeeTestSdk"), //从组件工厂中获取指定的组件ComponentUid
				new ServerCallback<GtSDK>(){
					@Override
					public void onSuccess(GtSDK service) {

						mGtSDK=service;

						Toast.makeText(TestActivity.this, "GeeTestSDK服务获取成功！", Toast.LENGTH_SHORT).show();
						Toast.makeText(getApplicationContext(), "version"+mGtSDK.version(), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int errorNo, String strMsg) {

					}});
	}

	public void btnAPI(View v) {
		String captcha_id = "ad872a4e1a51888967bdb7cb45589605";
		mGtSDK.setCaptchaId(captcha_id);
		String custom_server_validate_url = "http://testcenter.geetest.com/gtweb/android_sdk_demo_server_validate/";
		mGtSDK.GtTest(custom_server_validate_url,new PlugGtListener() {

			@Override
			public void gtResult(String response) {
				Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

			}

		});

	}
}