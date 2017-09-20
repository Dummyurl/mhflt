package com.changju.fanlitou.bean.discover;

import java.util.List;

/**
 * Created by zm on 2017/5/23.
 */

public class DiscoverBidInsurance {


    /**
     * category : 6
     * total : 27
     * bid_list : [{"platform_name":"人人保险","is_promotion":false,"sub_name":"贴身守护出境人员，24小时全球保障","bid_name":"最惠全球旅行险","bonus_interest":"0","product_img_url":"http://img1.renrenbx.com/2016/10/14/2854e20ff83f210932af2985356b34e4.jpg","price":"80","type":"insurance","bid_id":44,"original_bonus_info":{"is_show":true,"original_bonus_value":1}}]
     */

    private String category;
    private String total;
    private List<BidListBean> bid_list;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<BidListBean> getBid_list() {
        return bid_list;
    }

    public void setBid_list(List<BidListBean> bid_list) {
        this.bid_list = bid_list;
    }

    public static class BidListBean {
        /**
         * platform_name : 人人保险
         * is_promotion : false
         * sub_name : 贴身守护出境人员，24小时全球保障
         * bid_name : 最惠全球旅行险
         * bonus_interest : 0
         * product_img_url : http://img1.renrenbx.com/2016/10/14/2854e20ff83f210932af2985356b34e4.jpg
         * price : 80
         * type : insurance
         * bid_id : 44
         * original_bonus_info : {"is_show":true,"original_bonus_value":1}
         */

        private String platform_name;
        private boolean is_promotion;
        private String sub_name;
        private String bid_name;
        private String bonus_interest;
        private String product_img_url;
        private String price;
        private String type;
        private int bid_id;
        private OriginalBonusInfoBean original_bonus_info;

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public boolean isIs_promotion() {
            return is_promotion;
        }

        public void setIs_promotion(boolean is_promotion) {
            this.is_promotion = is_promotion;
        }

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public String getProduct_img_url() {
            return product_img_url;
        }

        public void setProduct_img_url(String product_img_url) {
            this.product_img_url = product_img_url;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public static class OriginalBonusInfoBean {
            /**
             * is_show : true
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
}
