package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/6/5.
 */

public class RedBag {

    /**
     * red_packets : [{"status_str":"已使用","class_name":"used","amount":"¥ 5","description":["鑫合汇|月益升-经营贷YYS2016101803"],"type_str":"投资红包","url":"javascript:;","type":1,"date_str":"投资日期：2017-11-11"},{"status_str":"已使用","class_name":"used","amount":"¥ 15","description":["10亿活动","成功邀请第4位好友奖励"],"type_str":"现金券","url":"javascript:;","type":1,"date_str":"发放日期：2017-05-23"}]
     * total : 20
     */

    private int total;
    private List<RedPacketsBean> red_packets;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RedPacketsBean> getRed_packets() {
        return red_packets;
    }

    public void setRed_packets(List<RedPacketsBean> red_packets) {
        this.red_packets = red_packets;
    }

    public static class RedPacketsBean {
        /**
         * status_str : 已使用
         * class_name : used
         * amount : ¥ 5
         * description : ["鑫合汇|月益升-经营贷YYS2016101803"]
         * type_str : 投资红包
         * url : javascript:;
         * type : 1
         * date_str : 投资日期：2017-11-11
         */

        private String status_str;
        private String class_name;
        private String amount;
        private String type_str;
        private String url;
        private int type;
        private String date_str;
        private List<String> description;

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getType_str() {
            return type_str;
        }

        public void setType_str(String type_str) {
            this.type_str = type_str;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDate_str() {
            return date_str;
        }

        public void setDate_str(String date_str) {
            this.date_str = date_str;
        }

        public List<String> getDescription() {
            return description;
        }

        public void setDescription(List<String> description) {
            this.description = description;
        }
    }
}
