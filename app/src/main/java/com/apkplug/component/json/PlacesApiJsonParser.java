package com.apkplug.component.json;

import com.apkplug.component.ComponentInfo;

import java.util.List;

/**
 * Created by qinfeng on 16/3/7.
 */
public interface PlacesApiJsonParser {
    public List<ComponentInfo> parseComponentsInfo(String componentsInfo);
}
