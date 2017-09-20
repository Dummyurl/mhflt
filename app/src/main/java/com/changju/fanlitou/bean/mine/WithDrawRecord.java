package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/6/20.
 */

public class WithDrawRecord {

    /**
     * has_more : true
     * withdraw_record : [{"record":[{"status":"已提交待审核","amount_style":"black","amount":100,"fee":0,"status_style":"blue","bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png","submit_time":"2017-06-13","account_num":"9116","withdraw_id":199,"bank_name":"中国农业银行"}],"month":"2017年6月"},{"record":[{"status":"处理中","amount_style":"black","amount":50,"fee":2,"status_style":"blue","bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png","submit_time":"2017-05-27","account_num":"9116","withdraw_id":198,"bank_name":"中国农业银行"}],"month":"2017年5月"}]
     */

    private boolean has_more;
    private List<WithdrawRecordBean> withdraw_record;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<WithdrawRecordBean> getWithdraw_record() {
        return withdraw_record;
    }

    public void setWithdraw_record(List<WithdrawRecordBean> withdraw_record) {
        this.withdraw_record = withdraw_record;
    }

    public static class WithdrawRecordBean {
        /**
         * record : [{"status":"已提交待审核","amount_style":"black","amount":100,"fee":0,"status_style":"blue","bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png","submit_time":"2017-06-13","account_num":"9116","withdraw_id":199,"bank_name":"中国农业银行"}]
         * month : 2017年6月
         */

        private String month;
        private List<RecordBean> record;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class RecordBean {
            /**
             * status : 已提交待审核
             * amount_style : black
             * amount : 100
             * fee : 0
             * status_style : blue
             * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png
             * submit_time : 2017-06-13
             * account_num : 9116
             * withdraw_id : 199
             * bank_name : 中国农业银行
             */

            private String status;
            private String amount_style;
            private String amount;
            private String fee;
            private String status_style;
            private String bank_logo;
            private String submit_time;
            private String account_num;
            private int withdraw_id;
            private String bank_name;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAmount_style() {
                return amount_style;
            }

            public void setAmount_style(String amount_style) {
                this.amount_style = amount_style;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getStatus_style() {
                return status_style;
            }

            public void setStatus_style(String status_style) {
                this.status_style = status_style;
            }

            public String getBank_logo() {
                return bank_logo;
            }

            public void setBank_logo(String bank_logo) {
                this.bank_logo = bank_logo;
            }

            public String getSubmit_time() {
                return submit_time;
            }

            public void setSubmit_time(String submit_time) {
                this.submit_time = submit_time;
            }

            public String getAccount_num() {
                return account_num;
            }

            public void setAccount_num(String account_num) {
                this.account_num = account_num;
            }

            public int getWithdraw_id() {
                return withdraw_id;
            }

            public void setWithdraw_id(int withdraw_id) {
                this.withdraw_id = withdraw_id;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }
        }
    }
}
