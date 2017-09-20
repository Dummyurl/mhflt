package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/12.
 */

public class ConfirmWithdraw {

    /**
     * platform_withdraw_rule : 含手续费0元(免费提现不包含充值未投资金额), 具体费用以平台实际确认为准
     * account_balance : 10000.00
     * bound_bank_info : {"bank_name":"建设银行","card_no":"6227***********8219","bank_id":"CCB","bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/f27603aa/bank-f27603aa-6712-4f2a-a698-be59abf6b9ad-1480416213.png"}
     * is_need_user_agreement : false
     * bank_phone_number : 15400000067
     * is_open_new_tab : true
     * expect_withdraw_fees : 0
     * account_phone_number : 15400000067
     * remain_free_withdraw_times : 0
     * min_withdraw_amount : 100
     * withdraw_success_days : 1
     * is_need_withdraw_sms_code : false
     * platform_info : {"platform_id":78,"platform_name":"高搜易","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png","platform_bank_interface_type":"bank_storage"}
     */

    private String platform_withdraw_rule;
    private String account_balance;
    private BoundBankInfoBean bound_bank_info;
    private boolean is_need_user_agreement;
    private String bank_phone_number;
    private boolean is_open_new_tab;
    private String expect_withdraw_fees;
    private String account_phone_number;
    private String remain_free_withdraw_times;
    private String min_withdraw_amount;
    private int withdraw_success_days;
    private boolean is_need_withdraw_sms_code;
    private PlatformInfoBean platform_info;
    private boolean is_need_bank_phone_number;

    public boolean is_need_bank_phone_number() {
        return is_need_bank_phone_number;
    }

    public void setIs_need_bank_phone_number(boolean is_need_bank_phone_number) {
        this.is_need_bank_phone_number = is_need_bank_phone_number;
    }

    public String getPlatform_withdraw_rule() {
        return platform_withdraw_rule;
    }

    public void setPlatform_withdraw_rule(String platform_withdraw_rule) {
        this.platform_withdraw_rule = platform_withdraw_rule;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public BoundBankInfoBean getBound_bank_info() {
        return bound_bank_info;
    }

    public void setBound_bank_info(BoundBankInfoBean bound_bank_info) {
        this.bound_bank_info = bound_bank_info;
    }

    public boolean isIs_need_user_agreement() {
        return is_need_user_agreement;
    }

    public void setIs_need_user_agreement(boolean is_need_user_agreement) {
        this.is_need_user_agreement = is_need_user_agreement;
    }

    public String getBank_phone_number() {
        return bank_phone_number;
    }

    public void setBank_phone_number(String bank_phone_number) {
        this.bank_phone_number = bank_phone_number;
    }

    public boolean isIs_open_new_tab() {
        return is_open_new_tab;
    }

    public void setIs_open_new_tab(boolean is_open_new_tab) {
        this.is_open_new_tab = is_open_new_tab;
    }

    public String getExpect_withdraw_fees() {
        return expect_withdraw_fees;
    }

    public void setExpect_withdraw_fees(String expect_withdraw_fees) {
        this.expect_withdraw_fees = expect_withdraw_fees;
    }

    public String getAccount_phone_number() {
        return account_phone_number;
    }

    public void setAccount_phone_number(String account_phone_number) {
        this.account_phone_number = account_phone_number;
    }

    public String getRemain_free_withdraw_times() {
        return remain_free_withdraw_times;
    }

    public void setRemain_free_withdraw_times(String remain_free_withdraw_times) {
        this.remain_free_withdraw_times = remain_free_withdraw_times;
    }

    public String getMin_withdraw_amount() {
        return min_withdraw_amount;
    }

    public void setMin_withdraw_amount(String min_withdraw_amount) {
        this.min_withdraw_amount = min_withdraw_amount;
    }

    public int getWithdraw_success_days() {
        return withdraw_success_days;
    }

    public void setWithdraw_success_days(int withdraw_success_days) {
        this.withdraw_success_days = withdraw_success_days;
    }

    public boolean isIs_need_withdraw_sms_code() {
        return is_need_withdraw_sms_code;
    }

    public void setIs_need_withdraw_sms_code(boolean is_need_withdraw_sms_code) {
        this.is_need_withdraw_sms_code = is_need_withdraw_sms_code;
    }

    public PlatformInfoBean getPlatform_info() {
        return platform_info;
    }

    public void setPlatform_info(PlatformInfoBean platform_info) {
        this.platform_info = platform_info;
    }

    public static class BoundBankInfoBean {
        /**
         * bank_name : 建设银行
         * card_no : 6227***********8219
         * bank_id : CCB
         * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/f27603aa/bank-f27603aa-6712-4f2a-a698-be59abf6b9ad-1480416213.png
         */

        private String bank_name;
        private String card_no;
        private String bank_id;
        private String bank_logo;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }
    }

    public static class PlatformInfoBean {
        /**
         * platform_id : 78
         * platform_name : 高搜易
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png
         * platform_bank_interface_type : bank_storage
         */

        private int platform_id;
        private String platform_name;
        private String platform_logo;
        private String platform_bank_interface_type;

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public String getPlatform_bank_interface_type() {
            return platform_bank_interface_type;
        }

        public void setPlatform_bank_interface_type(String platform_bank_interface_type) {
            this.platform_bank_interface_type = platform_bank_interface_type;
        }
    }
}
