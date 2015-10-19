package com.apkplug.component.maketionsdk;

import com.apkplug.component.Base;

public abstract class MaketionCard extends Base {
	/**
	 * 验证账户
	 */
	public abstract void authenticateAccount(String pkey,String psign,String name);
	/**
	 * 检查验证状态
	 */
	public abstract boolean checkAuthentication();
	/**
	 * 清除验证信息
	 */
	public abstract void clearAuthentication();
	/**
	 * 拍照操作
	 */
	public abstract void takepic(PlugMkxBackUpload callback);
	/**
	 * 获取数据
	 */
	public abstract void getCardsWithTime(long time,PlugMkxBackCards callback);
	/**
	 * 获取数据
	 */
	public abstract void getCardsWithUUID(String[] uuids,PlugMkxBackCards callback);

	public int  version(){
		return 1;
	}



}
