package com.apkplug.component.json;

import com.apkplug.component.ComponentInfo;
import com.apkplug.component.util.Timber;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


class GsonPlacesApiJsonParser implements PlacesApiJsonParser {
    private final Gson gson;

    public GsonPlacesApiJsonParser() {
        gson = new GsonBuilder()
            .create();
    }


    @Override
    public List<ComponentInfo> parseComponentsInfo(String componentsInfo) {

        Timber.i("parseComponentsInfo");

        ArrayList<ComponentInfo> componentInfoArrayList = new ArrayList<ComponentInfo>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ComponentInfo>>() {
        }.getType();
        JSONObject cus;
        try {
            cus = new JSONObject(componentsInfo);
            componentInfoArrayList  = gson.fromJson(cus.getString("componentsInfo"), type);
            for (int i = 0; i < componentInfoArrayList.size(); i++) {
                componentInfoArrayList.get(i).setOsgiServer();
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Timber.e("JSON解析错误!");
        }
        return componentInfoArrayList;
    }
}
