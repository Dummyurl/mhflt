package com.changju.fanlitou.bean.account.investreport;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.changju.fanlitou.activity.InvestReportChildActivity;

import java.util.List;

/**
 * Created by zhangming on 2017/6/14.
 */

public class CurrentInvestReport {

    /**
     * category_count : 1
     * type : current
     * current_data : {"total_principal":20000,"total_income":100,"chart_data":[{"detail_list":[{"total_principal":10000,"bid_name":"测试","bid_id":6,"total_income":0,"yesterday_income":0,"start_date":"2017-05-21"},{"total_principal":10000,"bid_name":"测试","bid_id":7,"total_income":0,"yesterday_income":0,"start_date":"2017-05-21"}],"total_principal":20000,"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/37c0db82/app-37c0db82-a851-4ce1-b92f-5c58c11a5911-1490940774.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/7b793fcf/app-7b793fcf-e64e-48bc-ac63-c2fa09921a22-1472723831.png","name":"星火钱包","platform_id":48,"amount":20000,"bid_count":2,"percent":100}]}
     */

    private int category_count;
    private String type;
    private CurrentDataBean current_data;

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

    public CurrentDataBean getCurrent_data() {
        return current_data;
    }

    public void setCurrent_data(CurrentDataBean current_data) {
        this.current_data = current_data;
    }

    public static class CurrentDataBean {
        /**
         * total_principal : 20000
         * total_income : 100
         * chart_data : [{"detail_list":[{"total_principal":10000,"bid_name":"测试","bid_id":6,"total_income":0,"yesterday_income":0,"start_date":"2017-05-21"},{"total_principal":10000,"bid_name":"测试","bid_id":7,"total_income":0,"yesterday_income":0,"start_date":"2017-05-21"}],"total_principal":20000,"logo":"https://o0s106hgi.qnssl.com/media/plat-logo/37c0db82/app-37c0db82-a851-4ce1-b92f-5c58c11a5911-1490940774.png","logo_white":"https://o0s106hgi.qnssl.com/media/plat-logo/7b793fcf/app-7b793fcf-e64e-48bc-ac63-c2fa09921a22-1472723831.png","name":"星火钱包","platform_id":48,"amount":20000,"bid_count":2,"percent":100}]
         */

        private String total_principal;
        private String total_income;
        private List<ChartDataBean> chart_data;

        public String getTotal_principal() {
            return total_principal;
        }

        public void setTotal_principal(String total_principal) {
            this.total_principal = total_principal;
        }

        public String getTotal_income() {
            return total_income;
        }

        public void setTotal_income(String total_income) {
            this.total_income = total_income;
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
             * detail_list : [{"total_principal":10000,"bid_name":"测试","bid_id":6,"total_income":0,"yesterday_income":0,"start_date":"2017-05-21"},{"total_principal":10000,"bid_name":"测试","bid_id":7,"total_income":0,"yesterday_income":0,"start_date":"2017-05-21"}]
             * total_principal : 20000
             * logo : https://o0s106hgi.qnssl.com/media/plat-logo/37c0db82/app-37c0db82-a851-4ce1-b92f-5c58c11a5911-1490940774.png
             * logo_white : https://o0s106hgi.qnssl.com/media/plat-logo/7b793fcf/app-7b793fcf-e64e-48bc-ac63-c2fa09921a22-1472723831.png
             * name : 星火钱包
             * platform_id : 48
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
            private String total_principal;
            private String logo;
            private String logo_white;
            private String name;
            private int platform_id;
            private String amount;
            private int bid_count;
            private float percent;
            private List<DetailListBean> detail_list;

            public String getTotal_principal() {
                return total_principal;
            }

            public void setTotal_principal(String total_principal) {
                this.total_principal = total_principal;
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
                 * total_principal : 10000
                 * bid_name : 测试
                 * bid_id : 6
                 * total_income : 0
                 * yesterday_income : 0
                 * start_date : 2017-05-21
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

                private String total_principal;
                private String bid_name;
                private int bid_id;
                private String total_income;
                private String yesterday_income;
                private String start_date;

                public String getTotal_principal() {
                    return total_principal;
                }

                public void setTotal_principal(String total_principal) {
                    this.total_principal = total_principal;
                }

                public String getBid_name() {
                    return bid_name;
                }

                public void setBid_name(String bid_name) {
                    this.bid_name = bid_name;
                }

                public int getBid_id() {
                    return bid_id;
                }

                public void setBid_id(int bid_id) {
                    this.bid_id = bid_id;
                }

                public String getTotal_income() {
                    return total_income;
                }

                public void setTotal_income(String total_income) {
                    this.total_income = total_income;
                }

                public String getYesterday_income() {
                    return yesterday_income;
                }

                public void setYesterday_income(String yesterday_income) {
                    this.yesterday_income = yesterday_income;
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
