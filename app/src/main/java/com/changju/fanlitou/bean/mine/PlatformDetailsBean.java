package com.changju.fanlitou.bean.mine;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class PlatformDetailsBean implements Serializable{


    /**
     * tab_list : [{"tab_class":1,"title":"热门活动"},{"tab_class":2,"title":"平台介绍"},{"tab_class":3,"title":"平台规则"}]
     * interest_max : 16
     * raise_interest_flag : false
     * is_intelligent : false
     * is_paipaidai_activity : false
     * activity_start_day : 0
     * bonding_method : 小贷公司 、 融资性担保公司 、 抵押 、 风险准备金(515万元)
     * logo : https://o0s106hgi.qnssl.com/media/plat-logo/aef6256f/app-aef6256f-4ef0-4ff4-9edc-e1c5f54611f1-1490928151.png
     * id : 14
     * is_login : true
     * location_city : 北京
     * registered_capital : 5000
     * is_bank_storage_tube : false
     * is_enjoy_bonus : false
     * activity_start_year : 0
     * activity_first_invest_raise_interest : 0
     * online_date : 2014-03-01
     * is_free_withdraw : false
     * is_can_auto_register : false
     * is_registered : false
     * raise_interest : 0.5
     * is_checked : true
     * is_daily_bonus : false
     * platform_names : {}
     * is_can_assign : false
     * activity_start_month : 0
     * short_name : baocai
     * interest_manage_fee : false
     * activity_second_invest_raise_interest : 0
     * activity_limit_amount_str :
     * activity_dialog_str :
     * background : 凯乐科技1.3亿人民币战略投资
     * platform_activity : [{"content":"洪荒之\u201c利\u201d爆发 各式红包等你拿","success":true,"title":"红包活动","activity_logo_url":"https://o0s106hgi.qnssl.com/wap/platform_detail/activity_logo/red_packet_new.png","shrot_name":"baocai","list_url":"/products/","img_url":"https://o0s106hgi.qnssl.com/media/plat-act/f2912864/f2912864-4d94-43cd-a8bc-4d856cb1b0ec-1483585260.jpg"}]
     * main_bussiness : 房贷、中小企业贷
     * is_in_activity : true
     * is_618_activity : false
     * bid_type : 固收
     * name : 抱财网
     * activity_end_year : 0
     * url : http://www.baocai.com/
     * type : 上市系
     * activity_end_day : 0
     * interest_min : 7.5
     * activity :
     * tag_list : [{"is_show_dialog":false,"tag_name":"上市系","style":"blue","dialog_img":""}]
     * activity_end_month : 0
     * first_invest_redpacket_award_info : {"min_duration_days":0,"type":"","red_packet_amount":0,"is_show":false,"min_invest_amount":0,"img_url":""}
     * is_activity : false
     */

    private String interest_max;
    private boolean raise_interest_flag;
    private boolean is_intelligent;
    private boolean is_paipaidai_activity;
    private int activity_start_day;
    private String bonding_method;
    private String logo;
    private int id;
    private boolean is_login;
    private String location_city;
    private String registered_capital;
    private boolean is_bank_storage_tube;
    private boolean is_enjoy_bonus;
    private int activity_start_year;
    private String activity_first_invest_raise_interest;
    private String online_date;
    private boolean is_free_withdraw;
    private boolean is_can_auto_register;
    private boolean is_registered;
    private String raise_interest;
    private boolean is_checked;
    private boolean is_daily_bonus;
    private PlatformNamesBean platform_names;
    private boolean is_can_assign;
    private int activity_start_month;
    private String short_name;
    private boolean interest_manage_fee;
    private String activity_second_invest_raise_interest;
    private String activity_limit_amount_str;
    private String activity_dialog_str;
    private String background;
    private String main_bussiness;
    private boolean is_in_activity;
    private boolean is_618_activity;
    private String bid_type;
    private String name;
    private int activity_end_year;
    private String url;
    private String type;
    private int activity_end_day;
    private String interest_min;
    private String activity;
    private int activity_end_month;
    private FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info;
    private boolean is_activity;
    private List<TabListBean> tab_list;
    private List<PlatformActivityBean> platform_activity;
    private List<TagListBean> tag_list;

    public String getInterest_max() {
        return interest_max;
    }

    public void setInterest_max(String interest_max) {
        this.interest_max = interest_max;
    }

    public boolean isRaise_interest_flag() {
        return raise_interest_flag;
    }

    public void setRaise_interest_flag(boolean raise_interest_flag) {
        this.raise_interest_flag = raise_interest_flag;
    }

    public boolean isIs_intelligent() {
        return is_intelligent;
    }

    public void setIs_intelligent(boolean is_intelligent) {
        this.is_intelligent = is_intelligent;
    }

    public boolean isIs_paipaidai_activity() {
        return is_paipaidai_activity;
    }

    public void setIs_paipaidai_activity(boolean is_paipaidai_activity) {
        this.is_paipaidai_activity = is_paipaidai_activity;
    }

    public int getActivity_start_day() {
        return activity_start_day;
    }

    public void setActivity_start_day(int activity_start_day) {
        this.activity_start_day = activity_start_day;
    }

    public String getBonding_method() {
        return bonding_method;
    }

    public void setBonding_method(String bonding_method) {
        this.bonding_method = bonding_method;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_login() {
        return is_login;
    }

    public void setIs_login(boolean is_login) {
        this.is_login = is_login;
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

    public boolean isIs_bank_storage_tube() {
        return is_bank_storage_tube;
    }

    public void setIs_bank_storage_tube(boolean is_bank_storage_tube) {
        this.is_bank_storage_tube = is_bank_storage_tube;
    }

    public boolean isIs_enjoy_bonus() {
        return is_enjoy_bonus;
    }

    public void setIs_enjoy_bonus(boolean is_enjoy_bonus) {
        this.is_enjoy_bonus = is_enjoy_bonus;
    }

    public int getActivity_start_year() {
        return activity_start_year;
    }

    public void setActivity_start_year(int activity_start_year) {
        this.activity_start_year = activity_start_year;
    }

    public String getActivity_first_invest_raise_interest() {
        return activity_first_invest_raise_interest;
    }

    public void setActivity_first_invest_raise_interest(String activity_first_invest_raise_interest) {
        this.activity_first_invest_raise_interest = activity_first_invest_raise_interest;
    }

    public String getOnline_date() {
        return online_date;
    }

    public void setOnline_date(String online_date) {
        this.online_date = online_date;
    }

    public boolean isIs_free_withdraw() {
        return is_free_withdraw;
    }

    public void setIs_free_withdraw(boolean is_free_withdraw) {
        this.is_free_withdraw = is_free_withdraw;
    }

    public boolean isIs_can_auto_register() {
        return is_can_auto_register;
    }

    public void setIs_can_auto_register(boolean is_can_auto_register) {
        this.is_can_auto_register = is_can_auto_register;
    }

    public boolean isIs_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }

    public String getRaise_interest() {
        return raise_interest;
    }

    public void setRaise_interest(String raise_interest) {
        this.raise_interest = raise_interest;
    }

    public boolean isIs_checked() {
        return is_checked;
    }

    public void setIs_checked(boolean is_checked) {
        this.is_checked = is_checked;
    }

    public boolean isIs_daily_bonus() {
        return is_daily_bonus;
    }

    public void setIs_daily_bonus(boolean is_daily_bonus) {
        this.is_daily_bonus = is_daily_bonus;
    }

    public PlatformNamesBean getPlatform_names() {
        return platform_names;
    }

    public void setPlatform_names(PlatformNamesBean platform_names) {
        this.platform_names = platform_names;
    }

    public boolean isIs_can_assign() {
        return is_can_assign;
    }

    public void setIs_can_assign(boolean is_can_assign) {
        this.is_can_assign = is_can_assign;
    }

    public int getActivity_start_month() {
        return activity_start_month;
    }

    public void setActivity_start_month(int activity_start_month) {
        this.activity_start_month = activity_start_month;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public boolean isInterest_manage_fee() {
        return interest_manage_fee;
    }

    public void setInterest_manage_fee(boolean interest_manage_fee) {
        this.interest_manage_fee = interest_manage_fee;
    }

    public String getActivity_second_invest_raise_interest() {
        return activity_second_invest_raise_interest;
    }

    public void setActivity_second_invest_raise_interest(String activity_second_invest_raise_interest) {
        this.activity_second_invest_raise_interest = activity_second_invest_raise_interest;
    }

    public String getActivity_limit_amount_str() {
        return activity_limit_amount_str;
    }

    public void setActivity_limit_amount_str(String activity_limit_amount_str) {
        this.activity_limit_amount_str = activity_limit_amount_str;
    }

    public String getActivity_dialog_str() {
        return activity_dialog_str;
    }

    public void setActivity_dialog_str(String activity_dialog_str) {
        this.activity_dialog_str = activity_dialog_str;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getMain_bussiness() {
        return main_bussiness;
    }

    public void setMain_bussiness(String main_bussiness) {
        this.main_bussiness = main_bussiness;
    }

    public boolean isIs_in_activity() {
        return is_in_activity;
    }

    public void setIs_in_activity(boolean is_in_activity) {
        this.is_in_activity = is_in_activity;
    }

    public boolean isIs_618_activity() {
        return is_618_activity;
    }

    public void setIs_618_activity(boolean is_618_activity) {
        this.is_618_activity = is_618_activity;
    }

    public String getBid_type() {
        return bid_type;
    }

    public void setBid_type(String bid_type) {
        this.bid_type = bid_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActivity_end_year() {
        return activity_end_year;
    }

    public void setActivity_end_year(int activity_end_year) {
        this.activity_end_year = activity_end_year;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getActivity_end_day() {
        return activity_end_day;
    }

    public void setActivity_end_day(int activity_end_day) {
        this.activity_end_day = activity_end_day;
    }

    public String getInterest_min() {
        return interest_min;
    }

    public void setInterest_min(String interest_min) {
        this.interest_min = interest_min;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getActivity_end_month() {
        return activity_end_month;
    }

    public void setActivity_end_month(int activity_end_month) {
        this.activity_end_month = activity_end_month;
    }

    public FirstInvestRedpacketAwardInfoBean getFirst_invest_redpacket_award_info() {
        return first_invest_redpacket_award_info;
    }

    public void setFirst_invest_redpacket_award_info(FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info) {
        this.first_invest_redpacket_award_info = first_invest_redpacket_award_info;
    }

    public boolean isIs_activity() {
        return is_activity;
    }

    public void setIs_activity(boolean is_activity) {
        this.is_activity = is_activity;
    }

    public List<TabListBean> getTab_list() {
        return tab_list;
    }

    public void setTab_list(List<TabListBean> tab_list) {
        this.tab_list = tab_list;
    }

    public List<PlatformActivityBean> getPlatform_activity() {
        return platform_activity;
    }

    public void setPlatform_activity(List<PlatformActivityBean> platform_activity) {
        this.platform_activity = platform_activity;
    }

    public List<TagListBean> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagListBean> tag_list) {
        this.tag_list = tag_list;
    }

    public static class PlatformNamesBean implements Serializable{
    }

    public static class FirstInvestRedpacketAwardInfoBean implements Serializable{
        /**
         * min_duration_days : 0
         * type :
         * red_packet_amount : 0
         * is_show : false
         * min_invest_amount : 0
         * img_url :
         */

        private String min_duration_days;
        private String type;
        private String red_packet_amount;
        private boolean is_show;
        private String min_invest_amount;
        private String img_url;

        public String getMin_duration_days() {
            return min_duration_days;
        }

        public void setMin_duration_days(String min_duration_days) {
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

    public static class TabListBean implements Serializable{
        /**
         * tab_class : 1
         * title : 热门活动
         */

        private int tab_class;
        private String title;

        public int getTab_class() {
            return tab_class;
        }

        public void setTab_class(int tab_class) {
            this.tab_class = tab_class;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class PlatformActivityBean implements Serializable{
        /**
         * content : 洪荒之“利”爆发 各式红包等你拿
         * success : true
         * title : 红包活动
         * activity_logo_url : https://o0s106hgi.qnssl.com/wap/platform_detail/activity_logo/red_packet_new.png
         * shrot_name : baocai
         * list_url : /products/
         * img_url : https://o0s106hgi.qnssl.com/media/plat-act/f2912864/f2912864-4d94-43cd-a8bc-4d856cb1b0ec-1483585260.jpg
         */

        private String content;
        private boolean success;
        private String title;
        private String activity_logo_url;
        private String shrot_name;
        private String list_url;
        private String img_url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActivity_logo_url() {
            return activity_logo_url;
        }

        public void setActivity_logo_url(String activity_logo_url) {
            this.activity_logo_url = activity_logo_url;
        }

        public String getShrot_name() {
            return shrot_name;
        }

        public void setShrot_name(String shrot_name) {
            this.shrot_name = shrot_name;
        }

        public String getList_url() {
            return list_url;
        }

        public void setList_url(String list_url) {
            this.list_url = list_url;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }

    public static class TagListBean implements Serializable{
        @Override
        public String toString() {
            return "TagListBean{" +
                    "is_show_dialog=" + is_show_dialog +
                    ", tag_name='" + tag_name + '\'' +
                    ", style='" + style + '\'' +
                    ", dialog_img='" + dialog_img + '\'' +
                    '}';
        }

        /**
         * is_show_dialog : false
         * tag_name : 上市系
         * style : blue
         * dialog_img :
         */

        private boolean is_show_dialog;
        private String tag_name;
        private String style;
        private String dialog_img;

        public boolean isIs_show_dialog() {
            return is_show_dialog;
        }

        public void setIs_show_dialog(boolean is_show_dialog) {
            this.is_show_dialog = is_show_dialog;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getDialog_img() {
            return dialog_img;
        }

        public void setDialog_img(String dialog_img) {
            this.dialog_img = dialog_img;
        }
    }
}
