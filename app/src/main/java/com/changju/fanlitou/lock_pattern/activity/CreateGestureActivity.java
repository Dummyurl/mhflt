package com.changju.fanlitou.lock_pattern.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.lock_pattern.util.cache.ACache;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.UserUtils;
import com.star.lockpattern.util.LockPatternUtil;
import com.star.lockpattern.widget.LockPatternIndicator;
import com.star.lockpattern.widget.LockPatternView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * create gesture activity
 * Created by Sym on 2015/12/23.
 */
public class CreateGestureActivity extends BaseActivity {

	@Bind(R.id.lockPatterIndicator)
	LockPatternIndicator lockPatternIndicator;
	@Bind(R.id.lockPatternView)
	LockPatternView lockPatternView;
	@Bind(R.id.resetBtn)
	TextView resetBtn;
	@Bind(R.id.messageTv)
	TextView messageTv;
	@Bind(R.id.tv_time)
	TextView tv_time;
	@Bind(R.id.tv_hint)
	TextView tv_hint;

	private List<LockPatternView.Cell> mChosenPattern = null;
	private ACache aCache;
	private static final long DELAYTIME = 600L;
	private static final String TAG = "CreateGestureActivity";
	private String userName;

	@Override
	public void initParams(Bundle params) {

	}

	@Override
	public int bindLayout() {
		return R.layout.activity_create_gesture;
	}

	@Override
	public void initView(View view) {
		//不侵入状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			LinearLayout.LayoutParams params =
					new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,NormalUtils.getStatusBarHeight(this));
			findViewById(R.id.tv_status_bar).setLayoutParams(params);
		}

		if (!UserUtils.isLogin(this)){
			NormalUtils.showToast(this,"登录后才能设置手势密码!");
			finish();
		}
		ButterKnife.bind(this);
		aCache = ACache.get(this);
		lockPatternView.setOnPatternListener(patternListener);
		findViewById(R.id.iv_back).setOnClickListener(this);

		userName = UserUtils.getUserName(this);

		updateStatus(Status.DEFAULT, null);

	}

	@Override
	public void doBusiness(Context mContext) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String dayStr = day < 10 ? "0" + day : String.valueOf(day);
		String monthStr = "  Unknown";
		switch (month){
			case 0:
				monthStr = "  January";
				break;
			case 1:
				monthStr = "  February";
				break;
			case 2:
				monthStr = "  March";
				break;
			case 3:
				monthStr = "  April";
				break;
			case 4:
				monthStr = "  May";
				break;
			case 5:
				monthStr = "  June";
				break;
			case 6:
				monthStr = "  July";
				break;
			case 7:
				monthStr = "  August";
				break;
			case 8:
				monthStr = "  September";
				break;
			case 9:
				monthStr = "  October";
				break;
			case 10:
				monthStr = "  November";
				break;
			case 11:
				monthStr = "  December";
				break;
		}

		SpannableString spannableString = new SpannableString(dayStr + monthStr);
		RelativeSizeSpan sizeSpan = new RelativeSizeSpan(3.4615f);
		spannableString.setSpan(sizeSpan, 0, 2,
				Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		tv_time.setText(spannableString);

		tv_hint.setText(userName.substring(0,3)
				+ "****" + userName.substring(7, userName.length())
				+ "，请设置手势密码。");
	}

	@Override
	public void widgetClick(View v) {
		switch (v.getId()){
			case R.id.iv_back:
				finish();
				UserUtils.setSkipGesture(this,userName,true);
				break;
		}
	}


	/**
	 * 手势监听
	 */
	private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

		@Override
		public void onPatternStart() {
			lockPatternView.removePostClearPatternRunnable();
			//updateStatus(Status.DEFAULT, null);
			lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
		}

		@Override
		public void onPatternComplete(List<LockPatternView.Cell> pattern) {
			//Log.e(TAG, "--onPatternDetected--");
			if(mChosenPattern == null && pattern.size() >= 4) {
				mChosenPattern = new ArrayList<>(pattern);
				updateStatus(Status.CORRECT, pattern);
			} else if (mChosenPattern == null && pattern.size() < 4) {
				updateStatus(Status.LESSERROR, pattern);
			} else if (mChosenPattern != null) {
				if (mChosenPattern.equals(pattern)) {
					updateStatus(Status.CONFIRMCORRECT, pattern);
				} else {
					updateStatus(Status.CONFIRMERROR, pattern);
				}
			}
		}
	};

	/**
	 * 更新状态
	 * @param status
	 * @param pattern
     */
	private void updateStatus(Status status, List<LockPatternView.Cell> pattern) {
		messageTv.setTextColor(getResources().getColor(status.colorId));
		messageTv.setText(status.strId);
		switch (status) {
			case DEFAULT:
				lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
				if (aCache.hasGesture(userName)){
					resetBtn.setVisibility(View.GONE);
				}else {
					resetBtn.setVisibility(View.VISIBLE);
					resetBtn.setText("跳过");
					resetBtn.setTextColor(Color.parseColor("#999999"));
					resetBtn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							UserUtils.setSkipGesture(CreateGestureActivity.this,userName,true);
							CreateGestureActivity.this.finish();
						}
					});
				}
				break;
			case CORRECT:
				updateLockPatternIndicator();
				lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
				resetBtn.setVisibility(View.VISIBLE);
				resetBtn.setText(getResources().getString(R.string.create_gesture_reset));
				resetBtn.setTextColor(Color.parseColor("#538eeb"));
				resetBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						/**
						 * 重新设置手势
						 */
						mChosenPattern = null;
						lockPatternIndicator.setDefaultIndicator();
						updateStatus(Status.DEFAULT, null);
						lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
					}
				});
				break;
			case LESSERROR:
				lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
				break;
			case CONFIRMERROR:
				lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
				lockPatternView.postClearPatternRunnable(DELAYTIME);
				break;
			case CONFIRMCORRECT:
				saveChosenPattern(pattern);
				lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
				setLockPatternSuccess();
				break;
		}
	}

	/**
	 * 更新 Indicator
	 */
	private void updateLockPatternIndicator() {
		if (mChosenPattern == null)
			return;
		lockPatternIndicator.setIndicator(mChosenPattern);
	}


	/**
	 * 成功设置手势密码
     */
	private void setLockPatternSuccess() {
		finish();
	}

	/**
	 * 保存手势密码
	 */
	private void saveChosenPattern(List<LockPatternView.Cell> cells) {
		byte[] bytes = LockPatternUtil.patternToHash(cells);
		aCache.put(UserUtils.getUserName(this), bytes);
	}

	private enum Status {
		//默认的状态，刚开始的时候（初始化状态）
		DEFAULT(R.string.create_gesture_default, R.color.gesture_normal),
		//第一次记录成功
		CORRECT(R.string.create_gesture_correct, R.color.gesture_normal),
		//连接的点数小于4（二次确认的时候就不再提示连接的点数小于4，而是提示确认错误）
		LESSERROR(R.string.create_gesture_less_error, R.color.colorTextRed),
		//二次确认错误
		CONFIRMERROR(R.string.create_gesture_confirm_error, R.color.colorTextRed),
		//二次确认正确
		CONFIRMCORRECT(R.string.create_gesture_confirm_correct, R.color.gesture_normal);

		private Status(int strId, int colorId) {
			this.strId = strId;
			this.colorId = colorId;
		}
		private int strId;
		private int colorId;
	}

	@Override
	public void onBackPressed() {
		UserUtils.setSkipGesture(this,userName,true);
		super.onBackPressed();
	}

}
