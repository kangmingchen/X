package com.x.app;

import java.util.Hashtable;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.Message;

/**
 * <p>
 * Description:全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月15日
 */
public class AppContext extends Application {
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	// 默认分页大小
	public static final int PAGE_SIZE = 20;
	// 缓存失效时间
	private static final int CACHE_TIME = 60 * 60000;

	// 登录状态
	private boolean login = false;
	// 登陆用户的id
	private int loginUid = 0;
	// 缓存
	private Hashtable<String, Object> memCacheRegion = new Hashtable<String, Object>();
	// 保存图片路径
	private String saveImagePath;

	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// 注册App异常奔溃处理器
		Thread.setDefaultUncaughtExceptionHandler(null);
	}

}
