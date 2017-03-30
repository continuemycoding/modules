package com.qiang.framework.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.Toast;

import com.qiang.framework.download.ApkDownloader;
import com.qiang.framework.hook.LogHelper;
import com.qiang.framework.listener.UpdateManagerListener;
import com.qiang.framework.recommend.Product;
import com.qiang.framework.recommend.ProductManager;
import com.umeng.analytics.MobclickAgent;

public class UpdateManager
{
	public static void start(final Context context)
	{
		if(!SystemHelper.isWifiConnected(context))
			return;

//		DownloadItem downloadItem = new DownloadItem()
//		{
//			@Override
//			public void onDownloadCompleted(String path)
//			{
//				String json = FileHelper.readFileToString(path);
//
//				PlayerPrefs.setString("product.json", json);
//				PlayerPrefs.save();
//			}
//		};
//
//		downloadItem.url = "http://yqlang.com:8080/aplan/product.json";
//		downloadItem.cached = false;
//
//		new DownloadFileFromURL().execute(new DownloadItem[]{downloadItem});

		Product product = ProductManager.getProduct(context.getPackageName());
		if (product == null)
			return;

		start(context, product, SystemHelper.getVersionCode(context));
	}

	public static void start(final Context context, final Product product, final int versionCode)
	{
		start(context, product, versionCode, null);
	}

	public static void start(final Context context, final Product product, final int versionCode, final UpdateManagerListener updateManagerListener)
	{
		boolean selfUpdate = versionCode <= 0;

		if(product.versionCode > versionCode)
		{
			if(updateManagerListener != null)
			{
				updateManagerListener.onUpdateAvailable(product);
				return;
			}

			//String fileName = packageName + "-" + product.versionCode + ".apk";
			String fileName = product.packageName + ".apk";
			final String path = Environment.getExternalStorageDirectory() + "/Download/" + fileName;
			if(FileHelper.isFileExists(path))
			{
				if(selfUpdate)
				{
					new AlertDialog.Builder(context).setTitle("新版本已准备就绪，是否安装？").setNegativeButton("取消", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							MobclickAgent.onEvent(context, "installAppAlertDialog", "取消");
						}
					}).setPositiveButton("安装", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							MobclickAgent.onEvent(context, "installAppAlertDialog", "安装");
							SystemHelper.installApp(context, path);
						}
					}).show();

					return;
				}

				SystemHelper.installApp(context, path);
				return;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			if(selfUpdate)
			{
				builder.setTitle("升级版本 " + SystemHelper.getVersionName(context) + " -> " + product.versionName);
				builder.setMessage(product.releaseNote);
			}
			else
			{
				builder.setTitle("确认下载？");
			}

			builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					MobclickAgent.onEvent(context, "downloadAppAlertDialog", "取消");
				}
			}).setPositiveButton("下载", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					MobclickAgent.onEvent(context, "downloadAppAlertDialog", "下载");
					ApkDownloader.start(context, product);
				}
			}).show();
		}
		else
		{
			if(updateManagerListener != null)
			{
				updateManagerListener.onNoUpdateAvailable();
				return;
			}

			if(selfUpdate)
				LogHelper.info("已经是最新版本");
			else
				Toast.makeText(context, "产品版本号不对", Toast.LENGTH_SHORT).show();
		}
	}
}
