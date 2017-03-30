package com.qiang.framework.recommend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qiang.framework.helper.FileHelper;
import com.qiang.framework.helper.PlayerPrefs;
import com.qiang.framework.helper.Utils;

/**
 * Created by Lin on 2016/7/11.
 */
public class ProductManager
{
    private static Product[] products;

    private static Gson gson = new GsonBuilder().create();

    static
    {
        String json = PlayerPrefs.getString("product.json");
        if(!json.equals(""))
        {
            try
            {
                products = gson.fromJson(json, Product[].class);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if(products == null)
        {
            json = FileHelper.readAssetFileToString("product.json");
            products = gson.fromJson(json, Product[].class);
        }

        for(int i=0;i<products.length;i++)
        {
            Product product = products[i];
            if(product.piclist != null && !product.piclist.isEmpty())
                product.screenshotUrls = product.piclist.split(",");
        }

        Utils.shuffleArray(products);
    }

    public static Product[] getProducts()
    {
        return products;
    }

    public static Product getProduct(String packageName)
    {
        for (Product product : products)
        {
            if(product.packageName.equals(packageName))
                return product;
        }

        return null;
    }

    public static void save()
    {
        String json = gson.toJson(products);
        PlayerPrefs.setString("product.json", json);
        PlayerPrefs.save();
    }
}
