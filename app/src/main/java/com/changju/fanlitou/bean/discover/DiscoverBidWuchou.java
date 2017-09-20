package com.changju.fanlitou.bean.discover;

import java.util.List;

/**
 * Created by zm on 2017/5/23.
 */

public class DiscoverBidWuchou {


    /**
     * total : 61
     * bid_list : [{"max_duration":90,"max_interest":"16","is_new_user":false,"bid_name":"【NCVA103】第Y059期 奥迪奥迪","bid_progress_percent":100,"duration_unit":"天","min_interest":"13","bid_id":50,"min_duration":1,"platform_name":"维C理财","is_promotion":false,"type":"wuchou","bonus_interest":"1.4","original_bonus_info":{"is_show":true,"original_bonus_value":1},"is_daily_bonus":true,"total_amount":"1.19万"}]
     */

    private String total;
    private List<BidListBean> bid_list;

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
         * max_duration : 90
         * max_interest : 16
         * is_new_user : false
         * bid_name : 【NCVA103】第Y059期 奥迪奥迪
         * bid_progress_percent : 100
         * duration_unit : 天
         * min_interest : 13
         * bid_id : 50
         * min_duration : 1
         * platform_name : 维C理财
         * is_promotion : false
         * type : wuchou
         * bonus_interest : 1.4
         * original_bonus_info : {"is_show":true,"original_bonus_value":1}
         * is_daily_bonus : true
         * total_amount : 1.19万
         */

        private String max_duration;
        private String max_interest;
        private boolean is_new_user;
        private String bid_name;
        //未做修改
        private float bid_progress_percent;
        private String duration_unit;
        private String min_interest;
        private int bid_id;
        private String min_duration;
        private String platform_name;
        private boolean is_promotion;
        private String type;
        private String bonus_interest;
        private OriginalBonusInfoBean original_bonus_info;
        private boolean is_daily_bonus;
        private String total_amount;

        public String getMax_duration() {
            return max_duration;
        }

        public void setMax_duration(String max_duration) {
            this.max_duration = max_duration;
        }

        public String getMax_interest() {
            return max_interest;
        }

        public void setMax_interest(String max_interest) {
            this.max_interest = max_interest;
        }

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

        public float getBid_progress_percent() {
            return bid_progress_percent;
        }

        public void setBid_progress_percent(float bid_progress_percent) {
            this.bid_progress_percent = bid_progress_percent;
        }

        public String getDuration_unit() {
            return duration_unit;
        }

        public void setDuration_unit(String duration_unit) {
            this.duration_unit = duration_unit;
        }

        public String getMin_interest() {
            return min_interest;
        }

        public void setMin_interest(String min_interest) {
            this.min_interest = min_interest;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getMin_duration() {
            return min_duration;
        }

        public void setMin_duration(String min_duration) {
            this.min_duration = min_duration;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public boolean isIs_promotion() {
            return is_promotion;
        }

        public void setIs_promotion(boolean is_promotion) {
            this.is_promotion = is_promotion;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public static class OriginalBonusInfoBean {
            /**
             * is_show : true
             * original_bonus_value : 1
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
