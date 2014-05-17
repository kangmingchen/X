package com.x.app.ui;

import com.x.app.AppManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * <p>
 * Description:应用程序Activity的基类
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月17日
 */
public class BaseActivity extends Activity {
	// 是否允许全拼
	private boolean allowFullScreen = true;
	// 是否允许销毁
	private boolean allowDestroy = true;

	private View view;

	public boolean isAllowFullScreen() {
		return allowFullScreen;
	}

	public void setAllowFullScreen(boolean allowFullScreen) {
		this.allowFullScreen = allowFullScreen;
	}

	public boolean isAllowDestroy() {
		return allowDestroy;
	}

	public void setAllowDestroy(boolean allowDestroy, View view) {
		this.allowDestroy = allowDestroy;
		this.view = view;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		allowFullScreen = true;
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 结束Activity 从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && view != null) {
			view.onKeyDown(keyCode, event);
			if (!allowDestroy) {
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
