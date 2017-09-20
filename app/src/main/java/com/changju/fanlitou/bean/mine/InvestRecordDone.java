package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/6/8.
 */

public class InvestRecordDone {

    /**
     * total_records : 61
     * invest_record : [{"bid_category":1,"is_show_raise_interest":true,"platform_short_name":"iqianbang","second_bonus_interest":3,"raise_interest_value":"7.32","duration":"17天","raise_interest_tickets":[],"principal":1178600,"platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/dce6e994/app-dce6e994-081c-49c6-8425-61b00c994b0d-1490928270.png","order_id":162,"bid_id":1,"first_bonus_interest":3,"interest":"0.00","is_daily_bonus":0,"status":3,"bid_name":"爱车贷-zjtest111002","order_time":"2017.11.23","bonus":"36.6","questionmark_tag":0,"bid_url":"/m/bid_detail/fix/247/","daily_bonus_questionmark_info":{"content_list":[],"is_show":false,"title":""},"interest_and_bonus_rate":"10%+3%","is_show_bonus_dialog":false,"raise_interest_name":"加息(元)","platform_name":"爱钱帮"}]
     */

    private int total_records;
    private List<InvestRecordBean> invest_record;

    public int getTotal_records() {
        return total_records;
    }

    public void setTotal_records(int total_records) {
        this.total_records = total_records;
    }

    public List<InvestRecordBean> getInvest_record() {
        return invest_record;
    }

    public void setInvest_record(List<InvestRecordBean> invest_record) {
        this.invest_record = invest_record;
    }

    public static class InvestRecordBean {
        /**
         * bid_category : 1
         * is_show_raise_interest : true
         * platform_short_name : iqianbang
         * second_bonus_interest : 3
         * raise_interest_value : 7.32
         * duration : 17天
         * raise_interest_tickets : []
         * principal : 1178600
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/dce6e994/app-dce6e994-081c-49c6-8425-61b00c994b0d-1490928270.png
         * order_id : 162
         * bid_id : 1
         * first_bonus_interest : 3
         * interest : 0.00
         * is_daily_bonus : 0
         * status : 3
         * bid_name : 爱车贷-zjtest111002
         * order_time : 2017.11.23
         * bonus : 36.6
         * questionmark_tag : 0
         * bid_url : /m/bid_detail/fix/247/
         * daily_bonus_questionmark_info : {"content_list":[],"is_show":false,"title":""}
         * interest_and_bonus_rate : 10%+3%
         * is_show_bonus_dialog : false
         * raise_interest_name : 加息(元)
         * platform_name : 爱钱帮
         */

        private int bid_category;
        private boolean is_show_raise_interest;
        private String platform_short_name;
        private String second_bonus_interest;
        private String raise_interest_value;
        private String duration;
        private String principal;
        private String platform_logo;
        private int order_id;
        private int bid_id;
        private String first_bonus_interest;
        private String interest;
        private int is_daily_bonus;
        private int status;
        private String bid_name;
        private String order_time;
        private String bonus;
        private int questionmark_tag;
        private String bid_url;
        private DailyBonusQuestionmarkInfoBean daily_bonus_questionmark_info;
        private String interest_and_bonus_rate;
        private boolean is_show_bonus_dialog;
        private String raise_interest_name;
        private String platform_name;
        private List<RaiseInterestTicketsBean> raise_interest_tickets;
        private boolean is_show_platform_redpacket;
        private String platform_redpacket_name;
        private String platform_redpacket_value;

        public boolean is_show_raise_interest() {
            return is_show_raise_interest;
        }

        public boolean is_show_bonus_dialog() {
            return is_show_bonus_dialog;
        }

        public boolean is_show_platform_redpacket() {
            return is_show_platform_redpacket;
        }

        public void setIs_show_platform_redpacket(boolean is_show_platform_redpacket) {
            this.is_show_platform_redpacket = is_show_platform_redpacket;
        }

        public String getPlatform_redpacket_name() {
            return platform_redpacket_name;
        }

        public void setPlatform_redpacket_name(String platform_redpacket_name) {
            this.platform_redpacket_name = platform_redpacket_name;
        }

        public String getPlatform_redpacket_value() {
            return platform_redpacket_value;
        }

        public void setPlatform_redpacket_value(String platform_redpacket_value) {
            this.platform_redpacket_value = platform_redpacket_value;
        }

        public int getBid_category() {
            return bid_category;
        }

        public void setBid_category(int bid_category) {
            this.bid_category = bid_category;
        }

        public boolean isIs_show_raise_interest() {
            return is_show_raise_interest;
        }

        public void setIs_show_raise_interest(boolean is_show_raise_interest) {
            this.is_show_raise_interest = is_show_raise_interest;
        }

        public String getPlatform_short_name() {
            return platform_short_name;
        }

        public void setPlatform_short_name(String platform_short_name) {
            this.platform_short_name = platform_short_name;
        }

        public String getSecond_bonus_interest() {
            return second_bonus_interest;
        }

        public void setSecond_bonus_interest(String second_bonus_interest) {
            this.second_bonus_interest = second_bonus_interest;
        }

        public String getRaise_interest_value() {
            return raise_interest_value;
        }

        public void setRaise_interest_value(String raise_interest_value) {
            this.raise_interest_value = raise_interest_value;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getPrincipal() {
            return principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getFirst_bonus_interest() {
            return first_bonus_interest;
        }

        public void setFirst_bonus_interest(String first_bonus_interest) {
            this.first_bonus_interest = first_bonus_interest;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public int getIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(int is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getBonus() {
            return bonus;
        }

        public void setBonus(String bonus) {
            this.bonus = bonus;
        }

        public int getQuestionmark_tag() {
            return questionmark_tag;
        }

        public void setQuestionmark_tag(int questionmark_tag) {
            this.questionmark_tag = questionmark_tag;
        }

        public String getBid_url() {
            return bid_url;
        }

        public void setBid_url(String bid_url) {
            this.bid_url = bid_url;
        }

        public DailyBonusQuestionmarkInfoBean getDaily_bonus_questionmark_info() {
            return daily_bonus_questionmark_info;
        }

        public void setDaily_bonus_questionmark_info(DailyBonusQuestionmarkInfoBean daily_bonus_questionmark_info) {
            this.daily_bonus_questionmark_info = daily_bonus_questionmark_info;
        }

        public String getInterest_and_bonus_rate() {
            return interest_and_bonus_rate;
        }

        public void setInterest_and_bonus_rate(String interest_and_bonus_rate) {
            this.interest_and_bonus_rate = interest_and_bonus_rate;
        }

        public boolean isIs_show_bonus_dialog() {
            return is_show_bonus_dialog;
        }

        public void setIs_show_bonus_dialog(boolean is_show_bonus_dialog) {
            this.is_show_bonus_dialog = is_show_bonus_dialog;
        }

        public String getRaise_interest_name() {
            return raise_interest_name;
        }

        public void setRaise_interest_name(String raise_interest_name) {
            this.raise_interest_name = raise_interest_name;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public List<RaiseInterestTicketsBean> getRaise_interest_tickets() {
            return raise_interest_tickets;
        }

        public void setRaise_interest_tickets(List<RaiseInterestTicketsBean> raise_interest_tickets) {
            this.raise_interest_tickets = raise_interest_tickets;
        }

        public static class DailyBonusQuestionmarkInfoBean {
            /**
             * content_list : []
             * is_show : false
             * title :
             */

            private boolean is_show;
            private String title;
            private List<String> content_list;

            public boolean isIs_show() {
                return is_show;
            }

            public void setIs_show(boolean is_show) {
                this.is_show = is_show;
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
        }

        public static class RaiseInterestTicketsBean {
            /**
             * expire_date : 2017-02-28
             * id : 1
             * rule : 单笔金额>=1000元可使用 最高可兑换2000元
             * name : 1.5%加息券
             */
            private String expire_date;
            private int id;
            private String rule;
            private String name;

            public String getExpire_date() {
                return expire_date;
            }

            public void setExpire_date(String expire_date) {
                this.expire_date = expire_date;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRule() {
                return rule;
            }

            public void setRule(String rule) {
                this.rule = rule;
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
