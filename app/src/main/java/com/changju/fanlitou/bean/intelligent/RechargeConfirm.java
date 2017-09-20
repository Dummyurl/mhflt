package com.changju.fanlitou.bean.intelligent;

import java.util.List;

/**
 * Created by chengww on 2017/7/10.
 */

public class RechargeConfirm {

    /**
     * min_recharge_amount : 100
     * is_need_recharge_agreement : false
     * account_balance : 1990.00
     * bound_bank_info : {"bank_name":"中国农业银行","card_no":"6228***********9116","bank_id":"ABC","bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png"}
     * account_phone_number : 13426178576
     * is_need_recharge_sms_code : false
     * agreement_list : [{"content":"协议","title":"《充值协议》"}]
     * bank_phone_number : 13426178576
     * platform_info : {"platform_id":78,"platform_name":"高搜易","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png","platform_bank_interface_type":"bank_storage"}
     */

    private String min_recharge_amount;
    private boolean is_need_recharge_agreement;
    private String account_balance;
    private BoundBankInfoBean bound_bank_info;
    private String account_phone_number;
    private boolean is_need_recharge_sms_code;
    private String bank_phone_number;
    private PlatformInfoBean platform_info;
    private List<AgreementListBean> agreement_list;
    private boolean is_need_bank_phone_number;

    public boolean is_need_bank_phone_number() {
        return is_need_bank_phone_number;
    }

    public void setIs_need_bank_phone_number(boolean is_need_bank_phone_number) {
        this.is_need_bank_phone_number = is_need_bank_phone_number;
    }

    public String getMin_recharge_amount() {
        return min_recharge_amount;
    }

    public void setMin_recharge_amount(String min_recharge_amount) {
        this.min_recharge_amount = min_recharge_amount;
    }

    public boolean isIs_need_recharge_agreement() {
        return is_need_recharge_agreement;
    }

    public void setIs_need_recharge_agreement(boolean is_need_recharge_agreement) {
        this.is_need_recharge_agreement = is_need_recharge_agreement;
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

    public String getAccount_phone_number() {
        return account_phone_number;
    }

    public void setAccount_phone_number(String account_phone_number) {
        this.account_phone_number = account_phone_number;
    }

    public boolean isIs_need_recharge_sms_code() {
        return is_need_recharge_sms_code;
    }

    public void setIs_need_recharge_sms_code(boolean is_need_recharge_sms_code) {
        this.is_need_recharge_sms_code = is_need_recharge_sms_code;
    }

    public String getBank_phone_number() {
        return bank_phone_number;
    }

    public void setBank_phone_number(String bank_phone_number) {
        this.bank_phone_number = bank_phone_number;
    }

    public PlatformInfoBean getPlatform_info() {
        return platform_info;
    }

    public void setPlatform_info(PlatformInfoBean platform_info) {
        this.platform_info = platform_info;
    }

    public List<AgreementListBean> getAgreement_list() {
        return agreement_list;
    }

    public void setAgreement_list(List<AgreementListBean> agreement_list) {
        this.agreement_list = agreement_list;
    }

    public static class BoundBankInfoBean {
        /**
         * bank_name : 中国农业银行
         * card_no : 6228***********9116
         * bank_id : ABC
         * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png
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

    public static class AgreementListBean {
        /**
         * content : 协议
         * title : 《充值协议》
         */

        private String content;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
