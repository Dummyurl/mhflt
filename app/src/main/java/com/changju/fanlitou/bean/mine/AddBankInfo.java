package com.changju.fanlitou.bean.mine;

import java.util.List;

/**
 * Created by chengww on 2017/6/5.
 */

public class AddBankInfo {

    private String bank_name;
    private String card_num;
    private String phone_num;
    private boolean is_bound;
    private String id_number;
    private boolean is_verified;
    private String real_name;
    private String bank_logo;
    private boolean is_jd;
    private List<ProvinceBean> province;
    private List<BanksBean> banks;

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public boolean isIs_bound() {
        return is_bound;
    }

    public void setIs_bound(boolean is_bound) {
        this.is_bound = is_bound;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getBank_logo() {
        return bank_logo;
    }

    public void setBank_logo(String bank_logo) {
        this.bank_logo = bank_logo;
    }

    public boolean isIs_jd() {
        return is_jd;
    }

    public void setIs_jd(boolean is_jd) {
        this.is_jd = is_jd;
    }

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public List<BanksBean> getBanks() {
        return banks;
    }

    public void setBanks(List<BanksBean> banks) {
        this.banks = banks;
    }

    public static class ProvinceBean {

        private String name;
        private int id;
        private List<CityBean> city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<CityBean> getCity() {
            return city;
        }

        public void setCity(List<CityBean> city) {
            this.city = city;
        }

        public static class CityBean {
            /**
             * name : 黄浦区
             * id : 378
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }

    public static class BanksBean {
        /**
         * logo : https://o0s106hgi.qnssl.com/media/bank-logo/3e93647d/bank-3e93647d-2a74-49ea-a94a-5447ef8490d9-1480416189.jpg
         * code : ICBC
         * name : 中国工商银行
         */

        private String logo;
        private String code;
        private String name;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
