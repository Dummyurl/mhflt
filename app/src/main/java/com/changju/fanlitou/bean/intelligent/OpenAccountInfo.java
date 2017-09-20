package com.changju.fanlitou.bean.intelligent;

import java.util.List;

/**
 * Created by chengww on 2017/7/7.
 */

public class OpenAccountInfo {

    private int platform_id;
    private boolean is_need_bank_phone_number;
    private boolean is_need_bank_branch_name;
    private boolean is_need_city_province;
    private UserFltBoundBankInfoBean user_flt_bound_bank_info;
    private String bank_phone_number;
    private UserVerifyInfoBean user_verify_info;
    private String platform_name;
    private UserAgreementInfoBean user_agreement_info;
    private String platform_bank_interface_type;
    private boolean is_need_openaccount_sms_code;
    private UserProvinceInfoBean user_province_info;
    private List<ProvinceListBean> province_list;
    private List<BankListBean> bank_list;

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public boolean isIs_need_bank_phone_number() {
        return is_need_bank_phone_number;
    }

    public void setIs_need_bank_phone_number(boolean is_need_bank_phone_number) {
        this.is_need_bank_phone_number = is_need_bank_phone_number;
    }

    public boolean isIs_need_bank_branch_name() {
        return is_need_bank_branch_name;
    }

    public void setIs_need_bank_branch_name(boolean is_need_bank_branch_name) {
        this.is_need_bank_branch_name = is_need_bank_branch_name;
    }

    public boolean isIs_need_city_province() {
        return is_need_city_province;
    }

    public void setIs_need_city_province(boolean is_need_city_province) {
        this.is_need_city_province = is_need_city_province;
    }

    public UserFltBoundBankInfoBean getUser_flt_bound_bank_info() {
        return user_flt_bound_bank_info;
    }

    public void setUser_flt_bound_bank_info(UserFltBoundBankInfoBean user_flt_bound_bank_info) {
        this.user_flt_bound_bank_info = user_flt_bound_bank_info;
    }

    public String getBank_phone_number() {
        return bank_phone_number;
    }

    public void setBank_phone_number(String bank_phone_number) {
        this.bank_phone_number = bank_phone_number;
    }

    public UserVerifyInfoBean getUser_verify_info() {
        return user_verify_info;
    }

    public void setUser_verify_info(UserVerifyInfoBean user_verify_info) {
        this.user_verify_info = user_verify_info;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public UserAgreementInfoBean getUser_agreement_info() {
        return user_agreement_info;
    }

    public void setUser_agreement_info(UserAgreementInfoBean user_agreement_info) {
        this.user_agreement_info = user_agreement_info;
    }

    public String getPlatform_bank_interface_type() {
        return platform_bank_interface_type;
    }

    public void setPlatform_bank_interface_type(String platform_bank_interface_type) {
        this.platform_bank_interface_type = platform_bank_interface_type;
    }

    public boolean isIs_need_openaccount_sms_code() {
        return is_need_openaccount_sms_code;
    }

    public void setIs_need_openaccount_sms_code(boolean is_need_openaccount_sms_code) {
        this.is_need_openaccount_sms_code = is_need_openaccount_sms_code;
    }

    public UserProvinceInfoBean getUser_province_info() {
        return user_province_info;
    }

    public void setUser_province_info(UserProvinceInfoBean user_province_info) {
        this.user_province_info = user_province_info;
    }

    public List<ProvinceListBean> getProvince_list() {
        return province_list;
    }

    public void setProvince_list(List<ProvinceListBean> province_list) {
        this.province_list = province_list;
    }

    public List<BankListBean> getBank_list() {
        return bank_list;
    }

    public void setBank_list(List<BankListBean> bank_list) {
        this.bank_list = bank_list;
    }

    public static class UserFltBoundBankInfoBean {
        /**
         * bound_bank_info : {"bank_name":"中国农业银行","card_no":"6228480010979169116","bank_id":22,"bank_logo":"https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png"}
         * is_already_bound_flt : true
         */

        private BoundBankInfoBean bound_bank_info;
        private boolean is_already_bound_flt;

        public BoundBankInfoBean getBound_bank_info() {
            return bound_bank_info;
        }

        public void setBound_bank_info(BoundBankInfoBean bound_bank_info) {
            this.bound_bank_info = bound_bank_info;
        }

        public boolean isIs_already_bound_flt() {
            return is_already_bound_flt;
        }

        public void setIs_already_bound_flt(boolean is_already_bound_flt) {
            this.is_already_bound_flt = is_already_bound_flt;
        }

        public static class BoundBankInfoBean {
            /**
             * bank_name : 中国农业银行
             * card_no : 6228480010979169116
             * bank_id : 22
             * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/87df4e9c/bank-87df4e9c-2f95-4341-9f9f-cee98936404d-1480416201.png
             */

            private String bank_name;
            private String card_no;
            private int bank_id;
            private String bank_logo;

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public int getBank_id() {
                return bank_id;
            }

            public void setBank_id(int bank_id) {
                this.bank_id = bank_id;
            }

            public String getBank_logo() {
                return bank_logo;
            }

            public void setBank_logo(String bank_logo) {
                this.bank_logo = bank_logo;
            }
        }
    }

    public static class UserVerifyInfoBean {
        /**
         * is_verified : true
         * verified_info : {"id_number":"230204198404021111","name":"王晓利"}
         */

        private boolean is_verified;
        private VerifiedInfoBean verified_info;

        public boolean isIs_verified() {
            return is_verified;
        }

        public void setIs_verified(boolean is_verified) {
            this.is_verified = is_verified;
        }

        public VerifiedInfoBean getVerified_info() {
            return verified_info;
        }

        public void setVerified_info(VerifiedInfoBean verified_info) {
            this.verified_info = verified_info;
        }

        public static class VerifiedInfoBean {
            /**
             * id_number : 230204198404021111
             * name : 王晓利
             */

            private String id_number;
            private String name;

            public String getId_number() {
                return id_number;
            }

            public void setId_number(String id_number) {
                this.id_number = id_number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class UserAgreementInfoBean {
        /**
         * agreement_list : [{"content":"","title":"《借款合同》"}]
         * is_need_user_agreement : true
         */

        private boolean is_need_user_agreement;
        private List<AgreementListBean> agreement_list;

        public boolean isIs_need_user_agreement() {
            return is_need_user_agreement;
        }

        public void setIs_need_user_agreement(boolean is_need_user_agreement) {
            this.is_need_user_agreement = is_need_user_agreement;
        }

        public List<AgreementListBean> getAgreement_list() {
            return agreement_list;
        }

        public void setAgreement_list(List<AgreementListBean> agreement_list) {
            this.agreement_list = agreement_list;
        }

        public static class AgreementListBean {
            /**
             * content :
             * title : 《借款合同》
             */

            private String content;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }

    public static class UserProvinceInfoBean {
        /**
         * province_info : {"city":{"id":"382","name":"静安区"},"id":"9","name":"上海市"}
         * is_has_province_city : true
         */

        private ProvinceInfoBean province_info;
        private boolean is_has_province_city;

        public ProvinceInfoBean getProvince_info() {
            return province_info;
        }

        public void setProvince_info(ProvinceInfoBean province_info) {
            this.province_info = province_info;
        }

        public boolean isIs_has_province_city() {
            return is_has_province_city;
        }

        public void setIs_has_province_city(boolean is_has_province_city) {
            this.is_has_province_city = is_has_province_city;
        }

        public static class ProvinceInfoBean {
            /**
             * city : {"id":"382","name":"静安区"}
             * id : 9
             * name : 上海市
             */

            private CityBean city;
            private int id;
            private String name;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public static class CityBean {
                /**
                 * id : 382
                 * name : 静安区
                 */

                private int id;
                private String name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }

    public static class ProvinceListBean {
        /**
         * city : [{"name":"黄浦区","id":378},{"name":"卢湾区","id":379},{"name":"徐汇区","id":380},{"name":"长宁区","id":381},{"name":"静安区","id":382},{"name":"普陀区","id":383},{"name":"闸北区","id":384},{"name":"虹口区","id":385},{"name":"杨浦区","id":386},{"name":"闵行区","id":387},{"name":"宝山区","id":388},{"name":"嘉定区","id":389},{"name":"浦东新区","id":390},{"name":"金山区","id":391},{"name":"松江区","id":392},{"name":"青浦区","id":393},{"name":"南汇区","id":394},{"name":"奉贤区","id":395},{"name":"崇明县","id":396},{"name":"城桥镇","id":397}]
         * name : 上海市
         * id : 9
         */

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

    public static class BankListBean {
        /**
         * bank_name : 中国工商银行
         * bank_id : 21
         * bank_logo : https://o0s106hgi.qnssl.com/media/bank-logo/3e93647d/bank-3e93647d-2a74-49ea-a94a-5447ef8490d9-1480416189.jpg
         */

        private String bank_name;
        private int bank_id;
        private String bank_logo;

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public int getBank_id() {
            return bank_id;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }
    }
}
