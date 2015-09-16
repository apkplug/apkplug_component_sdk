package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.apkplug_component_sdk.R;
import com.apkplug.component.ComponentFactory;

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
            if (mPlugNames.get(position).equals("maketionSdk"))
            {
                startActivity(new Intent(MainActivity.this,MaketionActivity.class));
            }
            if (mPlugNames.get(position).equals("GeeTestSdk"))
            {
                startActivity(new Intent(MainActivity.this,TestActivity.class));
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
