package com.qiang.framework.qumiad;

import android.app.Activity;
import android.content.Context;

import com.dgsdk.cp.QMCPConnect;
import com.dgsdk.inter.QMExitListener;

//插屏广告
public class SpotAdPlugin
{
	private static final String tag = "SpotAdPlugin";

	public static void init(Context context) {
		QMCPConnect.getQumiConnectInstance(context).initPopAd(context);
	}

	public static void show(Context context)
	{
		QMCPConnect.getQumiConnectInstance(context).showPopUpAd(context);
	}

	public static void showExitAd(final Activity activity)
	{
		QMCPConnect.getQumiConnectInstance(activity).showQuMiExitAd(activity, new QMExitListener() {
			@Override
			public void onExit() {
				activity.finish();
			}
		});
	}
}
