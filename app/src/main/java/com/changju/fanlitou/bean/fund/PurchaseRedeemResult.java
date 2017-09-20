package com.changju.fanlitou.bean.fund;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chengww on 2017/8/3.
 */

public class PurchaseRedeemResult {
    private String create_time;
    private String confirm_date;
    private String arrive_date;
    private String success_pay_date;
    private String interest_start_date;
    private String order_no;
    private String fund_name;
    private String fund_code;
    private int fund_id;
    private String shares;
    private String amount;

    @SerializedName("class")
    private int classX;

    private String faild_msg;
    private boolean success;
    private String bank_name;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(String confirm_date) {
        this.confirm_date = confirm_date;
    }

    public String getArrive_date() {
        return arrive_date;
    }

    public void setArrive_date(String arrive_date) {
        this.arrive_date = arrive_date;
    }

    public String getSuccess_pay_date() {
        return success_pay_date;
    }

    public void setSuccess_pay_date(String success_pay_date) {
        this.success_pay_date = success_pay_date;
    }

    public String getInterest_start_date() {
        return interest_start_date;
    }

    public void setInterest_start_date(String interest_start_date) {
        this.interest_start_date = interest_start_date;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public int getFund_id() {
        return fund_id;
    }

    public void setFund_id(int fund_id) {
        this.fund_id = fund_id;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getClassX() {
        return classX;
    }

    public void setClassX(int classX) {
        this.classX = classX;
    }

    public String getFaild_msg() {
        return faild_msg;
    }

    public void setFaild_msg(String faild_msg) {
        this.faild_msg = faild_msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
