package com.changju.fanlitou.bean.discover.platformbid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/6/22.
 */

public class DiscoverPlatformBidCurrent extends IDiscoverPlatformBid{

    private int count;
    private List<FixedBidsBean> fixed_bids;
    private List<CurrentBidsBean> current_bids;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FixedBidsBean> getFixed_bids() {
        return fixed_bids;
    }

    public void setFixed_bids(List<FixedBidsBean> fixed_bids) {
        this.fixed_bids = fixed_bids;
    }

    public List<CurrentBidsBean> getCurrent_bids() {
        return current_bids;
    }

    public void setCurrent_bids(List<CurrentBidsBean> current_bids) {
        this.current_bids = current_bids;
    }

    public static class FixedBidsBean implements Serializable {
        /**
         * is_new_user : false
         * bid_name : 星火 C12
         * bid_interest : 11.5
         * bid_progress_percent : 100
         * duration_unit : 个月
         * bid_id : 337543
         * is_promotion : false
         * is_activity_bid : false
         * duration : 30
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         * type : p2p
         * platform_name : 星火钱包
         * bonus_interest : 1
         * tag_list : [{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}]
         * is_daily_bonus : false
         */

        private boolean is_new_user;
        private String bid_name;
        private String bid_interest;
        private float bid_progress_percent;
        private String duration_unit;
        private int bid_id;
        private boolean is_promotion;
        private boolean is_activity_bid;
        private String duration;
        private OriginalBonusInfoBean original_bonus_info;
        private String type;
        private String platform_name;
        private String bonus_interest;
        private boolean is_daily_bonus;
        private List<TagListBean> tag_list;

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

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class OriginalBonusInfoBean implements Serializable{
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

        public static class TagListBean implements Serializable{
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

    public static class CurrentBidsBean implements Serializable{
        /**
         * is_new_user : false
         * bid_name : 测试
         * bid_interest : 10
         * share_name : 待售份额
         * share_amount : 0
         * bid_id : 6
         * is_promotion : false
         * is_activity_bid : false
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         * type : current
         * platform_name : 星火钱包
         * bonus_interest : 1
         * tag_list : [{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}]
         * is_daily_bonus : true
         */

        private boolean is_new_user;
        private String bid_name;
        private String bid_interest;
        private String share_name;
        private String share_amount;
        private int bid_id;
        private boolean is_promotion;
        private boolean is_activity_bid;
        private OriginalBonusInfoBeanX original_bonus_info;
        private String type;
        private String platform_name;
        private String bonus_interest;
        private boolean is_daily_bonus;
        private List<TagListBeanX> tag_list;

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

        public String getShare_name() {
            return share_name;
        }

        public void setShare_name(String share_name) {
            this.share_name = share_name;
        }

        public String getShare_amount() {
            return share_amount;
        }

        public void setShare_amount(String share_amount) {
            this.share_amount = share_amount;
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

        public OriginalBonusInfoBeanX getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBeanX original_bonus_info) {
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

        public List<TagListBeanX> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBeanX> tag_list) {
            this.tag_list = tag_list;
        }

        public static class OriginalBonusInfoBeanX implements Serializable{
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

        public static class TagListBeanX implements Serializable{
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
