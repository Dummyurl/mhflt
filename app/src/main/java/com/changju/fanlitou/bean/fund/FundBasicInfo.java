package com.changju.fanlitou.bean.fund;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangming on 2017/7/17.
 */

public class FundBasicInfo implements Serializable{


    /**
     * purchase_str : 申购
     * redemp_status : 1
     * fund_type : 混合型
     * has_discount : false
     * real_tab_name : 净值走势
     * unit_nav : 1.0710
     * purchase_status : 1
     * invest_risk : 中高
     * fund_abbr : 光大中国制造
     * unit_nav_title : 单位净值
     * fanlitou_rate : 1.50
     * day_gr_title : 净值日增长率
     * status : 开放申购
     * subscribe_rate : 1.50
     * fund_name : 光大保德信中国制造2025灵活配置混合型证券投资基金
     * day_gr : +1.82
     * discount :
     * unit_nav_date : 05-04
     * history_unit_nav : {"datalist":[{"date":"2017-07-05","day_gr":"+1.55%","accum_nav":"1.1810","unit_nav":"1.1810"},{"date":"2017-07-04","day_gr":"-0.77%","accum_nav":"1.1630","unit_nav":"1.1630"},{"date":"2017-07-03","day_gr":"+1.82%","accum_nav":"1.1720","unit_nav":"1.1720"}],"data_list_title":{"col_2":"单位净值","col_3":"累计净值","col_1":"日期","col_4":"日涨幅"},"title":"历史净值"}
     * fund_code : 001740
     * btn_list : [{"operate_type":"apply","btn_class":"red","btn_str":"立即申购"}]
     * fund_id : 1865
     * income_tab_name : 收益率走势
     * history_nav : {"datalist":[{"rank_total":2420,"range":"近一周","rank":7,"gr":"+3.44","hs300_gr":"-0.47"},{"rank_total":2408,"range":"近一月","rank":72,"gr":"+9.23","hs300_gr":"+5.25"},{"rank_total":2354,"range":"近三月","rank":32,"gr":"+12.05","hs300_gr":"+4.19"}],"data_list_title":{"col_2":"本产品","col_3":"沪深300","col_1":"时间区间","col_4":"同类排名"},"title":"业绩表现"}
     * is_already_bind_card : true
     */

    private String purchase_str;
    private int redemp_status;
    private String fund_type;
    private boolean has_discount;
    private String real_tab_name;
    private String unit_nav;
    private int purchase_status;
    private String invest_risk;
    private String fund_abbr;
    private String unit_nav_title;
    private String fanlitou_rate;
    private String day_gr_title;
    private String status;
    private String subscribe_rate;
    private String fund_name;
    private String day_gr;
    private String discount;
    private String unit_nav_date;
    private HistoryUnitNavBean history_unit_nav;
    private String fund_code;
    private int fund_id;
    private String income_tab_name;
    private HistoryNavBean history_nav;
    private boolean is_already_bind_card;
    private List<BtnListBean> btn_list;

    public String getPurchase_str() {
        return purchase_str;
    }

    public void setPurchase_str(String purchase_str) {
        this.purchase_str = purchase_str;
    }

    public int getRedemp_status() {
        return redemp_status;
    }

    public void setRedemp_status(int redemp_status) {
        this.redemp_status = redemp_status;
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public boolean isHas_discount() {
        return has_discount;
    }

    public void setHas_discount(boolean has_discount) {
        this.has_discount = has_discount;
    }

    public String getReal_tab_name() {
        return real_tab_name;
    }

    public void setReal_tab_name(String real_tab_name) {
        this.real_tab_name = real_tab_name;
    }

    public String getUnit_nav() {
        return unit_nav;
    }

    public void setUnit_nav(String unit_nav) {
        this.unit_nav = unit_nav;
    }

    public int getPurchase_status() {
        return purchase_status;
    }

    public void setPurchase_status(int purchase_status) {
        this.purchase_status = purchase_status;
    }

    public String getInvest_risk() {
        return invest_risk;
    }

    public void setInvest_risk(String invest_risk) {
        this.invest_risk = invest_risk;
    }

    public String getFund_abbr() {
        return fund_abbr;
    }

    public void setFund_abbr(String fund_abbr) {
        this.fund_abbr = fund_abbr;
    }

    public String getUnit_nav_title() {
        return unit_nav_title;
    }

    public void setUnit_nav_title(String unit_nav_title) {
        this.unit_nav_title = unit_nav_title;
    }

    public String getFanlitou_rate() {
        return fanlitou_rate;
    }

    public void setFanlitou_rate(String fanlitou_rate) {
        this.fanlitou_rate = fanlitou_rate;
    }

    public String getDay_gr_title() {
        return day_gr_title;
    }

    public void setDay_gr_title(String day_gr_title) {
        this.day_gr_title = day_gr_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubscribe_rate() {
        return subscribe_rate;
    }

    public void setSubscribe_rate(String subscribe_rate) {
        this.subscribe_rate = subscribe_rate;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getDay_gr() {
        return day_gr;
    }

    public void setDay_gr(String day_gr) {
        this.day_gr = day_gr;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUnit_nav_date() {
        return unit_nav_date;
    }

    public void setUnit_nav_date(String unit_nav_date) {
        this.unit_nav_date = unit_nav_date;
    }

    public HistoryUnitNavBean getHistory_unit_nav() {
        return history_unit_nav;
    }

    public void setHistory_unit_nav(HistoryUnitNavBean history_unit_nav) {
        this.history_unit_nav = history_unit_nav;
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

    public String getIncome_tab_name() {
        return income_tab_name;
    }

    public void setIncome_tab_name(String income_tab_name) {
        this.income_tab_name = income_tab_name;
    }

    public HistoryNavBean getHistory_nav() {
        return history_nav;
    }

    public void setHistory_nav(HistoryNavBean history_nav) {
        this.history_nav = history_nav;
    }

    public boolean isIs_already_bind_card() {
        return is_already_bind_card;
    }

    public void setIs_already_bind_card(boolean is_already_bind_card) {
        this.is_already_bind_card = is_already_bind_card;
    }

    public List<BtnListBean> getBtn_list() {
        return btn_list;
    }

    public void setBtn_list(List<BtnListBean> btn_list) {
        this.btn_list = btn_list;
    }

    public static class HistoryUnitNavBean {
        /**
         * datalist : [{"date":"2017-07-05","day_gr":"+1.55%","accum_nav":"1.1810","unit_nav":"1.1810"},{"date":"2017-07-04","day_gr":"-0.77%","accum_nav":"1.1630","unit_nav":"1.1630"},{"date":"2017-07-03","day_gr":"+1.82%","accum_nav":"1.1720","unit_nav":"1.1720"}]
         * data_list_title : {"col_2":"单位净值","col_3":"累计净值","col_1":"日期","col_4":"日涨幅"}
         * title : 历史净值
         */

        private DataListTitleBean data_list_title;
        private String title;
        private List<DatalistBean> datalist;

        public DataListTitleBean getData_list_title() {
            return data_list_title;
        }

        public void setData_list_title(DataListTitleBean data_list_title) {
            this.data_list_title = data_list_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DatalistBean> getDatalist() {
            return datalist;
        }

        public void setDatalist(List<DatalistBean> datalist) {
            this.datalist = datalist;
        }

        public static class DataListTitleBean {
            /**
             * col_2 : 单位净值
             * col_3 : 累计净值
             * col_1 : 日期
             * col_4 : 日涨幅
             */

            private String col_2;
            private String col_3;
            private String col_1;
            private String col_4;

            public String getCol_2() {
                return col_2;
            }

            public void setCol_2(String col_2) {
                this.col_2 = col_2;
            }

            public String getCol_3() {
                return col_3;
            }

            public void setCol_3(String col_3) {
                this.col_3 = col_3;
            }

            public String getCol_1() {
                return col_1;
            }

            public void setCol_1(String col_1) {
                this.col_1 = col_1;
            }

            public String getCol_4() {
                return col_4;
            }

            public void setCol_4(String col_4) {
                this.col_4 = col_4;
            }
        }

        public static class DatalistBean {
            /**
             * date : 2017-07-05
             * day_gr : +1.55%
             * accum_nav : 1.1810
             * unit_nav : 1.1810
             */

            private String date;
            private String day_gr;
            private String accum_nav;
            private String unit_nav;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDay_gr() {
                return day_gr;
            }

            public void setDay_gr(String day_gr) {
                this.day_gr = day_gr;
            }

            public String getAccum_nav() {
                return accum_nav;
            }

            public void setAccum_nav(String accum_nav) {
                this.accum_nav = accum_nav;
            }

            public String getUnit_nav() {
                return unit_nav;
            }

            public void setUnit_nav(String unit_nav) {
                this.unit_nav = unit_nav;
            }
        }
    }

    public static class HistoryNavBean {
        /**
         * datalist : [{"rank_total":2420,"range":"近一周","rank":7,"gr":"+3.44","hs300_gr":"-0.47"},{"rank_total":2408,"range":"近一月","rank":72,"gr":"+9.23","hs300_gr":"+5.25"},{"rank_total":2354,"range":"近三月","rank":32,"gr":"+12.05","hs300_gr":"+4.19"}]
         * data_list_title : {"col_2":"本产品","col_3":"沪深300","col_1":"时间区间","col_4":"同类排名"}
         * title : 业绩表现
         */

        private DataListTitleBeanX data_list_title;
        private String title;
        private List<DatalistBeanX> datalist;

        public DataListTitleBeanX getData_list_title() {
            return data_list_title;
        }

        public void setData_list_title(DataListTitleBeanX data_list_title) {
            this.data_list_title = data_list_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DatalistBeanX> getDatalist() {
            return datalist;
        }

        public void setDatalist(List<DatalistBeanX> datalist) {
            this.datalist = datalist;
        }

        public static class DataListTitleBeanX {
            /**
             * col_2 : 本产品
             * col_3 : 沪深300
             * col_1 : 时间区间
             * col_4 : 同类排名
             */

            private String col_2;
            private String col_3;
            private String col_1;
            private String col_4;

            public String getCol_2() {
                return col_2;
            }

            public void setCol_2(String col_2) {
                this.col_2 = col_2;
            }

            public String getCol_3() {
                return col_3;
            }

            public void setCol_3(String col_3) {
                this.col_3 = col_3;
            }

            public String getCol_1() {
                return col_1;
            }

            public void setCol_1(String col_1) {
                this.col_1 = col_1;
            }

            public String getCol_4() {
                return col_4;
            }

            public void setCol_4(String col_4) {
                this.col_4 = col_4;
            }
        }

        public static class DatalistBeanX {
            /**
             * rank_total : 2420
             * range : 近一周
             * rank : 7
             * gr : +3.44
             * hs300_gr : -0.47
             */

            private String rank_total;
            private String range;
            private String rank;
            private String gr;
            private String hs300_gr;

            public String getRank_total() {
                return rank_total;
            }

            public void setRank_total(String rank_total) {
                this.rank_total = rank_total;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getGr() {
                return gr;
            }

            public void setGr(String gr) {
                this.gr = gr;
            }

            public String getHs300_gr() {
                return hs300_gr;
            }

            public void setHs300_gr(String hs300_gr) {
                this.hs300_gr = hs300_gr;
            }
        }
    }

    public static class BtnListBean {
        /**
         * operate_type : apply
         * btn_class : red
         * btn_str : 立即申购
         */

        private String operate_type;
        private String btn_class;
        private String btn_str;

        public String getOperate_type() {
            return operate_type;
        }

        public void setOperate_type(String operate_type) {
            this.operate_type = operate_type;
        }

        public String getBtn_class() {
            return btn_class;
        }

        public void setBtn_class(String btn_class) {
            this.btn_class = btn_class;
        }

        public String getBtn_str() {
            return btn_str;
        }

        public void setBtn_str(String btn_str) {
            this.btn_str = btn_str;
        }
    }
}
