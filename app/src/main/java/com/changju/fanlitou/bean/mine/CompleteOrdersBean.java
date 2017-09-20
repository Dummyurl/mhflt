package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class CompleteOrdersBean {

    /**
     * total : 19
     * result : [{"status":3,"status_str":"已赎回","amount":"0.00","arrive_time":"2017-08-01","name":"工银货币","msg":"成功","confirm_time":"2017-07-27","fund_code":"482002","shares":"10.00"},{"status":4,"status_str":"已持有","amount":"1000.00","arrive_time":"","name":"大摩品质生活股票","msg":"成功","confirm_time":"2017-07-25","fund_code":"000309","shares":"0.00"},{"status":4,"status_str":"已持有","amount":"1000.00","arrive_time":"","name":"招商医药股票","msg":"成功","confirm_time":"2017-07-25","fund_code":"000960","shares":"0.00"},{"status":3,"status_str":"已赎回","amount":"0.00","arrive_time":"2017-07-18","name":"工银货币","msg":"成功","confirm_time":"2017-07-13","fund_code":"482002","shares":"1.00"},{"status":6,"status_str":"赎回失败","amount":"0.00","arrive_time":"","name":"工银货币","msg":"份额格式不正确;","confirm_time":"","fund_code":"482002","shares":"0.11"},{"status":5,"status_str":"买入失败","amount":"100.00","arrive_time":"","name":"工银货币","msg":"成功","confirm_time":"2017-07-13","fund_code":"482002","shares":"0.00"},{"status":5,"status_str":"买入失败","amount":"1000.00","arrive_time":"","name":"国泰大宗商品(QDII-LOF)","msg":"申购的基金状态不是正常或不可赎回状态","confirm_time":"","fund_code":"160216","shares":"0.00"},{"status":3,"status_str":"已赎回","amount":"0.00","arrive_time":"2017-07-18","name":"工银货币","msg":"成功","confirm_time":"2017-07-13","fund_code":"482002","shares":"1.00"},{"status":6,"status_str":"赎回失败","amount":"0.00","arrive_time":"","name":"工银货币","msg":"客户赎回基金可用份额不足","confirm_time":"","fund_code":"482002","shares":"1.00"},{"status":6,"status_str":"赎回失败","amount":"0.00","arrive_time":"","name":"工银货币","msg":"未查询到客户可用份额","confirm_time":"","fund_code":"482002","shares":"1.00"}]
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
         * status : 3
         * status_str : 已赎回
         * amount : 0.00
         * arrive_time : 2017-08-01
         * name : 工银货币
         * msg : 成功
         * confirm_time : 2017-07-27
         * fund_code : 482002
         * shares : 10.00
         */

        private int status;
        private String status_str;
        private String amount;
        private String arrive_time;
        private String name;
        private String msg;
        private String confirm_time;
        private String fund_code;
        private String shares;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getArrive_time() {
            return arrive_time;
        }

        public void setArrive_time(String arrive_time) {
            this.arrive_time = arrive_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getConfirm_time() {
            return confirm_time;
        }

        public void setConfirm_time(String confirm_time) {
            this.confirm_time = confirm_time;
        }

        public String getFund_code() {
            return fund_code;
        }

        public void setFund_code(String fund_code) {
            this.fund_code = fund_code;
        }

        public String getShares() {
            return shares;
        }

        public void setShares(String shares) {
            this.shares = shares;
        }
    }
}
