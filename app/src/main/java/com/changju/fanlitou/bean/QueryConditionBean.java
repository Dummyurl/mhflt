package com.changju.fanlitou.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class QueryConditionBean {

    private List<ClassifyInsuranceBean> classify_insurance;
    private List<ClassifyPersonBean> classify_person;

    public List<ClassifyInsuranceBean> getClassify_insurance() {
        return classify_insurance;
    }

    public void setClassify_insurance(List<ClassifyInsuranceBean> classify_insurance) {
        this.classify_insurance = classify_insurance;
    }

    public List<ClassifyPersonBean> getClassify_person() {
        return classify_person;
    }

    public void setClassify_person(List<ClassifyPersonBean> classify_person) {
        this.classify_person = classify_person;
    }

    public static class ClassifyInsuranceBean {
        /**
         * name : 全部保险
         * key : all
         */

        private String name;
        private String key;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public static class ClassifyPersonBean {
        /**
         * name : 全部保险
         * key : all
         */

        private String name;
        private String key;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
