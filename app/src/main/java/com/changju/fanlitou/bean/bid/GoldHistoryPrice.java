package com.changju.fanlitou.bean.bid;

import java.util.List;

/**
 * Created by chengww on 2017/6/19.
 */

public class GoldHistoryPrice {

    /**
     * date :
     * price : ["275.50","275.20","273.89","274.26","275.51","276.29","276.70","277.61","279.82","279.26","280.00","281.40","278.50","279.75","280.35","279.00","278.99","278.16"]
     * time : ["2017-05-08","2017-05-09","2017-05-10","2017-05-11","2017-05-12","2017-05-15","2017-05-16","2017-05-17","2017-05-18","2017-05-19","2017-05-22","2017-05-23","2017-05-24","2017-05-25","2017-05-26","2017-05-31","2017-06-01","2017-06-02"]
     */

    private String date;
    private List<String> price;
    private List<String> time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
