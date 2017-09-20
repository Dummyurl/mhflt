package com.changju.fanlitou.bean.discover;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengww on 2017/5/8.
 */

public class Platform implements Serializable{

    /**
     * platform_list : [{"name_pinyin_full":"yibaijinrong","profit_info":{"total":4,"is_show":true,"type":"profit","profit_list":[{"name":"新手标","value":"12.5%"},{"name":"红包收益","value":"13.8417%"},{"name":"最高收益","value":"18.0%"},{"name":"最低收益","value":"6.0%"}]},"short_name":"pp100","platform_bonus_value":1,"is_promotion":false,"name_pinyin_abr":"ybjr","is_in_activity":false,"name":"壹佰金融","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/a02a99e3/app-a02a99e3-1b21-4562-9b5c-567ebfb88c46-1490927945.png","platform_id":21,"platform_type":"固收","is_can_auto_register":true,"is_checked":false,"tag_list":[{"name":"上市系","style":"blue"},{"name":"平台活动","style":"red"},{"name":"固收","style":"yellow"}],"first_invest_redpacket_award_info":{"min_duration_days":30,"type":"first","red_packet_amount":20,"is_show":true,"min_invest_amount":3000,"img_url":"https://o0s106hgi.qnssl.com/billion/img/billion_first.png"}},{"name_pinyin_full":"jiurongwang","profit_info":{"total":4,"is_show":true,"type":"profit","profit_list":[{"name":"最低收益","value":"10.0%"},{"name":"新手标","value":"13.0%"},{"name":"红包收益","value":"13.8250%"},{"name":"最高收益","value":"18.0%"}]},"short_name":"jiurong","platform_bonus_value":1,"is_promotion":false,"name_pinyin_abr":"jrw","is_in_activity":false,"name":"玖融网","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/41d48ab2/app-41d48ab2-9dae-4fee-b54c-dd55037bfce4-1490927124.png","platform_id":54,"platform_type":"固收","is_can_auto_register":true,"is_checked":false,"tag_list":[{"name":"上市系","style":"blue"},{"name":"平台活动","style":"red"},{"name":"固收","style":"yellow"}],"first_invest_redpacket_award_info":{"min_duration_days":0,"type":"hot","red_packet_amount":0,"is_show":true,"min_invest_amount":0,"img_url":"https://o0s106hgi.qnssl.com/billion/img/billion_hot.png"}}]
     * hot_search : [{"name":"爱钱帮"},{"name":"律金金融"}]
     * total : 2
     */

    private String total;
    private List<PlatformListBean> platform_list;
    private List<HotSearchBean> hot_search;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<PlatformListBean> getPlatform_list() {
        return platform_list;
    }

    public void setPlatform_list(List<PlatformListBean> platform_list) {
        this.platform_list = platform_list;
    }

    public List<HotSearchBean> getHot_search() {
        return hot_search;
    }

    public void setHot_search(List<HotSearchBean> hot_search) {
        this.hot_search = hot_search;
    }

    public static class PlatformListBean implements Serializable{
        /**
         * name_pinyin_full : yibaijinrong
         * profit_info : {"total":4,"is_show":true,"type":"profit","profit_list":[{"name":"新手标","value":"12.5%"},{"name":"红包收益","value":"13.8417%"},{"name":"最高收益","value":"18.0%"},{"name":"最低收益","value":"6.0%"}]}
         * short_name : pp100
         * platform_bonus_value : 1
         * is_promotion : false
         * name_pinyin_abr : ybjr
         * is_in_activity : false
         * name : 壹佰金融
         * logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/a02a99e3/app-a02a99e3-1b21-4562-9b5c-567ebfb88c46-1490927945.png
         * platform_id : 21
         * platform_type : 固收
         * is_can_auto_register : true
         * is_checked : false
         * tag_list : [{"name":"上市系","style":"blue"},{"name":"平台活动","style":"red"},{"name":"固收","style":"yellow"}]
         * first_invest_redpacket_award_info : {"min_duration_days":30,"type":"first","red_packet_amount":20,"is_show":true,"min_invest_amount":3000,"img_url":"https://o0s106hgi.qnssl.com/billion/img/billion_first.png"}
         */

        private String name_pinyin_full;
        private ProfitInfoBean profit_info;
        private String short_name;
        private String platform_bonus_value;
        private boolean is_promotion;
        private String name_pinyin_abr;
        private boolean is_in_activity;
        private String name;
        private String logo_app;
        private int platform_id;
        private String platform_type;
        private boolean is_can_auto_register;
        private boolean is_checked;
        private FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info;
        private List<TagListBean> tag_list;

        public String getName_pinyin_full() {
            return name_pinyin_full;
        }

        public void setName_pinyin_full(String name_pinyin_full) {
            this.name_pinyin_full = name_pinyin_full;
        }

        public ProfitInfoBean getProfit_info() {
            return profit_info;
        }

        public void setProfit_info(ProfitInfoBean profit_info) {
            this.profit_info = profit_info;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String getPlatform_bonus_value() {
            return platform_bonus_value;
        }

        public void setPlatform_bonus_value(String platform_bonus_value) {
            this.platform_bonus_value = platform_bonus_value;
        }

        public boolean isIs_promotion() {
            return is_promotion;
        }

        public void setIs_promotion(boolean is_promotion) {
            this.is_promotion = is_promotion;
        }

        public String getName_pinyin_abr() {
            return name_pinyin_abr;
        }

        public void setName_pinyin_abr(String name_pinyin_abr) {
            this.name_pinyin_abr = name_pinyin_abr;
        }

        public boolean isIs_in_activity() {
            return is_in_activity;
        }

        public void setIs_in_activity(boolean is_in_activity) {
            this.is_in_activity = is_in_activity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo_app() {
            return logo_app;
        }

        public void setLogo_app(String logo_app) {
            this.logo_app = logo_app;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getPlatform_type() {
            return platform_type;
        }

        public void setPlatform_type(String platform_type) {
            this.platform_type = platform_type;
        }

        public boolean isIs_can_auto_register() {
            return is_can_auto_register;
        }

        public void setIs_can_auto_register(boolean is_can_auto_register) {
            this.is_can_auto_register = is_can_auto_register;
        }

        public boolean isIs_checked() {
            return is_checked;
        }

        public void setIs_checked(boolean is_checked) {
            this.is_checked = is_checked;
        }

        public FirstInvestRedpacketAwardInfoBean getFirst_invest_redpacket_award_info() {
            return first_invest_redpacket_award_info;
        }

        public void setFirst_invest_redpacket_award_info(FirstInvestRedpacketAwardInfoBean first_invest_redpacket_award_info) {
            this.first_invest_redpacket_award_info = first_invest_redpacket_award_info;
        }

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class ProfitInfoBean implements Serializable{
            /**
             * total : 4
             * is_show : true
             * type : profit
             * profit_list : [{"name":"新手标","value":"12.5%"},{"name":"红包收益","value":"13.8417%"},{"name":"最高收益","value":"18.0%"},{"name":"最低收益","value":"6.0%"}]
             */

            private int total;
            private boolean is_show;
            private String type;
            private List<ProfitListBean> profit_list;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public boolean isIs_show() {
                return is_show;
            }

            public void setIs_show(boolean is_show) {
                this.is_show = is_show;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ProfitListBean> getProfit_list() {
                return profit_list;
            }

            public void setProfit_list(List<ProfitListBean> profit_list) {
                this.profit_list = profit_list;
            }

            public static class ProfitListBean implements Serializable{
                /**
                 * name : 新手标
                 * value : 12.5%
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class FirstInvestRedpacketAwardInfoBean implements Serializable{
            /**
             * min_duration_days : 30
             * type : first
             * red_packet_amount : 20
             * is_show : true
             * min_invest_amount : 3000
             * img_url : https://o0s106hgi.qnssl.com/billion/img/billion_first.png
             */

            private String min_duration_days;
            private String type;
            private String red_packet_amount;
            private boolean is_show;
            private String min_invest_amount;
            private String img_url;

            public String getMin_duration_days() {
                return min_duration_days;
            }

            public void setMin_duration_days(String min_duration_days) {
                this.min_duration_days = min_duration_days;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRed_packet_amount() {
                return red_packet_amount;
            }

            public void setRed_packet_amount(String red_packet_amount) {
                this.red_packet_amount = red_packet_amount;
            }

            public boolean isIs_show() {
                return is_show;
            }

            public void setIs_show(boolean is_show) {
                this.is_show = is_show;
            }

            public String getMin_invest_amount() {
                return min_invest_amount;
            }

            public void setMin_invest_amount(String min_invest_amount) {
                this.min_invest_amount = min_invest_amount;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }

        public static class TagListBean implements Serializable{
            /**
             * name : 上市系
             * style : blue
             */

            private String tag_name;
            private String style;

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getStyle() {
                return style;
            }

            public void setStyle(String style) {
                this.style = style;
            }
        }
    }

    public static class HotSearchBean implements Serializable {
        /**
         * name : 爱钱帮
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}