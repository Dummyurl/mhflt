package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/7.
 */

public class SMS {
    /**
     * msg : 获取短信验证码成功
     * sms_auth_token : F338F0A7F417BFF92BE0BEE11BD6BEC46B8C4F97E6A25D2D5F1421246782A3C7C6B33DE447AE91CA0F13B3FD62725DD67174B28B06912C01
     * success : true
     */

    private String msg;
    private String sms_auth_token;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSms_auth_token() {
        return sms_auth_token;
    }

    public void setSms_auth_token(String sms_auth_token) {
        this.sms_auth_token = sms_auth_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
