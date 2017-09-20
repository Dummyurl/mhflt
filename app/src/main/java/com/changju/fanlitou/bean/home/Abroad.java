package com.changju.fanlitou.bean.home;

/**
 * Created by Administrator on 2017/5/9.
 */

public class Abroad {


    /**
     * recommend_bid : {"is_show":true,"bid_type":"abroad","bid_detail":{"bid_url":"/m/bid_detail/gold/9/?short_name=meixin&from=/m/","bid_name":"安心计划12个月","bid_interest":7,"bid_progress_percent":35,"bid_duration":"12个月","platform":{"platform_name":"美信金融"},"raise_interest":1},"title":"固收推荐"}
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
         * bid_type : abroad
         * bid_detail : {"bid_url":"/m/bid_detail/gold/9/?short_name=meixin&from=/m/","bid_name":"安心计划12个月","bid_interest":7,"bid_progress_percent":35,"bid_duration":"12个月","platform":{"platform_name":"美信金融"},"raise_interest":1}
         * title : 固收推荐
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
             * bid_url : /m/bid_detail/gold/9/?short_name=meixin&from=/m/
             * bid_name : 安心计划12个月
             * bid_interest : 7
             * bid_progress_percent : 35
             * bid_duration : 12个月
             * platform : {"platform_name":"美信金融"}
             * raise_interest : 1
             */

            private String bid_url;
            private String bid_name;
            private String bid_interest;
            private String bid_progress_percent;
            private String bid_duration;
            private PlatformBean platform;
            private String raise_interest;
            private int bid_id;

            public int getBid_id() {
                return bid_id;
            }

            public void setBid_id(int bid_id) {
                this.bid_id = bid_id;
            }

            public String getBid_url() {
                return bid_url;
            }

            public void setBid_url(String bid_url) {
                this.bid_url = bid_url;
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

            public String getBid_progress_percent() {
                return bid_progress_percent;
            }

            public void setBid_progress_percent(String bid_progress_percent) {
                this.bid_progress_percent = bid_progress_percent;
            }

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

            public String getRaise_interest() {
                return raise_interest;
            }

            public void setRaise_interest(String raise_interest) {
                this.raise_interest = raise_interest;
            }

            public static class PlatformBean {
                /**
                 * platform_name : 美信金融
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
