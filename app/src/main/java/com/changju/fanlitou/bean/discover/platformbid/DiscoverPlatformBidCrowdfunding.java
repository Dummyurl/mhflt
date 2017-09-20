package com.changju.fanlitou.bean.discover.platformbid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/6/22.
 */

public class DiscoverPlatformBidCrowdfunding extends IDiscoverPlatformBid{

    /**
     * count : 8
     * platform_bids : [{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704181759048.jpg","bid_name":"乡志·圣水鸣琴","bid_progress_percent":26,"remain_time":"25天","bid_id":46,"min_invest_amount":"5,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"预约中","total_amount":"200万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704211335478.jpg","bid_name":"如是书店","bid_progress_percent":310,"remain_time":"27天","bid_id":44,"min_invest_amount":"999","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"预约中","total_amount":"400万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704181257468.jpg","bid_name":"宁波泊宁酒店-东城店","bid_progress_percent":165,"remain_time":"24天","bid_id":45,"min_invest_amount":"20,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"预约中","total_amount":"400万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704131458062.jpg","bid_name":"投资训练营 第3季","bid_progress_percent":570,"remain_time":"4天","bid_id":42,"min_invest_amount":"1,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"众筹中","total_amount":"1万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704111059556.jpg","bid_name":"拉萨三学居","bid_progress_percent":66,"remain_time":"3天","bid_id":40,"min_invest_amount":"3,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"众筹中","total_amount":"60万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/2017041711234310.jpg","bid_name":"方舟集落","bid_progress_percent":162,"remain_time":"3天","bid_id":39,"min_invest_amount":"20,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"预约中","total_amount":"200万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704011619078.jpg","bid_name":"普若瓦蓝精品度假酒店","bid_progress_percent":187,"remain_time":"11天","bid_id":41,"min_invest_amount":"50,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"预约中","total_amount":"750万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"},{"product_img_url":"https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704181759048.jpg","bid_name":"龙门山田园度假综合体","bid_progress_percent":4,"remain_time":"12天","bid_id":43,"min_invest_amount":"20,000","original_bonus_info":{"is_show":false,"original_bonus_value":1},"status_str":"预约中","total_amount":"500万","platform_name":"多彩投","bonus_interest":"50","tag_list":[{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}],"type":"crowdfunding"}]
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
         * product_img_url : https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704181759048.jpg
         * bid_name : 乡志·圣水鸣琴
         * bid_progress_percent : 26
         * remain_time : 25天
         * bid_id : 46
         * min_invest_amount : 5,000
         * original_bonus_info : {"is_show":false,"original_bonus_value":1}
         * status_str : 预约中
         * total_amount : 200万
         * platform_name : 多彩投
         * bonus_interest : 50
         * tag_list : [{"style":"red","name":"新手标"},{"style":"red","name":"按日返"}]
         * type : crowdfunding
         */

        private String product_img_url;
        private String bid_name;
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
        private List<TagListBean> tag_list;

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
