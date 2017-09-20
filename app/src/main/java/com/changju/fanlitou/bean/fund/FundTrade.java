package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/7/17.
 */

public class FundTrade {

    /**
     * hs300_rate : [0,-0.04,0.16,0.68,-0.36,-0.25,0.24,-0.23,0.11,0.91,0.58,0.34,0.21,-0.62,1.32,1.62,1.72,1.36,1.71,1.48]
     * rate : [0,-0.13,0.69,0.19,-0.38,-0.06,2.38,3.38,3.26,3.32,1.82,1.75,2.13,1.5,2.5,2.76,2.57,1.19,0.5,1.06]
     * hs_300_increase : 1.48
     * increase_till_now : 1.06
     * is_show_hs300 : true
     * offset : 8
     * date : ["17-03-13","17-03-14","17-03-15","17-03-16","17-03-17","17-03-20","17-03-21","17-03-22","17-03-23","17-03-24","17-03-27","17-03-28","17-03-29","17-03-30","17-04-05","17-04-06","17-04-07","17-04-10","17-04-11","17-04-12"]
     * income_tab_name : 收益率走势
     * real_tab_name : 净值走势
     * unit : %
     */

    private String hs_300_increase;
    private String increase_till_now;
    private boolean is_show_hs300;
    private int offset;
    private String income_tab_name;
    private String real_tab_name;
    private String unit;
    private List<Float> hs300_rate;
    private List<Float> rate;
    private List<String> date;

    public String getHs_300_increase() {
        return hs_300_increase;
    }

    public void setHs_300_increase(String hs_300_increase) {
        this.hs_300_increase = hs_300_increase;
    }

    public String getIncrease_till_now() {
        return increase_till_now;
    }

    public void setIncrease_till_now(String increase_till_now) {
        this.increase_till_now = increase_till_now;
    }

    public boolean isIs_show_hs300() {
        return is_show_hs300;
    }

    public void setIs_show_hs300(boolean is_show_hs300) {
        this.is_show_hs300 = is_show_hs300;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getIncome_tab_name() {
        return income_tab_name;
    }

    public void setIncome_tab_name(String income_tab_name) {
        this.income_tab_name = income_tab_name;
    }

    public String getReal_tab_name() {
        return real_tab_name;
    }

    public void setReal_tab_name(String real_tab_name) {
        this.real_tab_name = real_tab_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Float> getHs300_rate() {
        return hs300_rate;
    }

    public void setHs300_rate(List<Float> hs300_rate) {
        this.hs300_rate = hs300_rate;
    }

    public List<Float> getRate() {
        return rate;
    }

    public void setRate(List<Float> rate) {
        this.rate = rate;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }
}
