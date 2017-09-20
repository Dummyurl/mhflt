package com.changju.fanlitou.bean.account.investreport;

import java.util.List;

/**
 * Created by zm on 2017/5/16.
 */

public class CategoryInvestReport {

    /**
     * has_insurance : true
     * category_count : 6
     * type : category
     * category_data : {"chart_data":[{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/p2p_report.png","name":"固收","amount":38109.97,"bid_count":6,"type":"p2p","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/current_report.png","name":"活期","amount":10000,"bid_count":1,"type":"current","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/gold_report.png","name":"黄金","amount":10002.59,"bid_count":1,"type":"gold","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/crowdfund_report.png","name":"众筹","amount":100,"bid_count":1,"type":"crowdfunding","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/foreign_report.png","name":"海外","amount":190036,"bid_count":1,"type":"abroad","percent":10},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/insurance_report.png","name":"保险","amount":3550,"bid_count":1,"type":"insurance","percent":10}],"category_insurance_max_guarantee_amount":400000,"receiving_interest":69201.7,"receiving_principal":179046.86}
     */

    private boolean has_insurance;
    private int category_count;
    private String type;
    private CategoryDataBean category_data;

    public boolean isHas_insurance() {
        return has_insurance;
    }

    public void setHas_insurance(boolean has_insurance) {
        this.has_insurance = has_insurance;
    }

    public int getCategory_count() {
        return category_count;
    }

    public void setCategory_count(int category_count) {
        this.category_count = category_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CategoryDataBean getCategory_data() {
        return category_data;
    }

    public void setCategory_data(CategoryDataBean category_data) {
        this.category_data = category_data;
    }

    public static class CategoryDataBean {
        /**
         * chart_data : [{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/p2p_report.png","name":"固收","amount":38109.97,"bid_count":6,"type":"p2p","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/current_report.png","name":"活期","amount":10000,"bid_count":1,"type":"current","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/gold_report.png","name":"黄金","amount":10002.59,"bid_count":1,"type":"gold","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/crowdfund_report.png","name":"众筹","amount":100,"bid_count":1,"type":"crowdfunding","percent":20},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/foreign_report.png","name":"海外","amount":190036,"bid_count":1,"type":"abroad","percent":10},{"logo":"https://o0s106hgi.qnssl.com/logo/app/invest_report/insurance_report.png","name":"保险","amount":3550,"bid_count":1,"type":"insurance","percent":10}]
         * category_insurance_max_guarantee_amount : 400000
         * receiving_interest : 69201.7
         * receiving_principal : 179046.86
         */

        private String category_insurance_max_guarantee_amount;
        private String receiving_interest;
        private String receiving_principal;
        private List<ChartDataBean> chart_data;

        public String getCategory_insurance_max_guarantee_amount() {
            return category_insurance_max_guarantee_amount;
        }

        public void setCategory_insurance_max_guarantee_amount(String category_insurance_max_guarantee_amount) {
            this.category_insurance_max_guarantee_amount = category_insurance_max_guarantee_amount;
        }

        public String getReceiving_interest() {
            return receiving_interest;
        }

        public void setReceiving_interest(String receiving_interest) {
            this.receiving_interest = receiving_interest;
        }

        public String getReceiving_principal() {
            return receiving_principal;
        }

        public void setReceiving_principal(String receiving_principal) {
            this.receiving_principal = receiving_principal;
        }

        public List<ChartDataBean> getChart_data() {
            return chart_data;
        }

        public void setChart_data(List<ChartDataBean> chart_data) {
            this.chart_data = chart_data;
        }

        public static class ChartDataBean {
            /**
             * logo : https://o0s106hgi.qnssl.com/logo/app/invest_report/p2p_report.png
             * name : 固收
             * amount : 38109.97
             * bid_count : 6
             * type : p2p
             * percent : 20
             */

            private String logo;
            private String name;
            private String amount;
            private int bid_count;
            private String type;
            private float percent;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getBid_count() {
                return bid_count;
            }

            public void setBid_count(int bid_count) {
                this.bid_count = bid_count;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public float getPercent() {
                return percent;
            }

            public void setPercent(float percent) {
                this.percent = percent;
            }
        }
    }
}
