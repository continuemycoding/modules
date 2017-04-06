package com.qiang.framework.wanpuad;

import android.content.Context;

import cn.waps.AppConnect;

/**
 * Created by Administrator on 2017/4/6.
 */

public class AdPlugin {
    public static void init(Context context)
    {
        AppConnect.getInstance("617c120e7cc98479ccec64098146dd66", "waps", context);
    }

    public static void close(Context context)
    {
        AppConnect.getInstance(context).close();
    }
}
