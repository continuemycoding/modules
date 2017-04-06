package com.qiang.framework.wanpuad;

import android.content.Context;

import cn.waps.AppConnect;
import cn.waps.AppListener;

/**
 * Created by Administrator on 2017/4/6.
 */

public class OffersAdPlugin {
    public static void show(Context context)
    {
        AppConnect.getInstance(context).showOffers(context);
    }

    public static void showApp(Context context)
    {
        AppConnect.getInstance(context).showAppOffers(context);
    }

    public static void showGame(Context context)
    {
        AppConnect.getInstance(context).showGameOffers(context);
    }

    public static void setOffersCloseListener(Context context)
    {
        //设置关闭积分墙癿监听接口，必须在showOffers接口之前调用
        AppConnect.getInstance(context).setOffersCloseListener(new AppListener(){
            @Override
            public void onOffersClose() {

            }
        });
    }
}
