package com.changju.fanlitou.bean.account.investreport;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.activity.InvestReportChildActivity;

import java.util.List;

/**
 * Created by zhangming on 2017/6/14.
 */

public class CrowdfundingInvestReport {


    /**
     * crowdfunding_data : {"invest_principal":20000,"chart_data":[{"detail_list":[{"invest_principal":10000,"invest_bonus":8.82,"bid_id":49,"bid_name":"测试","start_date":"2017-04-26"},{"invest_principal":10000,"invest_bonus":0.77,"bid_id":54,"bid_name":"测试","start_date":"2017-05-17"}],"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/2fe218b1/app-2fe218b1-8bea-41b8-b29b-7ea8ee00b6ae-1490940729.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/2cf4152b/app-2cf4152b-c741-4686-a28d-641480f831a2-1481697033.png","name":"维C理财","platform_id":67,"amount":20000,"bid_count":2,"percent":100}]}
     * category_count : 1
     * type : crowdfunding
     */

    private CrowdfundingDataBean crowdfunding_data;
    private int category_count;
    private String type;

    public CrowdfundingDataBean getCrowdfunding_data() {
        return crowdfunding_data;
    }

    public void setCrowdfunding_data(CrowdfundingDataBean crowdfunding_data) {
        this.crowdfunding_data = crowdfunding_data;
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

    public static class CrowdfundingDataBean {
        /**
         * invest_principal : 20000
         * chart_data : [{"detail_list":[{"invest_principal":10000,"invest_bonus":8.82,"bid_id":49,"bid_name":"测试","start_date":"2017-04-26"},{"invest_principal":10000,"invest_bonus":0.77,"bid_id":54,"bid_name":"测试","start_date":"2017-05-17"}],"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/2fe218b1/app-2fe218b1-8bea-41b8-b29b-7ea8ee00b6ae-1490940729.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/2cf4152b/app-2cf4152b-c741-4686-a28d-641480f831a2-1481697033.png","name":"维C理财","platform_id":67,"amount":20000,"bid_count":2,"percent":100}]
         */

        private String invest_principal;
        private List<ChartDataBean> chart_data;

        public String getInvest_principal() {
            return invest_principal;
        }

        public void setInvest_principal(String invest_principal) {
            this.invest_principal = invest_principal;
        }

        public List<ChartDataBean> getChart_data() {
            return chart_data;
        }

        public void setChart_data(List<ChartDataBean> chart_data) {
            this.chart_data = chart_data;
        }

        public static class ChartDataBean
                extends AbstractExpandableItem<CrowdfundingDataBean.ChartDataBean.DetailListBean>
                implements MultiItemEntity {
            /**
             * detail_list : [{"invest_principal":10000,"invest_bonus":8.82,"bid_id":49,"bid_name":"测试","start_date":"2017-04-26"},{"invest_principal":10000,"invest_bonus":0.77,"bid_id":54,"bid_name":"测试","start_date":"2017-05-17"}]
             * logo : https://o0s106hgi.qnssl.com/media/plat-logo/2fe218b1/app-2fe218b1-8bea-41b8-b29b-7ea8ee00b6ae-1490940729.png
             * logo_white : https://o0s106hgi.qnssl.com/media/plat-logo/2cf4152b/app-2cf4152b-c741-4686-a28d-641480f831a2-1481697033.png
             * name : 维C理财
             * platform_id : 67
             * amount : 20000
             * bid_count : 2
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
                 * invest_principal : 10000
                 * invest_bonus : 8.82
                 * bid_id : 49
                 * bid_name : 测试
                 * start_date : 2017-04-26
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
                private String invest_principal;
                private String invest_bonus;
                private int bid_id;
                private String bid_name;
                private String start_date;

                public String getInvest_principal() {
                    return invest_principal;
                }

                public void setInvest_principal(String invest_principal) {
                    this.invest_principal = invest_principal;
                }

                public String getInvest_bonus() {
                    return invest_bonus;
                }

                public void setInvest_bonus(String invest_bonus) {
                    this.invest_bonus = invest_bonus;
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
