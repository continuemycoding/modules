package com.qiang.framework.dangbeiupdate;

import android.content.Context;

import com.google.gson.Gson;
import com.qiang.framework.download.DownloadFileFromURL;
import com.qiang.framework.download.DownloadItem;
import com.qiang.framework.helpers.FileHelper;
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

                product.url = newProduct.download_url;
                product.versionName = newProduct.app_version;
                product.versionCode = newProduct.app_code;
                product.releaseNote = newProduct.app_updateinfo;
                product.app_scanimg = newProduct.app_scanimg;

                if(product.app_scanimg != null && !product.app_scanimg.isEmpty())
                    product.screenshotUrls = product.app_scanimg.split(",");

                ProductManager.save();

                if(updateManagerListener != null)
                    updateManagerListener.onUpdateAvailable(product);
            }
        };

        downloadItem.url = "http://api.dangbei.net/dbapinew/view_app.php?id=" + product.dangbei_appId;

        new DownloadFileFromURL().execute(new DownloadItem[]{downloadItem});
    }
}
