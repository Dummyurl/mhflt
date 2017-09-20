package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/19.
 */

public class InvestResult {

    /**
     * status : FAIL
     * msg : 投资失败！
     * flt_order_no :
     */

    private String status;
    private String msg;
    private String flt_order_no;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFlt_order_no() {
        return flt_order_no;
    }

    public void setFlt_order_no(String flt_order_no) {
        this.flt_order_no = flt_order_no;
    }
}
