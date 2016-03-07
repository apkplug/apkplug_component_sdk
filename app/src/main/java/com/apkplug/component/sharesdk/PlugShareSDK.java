package com.apkplug.component.sharesdk;

import android.view.View;

import com.apkplug.component.Base;

public abstract class PlugShareSDK extends Base {
	public int version(){
		return 100;
	}
	public abstract void showShare(PlugShareInfo share);
	public abstract View getQuickCommentBar(PlugTopicTitle topic, String shareContent, PlugShareInfo share);
	public abstract View getTopicTitle(PlugTopicTitle topic);
	public abstract void showCommentListPage(PlugTopicTitle topic, PlugShareInfo share);
}	
