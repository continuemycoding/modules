package com.qiang.framework.xiaomiad;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.qiang.framework.listener.SplashAdListener;
import com.xiaomi.ad.adView.SplashAd;

public class SplashAdPlugin
{
    private static final String TAG = "SplashAdPlugin";

    public static void requestAd(Context context, ViewGroup container, String id, final SplashAdListener splashAdListener) {
        SplashAd splashAd = new SplashAd(context, container, new com.xiaomi.ad.SplashAdListener() {
            @Override
            public void onAdPresent() {
                // 开屏广告展示
                Log.d(TAG, "onAdPresent");
                splashAdListener.onAdPresent();
            }

            @Override
            public void onAdClick() {
                //用户点击了开屏广告
                Log.d(TAG, "onAdClick");
                splashAdListener.onAdClick();
            }

            @Override
            public void onAdDismissed() {
                //这个方法被调用时，表示从开屏广告消失。
                Log.d(TAG, "onAdDismissed");
                splashAdListener.onAdDismissed();
            }

            @Override
            public void onAdFailed(String s) {
                Log.d(TAG, "onAdFailed, message: " + s);
                //这个方法被调用时，表示从服务器端请求开屏广告时，出现错误。
                splashAdListener.onAdFailed(s);
            }
        });

        splashAd.requestAd(id);
    }
}