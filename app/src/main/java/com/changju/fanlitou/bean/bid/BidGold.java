package com.changju.fanlitou.bean.bid;

import java.util.List;

/**
 * Created by chengww on 2017/6/14.
 */

public class BidGold {

    /**
     * bid_detail : {"bid_category":2,"bid_type_str":"新手标","total_invest_people":146,"rank":1,"duration":7,"duration_unit_str":"天","financing_rate":66,"current_price":275.21,"is_gold_bid":true,"original_bonus_info":{"is_show":false,"original_bonus_value":1},"max_invest_amount":0,"platform":{"interest_max":14,"is_bank":false,"is_vc":true,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/89e9c283/thumb-89e9c283-17ea-4352-8224-3514a646a8ce-1475129118.png","can_auto_login":false,"id":28,"location_city":"北京","registered_capital":500,"credit_score":3,"is_state":false,"online_date":"2015-08-15","is_private":false,"shortname":"aujin","can_auto_register":true,"duration_min_unit":"天","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/a8d06df4/app-a8d06df4-eeb3-4135-8904-8d3810b7427e-1467179817.png","location_province":"北京","duration_max_unit":"年","background":"中信信托千万元战略入股","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/2dc82545/main-2dc82545-7efa-4159-a4ad-37189eaa7758-1475129117.png","main_bussiness":"互联网金融，互联网黄金","name":"黄金管家","url":"https://www.au32.cn/","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/577c5c44/app-577c5c44-e715-49d9-ad10-1c19a3a0c3be-1490927774.png","interest_min":1.6,"duration_max":5,"duration_min":30,"first_invest_redpacket_award_info":{"min_duration_days":0,"type":"","red_packet_amount":0,"is_show":false,"min_invest_amount":0,"img_url":""}},"introduction":"<h2>定期金<\/h2>\n            <h4>规模、时间自定义&nbsp;&nbsp;收益稳中求进<\/h4>\n            <p>您可以获得更高的存储利息，更能额外获得产品到期时金价上涨带来的收益。<\/p>\n            <div>\n                    <p style=\"word-break: break-all;\">\n                        利息管理费：\n                    <\/p>\n                    <p>\n                        无\n                    <\/p>\n            <\/div>\n            ","settlement_days_after_bid_fulled":2,"interest":19.88,"status":1,"status_str":"可投标","is_registered":true,"duration_unit":1,"bid_id":43,"display_type":"normal_gold_bid","min_invest_amount":1,"bid_url":"https://au32.cn/web/submitRegular.action?data.tradeAmount=1&data.liMoneyId=57","bid_type":0,"name":"新手礼包7天","bid_category_str":"定期","platform_bonus_interest":0.7,"total_invest_weight":1460,"is_user_enjoy_bonus":true,"tag_list":[{"is_show_dialog":true,"name":"新手标","dialog_content_list":["1","2"]},{"is_show_dialog":true,"name":"按日返","dialog_content_list":["您已通过返利投注册玉米理财","每次投资通过返利投跳转即可享受返利"]}],"iphone_flag":false}
     * button_name : 前往购买
     */

    private BidDetailBean bid_detail;
    private String button_name;
    private boolean is_show_register_dialog;

    public boolean is_show_register_dialog() {
        return is_show_register_dialog;
    }

    public void setIs_show_register_dialog(boolean is_show_register_dialog) {
        this.is_show_register_dialog = is_show_register_dialog;
    }

    public BidDetailBean getBid_detail() {
        return bid_detail;
    }

    public void setBid_detail(BidDetailBean bid_detail) {
        this.bid_detail = bid_detail;
    }

    public String getButton_name() {
        return button_name;
    }

    public void setButton_name(String button_name) {
        this.button_name = button_name;
    }

    public static class BidDetailBean {
        /**
         * bid_category : 2
         * bid_type_str : 新手标
         * total_invest_people : 146
         * rank : 1
         * duration : 7
         * duration_unit_str : 天
         * financing_rate : 66
         * current_price : 275.21
         * is_gold_bid : true
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         * max_invest_amount : 0
         * platform : {"interest_max":14,"is_bank":false,"is_vc":true,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/89e9c283/thumb-89e9c283-17ea-4352-8224-3514a646a8ce-1475129118.png","can_auto_login":false,"id":28,"location_city":"北京","registered_capital":500,"credit_score":3,"is_state":false,"online_date":"2015-08-15","is_private":false,"shortname":"aujin","can_auto_register":true,"duration_min_unit":"天","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/a8d06df4/app-a8d06df4-eeb3-4135-8904-8d3810b7427e-1467179817.png","location_province":"北京","duration_max_unit":"年","background":"中信信托千万元战略入股","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/2dc82545/main-2dc82545-7efa-4159-a4ad-37189eaa7758-1475129117.png","main_bussiness":"互联网金融，互联网黄金","name":"黄金管家","url":"https://www.au32.cn/","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/577c5c44/app-577c5c44-e715-49d9-ad10-1c19a3a0c3be-1490927774.png","interest_min":1.6,"duration_max":5,"duration_min":30,"first_invest_redpacket_award_info":{"min_duration_days":0,"type":"","red_packet_amount":0,"is_show":false,"min_invest_amount":0,"img_url":""}}
         * introduction : <h2>定期金</h2>
         <h4>规模、时间自定义&nbsp;&nbsp;收益稳中求进</h4>
         <p>您可以获得更高的存储利息，更能额外获得产品到期时金价上涨带来的收益。</p>
         <div>
         <p style="word-break: break-all;">
         利息管理费：
         </p>
         <p>
         无
         </p>
         </div>

         * settlement_days_after_bid_fulled : 2
         * interest : 19.88
         * status : 1
         * status_str : 可投标
         * is_registered : true
         * duration_unit : 1
         * bid_id : 43
         * display_type : normal_gold_bid
         * min_invest_amount : 1
         * bid_url : https://au32.cn/web/submitRegular.action?data.tradeAmount=1&data.liMoneyId=57
         * bid_type : 0
         * name : 新手礼包7天
         * bid_category_str : 定期
         * platform_bonus_interest : 0.7
         * total_invest_weight : 1460
         * is_user_enjoy_bonus : true
         * tag_list : [{"is_show_dialog":true,"name":"新手标","dialog_content_list":["1","2"]},{"is_show_dialog":true,"name":"按日返","dialog_content_list":["您已通过返利投注册玉米理财","每次投资通过返利投跳转即可享受返利"]}]
         * iphone_flag : false
         */

        private int bid_category;
        private String bid_type_str;
        private String total_invest_people;
        private String rank;
        private String duration;
        private String duration_unit_str;
        private String financing_rate;
        private String current_price;
        private boolean is_gold_bid;
        private OriginalBonusInfoBean original_bonus_info;
        private String max_invest_amount;
        private PlatformBean platform;
        private String introduction;
        private String settlement_days_after_bid_fulled;
        private String interest;
        private int status;
        private String status_str;
        private boolean is_registered;
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
        private boolean is_user_enjoy_bonus;
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

        public String getTotal_invest_people() {
            return total_invest_people;
        }

        public void setTotal_invest_people(String total_invest_people) {
            this.total_invest_people = total_invest_people;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
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

        public String getSettlement_days_after_bid_fulled() {
            return settlement_days_after_bid_fulled;
        }

        public void setSettlement_days_after_bid_fulled(String settlement_days_after_bid_fulled) {
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

        public boolean isIs_registered() {
            return is_registered;
        }

        public void setIs_registered(boolean is_registered) {
            this.is_registered = is_registered;
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

        public boolean isIs_user_enjoy_bonus() {
            return is_user_enjoy_bonus;
        }

        public void setIs_user_enjoy_bonus(boolean is_user_enjoy_bonus) {
            this.is_user_enjoy_bonus = is_user_enjoy_bonus;
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
             * first_invest_redpacket_award_info : {"min_duration_days":0,"type":"","red_packet_amount":0,"is_show":false,"min_invest_amount":0,"img_url":""}
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
            private FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info;

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

            public FirstInvestRedpacketAwardInfoBean getFirst_invest_redpacket_award_info() {
                return first_invest_redpacket_award_info;
            }

            public void setFirst_invest_redpacket_award_info(FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info) {
                this.first_invest_redpacket_award_info = first_invest_redpacket_award_info;
            }

            public static class FirstInvestRedpacketAwardInfoBean {
                /**
                 * min_duration_days : 0
                 * type :
                 * red_packet_amount : 0
                 * is_show : false
                 * min_invest_amount : 0
                 * img_url :
                 */

                private int min_duration_days;
                private String type;
                private String red_packet_amount;
                private boolean is_show;
                private String min_invest_amount;
                private String img_url;

                public int getMin_duration_days() {
                    return min_duration_days;
                }

                public void setMin_duration_days(int min_duration_days) {
                    this.min_duration_days = min_duration_days;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getRed_packet_amount() {
                    return red_packet_amount;
                }

                public void setRed_packet_amount(String red_packet_amount) {
                    this.red_packet_amount = red_packet_amount;
                }

                public boolean isIs_show() {
                    return is_show;
                }

                public void setIs_show(boolean is_show) {
                    this.is_show = is_show;
                }

                public String getMin_invest_amount() {
                    return min_invest_amount;
                }

                public void setMin_invest_amount(String min_invest_amount) {
                    this.min_invest_amount = min_invest_amount;
                }

                public String getImg_url() {
                    return img_url;
                }

                public void setImg_url(String img_url) {
                    this.img_url = img_url;
                }
            }
        }

        public static class TagListBean {
            /**
             * is_show_dialog : true
             * name : 新手标
             * dialog_content_list : ["1","2"]
             */

            private boolean is_show_dialog;
            private String name;
            private String dialog_img;
            private String type;
            private String title;
            private List<String> content_list;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getContent_list() {
                return content_list;
            }

            public void setContent_list(List<String> content_list) {
                this.content_list = content_list;
            }

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
}
