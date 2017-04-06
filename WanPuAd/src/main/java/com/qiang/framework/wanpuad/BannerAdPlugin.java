package com.qiang.framework.wanpuad;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.waps.AppConnect;
import cn.waps.AppListener;

/**
 * Created by Administrator on 2017/3/24.
 */

public class BannerAdPlugin
{
    public static void init(Activity context) {
        AppConnect.getInstance(context).setBannerAdNoDataListener(new AppListener(){
            @Override
            public void onBannerNoData() {
                Log.e("debug", "Banner广告无数据");
            }
        });

        LinearLayout adlayout = new LinearLayout(context);
        adlayout.setGravity(Gravity.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AppConnect.getInstance(context).showBannerAd(context, adlayout);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        context.addContentView(adlayout, layoutParams);
    }

    public static void destroy()
    {

    }
}
