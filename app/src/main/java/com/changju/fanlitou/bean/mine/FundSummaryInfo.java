package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class FundSummaryInfo {

    /**
     * total_income : -586.56
     * invest_amount : 58719.02
     * risk_level : 激进型
     * status_list : [{"status_str":"买入中","status":1},{"status_str":"赎回中","status":2},{"status_str":"已赎回","status":3},{"status_str":"已持有","status":4},{"status_str":"买入失败","status":5},{"status_str":"赎回失败","status":6}]
     * yesterday_income : 0.00
     */

    private String total_income;
    private String invest_amount;
    private String risk_level;
    private String yesterday_income;
    private List<StatusListBean> status_list;
    private boolean show_holding_higher_fund;

    public boolean isShow_holding_higher_fund() {
        return show_holding_higher_fund;
    }

    public void setShow_holding_higher_fund(boolean show_holding_higher_fund) {
        this.show_holding_higher_fund = show_holding_higher_fund;
    }

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public String getInvest_amount() {
        return invest_amount;
    }

    public void setInvest_amount(String invest_amount) {
        this.invest_amount = invest_amount;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public String getYesterday_income() {
        return yesterday_income;
    }

    public void setYesterday_income(String yesterday_income) {
        this.yesterday_income = yesterday_income;
    }

    public List<StatusListBean> getStatus_list() {
        return status_list;
    }

    public void setStatus_list(List<StatusListBean> status_list) {
        this.status_list = status_list;
    }

    public static class StatusListBean {
        /**
         * status_str : 买入中
         * status : 1
         */

        private String status_str;
        private int status;

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
