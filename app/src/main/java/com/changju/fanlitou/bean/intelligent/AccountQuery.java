package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/18.
 */

public class AccountQuery {
    /**
     * msg : 注册失败
     * is_able_to_invest : false
     * success : false
     */

    private String msg;
    private boolean is_able_to_invest;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIs_able_to_invest() {
        return is_able_to_invest;
    }

    public void setIs_able_to_invest(boolean is_able_to_invest) {
        this.is_able_to_invest = is_able_to_invest;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
