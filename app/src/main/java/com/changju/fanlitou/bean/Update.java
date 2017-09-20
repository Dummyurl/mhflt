package com.changju.fanlitou.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by chengww on 2017/6/26.
 */

public class Update implements Parcelable{


    /**
     * url : http://7xnupi.com2.z0.glb.qiniucdn.com/app-_360-release_1.0.0.apk
     * update_message_list : ["·全新邀请有礼，一键分享到微信、QQ和微博","·基金购买"]
     * version_name : 1.0.3
     * version_code : 1.0.3
     * update_type : 1
     */

    private String url;
    private String version_name;
    private String version_code;
    private int update_type;
    private List<String> update_message_list;

    protected Update(Parcel in) {
        url = in.readString();
        version_name = in.readString();
        version_code = in.readString();
        update_type = in.readInt();
        update_message_list = in.createStringArrayList();
    }

    public static final Creator<Update> CREATOR = new Creator<Update>() {
        @Override
        public Update createFromParcel(Parcel in) {
            return new Update(in);
        }

        @Override
        public Update[] newArray(int size) {
            return new Update[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public int getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(int update_type) {
        this.update_type = update_type;
    }

    public List<String> getUpdate_message_list() {
        return update_message_list;
    }

    public void setUpdate_message_list(List<String> update_message_list) {
        this.update_message_list = update_message_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(version_name);
        dest.writeString(version_code);
        dest.writeInt(update_type);
        dest.writeStringList(update_message_list);
    }
}
