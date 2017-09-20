package com.changju.fanlitou.bean.home;

/**
 * Created by ZM on 2017/5/8.
 */

public class Current {


    /**
     * recommend_bid : {"is_show":true,"bid_type":"current","bid_detail":{"bid_url":"/mobile_current_details/1/?from=/m/","name":"星火 X100","platform_name":"星火钱包","remain_shares":913896.73,"interest":7.24,"bonus_interest":0.5},"title":"活期推荐"}
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
         * bid_type : current
         * bid_detail : {"bid_url":"/mobile_current_details/1/?from=/m/","name":"星火 X100","platform_name":"星火钱包","remain_shares":913896.73,"interest":7.24,"bonus_interest":0.5}
         * title : 活期推荐
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
             * bid_url : /mobile_current_details/1/?from=/m/
             * name : 星火 X100
             * platform_name : 星火钱包
             * remain_shares : 913896.73
             * interest : 7.24
             * bonus_interest : 0.5
             */

            private String bid_url;
            private String name;
            private String platform_name;
            private String remain_shares;
            private String interest;
            private String bonus_interest;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPlatform_name() {
                return platform_name;
            }

            public void setPlatform_name(String platform_name) {
                this.platform_name = platform_name;
            }

            public String getRemain_shares() {
                return remain_shares;
            }

            public void setRemain_shares(String remain_shares) {
                this.remain_shares = remain_shares;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
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
