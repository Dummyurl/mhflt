package com.changju.fanlitou.bean.discover;

/**
 * Created by chengww on 2017/8/17.
 */

public class PlatformSearchBean {

    public PlatformSearchBean(String platform_name, String platform_name_show, int platform_id, String platform_logo_url) {
        this.platform_name = platform_name;
        this.platform_name_show = platform_name_show;
        this.platform_id = platform_id;
        this.platform_logo_url = platform_logo_url;
    }

    private String platform_name;
    private String platform_name_show;
    private int platform_id;
    private String platform_logo_url;

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getPlatform_name_show() {
        return platform_name_show;
    }

    public void setPlatform_name_show(String platform_name_show) {
        this.platform_name_show = platform_name_show;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getPlatform_logo_url() {
        return platform_logo_url;
    }

    public void setPlatform_logo_url(String platform_logo_url) {
        this.platform_logo_url = platform_logo_url;
    }
}
