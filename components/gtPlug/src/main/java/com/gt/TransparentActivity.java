package com.gt;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.geetest.sdk.GtDialog;
import com.geetest.sdk.GtDialog.GtListener;
import com.geetest.sdk.SdkInit;
import com.gt.plug.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TransparentActivity extends Activity {

	private SdkInit sdkInitData = new SdkInit();

	private Context context = TransparentActivity.this;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		GeeTestImp.mGeetestLib.setCaptchaId(GeeTestImp.mCaptcha_id);
		new GtAppDlgTask().execute();
	}

	class GtAppDlgTask extends AsyncTask<Void, Integer, Integer> {

		@Override
		protected Integer doInBackground(Void... params) {
			return GeeTestImp.mGeetestLib.preProcess();
		}

		@Override
		protected void onPostExecute(Integer serverStatusCode) {

			if (serverStatusCode == 1) {

				sdkInitData.setCaptcha_id(GeeTestImp.mCaptcha_id);
				sdkInitData.setChallenge_id(GeeTestImp.mGeetestLib.getChallengeId());
				sdkInitData.setContext(context);
				openGtTest(sdkInitData);

			} else {
				// TODO 使用自己的验证码体系来进行判断。或者不做任何处理
				Toast.makeText(
						getBaseContext(),
						"Geetest Server is Down,Please Use your own system or disable the geetest",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void openGtTest(SdkInit initData) {
		GtDialog dialog = GtDialog.newInstance(initData);


		dialog.setGtListener(new GtListener() {

			@Override
			public void gtResult(boolean success, String result) {

				// TODO Auto-generated method stub
				if (success) {


					// TODO If captcha is succeed on client side ,then post the
					// data to CustomServer to setup the second validate
					try {
						JSONObject res_json = new JSONObject(result);

						//TODO Demo use of captcha
						String custom_server_validate_url = GeeTestImp.mUrl;
						Map<String, String> params = new HashMap<String, String>();

						params.put("geetest_challenge",
								res_json.getString("geetest_challenge"));
						params.put("geetest_validate",
								res_json.getString("geetest_validate"));
						params.put("geetest_seccode",
								res_json.getString("geetest_seccode"));
						String response = GeeTestImp.mGeetestLib.submitPostData(
								custom_server_validate_url, params, "utf-8");
						
						GeeTestImp.mGtListener.gtResult(response);
					    TransparentActivity.this.finish();

					} catch (Exception e) {
						e.printStackTrace();
					}
					

				} else {
					// TODO 验证失败
					toastMsg("client captcha failed:" + result);
					TransparentActivity.this.finish();
				}
			}

			@Override
			public void closeGt() {
				TransparentActivity.this.finish();
			}
		});
		dialog.show();
	}

	private void toastMsg(String msg) {
		Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
	}

}
