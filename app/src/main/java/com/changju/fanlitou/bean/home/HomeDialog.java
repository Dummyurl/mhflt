package com.changju.fanlitou.bean.home;

/**
 * Created by zhangming on 2017/8/14.
 */

public class HomeDialog {

    /**
     * url : http://m.test2.fanlitou.com/mobile/home_dialog/
     * is_show : true
     */

    private String url;
    private boolean is_show_dialog;
    private String user_cookie_value;
    private String no_user_cookie_value;

    public String getUser_cookie_value() {
        return user_cookie_value;
    }

    public void setUser_cookie_value(String user_cookie_value) {
        this.user_cookie_value = user_cookie_value;
    }

    public String getNo_user_cookie_value() {
        return no_user_cookie_value;
    }

    public void setNo_user_cookie_value(String no_user_cookie_value) {
        this.no_user_cookie_value = no_user_cookie_value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean is_show_dialog() {
        return is_show_dialog;
    }

    public void setIs_show_dialog(boolean is_show_dialog) {
        this.is_show_dialog = is_show_dialog;
    }
}
