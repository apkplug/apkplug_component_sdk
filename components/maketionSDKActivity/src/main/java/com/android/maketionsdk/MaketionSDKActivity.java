package com.android.maketionsdk;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.maketion.uploadSdk.MkxActivityCamera;
import cn.maketion.uploadSdk.MkxBackAuth;
import cn.maketion.uploadSdk.MkxBackCards;
import cn.maketion.uploadSdk.MkxBackUpload;
import cn.maketion.uploadSdk.MkxCard;
import cn.maketion.uploadSdk.MkxErrorCode;
import cn.maketion.uploadSdk.MkxServer;

public class MaketionSDKActivity extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener {

	public static final String pname = "abcxyz1111111@163.com";
	public static final String pkey = "F681EA2B819A5927B2E765D1E4AA6AFA";
	public static final String psign = "a76e83486d7fc3f4f33bc25bb142c88d21a97263ea7e4df1105d24e553d66671569c7cb38a4edf033b462f582903888aa734176016099ea389570e2c8c4a7ff3";

	private Button authencate_btn;
	private Button authencation_status_btn;
	private Button clear_authencation_btn;
	private Button take_pic_btn;
	private Button get_data_btn;
	private Button savepath_btn;
	private Button some_btn;
	private EditText time_et;
	private ListView card_lv;
	private EditText username_et;
	
	private MkxServer server;
	private boolean isInit;
	private ProgressDialog progress;
	private MkxCard[] cardlist = new MkxCard[]{};
	private DataListAdapter adapter;
	
	private ArrayList<String> upLoadFails = new ArrayList<String>();
	private int uploadTimes = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maketion_sdk);
		authencate_btn = (Button) findViewById(R.id.maketionsdk_authenticate_btn);
		authencation_status_btn = (Button) findViewById(R.id.maketionsdk_authenticate_status_btn);
		clear_authencation_btn = (Button) findViewById(R.id.maketionsdk_clear_authentication_btn);
		take_pic_btn = (Button) findViewById(R.id.maketionsdk_take_pic_btn);
		get_data_btn = (Button) findViewById(R.id.maketionsdk_get_data_btn);
		savepath_btn  = (Button) findViewById(R.id.maketionsdk_set_savepath_btn);
		some_btn     = (Button) findViewById(R.id.maketionsdk_get_some_data_btn);
		time_et      = (EditText) findViewById(R.id.maketionsdk_time_et);
		card_lv = (ListView) findViewById(R.id.maketionsdk_data_list);
		
		authencate_btn.setOnClickListener(this);
		authencation_status_btn.setOnClickListener(this);
		clear_authencation_btn.setOnClickListener(this);
		take_pic_btn.setOnClickListener(this);
		get_data_btn.setOnClickListener(this);
		savepath_btn.setOnClickListener(this);
		some_btn.setOnClickListener(this);
		
		adapter = new DataListAdapter();
		card_lv.setAdapter(adapter);
		card_lv.setOnItemClickListener(adapter);
		card_lv.setOnItemLongClickListener(adapter);
		
		server =MkxServer.getServer(getApplication());
		
		isInit = server.isAuth();
		if(isInit){
			showToast("已经验证！", Toast.LENGTH_SHORT);
		}else{
			showToast("未验证！", Toast.LENGTH_SHORT);
		}
	}
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.maketionsdk_authenticate_btn://验证账户
			showDialog();
			break;
		case R.id.maketionsdk_authenticate_status_btn://检查验证状态
			checkAuthentication();
			break;
		case R.id.maketionsdk_clear_authentication_btn://清除验证信息
			clearAuthentication();
			break;
		case R.id.maketionsdk_take_pic_btn://拍照
			takepic();
			break;
		case R.id.maketionsdk_get_data_btn://获取数据
			getCards(0);
			break;
		case R.id.maketionsdk_set_savepath_btn://设置保存路径
			setSavePath();
			break;
		case R.id.maketionsdk_get_some_data_btn://获取部分数据
			if(!TextUtils.isEmpty(time_et.getText())){
				long time = Long.parseLong(time_et.getText().toString());
				getCards(System.currentTimeMillis()/1000-time);
			}else{
				showToast("请输入时间区间！", Toast.LENGTH_SHORT);
			}
			break;
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
			if(!TextUtils.isEmpty(username_et.getText())){
				authenticateAccount(username_et.getText().toString());
			}
			break;
		}
	}
	/**
	 * 验证账户
	 */
	private void authenticateAccount(String name) {
		if(!isInit){
			showProgress();
			server.auth(pkey, psign, name, new MkxBackAuth() {
				@Override
				public void onBack(int code, String errInfo) {
					progress.dismiss();
					if(code == MkxErrorCode.CODE_SUCCESS){
						isInit = server.isAuth();
						if(isInit){
							showToast("验证成功!", Toast.LENGTH_SHORT);
						}else{
							showToast("验证失败!", Toast.LENGTH_SHORT);
						}
					}else{
						showToast(errInfo, Toast.LENGTH_LONG);
					}
				}
			});
		}else{
			showToast("已经验证过了，不能重复验证!", Toast.LENGTH_SHORT);
		}
	}
	/**
	 * 检查验证状态
	 */
	private void checkAuthentication() {
		isInit = server.isAuth();
		if(isInit){
			showToast("已经验证", Toast.LENGTH_SHORT);
		}else{
			showToast("未验证", Toast.LENGTH_SHORT);
		}
	}
	/**
	 * 清除验证信息
	 */
	private void clearAuthentication() {
		server.clearAuth();
		isInit = server.isAuth();
		if(isInit){
			showToast("清除验证信息失败", Toast.LENGTH_SHORT);
		}else{
			showToast("清除验证信息成功！", Toast.LENGTH_SHORT);
		}
	}
	/**
	 * 获取数据
	 */
	private void getCards(long time) {
		if(isInit){
			showProgress();
			server.getDataWithTime(time, new MkxBackCards() {
				@Override
				public void onBack(int code, String info, MkxCard[] cards) {
					progress.dismiss();
					if(code == MkxErrorCode.CODE_SUCCESS){
						cardlist = cards;
						if(cards.length>0){
							showToast("获取数据成功！", Toast.LENGTH_SHORT);
						}else{
							showToast("获取的数据成功，但为空", Toast.LENGTH_SHORT);
						}
					}else{
						showToast("获取数据失败！错误信息是"+info, Toast.LENGTH_SHORT);
					}
					refreshView();
					
				}
			});
		}else{
			showToast("还未验证账户!", Toast.LENGTH_SHORT);
		}
	}
	/**
	 * 拍照操作
	 */
	private void takepic() {
		if(isInit){
			server.setUploadListener(new MkxBackUpload() {
				@Override
				public void onBack(int code, String errInfo, String uuid, int status) {
					switch (status) {
					case STATUS_ERROR://上传出错
						if(uploadTimes <= 5){//由于重新上传与拍照上传都是调用统一上传函数，因此需要限制重传次数，避免上传图片时产生无限递归
							uploadTimes++;
							server.uploadImage(uuid);//重新上传
						}else{//重新上传的次数达到上限时，不再上传，保存不能上传图片的uuid
							uploadTimes = 0;
							upLoadFails.add(uuid);
							showToast("上传失败，等待网络通畅时再重新上传", Toast.LENGTH_SHORT);
						}
						break;
					case STATUS_START://开始上传
						break;
					case STATUS_SUCESS://上传成功
						showToast("上传成功", Toast.LENGTH_SHORT);
						break;
					}
				}
			});
			Intent intent = new Intent(this, MkxActivityCamera.class);
			startActivity(intent);
		}else{
			showToast("还未验证账户，请验证账户再获取数据", Toast.LENGTH_SHORT);
		}
	}
	/**
	 * 设置名片保存的路径
	 */
	private void setSavePath() {
		server.setSdcardPath("maketionsdk");
		String sdcard = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			sdcard = Environment.getExternalStorageDirectory().toString();
		}
		showToast("设置名片保存路径为："+sdcard+"/maketionsdk", Toast.LENGTH_SHORT);
	}
	
	/**
	 * 提示信息
	 * @param info
	 * @param duration
	 */
	private void showToast(String info,int duration){
		Toast.makeText(getApplicationContext(), info, duration).show();
	}
	/**
	 * 刷新页面
	 */
	private void refreshView(){
		adapter.notifyDataSetChanged();
	}
	/**
	 * 显示进度
	 */
	private void showProgress(){
		if(progress==null){
			progress = new ProgressDialog(MaketionSDKActivity.this);
			progress.setTitle("进度");
			progress.setMessage("正在通信中....");
			progress.show();
		}else{
			if(progress.isShowing()){
				
			}else{
				progress.show();
			}
		}
	}
	/**
	 * 显示对话框
	 */
	private void showDialog() {
		username_et = new EditText(MaketionSDKActivity.this);
		Builder builder = new Builder(MaketionSDKActivity.this);
		builder.setTitle("请输入用户名")
			   .setView(username_et)
			   .setPositiveButton("确认", this)
			   .setNegativeButton("取消", null)
			   .show();
	}
	public class DataListAdapter extends BaseAdapter implements OnItemClickListener, OnItemLongClickListener{

		@Override
		public int getCount() {
			if(cardlist.length>0){
				return cardlist.length;
			}else{
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			if(cardlist.length>0){
				return cardlist[position];
			}else{
				return null;
			}
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolderContent viewHolderContent = null;

			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.data_list_item, null);

				viewHolderContent = new ViewHolderContent();
				viewHolderContent.logoIV = (ImageView) convertView
						.findViewById(R.id.contact_item_logo_iv);
				viewHolderContent.nameTV = (TextView) convertView
						.findViewById(R.id.contact_item_name_tv);
				viewHolderContent.companyTV = (TextView) convertView
						.findViewById(R.id.contact_item_company_tv);
				convertView.setTag(viewHolderContent);
			} else {
				viewHolderContent = (ViewHolderContent) convertView.getTag();
			}
			if(getItem(position) instanceof MkxCard){
				viewHolderContent.card = (MkxCard) getItem(position);
				viewHolderContent.nameTV.setText(viewHolderContent.card.name);
				viewHolderContent.companyTV.setText(viewHolderContent.card.cname);
			}
			return convertView;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if(isInit){
				Intent intent = new Intent(MaketionSDKActivity.this,CardDetailActivity.class);
				intent.putExtra("uuid", ((MkxCard) getItem(position)).carduuid);
				startActivity(intent);
			}else{
				showToast("还未验证账户!", Toast.LENGTH_SHORT);
			}
		}
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			Intent intent = new Intent(MaketionSDKActivity.this,CardPictureActivity.class);
			intent.putExtra("uuid", ((MkxCard) getItem(position)).carduuid);
			startActivity(intent);
			return true;
		}
	} 
	public class ViewHolderContent {
		private MkxCard card;
		private ImageView logoIV;
		private TextView nameTV;
		private TextView companyTV;
	}

}
