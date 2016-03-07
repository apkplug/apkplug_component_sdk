package com.apkplug.component;

import android.content.Context;

import com.apkplug.component.util.Timber;
import com.apkplug.component.json.JsonParserResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by qinfeng on 15/9/15.
 */
public class ComponentsInfoManager implements  ComponentsInfo{

    private final String TAG="ComponentInfoManager";
    private List<ComponentInfo> mComponentInfoList;
    private static Context mContext;

    public ComponentsInfoManager() {
        fillComponentInfoList(getJSONString());

    }
    public ComponentInfo getComponentInfo(String componentName){
        for (int i = 0; i < mComponentInfoList.size(); i++) {
            if (componentName.equals(mComponentInfoList.get(i).getPlugName())) {
                return mComponentInfoList.get(i);
            }

        }
        return null;
    }
    public static void onInit(Context context){
        mContext=context;
    }
    public  int numberOfComponents(){
        return mComponentInfoList.size();
    }
    public  ComponentInfo getComponentInfo(int position){
        return mComponentInfoList.get(position);
    }
    private String getJSONString() {
        String componentInfoListString=null;
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(mContext.getAssets().open("components.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//使用BufferReader读取输入流中的数据；
            String line;
            StringBuilder stringBuilder = new StringBuilder();//所有读取的json放到StringBuilder中，这里也可以使用StringBuffer,效果一样；
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            componentInfoListString = stringBuilder.toString();
            bufferedReader.close();//按相反的顺序关闭流；
            inputStreamReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return componentInfoListString;
    }
    private void fillComponentInfoList(String componentInfoListString) {
        mComponentInfoList = JsonParserResolver.JSON_PARSER.parseComponentsInfo(componentInfoListString);
        Timber.d(mComponentInfoList.toString());
    }
}
