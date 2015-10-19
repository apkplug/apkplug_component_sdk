package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apkplug.component.ComponentFactory;
import com.apkplug.component.ComponentManager;
import com.apkplug.component.ServerCallback;
import com.apkplug.component.geetestsdk.GeeTest;
import com.apkplug.component.geetestsdk.PlugGtListener;
import com.demo.apkplug_component_sdk.R;

public class TestActivity extends Activity {

	private GeeTest mGeeTest=null;
	private final String TAG="TestActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		ComponentManager.getInstance().searchComponent(
				ComponentFactory.getInstance().getComponent("GeeTestSdk"), //从组件工厂中获取指定的组件ComponentUid
				new ServerCallback<GeeTest>(){
					@Override
					public void onSuccess(GeeTest service) {

						mGeeTest=service;

						Toast.makeText(TestActivity.this, "GeeTestSDK服务获取成功！", Toast.LENGTH_SHORT).show();
						Toast.makeText(getApplicationContext(), "version"+mGeeTest.version(), Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailure(int errorNo, String strMsg) {

					}});
	}

	public void btnAPI(View v) {
		String captcha_id = "ad872a4e1a51888967bdb7cb45589605";
		mGeeTest.setCaptcha(captcha_id);
		String custom_server_validate_url = "http://testcenter.geetest.com/gtweb/android_sdk_demo_server_validate/";
		mGeeTest.geeTest(custom_server_validate_url, new PlugGtListener() {
			@Override
			public void gtResult(String s) {

			}
		});

	}
}