package com.changju.fanlitou.bean.account.investreport;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.activity.InvestReportChildActivity;

import java.util.List;

/**
 * Created by zhangming on 2017/6/14.
 */

public class InsuranceInvestReport {

    /**
     * category_count : 1
     * type : insurance
     * insurance_data : {"insurance_fee":20000,"max_insurance_amount":20000,"chart_data":[{"insurance_fee":20000,"name":"意外险","percent":100,"bid_count":3,"logo":"https://o0s106hgi.qnssl.com/wap/invest_report/insurance/wap_invest_report_insurance_logo_2.png","detail_list":[{"insurance_fee":20000,"insurance_duration":"30天","bid_id":70,"bid_name":"一生无忧","start_date":"2017-05-17"}]}]}
     */

    private int category_count;
    private String type;
    private InsuranceDataBean insurance_data;

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

    public InsuranceDataBean getInsurance_data() {
        return insurance_data;
    }

    public void setInsurance_data(InsuranceDataBean insurance_data) {
        this.insurance_data = insurance_data;
    }

    public static class InsuranceDataBean {
        /**
         * insurance_fee : 20000
         * max_insurance_amount : 20000
         * chart_data : [{"insurance_fee":20000,"name":"意外险","percent":100,"bid_count":3,"logo":"https://o0s106hgi.qnssl.com/wap/invest_report/insurance/wap_invest_report_insurance_logo_2.png","detail_list":[{"insurance_fee":20000,"insurance_duration":"30天","bid_id":70,"bid_name":"一生无忧","start_date":"2017-05-17"}]}]
         */

        private String insurance_fee;
        private String max_insurance_amount;
        private List<ChartDataBean> chart_data;

        public String getInsurance_fee() {
            return insurance_fee;
        }

        public void setInsurance_fee(String insurance_fee) {
            this.insurance_fee = insurance_fee;
        }

        public String getMax_insurance_amount() {
            return max_insurance_amount;
        }

        public void setMax_insurance_amount(String max_insurance_amount) {
            this.max_insurance_amount = max_insurance_amount;
        }

        public List<ChartDataBean> getChart_data() {
            return chart_data;
        }

        public void setChart_data(List<ChartDataBean> chart_data) {
            this.chart_data = chart_data;
        }

        public static class ChartDataBean
                extends AbstractExpandableItem<InsuranceInvestReport.InsuranceDataBean.ChartDataBean.DetailListBean>
                implements MultiItemEntity {
            /**
             * insurance_fee : 20000
             * name : 意外险
             * percent : 100
             * bid_count : 3
             * logo : https://o0s106hgi.qnssl.com/wap/invest_report/insurance/wap_invest_report_insurance_logo_2.png
             * detail_list : [{"insurance_fee":20000,"insurance_duration":"30天","bid_id":70,"bid_name":"一生无忧","start_date":"2017-05-17"}]
             */

            @Override
            public int getItemType() {
                return InvestReportChildActivity.TYPE_LEVEL_0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            private String insurance_fee;
            private String name;
            private float percent;
            private int bid_count;
            private String logo;
            private String logo_white;

            public String getLogo_white() {
                return logo_white;
            }

            public void setLogo_white(String logo_white) {
                this.logo_white = logo_white;
            }

            private List<DetailListBean> detail_list;

            public String getInsurance_fee() {
                return insurance_fee;
            }

            public void setInsurance_fee(String insurance_fee) {
                this.insurance_fee = insurance_fee;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public float getPercent() {
                return percent;
            }

            public void setPercent(float percent) {
                this.percent = percent;
            }

            public int getBid_count() {
                return bid_count;
            }

            public void setBid_count(int bid_count) {
                this.bid_count = bid_count;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public List<DetailListBean> getDetail_list() {
                return detail_list;
            }

            public void setDetail_list(List<DetailListBean> detail_list) {
                this.detail_list = detail_list;
            }

            public static class DetailListBean implements MultiItemEntity{
                /**
                 * insurance_fee : 20000
                 * insurance_duration : 30天
                 * bid_id : 70
                 * bid_name : 一生无忧
                 * start_date : 2017-05-17
                 */

                private int index;

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                @Override
                public int getItemType() {
                    return InvestReportChildActivity.TYPE_LEVEL_1;
                }

                private String insurance_fee;
                private String insurance_duration;
                private int bid_id;
                private String bid_name;
                private String start_date;

                public String getInsurance_fee() {
                    return insurance_fee;
                }

                public void setInsurance_fee(String insurance_fee) {
                    this.insurance_fee = insurance_fee;
                }

                public String getInsurance_duration() {
                    return insurance_duration;
                }

                public void setInsurance_duration(String insurance_duration) {
                    this.insurance_duration = insurance_duration;
                }

                public int getBid_id() {
                    return bid_id;
                }

                public void setBid_id(int bid_id) {
                    this.bid_id = bid_id;
                }

                public String getBid_name() {
                    return bid_name;
                }

                public void setBid_name(String bid_name) {
                    this.bid_name = bid_name;
                }

                public String getStart_date() {
                    return start_date;
                }

                public void setStart_date(String start_date) {
                    this.start_date = start_date;
                }
            }
        }
    }
}
