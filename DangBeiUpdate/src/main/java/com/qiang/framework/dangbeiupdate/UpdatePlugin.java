package com.qiang.framework.dangbeiupdate;

import android.content.Context;

import com.google.gson.Gson;
import com.qiang.framework.download.DownloadFileFromURL;
import com.qiang.framework.download.DownloadItem;
import com.qiang.framework.helper.FileHelper;
import com.qiang.framework.listener.UpdateManagerListener;
import com.qiang.framework.recommend.Product;
import com.qiang.framework.recommend.ProductManager;

/**
 * Created by Administrator on 2017/3/31.
 */

public class UpdatePlugin
{
    public static void start(final Context context, final UpdateManagerListener updateManagerListener)
    {
        final Product product = ProductManager.getProduct(context.getPackageName());
        if (product == null)
            return;

        start(context, product, updateManagerListener);
    }

    public static void start(final Context context, final Product product, final UpdateManagerListener updateManagerListener)
    {
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
                product.piclist = newProduct.piclist;

                if(product.piclist != null && !product.piclist.isEmpty())
                    product.screenshotUrls = product.piclist.split(",");

                ProductManager.save();

                if(updateManagerListener != null)
                    updateManagerListener.onUpdateAvailable(product);
            }
        };

        downloadItem.url = "http://api.dangbei.net/dbapinew/view.php?id=" + product.dangbei_appId;

        new DownloadFileFromURL().execute(new DownloadItem[]{downloadItem});
    }
}
