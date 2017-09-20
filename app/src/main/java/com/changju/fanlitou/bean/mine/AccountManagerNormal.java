package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/7/13.
 */

public class AccountManagerNormal {

    /**
     * total_count : 36
     * register_record : [{"platform_name":"高搜易","remark":"自动注册","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/3dea7cce/app-3dea7cce-4037-4061-99d2-455046860f75-1493369106.png","is_enjoy_bonus":"享返利","register_time":"2017-05-24","platform_url":"/m/mobile_platform/78/gaosouyi/","bonus_red":false}]
     * is_has_intelligent_account : true
     */

    private int total_count;
    private boolean is_has_intelligent_account;
    private List<RegisterRecordBean> register_record;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIs_has_intelligent_account() {
        return is_has_intelligent_account;
    }

    public void setIs_has_intelligent_account(boolean is_has_intelligent_account) {
        this.is_has_intelligent_account = is_has_intelligent_account;
    }

    public List<RegisterRecordBean> getRegister_record() {
        return register_record;
    }

    public void setRegister_record(List<RegisterRecordBean> register_record) {
        this.register_record = register_record;
    }

    public static class RegisterRecordBean {
        /**
         * platform_name : 高搜易
         * remark : 自动注册
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/3dea7cce/app-3dea7cce-4037-4061-99d2-455046860f75-1493369106.png
         * is_enjoy_bonus : 享返利
         * register_time : 2017-05-24
         * platform_url : /m/mobile_platform/78/gaosouyi/
         * bonus_red : false
         */

        private String platform_name;
        private String remark;
        private String platform_logo;
        private String is_enjoy_bonus;
        private String register_time;
        private String platform_url;
        private int platform_id;
        private boolean bonus_red;

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public String getIs_enjoy_bonus() {
            return is_enjoy_bonus;
        }

        public void setIs_enjoy_bonus(String is_enjoy_bonus) {
            this.is_enjoy_bonus = is_enjoy_bonus;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public String getPlatform_url() {
            return platform_url;
        }

        public void setPlatform_url(String platform_url) {
            this.platform_url = platform_url;
        }

        public boolean isBonus_red() {
            return bonus_red;
        }

        public void setBonus_red(boolean bonus_red) {
            this.bonus_red = bonus_red;
        }
    }
}
