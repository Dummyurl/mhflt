package com.changju.fanlitou.lock_pattern.activity;

import android.content.Context;
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
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.lock_pattern.util.cache.ACache;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.UserUtils;
import com.star.lockpattern.util.LockPatternUtil;
import com.star.lockpattern.widget.LockPatternView;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GestureLoginActivity extends BaseActivity {

    private int maxCount = 5;
    private Bundle args;

    private static final String TAG = "LoginGestureActivity";

    @Bind(R.id.lockPatternView)
    LockPatternView lockPatternView;
    @Bind(R.id.messageTv)
    TextView messageTv;
    @Bind(R.id.forgetGestureBtn)
    TextView forgetGestureBtn;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_hint)
    TextView tv_hint;

    private ACache aCache;
    private static final long DELAYTIME = 600l;
    private byte[] gesturePassword;
    private String userName;

    @Override
    public void initParams(Bundle params) {
        args = params;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_gesture_login;
    }

    @Override
    public void initView(View view) {
        //不侵入状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        if (!UserUtils.isLogin(this)){
            NormalUtils.showToast(this,"错误，用户未登录！");
            finish();
        }
        ButterKnife.bind(this);
        aCache = ACache.get(GestureLoginActivity.this);
        lockPatternView.setOnPatternListener(patternListener);
        updateStatus(Status.DEFAULT);

        // 禁止唤起手势页
        disablePatternLock(false);

    }

    @Override
    public void doBusiness(Context mContext) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = day < 10 ? "0" + day : String.valueOf(day);
        String monthStr = "  Unknown";
        switch (month) {
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

        String userName = UserUtils.getUserName(this);
        tv_hint.setText("欢迎回来，" + userName.substring(0, 3)
                + "****" + userName.substring(7, userName.length()));
    }

    @Override
    public void widgetClick(View v) {

    }

    private LockPatternView.OnPatternListener patternListener = new LockPatternView.OnPatternListener() {

        @Override
        public void onPatternStart() {
            lockPatternView.removePostClearPatternRunnable();
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> pattern) {
            if (pattern != null) {
                if (LockPatternUtil.checkPattern(pattern, gesturePassword)) {
                    updateStatus(Status.CORRECT);
                } else {
                    updateStatus(Status.ERROR);
                }
            }
        }
    };

    /**
     * 更新状态
     *
     * @param status
     */
    private void updateStatus(Status status) {
        messageTv.setText(status.strId);
        messageTv.setTextColor(getResources().getColor(status.colorId));
        switch (status) {
            case DEFAULT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                break;
            case ERROR:
                if (maxCount > 1){
                    lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                    lockPatternView.postClearPatternRunnable(DELAYTIME);
                    messageTv.append("，还可以输入" + --maxCount +"次");
                }else {
                    lockPatternView.setPattern(LockPatternView.DisplayMode.ERROR);
                    lockPatternView.postClearPatternRunnable(DELAYTIME);
                    messageTv.setText("达到最大可用次数，请重新登录");
                    forgetGesturePasswrod();
                }

                break;
            case CORRECT:
                lockPatternView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                loginGestureSuccess();
                break;
        }
    }

    /**
     * 手势登录成功
     */
    private void loginGestureSuccess() {
//        startActivity(MainActivity.class,args);
        //重置剩余密码次数
        maxCount = 5;

        finish();
    }

    /**
     * 忘记手势密码（去账号登录界面）
     */
    @OnClick(R.id.forgetGestureBtn)
    void forgetGesturePasswrod() {
        //重置剩余密码次数并重新设置
        maxCount = 5;
        UserUtils.setSkipGesture(this,userName,false);

        String phone = UserUtils.getUserName(this);
        aCache.clearGesture(UserUtils.getUserName(this));
        UserUtils.clearLogin(this);
        Bundle args = new Bundle();
        args.putString(LoginActivity.class.getSimpleName(), phone);
        startActivity(LoginActivity.class, args);
        finish();
    }

    @OnClick(R.id.changeAccountBtn)
    void changeAccount() {
        //重置剩余密码次数
        maxCount = 5;

        UserUtils.clearLogin(this);
        startActivity(LoginActivity.class);
        finish();
    }

    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_default, R.color.gesture_normal),
        //密码输入错误
        ERROR(R.string.gesture_error, R.color.colorTextRed),
        //密码输入正确
        CORRECT(R.string.gesture_correct, R.color.gesture_normal);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //得到当前用户的手势密码
        if (UserUtils.isLogin(this)) {
            userName = UserUtils.getUserName(this);
            gesturePassword = aCache.getAsBinary(userName);
            //获取剩余解锁次数
            maxCount = UserUtils.getLockCount(this,userName);
        }

    }

    @Override
    public void onBackPressed() {
        // 阻止Lock页面的返回事件
        moveTaskToBack(true);
    }

    //存剩余解锁次数
    @Override
    protected void onPause() {
        super.onPause();
        UserUtils.setLockCount(this,maxCount,userName);
    }
}
