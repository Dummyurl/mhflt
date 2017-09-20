package com.changju.fanlitou.bean.home;

/**
 * Created by chengww on 2017/5/7.
 */

public class RecommendBid {

    /**
     * recommend_bid : {"is_show":true,"bid_type":"gold","bid_detail":{"bid_url":"/m/bid_detail/gold/43/?short_name=aujin&from=/m/","bid_name":"新手礼包7天","bid_interest":19.88,"bid_progress_percent":64,"bid_duration":"7天","platform":{"platform_name":"黄金管家"},"raise_interest":50},"title":"固收推荐"}
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
         * bid_type : gold
         * bid_detail : {"bid_url":"/m/bid_detail/gold/43/?short_name=aujin&from=/m/","bid_name":"新手礼包7天","bid_interest":19.88,"bid_progress_percent":64,"bid_duration":"7天","platform":{"platform_name":"黄金管家"},"raise_interest":50}
         * title : 固收推荐
         */

        private boolean is_show;
        private String bid_type;
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


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
