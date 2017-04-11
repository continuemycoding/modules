package com.qiang.framework.xiaomiad;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qiang.framework.MyApplication;
import com.xiaomi.ad.AdListener;
import com.xiaomi.ad.adView.InterstitialAd;
import com.xiaomi.ad.common.pojo.AdError;
import com.xiaomi.ad.common.pojo.AdEvent;

public class SpotAdPlugin
{
	private static final String TAG = "SpotAdPlugin";

	private static InterstitialAd mInterstitialAd;

	private static final String POSITION_ID = "bb688b4c3ac88a6fc2213a89776906fe";

	public static void init(Context context) {
		Activity activity = MyApplication.getCurrentActivity();
		mInterstitialAd = new InterstitialAd(context, activity);
		mInterstitialAd = new InterstitialAd(context, activity.getWindow().getDecorView());
	}

	public static void requestAd(Context context)
	{
		try {
			if (mInterstitialAd.isReady()) {
				Log.e(TAG, "ad has been cached");
			} else {
				mInterstitialAd.requestAd(POSITION_ID, new AdListener() {
					@Override
					public void onAdError(AdError adError) {
						Log.e(TAG, "onAdError : " + adError.toString());
					}

					@Override
					public void onAdEvent(AdEvent adEvent) {
						try {
							switch (adEvent.mType) {
								case AdEvent.TYPE_SKIP:
									//用户关闭了广告
									Log.e(TAG, "ad skip!");
									break;
								case AdEvent.TYPE_CLICK:
									//用户点击了广告
									Log.e(TAG, "ad click!");
									break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onAdLoaded() {
						Log.e(TAG, "ad is ready : " + mInterstitialAd.isReady());
					}

					@Override
					public void onViewCreated(View view) {
						//won't be invoked
					}
				});

				Toast.makeText(context, "加载中...", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void show(Context context)
	{
		if (!mInterstitialAd.isReady()) {
			requestAd(context);
		} else {
			mInterstitialAd.show();
		}
	}

	public boolean isShowing(Context context) {
		return false;
	}

	public static void hide(Context context) {

	}

	public static void destroy(Context context) {

	}
}
