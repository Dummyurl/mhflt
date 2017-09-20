package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by zhangming on 2017/6/7.
 */

public class MoneyDetail {

    /**
     * has_more : true
     * transaction : [{"img":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png","class_name":"neg","type_str":"提现申请","amount":"50","date":"2017-05-27","balance":"96146.2","desc":"提现金额被冻结，已从可用余额中减去"},{"img":"https://o0s106hgi.qnssl.com/account_transaction/logoaward.png","class_name":"pos","type_str":"红包奖励","amount":"15","date":"2017-05-23","balance":"96196.2","desc":"返利投破10亿活动，成功邀请第6位好友奖励"}]
     * filter_list : [{"content":[],"is_show":true,"id":"all","name":"全部"},{"content":[],"is_show":true,"id":"onetime_bonus","name":"一次性返利"},{"content":[{"id":"","name":"全部"},{"id":"current|9","name":"星火 X50"},{"id":"p2p|16255","name":"【理财专区】E42017042005"},{"id":"p2p|16251","name":"爱房贷-北京个人房产周转贷FDHX170431-4"},{"id":"p2p|16234","name":"（济南）章丘市房产抵押标，编号：GXDY100801"},{"id":"p2p|16236","name":"\u201c车+\u201d库存贷A第一期，编号：SDQDCHMY002"},{"id":"p2p|16233","name":"北京房山区房产抵押标，编号：GXDY100989"},{"id":"p2p|16224","name":"（济南）槐荫区房产抵押标，编号：GXDY101306"},{"id":"p2p|5351","name":"押车宝168911"},{"id":"p2p|4924","name":"北京朝阳区房产抵押标，编号：GXDY101053"},{"id":"p2p|1062","name":"个人消费贷 个人消费贷\n            1063169-1-1"},{"id":"p2p|16223","name":"北京宾利雅致车辆抵押标，编号：GXDY101433"},{"id":"p2p|1","name":"资金周转 #889935"},{"id":"p2p|4829","name":"\u201c人人+\u201d公积金贷，编号：GC1607007"},{"id":"p2p|1053","name":"车辆周转贷 1018751-1-1"},{"id":"p2p|1047","name":"车辆周转贷 车辆周转贷\n            1002646-1-1"}],"is_show":true,"id":"daily_bonus","name":"按日返利"},{"content":[{"id":"invite","name":"邀请返利"},{"id":"withdraw","name":"提现"},{"id":"award","name":"奖励"}],"is_show":true,"id":"other","name":"其他"}]
     */

    private boolean has_more;
    private List<TransactionBean> transaction;
    private List<FilterListBean> filter_list;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<TransactionBean> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionBean> transaction) {
        this.transaction = transaction;
    }

    public List<FilterListBean> getFilter_list() {
        return filter_list;
    }

    public void setFilter_list(List<FilterListBean> filter_list) {
        this.filter_list = filter_list;
    }

    public static class TransactionBean {
        /**
         * img : https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png
         * class_name : neg
         * type_str : 提现申请
         * amount : 50
         * date : 2017-05-27
         * balance : 96146.2
         * desc : 提现金额被冻结，已从可用余额中减去
         */

        private String img;
        private String class_name;
        private String type_str;
        private String amount;
        private String date;
        private String balance;
        private String desc;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getType_str() {
            return type_str;
        }

        public void setType_str(String type_str) {
            this.type_str = type_str;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class FilterListBean {
        /**
         * content : []
         * is_show : true
         * id : all
         * name : 全部
         */

        private boolean is_show;
        private String id;
        private String name;
        private List<ContentBean> content;

        public boolean isIs_show() {
            return is_show;
        }

        public void setIs_show(boolean is_show) {
            this.is_show = is_show;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean{
            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
