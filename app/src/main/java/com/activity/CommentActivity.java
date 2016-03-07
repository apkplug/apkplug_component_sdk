package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.apkplug.component.ComponentManager;
import com.apkplug.component.ServerCallback;
import com.apkplug.component.sharesdk.PlugShareInfo;
import com.apkplug.component.sharesdk.PlugShareSDK;
import com.apkplug.component.sharesdk.PlugTopicTitle;
import com.demo.apkplug_component_sdk.R;

import org.apkplug.app.FrameworkInstance;

public class CommentActivity extends Activity {
	private FrameworkInstance frame=null;
	private LinearLayout layout=null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment);
		
		
		layout=(LinearLayout)this.findViewById(R.id.layout);
		ComponentManager.getInstance().searchComponent(
                "SharesdkDemo", //从组件工厂中获取指定的组件ComponentUid
                new ServerCallback<PlugShareSDK>(){
                    @Override
                    public void onSuccess(PlugShareSDK shareSdk) {
                        //成功获取到了组件的服务
                        Toast.makeText(CommentActivity.this, "SharesdkDemo服务获取成功！", Toast.LENGTH_SHORT).show();
                        final PlugShareInfo oks=new PlugShareInfo();
                        oks.setAddress("北京市");
                        oks.setTitle("apkplug下sharesdk分享组件");
                        oks.setTitleUrl("http://sharesdk.cn");
                        oks.setText("apkplug是android下的模块化解决方案，ShareSDK是一个非常棒的社会化分享组件,本着不重复造轮子的思想，我们将ShareSDK做成插件。详情见官网http://www.apkplug.com/cloud/plugshop/");
                        //oks.setImagePath(initImagePath());
                        oks.setImageUrl("http://echoapp.oss-cn-beijing.aliyuncs.com/78144f6fac654aa49748bcfaa122e565_headicon.jpg");
                        oks.setUrl("http://123.56.45.223:8080/echo/html/moblie/index.html#/app/merchant/bc0001c3da3940e89b80a783af9a8b32");
                        //oks.setFilePath(initImagePath());
                        oks.setComment("推荐使用apkplug框架减小应用体积，提供开发效率。");
                        oks.setSite(getString(R.string.app_name));
                        oks.setSiteUrl("http://www.apkplug.com");
                        oks.setVenueName("apkplug");
                        oks.setVenueDescription("apkplug是android下的模块化框架!");
                        oks.setLatitude(23.056081f);
                        oks.setLongitude(113.385708f);
                        oks.setDisableSSOWhenAuthorize(false);
                        
                        
                        final PlugTopicTitle topic=new PlugTopicTitle();
        				topic.setTopicId("apkplug下sharesdk分享组件");
        				topic.setTopicTitle("apkplug下sharesdk分享组件");
        				topic.setTopicAuthor("apkplug");
        				topic.setTopicPublishTime("2015-03-30");

        				View vtopic=shareSdk.getTopicTitle(topic);
						layout.addView(vtopic);
						View v=shareSdk.getQuickCommentBar(topic, "分享", oks);
						layout.addView(v);

                    }

                    @Override
                    public void onFailure(int errorNo, String strMsg) {
                       // Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                    }});
	}

	
}
