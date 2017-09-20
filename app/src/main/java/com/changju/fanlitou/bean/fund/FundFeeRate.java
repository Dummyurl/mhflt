package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/7/24.
 */

public class FundFeeRate {

    /**
     * status : 1
     * apply_data : [{"apply_min":"0","fanlitou_rate":"1.50%","original_rate":"1.20%","apply_max":"100万元"},{"apply_min":"100万元","fanlitou_rate":"1.50%","original_rate":"1.00%","apply_max":"300万元"},{"apply_min":"300万元","fanlitou_rate":"1.50%","original_rate":"0.60%","apply_max":"500万元"},{"apply_min":"500万元","fanlitou_rate":"1.50%","original_rate":"0.30%","apply_max":"1000万元"},{"apply_min":"1000万元","fanlitou_rate":"1.50%","original_rate":"1000元/笔","apply_max":"无限"}]
     * other_data : {"data_list":[{"redeem_rate":"1.50","name":"管理费率"},{"redeem_rate":"1.50","name":"托管费率"}]}
     * redeem_data : {"data_list":[{"redeem_rate":"1.50%","hold_duration":"持有年限<15日"},{"redeem_rate":"0.75%","hold_duration":"15日≤持有年限<30日"},{"redeem_rate":"0.50%","hold_duration":"30日≤持有年限<365日"},{"redeem_rate":"0.10%","hold_duration":"1年≤持有年限<2年"},{"redeem_rate":"0.00%","hold_duration":"持有年限≥2年"}]}
     * subscribe_data : {"data_list":[{"pert_val_max":"100万元","fanlitou_rate":"1.50%","original_rate":"1000元/笔","pert_val_min":"0"},{"pert_val_max":"300万元","fanlitou_rate":"1.50%","original_rate":"1000元/笔","pert_val_min":"100万元"},{"pert_val_max":"500万元","fanlitou_rate":"1.50%","original_rate":"1000元/笔","pert_val_min":"300万元"},{"pert_val_max":"1000万元","fanlitou_rate":"1.50%","original_rate":"1000元/笔","pert_val_min":"500万元"},{"pert_val_max":"无限","fanlitou_rate":"1.50%","original_rate":"1000元/笔","pert_val_min":"1000万元"}]}
     */

    private int status;
    private OtherDataBean other_data;
    private RedeemDataBean redeem_data;
    private SubscribeDataBean subscribe_data;
    private List<ApplyDataBean> apply_data;

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

    public List<ApplyDataBean> getApply_data() {
        return apply_data;
    }

    public void setApply_data(List<ApplyDataBean> apply_data) {
        this.apply_data = apply_data;
    }

    public static class OtherDataBean {
        private List<DataListBean> data_list;

        public List<DataListBean> getData_list() {
            return data_list;
        }

        public void setData_list(List<DataListBean> data_list) {
            this.data_list = data_list;
        }

        public static class DataListBean {
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

    public static class RedeemDataBean {
        private List<DataListBeanX> data_list;

        public List<DataListBeanX> getData_list() {
            return data_list;
        }

        public void setData_list(List<DataListBeanX> data_list) {
            this.data_list = data_list;
        }

        public static class DataListBeanX {
            /**
             * redeem_rate : 1.50%
             * hold_duration : 持有年限<15日
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

    public static class SubscribeDataBean {
        private List<DataListBeanXX> data_list;

        public List<DataListBeanXX> getData_list() {
            return data_list;
        }

        public void setData_list(List<DataListBeanXX> data_list) {
            this.data_list = data_list;
        }

        public static class DataListBeanXX {
            /**
             * pert_val_max : 100万元
             * fanlitou_rate : 1.50%
             * original_rate : 1000元/笔
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

    public static class ApplyDataBean {
        /**
         * apply_min : 0
         * fanlitou_rate : 1.50%
         * original_rate : 1.20%
         * apply_max : 100万元
         */

        private String apply_min;
        private String fanlitou_rate;
        private String original_rate;
        private String apply_max;

        public String getApply_min() {
            return apply_min;
        }

        public void setApply_min(String apply_min) {
            this.apply_min = apply_min;
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

        public String getApply_max() {
            return apply_max;
        }

        public void setApply_max(String apply_max) {
            this.apply_max = apply_max;
        }
    }
}
