package com.changju.fanlitou.bean.account.investreport;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.activity.InvestReportChildActivity;

import java.util.List;

/**
 * Created by zhangming on 2017/6/14.
 */

public class AbroadInvestReport {

    /**
     * category_count : 1
     * type : abroad
     * abroad_data : {"chart_data":[{"detail_list":[{"receiving_principal":7000,"receiving_interest":59.45,"bid_id":346691,"bid_name":"测试","start_date":"2017-04-28"}],"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/28395c28/app-28395c28-bc37-462c-80a3-645804a15853-1490940707.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/18797917/app-18797917-b31c-442e-ac6a-fb24cf0f40e3-1481697188.png","name":"微贷网","platform_id":68,"amount":7059.45,"bid_count":1,"percent":100}],"receiving_interest":59.45,"receiving_principal":7000}
     */

    private int category_count;
    private String type;
    private AbroadDataBean abroad_data;

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

    public AbroadDataBean getAbroad_data() {
        return abroad_data;
    }

    public void setAbroad_data(AbroadDataBean abroad_data) {
        this.abroad_data = abroad_data;
    }

    public static class AbroadDataBean {
        /**
         * chart_data : [{"detail_list":[{"receiving_principal":7000,"receiving_interest":59.45,"bid_id":346691,"bid_name":"测试","start_date":"2017-04-28"}],"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/28395c28/app-28395c28-bc37-462c-80a3-645804a15853-1490940707.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/18797917/app-18797917-b31c-442e-ac6a-fb24cf0f40e3-1481697188.png","name":"微贷网","platform_id":68,"amount":7059.45,"bid_count":1,"percent":100}]
         * receiving_interest : 59.45
         * receiving_principal : 7000
         */

        private String receiving_interest;
        private String receiving_principal;
        private List<ChartDataBean> chart_data;

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

        public static class ChartDataBean
                extends AbstractExpandableItem<AbroadDataBean.ChartDataBean.DetailListBean>
                implements MultiItemEntity {
            /**
             * detail_list : [{"receiving_principal":7000,"receiving_interest":59.45,"bid_id":346691,"bid_name":"测试","start_date":"2017-04-28"}]
             * logo : https://o0s106hgi.qnssl.com/media/plat-logo/28395c28/app-28395c28-bc37-462c-80a3-645804a15853-1490940707.png
             * logo_white : https://o0s106hgi.qnssl.com/media/plat-logo/18797917/app-18797917-b31c-442e-ac6a-fb24cf0f40e3-1481697188.png
             * name : 微贷网
             * platform_id : 68
             * amount : 7059.45
             * bid_count : 1
             * percent : 100
             */

            @Override
            public int getItemType() {
                return InvestReportChildActivity.TYPE_LEVEL_0;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            private String logo;
            private String logo_white;
            private String name;
            private int platform_id;
            private String amount;
            private int bid_count;
            private float percent;
            private List<DetailListBean> detail_list;

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getLogo_white() {
                return logo_white;
            }

            public void setLogo_white(String logo_white) {
                this.logo_white = logo_white;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPlatform_id() {
                return platform_id;
            }

            public void setPlatform_id(int platform_id) {
                this.platform_id = platform_id;
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

            public float getPercent() {
                return percent;
            }

            public void setPercent(float percent) {
                this.percent = percent;
            }

            public List<DetailListBean> getDetail_list() {
                return detail_list;
            }

            public void setDetail_list(List<DetailListBean> detail_list) {
                this.detail_list = detail_list;
            }

            public static class DetailListBean implements MultiItemEntity{
                /**
                 * receiving_principal : 7000
                 * receiving_interest : 59.45
                 * bid_id : 346691
                 * bid_name : 测试
                 * start_date : 2017-04-28
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
                private String receiving_principal;
                private String receiving_interest;
                private int bid_id;
                private String bid_name;
                private String start_date;

                public String getReceiving_principal() {
                    return receiving_principal;
                }

                public void setReceiving_principal(String receiving_principal) {
                    this.receiving_principal = receiving_principal;
                }

                public String getReceiving_interest() {
                    return receiving_interest;
                }

                public void setReceiving_interest(String receiving_interest) {
                    this.receiving_interest = receiving_interest;
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
