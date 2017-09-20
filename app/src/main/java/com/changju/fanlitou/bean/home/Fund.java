package com.changju.fanlitou.bean.home;

/**
 * Created by zhangming on 2017/6/26.
 */

public class Fund {

    /**
     * recommend_bid : {"is_show":true,"bid_type":"fund","bid_detail":{"bid_url":"/m/fund_detail/1/?from=/m/","risk":"中低风险","fund_name":"景顺稳定收益基金C","invest_type":"债券型","year_gr":"+1.47","fund_code":"261101","fund_id":1},"title":"限时首投返利3%，4.28结束"}
     */

    private RecommendBidBean recommend_bid;

    public RecommendBidBean getRecommend_bid() {
        return recommend_bid;
    }

    public void setRecommend_bid(RecommendBidBean recommend_bid) {
        this.recommend_bid = recommend_bid;
    }

    public static class RecommendBidBean {
        /**
         * is_show : true
         * bid_type : fund
         * bid_detail : {"bid_url":"/m/fund_detail/1/?from=/m/","risk":"中低风险","fund_name":"景顺稳定收益基金C","invest_type":"债券型","year_gr":"+1.47","fund_code":"261101","fund_id":1}
         * title : 限时首投返利3%，4.28结束
         */

        private boolean is_show;
        private String bid_type;
        private BidDetailBean bid_detail;
        private String title;

        public boolean isIs_show() {
            return is_show;
        }

        public void setIs_show(boolean is_show) {
            this.is_show = is_show;
        }

        public String getBid_type() {
            return bid_type;
        }

        public void setBid_type(String bid_type) {
            this.bid_type = bid_type;
        }

        public BidDetailBean getBid_detail() {
            return bid_detail;
        }

        public void setBid_detail(BidDetailBean bid_detail) {
            this.bid_detail = bid_detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class BidDetailBean {
            /**
             * bid_url : /m/fund_detail/1/?from=/m/
             * risk : 中低风险
             * fund_name : 景顺稳定收益基金C
             * invest_type : 债券型
             * year_gr : +1.47
             * fund_code : 261101
             * fund_id : 1
             */

            private String bid_url;
            private String risk;
            private String fund_name;
            private String invest_type;
            private String year_gr;
            private String fund_code;
            private int fund_id;

            public String getBid_url() {
                return bid_url;
            }

            public void setBid_url(String bid_url) {
                this.bid_url = bid_url;
            }

            public String getRisk() {
                return risk;
            }

            public void setRisk(String risk) {
                this.risk = risk;
            }

            public String getFund_name() {
                return fund_name;
            }

            public void setFund_name(String fund_name) {
                this.fund_name = fund_name;
            }

            public String getInvest_type() {
                return invest_type;
            }

            public void setInvest_type(String invest_type) {
                this.invest_type = invest_type;
            }

            public String getYear_gr() {
                return year_gr;
            }

            public void setYear_gr(String year_gr) {
                this.year_gr = year_gr;
            }

            public String getFund_code() {
                return fund_code;
            }

            public void setFund_code(String fund_code) {
                this.fund_code = fund_code;
            }

            public int getFund_id() {
                return fund_id;
            }

            public void setFund_id(int fund_id) {
                this.fund_id = fund_id;
            }
        }
    }
}
