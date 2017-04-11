package com.qiang.framework.xiaomiad;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xiaomi.ad.AdListener;
import com.xiaomi.ad.adView.H5BannerAd;
import com.xiaomi.ad.common.pojo.AdError;
import com.xiaomi.ad.common.pojo.AdEvent;

/**
 * Created by Administrator on 2017/3/24.
 */

public class BannerAdPlugin
{
    private static final String TAG = "BannerAdPlugin";

    private static ViewGroup container;

    public static void requestAd(Context context, final ViewGroup container, String id) {

        BannerAdPlugin.container = container;

        try {
            final H5BannerAd h5BannerAd = new H5BannerAd(context);
            h5BannerAd.requestAd(id, container.getWidth(), new AdListener() {
                @Override
                public void onAdError(AdError adError) {
                    Log.e(TAG, "onAdError : " + adError);
                }

                @Override
                public void onAdEvent(AdEvent adEvent) {
                    if (adEvent.mType == AdEvent.TYPE_CLICK) {
                        Log.d(TAG, "ad has been clicked!");
                    } else if (adEvent.mType == AdEvent.TYPE_SKIP) {
                        Log.d(TAG, "x button has been clicked!");
                    } else if (adEvent.mType == AdEvent.TYPE_VIEW) {
                        Log.d(TAG, "ad has been showed!");
                    }
                }

                @Override
                public void onAdLoaded() {
                    Log.i(TAG, "onAdLoaded");
                }

                @Override
                public void onViewCreated(View view) {
                    Log.i(TAG, "onViewCreated");

                    container.addView(view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show()
    {
        if(container != null)
            container.setVisibility(View.VISIBLE);
    }

    public static void hide()
    {
        if(container != null)
            container.setVisibility(View.GONE);
    }
}
