package com.changju.fanlitou.bean.fund;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangming on 2017/7/24.
 */

public class FundList {

    /**
     * show_currency_fund : false
     * values_filter : [{"code":"day_price_increase","name":"日涨幅"},{"code":"grand_income_percent_week","name":"近一周"},{"code":"grand_income_percent_month","name":"近一月"},{"code":"grand_income_percent_three_month","name":"近三月"},{"code":"grand_income_percent_six_month","name":"近半年"},{"code":"grand_income_percent_year","name":"近一年"},{"code":"grand_income_percent_till_now","name":"成立至今"}]
     * total : 2473
     * products : [{"bid_url":"/fund/detail/844/","fund_company":"华夏基金","apply_fee_discount":"1.50","has_flag":true,"fund_name":"华夏大盘精选混合","grand_income_percent_year":"+20.01%","grand_income_percent_month":"+6.98%","grand_income_percent_week":"-0.73%","day_price_increase":"-0.42%","fund_code":"000011","grand_income_percent_three_month":"+9.40%","grand_income_percent_till_now":"+1988.76%","fund_type":"混合型","invest_risk":"中风险","grand_income_percent_six_month":"+17.05%","fund_id":844,"latest_value":"12.0090","apply_fee":"1.50","alt_value":"-0.42%"},{"bid_url":"/fund/detail/2778/","fund_company":"嘉实基金","apply_fee_discount":"1.50","has_flag":true,"fund_name":"嘉实增长","grand_income_percent_year":"-11.64%","grand_income_percent_month":"+3.15%","grand_income_percent_week":"+0.70%","day_price_increase":"+0.15%","fund_code":"070002","grand_income_percent_three_month":"-6.74%","grand_income_percent_till_now":"+1069.37%","fund_type":"混合型","invest_risk":"中风险","grand_income_percent_six_month":"-7.61%","fund_id":2778,"latest_value":"8.5740","apply_fee":"1.50","alt_value":"+0.15%"}]
     * fund_type_filter : [{"code":"all","name":"全部"},{"code":"04","name":"货币型"},{"code":"01","name":"股票型"},{"code":"03","name":"混合型"},{"code":"02","name":"债券型"},{"code":"05","name":"指数型"},{"code":"06","name":"保本型"},{"code":"07","name":"ETF联接"},{"code":"08","name":"QDII"},{"code":"09","name":"LOF"},{"code":"98","name":"短期理财型"},{"code":"20","name":"基金组合"}]
     */

    private boolean show_currency_fund;
    private boolean has_more;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    private String total;
    private List<ValuesFilterBean> values_filter;
    private List<ProductsBean> products;
    private List<FundTypeFilterBean> fund_type_filter;
    private ArrayList<String> hot_search;

    public ArrayList<String> getHot_search() {
        return hot_search;
    }

    public void setHot_search(ArrayList<String> hot_search) {
        this.hot_search = hot_search;
    }

    public boolean isShow_currency_fund() {
        return show_currency_fund;
    }

    public void setShow_currency_fund(boolean show_currency_fund) {
        this.show_currency_fund = show_currency_fund;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ValuesFilterBean> getValues_filter() {
        return values_filter;
    }

    public void setValues_filter(List<ValuesFilterBean> values_filter) {
        this.values_filter = values_filter;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public List<FundTypeFilterBean> getFund_type_filter() {
        return fund_type_filter;
    }

    public void setFund_type_filter(List<FundTypeFilterBean> fund_type_filter) {
        this.fund_type_filter = fund_type_filter;
    }

    public static class ValuesFilterBean {
        /**
         * code : day_price_increase
         * name : 日涨幅
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProductsBean {
        /**
         * bid_url : /fund/detail/844/
         * fund_company : 华夏基金
         * apply_fee_discount : 1.50
         * has_flag : true
         * fund_name : 华夏大盘精选混合
         * grand_income_percent_year : +20.01%
         * grand_income_percent_month : +6.98%
         * grand_income_percent_week : -0.73%
         * day_price_increase : -0.42%
         * fund_code : 000011
         * grand_income_percent_three_month : +9.40%
         * grand_income_percent_till_now : +1988.76%
         * fund_type : 混合型
         * invest_risk : 中风险
         * grand_income_percent_six_month : +17.05%
         * fund_id : 844
         * latest_value : 12.0090
         * apply_fee : 1.50
         * alt_value : -0.42%
         */

        private String bid_url;
        private String fund_company;
        private String apply_fee_discount;
        private boolean has_flag;
        private String fund_name;
        private String grand_income_percent_year;
        private String grand_income_percent_month;
        private String grand_income_percent_week;
        private String day_price_increase;
        private String fund_code;
        private String grand_income_percent_three_month;
        private String grand_income_percent_till_now;
        private String fund_type;
        private String invest_risk;
        private String grand_income_percent_six_month;
        private int fund_id;
        private String latest_value;
        private String apply_fee;
        private String alt_value;

        public String getBid_url() {
            return bid_url;
        }

        public void setBid_url(String bid_url) {
            this.bid_url = bid_url;
        }

        public String getFund_company() {
            return fund_company;
        }

        public void setFund_company(String fund_company) {
            this.fund_company = fund_company;
        }

        public String getApply_fee_discount() {
            return apply_fee_discount;
        }

        public void setApply_fee_discount(String apply_fee_discount) {
            this.apply_fee_discount = apply_fee_discount;
        }

        public boolean isHas_flag() {
            return has_flag;
        }

        public void setHas_flag(boolean has_flag) {
            this.has_flag = has_flag;
        }

        public String getFund_name() {
            return fund_name;
        }

        public void setFund_name(String fund_name) {
            this.fund_name = fund_name;
        }

        public String getGrand_income_percent_year() {
            return grand_income_percent_year;
        }

        public void setGrand_income_percent_year(String grand_income_percent_year) {
            this.grand_income_percent_year = grand_income_percent_year;
        }

        public String getGrand_income_percent_month() {
            return grand_income_percent_month;
        }

        public void setGrand_income_percent_month(String grand_income_percent_month) {
            this.grand_income_percent_month = grand_income_percent_month;
        }

        public String getGrand_income_percent_week() {
            return grand_income_percent_week;
        }

        public void setGrand_income_percent_week(String grand_income_percent_week) {
            this.grand_income_percent_week = grand_income_percent_week;
        }

        public String getDay_price_increase() {
            return day_price_increase;
        }

        public void setDay_price_increase(String day_price_increase) {
            this.day_price_increase = day_price_increase;
        }

        public String getFund_code() {
            return fund_code;
        }

        public void setFund_code(String fund_code) {
            this.fund_code = fund_code;
        }

        public String getGrand_income_percent_three_month() {
            return grand_income_percent_three_month;
        }

        public void setGrand_income_percent_three_month(String grand_income_percent_three_month) {
            this.grand_income_percent_three_month = grand_income_percent_three_month;
        }

        public String getGrand_income_percent_till_now() {
            return grand_income_percent_till_now;
        }

        public void setGrand_income_percent_till_now(String grand_income_percent_till_now) {
            this.grand_income_percent_till_now = grand_income_percent_till_now;
        }

        public String getFund_type() {
            return fund_type;
        }

        public void setFund_type(String fund_type) {
            this.fund_type = fund_type;
        }

        public String getInvest_risk() {
            return invest_risk;
        }

        public void setInvest_risk(String invest_risk) {
            this.invest_risk = invest_risk;
        }

        public String getGrand_income_percent_six_month() {
            return grand_income_percent_six_month;
        }

        public void setGrand_income_percent_six_month(String grand_income_percent_six_month) {
            this.grand_income_percent_six_month = grand_income_percent_six_month;
        }

        public int getFund_id() {
            return fund_id;
        }

        public void setFund_id(int fund_id) {
            this.fund_id = fund_id;
        }

        public String getLatest_value() {
            return latest_value;
        }

        public void setLatest_value(String latest_value) {
            this.latest_value = latest_value;
        }

        public String getApply_fee() {
            return apply_fee;
        }

        public void setApply_fee(String apply_fee) {
            this.apply_fee = apply_fee;
        }

        public String getAlt_value() {
            return alt_value;
        }

        public void setAlt_value(String alt_value) {
            this.alt_value = alt_value;
        }
    }

    public static class FundTypeFilterBean {
        /**
         * code : all
         * name : 全部
         */

        private String code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
