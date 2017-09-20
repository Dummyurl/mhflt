package com.changju.fanlitou.bean.home;

/**
 * Created by zhangming on 2017/6/25.
 */

public class Insurance {

    /**
     * recommend_bid : {"is_show":true,"bid_type":"insurance","bid_detail":{"bid_id":1,"bid_url":"/m/bid_detail/insurance/32/?from=/m/","name":"家财险","price":45,"is_free":false,"duration":"1年","bonus_interest":"易安"},"title":"保险推荐"}
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
         * bid_type : insurance
         * bid_detail : {"bid_id":1,"bid_url":"/m/bid_detail/insurance/32/?from=/m/","name":"家财险","price":45,"is_free":false,"duration":"1年","bonus_interest":"易安"}
         * title : 保险推荐
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
             * bid_id : 1
             * bid_url : /m/bid_detail/insurance/32/?from=/m/
             * name : 家财险
             * price : 45
             * is_free : false
             * duration : 1年
             * bonus_interest : 易安
             */

            private int bid_id;
            private String bid_url;
            private String name;
            private String price;
            private boolean is_free;
            private String duration;
            private String bonus_interest;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public boolean isIs_free() {
                return is_free;
            }

            public void setIs_free(boolean is_free) {
                this.is_free = is_free;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getBonus_interest() {
                return bonus_interest;
            }

            public void setBonus_interest(String bonus_interest) {
                this.bonus_interest = bonus_interest;
            }
        }
    }
}
