package com.qiang.framework.ads.xiaomi;

import android.content.Context;
import android.view.ViewGroup;

import com.qiang.framework.helpers.ReflectHelper;

/**
 * Created by Administrator on 2017/3/24.
 */

public class BannerAdPlugin
{
    public static void requestAd(Context context, ViewGroup container, String id) {
        ReflectHelper.invokeMethod("com.qiang.framework.xiaomiad.BannerAdPlugin", "requestAd", new Object[]{context, container, id}, Context.class, ViewGroup.class, String.class);
    }

    public static void show()
    {
        ReflectHelper.invokeMethod("com.qiang.framework.xiaomiad.BannerAdPlugin", "show");
    }

    public static void hide()
    {
        ReflectHelper.invokeMethod("com.qiang.framework.xiaomiad.BannerAdPlugin", "hide");
    }
}
