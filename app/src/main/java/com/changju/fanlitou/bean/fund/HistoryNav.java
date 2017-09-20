package com.changju.fanlitou.bean.fund;

import java.util.List;

/**
 * Created by zhangming on 2017/7/17.
 */

public class HistoryNav {

    /**
     * datalist : [{"rank_total":478,"range":"近一周","rank":399,"gr":"-1.41","hs300_gr":"+0.16"},{"rank_total":477,"range":"近一月","rank":265,"gr":"+1.70","hs300_gr":"+1.48"},{"rank_total":462,"range":"近三月","rank":11,"gr":"+13.26","hs300_gr":"+5.78"},{"rank_total":438,"range":"近半年","rank":12,"gr":"+17.38","hs300_gr":"+6.35"},{"rank_total":413,"range":"近一年","rank":2,"gr":"+33.94","hs300_gr":"+9.04"},{"rank_total":480,"range":"成立至今","rank":48,"gr":"+61.40","hs300_gr":"+249.74"}]
     * title : 业绩表现
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
         * rank_total : 478
         * range : 近一周
         * rank : 399
         * gr : -1.41
         * hs300_gr : +0.16
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
