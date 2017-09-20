package com.changju.fanlitou.bean.discover;

import java.util.List;

/**
 * Created by zm on 2017/5/23.
 */

public class DiscoverBidGold {

    /**
     * category : 3
     * total : 8
     * bid_list : [{"is_new_user":true,"bid_name":"新手礼包7天","bid_interest":"19.88","duration_unit":"天","bid_id":43,"is_promotion":false,"is_activity_bid":false,"min_invest_amount":"1","duration":1,"original_bonus_info":{"is_show":true,"original_bonus_value":0.5},"type":"gold","platform_name":"黄金管家","bonus_interest":"0.7","is_daily_bonus":false}]
     */

    private String category;
    private String total;
    private List<BidListBean> bid_list;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<BidListBean> getBid_list() {
        return bid_list;
    }

    public void setBid_list(List<BidListBean> bid_list) {
        this.bid_list = bid_list;
    }

    public static class BidListBean {
        /**
         * is_new_user : true
         * bid_name : 新手礼包7天
         * bid_interest : 19.88
         * duration_unit : 天
         * bid_id : 43
         * is_promotion : false
         * is_activity_bid : false
         * min_invest_amount : 1
         * duration : 1
         * original_bonus_info : {"is_show":true,"original_bonus_value":0.5}
         * type : gold
         * platform_name : 黄金管家
         * bonus_interest : 0.7
         * is_daily_bonus : false
         */

        private boolean is_new_user;
        private String bid_name;
        private String bid_interest;
        private String duration_unit;
        private int bid_id;
        private boolean is_promotion;
        private boolean is_activity_bid;
        private String min_invest_amount;
        private String duration;
        private OriginalBonusInfoBean original_bonus_info;
        private String type;
        private String platform_name;
        private String bonus_interest;
        private boolean is_daily_bonus;

        public boolean isIs_new_user() {
            return is_new_user;
        }

        public void setIs_new_user(boolean is_new_user) {
            this.is_new_user = is_new_user;
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

        public String getDuration_unit() {
            return duration_unit;
        }

        public void setDuration_unit(String duration_unit) {
            this.duration_unit = duration_unit;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public boolean isIs_promotion() {
            return is_promotion;
        }

        public void setIs_promotion(boolean is_promotion) {
            this.is_promotion = is_promotion;
        }

        public boolean isIs_activity_bid() {
            return is_activity_bid;
        }

        public void setIs_activity_bid(boolean is_activity_bid) {
            this.is_activity_bid = is_activity_bid;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public static class OriginalBonusInfoBean {
            /**
             * is_show : true
             * original_bonus_value : 0.5
             */

            private boolean is_show;
            private String original_bonus_value;

            public boolean isIs_show() {
                return is_show;
            }

            public void setIs_show(boolean is_show) {
                this.is_show = is_show;
            }

            public String getOriginal_bonus_value() {
                return original_bonus_value;
            }

            public void setOriginal_bonus_value(String original_bonus_value) {
                this.original_bonus_value = original_bonus_value;
            }
        }
    }
}
