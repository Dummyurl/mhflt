package com.changju.fanlitou.bean.fund;


/**
 * Created by Administrator on 2017/8/2.
 */

public class PurchaseFeeBean {

    /**
     * original_fee_amount : 0.30
     * fee_amount : 0.30
     * save_money : 0.00
     */

    private String original_fee_amount;
    private String fee_amount;
    private String save_money;

    public String getOriginal_fee_amount() {
        return original_fee_amount;
    }

    public void setOriginal_fee_amount(String original_fee_amount) {
        this.original_fee_amount = original_fee_amount;
    }

    public String getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getSave_money() {
        return save_money;
    }

    public void setSave_money(String save_money) {
        this.save_money = save_money;
    }
}
