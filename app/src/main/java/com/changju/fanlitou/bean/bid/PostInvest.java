package com.changju.fanlitou.bean.bid;

/**
 * Created by chengww on 2017/6/20.
 */

public class PostInvest {

    /**
     * url : https://www.bundtrade.cn/bwgateway/flt/login?fcode=6fbfe5dcfe16dd71500d2e638b794a45&bid_url=https%3A%2F%2Ftest.bundtrade.cn%2FBundTradeH5%2Fh5fix.p2p.primaryMkt%21fixedProDetail.action%3Fcondition.showFinish%3D1%26buyYN%3DY%26conditions.proCode%3D149569170452204959&register_token=6341fda21b1fa3c061840fc0405137198f2b970885b74e12135c3bc474a54d830a7de5ee2a054213de5843ae9aca8b5a&uid=eca8257e5bbc5fd816f5db9bdafee9a5&source=337948478824d57f79a7567c801f856c&login_token=aa4ba2c00fb4db74257e77897b5ac2e266ff8e65d1582fdaa07c3770e5113b98f479fd6acc8f26b0be199eb535d94f2b&sign=09561e67cc1827c6039a89a8cb6fe4ee33f10d80b98ddd523ff991534a3b3a780f4fcd855f1da7a1c17cf24f6a00405c&t=5202e90cce92d191bb8d6342136999fb
     * result_type : auto_register
     * success : true
     */

    private String url;
    private String result_type;
    private boolean success;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
