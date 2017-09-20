package com.changju.fanlitou.bean.intelligent;

/**
 * Created by chengww on 2017/7/13.
 */

public class WithdrawResult {

    /**
     * status : FAIL
     * bound_bank_info : {"bank_name":"中国建设银行","card_no":"6228***********9116","bank_id":"CCB","bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/f27603aa/bank-f27603aa-6712-4f2a-a698-be59abf6b9ad-1480416213.png"}
     * withdraw_success_days : 0
     * success : false
     * msg : 充值金额不能小于50元
     * order_number : 1762937715246936
     * withdraw_amount : 0.01
     * platform_info : {"platform_id":71,"platform_name":"玉米理财","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/417e338f/main-417e338f-f413-402e-902c-88ea9a1b495f-1486633700.jpg","platform_shortname":"yumilc"}
     */

    private String status;
    private BoundBankInfoBean bound_bank_info;
    private int withdraw_success_days;
    private boolean success;
    private String msg;
    private String order_number;
    private String withdraw_amount;
    private PlatformInfoBean platform_info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BoundBankInfoBean getBound_bank_info() {
        return bound_bank_info;
    }

    public void setBound_bank_info(BoundBankInfoBean bound_bank_info) {
        this.bound_bank_info = bound_bank_info;
    }

    public int getWithdraw_success_days() {
        return withdraw_success_days;
    }

    public void setWithdraw_success_days(int withdraw_success_days) {
        this.withdraw_success_days = withdraw_success_days;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getWithdraw_amount() {
        return withdraw_amount;
    }

    public void setWithdraw_amount(String withdraw_amount) {
        this.withdraw_amount = withdraw_amount;
    }

    public PlatformInfoBean getPlatform_info() {
        return platform_info;
    }

    public void setPlatform_info(PlatformInfoBean platform_info) {
        this.platform_info = platform_info;
    }

    public static class BoundBankInfoBean {
        /**
         * bank_name : 中国建设银行
         * card_no : 6228***********9116
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
         * platform_id : 71
         * platform_name : 玉米理财
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/417e338f/main-417e338f-f413-402e-902c-88ea9a1b495f-1486633700.jpg
         * platform_shortname : yumilc
         */

        private int platform_id;
        private String platform_name;
        private String platform_logo;
        private String platform_shortname;

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

        public String getPlatform_shortname() {
            return platform_shortname;
        }

        public void setPlatform_shortname(String platform_shortname) {
            this.platform_shortname = platform_shortname;
        }
    }
}
