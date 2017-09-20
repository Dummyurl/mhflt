package com.changju.fanlitou.bean.bid;

import java.util.List;

/**
 * Created by chengww on 2017/6/14.
 */

public class BidInsurance {

    /**
     * bid_detail : {"sub_name":"只为真爱的你们而准备","insurance_company_introduction":"","bid_type_str":"普通标","insurance_company_pc_logo":"","duration_unit_str":"年","platform":{"interest_max":0,"is_bank":false,"is_vc":false,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/2bbd5200/thumb-2bbd5200-ef1b-45f6-bc92-cc27ac812b78-1477389470.png","bonding_method":"其他","can_auto_login":false,"id":58,"location_city":"上海","registered_capital":69,"credit_score":3,"is_state":false,"online_date":"15-01-04","is_private":true,"shortname":"renrenbx","can_auto_register":true,"duration_min_unit":"天","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/ad07fdf7/app-ad07fdf7-e831-4eb1-a043-481a746a7abb-1477389471.png","location_province":"上海","duration_max_unit":"个月","background":"汇保科技旗下\u201c人人保险\u201d","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/b464d5f3/main-b464d5f3-b051-4c58-b111-add2e7f1ec67-1477389470.png","main_bussiness":"保险","name":"人人保险","url":"http://www.renrenbx.com/","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/174e321a/app-174e321a-755b-42f4-ae4f-e601e541affd-1490927069.png","interest_min":0,"introduction":"<p>上海汇保科技有限公司(简称\u201c汇保科技\u201d)于2015年1月正式成立。由一批来自保险、互联网、风险投资等领域的精英组成。公司成立仅半年就取得了骄人的成绩，目前和国内超过30家保险企业强强联合，共同推出一系列精选保险产品。汇保科技旗下\u201c人人保险\u201dAPP通过对保险行业在线分销渠道的重塑加上先进的移动互联网技术以及创新的运营模式。迅速成为移动互联网保险行业的的一匹\u201c黑马\u201d。<\/p>","duration_max":999,"duration_min":10},"is_repeatable":false,"duration":3,"duration_days":1095,"status_str":"可投标","is_insurance_bid":true,"mobile_url":"http://uat.api2.renrenbx.com/mobile/product/productShow.html?productId=201610141018undergraduatelover&channel=test&accessToken=f6a85137d1ba43d0a6ee3d89d13f2033","settlement_days_after_bid_fulled":2,"introduction":[{"url":"https://o0s106hgi.qnssl.com/media/plat-logo/454d2075/main-454d2075-ae0e-4738-99f6-acc63b9e467c-1478616572.jpg"},{"url":"https://o0s106hgi.qnssl.com/media/plat-logo/ad1be985/main-ad1be985-73d4-481a-a247-2f69319d5e72-1478616678.jpg"},{"url":"https://o0s106hgi.qnssl.com/media/plat-logo/58188014/main-58188014-af35-455b-8f43-34b84d06a3f6-1478616572.jpg"}],"bonus_amount":22.425,"bonus_value":7.5,"bonus_interest":0.075,"insurance_company_name":"爱上鲜花","status":1,"product_img_url":"https://o0s106hgi.qnssl.com/media/plat-logo/dcd9437f/main-dcd9437f-43d0-426f-826e-5fdca01c39a0-1478596753.png","duration_unit":365,"price":299,"bid_order_url":"http://uat.api2.renrenbx.com/mobile/product/productShow.html?productId=201610141018undergraduatelover&channel=test&accessToken=f6a85137d1ba43d0a6ee3d89d13f2033","product_banner_img":"","bid_id":34,"is_free":false,"classify_person":[{"name":"新新青年","key":5}],"bid_url":"http://uat.api2.renrenbx.com/mobile/product/productShow.html?productId=201610141018undergraduatelover&channel=test&accessToken=f6a85137d1ba43d0a6ee3d89d13f2033","bid_type":1,"name":"大学生恋爱险钻石版","insurance_amount":"1颗50分心型钻石元","classify_insurance":[{"name":"创意保险","key":5}],"is_user_enjoy_bonus":false,"insurance_company_wap_logo":""}
     * is_registered : false
     * button_name : 前往购买
     */

    private BidDetailBean bid_detail;
    private boolean is_registered;
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

    public boolean isIs_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }

    public String getButton_name() {
        return button_name;
    }

    public void setButton_name(String button_name) {
        this.button_name = button_name;
    }

    public static class BidDetailBean {
        /**
         * sub_name : 只为真爱的你们而准备
         * insurance_company_introduction :
         * bid_type_str : 普通标
         * insurance_company_pc_logo :
         * duration_unit_str : 年
         * platform : {"interest_max":0,"is_bank":false,"is_vc":false,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/2bbd5200/thumb-2bbd5200-ef1b-45f6-bc92-cc27ac812b78-1477389470.png","bonding_method":"其他","can_auto_login":false,"id":58,"location_city":"上海","registered_capital":69,"credit_score":3,"is_state":false,"online_date":"15-01-04","is_private":true,"shortname":"renrenbx","can_auto_register":true,"duration_min_unit":"天","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/ad07fdf7/app-ad07fdf7-e831-4eb1-a043-481a746a7abb-1477389471.png","location_province":"上海","duration_max_unit":"个月","background":"汇保科技旗下\u201c人人保险\u201d","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/b464d5f3/main-b464d5f3-b051-4c58-b111-add2e7f1ec67-1477389470.png","main_bussiness":"保险","name":"人人保险","url":"http://www.renrenbx.com/","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/174e321a/app-174e321a-755b-42f4-ae4f-e601e541affd-1490927069.png","interest_min":0,"introduction":"<p>上海汇保科技有限公司(简称\u201c汇保科技\u201d)于2015年1月正式成立。由一批来自保险、互联网、风险投资等领域的精英组成。公司成立仅半年就取得了骄人的成绩，目前和国内超过30家保险企业强强联合，共同推出一系列精选保险产品。汇保科技旗下\u201c人人保险\u201dAPP通过对保险行业在线分销渠道的重塑加上先进的移动互联网技术以及创新的运营模式。迅速成为移动互联网保险行业的的一匹\u201c黑马\u201d。<\/p>","duration_max":999,"duration_min":10}
         * is_repeatable : false
         * duration : 3
         * duration_days : 1095
         * status_str : 可投标
         * is_insurance_bid : true
         * mobile_url : http://uat.api2.renrenbx.com/mobile/product/productShow.html?productId=201610141018undergraduatelover&channel=test&accessToken=f6a85137d1ba43d0a6ee3d89d13f2033
         * settlement_days_after_bid_fulled : 2
         * introduction : [{"url":"https://o0s106hgi.qnssl.com/media/plat-logo/454d2075/main-454d2075-ae0e-4738-99f6-acc63b9e467c-1478616572.jpg"},{"url":"https://o0s106hgi.qnssl.com/media/plat-logo/ad1be985/main-ad1be985-73d4-481a-a247-2f69319d5e72-1478616678.jpg"},{"url":"https://o0s106hgi.qnssl.com/media/plat-logo/58188014/main-58188014-af35-455b-8f43-34b84d06a3f6-1478616572.jpg"}]
         * bonus_amount : 22.425
         * bonus_value : 7.5
         * bonus_interest : 0.075
         * insurance_company_name : 爱上鲜花
         * status : 1
         * product_img_url : https://o0s106hgi.qnssl.com/media/plat-logo/dcd9437f/main-dcd9437f-43d0-426f-826e-5fdca01c39a0-1478596753.png
         * duration_unit : 365
         * price : 299
         * bid_order_url : http://uat.api2.renrenbx.com/mobile/product/productShow.html?productId=201610141018undergraduatelover&channel=test&accessToken=f6a85137d1ba43d0a6ee3d89d13f2033
         * product_banner_img :
         * bid_id : 34
         * is_free : false
         * classify_person : [{"name":"新新青年","key":5}]
         * bid_url : http://uat.api2.renrenbx.com/mobile/product/productShow.html?productId=201610141018undergraduatelover&channel=test&accessToken=f6a85137d1ba43d0a6ee3d89d13f2033
         * bid_type : 1
         * name : 大学生恋爱险钻石版
         * insurance_amount : 1颗50分心型钻石元
         * classify_insurance : [{"name":"创意保险","key":5}]
         * is_user_enjoy_bonus : false
         * insurance_company_wap_logo :
         */

        private String sub_name;
        private String insurance_company_introduction;
        private String bid_type_str;
        private String insurance_company_pc_logo;
        private String duration_unit_str;
        private PlatformBean platform;
        private boolean is_repeatable;
        private int duration;
        private int duration_days;
        private String status_str;
        private boolean is_insurance_bid;
        private String mobile_url;
        private int settlement_days_after_bid_fulled;
        private String bonus_amount;
        private String bonus_value;
        private String bonus_interest;
        private String insurance_company_name;
        private int status;
        private String product_img_url;
        private int duration_unit;
        private String price;
        private String bid_order_url;
        private String product_banner_img;
        private int bid_id;
        private boolean is_free;
        private String bid_url;
        private int bid_type;
        private String name;
        private String insurance_amount;
        private boolean is_user_enjoy_bonus;
        private String insurance_company_wap_logo;
        private List<IntroductionBean> introduction;
        private List<ClassifyPersonBean> classify_person;
        private List<ClassifyInsuranceBean> classify_insurance;

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public String getInsurance_company_introduction() {
            return insurance_company_introduction;
        }

        public void setInsurance_company_introduction(String insurance_company_introduction) {
            this.insurance_company_introduction = insurance_company_introduction;
        }

        public String getBid_type_str() {
            return bid_type_str;
        }

        public void setBid_type_str(String bid_type_str) {
            this.bid_type_str = bid_type_str;
        }

        public String getInsurance_company_pc_logo() {
            return insurance_company_pc_logo;
        }

        public void setInsurance_company_pc_logo(String insurance_company_pc_logo) {
            this.insurance_company_pc_logo = insurance_company_pc_logo;
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

        public boolean isIs_repeatable() {
            return is_repeatable;
        }

        public void setIs_repeatable(boolean is_repeatable) {
            this.is_repeatable = is_repeatable;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
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

        public boolean isIs_insurance_bid() {
            return is_insurance_bid;
        }

        public void setIs_insurance_bid(boolean is_insurance_bid) {
            this.is_insurance_bid = is_insurance_bid;
        }

        public String getMobile_url() {
            return mobile_url;
        }

        public void setMobile_url(String mobile_url) {
            this.mobile_url = mobile_url;
        }

        public int getSettlement_days_after_bid_fulled() {
            return settlement_days_after_bid_fulled;
        }

        public void setSettlement_days_after_bid_fulled(int settlement_days_after_bid_fulled) {
            this.settlement_days_after_bid_fulled = settlement_days_after_bid_fulled;
        }

        public String getBonus_amount() {
            return bonus_amount;
        }

        public void setBonus_amount(String bonus_amount) {
            this.bonus_amount = bonus_amount;
        }

        public String getBonus_value() {
            return bonus_value;
        }

        public void setBonus_value(String bonus_value) {
            this.bonus_value = bonus_value;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public String getInsurance_company_name() {
            return insurance_company_name;
        }

        public void setInsurance_company_name(String insurance_company_name) {
            this.insurance_company_name = insurance_company_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getProduct_img_url() {
            return product_img_url;
        }

        public void setProduct_img_url(String product_img_url) {
            this.product_img_url = product_img_url;
        }

        public int getDuration_unit() {
            return duration_unit;
        }

        public void setDuration_unit(int duration_unit) {
            this.duration_unit = duration_unit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBid_order_url() {
            return bid_order_url;
        }

        public void setBid_order_url(String bid_order_url) {
            this.bid_order_url = bid_order_url;
        }

        public String getProduct_banner_img() {
            return product_banner_img;
        }

        public void setProduct_banner_img(String product_banner_img) {
            this.product_banner_img = product_banner_img;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public boolean isIs_free() {
            return is_free;
        }

        public void setIs_free(boolean is_free) {
            this.is_free = is_free;
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

        public String getInsurance_amount() {
            return insurance_amount;
        }

        public void setInsurance_amount(String insurance_amount) {
            this.insurance_amount = insurance_amount;
        }

        public boolean isIs_user_enjoy_bonus() {
            return is_user_enjoy_bonus;
        }

        public void setIs_user_enjoy_bonus(boolean is_user_enjoy_bonus) {
            this.is_user_enjoy_bonus = is_user_enjoy_bonus;
        }

        public String getInsurance_company_wap_logo() {
            return insurance_company_wap_logo;
        }

        public void setInsurance_company_wap_logo(String insurance_company_wap_logo) {
            this.insurance_company_wap_logo = insurance_company_wap_logo;
        }

        public List<IntroductionBean> getIntroduction() {
            return introduction;
        }

        public void setIntroduction(List<IntroductionBean> introduction) {
            this.introduction = introduction;
        }

        public List<ClassifyPersonBean> getClassify_person() {
            return classify_person;
        }

        public void setClassify_person(List<ClassifyPersonBean> classify_person) {
            this.classify_person = classify_person;
        }

        public List<ClassifyInsuranceBean> getClassify_insurance() {
            return classify_insurance;
        }

        public void setClassify_insurance(List<ClassifyInsuranceBean> classify_insurance) {
            this.classify_insurance = classify_insurance;
        }

        public static class PlatformBean {
            /**
             * interest_max : 0
             * is_bank : false
             * is_vc : false
             * logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/2bbd5200/thumb-2bbd5200-ef1b-45f6-bc92-cc27ac812b78-1477389470.png
             * bonding_method : 其他
             * can_auto_login : false
             * id : 58
             * location_city : 上海
             * registered_capital : 69
             * credit_score : 3
             * is_state : false
             * online_date : 15-01-04
             * is_private : true
             * shortname : renrenbx
             * can_auto_register : true
             * duration_min_unit : 天
             * logo_app_square : https://o0s106hgi.qnssl.com/media/plat-logo/ad07fdf7/app-ad07fdf7-e831-4eb1-a043-481a746a7abb-1477389471.png
             * location_province : 上海
             * duration_max_unit : 个月
             * background : 汇保科技旗下“人人保险”
             * is_public : false
             * can_bind_account : false
             * logo_main : https://o0s106hgi.qnssl.com/media/plat-logo/b464d5f3/main-b464d5f3-b051-4c58-b111-add2e7f1ec67-1477389470.png
             * main_bussiness : 保险
             * name : 人人保险
             * url : http://www.renrenbx.com/
             * logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/174e321a/app-174e321a-755b-42f4-ae4f-e601e541affd-1490927069.png
             * interest_min : 0
             * introduction : <p>上海汇保科技有限公司(简称“汇保科技”)于2015年1月正式成立。由一批来自保险、互联网、风险投资等领域的精英组成。公司成立仅半年就取得了骄人的成绩，目前和国内超过30家保险企业强强联合，共同推出一系列精选保险产品。汇保科技旗下“人人保险”APP通过对保险行业在线分销渠道的重塑加上先进的移动互联网技术以及创新的运营模式。迅速成为移动互联网保险行业的的一匹“黑马”。</p>
             * duration_max : 999
             * duration_min : 10
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
            private String introduction;
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

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
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

        public static class IntroductionBean {
            /**
             * url : https://o0s106hgi.qnssl.com/media/plat-logo/454d2075/main-454d2075-ae0e-4738-99f6-acc63b9e467c-1478616572.jpg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ClassifyPersonBean {
            /**
             * name : 新新青年
             * key : 5
             */

            private String name;
            private int key;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }
        }

        public static class ClassifyInsuranceBean {
            /**
             * name : 创意保险
             * key : 5
             */

            private String name;
            private int key;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getKey() {
                return key;
            }

            public void setKey(int key) {
                this.key = key;
            }
        }
    }
}
