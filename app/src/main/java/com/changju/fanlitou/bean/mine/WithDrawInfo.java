package com.changju.fanlitou.bean.mine;


import java.util.List;

/**
 * Created by chengww on 2017/6/6.
 */

public class WithDrawInfo {

    /**
     * is_bound : true
     * is_verified : true
     * withdraw_info : {"free_withdraw_count":3,"is_bound":true,"available_cash":632.06,"phone":"13000000020","bank_accounts":[{"bank_logo":"","number":"2952","name":"德州银行","id":55}],"wcount":0,"withdraw_fee":2,"has_province_city_info":true,"fee":0,"is_verified":true}
     * total_count : 1
     */

    private boolean is_bound;
    private boolean is_verified;
    private WithdrawInfoBean withdraw_info;
    private int total_count;

    public boolean isIs_bound() {
        return is_bound;
    }

    public void setIs_bound(boolean is_bound) {
        this.is_bound = is_bound;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public WithdrawInfoBean getWithdraw_info() {
        return withdraw_info;
    }

    public void setWithdraw_info(WithdrawInfoBean withdraw_info) {
        this.withdraw_info = withdraw_info;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public static class WithdrawInfoBean {
        /**
         * free_withdraw_count : 3
         * is_bound : true
         * available_cash : 632.06
         * phone : 13000000020
         * bank_accounts : [{"bank_logo":"","number":"2952","name":"德州银行","id":55}]
         * wcount : 0
         * withdraw_fee : 2
         * has_province_city_info : true
         * fee : 0
         * is_verified : true
         */

        private int free_withdraw_count;
        private boolean is_bound;
        private String available_cash;
        private String phone;
        private int wcount;
        private String withdraw_fee;
        private boolean has_province_city_info;
        private String fee;
        private boolean is_verified;
        private List<BankAccountsBean> bank_accounts;
        private boolean is_show_jifen_alert;
        private String year_yld;

        public String getYear_yld() {
            return year_yld;
        }

        public void setYear_yld(String year_yld) {
            this.year_yld = year_yld;
        }

        public boolean is_show_jifen_alert() {
            return is_show_jifen_alert;
        }

        public void setIs_show_jifen_alert(boolean is_show_jifen_alert) {
            this.is_show_jifen_alert = is_show_jifen_alert;
        }

        public int getFree_withdraw_count() {
            return free_withdraw_count;
        }

        public void setFree_withdraw_count(int free_withdraw_count) {
            this.free_withdraw_count = free_withdraw_count;
        }

        public boolean isIs_bound() {
            return is_bound;
        }

        public void setIs_bound(boolean is_bound) {
            this.is_bound = is_bound;
        }

        public String getAvailable_cash() {
            return available_cash;
        }

        public void setAvailable_cash(String available_cash) {
            this.available_cash = available_cash;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getWcount() {
            return wcount;
        }

        public void setWcount(int wcount) {
            this.wcount = wcount;
        }

        public String getWithdraw_fee() {
            return withdraw_fee;
        }

        public void setWithdraw_fee(String withdraw_fee) {
            this.withdraw_fee = withdraw_fee;
        }

        public boolean isHas_province_city_info() {
            return has_province_city_info;
        }

        public void setHas_province_city_info(boolean has_province_city_info) {
            this.has_province_city_info = has_province_city_info;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public boolean isIs_verified() {
            return is_verified;
        }

        public void setIs_verified(boolean is_verified) {
            this.is_verified = is_verified;
        }

        public List<BankAccountsBean> getBank_accounts() {
            return bank_accounts;
        }

        public void setBank_accounts(List<BankAccountsBean> bank_accounts) {
            this.bank_accounts = bank_accounts;
        }

        public static class BankAccountsBean {
            /**
             * bank_logo :
             * number : 2952
             * name : 德州银行
             * id : 55
             */

            private String bank_logo;
            private String number;
            private String name;
            private int id;

            public String getBank_logo() {
                return bank_logo;
            }

            public void setBank_logo(String bank_logo) {
                this.bank_logo = bank_logo;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
