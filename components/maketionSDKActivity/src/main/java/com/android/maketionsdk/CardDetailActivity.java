package com.android.maketionsdk;

import cn.maketion.uploadSdk.MkxBackCards;
import cn.maketion.uploadSdk.MkxCard;
import cn.maketion.uploadSdk.MkxErrorCode;
import cn.maketion.uploadSdk.MkxServer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class CardDetailActivity extends Activity {

	private MkxServer server;
	private ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_detail_activity);
		server = MkxServer.getServer(getApplication());
		if (getIntent() != null && !TextUtils.isEmpty(getIntent().getStringExtra("uuid"))) {
			String uuid = getIntent().getStringExtra("uuid").toString();
			showProgress();
			//获取单张名片信息
			server.getDataWithUUID(new String[]{uuid}, new MkxBackCards() {
				@Override
				public void onBack(int code, String info, MkxCard[] cards) {
					progress.dismiss();
					if(code == MkxErrorCode.CODE_SUCCESS){
						setView(cards);
					}
				}

				
			});
		} else {
			Toast.makeText(CardDetailActivity.this, "没有获取uuid",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	private void setView(final MkxCard[] cards) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				((EditText)findViewById(R.id.maketionsdk_card_detail_name_et)).setText(cards[0].name);
				((EditText)findViewById(R.id.maketionsdk_card_detail_duty_et)).setText(cards[0].duty);
				((EditText)findViewById(R.id.maketionsdk_card_detail_mobile1_et)).setText(cards[0].mobile1);
				((EditText)findViewById(R.id.maketionsdk_card_detail_mobile2_et)).setText(cards[0].mobile2);
				((EditText)findViewById(R.id.maketionsdk_card_detail_email_et)).setText(cards[0].email);
				((EditText)findViewById(R.id.maketionsdk_card_detail_tel1_et)).setText(cards[0].tel1);
				((EditText)findViewById(R.id.maketionsdk_card_detail_tel2_et)).setText(cards[0].tel2);
				((EditText)findViewById(R.id.maketionsdk_card_detail_cname_et)).setText(cards[0].cname);
				((EditText)findViewById(R.id.maketionsdk_card_detail_address_et)).setText(cards[0].address);
				((EditText)findViewById(R.id.maketionsdk_card_detail_fax_et)).setText(cards[0].fax);
				((EditText)findViewById(R.id.maketionsdk_card_detail_web_et)).setText(cards[0].website);
			}
		});
	}
	/**
	 * 显示进度
	 */
	private void showProgress(){
		if(progress==null){
			progress = new ProgressDialog(CardDetailActivity.this);
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
}
