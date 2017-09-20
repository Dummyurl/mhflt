package com.changju.fanlitou.bean.fund;

/**
 * Created by Administrator on 2017/7/28.
 */

public class SmSBean {

    /**
     * success : false
     * order_id : 0
     * msg : 绑卡失败，请检查您的身份证、手机号、银行卡是否匹配。
     * is_already_bind_card : false
     */

    private boolean success;
    private String order_id;
    private String msg;
    private boolean is_already_bind_card;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIs_already_bind_card() {
        return is_already_bind_card;
    }

    public void setIs_already_bind_card(boolean is_already_bind_card) {
        this.is_already_bind_card = is_already_bind_card;
    }
}
