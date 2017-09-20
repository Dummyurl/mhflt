package com.changju.fanlitou.bean.account;

import java.util.List;

/**
 * Created by chengww on 2017/6/16.
 */

public class CurrentIncomeList {

    /**
     * week_data : {"total_income":10,"data_list":[{"date":"2017-05-21","principal":10000,"income":10}]}
     * month_data : {"total_income":10,"data_list":[{"date":"2017-05-21","principal":10000,"income":10}]}
     * bid_name : 测试
     */

    private WeekDataBean week_data;
    private MonthDataBean month_data;
    private String bid_name;

    public WeekDataBean getWeek_data() {
        return week_data;
    }

    public void setWeek_data(WeekDataBean week_data) {
        this.week_data = week_data;
    }

    public MonthDataBean getMonth_data() {
        return month_data;
    }

    public void setMonth_data(MonthDataBean month_data) {
        this.month_data = month_data;
    }

    public String getBid_name() {
        return bid_name;
    }

    public void setBid_name(String bid_name) {
        this.bid_name = bid_name;
    }

    public static class WeekDataBean {
        /**
         * total_income : 10
         * data_list : [{"date":"2017-05-21","principal":10000,"income":10}]
         */

        private String total_income;
        private List<DataListBean> data_list;

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
        }

        public List<DataListBean> getData_list() {
            return data_list;
        }

        public void setData_list(List<DataListBean> data_list) {
            this.data_list = data_list;
        }

        public static class DataListBean {
            /**
             * date : 2017-05-21
             * principal : 10000
             * income : 10
             */

            private String date;
            private String principal;
            private String income;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }
        }
    }

    public static class MonthDataBean {
        /**
         * total_income : 10
         * data_list : [{"date":"2017-05-21","principal":10000,"income":10}]
         */

        private String total_income;
        private List<DataListBeanX> data_list;

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
        }

        public List<DataListBeanX> getData_list() {
            return data_list;
        }

        public void setData_list(List<DataListBeanX> data_list) {
            this.data_list = data_list;
        }

        public static class DataListBeanX {
            /**
             * date : 2017-05-21
             * principal : 10000
             * income : 10
             */

            private String date;
            private String principal;
            private String income;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }
        }
    }
}
