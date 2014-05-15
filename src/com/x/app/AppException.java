package com.x.app;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Looper;

/**
 * <p>
 * Description:应用程序异常类：用于捕获异常和提示错误信息
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月15日
 */
public class AppException extends Exception implements UncaughtExceptionHandler {

	// 是否保存错误日志
	private final static boolean Debug = false;

	/** 定义异常类型 */
	public final static byte TYPE_NETWORK = 0x01;
	public final static byte TYPE_SOCKET = 0x02;
	public final static byte TYPE_HTTP_CODE = 0x03;
	public final static byte TYPE_HTTP_ERROR = 0x04;
	public final static byte TYPE_XML = 0x05;
	public final static byte TYPE_IO = 0x06;
	public final static byte TYPE_RUN = 0x07;

	private byte type;
	private int code;

	/**
	 * 系统默认的 UncaughtException 处理类
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	private AppException() {
		this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	private AppException(byte type, int code, Exception excp) {
		super(excp);
		this.type = type;
		this.code = code;
		if (Debug) {

		}
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub

	}

	/**
	 * 自定义异常处理：收集错误信息&发送错误报告
	 * @param ex
	 * @return true：处理了该异常信息；返回返回false
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}

		final Context context = AppManager.getAppManager().currentActivity();
		if (context == null) {
			return false;
		}

		final String crashReport = getCrashReport(context, ex);

		// 显示异常信息&发送报告
		new Thread() {
			public void run() {
				Looper.prepare();
			}
		}.start();
		return true;
	}

	/**
	 * 获取APP奔溃异常报告
	 * 
	 * @param context
	 * @param ex
	 * @return
	 */
	private String getCrashReport(Context context, Throwable ex) {
		PackageInfo pInfo = ((AppContext) context.getApplicationContext()).getPackageInfo();
		StringBuffer exceptionStr = new StringBuffer();
		exceptionStr.append("Version:").append(pInfo.versionName).append("(").append(pInfo.versionCode).append(")\n");
		exceptionStr.append("Android:").append(android.os.Build.VERSION.RELEASE).append("(").append(android.os.Build.MODEL).append(")\n");
		exceptionStr.append("Exception:").append(ex.getMessage()).append("\n");
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; i++) {
			exceptionStr.append(elements[i].toString()).append("\n");
		}
		return exceptionStr.toString();
	}

}
