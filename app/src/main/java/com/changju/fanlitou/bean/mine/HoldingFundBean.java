package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class HoldingFundBean {

    /**
     * total : 3
     * result : [{"status":4,"invest_amount":"18.59",
     * "redemp_status":1,"share":"9.88","fund_type":"股票型","inco
     * me_rate":"0.00%","id":2051,"status_str":"已持有","name":"大摩品质生
     * 活股票","fund_code":"000309","total_income":0,"interest_start_date_notify":f
     * alse,"recent_income":0,"recent_nav":"1.8820","redemp_status_str":"可赎回"},{"status":4,
     * "invest_amount":"10.46","redemp_status":1,"share":"9.88","fund_type":"股票型","income_rate":"0.00%",
     * "id":2340,"status_str":"已持有","name":"招商医药股票","fund_code":"000960","total_income":0,"interest_start_dat
     * e_notify":false,"recent_income":0,"recent_nav":"1.0590","redemp_status_str":"可赎回"},{"status":4,"invest_amount":"59276.5
     * 2","redemp_status":1,"share":"59276.52","fund_type":"货币型","income_rate":"-0.02%","id":1157,"status_str":"已持有","name":"工银货币","f
     * und_code":"482002","total_income":"-586.56","interest_start_date_notify":false,"recent_income":0,"recent_nav":"--","redemp_status_str":"可赎回"}]
     */

    private int total;
    private List<ResultBean> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * status : 4
         * invest_amount : 18.59
         * redemp_status : 1
         * share : 9.88
         * fund_type : 股票型
         * income_rate : 0.00%
         * id : 2051
         * status_str : 已持有
         * name : 大摩品质生活股票
         * fund_code : 000309
         * total_income : 0
         * interest_start_date_notify : false
         * recent_income : 0
         * recent_nav : 1.8820
         * redemp_status_str : 可赎回
         */

        private int status;
        private String invest_amount;
        private int redemp_status;
        private String share;
        private String fund_type;
        private String income_rate;
        private int id;
        private String status_str;
        private String name;
        private String fund_code;
        private String total_income;
        private boolean interest_start_date_notify;
        private String recent_income;
        private String recent_nav;
        private String redemp_status_str;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInvest_amount() {
            return invest_amount;
        }

        public void setInvest_amount(String invest_amount) {
            this.invest_amount = invest_amount;
        }

        public int getRedemp_status() {
            return redemp_status;
        }

        public void setRedemp_status(int redemp_status) {
            this.redemp_status = redemp_status;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getFund_type() {
            return fund_type;
        }

        public void setFund_type(String fund_type) {
            this.fund_type = fund_type;
        }

        public String getIncome_rate() {
            return income_rate;
        }

        public void setIncome_rate(String income_rate) {
            this.income_rate = income_rate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFund_code() {
            return fund_code;
        }

        public void setFund_code(String fund_code) {
            this.fund_code = fund_code;
        }

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
        }

        public boolean isInterest_start_date_notify() {
            return interest_start_date_notify;
        }

        public void setInterest_start_date_notify(boolean interest_start_date_notify) {
            this.interest_start_date_notify = interest_start_date_notify;
        }

        public String getRecent_income() {
            return recent_income;
        }

        public void setRecent_income(String recent_income) {
            this.recent_income = recent_income;
        }

        public String getRecent_nav() {
            return recent_nav;
        }

        public void setRecent_nav(String recent_nav) {
            this.recent_nav = recent_nav;
        }

        public String getRedemp_status_str() {
            return redemp_status_str;
        }

        public void setRedemp_status_str(String redemp_status_str) {
            this.redemp_status_str = redemp_status_str;
        }
    }
}
