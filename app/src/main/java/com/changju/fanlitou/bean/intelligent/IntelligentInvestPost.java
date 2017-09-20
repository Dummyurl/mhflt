package com.changju.fanlitou.bean.intelligent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chengww on 2017/7/10.
 */

public class IntelligentInvestPost implements Parcelable{

    /**
     * msg : 成功
     * is_bid_fulled : false
     * redirect_url : https://test-api-open.gaosouyi.com/Fanlitou/invest?from=8969fbb599a060da7d9da402d68b1e4c&uid=fd62b633c58a8356429c1d5eec7a4b0a&bid_id=21ba2aa09f6133d779cded2fc7f03932&sign=b903f5a17a616fc138d6ee0db5fa3d626b9d60b9891b1550890e305934aad11e0d25a948cccb4d6c019ef16adf45dcf6&fcode=31742cfe1593193e9372985e62b4927d&f_order_no=65953fdc99868e162caea27f391ff2393b4a0fd08e344ed812574d842b075599&amount=fbc60e97b1e3d9a78d4260ba653b2a94&t=e2be2884803687ad58765c77b118acfa
     * success : true
     * flt_order_no : 1762927627284838
     */

    private String msg;
    private boolean is_bid_fulled;
    private String redirect_url;
    private boolean success;
    private String flt_order_no;

    protected IntelligentInvestPost(Parcel in) {
        msg = in.readString();
        is_bid_fulled = in.readByte() != 0;
        redirect_url = in.readString();
        success = in.readByte() != 0;
        flt_order_no = in.readString();
    }

    public static final Creator<IntelligentInvestPost> CREATOR = new Creator<IntelligentInvestPost>() {
        @Override
        public IntelligentInvestPost createFromParcel(Parcel in) {
            return new IntelligentInvestPost(in);
        }

        @Override
        public IntelligentInvestPost[] newArray(int size) {
            return new IntelligentInvestPost[size];
        }
    };

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIs_bid_fulled() {
        return is_bid_fulled;
    }

    public void setIs_bid_fulled(boolean is_bid_fulled) {
        this.is_bid_fulled = is_bid_fulled;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFlt_order_no() {
        return flt_order_no;
    }

    public void setFlt_order_no(String flt_order_no) {
        this.flt_order_no = flt_order_no;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeByte((byte) (is_bid_fulled ? 1 : 0));
        dest.writeString(redirect_url);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeString(flt_order_no);
    }
}
