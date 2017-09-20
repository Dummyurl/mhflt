package com.changju.fanlitou.bean.account;

import java.util.List;

/**
 * Created by chengww on 2017/6/16.
 */

public class BidRepayDetail {

    /**
     * bid_name : 爱房贷-北京个人房产周转贷FDHX170431-4
     * bid_amount : 3300
     * tag : {"name":"现金券","tag_type":1}
     * collecting_total : 3377.28
     * bid_time : 2017.04.20
     * total_count : 3
     * collected_total : 3356.67
     * bid_duration : 90天
     * bid_replay_list : [{"platform_short_name":"iqianbang","bid_id":346283,"total_term":3,"principal":0,"platform_name":"爱钱帮","term":1,"total_amount":20.61,"bid_time":"2017.04.15","platform_id":8,"repay_date":"2017.05.14","interest":20.61,"collected_flag":true},{"platform_short_name":"iqianbang","bid_id":346283,"total_term":3,"principal":0,"platform_name":"爱钱帮","term":2,"total_amount":26.62,"bid_time":"2017.04.15","platform_id":8,"repay_date":"2017.06.14","interest":26.62,"collected_flag":false},{"platform_short_name":"iqianbang","bid_id":346283,"total_term":3,"principal":3300,"platform_name":"爱钱帮","term":3,"total_amount":3330.06,"bid_time":"2017.04.15","platform_id":8,"repay_date":"2017.07.19","interest":30.06,"collected_flag":false}]
     * expect_repay_total : 8.13
     * total_interest : 8.13
     * already_repay_total : 8.13
     * is_daily_bonus : true
     */

    private String bid_name;
    private int bid_amount;
    private TagBean tag;
    private String collecting_total;
    private String bid_time;
    private int total_count;
    private String collected_total;
    private String bid_duration;
    private String expect_repay_total;
    private String total_interest;
    private String already_repay_total;
    private boolean is_daily_bonus;
    private List<BidReplayListBean> bid_replay_list;

    public String getBid_name() {
        return bid_name;
    }

    public void setBid_name(String bid_name) {
        this.bid_name = bid_name;
    }

    public int getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(int bid_amount) {
        this.bid_amount = bid_amount;
    }

    public TagBean getTag() {
        return tag;
    }

    public void setTag(TagBean tag) {
        this.tag = tag;
    }

    public String getCollecting_total() {
        return collecting_total;
    }

    public void setCollecting_total(String collecting_total) {
        this.collecting_total = collecting_total;
    }

    public String getBid_time() {
        return bid_time;
    }

    public void setBid_time(String bid_time) {
        this.bid_time = bid_time;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public String getCollected_total() {
        return collected_total;
    }

    public void setCollected_total(String collected_total) {
        this.collected_total = collected_total;
    }

    public String getBid_duration() {
        return bid_duration;
    }

    public void setBid_duration(String bid_duration) {
        this.bid_duration = bid_duration;
    }

    public String getExpect_repay_total() {
        return expect_repay_total;
    }

    public void setExpect_repay_total(String expect_repay_total) {
        this.expect_repay_total = expect_repay_total;
    }

    public String getTotal_interest() {
        return total_interest;
    }

    public void setTotal_interest(String total_interest) {
        this.total_interest = total_interest;
    }

    public String getAlready_repay_total() {
        return already_repay_total;
    }

    public void setAlready_repay_total(String already_repay_total) {
        this.already_repay_total = already_repay_total;
    }

    public boolean isIs_daily_bonus() {
        return is_daily_bonus;
    }

    public void setIs_daily_bonus(boolean is_daily_bonus) {
        this.is_daily_bonus = is_daily_bonus;
    }

    public List<BidReplayListBean> getBid_replay_list() {
        return bid_replay_list;
    }

    public void setBid_replay_list(List<BidReplayListBean> bid_replay_list) {
        this.bid_replay_list = bid_replay_list;
    }

    public static class TagBean {
        /**
         * name : 现金券
         * tag_type : 1
         */

        private String name;
        private int tag_type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTag_type() {
            return tag_type;
        }

        public void setTag_type(int tag_type) {
            this.tag_type = tag_type;
        }
    }

    public static class BidReplayListBean {
        /**
         * platform_short_name : iqianbang
         * bid_id : 346283
         * total_term : 3
         * principal : 0
         * platform_name : 爱钱帮
         * term : 1
         * total_amount : 20.61
         * bid_time : 2017.04.15
         * platform_id : 8
         * repay_date : 2017.05.14
         * interest : 20.61
         * collected_flag : true
         */

        private String platform_short_name;
        private int bid_id;
        private int total_term;
        private String principal;
        private String platform_name;
        private int term;
        private String total_amount;
        private String bid_time;
        private int platform_id;
        private String repay_date;
        private String interest;
        private boolean collected_flag;

        public String getPlatform_short_name() {
            return platform_short_name;
        }

        public void setPlatform_short_name(String platform_short_name) {
            this.platform_short_name = platform_short_name;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public int getTotal_term() {
            return total_term;
        }

        public void setTotal_term(int total_term) {
            this.total_term = total_term;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public int getTerm() {
            return term;
        }

        public void setTerm(int term) {
            this.term = term;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getBid_time() {
            return bid_time;
        }

        public void setBid_time(String bid_time) {
            this.bid_time = bid_time;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getRepay_date() {
            return repay_date;
        }

        public void setRepay_date(String repay_date) {
            this.repay_date = repay_date;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public boolean isCollected_flag() {
            return collected_flag;
        }

        public void setCollected_flag(boolean collected_flag) {
            this.collected_flag = collected_flag;
        }
    }
}
