package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/5.
 */

public class IntelligentInvest {

    /**
     * url :
     * result_type : open_account
     * success : true
     */

    private String url;
    private String result_type;
    private boolean success;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
