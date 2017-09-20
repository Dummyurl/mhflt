package com.changju.fanlitou.bean.home;

/**
 * Created by ZM on 2017/5/9.
 */

public class GoldCurrent {


    /**
     * recommend_bid : {"is_show":true,"bid_type":"gold_current","bid_detail":{"bid_duration":"0天","platform":{"platform_name":"黄金钱包"},"bid_name":"流动金","bid_interest":1.86,"raise_interest":0.3,"bid_progress_percent":68,"bid_url":"/m/bid_detail/gold/81/?short_name=aujin&from=/m/","bid_id":81},"title":"活期金"}
     */

    private RecommendBidBean recommend_bid;

    public RecommendBidBean getRecommend_bid() {
        return recommend_bid;
    }

    public void setRecommend_bid(RecommendBidBean recommend_bid) {
        this.recommend_bid = recommend_bid;
    }

    public static class RecommendBidBean {
        /**
         * is_show : true
         * bid_type : gold_current
         * bid_detail : {"bid_duration":"0天","platform":{"platform_name":"黄金钱包"},"bid_name":"流动金","bid_interest":1.86,"raise_interest":0.3,"bid_progress_percent":68,"bid_url":"/m/bid_detail/gold/81/?short_name=aujin&from=/m/","bid_id":81}
         * title : 活期金
         */

        private boolean is_show;
        private String bid_type;
        private BidDetailBean bid_detail;
        private String title;

        public boolean isIs_show() {
            return is_show;
        }

        public void setIs_show(boolean is_show) {
            this.is_show = is_show;
        }

        public String getBid_type() {
            return bid_type;
        }

        public void setBid_type(String bid_type) {
            this.bid_type = bid_type;
        }

        public BidDetailBean getBid_detail() {
            return bid_detail;
        }

        public void setBid_detail(BidDetailBean bid_detail) {
            this.bid_detail = bid_detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class BidDetailBean {
            /**
             * bid_duration : 0天
             * platform : {"platform_name":"黄金钱包"}
             * bid_name : 流动金
             * bid_interest : 1.86
             * raise_interest : 0.3
             * bid_progress_percent : 68
             * bid_url : /m/bid_detail/gold/81/?short_name=aujin&from=/m/
             * bid_id : 81
             */

            private String bid_duration;
            private PlatformBean platform;
            private String bid_name;
            private String bid_interest;
            private String raise_interest;
            private String current_price;
            private String bid_url;
            private int bid_id;

            public String getBid_duration() {
                return bid_duration;
            }

            public void setBid_duration(String bid_duration) {
                this.bid_duration = bid_duration;
            }

            public PlatformBean getPlatform() {
                return platform;
            }

            public void setPlatform(PlatformBean platform) {
                this.platform = platform;
            }

            public String getBid_name() {
                return bid_name;
            }

            public void setBid_name(String bid_name) {
                this.bid_name = bid_name;
            }

            public String getBid_interest() {
                return bid_interest;
            }

            public void setBid_interest(String bid_interest) {
                this.bid_interest = bid_interest;
            }

            public String getRaise_interest() {
                return raise_interest;
            }

            public void setRaise_interest(String raise_interest) {
                this.raise_interest = raise_interest;
            }

            public String getCurrent_price() {
                return current_price;
            }

            public void setCurrent_price(String current_price) {
                this.current_price = current_price;
            }

            public String getBid_url() {
                return bid_url;
            }

            public void setBid_url(String bid_url) {
                this.bid_url = bid_url;
            }

            public int getBid_id() {
                return bid_id;
            }

            public void setBid_id(int bid_id) {
                this.bid_id = bid_id;
            }

            public static class PlatformBean {
                /**
                 * platform_name : 黄金钱包
                 */

                private String platform_name;

                public String getPlatform_name() {
                    return platform_name;
                }

                public void setPlatform_name(String platform_name) {
                    this.platform_name = platform_name;
                }
            }
        }
    }
}
