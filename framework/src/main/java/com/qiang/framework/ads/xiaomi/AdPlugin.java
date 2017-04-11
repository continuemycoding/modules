package com.qiang.framework.ads.xiaomi;

import android.content.Context;

import com.qiang.framework.helpers.ReflectHelper;

/**
 * Created by Administrator on 2017/4/12.
 */

public class AdPlugin {

    public static boolean hasAdModule()
    {
        try {
            Class.forName("com.xiaomi.ad.AdSdk");
        } catch (ClassNotFoundException e) {
            return false;
        }

        return true;
    }

    public static void setDebugOn()
    {
        ReflectHelper.invokeMethod("com.xiaomi.ad.AdSdk", "setDebugOn");
    }

    public static void setMockOn()
    {
        ReflectHelper.invokeMethod("com.xiaomi.ad.AdSdk", "setMockOn");
    }

    public static void initialize(Context context, String id)
    {
        ReflectHelper.invokeMethod("com.xiaomi.ad.AdSdk", "initialize", new Object[]{context, id}, Context.class, String.class);
    }
}
