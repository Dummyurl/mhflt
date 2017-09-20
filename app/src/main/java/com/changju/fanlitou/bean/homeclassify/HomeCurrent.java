package com.changju.fanlitou.bean.homeclassify;

import java.util.List;

/**
 * Created by zhangming on 2017/6/20.
 */

public class HomeCurrent {


    private List<CurrentGoldBidsBean> current_gold_bids;
    private List<CurrentBidsBean> current_bids;

    public List<CurrentGoldBidsBean> getCurrent_gold_bids() {
        return current_gold_bids;
    }

    public void setCurrent_gold_bids(List<CurrentGoldBidsBean> current_gold_bids) {
        this.current_gold_bids = current_gold_bids;
    }

    public List<CurrentBidsBean> getCurrent_bids() {
        return current_bids;
    }

    public void setCurrent_bids(List<CurrentBidsBean> current_bids) {
        this.current_bids = current_bids;
    }

    public static class CurrentGoldBidsBean {

        private int bid_category;
        private String bid_type_str;
        private int total_invest_people;
        private int rank;
        private String duration;
        private String duration_unit_str;
        private String financing_rate;
        private String current_price;
        private boolean is_gold_bid;
        private OriginalBonusInfoBean original_bonus_info;
        private String max_invest_amount;
        private PlatformBean platform;
        private String introduction;
        private int settlement_days_after_bid_fulled;
        private String interest;
        private int status;
        private String status_str;
        private String duration_unit;
        private int bid_id;
        private String display_type;
        private String min_invest_amount;
        private String bid_url;
        private int bid_type;
        private String name;
        private String bid_category_str;
        private String platform_bonus_interest;
        private String total_invest_weight;
        private boolean iphone_flag;
        private List<TagListBean> tag_list;

        public int getBid_category() {
            return bid_category;
        }

        public void setBid_category(int bid_category) {
            this.bid_category = bid_category;
        }

        public String getBid_type_str() {
            return bid_type_str;
        }

        public void setBid_type_str(String bid_type_str) {
            this.bid_type_str = bid_type_str;
        }

        public int getTotal_invest_people() {
            return total_invest_people;
        }

        public void setTotal_invest_people(int total_invest_people) {
            this.total_invest_people = total_invest_people;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDuration_unit_str() {
            return duration_unit_str;
        }

        public void setDuration_unit_str(String duration_unit_str) {
            this.duration_unit_str = duration_unit_str;
        }

        public String getFinancing_rate() {
            return financing_rate;
        }

        public void setFinancing_rate(String financing_rate) {
            this.financing_rate = financing_rate;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public boolean isIs_gold_bid() {
            return is_gold_bid;
        }

        public void setIs_gold_bid(boolean is_gold_bid) {
            this.is_gold_bid = is_gold_bid;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public String getMax_invest_amount() {
            return max_invest_amount;
        }

        public void setMax_invest_amount(String max_invest_amount) {
            this.max_invest_amount = max_invest_amount;
        }

        public PlatformBean getPlatform() {
            return platform;
        }

        public void setPlatform(PlatformBean platform) {
            this.platform = platform;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getSettlement_days_after_bid_fulled() {
            return settlement_days_after_bid_fulled;
        }

        public void setSettlement_days_after_bid_fulled(int settlement_days_after_bid_fulled) {
            this.settlement_days_after_bid_fulled = settlement_days_after_bid_fulled;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
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

        public String getBid_url() {
            return bid_url;
        }

        public void setBid_url(String bid_url) {
            this.bid_url = bid_url;
        }

        public int getBid_type() {
            return bid_type;
        }

        public void setBid_type(int bid_type) {
            this.bid_type = bid_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBid_category_str() {
            return bid_category_str;
        }

        public void setBid_category_str(String bid_category_str) {
            this.bid_category_str = bid_category_str;
        }

        public String getPlatform_bonus_interest() {
            return platform_bonus_interest;
        }

        public void setPlatform_bonus_interest(String platform_bonus_interest) {
            this.platform_bonus_interest = platform_bonus_interest;
        }

        public String getTotal_invest_weight() {
            return total_invest_weight;
        }

        public void setTotal_invest_weight(String total_invest_weight) {
            this.total_invest_weight = total_invest_weight;
        }

        public boolean isIphone_flag() {
            return iphone_flag;
        }

        public void setIphone_flag(boolean iphone_flag) {
            this.iphone_flag = iphone_flag;
        }

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class OriginalBonusInfoBean {
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

        public static class PlatformBean {
            /**
             * interest_max : 14
             * is_bank : false
             * is_vc : true
             * logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/89e9c283/thumb-89e9c283-17ea-4352-8224-3514a646a8ce-1475129118.png
             * can_auto_login : false
             * id : 28
             * location_city : 北京
             * registered_capital : 500
             * credit_score : 3
             * is_state : false
             * online_date : 2015-08-15
             * is_private : false
             * shortname : aujin
             * can_auto_register : true
             * duration_min_unit : 天
             * logo_app_square : https://o0s106hgi.qnssl.com/media/plat-logo/a8d06df4/app-a8d06df4-eeb3-4135-8904-8d3810b7427e-1467179817.png
             * location_province : 北京
             * duration_max_unit : 年
             * background : 中信信托千万元战略入股
             * is_public : false
             * can_bind_account : false
             * logo_main : https://o0s106hgi.qnssl.com/media/plat-logo/2dc82545/main-2dc82545-7efa-4159-a4ad-37189eaa7758-1475129117.png
             * main_bussiness : 互联网金融，互联网黄金
             * name : 黄金管家
             * url : https://www.au32.cn/
             * logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/577c5c44/app-577c5c44-e715-49d9-ad10-1c19a3a0c3be-1490927774.png
             * interest_min : 1.6
             * duration_max : 5
             * duration_min : 30
             */

            private String interest_max;
            private boolean is_bank;
            private boolean is_vc;
            private String logo_thumbnail;
            private boolean can_auto_login;
            private int id;
            private String location_city;
            private String registered_capital;
            private String credit_score;
            private boolean is_state;
            private String online_date;
            private boolean is_private;
            private String shortname;
            private boolean can_auto_register;
            private String duration_min_unit;
            private String logo_app_square;
            private String location_province;
            private String duration_max_unit;
            private String background;
            private boolean is_public;
            private boolean can_bind_account;
            private String logo_main;
            private String main_bussiness;
            private String name;
            private String url;
            private String logo_app;
            private String interest_min;
            private String duration_max;
            private String duration_min;

            public String getInterest_max() {
                return interest_max;
            }

            public void setInterest_max(String interest_max) {
                this.interest_max = interest_max;
            }

            public boolean isIs_bank() {
                return is_bank;
            }

            public void setIs_bank(boolean is_bank) {
                this.is_bank = is_bank;
            }

            public boolean isIs_vc() {
                return is_vc;
            }

            public void setIs_vc(boolean is_vc) {
                this.is_vc = is_vc;
            }

            public String getLogo_thumbnail() {
                return logo_thumbnail;
            }

            public void setLogo_thumbnail(String logo_thumbnail) {
                this.logo_thumbnail = logo_thumbnail;
            }

            public boolean isCan_auto_login() {
                return can_auto_login;
            }

            public void setCan_auto_login(boolean can_auto_login) {
                this.can_auto_login = can_auto_login;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLocation_city() {
                return location_city;
            }

            public void setLocation_city(String location_city) {
                this.location_city = location_city;
            }

            public String getRegistered_capital() {
                return registered_capital;
            }

            public void setRegistered_capital(String registered_capital) {
                this.registered_capital = registered_capital;
            }

            public String getCredit_score() {
                return credit_score;
            }

            public void setCredit_score(String credit_score) {
                this.credit_score = credit_score;
            }

            public boolean isIs_state() {
                return is_state;
            }

            public void setIs_state(boolean is_state) {
                this.is_state = is_state;
            }

            public String getOnline_date() {
                return online_date;
            }

            public void setOnline_date(String online_date) {
                this.online_date = online_date;
            }

            public boolean isIs_private() {
                return is_private;
            }

            public void setIs_private(boolean is_private) {
                this.is_private = is_private;
            }

            public String getShortname() {
                return shortname;
            }

            public void setShortname(String shortname) {
                this.shortname = shortname;
            }

            public boolean isCan_auto_register() {
                return can_auto_register;
            }

            public void setCan_auto_register(boolean can_auto_register) {
                this.can_auto_register = can_auto_register;
            }

            public String getDuration_min_unit() {
                return duration_min_unit;
            }

            public void setDuration_min_unit(String duration_min_unit) {
                this.duration_min_unit = duration_min_unit;
            }

            public String getLogo_app_square() {
                return logo_app_square;
            }

            public void setLogo_app_square(String logo_app_square) {
                this.logo_app_square = logo_app_square;
            }

            public String getLocation_province() {
                return location_province;
            }

            public void setLocation_province(String location_province) {
                this.location_province = location_province;
            }

            public String getDuration_max_unit() {
                return duration_max_unit;
            }

            public void setDuration_max_unit(String duration_max_unit) {
                this.duration_max_unit = duration_max_unit;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public boolean isIs_public() {
                return is_public;
            }

            public void setIs_public(boolean is_public) {
                this.is_public = is_public;
            }

            public boolean isCan_bind_account() {
                return can_bind_account;
            }

            public void setCan_bind_account(boolean can_bind_account) {
                this.can_bind_account = can_bind_account;
            }

            public String getLogo_main() {
                return logo_main;
            }

            public void setLogo_main(String logo_main) {
                this.logo_main = logo_main;
            }

            public String getMain_bussiness() {
                return main_bussiness;
            }

            public void setMain_bussiness(String main_bussiness) {
                this.main_bussiness = main_bussiness;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLogo_app() {
                return logo_app;
            }

            public void setLogo_app(String logo_app) {
                this.logo_app = logo_app;
            }

            public String getInterest_min() {
                return interest_min;
            }

            public void setInterest_min(String interest_min) {
                this.interest_min = interest_min;
            }

            public String getDuration_max() {
                return duration_max;
            }

            public void setDuration_max(String duration_max) {
                this.duration_max = duration_max;
            }

            public String getDuration_min() {
                return duration_min;
            }

            public void setDuration_min(String duration_min) {
                this.duration_min = duration_min;
            }
        }

        public static class TagListBean {
            /**
             * is_show_dialog : true
             * name : 新手标
             * dialog_img : https://o0s106hgi.qnssl.com/bid_detail/tag/bid_detail_tag.png
             */

            private boolean is_show_dialog;
            private String name;
            private String dialog_img;

            public boolean isIs_show_dialog() {
                return is_show_dialog;
            }

            public void setIs_show_dialog(boolean is_show_dialog) {
                this.is_show_dialog = is_show_dialog;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDialog_img() {
                return dialog_img;
            }

            public void setDialog_img(String dialog_img) {
                this.dialog_img = dialog_img;
            }
        }
    }

    public static class CurrentBidsBean {

        private int status;
        private String status_str;
        private int bid_type;
        private String name;
        private String share_name;
        private String bid_type_str;
        private String total_share;
        private String share_amount;
        private int bid_id;
        private String introduction;
        private String extra_raise_interest;
        private String platform_bonus_interest;
        private String interest;
        private boolean extra_raise_interest_flag;
        private String min_invest_share;
        private boolean promotion_flag;
        private boolean is_daily_bonus;
        private PlatformBeanX platform;
        private boolean iphone_flag;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public int getBid_type() {
            return bid_type;
        }

        public void setBid_type(int bid_type) {
            this.bid_type = bid_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShare_name() {
            return share_name;
        }

        public void setShare_name(String share_name) {
            this.share_name = share_name;
        }

        public String getBid_type_str() {
            return bid_type_str;
        }

        public void setBid_type_str(String bid_type_str) {
            this.bid_type_str = bid_type_str;
        }

        public String getTotal_share() {
            return total_share;
        }

        public void setTotal_share(String total_share) {
            this.total_share = total_share;
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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getExtra_raise_interest() {
            return extra_raise_interest;
        }

        public void setExtra_raise_interest(String extra_raise_interest) {
            this.extra_raise_interest = extra_raise_interest;
        }

        public String getPlatform_bonus_interest() {
            return platform_bonus_interest;
        }

        public void setPlatform_bonus_interest(String platform_bonus_interest) {
            this.platform_bonus_interest = platform_bonus_interest;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public boolean isExtra_raise_interest_flag() {
            return extra_raise_interest_flag;
        }

        public void setExtra_raise_interest_flag(boolean extra_raise_interest_flag) {
            this.extra_raise_interest_flag = extra_raise_interest_flag;
        }

        public String getMin_invest_share() {
            return min_invest_share;
        }

        public void setMin_invest_share(String min_invest_share) {
            this.min_invest_share = min_invest_share;
        }

        public boolean isPromotion_flag() {
            return promotion_flag;
        }

        public void setPromotion_flag(boolean promotion_flag) {
            this.promotion_flag = promotion_flag;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public PlatformBeanX getPlatform() {
            return platform;
        }

        public void setPlatform(PlatformBeanX platform) {
            this.platform = platform;
        }

        public boolean isIphone_flag() {
            return iphone_flag;
        }

        public void setIphone_flag(boolean iphone_flag) {
            this.iphone_flag = iphone_flag;
        }

        public static class PlatformBeanX {
            /**
             * name : 星火钱包
             * logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/37c0db82/app-37c0db82-a851-4ce1-b92f-5c58c11a5911-1490940774.png
             * logo_app_square : https://o0s106hgi.qnssl.com/media/plat-logo/2cd176e7/app-2cd176e7-f990-42ca-aa6b-009b9dded38f-1473662752.png
             * logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/9d8b9857/thumb-9d8b9857-430a-4092-b18f-4d089d4c51f9-1473662341.png
             * shortname : xeenho
             * id : 48
             * logo_main : https://o0s106hgi.qnssl.com/media/plat-logo/196bd1d6/main-196bd1d6-96c1-4ca7-bc52-721d032dc5d3-1473662340.png
             */

            private String name;
            private String logo_app;
            private String logo_app_square;
            private String logo_thumbnail;
            private String shortname;
            private int id;
            private String logo_main;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo_app() {
                return logo_app;
            }

            public void setLogo_app(String logo_app) {
                this.logo_app = logo_app;
            }

            public String getLogo_app_square() {
                return logo_app_square;
            }

            public void setLogo_app_square(String logo_app_square) {
                this.logo_app_square = logo_app_square;
            }

            public String getLogo_thumbnail() {
                return logo_thumbnail;
            }

            public void setLogo_thumbnail(String logo_thumbnail) {
                this.logo_thumbnail = logo_thumbnail;
            }

            public String getShortname() {
                return shortname;
            }

            public void setShortname(String shortname) {
                this.shortname = shortname;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogo_main() {
                return logo_main;
            }

            public void setLogo_main(String logo_main) {
                this.logo_main = logo_main;
            }
        }
    }
}
