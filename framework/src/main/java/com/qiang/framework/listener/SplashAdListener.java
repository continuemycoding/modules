package com.qiang.framework.listener;

/**
 * Created by Administrator on 2017/4/12.
 */

public interface SplashAdListener
{
    void onAdPresent();

    void onAdClick();

    void onAdDismissed();

    void onAdFailed(String message);
}
