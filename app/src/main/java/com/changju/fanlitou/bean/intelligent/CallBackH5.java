package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/8/29.
 */

public class CallBackH5 {

    /**
     * success : true
     * is_bid_fulled : false
     * title : 充值成功
     * content : 充值成功
     * callback_type : withdraw
     * jump_page :
     * bid_id :
     * platform_id : 47
     * flt_order_no : 123456789
     * platform_name : 和信贷
     * header_name : 通知
     */

    private boolean success;
    private boolean is_bid_fulled;
    private String title;
    private String content;
    private String callback_type;
    private String jump_page;
    private String bid_id;
    private int platform_id;
    private String flt_order_no;
    private String platform_name;
    private String header_name;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIs_bid_fulled() {
        return is_bid_fulled;
    }

    public void setIs_bid_fulled(boolean is_bid_fulled) {
        this.is_bid_fulled = is_bid_fulled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCallback_type() {
        return callback_type;
    }

    public void setCallback_type(String callback_type) {
        this.callback_type = callback_type;
    }

    public String getJump_page() {
        return jump_page;
    }

    public void setJump_page(String jump_page) {
        this.jump_page = jump_page;
    }

    public String getBid_id() {
        return bid_id;
    }

    public void setBid_id(String bid_id) {
        this.bid_id = bid_id;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getFlt_order_no() {
        return flt_order_no;
    }

    public void setFlt_order_no(String flt_order_no) {
        this.flt_order_no = flt_order_no;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getHeader_name() {
        return header_name;
    }

    public void setHeader_name(String header_name) {
        this.header_name = header_name;
    }
}
