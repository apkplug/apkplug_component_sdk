package com.apkplug.plug.imp;



import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;

import com.apkplug.component.maketionsdk.MaketionCard;
import com.apkplug.component.maketionsdk.PlugMkxBackCards;
import com.apkplug.component.maketionsdk.PlugMkxBackUpload;
import com.apkplug.component.maketionsdk.PlugMkxCard;

import java.util.ArrayList;

import cn.maketion.uploadSdk.MkxActivityCamera;
import cn.maketion.uploadSdk.MkxBackAuth;
import cn.maketion.uploadSdk.MkxBackCards;
import cn.maketion.uploadSdk.MkxBackUpload;
import cn.maketion.uploadSdk.MkxCard;
import cn.maketion.uploadSdk.MkxErrorCode;
import cn.maketion.uploadSdk.MkxServer;

public class MaketionCardImp extends MaketionCard {


	private Context mContext=null;
	public static final String pname = "abcxyz1111111@163.com";
	public static final String pkey = "F681EA2B819A5927B2E765D1E4AA6AFA";
	public static final String psign = "a76e83486d7fc3f4f33bc25bb142c88d21a97263ea7e4df1105d24e553d66671569c7cb38a4edf033b462f582903888aa734176016099ea389570e2c8c4a7ff3";

	private MkxServer server;
	private boolean isInit=false;
	@Override
	public int versionInPlug() {
		return 1;
	}
	public MaketionCardImp(Context context) {
		mContext=context;
		server =MkxServer.getServer((Application) mContext.getApplicationContext());
		isInit = server.isAuth();
		if(isInit){
			Toast.makeText(mContext, "已经验证！", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(mContext, "未验证！", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean checkAuthentication() {
		isInit = server.isAuth();
		return isInit;
	}
	public void authenticateAccount(String pkey, String psign,String name) {
		if(!isInit){
			server.auth(pkey, psign, name, new MkxBackAuth() {
				@Override
				public void onBack(int code, String errInfo) {

					if (code == MkxErrorCode.CODE_SUCCESS) {
						isInit = server.isAuth();
						if (isInit) {

							Toast.makeText(mContext, "验证成功!", Toast.LENGTH_SHORT).show();
						} else {

							Toast.makeText(mContext, "验证失败!", Toast.LENGTH_SHORT).show();
						}
					} else {

						Toast.makeText(mContext, errInfo, Toast.LENGTH_SHORT).show();
					}
				}
			});
		}else{
			Toast.makeText(mContext, "已经验证过了，不能重复验证!", Toast.LENGTH_SHORT).show();
			
		}
	}

	/**
	 * 清除验证信息
	 */
	public void clearAuthentication() {
		server.clearAuth();
		isInit = server.isAuth();
		if(isInit){
			Toast.makeText(mContext, "清除验证信息失败", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(mContext, "清除验证信息成功！", Toast.LENGTH_SHORT).show();
			
		}
	}
	/**
	 * 获取数据
	 */
	public void getCardsWithUUID(String[] uuids,final PlugMkxBackCards callback) {
		final ArrayList<PlugMkxCard> cardlist=new ArrayList<PlugMkxCard>();
		if (isInit) {
			server.getDataWithUUID(uuids, new MkxBackCards() {
				@Override
				public void onBack(int code, String info, MkxCard[] cards) {
					if (code == MkxErrorCode.CODE_SUCCESS) {
						if (cards.length > 0) {


							for (int i = 0; i < cards.length; i++) {

								PlugMkxCard mkxCardInPlug = new PlugMkxCard();
								mkxCardInPlug.setAddress(cards[i].address);
								mkxCardInPlug.setAudit(cards[i].audit);
								mkxCardInPlug.setCarduuid(cards[i].carduuid);
								mkxCardInPlug.setCname(cards[i].cname);
								mkxCardInPlug.setCreatime(cards[i].createtime);
								mkxCardInPlug.setDuty(cards[i].duty);
								mkxCardInPlug.setEmail(cards[i].email);
								mkxCardInPlug.setFax(cards[i].fax);
								mkxCardInPlug.setFields(cards[i].fields);
								mkxCardInPlug.setFlag(cards[i].flag);
								mkxCardInPlug.setLogo(cards[i].logo);
								mkxCardInPlug.setMobile1(cards[i].mobile1);
								mkxCardInPlug.setMobile2(cards[i].mobile2);
								mkxCardInPlug.setName(cards[i].name);
								mkxCardInPlug.setTell1(cards[i].tel1);
								mkxCardInPlug.setTell2(cards[i].tel2);
								mkxCardInPlug.setUpdatetime(cards[i].updatetime);
								mkxCardInPlug.setWebsite(cards[i].website);

								cardlist.add(mkxCardInPlug);
								callback.onBack(code, info, cardlist);
							}

							//Toast.makeText(mContext, "获取数据成功！", Toast.LENGTH_SHORT).show();
						} else {

							Toast.makeText(mContext, "获取的数据成功，但为空", Toast.LENGTH_SHORT).show();
							callback.onBack(code, info, cardlist);
						}
					} else {

						//Toast.makeText(mContext, "获取数据失败！错误信息是", Toast.LENGTH_SHORT).show();
						callback.onBack(code, info, cardlist);
					}

				}
			});
		}else{
			Toast.makeText(mContext, "还未验证账户!", Toast.LENGTH_SHORT).show();
		}
	}
	public void getCardsWithTime(long time,final PlugMkxBackCards callback) {
		
		final ArrayList<PlugMkxCard> cardlist=new ArrayList<PlugMkxCard>();
		
		if(isInit){
			
			server.getDataWithTime(time, new MkxBackCards() {
				@Override
				public void onBack(int code, String info, MkxCard[] cards) {

					if (code == MkxErrorCode.CODE_SUCCESS) {
						if (cards.length > 0) {


							for (int i = 0; i < cards.length; i++) {

								PlugMkxCard mkxCardInPlug = new PlugMkxCard();
								mkxCardInPlug.setAddress(cards[i].address);
								mkxCardInPlug.setAudit(cards[i].audit);
								mkxCardInPlug.setCarduuid(cards[i].carduuid);
								mkxCardInPlug.setCname(cards[i].cname);
								mkxCardInPlug.setCreatime(cards[i].createtime);
								mkxCardInPlug.setDuty(cards[i].duty);
								mkxCardInPlug.setEmail(cards[i].email);
								mkxCardInPlug.setFax(cards[i].fax);
								mkxCardInPlug.setFields(cards[i].fields);
								mkxCardInPlug.setFlag(cards[i].flag);
								mkxCardInPlug.setLogo(cards[i].logo);
								mkxCardInPlug.setMobile1(cards[i].mobile1);
								mkxCardInPlug.setMobile2(cards[i].mobile2);
								mkxCardInPlug.setName(cards[i].name);
								mkxCardInPlug.setTell1(cards[i].tel1);
								mkxCardInPlug.setTell2(cards[i].tel2);
								mkxCardInPlug.setUpdatetime(cards[i].updatetime);
								mkxCardInPlug.setWebsite(cards[i].website);

								cardlist.add(mkxCardInPlug);

							}
							callback.onBack(code, info, cardlist);
							//Toast.makeText(mContext, "获取数据成功！", Toast.LENGTH_SHORT).show();
						} else {
							callback.onBack(code, info, cardlist);
							Toast.makeText(mContext, "获取的数据成功，但为空", Toast.LENGTH_SHORT).show();
						}
					} else {
						callback.onBack(code, info, cardlist);
						Toast.makeText(mContext, "获取数据失败！错误信息是", Toast.LENGTH_SHORT).show();
					}

				}
			});
		}else{
			Toast.makeText(mContext, "还未验证账户!", Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * 拍照操作
	 */
	public void takepic(final PlugMkxBackUpload callback) {
		if(isInit){
			server.setUploadListener(new MkxBackUpload() {
				@Override
				public void onBack(int code, String errInfo, String uuid, int status) {
					callback.onBack(code, errInfo, uuid, status);
				}
			});
			Intent intent = new Intent(mContext, MkxActivityCamera.class);
			mContext.startActivity(intent);
		}else{
			
			Toast.makeText(mContext, "还未验证账户，请验证账户再获取数据", Toast.LENGTH_SHORT).show();
		}
	}
	/**
	 * 设置名片保存的路径
	 */
	public void setSavePath(String path) {
		server.setSdcardPath(path);
		String sdcard = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			sdcard = Environment.getExternalStorageDirectory().toString();
		}
		
		Toast.makeText(mContext, "设置名片保存路径为："+sdcard+"/"+path, Toast.LENGTH_SHORT).show();
		
	}


}
