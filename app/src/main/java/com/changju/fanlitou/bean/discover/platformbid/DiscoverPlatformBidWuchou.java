package com.changju.fanlitou.bean.discover.platformbid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/6/22.
 */

public class DiscoverPlatformBidWuchou extends IDiscoverPlatformBid{


    /**
     * count : 5
     * platform_bids : [{"max_duration":90,"max_interest":"0","id_no_limit":true,"bid_name":"【null】第M122817155419期 奥迪SUV","bid_progress_percent":40,"duration_unit":"天","min_interest":"8","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"bid_id":89,"min_duration":1,"platform_name":"维C理财","is_promotion":false,"product_img_url":"https://dn-weiclicai.qbox.me/594794e311f1f.jpg","type":"wuchou","bonus_interest":"1","is_new_user":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1},"is_daily_bonus":true,"total_amount":"5,000"},{"max_duration":90,"max_interest":"13","id_no_limit":false,"bid_name":"【null】第Y065期 奥迪SUV","bid_progress_percent":30,"duration_unit":"天","min_interest":"10","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"bid_id":87,"min_duration":1,"platform_name":"维C理财","is_promotion":false,"product_img_url":"https://dn-weiclicai.qbox.me/594794e311f1f.jpg","type":"wuchou","bonus_interest":"1","is_new_user":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1},"is_daily_bonus":true,"total_amount":"1万"},{"max_duration":90,"max_interest":"0","id_no_limit":true,"bid_name":"【天津01】第M1605期 奔驰Mini","bid_progress_percent":15,"duration_unit":"天","min_interest":"8","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"bid_id":86,"min_duration":1,"platform_name":"维C理财","is_promotion":false,"product_img_url":"https://dn-weiclicai.qbox.me/594794e311f1f.jpg","type":"wuchou","bonus_interest":"1","is_new_user":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1},"is_daily_bonus":true,"total_amount":"10万"},{"max_duration":30,"max_interest":"8","id_no_limit":false,"bid_name":"【天津03】第C074期 奥迪SUV","bid_progress_percent":6,"duration_unit":"天","min_interest":"8","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"bid_id":88,"min_duration":1,"platform_name":"维C理财","is_promotion":false,"product_img_url":"https://dn-weiclicai.qbox.me/594794e311f1f.jpg","type":"wuchou","bonus_interest":"1","is_new_user":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1},"is_daily_bonus":true,"total_amount":"5万"},{"max_duration":90,"max_interest":"0","id_no_limit":true,"bid_name":"【长沙34】第M5143期 英菲尼迪Q50L","bid_progress_percent":6,"duration_unit":"天","min_interest":"8","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"bid_id":61,"min_duration":1,"platform_name":"维C理财","is_promotion":false,"product_img_url":"https://dn-weiclicai.qbox.me/594794e311f1f.jpg","type":"wuchou","bonus_interest":"1","is_new_user":false,"original_bonus_info":{"is_show":false,"original_bonus_value":1},"is_daily_bonus":true,"total_amount":"10万"}]
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
         * max_duration : 90
         * max_interest : 0
         * id_no_limit : true
         * bid_name : 【null】第M122817155419期 奥迪SUV
         * bid_progress_percent : 40
         * duration_unit : 天
         * min_interest : 8
         * tag_list : [{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}]
         * bid_id : 89
         * min_duration : 1
         * platform_name : 维C理财
         * is_promotion : false
         * product_img_url : https://dn-weiclicai.qbox.me/594794e311f1f.jpg
         * type : wuchou
         * bonus_interest : 1
         * is_new_user : false
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         * is_daily_bonus : true
         * total_amount : 5,000
         */

        private String max_duration;
        private String max_interest;
        private boolean id_no_limit;
        private String bid_name;
        private float bid_progress_percent;
        private String duration_unit;
        private String min_interest;
        private int bid_id;
        private String min_duration;
        private String platform_name;
        private boolean is_promotion;
        private String product_img_url;
        private String type;
        private String bonus_interest;
        private boolean is_new_user;
        private OriginalBonusInfoBean original_bonus_info;
        private boolean is_daily_bonus;
        private String total_amount;
        private List<TagListBean> tag_list;

        public String getMax_duration() {
            return max_duration;
        }

        public void setMax_duration(String max_duration) {
            this.max_duration = max_duration;
        }

        public String getMax_interest() {
            return max_interest;
        }

        public void setMax_interest(String max_interest) {
            this.max_interest = max_interest;
        }

        public boolean isId_no_limit() {
            return id_no_limit;
        }

        public void setId_no_limit(boolean id_no_limit) {
            this.id_no_limit = id_no_limit;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public float getBid_progress_percent() {
            return bid_progress_percent;
        }

        public void setBid_progress_percent(float bid_progress_percent) {
            this.bid_progress_percent = bid_progress_percent;
        }

        public String getDuration_unit() {
            return duration_unit;
        }

        public void setDuration_unit(String duration_unit) {
            this.duration_unit = duration_unit;
        }

        public String getMin_interest() {
            return min_interest;
        }

        public void setMin_interest(String min_interest) {
            this.min_interest = min_interest;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getMin_duration() {
            return min_duration;
        }

        public void setMin_duration(String min_duration) {
            this.min_duration = min_duration;
        }

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

        public String getProduct_img_url() {
            return product_img_url;
        }

        public void setProduct_img_url(String product_img_url) {
            this.product_img_url = product_img_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        public boolean isIs_new_user() {
            return is_new_user;
        }

        public void setIs_new_user(boolean is_new_user) {
            this.is_new_user = is_new_user;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public boolean isIs_daily_bonus() {
            return is_daily_bonus;
        }

        public void setIs_daily_bonus(boolean is_daily_bonus) {
            this.is_daily_bonus = is_daily_bonus;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
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
