package com.android.maketionsdk;

import java.io.File;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;
import cn.maketion.uploadSdk.MkxBackPicture;
import cn.maketion.uploadSdk.MkxServer;

public class CardPictureActivity extends Activity {
	private ImageView card_pic_iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_pic);
		card_pic_iv = (ImageView) findViewById(R.id.maketionsdk_picture_iv);
		if(getIntent()!=null&&!TextUtils.isEmpty(getIntent().getStringExtra("uuid"))){
			
			String uuid = getIntent().getStringExtra("uuid").toString();
			
			MkxServer.getServer(getApplication()).getCardImage(uuid, new MkxBackPicture() {
				@Override
				public void onBack(final File file) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(file != null){
								Toast.makeText(CardPictureActivity.this, "获取名片原图成功", Toast.LENGTH_SHORT).show();
								card_pic_iv.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
							}else{
								Toast.makeText(CardPictureActivity.this, "获取名片原图失败", Toast.LENGTH_SHORT).show();
							}
						}
					});
					
				}
			});
		}else{
			Toast.makeText(CardPictureActivity.this, "没有获取uuid", Toast.LENGTH_SHORT).show();
		}
		
	}
}
