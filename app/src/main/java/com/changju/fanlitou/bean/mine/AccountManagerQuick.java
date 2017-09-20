package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/7/13.
 */

public class AccountManagerQuick {

    /**
     * total_count : 2
     * register_record : [{"bank_name":"建设银行","platform_short_name":"yumilc","is_enjoy_bonus":"是","register_time":"2017-05-18","receiving_principal_interest":0,"is_favorite_platform":"是","platform_app_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/22fe4864/app-22fe4864-bc1e-403f-90bc-ad790bfc1eab-1490871347.png","bank_account_no":"6228***********9116","platform_name":"玉米理财","platform_user_name":"13426178576","invest_bids":2,"platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/c3ce590a/app-c3ce590a-f2eb-4a37-83f7-c1234e5f681c-1486633701.jpg","platform_id":71,"total_asset":4,"balance":3.4},{"bank_name":"中国农业银行","platform_short_name":"gaosouyi","is_enjoy_bonus":"是","register_time":"2017-05-24","receiving_principal_interest":0,"is_favorite_platform":"是","platform_app_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/3dea7cce/app-3dea7cce-4037-4061-99d2-455046860f75-1493369106.png","bank_account_no":"6228***********9116","platform_name":"高搜易","platform_user_name":"13426178576","invest_bids":1,"platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/a6773b5a/app-a6773b5a-c41a-4e11-b0e5-d46d71f87977-1495794296.png","platform_id":78,"total_asset":2000,"balance":1990}]
     * is_has_intelligent_account : true
     */

    private int total_count;
    private boolean is_has_intelligent_account;
    private List<RegisterRecordBean> register_record;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIs_has_intelligent_account() {
        return is_has_intelligent_account;
    }

    public void setIs_has_intelligent_account(boolean is_has_intelligent_account) {
        this.is_has_intelligent_account = is_has_intelligent_account;
    }

    public List<RegisterRecordBean> getRegister_record() {
        return register_record;
    }

    public void setRegister_record(List<RegisterRecordBean> register_record) {
        this.register_record = register_record;
    }

    public static class RegisterRecordBean {
        /**
         * bank_name : 建设银行
         * platform_short_name : yumilc
         * is_enjoy_bonus : 是
         * register_time : 2017-05-18
         * receiving_principal_interest : 0
         * is_favorite_platform : 是
         * platform_app_logo : https://o0s106hgi.qnssl.com/media/plat-logo/22fe4864/app-22fe4864-bc1e-403f-90bc-ad790bfc1eab-1490871347.png
         * bank_account_no : 6228***********9116
         * platform_name : 玉米理财
         * platform_user_name : 13426178576
         * invest_bids : 2
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/c3ce590a/app-c3ce590a-f2eb-4a37-83f7-c1234e5f681c-1486633701.jpg
         * platform_id : 71
         * total_asset : 4
         * balance : 3.4
         */

        private String bank_name;
        private String platform_short_name;
        private String is_enjoy_bonus;
        private String register_time;
        private String receiving_principal_interest;
        private String is_favorite_platform;
        private String platform_app_logo;
        private String bank_account_no;
        private String platform_name;
        private String platform_user_name;
        private String invest_bids;
        private String platform_logo;
        private int platform_id;
        private String total_asset;
        private String balance;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getPlatform_short_name() {
            return platform_short_name;
        }

        public void setPlatform_short_name(String platform_short_name) {
            this.platform_short_name = platform_short_name;
        }

        public String getIs_enjoy_bonus() {
            return is_enjoy_bonus;
        }

        public void setIs_enjoy_bonus(String is_enjoy_bonus) {
            this.is_enjoy_bonus = is_enjoy_bonus;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public String getReceiving_principal_interest() {
            return receiving_principal_interest;
        }

        public void setReceiving_principal_interest(String receiving_principal_interest) {
            this.receiving_principal_interest = receiving_principal_interest;
        }

        public String getIs_favorite_platform() {
            return is_favorite_platform;
        }

        public void setIs_favorite_platform(String is_favorite_platform) {
            this.is_favorite_platform = is_favorite_platform;
        }

        public String getPlatform_app_logo() {
            return platform_app_logo;
        }

        public void setPlatform_app_logo(String platform_app_logo) {
            this.platform_app_logo = platform_app_logo;
        }

        public String getBank_account_no() {
            return bank_account_no;
        }

        public void setBank_account_no(String bank_account_no) {
            this.bank_account_no = bank_account_no;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getPlatform_user_name() {
            return platform_user_name;
        }

        public void setPlatform_user_name(String platform_user_name) {
            this.platform_user_name = platform_user_name;
        }

        public String getInvest_bids() {
            return invest_bids;
        }

        public void setInvest_bids(String invest_bids) {
            this.invest_bids = invest_bids;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getTotal_asset() {
            return total_asset;
        }

        public void setTotal_asset(String total_asset) {
            this.total_asset = total_asset;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
