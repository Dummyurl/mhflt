package com.changju.fanlitou.bean.account;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16.
 */

public class FundList {

    /**
     * has_insurance : false
     * category_count : 2
     * type : category
     * category_data : {"chart_data":[{"insurance_max_guarantee_amount":0,"platform_count":0,"receiving_amount":414390.22,"total_principal":366068.01,"insurance_category_count":0,"logo":"","name":"固收","amount":414390.22,"total_interest":48322.21,"bid_count":16,"type":"p2p"},{"insurance_max_guarantee_amount":0,"platform_count":0,"receiving_amount":29485.9,"total_principal":29485.9,"insurance_category_count":0,"logo":"","name":"活期","amount":29485.9,"total_interest":0,"bid_count":1,"type":"current"}],"category_insurance_max_guarantee_amount":0,"receiving_interest":48322.21,"receiving_principal":395553.91,"receiving_amount":443876.12}
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
         * chart_data : [{"insurance_max_guarantee_amount":0,"platform_count":0,"receiving_amount":414390.22,"total_principal":366068.01,"insurance_category_count":0,"logo":"","name":"固收","amount":414390.22,"total_interest":48322.21,"bid_count":16,"type":"p2p"},{"insurance_max_guarantee_amount":0,"platform_count":0,"receiving_amount":29485.9,"total_principal":29485.9,"insurance_category_count":0,"logo":"","name":"活期","amount":29485.9,"total_interest":0,"bid_count":1,"type":"current"}]
         * category_insurance_max_guarantee_amount : 0
         * receiving_interest : 48322.21
         * receiving_principal : 395553.91
         * receiving_amount : 443876.12
         */

        private int category_insurance_max_guarantee_amount;
        private String receiving_interest;
        private String receiving_principal;
        private String receiving_amount;
        private List<ChartDataBean> chart_data;

        public int getCategory_insurance_max_guarantee_amount() {
            return category_insurance_max_guarantee_amount;
        }

        public void setCategory_insurance_max_guarantee_amount(int category_insurance_max_guarantee_amount) {
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

        public String getReceiving_amount() {
            return receiving_amount;
        }

        public void setReceiving_amount(String receiving_amount) {
            this.receiving_amount = receiving_amount;
        }

        public List<ChartDataBean> getChart_data() {
            return chart_data;
        }

        public void setChart_data(List<ChartDataBean> chart_data) {
            this.chart_data = chart_data;
        }

        public static class ChartDataBean {
            /**
             * insurance_max_guarantee_amount : 0
             * platform_count : 0
             * receiving_amount : 414390.22
             * total_principal : 366068.01
             * insurance_category_count : 0
             * logo :
             * name : 固收
             * amount : 414390.22
             * total_interest : 48322.21
             * bid_count : 16
             * type : p2p
             */

            private String insurance_max_guarantee_amount;
            private int platform_count;
            private String receiving_amount;
            private String total_principal;
            private int insurance_category_count;
            private String logo;
            private String name;
            private String amount;
            private String total_interest;
            private int bid_count;
            private String type;

            public String getInsurance_max_guarantee_amount() {
                return insurance_max_guarantee_amount;
            }

            public void setInsurance_max_guarantee_amount(String insurance_max_guarantee_amount) {
                this.insurance_max_guarantee_amount = insurance_max_guarantee_amount;
            }

            public int getPlatform_count() {
                return platform_count;
            }

            public void setPlatform_count(int platform_count) {
                this.platform_count = platform_count;
            }

            public String getReceiving_amount() {
                return receiving_amount;
            }

            public void setReceiving_amount(String receiving_amount) {
                this.receiving_amount = receiving_amount;
            }

            public String getTotal_principal() {
                return total_principal;
            }

            public void setTotal_principal(String total_principal) {
                this.total_principal = total_principal;
            }

            public int getInsurance_category_count() {
                return insurance_category_count;
            }

            public void setInsurance_category_count(int insurance_category_count) {
                this.insurance_category_count = insurance_category_count;
            }

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

            public String getTotal_interest() {
                return total_interest;
            }

            public void setTotal_interest(String total_interest) {
                this.total_interest = total_interest;
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
        }
    }
}
