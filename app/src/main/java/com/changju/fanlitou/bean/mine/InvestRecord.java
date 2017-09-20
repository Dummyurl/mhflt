package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/6/17.
 */

public class InvestRecord {

    /**
     * total_records : 116
     * invest_record : [{"bid_category":5,"bid_url":"/m/bid_detail/foreign/4/","platform_short_name":"meixin","bonus_rate":"返利1%","bid_name":"安心计划6个月","bid_id":1,"order_time":"2017.06.16","order_id":77,"interest_rate":"年化收益5%","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/ac820f47/app-ac820f47-3424-450c-b234-26ba3f392eaa-1490927658.png","settlement_str":"满标2日内清算","platform_name":"美信金融","settlement_questionmark_info":{"content_list":["清算后返回注册及返利信息"],"is_show":true,"title":"清算时间"},"duration":"期限6个月","questionmark_tag":0,"raise_interest_tickets":[]}]
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
         * bid_category : 5
         * bid_url : /m/bid_detail/foreign/4/
         * platform_short_name : meixin
         * bonus_rate : 返利1%
         * bid_name : 安心计划6个月
         * bid_id : 1
         * order_time : 2017.06.16
         * order_id : 77
         * interest_rate : 年化收益5%
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/ac820f47/app-ac820f47-3424-450c-b234-26ba3f392eaa-1490927658.png
         * settlement_str : 满标2日内清算
         * platform_name : 美信金融
         * settlement_questionmark_info : {"content_list":["清算后返回注册及返利信息"],"is_show":true,"title":"清算时间"}
         * duration : 期限6个月
         * questionmark_tag : 0
         * raise_interest_tickets : []
         */

        private int bid_category;
        private String bid_url;
        private String platform_short_name;
        private String bonus_rate;
        private String bid_name;
        private int bid_id;
        private String order_time;
        private int order_id;
        private String interest_rate;
        private String platform_logo;
        private String settlement_str;
        private String platform_name;
        private SettlementQuestionmarkInfoBean settlement_questionmark_info;
        private String duration;
        private String questionmark_tag;

        public int getBid_category() {
            return bid_category;
        }

        public void setBid_category(int bid_category) {
            this.bid_category = bid_category;
        }

        public String getBid_url() {
            return bid_url;
        }

        public void setBid_url(String bid_url) {
            this.bid_url = bid_url;
        }

        public String getPlatform_short_name() {
            return platform_short_name;
        }

        public void setPlatform_short_name(String platform_short_name) {
            this.platform_short_name = platform_short_name;
        }

        public String getBonus_rate() {
            return bonus_rate;
        }

        public void setBonus_rate(String bonus_rate) {
            this.bonus_rate = bonus_rate;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getInterest_rate() {
            return interest_rate;
        }

        public void setInterest_rate(String interest_rate) {
            this.interest_rate = interest_rate;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public String getSettlement_str() {
            return settlement_str;
        }

        public void setSettlement_str(String settlement_str) {
            this.settlement_str = settlement_str;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public SettlementQuestionmarkInfoBean getSettlement_questionmark_info() {
            return settlement_questionmark_info;
        }

        public void setSettlement_questionmark_info(SettlementQuestionmarkInfoBean settlement_questionmark_info) {
            this.settlement_questionmark_info = settlement_questionmark_info;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getQuestionmark_tag() {
            return questionmark_tag;
        }

        public void setQuestionmark_tag(String questionmark_tag) {
            this.questionmark_tag = questionmark_tag;
        }


        public static class SettlementQuestionmarkInfoBean {
            /**
             * content_list : ["清算后返回注册及返利信息"]
             * is_show : true
             * title : 清算时间
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
    }
}
