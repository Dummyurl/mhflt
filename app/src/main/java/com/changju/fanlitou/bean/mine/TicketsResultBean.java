package com.changju.fanlitou.bean.mine;

/**
 * Created by chengww on 2017/7/28.
 */

public class TicketsResultBean {

    /**
     * message : 加息券使用成功
     * success : true
     */

    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
