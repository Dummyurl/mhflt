package com.changju.fanlitou.bean.bid;

import java.util.List;

/**
 * Created by chengww on 2017/6/14.
 */

public class BidDct {

    private BidDetailBean bid_detail;
    private boolean is_registered;
    private String button_name;
    private boolean is_show_register_dialog;

    public boolean is_show_register_dialog() {
        return is_show_register_dialog;
    }

    public void setIs_show_register_dialog(boolean is_show_register_dialog) {
        this.is_show_register_dialog = is_show_register_dialog;
    }

    public BidDetailBean getBid_detail() {
        return bid_detail;
    }

    public void setBid_detail(BidDetailBean bid_detail) {
        this.bid_detail = bid_detail;
    }

    public boolean isIs_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
    }

    public String getButton_name() {
        return button_name;
    }

    public void setButton_name(String button_name) {
        this.button_name = button_name;
    }

    public static class BidDetailBean {
        /**
         * bid_image_max : https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/2017041711234310.jpg
         * remain_time : 3天
         * financing_rate : 162
         * project_introduction : 纽约大学组织行为学研究生毕业，曾在联合国项目服务部参与战后排雷管理，现任世嘉地产集团助理董事，方舟集落CEO。
         * bid_brief : 像梭罗一样，隐居湖畔，像马云一样在湖畔传道沉思
         * status_str : 预约中
         * is_crowdfunding_bid : true
         * platform : {"interest_max":0,"is_bank":false,"is_vc":false,"logo_thumbnail":"https://o0s106hgi.qnssl.com/media/plat-logo/e7253318/thumb-e7253318-7d4e-470f-a622-04c570ba9623-1465295002.png","bonding_method":"六重风控","can_auto_login":false,"id":27,"location_city":"北京","registered_capital":882,"credit_score":3,"is_state":false,"online_date":"2014-10-30","is_private":true,"shortname":"duocaitou","can_auto_register":false,"duration_min_unit":"年","logo_app_square":"https://o0s106hgi.qnssl.com/media/plat-logo/3daccf2c/app-3daccf2c-2d6d-4a80-b70f-43f459147de1-1470037853.png","location_province":"北京","duration_max_unit":"年","background":"获得小米创始人雷军旗下顺为资本千万级天使轮投资","is_public":false,"can_bind_account":false,"logo_main":"https://o0s106hgi.qnssl.com/media/plat-logo/9643bece/main-9643bece-7a22-4919-8ce5-6974f8515dc2-1465295002.png","main_bussiness":"私募股权、消费众筹、收益权众筹","name":"多彩投","url":"http://www.duocaitou.com/","logo_app":"https://o0s106hgi.qnssl.com/media/plat-logo/a9a46379/app-a9a46379-f323-4ba0-be1d-e3af637f12ea-1490927788.png","interest_min":5,"duration_max":0,"duration_min":0}
         * settlement_days_after_bid_fulled : 2
         * bid_detail : <p style="white-space: normal; text-align: center; line-height: 2em;"><br/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">&nbsp;1&nbsp;</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 18px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px;">从 联 合 国 到 华 尔 街</span></strong></span><span style="margin: 0px; padding: 0px; font-size: 20px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px;"><br style="margin: 0px; padding: 0px;"/></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 16px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px;">我 想 和 这 个 世 界 谈 谈</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397276267822.jpg" data-ratio="0.75" data-w="800" title="1492397276267822.jpg" alt="652030408230728685.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">我叫朱佳音，大家可以叫我Mickey.J.zhu,也可以直接叫我Mickey。2013年，我从纽约大学组织行为学研究生毕业，毕业后，我拒绝了很多高薪的offer，也没有进入家族企业，而是选择在联合国项目服务部实习工作。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(255, 41, 65);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px; line-height: 1.75em;"></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397310328515.jpg" data-ratio="0.38515625" data-w="1280" title="1492397310328515.jpg" alt="08.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;">@ Photo by&nbsp;</span><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;">Mickey.J.Zhu</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;"><br style="margin: 0px; padding: 0px;"/></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">在联合国工作的一年时间里，工作组主要负责世界几大战区战后的排雷和武器管理工作（苏丹、阿富汗、利比亚、东欧等）。同事们很多都是工程师，他们本可以选择更安全稳定的工作，但是很多人都是抱着一个“make the world a better place”的信念。每一年，办公室都会有工作人员因为各种原因永远的离开这个世界，有的排雷工作中的意外，有的战后不稳定区域遭武装分子袭击。</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 1.75em;">那边的同事们是我见到过的最快乐的一群人，在阿富汗办公室的办公室里，团队里每一个人都很nice。每周五晚上工程师、项目专员都会和美军士兵一起开派对、玩游戏、团建。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><br style="margin: 0px; padding: 0px;"/></span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">这份工作对我意义重大，因为它所带来的意义和满足感是任何工作都无法给予的。</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">结束了在联合国的实习经历，我还是没能抵御世俗的“诱惑”，来到了一家华尔街上的精品投行做全职分析员。将近一年的时间里，我看了上千家公司的财务报表，可是金钱买不来原来工作的“幸福感”。在“倒买倒卖”之中并没有创造价值，我所做的只是在一个固定的蛋糕中切走了一块，对其进行一次财富再分配。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397320261548.jpg" data-ratio="0.665625" data-w="1280" title="1492397320261548.jpg" alt="014.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;">@ Photo by&nbsp;</span><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;">Mickey.J.Zhu</strong></span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;"><br style="margin: 0px; padding: 0px;"/></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><span style="margin: 0px; padding: 0px;">Data means nothing.&nbsp;</span>如同电影Margin Call里面金融公司的Risk大叔在片尾对Trader小帅说的一番话：“我曾经是个桥梁工程师，造了一座桥，为成百万人每天节省了一个多小时的往返车程，为人类累计节省了上千年的时间，让人们没有把这些时间浪费在没有意义的路程上。而现在，我却只是坐在电脑前扭曲着这些数字，琢磨怎么更有效的把别人的钱变成自己的钱。”</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">当我从华尔街离去</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">钱袋满满，内心空空</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">人的一生应该怎么度过？</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">究竟要做什么此生才不算虚度？</span></strong><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">经过慎重思考后</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">我，想为这个世界做一些事情</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">机缘巧合下，在一次回国的途中我接触到了阿拉善SEE生态协会里的西南项目中心公益环保项目。和印象中的慈善不一样，这个组织并不是单纯的的捐钱，而是在寻求一种人与自然共生共赢的方法。他们的理念是，当环保项目能够同时为村民创造财富，那么村民则会自发的来保护环境，效果远比说教有效。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">被这个理念吸引，我加入了阿拉善SEE生态协会，成为西南中心的一员，参与执行西南中心的生计替代扶贫环保项目。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><br/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">有没有一种方式能够</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">平衡</span><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"><span style="margin: 0px; padding: 0px;"></span>企业、村民和生态</span></strong><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">三者的利益关系？</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397339712442.jpg" data-ratio="0.4575" data-w="800" title="1492397339712442.jpg" alt="3dd9aba0fdb917f56fd7a2905b263a65.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="white-space: normal; line-height: 2em;"><br/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 24.5px;">带着从阿拉善中心接受的理念</span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 24.5px;">我在</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px;">曼么耐水库</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 24.5px;">旁</span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 24.5px;">开启了自己第一个<strong style="margin: 0px; padding: 0px;">商业项目</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 24.5px;">&nbsp;</span><strong style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif; font-size: 14px; line-height: 24.5px;">西双版纳·方舟集落帐篷酒店</strong><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">与以往不同的是，</span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; font-size: medium; white-space: normal; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;">这一次，我没有退路</span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; font-family: 微软雅黑, sans-serif;"></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">&nbsp;2&nbsp;</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 20px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px;">隐 居 于 湖 畔</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">湖畔对人的思想开阔有重要意义。<span style="margin: 0px; padding: 0px; line-height: 28px;">水，启迪了人的思想。在水边，人的思想可以无限放空，湿润清新的空气，寂静森林只能听见鸟鸣和虫鸣，湖畔总能让人沉下心来思考。</span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">英国浪漫主义湖畔诗人发源于湖畔，甚至以湖畔命名自己的学派，美国哲学家梭罗在湖畔完成了著名传世之作《瓦尔登湖》，阿里巴巴创始人马云甚至选择在西子湖畔创建一所“湖畔大学”，为企业家们创造一个交流学习进步的平台。</span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">澄清的湖面安抚着一切躁动的情绪</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">宁静的空气里有着湖边特有的湿润气息</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397360888144.jpg" data-ratio="0.26375" data-w="800" title="1492397360888144.jpg" alt="WechatIMG11952.jpg" style="margin: 0px; padding: 0px; max-width: 100%; line-height: 23.2727px; height: auto !important;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 18px;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">离开喧嚣，如同隐士一般</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">与山川草木为友，与日落星空相伴为邻</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">日出而作，日落而息</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><span style="margin: 0px; padding: 0px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">◇ &nbsp;&nbsp;◇ &nbsp;</span></strong></span><span style="margin: 0px; padding: 0px; line-height: 23.2727px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px;"></span></strong></span><span style="margin: 0px; padding: 0px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">&nbsp;◇&nbsp;</span></strong></span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);">&nbsp;3&nbsp;</span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 18px;"><strong style="margin: 0px; padding: 0px;">为什么是帐篷酒店？</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">我自幼酷爱接触“野”的事物，从小习惯和家人定期旅行。本科的时候，我去了美国，有了自己的车子，更是经常和朋友一起到露营。夜晚幕天席地，在星空下接着电瓶做Jam Session（即兴演奏）。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397382969216.jpg" data-ratio="0.665625" data-w="1280" title="1492397382969216.jpg" alt="013.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px;">@ Photo by&nbsp;</span><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px;">Mickey.J.Zhu</strong><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px;"><br style="margin: 0px; padding: 0px;"/></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">去阿拉斯加就开车一路向北，一定要冲到北极圈里面去，还必须是夜里零下20-30度的地方，没有手机信号、没有网没有电话只有狗拉雪橇送邮件。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397450893200.jpg" data-ratio="0.56171875" data-w="1280" title="1492397450893200.jpg" alt="021.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397461506309.jpg" data-ratio="0.665" data-w="800" title="1492397461506309.jpg" alt="019.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;">@ Photo by&nbsp;</span><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px; line-height: 28px;">Mickey.J.Zhu</strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; line-height: 1.75em;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">去个山谷里的音乐节就一定要搭个帐篷住在山里，以享受到全天等候的激动。当演出开始的一瞬间，一切都圆满了。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397471940357.jpg" data-ratio="1.5023474178403755" data-w="852" title="1492397471940357.jpg" alt="012.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(136, 136, 136);">@ Photo by&nbsp;<strong style="margin: 0px; padding: 0px;">Mickey.J.Zhu</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(136, 136, 136);"><strong style="margin: 0px; padding: 0px;"><br style="margin: 0px; padding: 0px;"/></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">从那时起，我爱上了住帐篷的体验，可以把自己的小窝带到世界上任何一处自己想去的地方，帐篷就是我的家。</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">对生活质量的追求也让我把自己的小帐篷越搞越舒服，不但配上了充气床垫，带上了小发电机，音响，还配上了可以洗澡的专用帐篷。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><strong style="margin: 0px; padding: 0px; color: rgb(255, 41, 65); line-height: 28px;"><span style="margin: 0px; padding: 0px;"></span></strong></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398026849340.jpg" data-ratio="1.0802083333333334" data-w="960" title="1492398026849340.jpg" alt="04_副本.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136);">@ Photo by&nbsp;</span><strong style="margin: 0px; padding: 0px; color: rgb(136, 136, 136);">Mickey.J.Zhu &nbsp;-<span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89);">&nbsp;山谷音乐节的大帐篷</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">接触最原始的自然</span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">不一定就是要艰苦朴素</span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; color: rgb(89, 89, 89);">体验过无数的酒店后，我发现在世界其他地方有很多奢华的帐篷酒店。与国内</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;">的“伪帐篷”酒店不同（</span><span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 14px; color: rgb(89, 89, 89);">一个帐篷顶，然后下面还是硬体建筑<span style="margin: 0px; padding: 0px;">），</span>真正帐篷酒店往往坐落于野外人迹罕至处，或深山老林，或旷野草甸。</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 20.3636px;">美国、非洲、东南亚，很多豪华帐篷酒店都卖到上千美金一晚，甚至比周围的别墅都要昂贵。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">几乎把有名的帐篷酒店住了个遍后，我发现<span style="margin: 0px; padding: 0px;">比起房子，</span>豪华帐篷有很多优点：</span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">• 最大限度的接触到了自然，让住在里面的人既有了自己的私人密闭空间，又不像住在房子里一样有一种被囚禁的感觉。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">• &nbsp; 每一家帐篷酒店都坐落于野外，地广人稀，每天晚上静的只能听到自己的心跳，枕着虫鸣入睡，清晨在鸟鸣中醒来。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">• &nbsp;由于帐篷极大的灵活性，奢华帐篷的选址都是房子所不能建造的地方，需要花很大成本和时间才能建造的地方。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"></span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">就这样，在西双版纳的热带雨林里，西双版纳 · 方舟集落帐篷酒店诞生了。</span><span style="margin: 0px; padding: 0px; text-align: center; color: rgb(89, 89, 89); font-size: 14px;">800亩的湖面，</span><span style="margin: 0px; padding: 0px; line-height: 1.75em; text-align: center; color: rgb(89, 89, 89); font-size: 14px;">4000亩的林地。</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">可以钓鱼、可以泛舟、可以进行原生态的雨林烧烤，可以徒步10多公里，可以学习到森林里各种不同的自然现象。最大限度的置身自然之中却不失去奢华。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 18px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">2016年10月</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 16px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">西双版纳 · 方舟集落正式试营业</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 16px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;"><br style="margin: 0px; padding: 0px;"/></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 16px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;"><br style="margin: 0px; padding: 0px;"/></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 16px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;"></span></strong></span><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">◇ &nbsp; &nbsp;◇ &nbsp;&nbsp;</span></strong></span><span style="margin: 0px; padding: 0px; line-height: 23.2727px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;"></span></strong></span><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">&nbsp;◇&nbsp;</span></strong></span><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;"></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89);">&nbsp;探寻未被开发的原始生态自然景观&nbsp;</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 20px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;"><strong style="margin: 0px; padding: 0px; line-height: 23.2727px;">西双版纳 · 方舟集落帐篷酒店</strong></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;">&nbsp;国内唯一一家热带雨林主题帐篷酒店&nbsp;</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px; color: rgb(255, 255, 255); line-height: 1.6;"><span style="margin: 0px; padding: 0px; font-size: 14px;"></span></strong><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 1.75em; text-align: center;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">&nbsp;4</span><span style="margin: 0px; padding: 0px; font-size: 14px;">&nbsp;</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 20px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px;">回 归</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 20px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">拥抱原始未经雕琢的自然</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">Environment-friendly</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 23.2727px;"><br style="margin: 0px; padding: 0px;"/></p><p style="white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397538430601.jpg" data-ratio="0.66625" data-w="800" title="1492397538430601.jpg" alt="996A6365.jpg" style="margin: 0px; padding: 0px; max-width: 100%; line-height: 23.2727px; height: auto !important;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">雾霾的都市里，连清甜的空气都变成一种奢侈。</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">方舟集落帐篷酒店打造国内为数不多的真实野奢度假地，<span style="margin: 0px; padding: 0px;">远离尘嚣不只是说说而已</span>。在一望无际的热带雨林中安营扎寨，连隔壁邻居都是个位数。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397582161963.jpg" data-ratio="0.66625" data-w="800" title="1492397582161963.jpg" alt="996A6090.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(89, 89, 89); line-height: 1.75em;">△ 湖山交映，风生水起。一顶丛林帐篷，仿佛回到了丛林探险的时代，原始而富有野性。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(255, 41, 65);"></span></strong></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492738827700821.jpg" data-ratio="0.6875" data-w="800" title="1492738827700821.jpg" alt="023.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89);">△ 入夜，纯净的天空可以欣赏到闪烁的繁星。</span><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); line-height: 1.75em;">漫空苍穹下，点点繁星触手可及，一瞬间就可沉醉在自然的美景下。</span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492399522154292.jpg" data-ratio="0.75" data-w="800" style="margin: 0px; padding: 0px; max-width: 100%; line-height: 1.6; height: auto !important;" title="1492399522154292.jpg" alt="763633638872781842.jpg"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(89, 89, 89); line-height: 1.75em;">△&nbsp;就这样，与自然融为一体。你在湖边看风景，也成为别人眼中的一道风景。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397706907839.jpg" data-ratio="0.6666666666666666" data-w="1200" title="1492397706907839.jpg" alt="1200_800.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/><span style="margin: 0px; padding: 0px; line-height: 1.75em;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(89, 89, 89); line-height: 1.75em;">△ 帐篷里，4000亩的寂静，仿佛只能听到自己的心跳。能够惊扰到这里寂静的，便只有鸟鸣与虫鸣。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 23.2727px;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">▼</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 20px;">用 简 单 重 新 定 义 舒 适</span></strong><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 20px;"></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 1.75em;">与绝妙风景仅有一“帐”之隔</span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">将自身置身于自然中</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">连夜晚都能嗅到空气中清新的草香</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">清晨，在森林中慢慢苏醒</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397799732179.jpg" data-ratio="0.66625" data-w="800" title="1492397799732179.jpg" alt="996A6147.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; color: rgb(89, 89, 89); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; color: rgb(89, 89, 89); font-size: 14px;">许多酒店都在日益浮躁的商业竞争中一味追求客房数量、入住率，使得原本应该安静舒适的度假场所变成熙熙攘攘的喧嚣旅馆。除了热闹，什么也没有。</span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 23.2727px;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">舒适不需要太多的物质去堆叠</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">西双版纳·方舟集落</span></strong><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">不以入住率衡量，不用价格定义“好”酒店，极其重视每一位客人野奢度假体验。<span style="margin: 0px; padding: 0px; line-height: 24.5px;">无拘无束、独一无二、不盲从、不跟风、低调舒适……是酒店主人最希望向客人们传达的理念。</span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">帐篷内部每间<span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0); font-size: 15px;"><strong style="margin: 0px; padding: 0px;">20-50㎡</strong></span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;">空间内部格局简约却不简单</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397817912104.jpg" data-ratio="0.66625" data-w="800" title="1492397817912104.jpg" alt="574180457078286726.jpg" style="margin: 0px; padding: 0px; max-width: 100%; line-height: 23.2727px; height: auto !important;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"><span style="margin: 0px; padding: 0px; line-height: 28px;">方舟帐篷酒店</span><span style="margin: 0px; padding: 0px;">几乎拥有房子几乎所有的优点：保温（空调火炉都可以有）、遮阳、挡雨、上下水系统、甚至浴缸都一应俱全。</span><span style="margin: 0px; padding: 0px; line-height: 28px;">用了最好的被褥、枕头等消耗品，有着齐全的卫浴设施，冬暖夏凉的温控设施，我们还做了五星级酒店才会有的水加压处理，让客人能在结束一天的行程后舒舒服服冲个热水澡。</span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397849800552.jpg" data-ratio="1.5" data-w="800" title="1492397849800552.jpg" alt="996A6128.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 帐篷和帐篷之间以绿植相隔，既形成了天然的屏障，又保证了每间客房的绝对隐私。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397866662947.jpg" data-ratio="0.66625" data-w="800" title="1492397866662947.jpg" alt="996A6084.jpg" style="margin: 0px; padding: 0px; max-width: 100%; line-height: 23.2727px; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 帐篷材质采用防蚊虫可降解帆布，不含甲醛和任何有害化工原料。&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; text-align: center;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; text-align: center;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397882554879.jpg" data-ratio="0.66625" data-w="800" title="1492397882554879.jpg" alt="996A6179.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△内部可容纳10人左右，甚至可以在帐篷内聚会派对</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; text-align: center;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; text-align: center;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397897367557.jpg" data-ratio="0.66625" data-w="800" title="1492397897367557.jpg" alt="996A6146.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 恰到好处的水压，在极简中彰显酒店主人对生活品质的要求。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; text-align: center;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397940648260.jpg" data-ratio="0.66625" data-w="800" title="1492397940648260.jpg" alt="996A6076.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 帐篷外，设置木质躺椅。下午三四点的阳光温柔静好，一杯清茶，一本挚爱书籍，足以忘记一切烦扰。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492397957961435.jpg" data-ratio="0.66625" data-w="800" title="1492397957961435.jpg" alt="996A6073.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 推开门便是山中小径，房间旁便可随意垂钓。门外步行几十步，便是一处最佳篝火营地。</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">第一期帐篷共<strong style="margin: 0px; padding: 0px;">7</strong>间</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">在未经任何推广的情况下</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">试营业期间</span><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 14px; line-height: 1.75em;">入住率保证在<span style="margin: 0px; padding: 0px; line-height: 1.75em; font-size: 18px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; color: rgb(0, 0, 0);">80%</span></strong></span>以上</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">2017年4月我们订房率到达</span><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); line-height: 1.75em; font-size: 18px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 1.75em; color: rgb(0, 0, 0);">100%</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">忙碌的运营来得猝不及防</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">仅有的7个帐篷</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">没有办法满足日益增长的需求量</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">扩建成为了必然</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);">▼</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 14px;">第二期方舟集落</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 14px;">将会扩建<span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;">30座</strong></span>帐篷</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 14px;">每间帐篷约在<span style="margin: 0px; padding: 0px; color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;">20-50㎡</strong></span></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 14px; line-height: 1.75em;">增加一座大型公共区域</span><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398002471842.jpg" data-ratio="0.5" data-w="1000" title="1492398002471842.jpg" alt="06.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 公共区域外部效果设计图</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 23.2727px; text-align: center;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398056688323.jpg" data-ratio="0.5834305717619603" data-w="857" title="1492398056688323.jpg" alt="05.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 公共区域 亲水平台设计效果图</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(136, 136, 136);"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398072336803.jpg" data-ratio="0.5" data-w="1000" title="1492398072336803.jpg" alt="01.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">△ 公共区域内部设计效果图</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 1.75em; text-align: center;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136);">▼</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 20px;">方 舟 集 落 独 家 体 验</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">星空观测 | 篝火晚会 | 垂钓派对</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;">露天影院 |&nbsp;亲子课程</span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398130691132.jpg" data-ratio="0.66625" data-w="800" title="1492398130691132.jpg" alt="996A6303.jpg" style="margin: 0px; padding: 0px; max-width: 100%; line-height: 23.2727px; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(136, 136, 136);">△ 夜晚可以在帐篷旁星空观测，也可以召集好友来一场篝火盛宴</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 12px; color: rgb(136, 136, 136);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398143896022.jpg" data-ratio="0.66875" data-w="800" title="1492398143896022.jpg" alt="WechatIMG8356.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(136, 136, 136); font-size: 12px;">△ 热带雨林亲子徒步</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);">&nbsp;5&nbsp;</span></strong><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 12px;">&nbsp;</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 18px;"><strong style="margin: 0px; padding: 0px;">“让我们忠于理想，让我们面对现实”</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;">用环境友好的形式，传达对大自然的敬意</span></strong></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;">&nbsp;西双版纳 · 方舟集落帐篷酒店</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">不仅仅是一顶帐篷</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">更是与自然零距离接触的体验</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">一种亲近自然的野奢度假</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"><br style="margin: 0px; padding: 0px;"/></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; color: rgb(89, 89, 89); font-size: 14px; line-height: 24.5px;"></span></p><ul class=" list-paddingleft-2" style="list-style-type: square;"><li><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89);">我们同政府达成协议，<span style="margin: 0px; padding: 0px; font-family: 微软雅黑, sans-serif;">把水库及水库周边建立成为水源地湿地保护区。</span></span></p></li><li><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif;">我们联合了科研机构，对水库周边4000多亩林地展开了科学研究，帮助这里被破坏的山林恢复到以前原始的样子，让这里的大自然恢复他本来的功能。</span></p></li><li><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif;">我们和科研机构、公益组织合作，为周围的村民谋求了养蜂、林间套种对生物多样性有益的经济物种等生计方法，让村民自发的保护环境。</span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif;"></span></p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px; clear: both; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; color: rgb(89, 89, 89); font-family: 微软雅黑, sans-serif;"></span></p></li></ul><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 23.2727px;"><br style="margin: 0px; padding: 0px;"/></p><p style="white-space: normal; text-align: center; line-height: 2em;"><img data-s="300,640" data-type="jpeg" src="https://oss-cn-beijing.aliyuncs.com/dct-test/project_thumb/1492398162586218.jpg" data-ratio="1.42375" data-w="800" title="1492398162586218.jpg" alt="996A6394.jpg" style="margin: 0px; padding: 0px; max-width: 100%; height: auto !important;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;"></span></strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px;">环保，不只存在于公益</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px;">商业，亦非一味逐利</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px;">西双版纳·方舟集落</span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 18px;"><strong style="margin: 0px; padding: 0px;">平 衡 与 共 赢</strong></span></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; clear: both; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; line-height: 23.2727px;"><br style="margin: 0px; padding: 0px;"/></p><p style="margin-top: 0px; margin-bottom: 0px; font-size: medium; white-space: normal; padding: 0px; font-family: &#39;Helvetica Neue&#39;, Helvetica, &#39;Hiragino Sans GB&#39;, &#39;Microsoft YaHei&#39;, Arial, sans-serif; text-align: center; line-height: 2em;"><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">◇ &nbsp; &nbsp;◇ &nbsp;&nbsp;</span></strong></span><span style="margin: 0px; padding: 0px; line-height: 23.2727px; color: rgb(255, 255, 255); background-color: rgb(0, 0, 0);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; font-size: 14px;"></span></strong></span><span style="margin: 0px; padding: 0px; font-size: 14px; background-color: rgb(255, 255, 255);"><strong style="margin: 0px; padding: 0px;"><span style="margin: 0px; padding: 0px; line-height: 24.5px;">&nbsp;◇&nbsp;</span></strong></span></p>
         * status : 0
         * bid_order_url : https://www.duocaitou.com/assets/h5/pro-detail.html?proid=200080&pcode=flt080
         * bid_id : 39
         * min_invest_amount : 20000
         * project_initiator : 朱佳音
         * name : 方舟集落
         * bid_url : https://www.duocaitou.com/project/detail/id/200080
         * bid_image_small : https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/2017041711234310.jpg
         * total_amount : 200万
         * project_plan : [{"plan_id":"1429","limit_num":20,"title":"【方舟集落】收益权","price":20000,"project_plan":"【方舟集落】收益权\n个人限投20份￥20000.00/份","reward_plan":"【方舟集落】收益权\n个人限投20份￥20000.00/份","reward_plan_detail":"1.预期投资收益率：20%\\/年投资人每季度分红，按照实际收益率分配，若当年现金分红收益率不到10％，大股东将在第4季度(或每季度)进行补足；2. 消 费 权 益 1.一次性可获得投资金额的45%作为消费金。2.投资期限内有效，可用于方舟集落旗下及未来所有店，法定节假日不可用，需提前14天预约；3.投资人使用现金消费时，可享受房费OTA价格8折优惠；3. 额 外 福 利 1.投资满20万元的投资人，享有项目方赠送价值1000元的阿拉善西南中心出品-千花蜂蜜共两瓶。2.投资满30万元的投资人，享有酒店团建聚会套餐一次（含14个间夜住宿，公共空间使用权，与团建探险活动一次）3.推荐朋友入住可享受入住金额的15%现金奖励4. 退 出 机 制 1.自收益起始日，大股东应自收益起始日起届满3年之日起3个工作日以内以初始投资额的100％价格回购投资人收益权。2.其他：通过多彩投寻求股权收益权份额转让。"}]
         * pc_banner_image : https://oss-cn-beijing.aliyuncs.com/dct-test/pjt/ddem/201704171123494.jpg
         * platform_bonus_interest : 50
         * is_user_enjoy_bonus : true
         * tag_list : [{"is_show_dialog":true,"name":"新手标","dialog_content_list":["1","2"]},{"is_show_dialog":true,"name":"按日返","dialog_content_list":["您已通过返利投注册玉米理财","每次投资通过返利投跳转即可享受返利"]}]
         * iphone_flag : false
         */

        private String bid_image_max;
        private String remain_time;
        private float financing_rate;
        private String project_introduction;
        private String bid_brief;
        private String status_str;
        private boolean is_crowdfunding_bid;
        private PlatformBean platform;
        private int settlement_days_after_bid_fulled;
        private String bid_detail;
        private int status;
        private String bid_order_url;
        private int bid_id;
        private String min_invest_amount;
        private String project_initiator;
        private String name;
        private String bid_url;
        private String bid_image_small;
        private String total_amount;
        private String pc_banner_image;
        private String platform_bonus_interest;
        private boolean is_user_enjoy_bonus;
        private boolean iphone_flag;
        private List<ProjectPlanBean> project_plan;
        private List<TagListBean> tag_list;

        public String getBid_image_max() {
            return bid_image_max;
        }

        public void setBid_image_max(String bid_image_max) {
            this.bid_image_max = bid_image_max;
        }

        public String getRemain_time() {
            return remain_time;
        }

        public void setRemain_time(String remain_time) {
            this.remain_time = remain_time;
        }

        public float getFinancing_rate() {
            return financing_rate;
        }

        public void setFinancing_rate(float financing_rate) {
            this.financing_rate = financing_rate;
        }

        public String getProject_introduction() {
            return project_introduction;
        }

        public void setProject_introduction(String project_introduction) {
            this.project_introduction = project_introduction;
        }

        public String getBid_brief() {
            return bid_brief;
        }

        public void setBid_brief(String bid_brief) {
            this.bid_brief = bid_brief;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public boolean isIs_crowdfunding_bid() {
            return is_crowdfunding_bid;
        }

        public void setIs_crowdfunding_bid(boolean is_crowdfunding_bid) {
            this.is_crowdfunding_bid = is_crowdfunding_bid;
        }

        public PlatformBean getPlatform() {
            return platform;
        }

        public void setPlatform(PlatformBean platform) {
            this.platform = platform;
        }

        public int getSettlement_days_after_bid_fulled() {
            return settlement_days_after_bid_fulled;
        }

        public void setSettlement_days_after_bid_fulled(int settlement_days_after_bid_fulled) {
            this.settlement_days_after_bid_fulled = settlement_days_after_bid_fulled;
        }

        public String getBid_detail() {
            return bid_detail;
        }

        public void setBid_detail(String bid_detail) {
            this.bid_detail = bid_detail;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBid_order_url() {
            return bid_order_url;
        }

        public void setBid_order_url(String bid_order_url) {
            this.bid_order_url = bid_order_url;
        }

        public int getBid_id() {
            return bid_id;
        }

        public void setBid_id(int bid_id) {
            this.bid_id = bid_id;
        }

        public String getMin_invest_amount() {
            return min_invest_amount;
        }

        public void setMin_invest_amount(String min_invest_amount) {
            this.min_invest_amount = min_invest_amount;
        }

        public String getProject_initiator() {
            return project_initiator;
        }

        public void setProject_initiator(String project_initiator) {
            this.project_initiator = project_initiator;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBid_url() {
            return bid_url;
        }

        public void setBid_url(String bid_url) {
            this.bid_url = bid_url;
        }

        public String getBid_image_small() {
            return bid_image_small;
        }

        public void setBid_image_small(String bid_image_small) {
            this.bid_image_small = bid_image_small;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getPc_banner_image() {
            return pc_banner_image;
        }

        public void setPc_banner_image(String pc_banner_image) {
            this.pc_banner_image = pc_banner_image;
        }

        public String getPlatform_bonus_interest() {
            return platform_bonus_interest;
        }

        public void setPlatform_bonus_interest(String platform_bonus_interest) {
            this.platform_bonus_interest = platform_bonus_interest;
        }

        public boolean isIs_user_enjoy_bonus() {
            return is_user_enjoy_bonus;
        }

        public void setIs_user_enjoy_bonus(boolean is_user_enjoy_bonus) {
            this.is_user_enjoy_bonus = is_user_enjoy_bonus;
        }

        public boolean isIphone_flag() {
            return iphone_flag;
        }

        public void setIphone_flag(boolean iphone_flag) {
            this.iphone_flag = iphone_flag;
        }

        public List<ProjectPlanBean> getProject_plan() {
            return project_plan;
        }

        public void setProject_plan(List<ProjectPlanBean> project_plan) {
            this.project_plan = project_plan;
        }

        public List<TagListBean> getTag_list() {
            return tag_list;
        }

        public void setTag_list(List<TagListBean> tag_list) {
            this.tag_list = tag_list;
        }

        public static class PlatformBean {
            /**
             * interest_max : 0
             * is_bank : false
             * is_vc : false
             * logo_thumbnail : https://o0s106hgi.qnssl.com/media/plat-logo/e7253318/thumb-e7253318-7d4e-470f-a622-04c570ba9623-1465295002.png
             * bonding_method : 六重风控
             * can_auto_login : false
             * id : 27
             * location_city : 北京
             * registered_capital : 882
             * credit_score : 3
             * is_state : false
             * online_date : 2014-10-30
             * is_private : true
             * shortname : duocaitou
             * can_auto_register : false
             * duration_min_unit : 年
             * logo_app_square : https://o0s106hgi.qnssl.com/media/plat-logo/3daccf2c/app-3daccf2c-2d6d-4a80-b70f-43f459147de1-1470037853.png
             * location_province : 北京
             * duration_max_unit : 年
             * background : 获得小米创始人雷军旗下顺为资本千万级天使轮投资
             * is_public : false
             * can_bind_account : false
             * logo_main : https://o0s106hgi.qnssl.com/media/plat-logo/9643bece/main-9643bece-7a22-4919-8ce5-6974f8515dc2-1465295002.png
             * main_bussiness : 私募股权、消费众筹、收益权众筹
             * name : 多彩投
             * url : http://www.duocaitou.com/
             * logo_app : https://o0s106hgi.qnssl.com/media/plat-logo/a9a46379/app-a9a46379-f323-4ba0-be1d-e3af637f12ea-1490927788.png
             * interest_min : 5
             * duration_max : 0
             * duration_min : 0
             */

            private String interest_max;
            private boolean is_bank;
            private boolean is_vc;
            private String logo_thumbnail;
            private String bonding_method;
            private boolean can_auto_login;
            private int id;
            private String location_city;
            private String registered_capital;
            private String credit_score;
            private boolean is_state;
            private String online_date;
            private boolean is_private;
            private String shortname;
            private boolean can_auto_register;
            private String duration_min_unit;
            private String logo_app_square;
            private String location_province;
            private String duration_max_unit;
            private String background;
            private boolean is_public;
            private boolean can_bind_account;
            private String logo_main;
            private String main_bussiness;
            private String name;
            private String url;
            private String logo_app;
            private String interest_min;
            private String duration_max;
            private String duration_min;

            public String getInterest_max() {
                return interest_max;
            }

            public void setInterest_max(String interest_max) {
                this.interest_max = interest_max;
            }

            public boolean isIs_bank() {
                return is_bank;
            }

            public void setIs_bank(boolean is_bank) {
                this.is_bank = is_bank;
            }

            public boolean isIs_vc() {
                return is_vc;
            }

            public void setIs_vc(boolean is_vc) {
                this.is_vc = is_vc;
            }

            public String getLogo_thumbnail() {
                return logo_thumbnail;
            }

            public void setLogo_thumbnail(String logo_thumbnail) {
                this.logo_thumbnail = logo_thumbnail;
            }

            public String getBonding_method() {
                return bonding_method;
            }

            public void setBonding_method(String bonding_method) {
                this.bonding_method = bonding_method;
            }

            public boolean isCan_auto_login() {
                return can_auto_login;
            }

            public void setCan_auto_login(boolean can_auto_login) {
                this.can_auto_login = can_auto_login;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLocation_city() {
                return location_city;
            }

            public void setLocation_city(String location_city) {
                this.location_city = location_city;
            }

            public String getRegistered_capital() {
                return registered_capital;
            }

            public void setRegistered_capital(String registered_capital) {
                this.registered_capital = registered_capital;
            }

            public String getCredit_score() {
                return credit_score;
            }

            public void setCredit_score(String credit_score) {
                this.credit_score = credit_score;
            }

            public boolean isIs_state() {
                return is_state;
            }

            public void setIs_state(boolean is_state) {
                this.is_state = is_state;
            }

            public String getOnline_date() {
                return online_date;
            }

            public void setOnline_date(String online_date) {
                this.online_date = online_date;
            }

            public boolean isIs_private() {
                return is_private;
            }

            public void setIs_private(boolean is_private) {
                this.is_private = is_private;
            }

            public String getShortname() {
                return shortname;
            }

            public void setShortname(String shortname) {
                this.shortname = shortname;
            }

            public boolean isCan_auto_register() {
                return can_auto_register;
            }

            public void setCan_auto_register(boolean can_auto_register) {
                this.can_auto_register = can_auto_register;
            }

            public String getDuration_min_unit() {
                return duration_min_unit;
            }

            public void setDuration_min_unit(String duration_min_unit) {
                this.duration_min_unit = duration_min_unit;
            }

            public String getLogo_app_square() {
                return logo_app_square;
            }

            public void setLogo_app_square(String logo_app_square) {
                this.logo_app_square = logo_app_square;
            }

            public String getLocation_province() {
                return location_province;
            }

            public void setLocation_province(String location_province) {
                this.location_province = location_province;
            }

            public String getDuration_max_unit() {
                return duration_max_unit;
            }

            public void setDuration_max_unit(String duration_max_unit) {
                this.duration_max_unit = duration_max_unit;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public boolean isIs_public() {
                return is_public;
            }

            public void setIs_public(boolean is_public) {
                this.is_public = is_public;
            }

            public boolean isCan_bind_account() {
                return can_bind_account;
            }

            public void setCan_bind_account(boolean can_bind_account) {
                this.can_bind_account = can_bind_account;
            }

            public String getLogo_main() {
                return logo_main;
            }

            public void setLogo_main(String logo_main) {
                this.logo_main = logo_main;
            }

            public String getMain_bussiness() {
                return main_bussiness;
            }

            public void setMain_bussiness(String main_bussiness) {
                this.main_bussiness = main_bussiness;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getLogo_app() {
                return logo_app;
            }

            public void setLogo_app(String logo_app) {
                this.logo_app = logo_app;
            }

            public String getInterest_min() {
                return interest_min;
            }

            public void setInterest_min(String interest_min) {
                this.interest_min = interest_min;
            }

            public String getDuration_max() {
                return duration_max;
            }

            public void setDuration_max(String duration_max) {
                this.duration_max = duration_max;
            }

            public String getDuration_min() {
                return duration_min;
            }

            public void setDuration_min(String duration_min) {
                this.duration_min = duration_min;
            }
        }

        public static class ProjectPlanBean {
            /**
             * plan_id : 1429
             * limit_num : 20
             * title : 【方舟集落】收益权
             * price : 20000
             * project_plan : 【方舟集落】收益权
             个人限投20份￥20000.00/份
             * reward_plan : 【方舟集落】收益权
             个人限投20份￥20000.00/份
             * reward_plan_detail : 1.预期投资收益率：20%\/年投资人每季度分红，按照实际收益率分配，若当年现金分红收益率不到10％，大股东将在第4季度(或每季度)进行补足；2. 消 费 权 益 1.一次性可获得投资金额的45%作为消费金。2.投资期限内有效，可用于方舟集落旗下及未来所有店，法定节假日不可用，需提前14天预约；3.投资人使用现金消费时，可享受房费OTA价格8折优惠；3. 额 外 福 利 1.投资满20万元的投资人，享有项目方赠送价值1000元的阿拉善西南中心出品-千花蜂蜜共两瓶。2.投资满30万元的投资人，享有酒店团建聚会套餐一次（含14个间夜住宿，公共空间使用权，与团建探险活动一次）3.推荐朋友入住可享受入住金额的15%现金奖励4. 退 出 机 制 1.自收益起始日，大股东应自收益起始日起届满3年之日起3个工作日以内以初始投资额的100％价格回购投资人收益权。2.其他：通过多彩投寻求股权收益权份额转让。
             */

            private String plan_id;
            private String limit_num;
            private String title;
            private String price;
            private String project_plan;
            private String reward_plan;
            private String reward_plan_detail;

            public String getPlan_id() {
                return plan_id;
            }

            public void setPlan_id(String plan_id) {
                this.plan_id = plan_id;
            }

            public String getLimit_num() {
                return limit_num;
            }

            public void setLimit_num(String limit_num) {
                this.limit_num = limit_num;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getProject_plan() {
                return project_plan;
            }

            public void setProject_plan(String project_plan) {
                this.project_plan = project_plan;
            }

            public String getReward_plan() {
                return reward_plan;
            }

            public void setReward_plan(String reward_plan) {
                this.reward_plan = reward_plan;
            }

            public String getReward_plan_detail() {
                return reward_plan_detail;
            }

            public void setReward_plan_detail(String reward_plan_detail) {
                this.reward_plan_detail = reward_plan_detail;
            }
        }

        public static class TagListBean {
            /**
             * is_show_dialog : true
             * name : 新手标
             * dialog_content_list : ["1","2"]
             */

            private boolean is_show_dialog;
            private String name;
            private String dialog_img;
            private String type;
            private String title;
            private List<String> content_list;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<String> getContent_list() {
                return content_list;
            }

            public void setContent_list(List<String> content_list) {
                this.content_list = content_list;
            }

            public boolean isIs_show_dialog() {
                return is_show_dialog;
            }

            public void setIs_show_dialog(boolean is_show_dialog) {
                this.is_show_dialog = is_show_dialog;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDialog_img() {
                return dialog_img;
            }

            public void setDialog_img(String dialog_img) {
                this.dialog_img = dialog_img;
            }
        }
    }
}
