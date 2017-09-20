package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/7/17.
 */

public class HistoryUnitNav {

    /**
     * total : 306
     * data_list : [{"date":"日期","day_gr":"日涨幅","accum_nav":"累计净值","unit_nav":"单位净值"},{"date":"2017-07-05","day_gr":"+2.86%","accum_nav":"2.2600","unit_nav":"1.9750"},{"date":"2017-07-04","day_gr":"-0.98%","accum_nav":"2.2050","unit_nav":"1.9200"},{"date":"2017-07-03","day_gr":"0.00%","accum_nav":"2.2240","unit_nav":"1.9390"},{"date":"2017-06-30","day_gr":"+0.83%","accum_nav":"2.2240","unit_nav":"1.9390"},{"date":"2017-06-29","day_gr":"+0.05%","accum_nav":"2.2080","unit_nav":"1.9230"},{"date":"2017-06-28","day_gr":"-0.77%","accum_nav":"2.2070","unit_nav":"1.9220"},{"date":"2017-06-27","day_gr":"-0.31%","accum_nav":"2.2220","unit_nav":"1.9370"},{"date":"2017-06-26","day_gr":"+1.99%","accum_nav":"2.2280","unit_nav":"1.9430"},{"date":"2017-06-23","day_gr":"+0.74%","accum_nav":"2.1900","unit_nav":"1.9050"},{"date":"2017-06-22","day_gr":"-0.84%","accum_nav":"2.1760","unit_nav":"1.8910"}]
     * data_list_title : {"col_2":"单位净值","col_3":"累计净值","col_1":"日期","col_4":"日涨幅"}
     * title : 历史净值
     */

    private int total;
    private DataListTitleBean data_list_title;
    private String title;
    private List<DataListBean> data_list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public List<DataListBean> getData_list() {
        return data_list;
    }

    public void setData_list(List<DataListBean> data_list) {
        this.data_list = data_list;
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

    public static class DataListBean {
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
