package com.x.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

/**
 * <p>
 * Description:应用程序配置类：用于保存用户相关信息及设置
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月17日
 */
public class AppConfig {
	/**
	 * APP全名
	 */
	public final static String APP_NAME = "x";
	private final static String APP_CONFIG = "config";

	private final static String PERF = "perf_";

	/**
	 * 加载图片
	 */
	public final static String CONF_LOAD_IMAGE = PERF + "loadimage";

	/**
	 * 左右滑动
	 */
	public final static String CONF_SCROLL = PERF + "scroll";

	/**
	 * 检测更新
	 */
	public final static String CONF_CHECKUP = PERF + "checkup";
	
	/**
	 * APP唯一标识符
	 */
	public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";

	public final static String SAVE_IMAGE_PATH = "save_image_path";
	public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + APP_NAME + File.separator;

	private Context mContext;
	private static AppConfig appConfig;

	public static AppConfig getAppConfig(Context context) {
		if (appConfig == null) {
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}

	/**
	 * 获取Preference设置
	 * 
	 * @param context
	 * @return
	 */
	public static SharedPreferences getSharedPreferences(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * 是否加载显示文章图片
	 */
	public static boolean isLoadImage(Context context) {
		return getSharedPreferences(context).getBoolean(CONF_LOAD_IMAGE, true);
	}

	public void set(Properties ps) {
		Properties props = get();
		props.putAll(ps);
		setProps(props);
	}

	public void set(String key, String value) {
		Properties props = get();
		props.setProperty(key, value);
		setProps(props);
	}

	public void remove(String... key) {
		Properties props = get();
		for (String k : key)
			props.remove(k);
		setProps(props);
	}

	public String get(String key) {
		Properties props = get();
		return (props != null) ? props.getProperty(key) : null;
	}

	public Properties get() {
		FileInputStream fis = null;
		Properties props = new Properties();
		try {
			// 读取app_config目录下的config
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);
			props.load(fis);
		} catch (Exception e) {

		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			} finally {
				fis = null;
			}
		}
		return props;
	}

	public void setProps(Properties p) {
		FileOutputStream fos = null;
		try {
			// 把config建在(自定义)app_config的目录下
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			File conf = new File(dirConf, APP_CONFIG);
			fos = new FileOutputStream(conf);

			p.store(fos, null);
			fos.flush();
		} catch (Exception e) {

		} finally {
			try {
				fos.close();
			} catch (Exception e) {

			} finally {
				fos = null;
			}
		}
	}
}
