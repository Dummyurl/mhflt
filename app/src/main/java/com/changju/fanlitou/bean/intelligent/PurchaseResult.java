package com.changju.fanlitou.bean.intelligent;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by chengww on 2017/7/11.
 */

public class PurchaseResult implements Parcelable{

    /**
     * status : FAIL
     * settle_date_after_bid_fulled : 2
     * bid_name : 信托宝•省心567期
     * msg : 投资失败
     * other_available_bid_list : [{"pay_type_str":"一次性还本付息","bid_interest":8.5,"bid_id":357234,"duration_unit_str":"天","min_invest_amount":5000,"duration":182,"bid_name":"瑞盈宝53期","platform_shortname":"gaosouyi","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png","progress_percent":1.6,"platform_name":"高搜易","bonus_interest":1},{"pay_type_str":"一次性还本付息","bid_interest":9.9,"bid_id":355023,"duration_unit_str":"天","min_invest_amount":0,"duration":11,"bid_name":"彩虹计划 - 11个月","platform_shortname":"ppdai","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/407a2f94/main-407a2f94-cca5-4594-bcd6-3375ad5d16d5-1478662736.png","progress_percent":0,"platform_name":"拍拍贷","bonus_interest":1},{"pay_type_str":"一次性还本付息","bid_interest":9.9,"bid_id":357237,"duration_unit_str":"天","min_invest_amount":10,"duration":91,"bid_name":"信托宝\u2022省心567期","platform_shortname":"gaosouyi","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png","progress_percent":0.4,"platform_name":"高搜易","bonus_interest":1}]
     * bid_amount : 111.00
     * order_number : 177738662522463
     * is_bid_fulled : false
     * platform_info : {"platform_id":78,"platform_name":"高搜易","platform_logo":"https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png","platform_shortname":"gaosouyi"}
     */

    private String status;
    private int settle_date_after_bid_fulled;
    private String bid_name;
    private String msg;
    private String bid_amount;
    private String order_number;
    private boolean is_bid_fulled;
    private PlatformInfoBean platform_info;
    private List<OtherAvailableBidListBean> other_available_bid_list;

    protected PurchaseResult(Parcel in) {
        status = in.readString();
        settle_date_after_bid_fulled = in.readInt();
        bid_name = in.readString();
        msg = in.readString();
        bid_amount = in.readString();
        order_number = in.readString();
        is_bid_fulled = in.readByte() != 0;
        platform_info = in.readParcelable(PlatformInfoBean.class.getClassLoader());
        other_available_bid_list = in.createTypedArrayList(OtherAvailableBidListBean.CREATOR);
    }

    public static final Creator<PurchaseResult> CREATOR = new Creator<PurchaseResult>() {
        @Override
        public PurchaseResult createFromParcel(Parcel in) {
            return new PurchaseResult(in);
        }

        @Override
        public PurchaseResult[] newArray(int size) {
            return new PurchaseResult[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSettle_date_after_bid_fulled() {
        return settle_date_after_bid_fulled;
    }

    public void setSettle_date_after_bid_fulled(int settle_date_after_bid_fulled) {
        this.settle_date_after_bid_fulled = settle_date_after_bid_fulled;
    }

    public String getBid_name() {
        return bid_name;
    }

    public void setBid_name(String bid_name) {
        this.bid_name = bid_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(String bid_amount) {
        this.bid_amount = bid_amount;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public boolean isIs_bid_fulled() {
        return is_bid_fulled;
    }

    public void setIs_bid_fulled(boolean is_bid_fulled) {
        this.is_bid_fulled = is_bid_fulled;
    }

    public PlatformInfoBean getPlatform_info() {
        return platform_info;
    }

    public void setPlatform_info(PlatformInfoBean platform_info) {
        this.platform_info = platform_info;
    }

    public List<OtherAvailableBidListBean> getOther_available_bid_list() {
        return other_available_bid_list;
    }

    public void setOther_available_bid_list(List<OtherAvailableBidListBean> other_available_bid_list) {
        this.other_available_bid_list = other_available_bid_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(settle_date_after_bid_fulled);
        dest.writeString(bid_name);
        dest.writeString(msg);
        dest.writeString(bid_amount);
        dest.writeString(order_number);
        dest.writeByte((byte) (is_bid_fulled ? 1 : 0));
        dest.writeParcelable(platform_info, flags);
        dest.writeTypedList(other_available_bid_list);
    }

    public static class PlatformInfoBean implements Parcelable{
        /**
         * platform_id : 78
         * platform_name : 高搜易
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png
         * platform_shortname : gaosouyi
         */

        private int platform_id;
        private String platform_name;
        private String platform_logo;
        private String platform_shortname;

        protected PlatformInfoBean(Parcel in) {
            platform_id = in.readInt();
            platform_name = in.readString();
            platform_logo = in.readString();
            platform_shortname = in.readString();
        }

        public static final Creator<PlatformInfoBean> CREATOR = new Creator<PlatformInfoBean>() {
            @Override
            public PlatformInfoBean createFromParcel(Parcel in) {
                return new PlatformInfoBean(in);
            }

            @Override
            public PlatformInfoBean[] newArray(int size) {
                return new PlatformInfoBean[size];
            }
        };

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

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public String getPlatform_shortname() {
            return platform_shortname;
        }

        public void setPlatform_shortname(String platform_shortname) {
            this.platform_shortname = platform_shortname;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(platform_id);
            dest.writeString(platform_name);
            dest.writeString(platform_logo);
            dest.writeString(platform_shortname);
        }
    }

    public static class OtherAvailableBidListBean implements Parcelable{
        /**
         * pay_type_str : 一次性还本付息
         * bid_interest : 8.5
         * bid_id : 357234
         * duration_unit_str : 天
         * min_invest_amount : 5000
         * duration : 182
         * bid_name : 瑞盈宝53期
         * platform_shortname : gaosouyi
         * platform_logo : https://o0s106hgi.qnssl.com/media/plat-logo/556d09c8/main-556d09c8-108d-4b96-97fb-eb5cd2167bd8-1495794296.png
         * progress_percent : 1.6
         * platform_name : 高搜易
         * bonus_interest : 1
         */

        private String pay_type_str;
        private String bid_interest;
        private int bid_id;
        private String duration_unit_str;
        private String min_invest_amount;
        private String duration;
        private String bid_name;
        private String platform_shortname;
        private String platform_logo;
        private float progress_percent;
        private String platform_name;
        private String bonus_interest;

        protected OtherAvailableBidListBean(Parcel in) {
            pay_type_str = in.readString();
            bid_interest = in.readString();
            bid_id = in.readInt();
            duration_unit_str = in.readString();
            min_invest_amount = in.readString();
            duration = in.readString();
            bid_name = in.readString();
            platform_shortname = in.readString();
            platform_logo = in.readString();
            progress_percent = in.readFloat();
            platform_name = in.readString();
            bonus_interest = in.readString();
        }

        public static final Creator<OtherAvailableBidListBean> CREATOR = new Creator<OtherAvailableBidListBean>() {
            @Override
            public OtherAvailableBidListBean createFromParcel(Parcel in) {
                return new OtherAvailableBidListBean(in);
            }

            @Override
            public OtherAvailableBidListBean[] newArray(int size) {
                return new OtherAvailableBidListBean[size];
            }
        };

        public String getPay_type_str() {
            return pay_type_str;
        }

        public void setPay_type_str(String pay_type_str) {
            this.pay_type_str = pay_type_str;
        }

        public String getBid_interest() {
            return bid_interest;
        }

        public void setBid_interest(String bid_interest) {
            this.bid_interest = bid_interest;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getDuration_unit_str() {
            return duration_unit_str;
        }

        public void setDuration_unit_str(String duration_unit_str) {
            this.duration_unit_str = duration_unit_str;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getBid_name() {
            return bid_name;
        }

        public void setBid_name(String bid_name) {
            this.bid_name = bid_name;
        }

        public String getPlatform_shortname() {
            return platform_shortname;
        }

        public void setPlatform_shortname(String platform_shortname) {
            this.platform_shortname = platform_shortname;
        }

        public String getPlatform_logo() {
            return platform_logo;
        }

        public void setPlatform_logo(String platform_logo) {
            this.platform_logo = platform_logo;
        }

        public float getProgress_percent() {
            return progress_percent;
        }

        public void setProgress_percent(float progress_percent) {
            this.progress_percent = progress_percent;
        }

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getBonus_interest() {
            return bonus_interest;
        }

        public void setBonus_interest(String bonus_interest) {
            this.bonus_interest = bonus_interest;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(pay_type_str);
            dest.writeString(bid_interest);
            dest.writeInt(bid_id);
            dest.writeString(duration_unit_str);
            dest.writeString(min_invest_amount);
            dest.writeString(duration);
            dest.writeString(bid_name);
            dest.writeString(platform_shortname);
            dest.writeString(platform_logo);
            dest.writeFloat(progress_percent);
            dest.writeString(platform_name);
            dest.writeString(bonus_interest);
        }
    }
}
