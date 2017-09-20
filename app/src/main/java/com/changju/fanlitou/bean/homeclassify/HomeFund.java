package com.changju.fanlitou.bean.homeclassify;

import java.util.List;

/**
 * Created by zhangming on 2017/7/31.
 */

public class HomeFund {


    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * fund_list : [{"risk":"高风险","fund_name":"易基消费行业","invest_type":"股票型","year_gr":"+42.40","fund_code":"110022","fund_id":2839},{"risk":"中风险","fund_name":"嘉实沪港深精选股票","invest_type":"股票型","year_gr":"+42.40","fund_code":"001878","fund_id":4861}]
         * name : 高收益股票型基金
         */

        private String name;
        private List<FundListBean> fund_list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<FundListBean> getFund_list() {
            return fund_list;
        }

        public void setFund_list(List<FundListBean> fund_list) {
            this.fund_list = fund_list;
        }

        public static class FundListBean {
            /**
             * risk : 高风险
             * fund_name : 易基消费行业
             * invest_type : 股票型
             * year_gr : +42.40
             * fund_code : 110022
             * fund_id : 2839
             */

            private String risk;
            private String fund_name;
            private String invest_type;
            private String year_gr;
            private String fund_code;
            private int fund_id;

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
