package com.changju.fanlitou.bean.discover;

import java.util.List;

/**
 * Created by zm on 2017/5/23.
 */

public class DiscoverBidCrowdfunding {


    /**
     * total : 61
     * bid_list : [{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704181759048.jpg","bid_name":"乡志·圣水鸣琴","bid_progress_percent":26,"remain_time":"25天","bid_id":46,"min_invest_amount":"5,000","original_bonus_info":{"is_show":true,"original_bonus_value":1},"status_str":"预约中","total_amount":"200万","platform_name":"多彩投","bonus_interest":"50","type":"crowdfunding"}]
     */

    private String total;
    private List<BidListBean> bid_list;

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
         * product_img_url : https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704181759048.jpg
         * bid_name : 乡志·圣水鸣琴
         * bid_progress_percent : 26
         * remain_time : 25天
         * bid_id : 46
         * min_invest_amount : 5,000
         * original_bonus_info : {"is_show":true,"original_bonus_value":1}
         * status_str : 预约中
         * total_amount : 200万
         * platform_name : 多彩投
         * bonus_interest : 50
         * type : crowdfunding
         */

        private String product_img_url;
        private String bid_name;
        //未做修改
        private float bid_progress_percent;
        private String remain_time;
        private int bid_id;
        private String min_invest_amount;
        private OriginalBonusInfoBean original_bonus_info;
        private String status_str;
        private String total_amount;
        private String platform_name;
        private String bonus_interest;
        private String type;

        //wuchou添加的字段
        private String min_interest;
        private String max_interest;
        private int min_duration;
        private int max_duration;
        private String duration_unit;

        public String getMin_interest() {
            return min_interest;
        }

        public void setMin_interest(String min_interest) {
            this.min_interest = min_interest;
        }

        public String getMax_interest() {
            return max_interest;
        }

        public void setMax_interest(String max_interest) {
            this.max_interest = max_interest;
        }

        public int getMin_duration() {
            return min_duration;
        }

        public void setMin_duration(int min_duration) {
            this.min_duration = min_duration;
        }

        public int getMax_duration() {
            return max_duration;
        }

        public void setMax_duration(int max_duration) {
            this.max_duration = max_duration;
        }

        public String getDuration_unit() {
            return duration_unit;
        }

        public void setDuration_unit(String duration_unit) {
            this.duration_unit = duration_unit;
        }

        public String getProduct_img_url() {
            return product_img_url;
        }

        public void setProduct_img_url(String product_img_url) {
            this.product_img_url = product_img_url;
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

        public String getRemain_time() {
            return remain_time;
        }

        public void setRemain_time(String remain_time) {
            this.remain_time = remain_time;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public OriginalBonusInfoBean getOriginal_bonus_info() {
            return original_bonus_info;
        }

        public void setOriginal_bonus_info(OriginalBonusInfoBean original_bonus_info) {
            this.original_bonus_info = original_bonus_info;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
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
