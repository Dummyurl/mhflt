package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/7/24.
 */

public class FundHisDiv {

    /**
     * div_info : [{"div_remv_tax":0.05,"record_date":"2016-08-26","pay_date":"2016-08-25"},{"div_remv_tax":0.08,"record_date":"2015-11-13","pay_date":"2015-11-12"},{"div_remv_tax":0.01,"record_date":"2013-01-21","pay_date":"2013-01-18"}]
     * total_count : 3
     */

    private String total_count;
    private List<DivInfoBean> div_info;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<DivInfoBean> getDiv_info() {
        return div_info;
    }

    public void setDiv_info(List<DivInfoBean> div_info) {
        this.div_info = div_info;
    }

    public static class DivInfoBean {
        /**
         * div_remv_tax : 0.05
         * record_date : 2016-08-26
         * pay_date : 2016-08-25
         */

        private String div_remv_tax;
        private String record_date;
        private String pay_date;

        public String getDiv_remv_tax() {
            return div_remv_tax;
        }

        public void setDiv_remv_tax(String div_remv_tax) {
            this.div_remv_tax = div_remv_tax;
        }

        public String getRecord_date() {
            return record_date;
        }

        public void setRecord_date(String record_date) {
            this.record_date = record_date;
        }

        public String getPay_date() {
            return pay_date;
        }

        public void setPay_date(String pay_date) {
            this.pay_date = pay_date;
        }
    }
}
