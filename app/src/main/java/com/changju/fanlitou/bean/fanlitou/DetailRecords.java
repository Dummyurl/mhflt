package com.changju.fanlitou.bean.fanlitou;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhangming on 2017/8/10.
 */

public class DetailRecords {

    /**
     * datalist : [{"status":"","amount":"+0.0006","remark":"返利宝每日收益","order_no":"15525","date":"2017-07-29","accum_income":"0.0086","balance":0,"type":"income","class":"postive","icon":"https://o0s106hgi.qnssl.com/jifen/icon/income.png"}]
     * record_type : income
     * total : 33
     */

    private String record_type;
    private int total;
    private List<DatalistBean> datalist;

    public String getRecord_type() {
        return record_type;
    }

    public void setRecord_type(String record_type) {
        this.record_type = record_type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatalistBean> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DatalistBean> datalist) {
        this.datalist = datalist;
    }

    public static class DatalistBean {
        /**
         * status :
         * amount : +0.0006
         * remark : 返利宝每日收益
         * order_no : 15525
         * date : 2017-07-29
         * accum_income : 0.0086
         * balance : 0
         * type : income
         * class : postive
         * icon : https://o0s106hgi.qnssl.com/jifen/icon/income.png
         */

        private String status;
        private String amount;
        private String remark;
        private String order_no;
        private String date;
        private String accum_income;
        private String balance;
        private String type;
        @SerializedName("class")
        private String classX;
        private String icon;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAccum_income() {
            return accum_income;
        }

        public void setAccum_income(String accum_income) {
            this.accum_income = accum_income;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
