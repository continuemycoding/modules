package com.qiang.framework.qumiad;

import android.content.Context;

import com.dgsdk.cp.QMCPConnect;

/**
 * Created by Administrator on 2017/4/6.
 */

public class AdPlugin {
    public static void init(Context context)
    {
        QMCPConnect.ConnectQuMi(context, "4e8df4b937bddc24", "7fe566a5759a4c26");
    }
}
