package com.changju.fanlitou.bean.home;

/**
 * Created by Administrator on 2017/5/9.
 */

public class Vc {

    /**
     * recommend_bid : {"is_show":true,"bid_type":"vc","bid_detail":{"status":1,"max_duration":90,"duration_unit":"天","min_duration":1,"min_invest_amount":1000,"name":"【NCVA103】第Y059期 奥迪奥迪","status_str":"可投标","total_amount":12000,"bid_img":"https://dn-weiclicai.qbox.me/5901be65b716f.jpg","bid_url":"/m/bid_detail/crowdfund_vc/50/?from=/m/","platform_name":"维C理财","progress_rate":0},"title":"维C推荐"}
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
         * bid_type : vc
         * bid_detail : {"status":1,"max_duration":90,"duration_unit":"天","min_duration":1,"min_invest_amount":1000,"name":"【NCVA103】第Y059期 奥迪奥迪","status_str":"可投标","total_amount":12000,"bid_img":"https://dn-weiclicai.qbox.me/5901be65b716f.jpg","bid_url":"/m/bid_detail/crowdfund_vc/50/?from=/m/","platform_name":"维C理财","progress_rate":0}
         * title : 维C推荐
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
             * status : 1
             * max_duration : 90
             * duration_unit : 天
             * min_duration : 1
             * min_invest_amount : 1000
             * name : 【NCVA103】第Y059期 奥迪奥迪
             * status_str : 可投标
             * total_amount : 12000
             * bid_img : https://dn-weiclicai.qbox.me/5901be65b716f.jpg
             * bid_url : /m/bid_detail/crowdfund_vc/50/?from=/m/
             * platform_name : 维C理财
             * progress_rate : 0
             */

            private int status;
            private String max_duration;
            private String duration_unit;
            private String min_duration;
            private String min_invest_amount;
            private String name;
            private String status_str;
            private String total_amount;
            private String bid_img;
            private String bid_url;
            private String platform_name;
            private String progress_rate;
            private int bid_id;

            public int getBid_id() {
                return bid_id;
            }

            public void setBid_id(int bid_id) {
                this.bid_id = bid_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getMax_duration() {
                return max_duration;
            }

            public void setMax_duration(String max_duration) {
                this.max_duration = max_duration;
            }

            public String getDuration_unit() {
                return duration_unit;
            }

            public void setDuration_unit(String duration_unit) {
                this.duration_unit = duration_unit;
            }

            public String getMin_duration() {
                return min_duration;
            }

            public void setMin_duration(String min_duration) {
                this.min_duration = min_duration;
            }

            public String getMin_invest_amount() {
                return min_invest_amount;
            }

            public void setMin_invest_amount(String min_invest_amount) {
                this.min_invest_amount = min_invest_amount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus_str() {
                return status_str;
            }

            public void setStatus_str(String status_str) {
                this.status_str = status_str;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getBid_img() {
                return bid_img;
            }

            public void setBid_img(String bid_img) {
                this.bid_img = bid_img;
            }

            public String getBid_url() {
                return bid_url;
            }

            public void setBid_url(String bid_url) {
                this.bid_url = bid_url;
            }

            public String getPlatform_name() {
                return platform_name;
            }

            public void setPlatform_name(String platform_name) {
                this.platform_name = platform_name;
            }

            public String getProgress_rate() {
                return progress_rate;
            }

            public void setProgress_rate(String progress_rate) {
                this.progress_rate = progress_rate;
            }
        }
    }
}
