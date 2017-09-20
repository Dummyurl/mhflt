package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/8/3.
 */

public class FundTradeRecord {

    private String total;
    private List<ResultBean> result;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
         * status : 5
         * invest_amount : 500098.00
         * flt_order_no : 1501065354868331
         * fund_type : ETF
         * invest_share : --
         * fund_code : 070023
         * order_date : 2017-07-26
         * id : 393
         * status_str : 买入失败
         * name : 嘉实深证基本面120EFT联接
         * is_redem : false
         */

        private int status;
        private String invest_amount;
        private String flt_order_no;
        private String fund_type;
        private String invest_share;
        private String fund_code;
        private String order_date;
        private int id;
        private String status_str;
        private String name;
        private boolean is_redem;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInvest_amount() {
            return invest_amount;
        }

        public void setInvest_amount(String invest_amount) {
            this.invest_amount = invest_amount;
        }

        public String getFlt_order_no() {
            return flt_order_no;
        }

        public void setFlt_order_no(String flt_order_no) {
            this.flt_order_no = flt_order_no;
        }

        public String getFund_type() {
            return fund_type;
        }

        public void setFund_type(String fund_type) {
            this.fund_type = fund_type;
        }

        public String getInvest_share() {
            return invest_share;
        }

        public void setInvest_share(String invest_share) {
            this.invest_share = invest_share;
        }

        public String getFund_code() {
            return fund_code;
        }

        public void setFund_code(String fund_code) {
            this.fund_code = fund_code;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
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

        public boolean isIs_redem() {
            return is_redem;
        }

        public void setIs_redem(boolean is_redem) {
            this.is_redem = is_redem;
        }
    }
}
