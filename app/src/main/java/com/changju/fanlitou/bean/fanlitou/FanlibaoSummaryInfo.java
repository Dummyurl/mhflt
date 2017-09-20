package com.changju.fanlitou.bean.fanlitou;

/**
 * Created by zhangming on 2017/8/10.
 */

public class FanlibaoSummaryInfo{

    /**
     * year_yld : 4.45
     * notify_info : {"amount":"0.00","is_show":false}
     * latest_income : 0.0000
     * accum_income : 0.0000
     * accum_income_class : zero
     * balance : 122766.55
     * is_open_jifen : true
     * latest_income_class : zero
     */

    private String year_yld;
    private NotifyInfoBean notify_info;
    private String latest_income;
    private String accum_income;
    private String accum_income_class;
    private String balance;
    private boolean is_open_jifen;
    private String latest_income_class;

    public String getYear_yld() {
        return year_yld;
    }

    public void setYear_yld(String year_yld) {
        this.year_yld = year_yld;
    }

    public NotifyInfoBean getNotify_info() {
        return notify_info;
    }

    public void setNotify_info(NotifyInfoBean notify_info) {
        this.notify_info = notify_info;
    }

    public String getLatest_income() {
        return latest_income;
    }

    public void setLatest_income(String latest_income) {
        this.latest_income = latest_income;
    }

    public String getAccum_income() {
        return accum_income;
    }

    public void setAccum_income(String accum_income) {
        this.accum_income = accum_income;
    }

    public String getAccum_income_class() {
        return accum_income_class;
    }

    public void setAccum_income_class(String accum_income_class) {
        this.accum_income_class = accum_income_class;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean isIs_open_jifen() {
        return is_open_jifen;
    }

    public void setIs_open_jifen(boolean is_open_jifen) {
        this.is_open_jifen = is_open_jifen;
    }

    public String getLatest_income_class() {
        return latest_income_class;
    }

    public void setLatest_income_class(String latest_income_class) {
        this.latest_income_class = latest_income_class;
    }

    public static class NotifyInfoBean {
        /**
         * amount : 0.00
         * is_show : false
         */

        private String amount;
        private boolean is_show;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public boolean isIs_show() {
            return is_show;
        }

        public void setIs_show(boolean is_show) {
            this.is_show = is_show;
        }
    }
}
