package com.qiang.framework.wanpuad;

import android.content.Context;

import cn.waps.AppConnect;
import cn.waps.AppListener;

public class SpotAdPlugin
{
	public static void init(Context context) {
		AppConnect.getInstance(context).initPopAd(context);
	}

	public static void show(Context context)
	{
		//AppConnect.getInstance(context).setPopAdBack(true);

		AppConnect.getInstance(context).showPopAd(context, new AppListener(){
			@Override
			public void onPopClose() {super.onPopClose(); }
		});
	}
}
