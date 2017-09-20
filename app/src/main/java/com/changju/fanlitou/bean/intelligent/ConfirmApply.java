package com.changju.fanlitou.bean.intelligent;

import java.util.List;

/**
 * Created by chengww on 2017/7/8.
 */

public class ConfirmApply {

    /**
     * account_balance : 3.40
     * account_phone_number : 13426178576
     * agreement_list : [{"content":"","url":"https://lc.gaosouyi.com/Public/contract_jiekuan?id=154392","display_type":"url","title":"《借款合同》"}]
     * bid_detail_info : {"pay_type_str":"一次性还本付息","bid_interest":500,"bid_id":"346598","is_assign_bid":false,"duration_unit_str":"天","min_invest_amount":"50.00","duration":30,"remain_amount":"0.10","bid_name":"返利投测试专属产品3","max_invest_amount":"0.10","bid_type":"p2p","bonus_interest":1.2,"original_bonus_info":{"is_show":false,"original_bonus_value":1}}
     * is_need_invest_sms_code : true
     * is_need_user_agreement : true
     * platform_info : {"platform_id":71,"platform_name":"玉米理财","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/417e338f/main-417e338f-f413-402e-902c-88ea9a1b495f-1486633700.jpg","platform_bank_interface_type":"quick_payment"}
     */

    private String account_balance;
    private String account_phone_number;
    private BidDetailInfoBean bid_detail_info;
    private boolean is_need_invest_sms_code;
    private boolean is_need_user_agreement;
    private PlatformInfoBean platform_info;
    private List<AgreementListBean> agreement_list;

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getAccount_phone_number() {
        return account_phone_number;
    }

    public void setAccount_phone_number(String account_phone_number) {
        this.account_phone_number = account_phone_number;
    }

    public BidDetailInfoBean getBid_detail_info() {
        return bid_detail_info;
    }

    public void setBid_detail_info(BidDetailInfoBean bid_detail_info) {
        this.bid_detail_info = bid_detail_info;
    }

    public boolean isIs_need_invest_sms_code() {
        return is_need_invest_sms_code;
    }

    public void setIs_need_invest_sms_code(boolean is_need_invest_sms_code) {
        this.is_need_invest_sms_code = is_need_invest_sms_code;
    }

    public boolean isIs_need_user_agreement() {
        return is_need_user_agreement;
    }

    public void setIs_need_user_agreement(boolean is_need_user_agreement) {
        this.is_need_user_agreement = is_need_user_agreement;
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

    public static class BidDetailInfoBean {
        /**
         * pay_type_str : 一次性还本付息
         * bid_interest : 500
         * bid_id : 346598
         * is_assign_bid : false
         * duration_unit_str : 天
         * min_invest_amount : 50.00
         * duration : 30
         * remain_amount : 0.10
         * bid_name : 返利投测试专属产品3
         * max_invest_amount : 0.10
         * bid_type : p2p
         * bonus_interest : 1.2
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         */

        private String pay_type_str;
        private String bid_interest;
        private String bid_id;
        private boolean is_assign_bid;
        private String duration_unit_str;
        private String min_invest_amount;
        private String duration;
        private String remain_amount;
        private String bid_name;
        private String max_invest_amount;
        private String bid_type;
        private String bonus_interest;
        private OriginalBonusInfoBean original_bonus_info;

        public String getPay_type_str() {
            return pay_type_str;
        }

        public void setPay_type_str(String pay_type_str) {
            this.pay_type_str = pay_type_str;
        }

        public String getBid_interest() {
            return bid_interest;
        }

        public void setBid_interest(String bid_interest) {
            this.bid_interest = bid_interest;
        }

        public String getBid_id() {
            return bid_id;
        }

        public void setBid_id(String bid_id) {
            this.bid_id = bid_id;
        }

        public boolean isIs_assign_bid() {
            return is_assign_bid;
        }

        public void setIs_assign_bid(boolean is_assign_bid) {
            this.is_assign_bid = is_assign_bid;
        }

        public String getDuration_unit_str() {
            return duration_unit_str;
        }

        public void setDuration_unit_str(String duration_unit_str) {
            this.duration_unit_str = duration_unit_str;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getRemain_amount() {
            return remain_amount;
        }

        public void setRemain_amount(String remain_amount) {
            this.remain_amount = remain_amount;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public String getMax_invest_amount() {
            return max_invest_amount;
        }

        public void setMax_invest_amount(String max_invest_amount) {
            this.max_invest_amount = max_invest_amount;
        }

        public String getBid_type() {
            return bid_type;
        }

        public void setBid_type(String bid_type) {
            this.bid_type = bid_type;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public static class OriginalBonusInfoBean {
            /**
             * is_show : false
             * original_bonus_value : 1
             */

            private boolean is_show;
            private String original_bonus_value;

            public boolean isIs_show() {
                return is_show;
            }

            public void setIs_show(boolean is_show) {
                this.is_show = is_show;
            }

            public String getOriginal_bonus_value() {
                return original_bonus_value;
            }

            public void setOriginal_bonus_value(String original_bonus_value) {
                this.original_bonus_value = original_bonus_value;
            }
        }
    }

    public static class PlatformInfoBean {
        /**
         * platform_id : 71
         * platform_name : 玉米理财
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/417e338f/main-417e338f-f413-402e-902c-88ea9a1b495f-1486633700.jpg
         * platform_bank_interface_type : quick_payment
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
         * content :
         * url : https://lc.gaosouyi.com/Public/contract_jiekuan?id=154392
         * display_type : url
         * title : 《借款合同》
         */

        private String content;
        private String url;
        private String display_type;
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDisplay_type() {
            return display_type;
        }

        public void setDisplay_type(String display_type) {
            this.display_type = display_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
