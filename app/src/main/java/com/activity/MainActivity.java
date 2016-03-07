package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkplug.component.ComponentFactory;
import com.apkplug.component.ComponentManager;
import com.apkplug.component.ServerCallback;
import com.apkplug.component.buglysdk.Bugly;
import com.apkplug.component.easemobimsdk.EaseMobIM;
import com.apkplug.component.sharesdk.PlugShareInfo;
import com.apkplug.component.sharesdk.PlugShareSDK;
import com.apkplug.component.sobotsdk.Sobot;
import com.apkplug.component.udesksdk.Udesk;
import com.apkplug.component.umengcommunitysdk.UmengCommunity;
import com.demo.apkplug_component_sdk.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {


    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.listView)
    ListView mListView;
    private ArrayList<String> mPlugNames=new ArrayList<String>();
    private ArrayAdapter<String> mAdapter=null ;

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            if (mPlugNames.get(position).equals("MaketionSdk"))
            {
                startActivity(new Intent(MainActivity.this,MaketionActivity.class));
            }
            if (mPlugNames.get(position).equals("GeeTestSdk"))
            {
                startActivity(new Intent(MainActivity.this, GeeTestActivity.class));
            }
            if (mPlugNames.get(position).equals("ChatUIDemo"))
            {
                ComponentManager.getInstance().searchComponent(
                        "ChatUIDemo", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<EaseMobIM>() {
                            @Override
                            public void onSuccess(EaseMobIM easeMobIM) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "ChatUIDemo服务获取成功！", Toast.LENGTH_SHORT).show();
                                easeMobIM.start();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            if (mPlugNames.get(position).equals("UdeskDemo"))
            {
                ComponentManager.getInstance().searchComponent(
                        "UdeskDemo", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<Udesk>() {
                            @Override
                            public void onSuccess(Udesk udesk) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "UdeskDemo服务获取成功！", Toast.LENGTH_SHORT).show();
                                udesk.start();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
//            if (mPlugNames.get(position).equals("SharesdkDemo"))
//            {
//                ComponentManager.getInstance().searchComponent(
//                        "SharesdkDemo", //从组件工厂中获取指定的组件ComponentUid
//                        new ServerCallback<ShareSdk>(){
//                            @Override
//                            public void onSuccess(ShareSdk shareSdk) {
//                                //成功获取到了组件的服务
//                                Toast.makeText(MainActivity.this, "SharesdkDemo服务获取成功！", Toast.LENGTH_SHORT).show();
//                                shareSdk.start();
//
//                            }
//
//                            @Override
//                            public void onFailure(int errorNo, String strMsg) {
//                               // Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
//                            }});
//            }

            if (mPlugNames.get(position).equals("SharesdkDemo")){

                ComponentManager.getInstance().searchComponent(
                        "SharesdkDemo", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<PlugShareSDK>(){
                            @Override
                            public void onSuccess(PlugShareSDK shareSdk) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "SharesdkDemo服务获取成功！", Toast.LENGTH_SHORT).show();
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

                                shareSdk.showShare(oks);

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                // Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                            }});
            }

            if (mPlugNames.get(position).equals("BuglySdk"))
            {
                ComponentManager.getInstance().searchComponent(
                        "BuglySdk", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<Bugly>(){
                            @Override
                            public void onSuccess(Bugly bugly) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "BuglySdk服务获取成功！", Toast.LENGTH_SHORT).show();
                                bugly.start("900010309");

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                // Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                            }});
            }

            if (mPlugNames.get(position).equals("UmengCommunity"))
            {
                ComponentManager.getInstance().searchComponent(
                        "UmengCommunity", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<UmengCommunity>(){
                            @Override
                            public void onSuccess(UmengCommunity uc) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "UmengCommunitySdk服务获取成功！", Toast.LENGTH_SHORT).show();
                                uc.start();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                // Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                            }});
            }
            if (mPlugNames.get(position).equals("Sobot"))
            {
                ComponentManager.getInstance().searchComponent(
                        "Sobot", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<Sobot>(){
                            @Override
                            public void onSuccess(Sobot uc) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "Sobot服务获取成功！", Toast.LENGTH_SHORT).show();
                                uc.start();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                // Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
                            }});
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i=0;i<ComponentFactory.getInstance().getComponentInfoManager().numberOfComponents();i++)
        {
            mPlugNames.add(ComponentFactory.getInstance().getComponentInfoManager().getComponentInfo(i).getPlugName());
        }

        mAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mPlugNames);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mMessageClickedHandler);

    }






}
