package com.qiang.framework.dangbeiupdate;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.google.gson.Gson;
import com.qiang.framework.download.DownloadFileFromURL;
import com.qiang.framework.download.DownloadItem;
import com.qiang.framework.helper.FileHelper;
import com.qiang.framework.helper.PlayerPrefs;
import com.qiang.framework.helper.UpdateManager;
import com.qiang.framework.recommend.Product;
import com.qiang.framework.recommend.ProductManager;

import org.apache.commons.lang3.RandomUtils;

/**
 * Created by Administrator on 2017/3/31.
 */

public class UpdatePlugin
{
    public static void start(final Context context)
    {
        final Product product = ProductManager.getProduct(context.getPackageName());
        if (product == null)
            return;

        DownloadItem downloadItem = new DownloadItem()
        {
            @Override
            public void onDownloadCompleted(String path)
            {
                String json = FileHelper.readFileToString(path);
                Product newProduct = new Gson().fromJson(json, Product.class);

                product.url = newProduct.downurl;
                product.versionName = newProduct.appver;
                product.versionCode = newProduct.appcode;
                product.releaseNote = newProduct.upinfo;
                //product.piclist = newProduct.piclist;
                //product.screenshotUrls = null;

                ProductManager.save();

                UpdateManager.start(context);
            }
        };

        downloadItem.url = "http://api.dangbei.net/dbapinew/view.php?id=" + product.dangbei_appId;

        new DownloadFileFromURL().execute(new DownloadItem[]{downloadItem});
    }
}
