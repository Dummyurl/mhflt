package com.changju.fanlitou.bean.discover.platformbid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/6/22.
 */

public class DiscoverPlatformBidGold extends IDiscoverPlatformBid{
    /**
     * count : 7
     * platform_bids : [{"current_price":277.27,"is_new_user":true,"bid_name":"新手礼包7天","bid_interest":"19.88","duration_unit":"天","type":"gold","bid_id":43,"platform_name":"黄金管家","bonus_interest":"0.5","is_promotion":false,"is_activity_bid":false,"display_type":"normal_gold_bid","min_invest_amount":"1","total_invest_weight":2360,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1}},{"current_price":277.27,"is_new_user":false,"bid_name":"年年赢金08号365天","bid_interest":"3.6","duration_unit":"天","type":"gold","bid_id":48,"platform_name":"黄金管家","bonus_interest":"0.5","is_promotion":false,"is_activity_bid":false,"display_type":"normal_gold_bid","min_invest_amount":"1","total_invest_weight":151.633,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1}},{"current_price":277.27,"is_new_user":false,"bid_name":"半年理金08号180天","bid_interest":"3.5","duration_unit":"天","type":"gold","bid_id":47,"platform_name":"黄金管家","bonus_interest":"0.5","is_promotion":false,"is_activity_bid":false,"display_type":"normal_gold_bid","min_invest_amount":"1","total_invest_weight":2715.593,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1}},{"current_price":277.27,"is_new_user":false,"bid_name":"季季送金08号90天","bid_interest":"2.2","duration_unit":"天","type":"gold","bid_id":46,"platform_name":"黄金管家","bonus_interest":"0.5","is_promotion":false,"is_activity_bid":false,"display_type":"normal_gold_bid","min_invest_amount":"1","total_invest_weight":4573.15,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1}},{"current_price":277.27,"is_new_user":false,"bid_name":"周周赚金13号7天","bid_interest":"1.5","duration_unit":"天","type":"gold","bid_id":89,"platform_name":"黄金管家","bonus_interest":"0.5","is_promotion":false,"is_activity_bid":false,"display_type":"normal_gold_bid","min_invest_amount":"1","total_invest_weight":3242.293,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1}},{"current_price":277.27,"is_new_user":false,"bid_name":"月月盈金11号30天","bid_interest":"1.45","duration_unit":"天","type":"gold","bid_id":90,"platform_name":"黄金管家","bonus_interest":"0.5","is_promotion":false,"is_activity_bid":false,"display_type":"normal_gold_bid","min_invest_amount":"1","total_invest_weight":1,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1}},{"current_price":277.27,"is_new_user":false,"bid_name":"活期金","bid_interest":"1.3","duration_unit":"天","type":"gold","bid_id":29,"platform_name":"黄金管家","bonus_interest":"0.3","is_promotion":false,"is_activity_bid":false,"display_type":"au_current_bid","min_invest_amount":"0","total_invest_weight":16240.013,"duration":1,"tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"is_daily_bonus":true,"original_bonus_info":{"is_show":false,"original_bonus_value":1}}]
     */

    private int count;
    private List<PlatformBidsBean> platform_bids;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PlatformBidsBean> getPlatform_bids() {
        return platform_bids;
    }

    public void setPlatform_bids(List<PlatformBidsBean> platform_bids) {
        this.platform_bids = platform_bids;
    }

    public static class PlatformBidsBean implements Serializable {
        /**
         * current_price : 277.27
         * is_new_user : true
         * bid_name : 新手礼包7天
         * bid_interest : 19.88
         * duration_unit : 天
         * type : gold
         * bid_id : 43
         * platform_name : 黄金管家
         * bonus_interest : 0.5
         * is_promotion : false
         * is_activity_bid : false
         * display_type : normal_gold_bid
         * min_invest_amount : 1
         * total_invest_weight : 2360
         * duration : 1
         * tag_list : [{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}]
         * is_daily_bonus : false
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         */

        private String current_price;
        private boolean is_new_user;
        private String bid_name;
        private String bid_interest;
        private String duration_unit;
        private String type;
        private int bid_id;
        private String platform_name;
        private String bonus_interest;
        private boolean is_promotion;
        private boolean is_activity_bid;
        private String display_type;
        private String min_invest_amount;
        private String total_invest_weight;
        private String duration;
        private boolean is_daily_bonus;
        private OriginalBonusInfoBean original_bonus_info;
        private List<TagListBean> tag_list;

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

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

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
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

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public String getTotal_invest_weight() {
            return total_invest_weight;
        }

        public void setTotal_invest_weight(String total_invest_weight) {
            this.total_invest_weight = total_invest_weight;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class OriginalBonusInfoBean implements Serializable {
            /**
             * is_show : false
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

        public static class TagListBean implements Serializable {
            /**
             * style : red
             * name : 新手标
             */

            private String style;
            private String name;

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
