package com.changju.fanlitou.bean.home;

import java.util.List;

/**
 * Created by chengww on 2017/5/11.
 */

public class FavoritePlatformList {

    private List<FavoritePlatformBean> favorite_platform;

    public List<FavoritePlatformBean> getFavorite_platform() {
        return favorite_platform;
    }

    public void setFavorite_platform(List<FavoritePlatformBean> favorite_platform) {
        this.favorite_platform = favorite_platform;
    }

    public static class FavoritePlatformBean {
        /**
         * platform_id : 8
         * platform_name : 爱钱帮
         * logo_url : https://o0s106hgi.qnssl.com/media/plat-logo/a7c818a5/favo-a7c818a5-1784-425c-84ab-8c93034863a7-1491033676.png
         * logo_square_url : https://o0s106hgi.qnssl.com/media/plat-logo/ac779374/app-ac779374-b8ea-46a4-a319-cbdce69ede68-1464075873.jpg
         * is_checked : true
         */

        private int platform_id;
        private String platform_name;
        private String logo_url;
        private String logo_square_url;
        private boolean is_checked;

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

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public String getLogo_square_url() {
            return logo_square_url;
        }

        public void setLogo_square_url(String logo_square_url) {
            this.logo_square_url = logo_square_url;
        }

        public boolean isIs_checked() {
            return is_checked;
        }

        public void setIs_checked(boolean is_checked) {
            this.is_checked = is_checked;
        }
    }
}
