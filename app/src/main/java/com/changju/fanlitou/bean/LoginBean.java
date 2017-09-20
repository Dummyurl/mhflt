package com.changju.fanlitou.bean;

/**
 * Created by chengww on 2017/5/16.
 */

public class LoginBean {

    /**
     * msg : 登录成功
     * login_token : WB03Q8KZZQO7IVJ9TQIS
     * success : true
     */

    private String msg;
    private String login_token;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getLogin_token() {
        return login_token;
    }

    public void setLogin_token(String login_token) {
        this.login_token = login_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
