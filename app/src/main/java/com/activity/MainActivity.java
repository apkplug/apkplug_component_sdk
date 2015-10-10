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
import com.demo.apkplug_component_sdk.R;
import com.plug.LaunchPlug;

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

    private LaunchPlug mLaunchPlug=null;

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            if (mPlugNames.get(position).equals("maketionSdk"))
            {
                startActivity(new Intent(MainActivity.this,MaketionActivity.class));
            }
            if (mPlugNames.get(position).equals("GeeTestSdk"))
            {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
            if (mPlugNames.get(position).equals("ChatUIDemo"))
            {
                ComponentManager.getInstance().searchComponent(
                        ComponentFactory.getInstance().getComponent("ChatUIDemo"), //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<LaunchPlug>() {
                            @Override
                            public void onSuccess(LaunchPlug launchPlug) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "ChatUIDemo服务获取成功！", Toast.LENGTH_SHORT).show();
                                mLaunchPlug = launchPlug;
                                mLaunchPlug.launchPlug();

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
                        ComponentFactory.getInstance().getComponent("UdeskDemo"), //从组件工厂中获取指定的组件ComponentUid
                        new ServerCallback<LaunchPlug>(){
                            @Override
                            public void onSuccess(LaunchPlug launchPlug) {
                                //成功获取到了组件的服务
                                Toast.makeText(MainActivity.this, "UdeskDemo服务获取成功！", Toast.LENGTH_SHORT).show();
                                mLaunchPlug=launchPlug;
                                mLaunchPlug.launchPlug();

                            }

                            @Override
                            public void onFailure(int errorNo, String strMsg) {
                                Toast.makeText(MainActivity.this, strMsg, Toast.LENGTH_SHORT).show();
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
