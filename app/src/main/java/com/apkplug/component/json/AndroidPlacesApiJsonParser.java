package com.apkplug.component.json;


import com.apkplug.component.ComponentInfo;
import com.apkplug.component.util.Timber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


class AndroidPlacesApiJsonParser implements PlacesApiJsonParser {

    @Override
    public List<ComponentInfo> parseComponentsInfo(String componentsInfo) {

        Timber.i("parseComponentsInfo");
        ArrayList<ComponentInfo> componentInfoArrayList = new ArrayList<ComponentInfo>();

        try {
            JSONObject obj = new JSONObject(componentsInfo);
            JSONArray arr = obj.getJSONArray("componentsInfo");
            for (int i = 0; i < arr.length(); i++)
            {
                String plugName = arr.getJSONObject(i).getString("plugName");
                String symbolicName = arr.getJSONObject(i).getString("symbolicName");
                String version = arr.getJSONObject(i).getString("version");
                String appid = arr.getJSONObject(i).getString("plugName");
                String assetsPath = arr.getJSONObject(i).getString("assetsPath");
                String osgiServerClassName = arr.getJSONObject(i).getString("osgiServerClassName");

                ComponentInfo componentInfo = new ComponentInfo(plugName,symbolicName,version,osgiServerClassName,appid,assetsPath);
                componentInfo.setOsgiServer();
                componentInfoArrayList.add(componentInfo);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Timber.e("JSON解析错误!");
        }
        return componentInfoArrayList;
    }
}
