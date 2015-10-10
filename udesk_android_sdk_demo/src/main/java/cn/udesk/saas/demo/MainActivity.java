package cn.udesk.saas.demo;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.HashMap;

import cn.udesk.saas.sdk.OnUserFieldCallback;
import cn.udesk.saas.sdk.OnUserInfoCallback;
import cn.udesk.saas.sdk.UDeskSDK;
import cn.udesk.saas.sdk.UdeskCommodityConstants;
import cn.udesk.saas.sdk.UdeskConversationArgs;
import cn.udesk.saas.sdk.UdeskUserInfo;

public class MainActivity extends Activity {
	private final static String TAG = MainActivity.class.getSimpleName();
	private final static String UDESK_SUB_DOMAIN = "wkudesk.udesk.cn";// 在udesk平台申请的演示用域名。
	private final static String UDESK_SECRET_KEY = "500bd56126a2940f1a13a830e0ec3faf";// udesk平台分配的演示用secret	
	private EditText etSubDomain, etSecret;
	/**
	 * 用户唯一身份标识必填
	 */
	private String userUniqueIdentifier= "xxxxxxxxx" ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etSubDomain = (EditText) findViewById(R.id.et_subdomain);
		etSecret = (EditText) findViewById(R.id.et_secret);

		etSubDomain.setText(UDESK_SUB_DOMAIN);
		etSecret.setText(UDESK_SECRET_KEY);

		// 必选
		UDeskSDK.getInstance().init(UDESK_SECRET_KEY, UDESK_SUB_DOMAIN,MainActivity.this);

		// 如果您需要定义一些 自定义格式的信息格式，请调用此方法。 可选
		UDeskSDK.getInstance().getCustomeUserField(getApplication(),
				new OnUserFieldCallback() {

					@Override
					public void onSuccess(JSONArray result) {
						Log.e(TAG, "    onSuccess  result:" + result.toString());

					}

					@Override
					public void onFail(String message) {
						Log.e(TAG, "    onFail  result:" + message);
					}
				});

		findViewById(R.id.btn_open_helper).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 设置当前模式
						// openUdesk(UDeskSDK.MODE_HELPER);
						UDeskSDK.getInstance()
								.showFAQSection(MainActivity.this);
					}
				});
		findViewById(R.id.btn_open_im).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						setDomainKey();
						UdeskConversationArgs args = new UdeskConversationArgs();// 会话界面参数
	
						// 如果有需要的话，可以在此构造商品信息. 可选
						Bundle commodity = new Bundle();
						commodity.putString(
								UdeskCommodityConstants.COMMODITY_TITLE,
								"Udesk正品 运动水壶水杯 大容量 铝合金户外水壶 骑行水壶 BTWIN");// 商品主标题
						commodity.putString(
								UdeskCommodityConstants.COMMODITY_SUBTITLE,
								"¥ 39.90");// 商品副标题
						commodity
								.putString(
										UdeskCommodityConstants.COMMODITY_THUMBNAIL_URL,
										"http://pic.baike.soso.com/p/20140717/20140717104126-817003180.jpg");// 左侧图片
						commodity
								.putString(
										UdeskCommodityConstants.COMMODITY_URL,
										"http://detail.tmall.com/item.htm?spm=a230r.1.14.1.wqKTaG&id=38464626372&cm_id=140105335569ed55e27b&abbucket=4");// 商品网络链接
						args.setCommodity(commodity);

						// 是否需要表情符号选择功能。 默认为true
						args.setShowEmotionFunction(true);

						// 是否需要图片选择功能(拍摄和图库选择)。 默认为true
						args.setShowPictureFunction(true);

						// 当没有可用客服时，需要显示表单页面让终端用户填写。 默认为false
						args.setShowFormWhenOffline(true);

						// 启动IM界面
						UDeskSDK.getInstance().showConversation(
								MainActivity.this, null, null, args);
					}
				});

		findViewById(R.id.btn_open_all).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 打开UDesk
						setDomainKey();
						UDeskSDK.getInstance().showFAQs(MainActivity.this);
					}
				});

		setUserInfo(userUniqueIdentifier);// 请在初始化时 进行信息的添加

	}

	private void setUserInfo(String uniqueIdentifier) {
		setDomainKey();

		// 常用用户信息
		HashMap<String, String> info = new HashMap<String, String>();
		info.put(UdeskUserInfo.NICK_NAME, "用户骑士在线");
		info.put(UdeskUserInfo.CELLPHONE, "010-63369163");
		info.put(UdeskUserInfo.DESCRIPTION, " Desc desc ");
		info.put(UdeskUserInfo.EMAIL, "wk515811235@163.com");
		info.put(UdeskUserInfo.QQ, "123456123");
		info.put(UdeskUserInfo.WEIBO_NAME, "weiboname123");
		info.put(UdeskUserInfo.WEIXIN_ID, "weixin123");
		// 自定义用户信息
		HashMap<String, String> extraInfo = new HashMap<String, String>();
		extraInfo.put("key_extra1", "value_extra1");
		extraInfo.put("key_extra2", "value_extra2");
		extraInfo.put("key_extra3", "value_extra3");

		// 提交用户信息。 请注意 回调代码是在非主线程中
		UDeskSDK.getInstance().setUserInfo(this, uniqueIdentifier,info, extraInfo,
				new OnUserInfoCallback() {
					public void onSuccess() {
						Log.e(TAG, "    onSuccess  ");
					}

					public void onFail(final String message) {
						//Log.e(TAG, "    onFail  " + String.valueOf(message));
						MainActivity.this.runOnUiThread(new Runnable() {
							public void run() {
								
								Toast.makeText(getApplicationContext(), message,Toast.LENGTH_LONG).show();
							}
						});
					}
				});
	}

	private void setDomainKey() {
		// 这只是Demo使用，当subdomain及secret需要修改时，用作测试。
		// 正常情况下，调用即可: UDeskSDK.getInstance().init( secret,subdomain)
		String subdomain = etSubDomain.getText().toString();
		if (TextUtils.isEmpty(subdomain)) {
			subdomain = UDESK_SUB_DOMAIN;
		}

		String secret = etSecret.getText().toString();
		if (TextUtils.isEmpty(secret)) {
			secret = UDESK_SECRET_KEY;
		}

		// 设置UDesk平台分配的二级域名及secret key
		UDeskSDK.getInstance().init(secret, subdomain,MainActivity.this);
	}

}
