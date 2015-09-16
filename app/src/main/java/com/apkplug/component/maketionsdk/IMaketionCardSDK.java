package com.apkplug.component.maketionsdk;

public interface IMaketionCardSDK {
	/**
	 * 验证账户
	 */
	public void authenticateAccount(String pkey,String psign,String name);
	/**
	 * 检查验证状态
	 */
	public boolean checkAuthentication();
	/**
	 * 清除验证信息
	 */
	public void clearAuthentication();
	public void startPictureActivity();
	/**
	 * 拍照操作
	 */
	public void takepic(PlugMkxBackUpload callback);
	/**
	 * 获取数据
	 */
	public void getCardsWithTime(long time,PlugMkxBackCards callback);
	/**
	 * 获取数据
	 */
	public void getCardsWithUUID(String[] uuids,PlugMkxBackCards callback);
}
