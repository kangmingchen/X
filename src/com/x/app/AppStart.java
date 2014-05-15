package com.x.app;

import com.x.app.ui.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * <p>
 * Description:应用程序启动类：显示欢迎界面并跳转到主界面
 * </p>
 * 
 * @author Chenkm
 * @version 1.0
 * @date 2014年5月15日
 */
public class AppStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final View view = View.inflate(this, R.layout.start, null);
		setContentView(view);

		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				redirectTo();
			}
		});
	}

	/**
	 * 跳转到主界面
	 */
	private void redirectTo() {
		Intent intent = new Intent(this, Main.class);
		startActivity(intent);
		finish();
	}

}
