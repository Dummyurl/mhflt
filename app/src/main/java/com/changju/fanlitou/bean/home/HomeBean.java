package com.changju.fanlitou.bean.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chengww on 2017/5/4.
 */

public class HomeBean implements Serializable,Parcelable{


    private List<BannersBean> banners;
    private List<UserFavoritePlatformsBean> user_favorite_platforms;

    protected HomeBean(Parcel in) {
        banners = in.createTypedArrayList(BannersBean.CREATOR);
        user_favorite_platforms = in.createTypedArrayList(UserFavoritePlatformsBean.CREATOR);
    }

    public static final Creator<HomeBean> CREATOR = new Creator<HomeBean>() {
        @Override
        public HomeBean createFromParcel(Parcel in) {
            return new HomeBean(in);
        }

        @Override
        public HomeBean[] newArray(int size) {
            return new HomeBean[size];
        }
    };

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<UserFavoritePlatformsBean> getUser_favorite_platforms() {
        return user_favorite_platforms;
    }

    public void setUser_favorite_platforms(List<UserFavoritePlatformsBean> user_favorite_platforms) {
        this.user_favorite_platforms = user_favorite_platforms;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(banners);
        dest.writeTypedList(user_favorite_platforms);
    }

    public static class BannersBean implements Serializable,Parcelable{
        /**
         * webview_info : {"webview_url":"http://m.test.fanlitou.com/m/activity/","is_has_header":true,"title":"活动"}
         * original_info : {"platform_id":0,"platform_name":"","original_type":""}
         * type : webview
         * banner_url : https://o0s106hgi.qnssl.com/media/banner/b8262bfe-972f-4bf2-ae74-c3393d3262b2-1493972970.png
         */

        private WebviewInfoBean webview_info;
        private OriginalInfoBean original_info;
        private String type;
        private String banner_url;

        protected BannersBean(Parcel in) {
            webview_info = in.readParcelable(WebviewInfoBean.class.getClassLoader());
            original_info = in.readParcelable(OriginalInfoBean.class.getClassLoader());
            type = in.readString();
            banner_url = in.readString();
        }

        public static final Creator<BannersBean> CREATOR = new Creator<BannersBean>() {
            @Override
            public BannersBean createFromParcel(Parcel in) {
                return new BannersBean(in);
            }

            @Override
            public BannersBean[] newArray(int size) {
                return new BannersBean[size];
            }
        };

        public WebviewInfoBean getWebview_info() {
            return webview_info;
        }

        public void setWebview_info(WebviewInfoBean webview_info) {
            this.webview_info = webview_info;
        }

        public OriginalInfoBean getOriginal_info() {
            return original_info;
        }

        public void setOriginal_info(OriginalInfoBean original_info) {
            this.original_info = original_info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(webview_info, flags);
            dest.writeParcelable(original_info, flags);
            dest.writeString(type);
            dest.writeString(banner_url);
        }

        public static class WebviewInfoBean implements Serializable,Parcelable{
            /**
             * webview_url : http://m.test.fanlitou.com/m/activity/
             * is_has_header : true
             * title : 活动
             */

            private String webview_url;
            private boolean is_has_header;
            private boolean is_has_footer;

            public boolean is_has_footer() {
                return is_has_footer;
            }

            public void setIs_has_footer(boolean is_has_footer) {
                this.is_has_footer = is_has_footer;
            }

            private String title;

            protected WebviewInfoBean(Parcel in) {
                webview_url = in.readString();
                is_has_header = in.readByte() != 0;
                title = in.readString();
            }

            public static final Creator<WebviewInfoBean> CREATOR = new Creator<WebviewInfoBean>() {
                @Override
                public WebviewInfoBean createFromParcel(Parcel in) {
                    return new WebviewInfoBean(in);
                }

                @Override
                public WebviewInfoBean[] newArray(int size) {
                    return new WebviewInfoBean[size];
                }
            };

            public String getWebview_url() {
                return webview_url;
            }

            public void setWebview_url(String webview_url) {
                this.webview_url = webview_url;
            }

            public boolean isIs_has_header() {
                return is_has_header;
            }

            public void setIs_has_header(boolean is_has_header) {
                this.is_has_header = is_has_header;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(webview_url);
                dest.writeByte((byte) (is_has_header ? 1 : 0));
                dest.writeString(title);
            }
        }

        public static class OriginalInfoBean implements Serializable,Parcelable{
            /**
             * platform_id : 0
             * platform_name :
             * original_type :
             */

            private int platform_id;
            private String platform_name;
            private String original_type;

            protected OriginalInfoBean(Parcel in) {
                platform_id = in.readInt();
                platform_name = in.readString();
                original_type = in.readString();
            }

            public static final Creator<OriginalInfoBean> CREATOR = new Creator<OriginalInfoBean>() {
                @Override
                public OriginalInfoBean createFromParcel(Parcel in) {
                    return new OriginalInfoBean(in);
                }

                @Override
                public OriginalInfoBean[] newArray(int size) {
                    return new OriginalInfoBean[size];
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

            public String getOriginal_type() {
                return original_type;
            }

            public void setOriginal_type(String original_type) {
                this.original_type = original_type;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(platform_id);
                dest.writeString(platform_name);
                dest.writeString(original_type);
            }
        }
    }

    public static class UserFavoritePlatformsBean implements Serializable,Parcelable{
        /**
         * platform_name : 合盘贷
         * platform_short_name : hepandai
         * platform_id : 15
         * platform_type : fixed
         * logo_url_square : https://o0s106hgi.qnssl.com/media/plat-logo/0ac72a39/app-0ac72a39-a934-4073-8957-e19befe6db9e-1464071341.jpg
         * is_hot : true
         * logo_url : https://o0s106hgi.qnssl.com/media/plat-logo/597977e5/favo-597977e5-b51b-439d-b35f-625caa0f1c94-1491033563.png
         */

        private String platform_name;
        private String platform_short_name;
        private int platform_id;
        private String platform_type;
        private String logo_url_square;
        private boolean is_hot;
        private String logo_url;

        protected UserFavoritePlatformsBean(Parcel in) {
            platform_name = in.readString();
            platform_short_name = in.readString();
            platform_id = in.readInt();
            platform_type = in.readString();
            logo_url_square = in.readString();
            is_hot = in.readByte() != 0;
            logo_url = in.readString();
        }

        public static final Creator<UserFavoritePlatformsBean> CREATOR = new Creator<UserFavoritePlatformsBean>() {
            @Override
            public UserFavoritePlatformsBean createFromParcel(Parcel in) {
                return new UserFavoritePlatformsBean(in);
            }

            @Override
            public UserFavoritePlatformsBean[] newArray(int size) {
                return new UserFavoritePlatformsBean[size];
            }
        };

        public String getPlatform_name() {
            return platform_name;
        }

        public void setPlatform_name(String platform_name) {
            this.platform_name = platform_name;
        }

        public String getPlatform_short_name() {
            return platform_short_name;
        }

        public void setPlatform_short_name(String platform_short_name) {
            this.platform_short_name = platform_short_name;
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

        public String getLogo_url_square() {
            return logo_url_square;
        }

        public void setLogo_url_square(String logo_url_square) {
            this.logo_url_square = logo_url_square;
        }

        public boolean isIs_hot() {
            return is_hot;
        }

        public void setIs_hot(boolean is_hot) {
            this.is_hot = is_hot;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(platform_name);
            dest.writeString(platform_short_name);
            dest.writeInt(platform_id);
            dest.writeString(platform_type);
            dest.writeString(logo_url_square);
            dest.writeByte((byte) (is_hot ? 1 : 0));
            dest.writeString(logo_url);
        }
    }
}
