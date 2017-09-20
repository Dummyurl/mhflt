package com.changju.fanlitou.bean.fund;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class FundRedeemBean implements Serializable{


    /**
     * min_redem_share : 0.01
     * card_num : 6217***********7133
     * bank_name : 建设银行
     * fund_name : 前海开源周期优选混合C
     * max_redem_share : 0.00
     * quarter_redem_share : 0.00
     * fee_info : {"status":1,"apply_data":[],"other_data":{"data_list":[{"redeem_rate":"1.50","name":"管理费率"},{"redeem_rate":"0.25","name":"托管费率"}]},"redeem_data":{"data_list":[{"redeem_rate":"0.50%","hold_duration":"期限<30日"},{"redeem_rate":"0.00%","hold_duration":"期限≥30日"}]},"subscribe_data":{"data_list":[{"pert_val_max":"50万元","fanlitou_rate":"1.50%","original_rate":"1.50%","pert_val_min":"0"},{"pert_val_max":"250万元","fanlitou_rate":"1.00%","original_rate":"1.00%","pert_val_min":"50万元"},{"pert_val_max":"500万元","fanlitou_rate":"0.60%","original_rate":"0.60%","pert_val_min":"250万元"},{"pert_val_max":"无限","fanlitou_rate":"1000元/笔","original_rate":"1000元/笔","pert_val_min":"500万元"}]}}
     * fee : 0.50%
     * min_hold : 0.00
     * half_redem_share : 0.00
     * fund_code : 003858
     * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/f27603aa/bank-f27603aa-6712-4f2a-a698-be59abf6b9ad-1480416213.png
     * fund_id : 3774
     */

    private String min_redem_share;
    private String card_num;
    private String bank_name;
    private String fund_name;
    private String max_redem_share;
    private String quarter_redem_share;
    private FeeInfoBean fee_info;
    private String fee;
    private String min_hold;
    private String half_redem_share;
    private String fund_code;
    private String bank_logo;
    private int fund_id;

    public String getMin_redem_share() {
        return min_redem_share;
    }

    public void setMin_redem_share(String min_redem_share) {
        this.min_redem_share = min_redem_share;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getMax_redem_share() {
        return max_redem_share;
    }

    public void setMax_redem_share(String max_redem_share) {
        this.max_redem_share = max_redem_share;
    }

    public String getQuarter_redem_share() {
        return quarter_redem_share;
    }

    public void setQuarter_redem_share(String quarter_redem_share) {
        this.quarter_redem_share = quarter_redem_share;
    }

    public FeeInfoBean getFee_info() {
        return fee_info;
    }

    public void setFee_info(FeeInfoBean fee_info) {
        this.fee_info = fee_info;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getMin_hold() {
        return min_hold;
    }

    public void setMin_hold(String min_hold) {
        this.min_hold = min_hold;
    }

    public String getHalf_redem_share() {
        return half_redem_share;
    }

    public void setHalf_redem_share(String half_redem_share) {
        this.half_redem_share = half_redem_share;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public String getBank_logo() {
        return bank_logo;
    }

    public void setBank_logo(String bank_logo) {
        this.bank_logo = bank_logo;
    }

    public int getFund_id() {
        return fund_id;
    }

    public void setFund_id(int fund_id) {
        this.fund_id = fund_id;
    }

    public static class FeeInfoBean implements Serializable{
        /**
         * status : 1
         * apply_data : []
         * other_data : {"data_list":[{"redeem_rate":"1.50","name":"管理费率"},{"redeem_rate":"0.25","name":"托管费率"}]}
         * redeem_data : {"data_list":[{"redeem_rate":"0.50%","hold_duration":"期限<30日"},{"redeem_rate":"0.00%","hold_duration":"期限≥30日"}]}
         * subscribe_data : {"data_list":[{"pert_val_max":"50万元","fanlitou_rate":"1.50%","original_rate":"1.50%","pert_val_min":"0"},{"pert_val_max":"250万元","fanlitou_rate":"1.00%","original_rate":"1.00%","pert_val_min":"50万元"},{"pert_val_max":"500万元","fanlitou_rate":"0.60%","original_rate":"0.60%","pert_val_min":"250万元"},{"pert_val_max":"无限","fanlitou_rate":"1000元/笔","original_rate":"1000元/笔","pert_val_min":"500万元"}]}
         */

        private int status;
        private OtherDataBean other_data;
        private RedeemDataBean redeem_data;
        private SubscribeDataBean subscribe_data;
        private List<?> apply_data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public OtherDataBean getOther_data() {
            return other_data;
        }

        public void setOther_data(OtherDataBean other_data) {
            this.other_data = other_data;
        }

        public RedeemDataBean getRedeem_data() {
            return redeem_data;
        }

        public void setRedeem_data(RedeemDataBean redeem_data) {
            this.redeem_data = redeem_data;
        }

        public SubscribeDataBean getSubscribe_data() {
            return subscribe_data;
        }

        public void setSubscribe_data(SubscribeDataBean subscribe_data) {
            this.subscribe_data = subscribe_data;
        }

        public List<?> getApply_data() {
            return apply_data;
        }

        public void setApply_data(List<?> apply_data) {
            this.apply_data = apply_data;
        }

        public static class OtherDataBean implements Serializable{
            private List<DataListBean> data_list;

            public List<DataListBean> getData_list() {
                return data_list;
            }

            public void setData_list(List<DataListBean> data_list) {
                this.data_list = data_list;
            }

            public static class DataListBean implements Serializable{
                /**
                 * redeem_rate : 1.50
                 * name : 管理费率
                 */

                private String redeem_rate;
                private String name;

                public String getRedeem_rate() {
                    return redeem_rate;
                }

                public void setRedeem_rate(String redeem_rate) {
                    this.redeem_rate = redeem_rate;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class RedeemDataBean implements Serializable{
            private List<DataListBeanX> data_list;

            public List<DataListBeanX> getData_list() {
                return data_list;
            }

            public void setData_list(List<DataListBeanX> data_list) {
                this.data_list = data_list;
            }

            public static class DataListBeanX implements Serializable{
                /**
                 * redeem_rate : 0.50%
                 * hold_duration : 期限<30日
                 */

                private String redeem_rate;
                private String hold_duration;

                public String getRedeem_rate() {
                    return redeem_rate;
                }

                public void setRedeem_rate(String redeem_rate) {
                    this.redeem_rate = redeem_rate;
                }

                public String getHold_duration() {
                    return hold_duration;
                }

                public void setHold_duration(String hold_duration) {
                    this.hold_duration = hold_duration;
                }
            }
        }

        public static class SubscribeDataBean implements Serializable{
            private List<DataListBeanXX> data_list;

            public List<DataListBeanXX> getData_list() {
                return data_list;
            }

            public void setData_list(List<DataListBeanXX> data_list) {
                this.data_list = data_list;
            }

            public static class DataListBeanXX implements Serializable{
                /**
                 * pert_val_max : 50万元
                 * fanlitou_rate : 1.50%
                 * original_rate : 1.50%
                 * pert_val_min : 0
                 */

                private String pert_val_max;
                private String fanlitou_rate;
                private String original_rate;
                private String pert_val_min;

                public String getPert_val_max() {
                    return pert_val_max;
                }

                public void setPert_val_max(String pert_val_max) {
                    this.pert_val_max = pert_val_max;
                }

                public String getFanlitou_rate() {
                    return fanlitou_rate;
                }

                public void setFanlitou_rate(String fanlitou_rate) {
                    this.fanlitou_rate = fanlitou_rate;
                }

                public String getOriginal_rate() {
                    return original_rate;
                }

                public void setOriginal_rate(String original_rate) {
                    this.original_rate = original_rate;
                }

                public String getPert_val_min() {
                    return pert_val_min;
                }

                public void setPert_val_min(String pert_val_min) {
                    this.pert_val_min = pert_val_min;
                }
            }
        }
    }
}
