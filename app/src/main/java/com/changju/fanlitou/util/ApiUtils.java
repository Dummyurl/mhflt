package com.changju.fanlitou.util;

import android.content.Context;

import com.changju.fanlitou.base.MyApplication;

/**
 * Created by chengww on 2017/5/4.
 */

public class ApiUtils {

    //debug api头
    private static final String DEBUG_HEADER = "http://m.test3.fanlitou.com/";
    private static final String COMMON_HEADER = "https://m.fanlitou.com/";
    private static final String HOME = "api/app/1.0/home/";
    private static final String HOME_BID_RECOMMEND = "api/app/1.0/home_bid_recommend/";
    //发现
    private static final String DISCOVER_PLATFORMS = "api/app/1.0/get_platforms/";
    //首页收藏平台弹窗
    private static final String FAVORITE_PLATFORM_LIST = "api/app/1.0/get_favorite_platform_list";
    //登录
    private static final String LOGIN = "api/app/1.0/login/";
    //注册
    private static final String REGISTER = "api/app/1.0/account/register/";
    //账本，投资列表
    private static final String INVEST_REPORT = "api/app/1.0/finance/invest_report/";

    // api/finance/invest_report/?user_name=15810910235&type=category

    public static String getHomePage(Context context) {

        return getHeader() + HOME + "?user_name=" + UserUtils.getUserName(context) + "&" +
                getSignPara() + "&login_token=" + UserUtils.getLoginToken(context);
    }


    public static String getHomeBidRecommend() {
        return getHeader() + HOME_BID_RECOMMEND + "?" + getSignPara();
    }

    /**
     * @param orderby           排序条件，目前为空即可
     * @param platform_bg_type  过滤条件
     *                          不限：传空值
     *                          上市系：1;
     *                          风投系：2;
     *                          国资系：3;
     *                          民营系：4;
     * @param platform_type     平台类别
     *                          不限：传空值
     *                          固收：1
     *                          众筹：2
     *                          黄金：3
     *                          活期：4
     *                          海外：6
     *                          保险：7
     * @param platform_activity 平台活动
     *                          不限：传空值
     *                          新手注册：1
     *                          投资奖品：2
     *                          红包活动：3
     *                          送优惠券：4
     * @param platform_flt      返利投平台属性
     *                          不限：传空值
     *                          账户直通：1
     *                          返利投活动：2
     * @return
     */
    public static String getDiscoverPlatforms(Context context, String orderby,
                                              String platform_type, String platform_flt,
                                              String platform_activity, String platform_bg_type) {

        String header = getHeader() + DISCOVER_PLATFORMS;

        return header + "?user_name=" + UserUtils.getUserName(context) + "&orderby=" + orderby
                + "&platform_bg_type=" + platform_bg_type + "&platform_type=" + platform_type
                + "&platform_activity=" + platform_activity
                + "&platform_flt=" + platform_flt + "&" + getSignPara();

    }

    public static String getFavoritePlatformList(Context context) {
        return getHeader() + FAVORITE_PLATFORM_LIST + "?user_name=" + UserUtils.getUserName(context)
                + "&" + getSignPara() + "&login_token=" + UserUtils.getLoginToken(context);
    }

    public static String getLogin() {
        return getHeader() + LOGIN;
    }

    public static String getRegister() {
        return getHeader() + REGISTER;
    }

    public static String getHeader() {
        if (MyApplication.isDebug)
            return DEBUG_HEADER;
        else
            return COMMON_HEADER;
    }

    public static String getRandom() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    private static String getSignPara() {
        String random = getRandom();
        long time = System.currentTimeMillis();
        String sign = NormalUtils.md5(time + random + MyApplication.key);
        return "t=" + time
                + "&random=" + random + "&sign=" + sign;
    }

    //注册 传入 手机号 和 类型
    private static final String SEND_SMS = "api/app/1.0/account/sendsms/";

    public static String getSendSms(String phone_number, String type) {
        return getHeader() + SEND_SMS + "?" + getSignPara()
                + "&type=" + type + "&phone_number=" + phone_number;
    }

    /**
     * 忘记密码
     *
     * @return
     */
    public static String postForgetPassword() {
        return getHeader() + "api/app/1.0/account/forget_password/";
    }

    private static final String SAVE_FAVORITE_PLATFORM = "api/app/1.0/save_favorite_platforms/";

    public static String postSaveFavoritePlatform() {
        return getHeader() + SAVE_FAVORITE_PLATFORM;
    }

    public static String postSaveFavoritePlatformFromDetail() {
        return getHeader() + "api/app/1.0/save_favorite_platform/";
    }

    private static final String ACCOUNT_INFO = "api/app/1.0/account_info/";

    public static String getAccountInfo(Context context) {
        return getHeader() + ACCOUNT_INFO + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    private static final String REPAYING = "api/app/1.0/finance/invested/repaying/";

    public static String getRepaying(Context context, int page) {
        return getHeader() + REPAYING + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=10";
    }

    private static final String REPAYING_COMPLETE = "api/app/1.0/finance/invested/complete/";

    public static String getRepayingComplete(Context context, int page) {
        return getHeader() + REPAYING_COMPLETE + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=10";
    }

    //资金明细
    /*
    http://m.test2.fanlitou.com/api/app/1.0/account/transaction/
    ?user_name=13426178576&filter_id=&content_id=&page=1
    &t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    private static final String MONEY_DETAIL = "api/app/1.0/account/transaction/";

    public static String getMoneyDetail(Context context, int page, String content_id, String filter_id) {
        return getHeader() + MONEY_DETAIL + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&content_id=" + content_id + "&filter_id=" + filter_id
                + "&page_count=" + 10;
    }

    //二级分类—Banner
    /*
    http://m.test2.fanlitou.com/api/app/1.0/wap_second_page_banner
    ?page_type=current
    &t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    private static final String CLASSIFY_BANNER = "api/app/1.0/wap_second_page_banner/";

    public static String getClassifyBanner(Context context, String page_type) {
        return getHeader() + CLASSIFY_BANNER + "?" + getSignPara()
                + "&page_type=" + page_type
                + "&login_token=" + UserUtils.getLoginToken(context);
    }


    //二级分类页面 黄金
    /*
    http://m.test2.fanlitou.com/api/app/1.0/get_home_gold
    /?t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    private static final String HOME_GOLD = "api/app/1.0/get_home_gold/";

    public static String getHomeGold(Context context,String platformName) {
        return getHeader() + HOME_GOLD + "?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&platform_short_name=" + platformName;
    }

    //二级分类页面 海外
    /*
    http://m.test2.fanlitou.com/api/app/1.0/get_home_foreign
    /?t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    private static final String HOME_FOREIGN = "api/app/1.0/get_home_foreign/";

    public static String getHomeForeign(Context context) {
        return getHeader() + HOME_FOREIGN + "?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //二级分类页面 众筹
    /*
    http://m.test2.fanlitou.com/api/app/1.0/second_crowdfunding_bid_list
    /?&t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    private static final String HOME_CROWDFUNDING = "api/app/1.0/second_crowdfunding_bid_list/";

    public static String getHomeCrowdfunding(Context context) {
        return getHeader() + HOME_CROWDFUNDING + "?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //二级分类列表 活期
    /*
    http://m.test2.fanlitou.com/api/app/1.0/current_bid_category_list/
    ?t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    public static String getHomeCurrent(Context context) {
        return getHeader() + "api/app/1.0/current_bid_category_list/" + "?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //二级分类列表 基金
    public static String getHomeFund(Context context) {
        return getHeader() + "api/app/1.0/fund/get_fund_second_page/" + "?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    /*
     * 查询条件
     *
     * */
    public static String getQueryCondition(Context context) {
        return getHeader() + "api/app/1.0/insurance/classify_list/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                ;
    }

    //发现页－标的详情－保险
    public static String getBidInsuranceInfo2(String classify_insurance_key, String classify_person_key) {
        return getHeader() + "api/app/1.0/insurance_bid_category_list/"
                + "?" + getSignPara() + "&classify_insurance_key=" + classify_insurance_key +
                "&classify_person_key=" + classify_person_key;
    }


    //二级分类-固收
    /*
    http://m.test2.fanlitou.com/api/app/1.0/get_home_fix
    /?t=1494902373501&random=123456&sign=712a4ab4620d49ca9293e25c1292d0c2&login_token=123
     */
    public static String getHomeFixed(Context context) {
        return getHeader() + "api/app/1.0/get_home_fix" + "?" + getSignPara() + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现，标的列表
    private static final String BID_LIST = "api/app/1.0/bid_list/";

    public static String getBidList(Context context,
                                    String order_by_name,
                                    String order_by_type,
                                    int new_user,
                                    int page,
                                    int page_count) {
        return getHeader() + BID_LIST + "?user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context) + "&" + getSignPara()
                + "&order_by_name=" + order_by_name
                + "&order_by_type=" + order_by_type + "&new_user=" + new_user
                + "&page=" + page + "&page_count=" + page_count + "&category=1";
    }

    public static String getInvestReport(Context context, String type) {
        return getHeader() + INVEST_REPORT + "?user_name=" + UserUtils.getUserName(context)
                + "&type=" + type + "&login_token=" + UserUtils.getLoginToken(context) + "&" + getSignPara();
    }

    public static String getPlatformNames() {
        return getHeader() + "api/app/1.0/get_platform_names/"
                + "?" + getSignPara();
    }

    //账本－回款日历
    public static String getRepayCalendar(Context context, int year, int month, int repay_day) {
        return getHeader() + "api/app/1.0/finance/repay_calendar/"
                + "?" + getSignPara() + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&repay_year=" + year + "&repay_month=" + month +
                "&repay_day=" + repay_day;
    }

    public static String getBankInfo(Context context) {
        return getHeader() + "api/app/1.0/account/bank_info/"
                + "?" + getSignPara() + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //添加银行卡信息
    public static String getAddBankInfo(Context context) {
        return getHeader() + "api/app/1.0/account/bank/" + "?"
                + getSignPara() + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //红包
    public static String getMyRedPacketInfo(Context context, int award_type, int page) {
        return getHeader() + "api/app/1.0/account/activity/get_my_redpacket_info/"
                + "?" + getSignPara() + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page_count=" + 10 + "&award_type=" + award_type
                + "&page=" + page;
    }

    //我的－提现－获取提现信息
    public static String getWithDrawInfo(Context context) {
        return getHeader() + "api/app/1.0/account/withdraw/info/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //我的－银行卡管理－添加银行卡
    public static String postAddBankCard() {
        return getHeader() + "api/app/1.0/account/bank/add/";
    }

    //我的－提现－发起提现
    public static String postWithDraw() {
        return getHeader() + "api/app/1.0/account/withdraw/";
    }

    public static String postChangePWD() {
        return getHeader() + "api/app/1.0/account/change_password/";
    }

    //我的－投资记录
    public static String getInVestRecord(Context context, String type,
                                         int page, int page_count) {
        return getHeader() + "api/app/1.0/account/invest_record/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&type=" + type + "&page=" + page +
                "&page_count=" + page_count;
    }

    //发现页－标的详情－固收
    public static String getBidFixInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页－标的详情－活期
    public static String getBidCurrentInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/current_bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页－标的详情－众筹（维C理财）
    public static String getBidVcInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/wuchou_bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页－标的详情－海外
    public static String getBidAbroadInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/foreign_bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页－标的详情－黄金
    public static String getBidGoldInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/gold_bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页－标的详情－保险
    public static String getBidInsuranceInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/insurance_bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页－标的详情－众筹（多彩投）
    public static String getBidrowdfundingInfo(Context context, int bidId) {
        return getHeader() + "api/app/1.0/crowdfunding_bid/" + bidId + "/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //首页显示活动菜单
    public static String getMenuInfo() {
        return getHeader() + "api/app/1.0/activity/menu_info/"
                + "?" + getSignPara();
    }

    /**
     * 账本－投资报表－活期回款明细
     *
     * @param context
     * @param type    查询类型
     *                p2p_current：固收活期
     *                gold_current：黄金活期
     * @param bid_id
     * @return url
     */
    public static String getCurrentIncomeList(Context context, String type, int bid_id) {
        return getHeader() + "api/app/1.0/finance/current_income_list/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&type=" + type + "&bid_id=" + bid_id;
    }

    /**
     * 账本－投资报表－活期回款明细
     *
     * @param context
     * @param type     查询类型
     *                 1:待回款
     *                 2:已回款
     *                 3:全部
     * @param bid_type 查询类型
     *                 固收：p2p
     *                 黄金：gold
     *                 海外：abroad
     * @param bid_id
     * @return url
     */
    public static String getBidRepayDetail(Context context, int type,
                                           String bid_type, int bid_id) {
        return getHeader() + "api/app/1.0/finance/invest_report/bid_repay_detail/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&bid_type=" + bid_type + "&bid_id=" + bid_id
                + "&type=" + type;
    }

    //首页二级分类列表 保险
    public static String getBidInsuranceInfo(String classify_insurance_key, String classify_person_key) {
        return getHeader() + "api/app/1.0/insurance_bid_category_list/"
                + "?" + getSignPara() + "&classify_insurance_key=" + classify_insurance_key +
                "&classify_person_key=" + classify_person_key;
    }

    //首页二级分类列表 保险 查询条件
    public static String getBidInsuranceSearch() {
        return getHeader() + "api/app/1.0/insurance/classify_list/"
                + "?" + getSignPara();
    }

    //发现页－标的详情－黄金金价
    public static String getGoldHistoryPrice(String start_time, String end_time) {
        return getHeader() + "api/app/1.0/gold/history_price/" +
                "?" + getSignPara() + "&start_time=" + start_time + "&end_time="
                + end_time;
    }

    //锦囊-列表
    public static String getArticleList(int page, int page_count) {
        return getHeader() + "api/app/1.0/article_list/" + "?" + getSignPara()
                + "&page=" + page + "&page_count=" + page_count;
    }

    //锦囊-详情
    public static String getArticleDetial(int artical_id) {
        return getHeader() + "api/app/1.0/article/" + artical_id + "/?"
                + getSignPara();
    }

    //提现记录
    public static String getWithDrawRecord(Context context, int page, int page_count) {
        return getHeader() + "api/app/1.0/account/withdraw/record/"
                + "?" + getSignPara() + "&user_name="
                + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //标的详情-前往购买-固收
    public static String postInvest(int bid_id) {
        return getHeader() + "api/app/1.0/bid/" +
                bid_id + "/invest/";
    }

    //发现页－平台详情
    public static String getPlatformDetails(Context context, int platform_id) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                ;
    }

    //发现页-平台详情-平台标的列表-固收
    public static String getPlatformBidlistFixed(Context context, int platform_id, int page, int page_count) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "platform_bids/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //发现页-平台详情-平台标的列表-黄金
    public static String getPlatformBidlistGold(Context context, int platform_id, int page, int page_count) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "gold_bids/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //发现页-平台详情-平台标的列表-活期
    //由于该平台性质既包含活期也包含活期，顾不分页，一次性展示所有产品
    public static String getPlatformBidlistCurrent(Context context, int platform_id) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "current_bids/"
                + "?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //发现页-平台详情-平台标的列表-海外
    public static String getPlatformBidlistForeign(Context context, int platform_id, int page, int page_count) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "foreign_bids/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //发现页-平台详情-平台标的列表-众筹（维C）
    public static String getPlatformBidlistWuchou(Context context, int platform_id, int page, int page_count) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "wuchou_bids/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //发现页-平台详情-平台标的列表-众筹（多彩投）
    public static String getPlatformBidlistCrowdfunding(Context context, int platform_id, int page, int page_count) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "crowdfunding_bids/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //发现页-平台详情-平台标的列表-保险
    public static String getPlatformBidlistInsurance(Context context, int platform_id, int page, int page_count) {
        return getHeader() + "api/app/1.0/platform/" + platform_id + "/" + "insurance_bids/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //版本升级接口
    public static String getVersionInfo(Context context) {
        return getHeader() + "api/app/1.0/app/get_version_info/?" + getSignPara() +
                "&device=android&version_code=" +
                NormalUtils.getVersion(context);
    }

    //智能投顾-前往购买
    public static String getIntelligentInvest(Context context, int bid_id) {
        return getHeader() + "api/app/1.0/intelligent_invest/" +
                bid_id + "/invest/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //智能投顾-获取开户信息
    public static String getOpeanAccountInfo(Context context,int bid_id){
        return getHeader() + "api/app/1.0/intelligent_invest/get_open_account_info/" +
                bid_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //智能投顾-获取短信验证码
    public static String postIntelligentSMS(){
        return getHeader() + "api/app/1.0/intelligent_invest/sms_auth_code/";
    }

    //智能投顾-开户
    public static String postOpenAccount(){
        return getHeader() + "api/app/1.0/intelligent_invest/open_account/";
    }

    //智能投顾-获取购买确认信息
    public static String getConfirmApply(Context context,int bid_id){
        return getHeader() + "api/app/1.0/intelligent_invest/confirm_apply/"
                + bid_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //智能投顾-购买页获取投资预估利息返利
    public static String getInvestCalculator(int bid_id,String bid_type,String bid_amount){
        return getHeader() + "api/app/1.0/invest_calculator/" + "?"
                + getSignPara() + "&bid_id=" + bid_id
                + "&bid_type=" + bid_type + "&bid_amount=" + bid_amount;
    }

    //智能投顾-获取充值确认信息
    public static String getRechargeConfirm(Context context,int bid_id){
        return getHeader() + "api/app/1.0/intelligent_invest/confirm_recharge/"
                + bid_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //智能投顾-充值
    public static String postRecharge(){
        return getHeader() + "api/app/1.0/intelligent_invest/recharge/";
    }

    //智能投顾-购买
    public static String postIntelligentInvest(){
        return getHeader() + "api/app/1.0/intelligent_invest/invest/";
    }

    //智能投顾-购买结果信息
    public static String getPurchaseResult(Context context,int bid_id,String flt_order_no){
        return getHeader() + "api/app/1.0/intelligent_invest/purchase_result/"
                + bid_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&flt_order_no=" + flt_order_no;
    }

    //智能投顾-获取提现信息
    public static String getConfirmWithdraw(Context context,int platform_id){
        return getHeader() + "api/app/1.0/intelligent_invest/confirm_withdraw/"
                + platform_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //智能投顾-提现
    public static String postIntelligentWithdraw(){
        return getHeader() + "api/app/1.0/intelligent_invest/withdraw/";
    }

    //智能投顾-提现结果信息
    public static String getResultWithdraw(Context context,int platform_id,String flt_order_no){
        return getHeader() + "api/app/1.0/intelligent_invest/result_withdraw/"
                + platform_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&flt_order_no=" + flt_order_no;
    }

    //我的－账户管理（原）  平台管理（现）
    public static String getAccountManagement(Context context,String type,
                                              int page){
        return getHeader() + "api/app/1.0/account/account_management/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&type=" + type
                + "&page=" + page
                + "&page_count=" + 10;
    }

    //基金-详情-基础信息
    // api/app/1.0/fund/get_fund_detail_basic_info/{fund_id}/
    public static String getFundingDetailBaseInfo(Context context,int fund_id){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_basic_info/"
                + fund_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //基金-详情-获取历史业绩表现
    public static String getFundingHistoryNav(Context context,int fund_id){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_history_nav/"
                + fund_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //基金-详情-获取历史净值
    public static String getFundingHistoryUnitNav(Context context,int fund_id, int page, int page_count){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_history_unit_nav/"
                + fund_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //基金-详情-获取收益率、净值走势
    public static String getFundingIncomeRate(Context context,int fund_id,String query_type,String duration_type){
        return getHeader() + "api/app/1.0/fund/get_fund_income_rate/"
                + "?" + getSignPara()
                + "&query_type=" + query_type
                + "&fund_id=" + fund_id
                + "&duration_type=" + duration_type
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //基金-详情-费率相关
    public static String getFundingFeeRate(Context context,int fund_id){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_fee_info/"
                + fund_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //基金-详情-历史分红
    public static String getFundingHistoryDiv(Context context,int fund_id,int page,int page_count){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_div_info/"
                + fund_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page + "&page_count=" + page_count;
    }

    //基金-详情-基金经理
    public static String getFundingManager(Context context,int fund_id){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_manager_info/"
                + fund_id + "/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //智能投顾-开户（存管）
    public static String getBankStorageOpenAccount(Context context, int bid_id, String real_name,
                                                   String id_number, String bank_phone_num, String bank_card_number,
                                                   int bank_id, String sms_auth_code, String bank_branch_name,
                                                   String province_name, int province_id, String city_name,
                                                   int city_id){
        return getHeader() + "api/app/1.0/intelligent_invest_bankstorage/open_account/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&bid_id=" + bid_id
                + "&real_name=" + real_name
                + "&id_number=" + id_number
                + "&bank_phone_num=" + bank_phone_num
                + "&bank_card_number=" + bank_card_number
                + "&bank_id=" + bank_id
                + "&sms_auth_code=" + sms_auth_code
                + "&bank_branch_name=" + bank_branch_name
                + "&province_name=" + province_name
                + "&province_id=" + province_id
                + "&city_name=" + city_name
                + "&city_id=" + city_id;

    }

    //智能投顾-查询开户状态
    public static String postAccountQuery(){
        return getHeader() + "api/app/1.0/intelligent_invest/account_query/";
    }

    //智能投顾-购买（存管）
    public static String getBankStorageInvest(Context context,int bid_id,String amount){
        return getHeader() + "api/app/1.0/intelligent_invest_bankstorage/invest/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&bid_id=" + bid_id
                + "&amount=" + amount;
    }

    //智能投顾-查询投资状态
    public static String postInvestQuery(){
        return getHeader() + "api/app/1.0/intelligent_invest/invest_query/";
    }

    //智能投顾-提现（存管）
    public static String getBankStorageWithdraw(Context context,int platform_id,float amount){
        return getHeader() + "api/app/1.0/intelligent_invest_bankstorage/withdraw/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&platform_id=" + platform_id
                + "&amount=" + amount;
    }

    //智能投顾-查询提现状态
    public static String postWithdrawQuery(){
        return getHeader() + "api/app/1.0/intelligent_invest/withdraw_query/";
    }

    //智能投顾-查询充值状态
    public static String postDepositQuery(){
        return getHeader() + "api/app/1.0/intelligent_invest/deposit_query/";
    }

    //智能投顾-充值（存管）
    public static String getBankstorageRecharge(Context context,int platform_id,
                                                String amount,int bid_id){
        return getHeader() + "api/app/1.0/intelligent_invest_bankstorage/recharge/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&platform_id=" + platform_id
                + "&bid_id=" + bid_id
                + "&amount=" + amount;
    }

    //我的-我的基金-基金档案
    public static String getFundDetailProfileInfo(int fundID){
        return getHeader() + "api/app/1.0/fund/get_fund_detail_profile_info/" + fundID+"/?" + getSignPara();
    }

    //基金-基金申购
    public static String getConfirmPurchase(Context context,int fundID){
        return getHeader() + "api/app/1.0/fund/confirm_purchase/" + fundID+"/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }
    //基金-基金申购-计算基金购买费率
    public static String getCalcPurchaseFee(Context context,int fundID, String amount){
        return getHeader() + "api/app/1.0/fund/calc_purchase_fee" + "/?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context)+"&fund_id=" + fundID+"&amount=" + amount;
    }

    //基金-基金申购-确认申购
    public static String postWithConfirmPurchase() {
        return getHeader() + "api/app/1.0/fund/apply/";
    }

    //基金-列表页面
    public static String getFundList(String fund_type,String values_type,int page,int page_count,String search){
        return getHeader() + "api/app/1.0/fund/get_fund_list/"
                +"?" + getSignPara()
                + "&fund_type=" + fund_type
                + "&values_type=" + values_type
                + "&page=" + page
                + "&page_count=" + page_count
                + "&search=" + search;
    }

    //我的-投资记录-使用加息券
    public static String postApplyRaiseInterestTicket(){
        return getHeader() + "api/app/1.0/apply_raise_interest_ticket/";
    }

    //我的基金-交易记录页面
    public static String getFundTradeRecord(Context context, int page, int page_count){
        return getHeader() + "api/app/1.0/account/fund/get_fund_invest_record/"
                +"?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page
                + "&page_count=" + page_count;
    }
    //已完成订单信息
    public static String getCompleteOrders(Context context,int page,int page_count){
        return getHeader() + "api/app/1.0/account/fund/get_complete_orders/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page
                + "&page_count=" + page_count;
    }
    //持有中基金信息
    public static String getHoldingFund(Context context,int page,int page_count){
        return getHeader() + "api/app/1.0/account/fund/get_holding_fund/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page
                + "&page_count=" + page_count;
    }
    //处理中基金信息

    public static String getProcessingFund(Context context,int page,int page_count){
        return getHeader() + "api/app/1.0/account/fund/get_processing_fund/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page
                + "&page_count=" + page_count;
    }
    //4.我的基金总览页
    public static String getFundSummaryInfo(Context context){
        return getHeader() + "api/app/1.0/account/fund/get_fund_summary_info/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                ;
    }

    //返利宝总览信息
    public static String getFanlibaoSummaryInfo(Context context){
        return getHeader() + "api/app/1.0/account/credit/account_info/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                ;
    }

    //返利宝明细记录
    public static String getFanlibaoDetailRecord(Context context,int page,int page_count,String record_type){
        return getHeader() + "api/app/1.0/account/credit/detail_records/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&page=" + page
                + "&page_count=" + page_count
                + "&record_type=" + record_type;
    }

    //基金-基金赎回
    public static String getConfirmRedeem(Context context,int fundID){
        return getHeader() + "api/app/1.0/fund/confirm_redeem/" + fundID+"/?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //基金-基金申购-计算基金购买费率
    public static String getCalcRedeemFee(Context context,int fundID, String amount){
        return getHeader() + "api/app/1.0/fund/calc_redeem_fee" + "/?" + getSignPara()
                + "&login_token=" + UserUtils.getLoginToken(context)+"&fund_id=" + fundID+"&amount=" + amount;
    }
    //基金-基金申购-确认申购
    public static String postWithConfirmRedeem() {
        return getHeader() + "api/app/1.0/fund/redeem/";
    }
    //1.获得基金开户信息
    public static String getFundBankInfo(Context context){
        return getHeader() + "api/app/1.0/fund/get_fund_bank_info/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    //2.提交信息获取验证码
    public static String postBankSendSms(){
        return getHeader() + "api/app/1.0/fund/bank_send_sms/";
    }
    //3.提交开户验证码
    public static String postAddBank(){
        return getHeader() + "api/app/1.0/fund/add_bank/";
    }

    //申购、认购、赎回确认页
    public static String getPurchaseRedeemResult(Context context,int fund_id,String order_no){
        return getHeader() + "api/app/1.0/fund/purchase_redeem_result/"
                + fund_id + "/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&order_no=" + order_no;

    }

    //基金-风险测评，获取信息
    public static String getFundQuestion(Context context){
        return getHeader() + "api/app/1.0/account/risk_assessment/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context);
    }

    /**
     * 基金-进行风险评测
     */
    public static String postQuestionId() {
        return getHeader() + "api/app/1.0/account/do_risk_assessment/";
    }

    /**
     * 返利宝介绍页面H5
     */
    public static String getFanlibaoIntroduceH5() {
        return getHeader() + "mobile/rebate_h5/";
    }

    /**
     * 新手指南H5
     */
    public static String getBeginnerGuideH5() {
        return getHeader() + "mobile/beginner_guideH5_h5/";
    }

    /**
     * 活动页1H5
     */
    public static String getActivity1H5() {
        return getHeader() + "mobile/activity_h5/";
    }

    /**
     * 活动页2H5
     */
    public static String getActivity2H5() {
        return getHeader() + "mobile/activity_h5/secondary/";
    }

    /**
     * 邀请有礼H5
     */
    public static String getInviteH5() {
        return getHeader() + "mobile/invite_h5/";
    }

    /**
     * 弹窗H5
     */
    public static String getHomeDialogH5() {
        return getHeader() + "mobile/home_dialog_h5/";
    }

    /**
     * 智投-确认购买页H5
     */
    public static String getIntelligentInvestH5(int bid_id) {
        return getHeader() + "mobile/intelligent_invest_h5/" + bid_id + "/";
    }

    /**
     * 智投-回调页H5
     */
    public static String getIntelligentCallbackH5() {
        return getHeader() + "mobile/intelligent_h5/callback/";
    }
    /**
     *首页弹框信息
     */
    public static String getHomeDialog(Context context,String user_cookie_value,String no_user_cookie_value) {
        return getHeader() + "api/app/1.0/home_dialog/"
                + "?" + getSignPara()
                + "&user_name=" + UserUtils.getUserName(context)
                + "&login_token=" + UserUtils.getLoginToken(context)
                + "&user_cookie_value=" + user_cookie_value
                + "&no_user_cookie_value=" + no_user_cookie_value;
    }
    /**
     * 积分开户
     * user_name	sring	用户名
     * t	        String	时间戳，如1494900313656
     * sign	        String	验签
     * random	    String	6位随机数
     * login_token	String	login接口中返回的用户登录token
     * @return
     */
    public static String postCreditOpen() {
        return getHeader() + "api/app/1.0/account/credit/open_account/";
    }

    /**
     * 智能投顾-注册（快捷）
     * 玉米理财 需额外开户
     * @return
     */
    public static String postYMRegister(){
        return getHeader() + "api/app/1.0/intelligent_invest/register/";
    }

    /**
     * 我的--我的活动H5链接
     * @return
     */
    public static String getMyselfActivityH5(){
        return getHeader() + "/mobile/myself_activity_h5/";
    }
}
