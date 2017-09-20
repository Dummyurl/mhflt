package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class FundDetailProfileInfo {

    /**
     * subscribe_days :
     * unit_nav_title : 单位净值
     * subscribe_rate : 1.50
     * real_tab_name : 净值走势
     * purchase_str : 申购
     * is_show_stock : true
     * fund_name : 易方达消费行业股票型证券投资基金
     * redemp_status : 1
     * day_gr : +0.56
     * bond_enddate : 2017-01-20
     * stock_enddate : 2017-01-20
     * min_invest_amount : 10
     * stock_fund_detail : [{"stock_name":"小天鹅A","stock_code":"000418","holding_val":54297050,"nav_ratio":4.23,"holding_num":1679983},{"stock_name":"泸州老窖","stock_code":"000568","holding_val":112190595,"nav_ratio":8.74,"holding_num":3399715},{"stock_name":"格力电器","stock_code":"000651","holding_val":98480000,"nav_ratio":7.67,"holding_num":4000000},{"stock_name":"五粮液","stock_code":"000858","holding_val":117225552,"nav_ratio":9.13,"holding_num":3399813},{"stock_name":"上汽集团","stock_code":"600104","holding_val":93800000,"nav_ratio":7.31,"holding_num":4000000},{"stock_name":"伊利股份","stock_code":"600887","holding_val":70400000,"nav_ratio":5.48,"holding_num":4000000},{"stock_name":"贵州茅台","stock_code":"600519","holding_val":126308700,"nav_ratio":9.84,"holding_num":378000},{"stock_name":"索菲亚","stock_code":"002572","holding_val":54160000,"nav_ratio":4.22,"holding_num":1000000},{"stock_name":"隆鑫通用","stock_code":"603766","holding_val":58184000,"nav_ratio":4.53,"holding_num":2800000},{"stock_name":"美的集团","stock_code":"000333","holding_val":121131000,"nav_ratio":9.44,"holding_num":4300000}]
     * fund_type : 股票型
     * asset_config : {"other":0.19,"bond":0.77,"cash":11.64,"end_date":"2016-12-31","stock":87.4}
     * unit_nav_date : 04-17
     * trup_bank : 中国农业银行股份有限公司
     * unit_nav : 1.6330
     * fanlitou_rate : 0.30
     * fund_id : 674
     * history_unit_nav : {"datalist":[{"date":"日期","day_gr":"日涨幅","accum_nav":"累计净值","unit_nav":"单位净值"},{"date":"2017-04-17","day_gr":"+0.99%","accum_nav":"1.6330","unit_nav":"1.6330"},{"date":"2017-04-14","day_gr":"-0.86%","accum_nav":"1.6170","unit_nav":"1.6170"},{"date":"2017-04-13","day_gr":"+1.05%","accum_nav":"1.6310","unit_nav":"1.6310"}],"title":"历史净值"}
     * max_invest_amount : 20
     * fund_company : 易方达基金管理有限公司
     * income_tab_name : 收益率走势
     * is_show_bond : true
     * bond_fund_detail : [{"bond_name":"16国债18","tot_val_prop":0.78,"bond_code":"019546","tot_val":9966000,"bond_sum":100000}]
     * user_redem_status : 0
     * purchase_status : 1
     * fund_code : 110022
     * history_nav : {"datalist":[{"rank_total":478,"range":"近一周","rank":399,"gr":"-1.41","hs300_gr":"+0.16"},{"rank_total":477,"range":"近一月","rank":265,"gr":"+1.70","hs300_gr":"+1.48"},{"rank_total":462,"range":"近三月","rank":11,"gr":"+13.26","hs300_gr":"+5.78"}],"title":"业绩表现"}
     * status : 开放申购
     * fund_size : 12.94亿
     * invest_risk : 高
     * fund_abbr : 易方达消费行业股票
     * redeem_days :
     * estab_date : 2010-08-20
     * discount : 2.0
     * day_gr_title : 净值日增长率
     * is_already_bind_card : false
     */

    private String subscribe_days;
    private String unit_nav_title;
    private String subscribe_rate;
    private String real_tab_name;
    private String purchase_str;
    private boolean is_show_stock;
    private String fund_name;
    private int redemp_status;
    private String day_gr;
    private String bond_enddate;
    private String stock_enddate;
    private String min_invest_amount;
    private String fund_type;
    private AssetConfigBean asset_config;
    private String unit_nav_date;
    private String trup_bank;
    private String unit_nav;
    private String fanlitou_rate;
    private String fund_id;
    private HistoryUnitNavBean history_unit_nav;
    private String max_invest_amount;
    private String fund_company;
    private String income_tab_name;
    private boolean is_show_bond;
    private int user_redem_status;
    private int purchase_status;
    private String fund_code;
    private HistoryNavBean history_nav;
    private String status;
    private String fund_size;
    private String invest_risk;
    private String fund_abbr;
    private String redeem_days;
    private String estab_date;
    private String discount;
    private String day_gr_title;
    private boolean is_already_bind_card;
    private List<StockFundDetailBean> stock_fund_detail;
    private List<BondFundDetailBean> bond_fund_detail;
    private Boolean is_show_asset_config;

    public Boolean getIs_show_asset_config() {
        return is_show_asset_config;
    }

    public void setIs_show_asset_config(Boolean is_show_asset_config) {
        this.is_show_asset_config = is_show_asset_config;
    }

    public String getSubscribe_days() {
        return subscribe_days;
    }

    public void setSubscribe_days(String subscribe_days) {
        this.subscribe_days = subscribe_days;
    }

    public String getUnit_nav_title() {
        return unit_nav_title;
    }

    public void setUnit_nav_title(String unit_nav_title) {
        this.unit_nav_title = unit_nav_title;
    }

    public String getSubscribe_rate() {
        return subscribe_rate;
    }

    public void setSubscribe_rate(String subscribe_rate) {
        this.subscribe_rate = subscribe_rate;
    }

    public String getReal_tab_name() {
        return real_tab_name;
    }

    public void setReal_tab_name(String real_tab_name) {
        this.real_tab_name = real_tab_name;
    }

    public String getPurchase_str() {
        return purchase_str;
    }

    public void setPurchase_str(String purchase_str) {
        this.purchase_str = purchase_str;
    }

    public boolean is_show_stock() {
        return is_show_stock;
    }

    public void setIs_show_stock(boolean is_show_stock) {
        this.is_show_stock = is_show_stock;
    }

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public int getRedemp_status() {
        return redemp_status;
    }

    public void setRedemp_status(int redemp_status) {
        this.redemp_status = redemp_status;
    }

    public String getDay_gr() {
        return day_gr;
    }

    public void setDay_gr(String day_gr) {
        this.day_gr = day_gr;
    }

    public String getBond_enddate() {
        return bond_enddate;
    }

    public void setBond_enddate(String bond_enddate) {
        this.bond_enddate = bond_enddate;
    }

    public String getStock_enddate() {
        return stock_enddate;
    }

    public void setStock_enddate(String stock_enddate) {
        this.stock_enddate = stock_enddate;
    }

    public String getMin_invest_amount() {
        return min_invest_amount;
    }

    public void setMin_invest_amount(String min_invest_amount) {
        this.min_invest_amount = min_invest_amount;
    }

    public String getFund_type() {
        return fund_type;
    }

    public void setFund_type(String fund_type) {
        this.fund_type = fund_type;
    }

    public AssetConfigBean getAsset_config() {
        return asset_config;
    }

    public void setAsset_config(AssetConfigBean asset_config) {
        this.asset_config = asset_config;
    }

    public String getUnit_nav_date() {
        return unit_nav_date;
    }

    public void setUnit_nav_date(String unit_nav_date) {
        this.unit_nav_date = unit_nav_date;
    }

    public String getTrup_bank() {
        return trup_bank;
    }

    public void setTrup_bank(String trup_bank) {
        this.trup_bank = trup_bank;
    }

    public String getUnit_nav() {
        return unit_nav;
    }

    public void setUnit_nav(String unit_nav) {
        this.unit_nav = unit_nav;
    }

    public String getFanlitou_rate() {
        return fanlitou_rate;
    }

    public void setFanlitou_rate(String fanlitou_rate) {
        this.fanlitou_rate = fanlitou_rate;
    }

    public String getFund_id() {
        return fund_id;
    }

    public void setFund_id(String fund_id) {
        this.fund_id = fund_id;
    }

    public HistoryUnitNavBean getHistory_unit_nav() {
        return history_unit_nav;
    }

    public void setHistory_unit_nav(HistoryUnitNavBean history_unit_nav) {
        this.history_unit_nav = history_unit_nav;
    }

    public String getMax_invest_amount() {
        return max_invest_amount;
    }

    public void setMax_invest_amount(String max_invest_amount) {
        this.max_invest_amount = max_invest_amount;
    }

    public String getFund_company() {
        return fund_company;
    }

    public void setFund_company(String fund_company) {
        this.fund_company = fund_company;
    }

    public String getIncome_tab_name() {
        return income_tab_name;
    }

    public void setIncome_tab_name(String income_tab_name) {
        this.income_tab_name = income_tab_name;
    }

    public boolean is_show_bond() {
        return is_show_bond;
    }

    public void setIs_show_bond(boolean is_show_bond) {
        this.is_show_bond = is_show_bond;
    }

    public int getUser_redem_status() {
        return user_redem_status;
    }

    public void setUser_redem_status(int user_redem_status) {
        this.user_redem_status = user_redem_status;
    }

    public int getPurchase_status() {
        return purchase_status;
    }

    public void setPurchase_status(int purchase_status) {
        this.purchase_status = purchase_status;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }

    public HistoryNavBean getHistory_nav() {
        return history_nav;
    }

    public void setHistory_nav(HistoryNavBean history_nav) {
        this.history_nav = history_nav;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFund_size() {
        return fund_size;
    }

    public void setFund_size(String fund_size) {
        this.fund_size = fund_size;
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

    public String getRedeem_days() {
        return redeem_days;
    }

    public void setRedeem_days(String redeem_days) {
        this.redeem_days = redeem_days;
    }

    public String getEstab_date() {
        return estab_date;
    }

    public void setEstab_date(String estab_date) {
        this.estab_date = estab_date;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDay_gr_title() {
        return day_gr_title;
    }

    public void setDay_gr_title(String day_gr_title) {
        this.day_gr_title = day_gr_title;
    }

    public boolean isIs_already_bind_card() {
        return is_already_bind_card;
    }

    public void setIs_already_bind_card(boolean is_already_bind_card) {
        this.is_already_bind_card = is_already_bind_card;
    }

    public List<StockFundDetailBean> getStock_fund_detail() {
        return stock_fund_detail;
    }

    public void setStock_fund_detail(List<StockFundDetailBean> stock_fund_detail) {
        this.stock_fund_detail = stock_fund_detail;
    }

    public List<BondFundDetailBean> getBond_fund_detail() {
        return bond_fund_detail;
    }

    public void setBond_fund_detail(List<BondFundDetailBean> bond_fund_detail) {
        this.bond_fund_detail = bond_fund_detail;
    }

    public static class AssetConfigBean {
        /**
         * other : 0.19
         * bond : 0.77
         * cash : 11.64
         * end_date : 2016-12-31
         * stock : 87.4
         */

        private float other;
        private float bond;
        private float cash;
        private String end_date;
        private float stock;

        public float getOther() {
            return other;
        }

        public void setOther(float other) {
            this.other = other;
        }

        public float getBond() {
            return bond;
        }

        public void setBond(float bond) {
            this.bond = bond;
        }

        public float getCash() {
            return cash;
        }

        public void setCash(float cash) {
            this.cash = cash;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public float getStock() {
            return stock;
        }

        public void setStock(float stock) {
            this.stock = stock;
        }
    }

    public static class HistoryUnitNavBean {
        /**
         * datalist : [{"date":"日期","day_gr":"日涨幅","accum_nav":"累计净值","unit_nav":"单位净值"},{"date":"2017-04-17","day_gr":"+0.99%","accum_nav":"1.6330","unit_nav":"1.6330"},{"date":"2017-04-14","day_gr":"-0.86%","accum_nav":"1.6170","unit_nav":"1.6170"},{"date":"2017-04-13","day_gr":"+1.05%","accum_nav":"1.6310","unit_nav":"1.6310"}]
         * title : 历史净值
         */

        private String title;
        private List<DatalistBean> datalist;

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

        public static class DatalistBean {
            /**
             * date : 日期
             * day_gr : 日涨幅
             * accum_nav : 累计净值
             * unit_nav : 单位净值
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
         * datalist : [{"rank_total":478,"range":"近一周","rank":399,"gr":"-1.41","hs300_gr":"+0.16"},{"rank_total":477,"range":"近一月","rank":265,"gr":"+1.70","hs300_gr":"+1.48"},{"rank_total":462,"range":"近三月","rank":11,"gr":"+13.26","hs300_gr":"+5.78"}]
         * title : 业绩表现
         */

        private String title;
        private List<DatalistBeanX> datalist;

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

        public static class DatalistBeanX {
            /**
             * rank_total : 478
             * range : 近一周
             * rank : 399
             * gr : -1.41
             * hs300_gr : +0.16
             */

            private int rank_total;
            private String range;
            private int rank;
            private String gr;
            private String hs300_gr;

            public int getRank_total() {
                return rank_total;
            }

            public void setRank_total(int rank_total) {
                this.rank_total = rank_total;
            }

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
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

    public static class StockFundDetailBean {
        /**
         * stock_name : 小天鹅A
         * stock_code : 000418
         * holding_val : 54297050
         * nav_ratio : 4.23
         * holding_num : 1679983
         */

        private String stock_name;
        private String stock_code;
        private String holding_val;
        private String nav_ratio;
        private int holding_num;

        public String getStock_name() {
            return stock_name;
        }

        public void setStock_name(String stock_name) {
            this.stock_name = stock_name;
        }

        public String getStock_code() {
            return stock_code;
        }

        public void setStock_code(String stock_code) {
            this.stock_code = stock_code;
        }

        public String getHolding_val() {
            return holding_val;
        }

        public void setHolding_val(String holding_val) {
            this.holding_val = holding_val;
        }

        public String getNav_ratio() {
            return nav_ratio;
        }

        public void setNav_ratio(String nav_ratio) {
            this.nav_ratio = nav_ratio;
        }

        public int getHolding_num() {
            return holding_num;
        }

        public void setHolding_num(int holding_num) {
            this.holding_num = holding_num;
        }
    }

    public static class BondFundDetailBean {
        /**
         * bond_name : 16国债18
         * tot_val_prop : 0.78
         * bond_code : 019546
         * tot_val : 9966000
         * bond_sum : 100000
         */

        private String bond_name;
        private String tot_val_prop;
        private String bond_code;
        private String tot_val;
        private String bond_sum;

        public String getBond_name() {
            return bond_name;
        }

        public void setBond_name(String bond_name) {
            this.bond_name = bond_name;
        }

        public String getTot_val_prop() {
            return tot_val_prop;
        }

        public void setTot_val_prop(String tot_val_prop) {
            this.tot_val_prop = tot_val_prop;
        }

        public String getBond_code() {
            return bond_code;
        }

        public void setBond_code(String bond_code) {
            this.bond_code = bond_code;
        }

        public String getTot_val() {
            return tot_val;
        }

        public void setTot_val(String tot_val) {
            this.tot_val = tot_val;
        }

        public String getBond_sum() {
            return bond_sum;
        }

        public void setBond_sum(String bond_sum) {
            this.bond_sum = bond_sum;
        }
    }
}
