package com.changju.fanlitou.bean.intelligent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chengww on 2017/7/10.
 */

public class Recharge implements Parcelable{

    /**
     * msg : 充值失败: 充值信息提交失败.原因:验证码不匹配.汇付宝单号:B17062956316773D
     * redirect_url :
     * account_blance : 0
     * success : false
     */

    private String msg;
    private String redirect_url;
    private String account_blance;
    private boolean success;
    private boolean is_jump;
    private String flt_order_no;

    public static final Creator<Recharge> CREATOR = new Creator<Recharge>() {
        @Override
        public Recharge createFromParcel(Parcel in) {
            return new Recharge(in);
        }

        @Override
        public Recharge[] newArray(int size) {
            return new Recharge[size];
        }
    };

    public boolean is_jump() {
        return is_jump;
    }

    public void setIs_jump(boolean is_jump) {
        this.is_jump = is_jump;
    }

    protected Recharge(Parcel in) {
        msg = in.readString();
        redirect_url = in.readString();
        account_blance = in.readString();
        success = in.readByte() != 0;
        flt_order_no = in.readString();
    }

    public String getFlt_order_no() {
        return flt_order_no;
    }

    public void setFlt_order_no(String flt_order_no) {
        this.flt_order_no = flt_order_no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public String getAccount_blance() {
        return account_blance;
    }

    public void setAccount_blance(String account_blance) {
        this.account_blance = account_blance;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(redirect_url);
        dest.writeString(account_blance);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeByte((byte) (is_jump ? 1 : 0));
        dest.writeString(flt_order_no);
    }
}
