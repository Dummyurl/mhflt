package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/10.
 */

public class InvestCalculator {
    /**
     * invest_income : 13.69
     * daily_bonus : 0
     * total_bonus : 0.27
     */

    private String invest_income;
    private String daily_bonus;
    private String total_bonus;

    public String getInvest_income() {
        return invest_income;
    }

    public void setInvest_income(String invest_income) {
        this.invest_income = invest_income;
    }

    public String getDaily_bonus() {
        return daily_bonus;
    }

    public void setDaily_bonus(String daily_bonus) {
        this.daily_bonus = daily_bonus;
    }

    public String getTotal_bonus() {
        return total_bonus;
    }

    public void setTotal_bonus(String total_bonus) {
        this.total_bonus = total_bonus;
    }
}
