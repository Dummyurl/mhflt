package com.changju.fanlitou.bean.discover.platformbid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/6/22.
 */

public class DiscoverPlatformBidInsurance extends IDiscoverPlatformBid{

    /**
     * count : 1
     * platform_bids : [{"sub_name":"贴身守护出境人员，24小时全球保障","bid_name":"最惠全球旅行险","price":"80","bid_id":44,"is_promotion":false,"product_img_url":"http://img1.renrenbx.com/2016/10/14/2854e20ff83f210932af2985356b34e4.jpg","original_bonus_info":{"is_show":false,"original_bonus_value":1},"platform_name":"人人保险","bonus_interest":"0","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"insurance"}]
     */

    private int count;
    private List<PlatformBidsBean> platform_bids;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PlatformBidsBean> getPlatform_bids() {
        return platform_bids;
    }

    public void setPlatform_bids(List<PlatformBidsBean> platform_bids) {
        this.platform_bids = platform_bids;
    }

    public static class PlatformBidsBean implements Serializable {
        /**
         * sub_name : 贴身守护出境人员，24小时全球保障
         * bid_name : 最惠全球旅行险
         * price : 80
         * bid_id : 44
         * is_promotion : false
         * product_img_url : http://img1.renrenbx.com/2016/10/14/2854e20ff83f210932af2985356b34e4.jpg
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         * platform_name : 人人保险
         * bonus_interest : 0
         * tag_list : [{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}]
         * type : insurance
         */

        private String sub_name;
        private String bid_name;
        private String price;
        private int bid_id;
        private boolean is_promotion;
        private String product_img_url;
        private OriginalBonusInfoBean original_bonus_info;
        private String platform_name;
        private String bonus_interest;
        private String type;
        private List<TagListBean> tag_list;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public boolean isIs_promotion() {
            return is_promotion;
        }

        public void setIs_promotion(boolean is_promotion) {
            this.is_promotion = is_promotion;
        }

        public String getProduct_img_url() {
            return product_img_url;
        }

        public void setProduct_img_url(String product_img_url) {
            this.product_img_url = product_img_url;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class OriginalBonusInfoBean implements Serializable{
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

        public static class TagListBean implements Serializable{
            /**
             * style : red
             * name : 新手标
             */

            private String style;
            private String name;

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
