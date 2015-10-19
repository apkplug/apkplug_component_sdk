package cn.sharesdk.plug;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.apkplug.component.sharesdk.ShareSdk;

import cn.sharesdk.demo.CustomShareFieldsPage;
import cn.sharesdk.demo.MainActivity;
import cn.sharesdk.demo.R;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;


/**
 * Created by qinfeng on 15/10/8.
 */
public class ShareSdkImp extends ShareSdk {

    private Context mContext=null;

    public ShareSdkImp(Context mContext) {
        this.mContext = mContext;
    }



	@Override
	public void start() {
		showShare(true, null, false);
	}

	@Override
	public int versionInPlug() {
		return 1;
	}
	private void showShare(boolean silent, String platform, boolean captureView) {

		final OnekeyShare oks = new OnekeyShare();

		//oks.setAddress("12345678901");
		oks.setTitle(CustomShareFieldsPage.getString("title", mContext.getString(R.string.evenote_title)));
		oks.setTitleUrl(CustomShareFieldsPage.getString("titleUrl", "http://mob.com"));
		String customText = CustomShareFieldsPage.getString( "text", null);
		if (customText != null) {
			oks.setText(customText);
		} else if (MainActivity.TEST_TEXT != null && MainActivity.TEST_TEXT.containsKey(0)) {
			oks.setText(MainActivity.TEST_TEXT.get(0));
		} else {
			oks.setText(mContext.getString(R.string.share_content));
		}

//		if (captureView) {
//			oks.setViewToShare(getPage());
//		} else {
		oks.setImagePath(CustomShareFieldsPage.getString("imagePath", MainActivity.TEST_IMAGE));
//			oks.setImageUrl(CustomShareFieldsPage.getString("imageUrl", MainActivity.TEST_IMAGE_URL));
		//	oks.setImageArray(new String[]{MainActivity.TEST_IMAGE, MainActivity.TEST_IMAGE_URL});
//		}

		oks.setUrl(CustomShareFieldsPage.getString("url", "http://www.mob.com"));
		oks.setFilePath(CustomShareFieldsPage.getString("filePath", MainActivity.TEST_IMAGE));
		oks.setComment(CustomShareFieldsPage.getString("comment", mContext.getString(R.string.share)));
		oks.setSite(CustomShareFieldsPage.getString("site", mContext.getString(R.string.app_name)));
		oks.setSiteUrl(CustomShareFieldsPage.getString("siteUrl", "http://mob.com"));
		oks.setVenueName(CustomShareFieldsPage.getString("venueName", "ShareSDK"));
		oks.setVenueDescription(CustomShareFieldsPage.getString("venueDescription", "This is a beautiful place!"));
		oks.setSilent(silent);
		//oks.setShareFromQQAuthSupport(shareFromQQLogin);
		String theme = CustomShareFieldsPage.getString("theme", "classic");
		if(OnekeyShareTheme.SKYBLUE.toString().toLowerCase().equals(theme)){
			oks.setTheme(OnekeyShareTheme.SKYBLUE);
		}else{
			oks.setTheme(OnekeyShareTheme.CLASSIC);
		}

		if (platform != null) {
			oks.setPlatform(platform);
		}


		// 令编辑页面显示为Dialog模式
		oks.setDialogMode();

		// 在自动授权时可以禁用SSO方式
		//if(!CustomShareFieldsPage.getBoolean("enableSSO", true))
		oks.disableSSOWhenAuthorize();

		// 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
		//oks.setCallback(new OneKeyShareCallback());

		// 去自定义不同平台的字段内容
		//oks.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());

		// 去除注释，演示在九宫格设置自定义的图标
		Bitmap enableLogo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
		Bitmap disableLogo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.sharesdk_unchecked);
		String label = mContext.getResources().getString(R.string.app_name);
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				String text = "Customer Logo -- ShareSDK " + ShareSDK.getSDKVersionName();
				Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
			}
		};
		oks.setCustomerLogo(enableLogo, disableLogo, label, listener);

		// 去除注释，则快捷分享九宫格中将隐藏新浪微博和腾讯微博
//		oks.addHiddenPlatform(SinaWeibo.NAME);
//		oks.addHiddenPlatform(TencentWeibo.NAME);

		// 为EditPage设置一个背景的View
		//oks.setEditPageBackground(getPage());
		oks.show(mContext);
	}
}
