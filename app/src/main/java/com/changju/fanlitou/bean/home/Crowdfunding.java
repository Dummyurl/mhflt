package com.changju.fanlitou.bean.home;

/**
 * Created by ZM on 2017/5/8.
 */

public class Crowdfunding {

    /**
     * recommend_bid : {"is_show":true,"bid_type":"crowdfunding","bid_detail":{"platform_name":"多彩投","status_str":"crowd_funding_remain_time","total_amount":"500万","bid_img":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704061200188.jpg","name":"龙门山田园度假综合体","min_invest_amount":20000,"remain_time":"12天","status":0,"bid_url":"/m/bid_detail/crowdfund_dct/43/?from=/m/","progress_rate":4},"title":"众筹推荐"}
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
         * bid_type : crowdfunding
         * bid_detail : {"platform_name":"多彩投","status_str":"crowd_funding_remain_time","total_amount":"500万","bid_img":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704061200188.jpg","name":"龙门山田园度假综合体","min_invest_amount":20000,"remain_time":"12天","status":0,"bid_url":"/m/bid_detail/crowdfund_dct/43/?from=/m/","progress_rate":4}
         * title : 众筹推荐
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
             * platform_name : 多彩投
             * status_str : crowd_funding_remain_time
             * total_amount : 500万
             * bid_img : https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704061200188.jpg
             * name : 龙门山田园度假综合体
             * min_invest_amount : 20000
             * remain_time : 12天
             * status : 0
             * bid_url : /m/bid_detail/crowdfund_dct/43/?from=/m/
             * progress_rate : 4
             */

            private String platform_name;
            private String status_str;
            private String total_amount;
            private String bid_img;
            private String name;
            private String min_invest_amount;
            private String remain_time;
            private int status;
            private String bid_url;
            private String progress_rate;
            private int bid_id;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMin_invest_amount() {
                return min_invest_amount;
            }

            public void setMin_invest_amount(String min_invest_amount) {
                this.min_invest_amount = min_invest_amount;
            }

            public String getRemain_time() {
                return remain_time;
            }

            public void setRemain_time(String remain_time) {
                this.remain_time = remain_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getBid_url() {
                return bid_url;
            }

            public void setBid_url(String bid_url) {
                this.bid_url = bid_url;
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
