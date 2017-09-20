package com.changju.fanlitou.bean.account.investreport;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.activity.InvestReportChildActivity;

import java.util.List;

/**
 * Created by zhangming on 2017/6/14.
 */

public class GoldInvestReport {

    /**
     * gold_data : {"total_income":0,"hold_weight":40,"receiving_interest":164.38,"receiving_principal":20000,"chart_data":[{"name":"黄金管家","detail_list":[{"total_income":0,"hold_weight":40,"bid_name":"季季送金08号90天","yesterday_income":0,"receiving_interest":82.19,"receiving_principal":10000,"type":"gold_fix","bid_id":46,"start_date":"2017-05-20"}],"percent":100,"platform_id":28,"hold_weight":40,"bid_count":1,"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/577c5c44/app-577c5c44-e715-49d9-ad10-1c19a3a0c3be-1490927774.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/84febbea/app-84febbea-d0a3-4e5a-bde1-f2fb5b47f868-1466734227.png"}]}
     * category_count : 1
     * type : gold
     */

    private GoldDataBean gold_data;
    private int category_count;
    private String type;

    public GoldDataBean getGold_data() {
        return gold_data;
    }

    public void setGold_data(GoldDataBean gold_data) {
        this.gold_data = gold_data;
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

    public static class GoldDataBean {
        /**
         * total_income : 0
         * hold_weight : 40
         * receiving_interest : 164.38
         * receiving_principal : 20000
         * chart_data : [{"name":"黄金管家","detail_list":[{"total_income":0,"hold_weight":40,"bid_name":"季季送金08号90天","yesterday_income":0,"receiving_interest":82.19,"receiving_principal":10000,"type":"gold_fix","bid_id":46,"start_date":"2017-05-20"}],"percent":100,"platform_id":28,"hold_weight":40,"bid_count":1,"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/577c5c44/app-577c5c44-e715-49d9-ad10-1c19a3a0c3be-1490927774.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/84febbea/app-84febbea-d0a3-4e5a-bde1-f2fb5b47f868-1466734227.png"}]
         */

        private String total_income;
        private String hold_weight;
        private String receiving_interest;
        private String receiving_principal;
        private List<ChartDataBean> chart_data;

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
        }

        public String getHold_weight() {
            return hold_weight;
        }

        public void setHold_weight(String hold_weight) {
            this.hold_weight = hold_weight;
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

        public static class ChartDataBean
                extends AbstractExpandableItem<ChartDataBean.DetailListBean>
                implements MultiItemEntity {
            /**
             * name : 黄金管家
             * detail_list : [{"total_income":0,"hold_weight":40,"bid_name":"季季送金08号90天","yesterday_income":0,"receiving_interest":82.19,"receiving_principal":10000,"type":"gold_fix","bid_id":46,"start_date":"2017-05-20"}]
             * percent : 100
             * platform_id : 28
             * hold_weight : 40
             * bid_count : 1
             * logo : https://o0s106hgi.qnssl.com/media/plat-logo/577c5c44/app-577c5c44-e715-49d9-ad10-1c19a3a0c3be-1490927774.png
             * logo_white : https://o0s106hgi.qnssl.com/media/plat-logo/84febbea/app-84febbea-d0a3-4e5a-bde1-f2fb5b47f868-1466734227.png
             */
            @Override
            public int getItemType() {
                return InvestReportChildActivity.TYPE_LEVEL_0;
            }

            @Override
            public int getLevel() {
                return 0;
            }
            private String name;
            private float percent;
            private int platform_id;
            private String hold_weight;
            private int bid_count;
            private String logo;
            private String logo_white;
            private List<DetailListBean> detail_list;

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

            public int getPlatform_id() {
                return platform_id;
            }

            public void setPlatform_id(int platform_id) {
                this.platform_id = platform_id;
            }

            public String getHold_weight() {
                return hold_weight;
            }

            public void setHold_weight(String hold_weight) {
                this.hold_weight = hold_weight;
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

            public String getLogo_white() {
                return logo_white;
            }

            public void setLogo_white(String logo_white) {
                this.logo_white = logo_white;
            }

            public List<DetailListBean> getDetail_list() {
                return detail_list;
            }

            public void setDetail_list(List<DetailListBean> detail_list) {
                this.detail_list = detail_list;
            }

            public static class DetailListBean implements MultiItemEntity{
                /**
                 * total_income : 0
                 * hold_weight : 40
                 * bid_name : 季季送金08号90天
                 * yesterday_income : 0
                 * receiving_interest : 82.19
                 * receiving_principal : 10000
                 * type : gold_fix
                 * bid_id : 46
                 * start_date : 2017-05-20
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

                private String total_income;
                private String hold_weight;
                private String bid_name;
                private String yesterday_income;
                private String receiving_interest;
                private String receiving_principal;
                private String type;
                private int bid_id;
                private String start_date;

                public String getTotal_income() {
                    return total_income;
                }

                public void setTotal_income(String total_income) {
                    this.total_income = total_income;
                }

                public String getHold_weight() {
                    return hold_weight;
                }

                public void setHold_weight(String hold_weight) {
                    this.hold_weight = hold_weight;
                }

                public String getBid_name() {
                    return bid_name;
                }

                public void setBid_name(String bid_name) {
                    this.bid_name = bid_name;
                }

                public String getYesterday_income() {
                    return yesterday_income;
                }

                public void setYesterday_income(String yesterday_income) {
                    this.yesterday_income = yesterday_income;
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

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getBid_id() {
                    return bid_id;
                }

                public void setBid_id(int bid_id) {
                    this.bid_id = bid_id;
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
