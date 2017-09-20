package com.changju.fanlitou.bean.bid;

import java.util.List;

/**
 * Created by chengww on 2017/6/14.
 */

public class BidAbroad {

    /**
     * bid_detail : {"bid_type_str":"普通标","bonus_type":1,"duration_unit_str":"个月","platform":{"interest_max":9.21,"is_bank":false,"is_vc":true,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/cfb11baa/thumb-cfb11baa-07a2-47c2-83f5-750ce23a73fe-1467699320.png","bonding_method":"第三方支付和托管账户","can_auto_login":false,"id":34,"location_city":"上海","registered_capital":100,"credit_score":3,"is_state":false,"online_date":"2016-01-28","is_private":false,"shortname":"meixin","can_auto_register":false,"duration_min_unit":"个月","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/3c3547d7/app-3c3547d7-7d99-4a40-baff-25a951ea38fe-1467270192.png","location_province":"上海","duration_max_unit":"个月","background":"BAI和梅花天使领投千万元Pre-A融资","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/ab822e06/main-ab822e06-f3aa-423b-9a2d-bada0c1ed9ae-1467699320.png","main_bussiness":"中小企业贷、房贷、国际保险、IPO股权、美元市场借贷","name":"美信金融","url":"http://www.meixinfinance.com","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/ac820f47/app-ac820f47-3424-450c-b234-26ba3f392eaa-1490927658.png","interest_min":5,"duration_max":13,"duration_min":1},"duration":6,"total_amount_str":"500万","remain_amount":3200000,"duration_days":180,"status_str":"可投标","pay_type":3,"min_amount_str":"1万","wap_introduction":"\n        <table class=\"bid-introduction\">\n                        <tr>\n                            <td class=\"name\">起投金额<\/td>\n                            <td class=\"val\">10000.0 美金<\/td>\n                        <\/tr>\n                        <tr>\n                            <td class=\"name\">起息日<\/td>\n                            <td class=\"val\">2016-09-01<\/td>\n                        <\/tr>\n                        <tr>\n                            <td class=\"name\">返利日期<\/td>\n                            <td class=\"val\">满标审核即返利<\/td>\n                        <\/tr>\n                        <tr>\n                            <td class=\"name\">还款方式<\/td>\n                            <td class=\"val\">一次性还本付息<\/td>\n                        <\/tr>\n                        <tr>\n                            <td class=\"name\">债权转让<\/td>\n                            <td class=\"val\">不可转让<\/td>\n                        <\/tr>\n                        <tr>\n                            <td class=\"name\">利息管理费<\/td>\n                            <td class=\"val\">无<\/td>\n                        <\/tr>\n                    <\/table>\n        ","introduction":"\n        <div class=\"introduction-content\">\n          <dl>\n            <dt>起息与回款<\/dt>\n            <dd><span class=\"table-title\">起投金额<\/span><span class=\"table-content\">10000.0 美金<\/span><span class=\"table-title\">起息日<\/span><span class=\"table-content\">2016-09-01<\/span><\/dd>\n            <dd class=\"last-dd\"><span class=\"table-title\">返利日期<\/span><span class=\"table-content\">满标审核即返利<\/span><span class=\"table-title\">还款方式<\/span><span class=\"table-content\">一次性还本付息<\/span><\/dd>\n          <\/dl>\n          <dl class=\"table-last\">\n            <dt>手续费相关<\/dt>\n            <dd><span class=\"table-title\">债权转让<\/span><span class=\"table-content\">不可转让<\/span><\/dd>\n            <dd class=\"last-dd\"><span class=\"table-title\">利息管理费<\/span><span class=\"table-content\">无<\/span><\/dd>\n          <\/dl>\n        <\/div>\n        ","is_abroad_bid":true,"bonus_value":1,"interest":5,"progress":0.35,"status":1,"pay_type_str":"一次性还本付息","duration_unit":30,"bid_order_url":"https://invest.meixinglobal.com/invest.html?access_token=514a1017-d286-4506-b58c-96ded5e59b92&phone=%2B86%2013426178576&partner_id=FANLITU00&product_id=8","bid_id":4,"min_amount":10000,"total_amount":5000000,"bid_url":"https://oapi.meixinglobal.com/products/anxin_plan.html?product_id=8","bid_type":1,"name":"安心计划6个月","mini_program_introduction":{"pay_type":"一次性还本付息","interest_manage_fee":"无","assign_info":"不可转让","min_invest_amount":"10,000美金","interest_start_date":"2016-08-31","bonus_date":"满标审核即返利"},"is_registered":false,"progress_percent":35,"is_user_enjoy_bonus":false,"tag_list":[{"is_show_dialog":true,"name":"新手标","dialog_content_list":["1","2"]},{"is_show_dialog":true,"name":"按日返","dialog_content_list":["您已通过返利投注册玉米理财","每次投资通过返利投跳转即可享受返利"]}],"iphone_flag":false}
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
         * bid_type_str : 普通标
         * bonus_type : 1
         * duration_unit_str : 个月
         * platform : {"interest_max":9.21,"is_bank":false,"is_vc":true,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/cfb11baa/thumb-cfb11baa-07a2-47c2-83f5-750ce23a73fe-1467699320.png","bonding_method":"第三方支付和托管账户","can_auto_login":false,"id":34,"location_city":"上海","registered_capital":100,"credit_score":3,"is_state":false,"online_date":"2016-01-28","is_private":false,"shortname":"meixin","can_auto_register":false,"duration_min_unit":"个月","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/3c3547d7/app-3c3547d7-7d99-4a40-baff-25a951ea38fe-1467270192.png","location_province":"上海","duration_max_unit":"个月","background":"BAI和梅花天使领投千万元Pre-A融资","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/ab822e06/main-ab822e06-f3aa-423b-9a2d-bada0c1ed9ae-1467699320.png","main_bussiness":"中小企业贷、房贷、国际保险、IPO股权、美元市场借贷","name":"美信金融","url":"http://www.meixinfinance.com","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/ac820f47/app-ac820f47-3424-450c-b234-26ba3f392eaa-1490927658.png","interest_min":5,"duration_max":13,"duration_min":1}
         * duration : 6
         * total_amount_str : 500万
         * remain_amount : 3200000
         * duration_days : 180
         * status_str : 可投标
         * pay_type : 3
         * min_amount_str : 1万
         * wap_introduction :
         <table class="bid-introduction">
         <tr>
         <td class="name">起投金额</td>
         <td class="val">10000.0 美金</td>
         </tr>
         <tr>
         <td class="name">起息日</td>
         <td class="val">2016-09-01</td>
         </tr>
         <tr>
         <td class="name">返利日期</td>
         <td class="val">满标审核即返利</td>
         </tr>
         <tr>
         <td class="name">还款方式</td>
         <td class="val">一次性还本付息</td>
         </tr>
         <tr>
         <td class="name">债权转让</td>
         <td class="val">不可转让</td>
         </tr>
         <tr>
         <td class="name">利息管理费</td>
         <td class="val">无</td>
         </tr>
         </table>

         * introduction :
         <div class="introduction-content">
         <dl>
         <dt>起息与回款</dt>
         <dd><span class="table-title">起投金额</span><span class="table-content">10000.0 美金</span><span class="table-title">起息日</span><span class="table-content">2016-09-01</span></dd>
         <dd class="last-dd"><span class="table-title">返利日期</span><span class="table-content">满标审核即返利</span><span class="table-title">还款方式</span><span class="table-content">一次性还本付息</span></dd>
         </dl>
         <dl class="table-last">
         <dt>手续费相关</dt>
         <dd><span class="table-title">债权转让</span><span class="table-content">不可转让</span></dd>
         <dd class="last-dd"><span class="table-title">利息管理费</span><span class="table-content">无</span></dd>
         </dl>
         </div>

         * is_abroad_bid : true
         * bonus_value : 1
         * interest : 5
         * progress : 0.35
         * status : 1
         * pay_type_str : 一次性还本付息
         * duration_unit : 30
         * bid_order_url : https://invest.meixinglobal.com/invest.html?access_token=514a1017-d286-4506-b58c-96ded5e59b92&phone=%2B86%2013426178576&partner_id=FANLITU00&product_id=8
         * bid_id : 4
         * min_amount : 10000
         * total_amount : 5000000
         * bid_url : https://oapi.meixinglobal.com/products/anxin_plan.html?product_id=8
         * bid_type : 1
         * name : 安心计划6个月
         * mini_program_introduction : {"pay_type":"一次性还本付息","interest_manage_fee":"无","assign_info":"不可转让","min_invest_amount":"10,000美金","interest_start_date":"2016-08-31","bonus_date":"满标审核即返利"}
         * is_registered : false
         * progress_percent : 35
         * is_user_enjoy_bonus : false
         * tag_list : [{"is_show_dialog":true,"name":"新手标","dialog_content_list":["1","2"]},{"is_show_dialog":true,"name":"按日返","dialog_content_list":["您已通过返利投注册玉米理财","每次投资通过返利投跳转即可享受返利"]}]
         * iphone_flag : false
         */

        private String bid_type_str;
        private int bonus_type;
        private String duration_unit_str;
        private PlatformBean platform;
        private int duration;
        private String total_amount_str;
        private String remain_amount;
        private int duration_days;
        private String status_str;
        private int pay_type;
        private String min_amount_str;
        private String wap_introduction;
        private String introduction;
        private boolean is_abroad_bid;
        private String bonus_value;
        private String interest;
        private String progress;
        private int status;
        private String pay_type_str;
        private int duration_unit;
        private String bid_order_url;
        private int bid_id;
        private String min_amount;
        private String total_amount;
        private String bid_url;
        private int bid_type;
        private String name;
        private MiniProgramIntroductionBean mini_program_introduction;
        private boolean is_registered;
        private String progress_percent;
        private boolean is_user_enjoy_bonus;
        private boolean iphone_flag;
        private List<TagListBean> tag_list;

        public String getBid_type_str() {
            return bid_type_str;
        }

        public void setBid_type_str(String bid_type_str) {
            this.bid_type_str = bid_type_str;
        }

        public int getBonus_type() {
            return bonus_type;
        }

        public void setBonus_type(int bonus_type) {
            this.bonus_type = bonus_type;
        }

        public String getDuration_unit_str() {
            return duration_unit_str;
        }

        public void setDuration_unit_str(String duration_unit_str) {
            this.duration_unit_str = duration_unit_str;
        }

        public PlatformBean getPlatform() {
            return platform;
        }

        public void setPlatform(PlatformBean platform) {
            this.platform = platform;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getTotal_amount_str() {
            return total_amount_str;
        }

        public void setTotal_amount_str(String total_amount_str) {
            this.total_amount_str = total_amount_str;
        }

        public String getRemain_amount() {
            return remain_amount;
        }

        public void setRemain_amount(String remain_amount) {
            this.remain_amount = remain_amount;
        }

        public int getDuration_days() {
            return duration_days;
        }

        public void setDuration_days(int duration_days) {
            this.duration_days = duration_days;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getMin_amount_str() {
            return min_amount_str;
        }

        public void setMin_amount_str(String min_amount_str) {
            this.min_amount_str = min_amount_str;
        }

        public String getWap_introduction() {
            return wap_introduction;
        }

        public void setWap_introduction(String wap_introduction) {
            this.wap_introduction = wap_introduction;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public boolean isIs_abroad_bid() {
            return is_abroad_bid;
        }

        public void setIs_abroad_bid(boolean is_abroad_bid) {
            this.is_abroad_bid = is_abroad_bid;
        }

        public String getBonus_value() {
            return bonus_value;
        }

        public void setBonus_value(String bonus_value) {
            this.bonus_value = bonus_value;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPay_type_str() {
            return pay_type_str;
        }

        public void setPay_type_str(String pay_type_str) {
            this.pay_type_str = pay_type_str;
        }

        public int getDuration_unit() {
            return duration_unit;
        }

        public void setDuration_unit(int duration_unit) {
            this.duration_unit = duration_unit;
        }

        public String getBid_order_url() {
            return bid_order_url;
        }

        public void setBid_order_url(String bid_order_url) {
            this.bid_order_url = bid_order_url;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getMin_amount() {
            return min_amount;
        }

        public void setMin_amount(String min_amount) {
            this.min_amount = min_amount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
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

        public MiniProgramIntroductionBean getMini_program_introduction() {
            return mini_program_introduction;
        }

        public void setMini_program_introduction(MiniProgramIntroductionBean mini_program_introduction) {
            this.mini_program_introduction = mini_program_introduction;
        }

        public boolean isIs_registered() {
            return is_registered;
        }

        public void setIs_registered(boolean is_registered) {
            this.is_registered = is_registered;
        }

        public String getProgress_percent() {
            return progress_percent;
        }

        public void setProgress_percent(String progress_percent) {
            this.progress_percent = progress_percent;
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

        public static class PlatformBean {
            /**
             * interest_max : 9.21
             * is_bank : false
             * is_vc : true
             * logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/cfb11baa/thumb-cfb11baa-07a2-47c2-83f5-750ce23a73fe-1467699320.png
             * bonding_method : 第三方支付和托管账户
             * can_auto_login : false
             * id : 34
             * location_city : 上海
             * registered_capital : 100
             * credit_score : 3
             * is_state : false
             * online_date : 2016-01-28
             * is_private : false
             * shortname : meixin
             * can_auto_register : false
             * duration_min_unit : 个月
             * logo_app_square : https://o0s106hgi.qnssl.com/media/plat-logo/3c3547d7/app-3c3547d7-7d99-4a40-baff-25a951ea38fe-1467270192.png
             * location_province : 上海
             * duration_max_unit : 个月
             * background : BAI和梅花天使领投千万元Pre-A融资
             * is_public : false
             * can_bind_account : false
             * logo_main : https://o0s106hgi.qnssl.com/media/plat-logo/ab822e06/main-ab822e06-f3aa-423b-9a2d-bada0c1ed9ae-1467699320.png
             * main_bussiness : 中小企业贷、房贷、国际保险、IPO股权、美元市场借贷
             * name : 美信金融
             * url : http://www.meixinfinance.com
             * logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/ac820f47/app-ac820f47-3424-450c-b234-26ba3f392eaa-1490927658.png
             * interest_min : 5
             * duration_max : 13
             * duration_min : 1
             */

            private String interest_max;
            private boolean is_bank;
            private boolean is_vc;
            private String logo_thumbnail;
            private String bonding_method;
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

            public String getBonding_method() {
                return bonding_method;
            }

            public void setBonding_method(String bonding_method) {
                this.bonding_method = bonding_method;
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

        public static class MiniProgramIntroductionBean {
            /**
             * pay_type : 一次性还本付息
             * interest_manage_fee : 无
             * assign_info : 不可转让
             * min_invest_amount : 10,000美金
             * interest_start_date : 2016-08-31
             * bonus_date : 满标审核即返利
             */

            private String pay_type;
            private String interest_manage_fee;
            private String assign_info;
            private String min_invest_amount;
            private String interest_start_date;
            private String bonus_date;

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getInterest_manage_fee() {
                return interest_manage_fee;
            }

            public void setInterest_manage_fee(String interest_manage_fee) {
                this.interest_manage_fee = interest_manage_fee;
            }

            public String getAssign_info() {
                return assign_info;
            }

            public void setAssign_info(String assign_info) {
                this.assign_info = assign_info;
            }

            public String getMin_invest_amount() {
                return min_invest_amount;
            }

            public void setMin_invest_amount(String min_invest_amount) {
                this.min_invest_amount = min_invest_amount;
            }

            public String getInterest_start_date() {
                return interest_start_date;
            }

            public void setInterest_start_date(String interest_start_date) {
                this.interest_start_date = interest_start_date;
            }

            public String getBonus_date() {
                return bonus_date;
            }

            public void setBonus_date(String bonus_date) {
                this.bonus_date = bonus_date;
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

            public List<String> getContent_list() {
                return content_list;
            }

            public void setContent_list(List<String> content_list) {
                this.content_list = content_list;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
