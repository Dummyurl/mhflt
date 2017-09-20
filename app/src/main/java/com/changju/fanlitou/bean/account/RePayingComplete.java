package com.changju.fanlitou.bean.account;

import java.util.List;

/**
 * Created by chengww on 2017/5/23.
 */

public class RePayingComplete {

    /**
     * count : 2
     * records : [{"bid_name":"大学生恋爱险钻石版","bid_id":34,"bonus":75,"total_term":0,"total_interest":0,"income_amount":0,"bid_time":"2017-04-12","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/174e321a/app-174e321a-755b-42f4-ae4f-e601e541affd-1490927069.png","platform_name":"人人保险","invest_principle":1000,"redeem_amount":0,"type":"insurance","classify":"创意保险","plan":""},{"bid_name":"【山东35】第M2606期 奥迪A8L","bid_id":1,"bonus":100,"total_term":1,"total_interest":200,"income_amount":0,"bid_time":"2016-12-26","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/2fe218b1/app-2fe218b1-8bea-41b8-b29b-7ea8ee00b6ae-1490940729.png","platform_name":"维C理财","invest_principle":10000,"redeem_amount":0,"type":"wuchou","classify":"","plan":""}]
     */

    private int count;
    private List<RecordsBean> records;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * bid_name : 大学生恋爱险钻石版
         * bid_id : 34
         * bonus : 75
         * total_term : 0
         * total_interest : 0
         * income_amount : 0
         * bid_time : 2017-04-12
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/174e321a/app-174e321a-755b-42f4-ae4f-e601e541affd-1490927069.png
         * platform_name : 人人保险
         * invest_principle : 1000
         * redeem_amount : 0
         * type : insurance
         * classify : 创意保险
         * plan :
         */

        private String bid_name;
        private int bid_id;
        private String bonus;
        private int total_term;
        private String total_interest;
        private String income_amount;
        private String bid_time;
        private String platform_logo;
        private String platform_name;
        private String invest_principle;
        private String redeem_amount;
        private String type;
        private String classify;
        private String plan;

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public int getTotal_term() {
            return total_term;
        }

        public void setTotal_term(int total_term) {
            this.total_term = total_term;
        }

        public String getTotal_interest() {
            return total_interest;
        }

        public void setTotal_interest(String total_interest) {
            this.total_interest = total_interest;
        }

        public String getIncome_amount() {
            return income_amount;
        }

        public void setIncome_amount(String income_amount) {
            this.income_amount = income_amount;
        }

        public String getBid_time() {
            return bid_time;
        }

        public void setBid_time(String bid_time) {
            this.bid_time = bid_time;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getInvest_principle() {
            return invest_principle;
        }

        public void setInvest_principle(String invest_principle) {
            this.invest_principle = invest_principle;
        }

        public String getRedeem_amount() {
            return redeem_amount;
        }

        public void setRedeem_amount(String redeem_amount) {
            this.redeem_amount = redeem_amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassify() {
            return classify;
        }

        public void setClassify(String classify) {
            this.classify = classify;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }
    }
}
