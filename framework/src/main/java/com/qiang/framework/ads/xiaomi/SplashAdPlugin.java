package com.qiang.framework.ads.xiaomi;

import android.content.Context;
import android.view.ViewGroup;

import com.qiang.framework.helpers.ReflectHelper;
import com.qiang.framework.listener.SplashAdListener;

public class SplashAdPlugin {
	public static void requestAd(Context context, ViewGroup container, String id, SplashAdListener splashAdListener) {
		ReflectHelper.invokeStaticMethod("com.qiang.framework.xiaomiad.SplashAdPlugin", "requestAd", new Object[]{context, container, id, splashAdListener}, Context.class, ViewGroup.class, String.class, SplashAdListener.class);
	}
}
