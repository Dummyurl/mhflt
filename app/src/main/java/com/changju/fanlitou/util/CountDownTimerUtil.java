package com.changju.fanlitou.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by ZM on 2017/5/17.
 */
public class CountDownTimerUtil extends CountDownTimer {
    private TextView button;

    /**
     * @param button          The TextView
     *
     *
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receiver
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtil(TextView button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setEnabled(false); //设置不可点击
        button.setText(millisUntilFinished / 1000 + "S");  //设置倒计时时间
    }

    @Override
    public void onFinish() {
        button.setText("重新获取");
        button.setEnabled(true);//重新获得点击
    }

    public void onClear(){
        button.setText("获取");
        button.setEnabled(true);
    }

}
