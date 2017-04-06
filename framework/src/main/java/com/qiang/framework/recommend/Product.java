package com.qiang.framework.recommend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Lin on 2016/7/2.
 */
public class Product
{
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String name;
    public String apptitle;//当贝
    public String packageName;
    public String packname;//当贝
    public int dangbei_appId;
    public String versionName;
    public String appver;//当贝
    public int versionCode;
    public int appcode;//当贝
    public String url;
    public String downurl;//当贝
    public String releaseNote;
    public String upinfo;//当贝
    public String[] screenshotUrls;
    public String piclist;//当贝

    public boolean multiplayer;
    public String app_type;
    public int dislike;

    @Override
    public String toString()
    {
        return gson.toJson(this);
    }
}
