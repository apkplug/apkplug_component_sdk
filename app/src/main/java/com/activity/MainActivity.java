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
import com.apkplug.component.sharesdk.ShareSdk;
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
            if (mPlugNames.get(position).equals("SharesdkDemo"))
            {
                ComponentManager.getInstance().searchComponent(
                        "SharesdkDemo", //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<ShareSdk>(){
                            @Override
                            public void onSuccess(ShareSdk shareSdk) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "SharesdkDemo服务获取成功！", Toast.LENGTH_SHORT).show();
                                shareSdk.start();

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
