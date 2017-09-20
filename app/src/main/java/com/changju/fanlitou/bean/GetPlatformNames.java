package com.changju.fanlitou.bean;

import java.util.List;

/**
 * Created by chengww on 2017/5/24.
 */

public class GetPlatformNames {

    private List<PlatformNamesBean> platform_names;

    public List<PlatformNamesBean> getPlatform_names() {
        return platform_names;
    }

    public void setPlatform_names(List<PlatformNamesBean> platform_names) {
        this.platform_names = platform_names;
    }

    public static class PlatformNamesBean {
        /**
         * has_crowdfunding : false
         * name : 广信贷
         * short_name : guangxindai
         * has_gold : false
         */

        private boolean has_crowdfunding;
        private String name;
        private String short_name;
        private boolean has_gold;

        public boolean isHas_crowdfunding() {
            return has_crowdfunding;
        }

        public void setHas_crowdfunding(boolean has_crowdfunding) {
            this.has_crowdfunding = has_crowdfunding;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public boolean isHas_gold() {
            return has_gold;
        }

        public void setHas_gold(boolean has_gold) {
            this.has_gold = has_gold;
        }
    }
}
