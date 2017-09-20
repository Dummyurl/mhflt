package com.changju.fanlitou.bean.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by chengww on 2017/6/15.
 */

public class MainMenu implements Parcelable{

    /**
     * activity_list : [{"activity_url":"","name":"618","activity_logo":""}]
     * menu_type : single
     * menu_logo : https://o0s106hgi.qnssl.com/activity/menu/logo/activity_menu_logo.png
     * is_show : true
     * menu_name : 活动
     */

    private String menu_type;
    private String menu_logo;
    private boolean is_show;
    private String menu_name;
    private List<ActivityListBean> activity_list;

    protected MainMenu(Parcel in) {
        menu_type = in.readString();
        menu_logo = in.readString();
        is_show = in.readByte() != 0;
        menu_name = in.readString();
        activity_list = in.createTypedArrayList(ActivityListBean.CREATOR);
    }

    public static final Creator<MainMenu> CREATOR = new Creator<MainMenu>() {
        @Override
        public MainMenu createFromParcel(Parcel in) {
            return new MainMenu(in);
        }

        @Override
        public MainMenu[] newArray(int size) {
            return new MainMenu[size];
        }
    };

    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getMenu_logo() {
        return menu_logo;
    }

    public void setMenu_logo(String menu_logo) {
        this.menu_logo = menu_logo;
    }

    public boolean isIs_show() {
        return is_show;
    }

    public void setIs_show(boolean is_show) {
        this.is_show = is_show;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public List<ActivityListBean> getActivity_list() {
        return activity_list;
    }

    public void setActivity_list(List<ActivityListBean> activity_list) {
        this.activity_list = activity_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menu_type);
        dest.writeString(menu_logo);
        dest.writeByte((byte) (is_show ? 1 : 0));
        dest.writeString(menu_name);
        dest.writeTypedList(activity_list);
    }

    public static class ActivityListBean implements Parcelable{
        /**
         * activity_url :
         * name : 618
         * activity_logo :
         */

        private String activity_url;
        private String name;
        private String activity_logo;

        protected ActivityListBean(Parcel in) {
            activity_url = in.readString();
            name = in.readString();
            activity_logo = in.readString();
        }

        public static final Creator<ActivityListBean> CREATOR = new Creator<ActivityListBean>() {
            @Override
            public ActivityListBean createFromParcel(Parcel in) {
                return new ActivityListBean(in);
            }

            @Override
            public ActivityListBean[] newArray(int size) {
                return new ActivityListBean[size];
            }
        };

        public String getActivity_url() {
            return activity_url;
        }

        public void setActivity_url(String activity_url) {
            this.activity_url = activity_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getActivity_logo() {
            return activity_logo;
        }

        public void setActivity_logo(String activity_logo) {
            this.activity_logo = activity_logo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(activity_url);
            dest.writeString(name);
            dest.writeString(activity_logo);
        }
    }
}
