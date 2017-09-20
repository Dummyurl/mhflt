package com.changju.fanlitou.bean.homeclassify;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/6/21.
 */

public class HomeFixed implements Serializable{


    /**
     * highest_rate_fix_bid : {"raise_interest_flag":true,"bid_progress_percent":100,"activity_start_day":21,"is_assign_bid":false,"is_promotion":false,"is_activity_bid":true,"remain_amount":"0元","duration_days":720,"original_bonus_info":{"is_show":true,"original_bonus_value":"1.0"},"pay_type":"按月等额本息","max_interest":"0.00","is_paipaidai_activity_bid":false,"short_name":"yironghengxi","is_618_activity_bid":false,"introduction":"\n                <table class=\"bid-introduction\">\n                                <tr>\n                                    <td class=\"name\">起投金额<\/td>\n                                    <td class=\"val\">50 元<\/td>\n                                <\/tr>\n                                <tr>\n                                    <td class=\"name\">起息日<\/td>\n                                    <td class=\"val\">满标后起息<\/td>\n                                <\/tr>\n                                <tr>\n                                    <td class=\"name\">返利日期<\/td>\n                                    <td class=\"val\">按日返利<\/td>\n                                <\/tr>\n                                <tr>\n                                    <td class=\"name\">还款方式<\/td>\n                                    <td class=\"val\">按月等额本息<\/td>\n                                <\/tr>\n                                <tr>\n                                    <td class=\"name\">债权转让<\/td>\n                                    <td class=\"val\">可以转让<\/td>\n                                <\/tr>\n                                <tr>\n                                    <td class=\"name\">利息管理费<\/td>\n                                    <td class=\"val\">按用户等级收取利息的一定比例<\/td>\n                                <\/tr>\n                            <\/table>\n                ","min_interest":"0.00","activity_first_invest_raise_interest":"2","platform":{"platform_duration_min":"1","platform_name":"易融恒信","platform_interest_min":"10.00","platform_short_name":"yironghengxi","platform_registered_capital":"5,000","is_intelligent":false,"platform_duration_min_unit":"个月","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/9fdfb7c3/app-9fdfb7c3-3c05-4e69-82d3-f2e87bd19240-1490940758.png","platform_duration_max_unit":"个月","platform_bank_interface_type":"other","platform_id":74,"platform_duration_max":"3","platform_logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/851696a5/app-851696a5-072f-445a-ba69-2c251c371514-1489898517.png","platform_logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/1f5d3fbc/thumb-1f5d3fbc-19a9-44dd-a730-98521701cd9d-1489898517.png","platform_can_auto_register":true,"platform_background":"武汉易融恒信金融信息服务有限公司","platform_interest_max":"17.00","first_invest_redpacket_award_info":{"min_duration_days":30,"type":"first","red_packet_amount":"40.00","is_show":true,"min_invest_amount":"5,000.00","img_url":"https://o0s106hgi.qnssl.com/billion/img/billion_first.png"},"platform_bonus_interest":"1.0"},"raise_interest":"2.0","promotion_flag":false,"new_user_invest_range":"","is_daily_bonus":true,"first_invest_raise_interest_info":{"first_invest_bonus_interest":0,"is_show":false},"is_lockin_bid":false,"activity_start_month":8,"bid_name":"稳定投141653","bid_interest":"18.60","special_limit_title":"","bid_status":1,"platform_names":{},"bid_id":363292,"activity_second_invest_raise_interest":"2","activity_dialog_str":"首投+2%，复投+2%，无限额；9.3结束","min_invest_amount":"50元","is_special_limit":false,"activity_end_year":2017,"activity_limit_amount_str":"无限额","bid_type":1,"total_amount":"15万","mini_program_introduction":{"pay_type":"按月等额本息","interest_manage_fee":"按用户等级收取利息的一定比例","assign_info":"可以转让","min_invest_amount":"50元","interest_start_date":"满标后起息","bonus_date":"按日返利"},"extra_interest":"4.0","is_weidai_bonus_activity":false,"special_limit_content":"","bid_duration":"24个月","new_user_invest_count":"投资次数：最多1次","activity_start_year":2017,"tag_list":[{"content_list":[],"name":"活动标","title":"","dialog_img":"https://o0s106hgi.qnssl.com/bid_detail/tag/bid_detail_tag_test.png","is_show_dialog":true,"type":"img"}],"activity_end_month":9,"activity_end_day":3,"first_invest_redpacket_award_info":{"red_packet_amount":40,"is_show":true,"min_duration_days":30,"min_invest_amount":5000},"iphone_flag":false}
     * filter_list : [{"multiple_choice":true,"content_list":[{"value":"","name":"不限","key":"platform_short_name"},{"value":"guangxindai","name":"广信贷","key":"platform_short_name"},{"value":"iqianbang","name":"爱钱帮","key":"platform_short_name"},{"value":"baocai","name":"抱财网","key":"platform_short_name"},{"value":"hepandai","name":"合盘贷","key":"platform_short_name"},{"value":"xinhehui","name":"鑫合汇","key":"platform_short_name"},{"value":"lvjinsuo","name":"律金金融","key":"platform_short_name"},{"value":"eastlending","name":"东方汇","key":"platform_short_name"},{"value":"jimu","name":"积木盒子","key":"platform_short_name"},{"value":"yyfax","name":"友金所","key":"platform_short_name"},{"value":"tuandai","name":"团贷网","key":"platform_short_name"},{"value":"rjs","name":"融金所","key":"platform_short_name"},{"value":"bestdai","name":"百思贷","key":"platform_short_name"},{"value":"rqbao","name":"瑞钱宝","key":"platform_short_name"},{"value":"junrongdai","name":"君融贷","key":"platform_short_name"},{"value":"xeenho","name":"星火钱包","key":"platform_short_name"},{"value":"beesbank","name":"蜜蜂有钱","key":"platform_short_name"},{"value":"hexindai","name":"和信贷","key":"platform_short_name"},{"value":"guojinbao","name":"国金宝","key":"platform_short_name"},{"value":"jiurong","name":"玖融网","key":"platform_short_name"},{"value":"chaincar","name":"链车金服","key":"platform_short_name"},{"value":"touna","name":"投哪网","key":"platform_short_name"},{"value":"ppdai","name":"拍拍贷","key":"platform_short_name"},{"value":"hepai","name":"合拍在线","key":"platform_short_name"},{"value":"91tianmi","name":"添米财富","key":"platform_short_name"},{"value":"dingdingjinf","name":"丁丁金服","key":"platform_short_name"},{"value":"weidai","name":"微贷网","key":"platform_short_name"},{"value":"madailicai","name":"麻袋理财","key":"platform_short_name"},{"value":"yumilc","name":"玉米理财","key":"platform_short_name"},{"value":"yirendai","name":"宜人贷","key":"platform_short_name"},{"value":"niwodai","name":"你我贷","key":"platform_short_name"},{"value":"yironghengxi","name":"易融恒信","key":"platform_short_name"},{"value":"jintoushou","name":"金投手","key":"platform_short_name"},{"value":"fengjr","name":"凤凰金融","key":"platform_short_name"},{"value":"gaosouyi","name":"高搜易","key":"platform_short_name"},{"value":"yidai","name":"宜贷网","key":"platform_short_name"},{"value":"bundwealth","name":"外滩云财富","key":"platform_short_name"},{"value":"myerong","name":"e融所","key":"platform_short_name"},{"value":"uf-club","name":"联金所","key":"platform_short_name"},{"value":"yuanbao365","name":"元宝365","key":"platform_short_name"}],"is_title_red":false,"title":"来自平台"},{"multiple_choice":false,"content_list":[{"value":0,"name":"不限","key":"hot_recommend"},{"value":1,"name":"活动","key":"hot_recommend"}],"is_title_red":true,"title":"热门推荐"},{"multiple_choice":false,"content_list":[{"value":0,"name":"不限","key":"interest"},{"value":1,"name":"12%以上","key":"interest"},{"value":2,"name":"8%～12%","key":"interest"},{"value":3,"name":"8%以下","key":"interest"}],"is_title_red":false,"title":"年化收益"},{"multiple_choice":false,"content_list":[{"value":0,"name":"不限","key":"duration"},{"value":1,"name":"3个月以内","key":"duration"},{"value":2,"name":"3～6个月","key":"duration"},{"value":3,"name":"6～12个月","key":"duration"},{"value":4,"name":"12个月以上","key":"duration"}],"is_title_red":false,"title":"投资期限"},{"multiple_choice":false,"content_list":[{"value":0,"name":"不限","key":"platform_bg_type"},{"value":1,"name":"上市系","key":"platform_bg_type"},{"value":2,"name":"风投系","key":"platform_bg_type"},{"value":3,"name":"国资系","key":"platform_bg_type"},{"value":4,"name":"民营系","key":"platform_bg_type"}],"is_title_red":false,"title":"平台背景"}]
     * newest_fix_platform : {"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/cb8dad7e/app-cb8dad7e-8ab3-44b8-b46a-91ef49458eda-1492743780.png","short_name":"jintoushou","name":"金投手","average_interest":8.5,"url":"http://www.jintoushou.com","background":"中建国能建设集团国资入股","id":75,"logo_app_operation":"https://o0s106hgi.qnssl.com/media/plat-logo/9f149286/app-9f149286-6d7a-4f75-93e6-612e6dab5386-1490607765.png"}
     */

    private HighestRateFixBidBean highest_rate_fix_bid;
    private NewestFixPlatformBean newest_fix_platform;
    private List<FilterListBean> filter_list;

    public HighestRateFixBidBean getHighest_rate_fix_bid() {
        return highest_rate_fix_bid;
    }

    public void setHighest_rate_fix_bid(HighestRateFixBidBean highest_rate_fix_bid) {
        this.highest_rate_fix_bid = highest_rate_fix_bid;
    }

    public NewestFixPlatformBean getNewest_fix_platform() {
        return newest_fix_platform;
    }

    public void setNewest_fix_platform(NewestFixPlatformBean newest_fix_platform) {
        this.newest_fix_platform = newest_fix_platform;
    }

    public List<FilterListBean> getFilter_list() {
        return filter_list;
    }

    public void setFilter_list(List<FilterListBean> filter_list) {
        this.filter_list = filter_list;
    }

    public static class HighestRateFixBidBean {
        /**
         * raise_interest_flag : true
         * bid_progress_percent : 100
         * activity_start_day : 21
         * is_assign_bid : false
         * is_promotion : false
         * is_activity_bid : true
         * remain_amount : 0元
         * duration_days : 720
         * original_bonus_info : {"is_show":true,"original_bonus_value":"1.0"}
         * pay_type : 按月等额本息
         * max_interest : 0.00
         * is_paipaidai_activity_bid : false
         * short_name : yironghengxi
         * is_618_activity_bid : false
         * introduction :
         <table class="bid-introduction">
         <tr>
         <td class="name">起投金额</td>
         <td class="val">50 元</td>
         </tr>
         <tr>
         <td class="name">起息日</td>
         <td class="val">满标后起息</td>
         </tr>
         <tr>
         <td class="name">返利日期</td>
         <td class="val">按日返利</td>
         </tr>
         <tr>
         <td class="name">还款方式</td>
         <td class="val">按月等额本息</td>
         </tr>
         <tr>
         <td class="name">债权转让</td>
         <td class="val">可以转让</td>
         </tr>
         <tr>
         <td class="name">利息管理费</td>
         <td class="val">按用户等级收取利息的一定比例</td>
         </tr>
         </table>

         * min_interest : 0.00
         * activity_first_invest_raise_interest : 2
         * platform : {"platform_duration_min":"1","platform_name":"易融恒信","platform_interest_min":"10.00","platform_short_name":"yironghengxi","platform_registered_capital":"5,000","is_intelligent":false,"platform_duration_min_unit":"个月","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/9fdfb7c3/app-9fdfb7c3-3c05-4e69-82d3-f2e87bd19240-1490940758.png","platform_duration_max_unit":"个月","platform_bank_interface_type":"other","platform_id":74,"platform_duration_max":"3","platform_logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/851696a5/app-851696a5-072f-445a-ba69-2c251c371514-1489898517.png","platform_logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/1f5d3fbc/thumb-1f5d3fbc-19a9-44dd-a730-98521701cd9d-1489898517.png","platform_can_auto_register":true,"platform_background":"武汉易融恒信金融信息服务有限公司","platform_interest_max":"17.00","first_invest_redpacket_award_info":{"min_duration_days":30,"type":"first","red_packet_amount":"40.00","is_show":true,"min_invest_amount":"5,000.00","img_url":"https://o0s106hgi.qnssl.com/billion/img/billion_first.png"},"platform_bonus_interest":"1.0"}
         * raise_interest : 2.0
         * promotion_flag : false
         * new_user_invest_range :
         * is_daily_bonus : true
         * first_invest_raise_interest_info : {"first_invest_bonus_interest":0,"is_show":false}
         * is_lockin_bid : false
         * activity_start_month : 8
         * bid_name : 稳定投141653
         * bid_interest : 18.60
         * special_limit_title :
         * bid_status : 1
         * platform_names : {}
         * bid_id : 363292
         * activity_second_invest_raise_interest : 2
         * activity_dialog_str : 首投+2%，复投+2%，无限额；9.3结束
         * min_invest_amount : 50元
         * is_special_limit : false
         * activity_end_year : 2017
         * activity_limit_amount_str : 无限额
         * bid_type : 1
         * total_amount : 15万
         * mini_program_introduction : {"pay_type":"按月等额本息","interest_manage_fee":"按用户等级收取利息的一定比例","assign_info":"可以转让","min_invest_amount":"50元","interest_start_date":"满标后起息","bonus_date":"按日返利"}
         * extra_interest : 4.0
         * is_weidai_bonus_activity : false
         * special_limit_content :
         * bid_duration : 24个月
         * new_user_invest_count : 投资次数：最多1次
         * activity_start_year : 2017
         * tag_list : [{"content_list":[],"name":"活动标","title":"","dialog_img":"https://o0s106hgi.qnssl.com/bid_detail/tag/bid_detail_tag_test.png","is_show_dialog":true,"type":"img"}]
         * activity_end_month : 9
         * activity_end_day : 3
         * first_invest_redpacket_award_info : {"red_packet_amount":40,"is_show":true,"min_duration_days":30,"min_invest_amount":5000}
         * iphone_flag : false
         */

        private boolean raise_interest_flag;
        private String bid_progress_percent;
        private String activity_start_day;
        private boolean is_assign_bid;
        private boolean is_promotion;
        private boolean is_activity_bid;
        private String remain_amount;
        private String duration_days;
        private OriginalBonusInfoBean original_bonus_info;
        private String pay_type;
        private String max_interest;
        private boolean is_paipaidai_activity_bid;
        private String short_name;
        private boolean is_618_activity_bid;
        private String introduction;
        private String min_interest;
        private String activity_first_invest_raise_interest;
        private PlatformBean platform;
        private String raise_interest;
        private boolean promotion_flag;
        private String new_user_invest_range;
        private boolean is_daily_bonus;
        private FirstInvestRaiseInterestInfoBean first_invest_raise_interest_info;
        private boolean is_lockin_bid;
        private String activity_start_month;
        private String bid_name;
        private String bid_interest;
        private String special_limit_title;
        private int bid_status;
        private PlatformNamesBean platform_names;
        private int bid_id;
        private String activity_second_invest_raise_interest;
        private String activity_dialog_str;
        private String min_invest_amount;
        private boolean is_special_limit;
        private String activity_end_year;
        private String activity_limit_amount_str;
        private int bid_type;
        private String total_amount;
        private MiniProgramIntroductionBean mini_program_introduction;
        private String extra_interest;
        private boolean is_weidai_bonus_activity;
        private String special_limit_content;
        private String bid_duration;
        private String new_user_invest_count;
        private String activity_start_year;
        private String activity_end_month;
        private String activity_end_day;
        private FirstInvestRedpacketAwardInfoBeanX first_invest_redpacket_award_info;
        private boolean iphone_flag;
        private List<TagListBean> tag_list;

        public boolean isRaise_interest_flag() {
            return raise_interest_flag;
        }

        public void setRaise_interest_flag(boolean raise_interest_flag) {
            this.raise_interest_flag = raise_interest_flag;
        }

        public String getBid_progress_percent() {
            return bid_progress_percent;
        }

        public void setBid_progress_percent(String bid_progress_percent) {
            this.bid_progress_percent = bid_progress_percent;
        }

        public String getActivity_start_day() {
            return activity_start_day;
        }

        public void setActivity_start_day(String activity_start_day) {
            this.activity_start_day = activity_start_day;
        }

        public boolean isIs_assign_bid() {
            return is_assign_bid;
        }

        public void setIs_assign_bid(boolean is_assign_bid) {
            this.is_assign_bid = is_assign_bid;
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

        public String getRemain_amount() {
            return remain_amount;
        }

        public void setRemain_amount(String remain_amount) {
            this.remain_amount = remain_amount;
        }

        public String getDuration_days() {
            return duration_days;
        }

        public void setDuration_days(String duration_days) {
            this.duration_days = duration_days;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getMax_interest() {
            return max_interest;
        }

        public void setMax_interest(String max_interest) {
            this.max_interest = max_interest;
        }

        public boolean isIs_paipaidai_activity_bid() {
            return is_paipaidai_activity_bid;
        }

        public void setIs_paipaidai_activity_bid(boolean is_paipaidai_activity_bid) {
            this.is_paipaidai_activity_bid = is_paipaidai_activity_bid;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public boolean isIs_618_activity_bid() {
            return is_618_activity_bid;
        }

        public void setIs_618_activity_bid(boolean is_618_activity_bid) {
            this.is_618_activity_bid = is_618_activity_bid;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getMin_interest() {
            return min_interest;
        }

        public void setMin_interest(String min_interest) {
            this.min_interest = min_interest;
        }

        public String getActivity_first_invest_raise_interest() {
            return activity_first_invest_raise_interest;
        }

        public void setActivity_first_invest_raise_interest(String activity_first_invest_raise_interest) {
            this.activity_first_invest_raise_interest = activity_first_invest_raise_interest;
        }

        public PlatformBean getPlatform() {
            return platform;
        }

        public void setPlatform(PlatformBean platform) {
            this.platform = platform;
        }

        public String getRaise_interest() {
            return raise_interest;
        }

        public void setRaise_interest(String raise_interest) {
            this.raise_interest = raise_interest;
        }

        public boolean isPromotion_flag() {
            return promotion_flag;
        }

        public void setPromotion_flag(boolean promotion_flag) {
            this.promotion_flag = promotion_flag;
        }

        public String getNew_user_invest_range() {
            return new_user_invest_range;
        }

        public void setNew_user_invest_range(String new_user_invest_range) {
            this.new_user_invest_range = new_user_invest_range;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public FirstInvestRaiseInterestInfoBean getFirst_invest_raise_interest_info() {
            return first_invest_raise_interest_info;
        }

        public void setFirst_invest_raise_interest_info(FirstInvestRaiseInterestInfoBean first_invest_raise_interest_info) {
            this.first_invest_raise_interest_info = first_invest_raise_interest_info;
        }

        public boolean isIs_lockin_bid() {
            return is_lockin_bid;
        }

        public void setIs_lockin_bid(boolean is_lockin_bid) {
            this.is_lockin_bid = is_lockin_bid;
        }

        public String getActivity_start_month() {
            return activity_start_month;
        }

        public void setActivity_start_month(String activity_start_month) {
            this.activity_start_month = activity_start_month;
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

        public String getSpecial_limit_title() {
            return special_limit_title;
        }

        public void setSpecial_limit_title(String special_limit_title) {
            this.special_limit_title = special_limit_title;
        }

        public int getBid_status() {
            return bid_status;
        }

        public void setBid_status(int bid_status) {
            this.bid_status = bid_status;
        }

        public PlatformNamesBean getPlatform_names() {
            return platform_names;
        }

        public void setPlatform_names(PlatformNamesBean platform_names) {
            this.platform_names = platform_names;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getActivity_second_invest_raise_interest() {
            return activity_second_invest_raise_interest;
        }

        public void setActivity_second_invest_raise_interest(String activity_second_invest_raise_interest) {
            this.activity_second_invest_raise_interest = activity_second_invest_raise_interest;
        }

        public String getActivity_dialog_str() {
            return activity_dialog_str;
        }

        public void setActivity_dialog_str(String activity_dialog_str) {
            this.activity_dialog_str = activity_dialog_str;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public boolean isIs_special_limit() {
            return is_special_limit;
        }

        public void setIs_special_limit(boolean is_special_limit) {
            this.is_special_limit = is_special_limit;
        }

        public String getActivity_end_year() {
            return activity_end_year;
        }

        public void setActivity_end_year(String activity_end_year) {
            this.activity_end_year = activity_end_year;
        }

        public String getActivity_limit_amount_str() {
            return activity_limit_amount_str;
        }

        public void setActivity_limit_amount_str(String activity_limit_amount_str) {
            this.activity_limit_amount_str = activity_limit_amount_str;
        }

        public int getBid_type() {
            return bid_type;
        }

        public void setBid_type(int bid_type) {
            this.bid_type = bid_type;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public MiniProgramIntroductionBean getMini_program_introduction() {
            return mini_program_introduction;
        }

        public void setMini_program_introduction(MiniProgramIntroductionBean mini_program_introduction) {
            this.mini_program_introduction = mini_program_introduction;
        }

        public String getExtra_interest() {
            return extra_interest;
        }

        public void setExtra_interest(String extra_interest) {
            this.extra_interest = extra_interest;
        }

        public boolean isIs_weidai_bonus_activity() {
            return is_weidai_bonus_activity;
        }

        public void setIs_weidai_bonus_activity(boolean is_weidai_bonus_activity) {
            this.is_weidai_bonus_activity = is_weidai_bonus_activity;
        }

        public String getSpecial_limit_content() {
            return special_limit_content;
        }

        public void setSpecial_limit_content(String special_limit_content) {
            this.special_limit_content = special_limit_content;
        }

        public String getBid_duration() {
            return bid_duration;
        }

        public void setBid_duration(String bid_duration) {
            this.bid_duration = bid_duration;
        }

        public String getNew_user_invest_count() {
            return new_user_invest_count;
        }

        public void setNew_user_invest_count(String new_user_invest_count) {
            this.new_user_invest_count = new_user_invest_count;
        }

        public String getActivity_start_year() {
            return activity_start_year;
        }

        public void setActivity_start_year(String activity_start_year) {
            this.activity_start_year = activity_start_year;
        }

        public String getActivity_end_month() {
            return activity_end_month;
        }

        public void setActivity_end_month(String activity_end_month) {
            this.activity_end_month = activity_end_month;
        }

        public String getActivity_end_day() {
            return activity_end_day;
        }

        public void setActivity_end_day(String activity_end_day) {
            this.activity_end_day = activity_end_day;
        }

        public FirstInvestRedpacketAwardInfoBeanX getFirst_invest_redpacket_award_info() {
            return first_invest_redpacket_award_info;
        }

        public void setFirst_invest_redpacket_award_info(FirstInvestRedpacketAwardInfoBeanX first_invest_redpacket_award_info) {
            this.first_invest_redpacket_award_info = first_invest_redpacket_award_info;
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
             * is_show : true
             * original_bonus_value : 1.0
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
             * platform_duration_min : 1
             * platform_name : 易融恒信
             * platform_interest_min : 10.00
             * platform_short_name : yironghengxi
             * platform_registered_capital : 5,000
             * is_intelligent : false
             * platform_duration_min_unit : 个月
             * platform_logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/9fdfb7c3/app-9fdfb7c3-3c05-4e69-82d3-f2e87bd19240-1490940758.png
             * platform_duration_max_unit : 个月
             * platform_bank_interface_type : other
             * platform_id : 74
             * platform_duration_max : 3
             * platform_logo_app_square : https://o0s106hgi.qnssl.com/media/plat-logo/851696a5/app-851696a5-072f-445a-ba69-2c251c371514-1489898517.png
             * platform_logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/1f5d3fbc/thumb-1f5d3fbc-19a9-44dd-a730-98521701cd9d-1489898517.png
             * platform_can_auto_register : true
             * platform_background : 武汉易融恒信金融信息服务有限公司
             * platform_interest_max : 17.00
             * first_invest_redpacket_award_info : {"min_duration_days":30,"type":"first","red_packet_amount":"40.00","is_show":true,"min_invest_amount":"5,000.00","img_url":"https://o0s106hgi.qnssl.com/billion/img/billion_first.png"}
             * platform_bonus_interest : 1.0
             */

            private String platform_duration_min;
            private String platform_name;
            private String platform_interest_min;
            private String platform_short_name;
            private String platform_registered_capital;
            private boolean is_intelligent;
            private String platform_duration_min_unit;
            private String platform_logo_app;
            private String platform_duration_max_unit;
            private String platform_bank_interface_type;
            private int platform_id;
            private String platform_duration_max;
            private String platform_logo_app_square;
            private String platform_logo_thumbnail;
            private boolean platform_can_auto_register;
            private String platform_background;
            private String platform_interest_max;
            private FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info;
            private String platform_bonus_interest;

            public String getPlatform_duration_min() {
                return platform_duration_min;
            }

            public void setPlatform_duration_min(String platform_duration_min) {
                this.platform_duration_min = platform_duration_min;
            }

            public String getPlatform_name() {
                return platform_name;
            }

            public void setPlatform_name(String platform_name) {
                this.platform_name = platform_name;
            }

            public String getPlatform_interest_min() {
                return platform_interest_min;
            }

            public void setPlatform_interest_min(String platform_interest_min) {
                this.platform_interest_min = platform_interest_min;
            }

            public String getPlatform_short_name() {
                return platform_short_name;
            }

            public void setPlatform_short_name(String platform_short_name) {
                this.platform_short_name = platform_short_name;
            }

            public String getPlatform_registered_capital() {
                return platform_registered_capital;
            }

            public void setPlatform_registered_capital(String platform_registered_capital) {
                this.platform_registered_capital = platform_registered_capital;
            }

            public boolean isIs_intelligent() {
                return is_intelligent;
            }

            public void setIs_intelligent(boolean is_intelligent) {
                this.is_intelligent = is_intelligent;
            }

            public String getPlatform_duration_min_unit() {
                return platform_duration_min_unit;
            }

            public void setPlatform_duration_min_unit(String platform_duration_min_unit) {
                this.platform_duration_min_unit = platform_duration_min_unit;
            }

            public String getPlatform_logo_app() {
                return platform_logo_app;
            }

            public void setPlatform_logo_app(String platform_logo_app) {
                this.platform_logo_app = platform_logo_app;
            }

            public String getPlatform_duration_max_unit() {
                return platform_duration_max_unit;
            }

            public void setPlatform_duration_max_unit(String platform_duration_max_unit) {
                this.platform_duration_max_unit = platform_duration_max_unit;
            }

            public String getPlatform_bank_interface_type() {
                return platform_bank_interface_type;
            }

            public void setPlatform_bank_interface_type(String platform_bank_interface_type) {
                this.platform_bank_interface_type = platform_bank_interface_type;
            }

            public int getPlatform_id() {
                return platform_id;
            }

            public void setPlatform_id(int platform_id) {
                this.platform_id = platform_id;
            }

            public String getPlatform_duration_max() {
                return platform_duration_max;
            }

            public void setPlatform_duration_max(String platform_duration_max) {
                this.platform_duration_max = platform_duration_max;
            }

            public String getPlatform_logo_app_square() {
                return platform_logo_app_square;
            }

            public void setPlatform_logo_app_square(String platform_logo_app_square) {
                this.platform_logo_app_square = platform_logo_app_square;
            }

            public String getPlatform_logo_thumbnail() {
                return platform_logo_thumbnail;
            }

            public void setPlatform_logo_thumbnail(String platform_logo_thumbnail) {
                this.platform_logo_thumbnail = platform_logo_thumbnail;
            }

            public boolean isPlatform_can_auto_register() {
                return platform_can_auto_register;
            }

            public void setPlatform_can_auto_register(boolean platform_can_auto_register) {
                this.platform_can_auto_register = platform_can_auto_register;
            }

            public String getPlatform_background() {
                return platform_background;
            }

            public void setPlatform_background(String platform_background) {
                this.platform_background = platform_background;
            }

            public String getPlatform_interest_max() {
                return platform_interest_max;
            }

            public void setPlatform_interest_max(String platform_interest_max) {
                this.platform_interest_max = platform_interest_max;
            }

            public FirstInvestRedpacketAwardInfoBean getFirst_invest_redpacket_award_info() {
                return first_invest_redpacket_award_info;
            }

            public void setFirst_invest_redpacket_award_info(FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info) {
                this.first_invest_redpacket_award_info = first_invest_redpacket_award_info;
            }

            public String getPlatform_bonus_interest() {
                return platform_bonus_interest;
            }

            public void setPlatform_bonus_interest(String platform_bonus_interest) {
                this.platform_bonus_interest = platform_bonus_interest;
            }

            public static class FirstInvestRedpacketAwardInfoBean {
                /**
                 * min_duration_days : 30
                 * type : first
                 * red_packet_amount : 40.00
                 * is_show : true
                 * min_invest_amount : 5,000.00
                 * img_url : https://o0s106hgi.qnssl.com/billion/img/billion_first.png
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
        }

        public static class FirstInvestRaiseInterestInfoBean {
        }

        public static class PlatformNamesBean {
        }

        public static class MiniProgramIntroductionBean {
            /**
             * pay_type : 按月等额本息
             * interest_manage_fee : 按用户等级收取利息的一定比例
             * assign_info : 可以转让
             * min_invest_amount : 50元
             * interest_start_date : 满标后起息
             * bonus_date : 按日返利
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

        public static class FirstInvestRedpacketAwardInfoBeanX {
            /**
             * red_packet_amount : 40
             * is_show : true
             * min_duration_days : 30
             * min_invest_amount : 5000
             */

            private String red_packet_amount;
            private boolean is_show;
            private String min_duration_days;
            private String min_invest_amount;

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

            public String getMin_duration_days() {
                return min_duration_days;
            }

            public void setMin_duration_days(String min_duration_days) {
                this.min_duration_days = min_duration_days;
            }

            public String getMin_invest_amount() {
                return min_invest_amount;
            }

            public void setMin_invest_amount(String min_invest_amount) {
                this.min_invest_amount = min_invest_amount;
            }
        }

        public static class TagListBean {
            /**
             * content_list : []
             * name : 活动标
             * title :
             * dialog_img : https://o0s106hgi.qnssl.com/bid_detail/tag/bid_detail_tag_test.png
             * is_show_dialog : true
             * type : img
             */

            private String name;
            private String title;
            private String dialog_img;
            private boolean is_show_dialog;
            private String type;
            private List<?> content_list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDialog_img() {
                return dialog_img;
            }

            public void setDialog_img(String dialog_img) {
                this.dialog_img = dialog_img;
            }

            public boolean isIs_show_dialog() {
                return is_show_dialog;
            }

            public void setIs_show_dialog(boolean is_show_dialog) {
                this.is_show_dialog = is_show_dialog;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<?> getContent_list() {
                return content_list;
            }

            public void setContent_list(List<?> content_list) {
                this.content_list = content_list;
            }
        }
    }

    public static class NewestFixPlatformBean {
        /**
         * logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/cb8dad7e/app-cb8dad7e-8ab3-44b8-b46a-91ef49458eda-1492743780.png
         * short_name : jintoushou
         * name : 金投手
         * average_interest : 8.5
         * url : http://www.jintoushou.com
         * background : 中建国能建设集团国资入股
         * id : 75
         * logo_app_operation : https://o0s106hgi.qnssl.com/media/plat-logo/9f149286/app-9f149286-6d7a-4f75-93e6-612e6dab5386-1490607765.png
         */

        private String logo_thumbnail;
        private String short_name;
        private String name;
        private double average_interest;
        private String url;
        private String background;
        private int id;
        private String logo_app_operation;

        public String getLogo_thumbnail() {
            return logo_thumbnail;
        }

        public void setLogo_thumbnail(String logo_thumbnail) {
            this.logo_thumbnail = logo_thumbnail;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getAverage_interest() {
            return average_interest;
        }

        public void setAverage_interest(double average_interest) {
            this.average_interest = average_interest;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo_app_operation() {
            return logo_app_operation;
        }

        public void setLogo_app_operation(String logo_app_operation) {
            this.logo_app_operation = logo_app_operation;
        }
    }

    public static class FilterListBean implements Serializable{
        /**
         * multiple_choice : true
         * content_list : [{"value":"","name":"不限","key":"platform_short_name"},{"value":"guangxindai","name":"广信贷","key":"platform_short_name"},{"value":"iqianbang","name":"爱钱帮","key":"platform_short_name"},{"value":"baocai","name":"抱财网","key":"platform_short_name"},{"value":"hepandai","name":"合盘贷","key":"platform_short_name"},{"value":"xinhehui","name":"鑫合汇","key":"platform_short_name"},{"value":"lvjinsuo","name":"律金金融","key":"platform_short_name"},{"value":"eastlending","name":"东方汇","key":"platform_short_name"},{"value":"jimu","name":"积木盒子","key":"platform_short_name"},{"value":"yyfax","name":"友金所","key":"platform_short_name"},{"value":"tuandai","name":"团贷网","key":"platform_short_name"},{"value":"rjs","name":"融金所","key":"platform_short_name"},{"value":"bestdai","name":"百思贷","key":"platform_short_name"},{"value":"rqbao","name":"瑞钱宝","key":"platform_short_name"},{"value":"junrongdai","name":"君融贷","key":"platform_short_name"},{"value":"xeenho","name":"星火钱包","key":"platform_short_name"},{"value":"beesbank","name":"蜜蜂有钱","key":"platform_short_name"},{"value":"hexindai","name":"和信贷","key":"platform_short_name"},{"value":"guojinbao","name":"国金宝","key":"platform_short_name"},{"value":"jiurong","name":"玖融网","key":"platform_short_name"},{"value":"chaincar","name":"链车金服","key":"platform_short_name"},{"value":"touna","name":"投哪网","key":"platform_short_name"},{"value":"ppdai","name":"拍拍贷","key":"platform_short_name"},{"value":"hepai","name":"合拍在线","key":"platform_short_name"},{"value":"91tianmi","name":"添米财富","key":"platform_short_name"},{"value":"dingdingjinf","name":"丁丁金服","key":"platform_short_name"},{"value":"weidai","name":"微贷网","key":"platform_short_name"},{"value":"madailicai","name":"麻袋理财","key":"platform_short_name"},{"value":"yumilc","name":"玉米理财","key":"platform_short_name"},{"value":"yirendai","name":"宜人贷","key":"platform_short_name"},{"value":"niwodai","name":"你我贷","key":"platform_short_name"},{"value":"yironghengxi","name":"易融恒信","key":"platform_short_name"},{"value":"jintoushou","name":"金投手","key":"platform_short_name"},{"value":"fengjr","name":"凤凰金融","key":"platform_short_name"},{"value":"gaosouyi","name":"高搜易","key":"platform_short_name"},{"value":"yidai","name":"宜贷网","key":"platform_short_name"},{"value":"bundwealth","name":"外滩云财富","key":"platform_short_name"},{"value":"myerong","name":"e融所","key":"platform_short_name"},{"value":"uf-club","name":"联金所","key":"platform_short_name"},{"value":"yuanbao365","name":"元宝365","key":"platform_short_name"}]
         * is_title_red : false
         * title : 来自平台
         */

        private boolean multiple_choice;
        private boolean is_title_red;
        private String title;
        private List<ContentListBean> content_list;

        public boolean isMultiple_choice() {
            return multiple_choice;
        }

        public void setMultiple_choice(boolean multiple_choice) {
            this.multiple_choice = multiple_choice;
        }

        public boolean isIs_title_red() {
            return is_title_red;
        }

        public void setIs_title_red(boolean is_title_red) {
            this.is_title_red = is_title_red;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ContentListBean> getContent_list() {
            return content_list;
        }

        public void setContent_list(List<ContentListBean> content_list) {
            this.content_list = content_list;
        }

        public static class ContentListBean implements Serializable{
            /**
             * value :
             * name : 不限
             * key : platform_short_name
             */

            private String value;
            private String name;
            private String key;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }
}
