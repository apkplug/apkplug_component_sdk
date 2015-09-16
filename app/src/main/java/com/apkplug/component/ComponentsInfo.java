package com.apkplug.component;

/**
 * Created by qinfeng on 15/9/15.
 */
public interface ComponentsInfo {
    public ComponentInfo getComponentInfo(String componentName);
    public  ComponentInfo getComponentInfo(int position);
    public  int numberOfComponents();
}
