package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ProcessingFundBean {

    /**
     * total : 17
     * result : [{"status":2,"processing_share":"10.00","operating_date":"2017-07-26","fund_type":"货币型","processing_amount":"--","id":1157,"status_str":"赎回中","name":"工银货币","fund_code":"482002","recent_nav":"1.0000","expect_confirm_date":"2017-07-28"},{"status":1,"processing_share":"--","operating_date":"2017-07-26","fund_type":"股票型","processing_amount":"111100.00","id":1440,"status_str":"买入中","name":"华夏领先股票","fund_code":"001042","recent_nav":"0.7110","expect_confirm_date":"2017-07-28"},{"status":2,"processing_share":"100.00","operating_date":"2017-07-26","fund_type":"货币型","processing_amount":"--","id":1157,"status_str":"赎回中","name":"工银货币","fund_code":"482002","recent_nav":"1.0000","expect_confirm_date":"2017-07-28"},{"status":1,"processing_share":"--","operating_date":"2017-07-26","fund_type":"股票型","processing_amount":"111100.00","id":1440,"status_str":"买入中","name":"华夏领先股票","fund_code":"001042","recent_nav":"0.7110","expect_confirm_date":"2017-07-28"},{"status":1,"processing_share":"--","operating_date":"2017-07-26","fund_type":"股票型","processing_amount":"111100.00","id":1440,"status_str":"买入中","name":"华夏领先股票","fund_code":"001042","recent_nav":"0.7110","expect_confirm_date":"2017-07-28"},{"status":1,"processing_share":"--","operating_date":"2017-07-26","fund_type":"股票型","processing_amount":"111100.00","id":1440,"status_str":"买入中","name":"华夏领先股票","fund_code":"001042","recent_nav":"0.7110","expect_confirm_date":"2017-07-28"},{"status":1,"processing_share":"--","operating_date":"2017-07-26","fund_type":"股票型","processing_amount":"100000.00","id":1440,"status_str":"买入中","name":"华夏领先股票","fund_code":"001042","recent_nav":"0.7110","expect_confirm_date":"2017-07-28"},{"status":1,"processing_share":"--","operating_date":"2017-07-25","fund_type":"QDII","processing_amount":"10000.00","id":218,"status_str":"买入中","name":"交银中证海外互联网","fund_code":"164906","recent_nav":"1.2430","expect_confirm_date":"2017-07-27"},{"status":1,"processing_share":"--","operating_date":"2017-07-25","fund_type":"QDII","processing_amount":"10000.00","id":218,"status_str":"买入中","name":"交银中证海外互联网","fund_code":"164906","recent_nav":"1.2430","expect_confirm_date":"2017-07-27"},{"status":1,"processing_share":"--","operating_date":"2017-07-11","fund_type":"货币型","processing_amount":"1000000.00","id":1157,"status_str":"买入中","name":"工银货币","fund_code":"482002","recent_nav":"1.0000","expect_confirm_date":"2017-07-12"}]
     */

    private int total;
    private List<ResultBean> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * status : 2
         * processing_share : 10.00
         * operating_date : 2017-07-26
         * fund_type : 货币型
         * processing_amount : --
         * id : 1157
         * status_str : 赎回中
         * name : 工银货币
         * fund_code : 482002
         * recent_nav : 1.0000
         * expect_confirm_date : 2017-07-28
         */

        private int status;
        private String processing_share;
        private String operating_date;
        private String fund_type;
        private String processing_amount;
        private int id;
        private String status_str;
        private String name;
        private String fund_code;
        private String recent_nav;
        private String expect_confirm_date;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getProcessing_share() {
            return processing_share;
        }

        public void setProcessing_share(String processing_share) {
            this.processing_share = processing_share;
        }

        public String getOperating_date() {
            return operating_date;
        }

        public void setOperating_date(String operating_date) {
            this.operating_date = operating_date;
        }

        public String getFund_type() {
            return fund_type;
        }

        public void setFund_type(String fund_type) {
            this.fund_type = fund_type;
        }

        public String getProcessing_amount() {
            return processing_amount;
        }

        public void setProcessing_amount(String processing_amount) {
            this.processing_amount = processing_amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFund_code() {
            return fund_code;
        }

        public void setFund_code(String fund_code) {
            this.fund_code = fund_code;
        }

        public String getRecent_nav() {
            return recent_nav;
        }

        public void setRecent_nav(String recent_nav) {
            this.recent_nav = recent_nav;
        }

        public String getExpect_confirm_date() {
            return expect_confirm_date;
        }

        public void setExpect_confirm_date(String expect_confirm_date) {
            this.expect_confirm_date = expect_confirm_date;
        }
    }
}
