package com.changju.fanlitou.bean.account;

import java.util.List;

/**
 * Created by chengww on 2017/5/23.
 */

public class RePaying {

    /**
     * count : 2
     * records : [{"term":8,"expect_bonus":123.28,"today_bonus":0,"bid_name":"押车宝168911","receivable_interest":416.65,"latest_repay_date":"2017-06-08","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png","total_term":12,"bid_id":337587,"platform_name":"一起好","type":"p2p","receivable_principal":10000,"received_interest":583.31,"is_daily_bonus":true,"invest_principle":10000,"received_bonus":0.27},{"term":8,"expect_bonus":93.2,"today_bonus":0,"bid_name":"企业借贷-0008（第五期）","receivable_interest":552.03,"latest_repay_date":"2017-06-08","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/64c27fc2/app-64c27fc2-64cc-4895-abb8-e9b0f62988f7-1490927241.png","total_term":13,"bid_id":337719,"platform_name":"银豆网","type":"p2p","receivable_principal":10000,"received_interest":758.6,"is_daily_bonus":false,"invest_principle":10000,"received_bonus":93.2}]
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
         * term : 8
         * expect_bonus : 123.28
         * today_bonus : 0
         * bid_name : 押车宝168911
         * receivable_interest : 416.65
         * latest_repay_date : 2017-06-08
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png
         * total_term : 12
         * bid_id : 337587
         * platform_name : 一起好
         * type : p2p
         * receivable_principal : 10000
         * received_interest : 583.31
         * is_daily_bonus : true
         * invest_principle : 10000
         * received_bonus : 0.27
         */

        private int term;
        private String expect_bonus;
        private String today_bonus;
        private String bid_name;
        private String receivable_interest;
        private String latest_repay_date;
        private String platform_logo;
        private int total_term;
        private int bid_id;
        private String platform_name;
        private String type;
        private String receivable_principal;
        private String received_interest;
        private boolean is_daily_bonus;
        private String invest_principle;
        private String received_bonus;

        public int getTerm() {
            return term;
        }

        public void setTerm(int term) {
            this.term = term;
        }

        public String getExpect_bonus() {
            return expect_bonus;
        }

        public void setExpect_bonus(String expect_bonus) {
            this.expect_bonus = expect_bonus;
        }

        public String getToday_bonus() {
            return today_bonus;
        }

        public void setToday_bonus(String today_bonus) {
            this.today_bonus = today_bonus;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public String getReceivable_interest() {
            return receivable_interest;
        }

        public void setReceivable_interest(String receivable_interest) {
            this.receivable_interest = receivable_interest;
        }

        public String getLatest_repay_date() {
            return latest_repay_date;
        }

        public void setLatest_repay_date(String latest_repay_date) {
            this.latest_repay_date = latest_repay_date;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public int getTotal_term() {
            return total_term;
        }

        public void setTotal_term(int total_term) {
            this.total_term = total_term;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getReceivable_principal() {
            return receivable_principal;
        }

        public void setReceivable_principal(String receivable_principal) {
            this.receivable_principal = receivable_principal;
        }

        public String getReceived_interest() {
            return received_interest;
        }

        public void setReceived_interest(String received_interest) {
            this.received_interest = received_interest;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public String getInvest_principle() {
            return invest_principle;
        }

        public void setInvest_principle(String invest_principle) {
            this.invest_principle = invest_principle;
        }

        public String getReceived_bonus() {
            return received_bonus;
        }

        public void setReceived_bonus(String received_bonus) {
            this.received_bonus = received_bonus;
        }
    }
}
