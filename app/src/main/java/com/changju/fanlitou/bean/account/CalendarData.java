package com.changju.fanlitou.bean.account;

import java.util.List;

/**
 * Created by chengww on 2017/6/2.
 */

public class CalendarData {

    /**
     * calendar_data : {"repay_month":"6","repay_list":[{"category":"固收","platform_name":"一起好","term":8,"platform_short_name":"yiqihao","bid_name":"押车宝168911","total_term":12,"class_name":"state_blue","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png","repay_day":8,"bid_id":337587,"platform_id":44,"platform":"一起好","repay_date":"2017-06-08","interest":83.33,"bid_flt_url":"/products/revision/337587/","total_bonus_count":0,"is_daily_bonus":true,"total_bonus":0,"condition":"已回款","principal":0},{"category":"固收","platform_name":"银豆网","term":8,"platform_short_name":"yindou","bid_name":"企业借贷-0008（第五期）","total_term":13,"class_name":"state_blue","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/64c27fc2/app-64c27fc2-64cc-4895-abb8-e9b0f62988f7-1490927241.png","repay_day":8,"bid_id":337719,"platform_id":50,"platform":"银豆网","repay_date":"2017-06-08","interest":110.41,"bid_flt_url":"/products/revision/337719/","total_bonus_count":1,"is_daily_bonus":false,"total_bonus":0,"condition":"已回款","principal":0},{"category":"固收","platform_name":"玖融网","term":8,"platform_short_name":"jiurong","bid_name":"玖融网测试标的","total_term":24,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/41d48ab2/app-41d48ab2-9dae-4fee-b54c-dd55037bfce4-1490927124.png","repay_day":12,"bid_id":337940,"platform_id":54,"platform":"玖融网","repay_date":"2017-06-12","interest":110.42,"bid_flt_url":"/products/revision/337940/","total_bonus_count":729,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":408.72},{"category":"固收","platform_name":"爱钱帮","term":2,"platform_short_name":"iqianbang","bid_name":"爱房贷-北京个人房产周转贷FDHX170431-4","total_term":3,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/dce6e994/app-dce6e994-081c-49c6-8425-61b00c994b0d-1490928270.png","repay_day":14,"bid_id":346283,"platform_id":8,"platform":"爱钱帮","repay_date":"2017-06-14","interest":26.62,"bid_flt_url":"/products/revision/346283/","total_bonus_count":88,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":0},{"category":"固收","platform_name":"壹佰金融","term":2,"platform_short_name":"pp100","bid_name":"【理财专区】E42017042005","total_term":13,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/a02a99e3/app-a02a99e3-1b21-4562-9b5c-567ebfb88c46-1490927945.png","repay_day":15,"bid_id":346288,"platform_id":21,"platform":"壹佰金融","repay_date":"2017-06-15","interest":42.16,"bid_flt_url":"/products/revision/346288/","total_bonus_count":0,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":0},{"category":"固收","platform_name":"一起好","term":11,"platform_short_name":"yiqihao","bid_name":"加息10939","total_term":12,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png","repay_day":22,"bid_id":333614,"platform_id":44,"platform":"一起好","repay_date":"2017-06-22","interest":0.53,"bid_flt_url":"/products/revision/333614/","total_bonus_count":364,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":26.13}],"repayed_date_list":[{"month":6,"day":20,"year":2017}],"repay_category_list":[{"class_name":"total-one","name":"当月固收理财金额","total_amount":808.32}],"total_repay_count":6,"total_principal":434.85,"repaying_date_list":[{"month":6,"day":1,"year":2017}],"repay_category_count":1,"total":808.32,"repay_days":[8,12,14,15,22],"repay_year":"2017","total_interest":373.47}
     */

    private CalendarDataBean calendar_data;

    public CalendarDataBean getCalendar_data() {
        return calendar_data;
    }

    public void setCalendar_data(CalendarDataBean calendar_data) {
        this.calendar_data = calendar_data;
    }

    public static class CalendarDataBean {
        /**
         * repay_month : 6
         * repay_list : [{"category":"固收","platform_name":"一起好","term":8,"platform_short_name":"yiqihao","bid_name":"押车宝168911","total_term":12,"class_name":"state_blue","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png","repay_day":8,"bid_id":337587,"platform_id":44,"platform":"一起好","repay_date":"2017-06-08","interest":83.33,"bid_flt_url":"/products/revision/337587/","total_bonus_count":0,"is_daily_bonus":true,"total_bonus":0,"condition":"已回款","principal":0},{"category":"固收","platform_name":"银豆网","term":8,"platform_short_name":"yindou","bid_name":"企业借贷-0008（第五期）","total_term":13,"class_name":"state_blue","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/64c27fc2/app-64c27fc2-64cc-4895-abb8-e9b0f62988f7-1490927241.png","repay_day":8,"bid_id":337719,"platform_id":50,"platform":"银豆网","repay_date":"2017-06-08","interest":110.41,"bid_flt_url":"/products/revision/337719/","total_bonus_count":1,"is_daily_bonus":false,"total_bonus":0,"condition":"已回款","principal":0},{"category":"固收","platform_name":"玖融网","term":8,"platform_short_name":"jiurong","bid_name":"玖融网测试标的","total_term":24,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/41d48ab2/app-41d48ab2-9dae-4fee-b54c-dd55037bfce4-1490927124.png","repay_day":12,"bid_id":337940,"platform_id":54,"platform":"玖融网","repay_date":"2017-06-12","interest":110.42,"bid_flt_url":"/products/revision/337940/","total_bonus_count":729,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":408.72},{"category":"固收","platform_name":"爱钱帮","term":2,"platform_short_name":"iqianbang","bid_name":"爱房贷-北京个人房产周转贷FDHX170431-4","total_term":3,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/dce6e994/app-dce6e994-081c-49c6-8425-61b00c994b0d-1490928270.png","repay_day":14,"bid_id":346283,"platform_id":8,"platform":"爱钱帮","repay_date":"2017-06-14","interest":26.62,"bid_flt_url":"/products/revision/346283/","total_bonus_count":88,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":0},{"category":"固收","platform_name":"壹佰金融","term":2,"platform_short_name":"pp100","bid_name":"【理财专区】E42017042005","total_term":13,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/a02a99e3/app-a02a99e3-1b21-4562-9b5c-567ebfb88c46-1490927945.png","repay_day":15,"bid_id":346288,"platform_id":21,"platform":"壹佰金融","repay_date":"2017-06-15","interest":42.16,"bid_flt_url":"/products/revision/346288/","total_bonus_count":0,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":0},{"category":"固收","platform_name":"一起好","term":11,"platform_short_name":"yiqihao","bid_name":"加息10939","total_term":12,"class_name":"state_red","type":"p2p","platform_logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png","repay_day":22,"bid_id":333614,"platform_id":44,"platform":"一起好","repay_date":"2017-06-22","interest":0.53,"bid_flt_url":"/products/revision/333614/","total_bonus_count":364,"is_daily_bonus":true,"total_bonus":0,"condition":"待回款","principal":26.13}]
         * repayed_date_list : [{"month":6,"day":20,"year":2017}]
         * repay_category_list : [{"class_name":"total-one","name":"当月固收理财金额","total_amount":808.32}]
         * total_repay_count : 6
         * total_principal : 434.85
         * repaying_date_list : [{"month":6,"day":1,"year":2017}]
         * repay_category_count : 1
         * total : 808.32
         * repay_days : [8,12,14,15,22]
         * repay_year : 2017
         * total_interest : 373.47
         */

        private String repay_month;
        private int total_repay_count;
        private String total_principal;
        private int repay_category_count;
        private String total;
        private String repay_year;
        private String total_interest;
        private List<RepayListBean> repay_list;
        private List<RepayedDateListBean> repayed_date_list;
        private List<RepayCategoryListBean> repay_category_list;
        private List<RepayingDateListBean> repaying_date_list;
        private List<Integer> repay_days;

        public String getRepay_month() {
            return repay_month;
        }

        public void setRepay_month(String repay_month) {
            this.repay_month = repay_month;
        }

        public int getTotal_repay_count() {
            return total_repay_count;
        }

        public void setTotal_repay_count(int total_repay_count) {
            this.total_repay_count = total_repay_count;
        }

        public String getTotal_principal() {
            return total_principal;
        }

        public void setTotal_principal(String total_principal) {
            this.total_principal = total_principal;
        }

        public int getRepay_category_count() {
            return repay_category_count;
        }

        public void setRepay_category_count(int repay_category_count) {
            this.repay_category_count = repay_category_count;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getRepay_year() {
            return repay_year;
        }

        public void setRepay_year(String repay_year) {
            this.repay_year = repay_year;
        }

        public String getTotal_interest() {
            return total_interest;
        }

        public void setTotal_interest(String total_interest) {
            this.total_interest = total_interest;
        }

        public List<RepayListBean> getRepay_list() {
            return repay_list;
        }

        public void setRepay_list(List<RepayListBean> repay_list) {
            this.repay_list = repay_list;
        }

        public List<RepayedDateListBean> getRepayed_date_list() {
            return repayed_date_list;
        }

        public void setRepayed_date_list(List<RepayedDateListBean> repayed_date_list) {
            this.repayed_date_list = repayed_date_list;
        }

        public List<RepayCategoryListBean> getRepay_category_list() {
            return repay_category_list;
        }

        public void setRepay_category_list(List<RepayCategoryListBean> repay_category_list) {
            this.repay_category_list = repay_category_list;
        }

        public List<RepayingDateListBean> getRepaying_date_list() {
            return repaying_date_list;
        }

        public void setRepaying_date_list(List<RepayingDateListBean> repaying_date_list) {
            this.repaying_date_list = repaying_date_list;
        }

        public List<Integer> getRepay_days() {
            return repay_days;
        }

        public void setRepay_days(List<Integer> repay_days) {
            this.repay_days = repay_days;
        }

        public static class RepayListBean {
            /**
             * category : 固收
             * platform_name : 一起好
             * term : 8
             * platform_short_name : yiqihao
             * bid_name : 押车宝168911
             * total_term : 12
             * class_name : state_blue
             * type : p2p
             * platform_logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/9bdf0667/app-9bdf0667-4b99-4a2b-a23e-9c54f45ab5ae-1490927422.png
             * repay_day : 8
             * bid_id : 337587
             * platform_id : 44
             * platform : 一起好
             * repay_date : 2017-06-08
             * interest : 83.33
             * bid_flt_url : /products/revision/337587/
             * total_bonus_count : 0
             * is_daily_bonus : true
             * total_bonus : 0
             * condition : 已回款
             * principal : 0
             */

            private String category;
            private String platform_name;
            private int term;
            private String platform_short_name;
            private String bid_name;
            private int total_term;
            private String class_name;
            private String type;
            private String platform_logo_app;
            private int repay_day;
            private int bid_id;
            private int platform_id;
            private String platform;
            private String repay_date;
            private String interest;
            private String bid_flt_url;
            private int total_bonus_count;
            private boolean is_daily_bonus;
            private String total_bonus;
            private String condition;
            private String principal;

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPlatform_name() {
                return platform_name;
            }

            public void setPlatform_name(String platform_name) {
                this.platform_name = platform_name;
            }

            public int getTerm() {
                return term;
            }

            public void setTerm(int term) {
                this.term = term;
            }

            public String getPlatform_short_name() {
                return platform_short_name;
            }

            public void setPlatform_short_name(String platform_short_name) {
                this.platform_short_name = platform_short_name;
            }

            public String getBid_name() {
                return bid_name;
            }

            public void setBid_name(String bid_name) {
                this.bid_name = bid_name;
            }

            public int getTotal_term() {
                return total_term;
            }

            public void setTotal_term(int total_term) {
                this.total_term = total_term;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPlatform_logo_app() {
                return platform_logo_app;
            }

            public void setPlatform_logo_app(String platform_logo_app) {
                this.platform_logo_app = platform_logo_app;
            }

            public int getRepay_day() {
                return repay_day;
            }

            public void setRepay_day(int repay_day) {
                this.repay_day = repay_day;
            }

            public int getBid_id() {
                return bid_id;
            }

            public void setBid_id(int bid_id) {
                this.bid_id = bid_id;
            }

            public int getPlatform_id() {
                return platform_id;
            }

            public void setPlatform_id(int platform_id) {
                this.platform_id = platform_id;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getRepay_date() {
                return repay_date;
            }

            public void setRepay_date(String repay_date) {
                this.repay_date = repay_date;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
            }

            public String getBid_flt_url() {
                return bid_flt_url;
            }

            public void setBid_flt_url(String bid_flt_url) {
                this.bid_flt_url = bid_flt_url;
            }

            public int getTotal_bonus_count() {
                return total_bonus_count;
            }

            public void setTotal_bonus_count(int total_bonus_count) {
                this.total_bonus_count = total_bonus_count;
            }

            public boolean isIs_daily_bonus() {
                return is_daily_bonus;
            }

            public void setIs_daily_bonus(boolean is_daily_bonus) {
                this.is_daily_bonus = is_daily_bonus;
            }

            public String getTotal_bonus() {
                return total_bonus;
            }

            public void setTotal_bonus(String total_bonus) {
                this.total_bonus = total_bonus;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }
        }

        public static class RepayedDateListBean {
            /**
             * month : 6
             * day : 20
             * year : 2017
             */

            private int month;
            private int day;
            private int year;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }

        public static class RepayCategoryListBean {
            /**
             * class_name : total-one
             * name : 当月固收理财金额
             * total_amount : 808.32
             */

            private String class_name;
            private String name;
            private String total_amount;

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }
        }

        public static class RepayingDateListBean {
            /**
             * month : 6
             * day : 1
             * year : 2017
             */

            private int month;
            private int day;
            private int year;

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
