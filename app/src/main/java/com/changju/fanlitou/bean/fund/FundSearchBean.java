package com.changju.fanlitou.bean.fund;

/**
 * Created by chengww on 2017/7/26.
 */

public class FundSearchBean {
    private String name;
    private String num;
    private int fund_id;

    public int getFund_id() {
        return fund_id;
    }

    public void setFund_id(int fund_id) {
        this.fund_id = fund_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
