package com.changju.fanlitou.activity.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.bean.mine.WithDrawInfo;
import com.changju.fanlitou.fragment.mine.HasBankCardFragment;
import com.changju.fanlitou.fragment.mine.NoBankCardFragment;
import com.changju.fanlitou.ui.dialog.JFAgreementDialog;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/26.
 */

public class EnchashmentActivity extends BaseActivity {
    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_enchashment;
    }

    private View include;
    private View include_load_error;

    @Override
    public void initView(View view) {
        UserUtils.checkLogin(this,EnchashmentActivity.class);

        GrowingIO.getInstance().setPageName(this, "我的-提现");

        findViewById(R.id.iv_back_my_account).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    private FragmentTransaction fragmentTransaction;
    @Override
    public void doBusiness(final Context mContext) {
    }

    private String lastString;
    private void initData(Context mContext) {
        OkGo.get(ApiUtils.getWithDrawInfo(mContext))
                .tag(EnchashmentActivity.class.getSimpleName())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (s.equals(lastString) || isFinishing() || isDestroyed())
                            return;

                        lastString = s;

                        WithDrawInfo withDrawInfo = ParseUtils.parseByGson(
                                s,WithDrawInfo.class);
                        if (withDrawInfo.isIs_verified() &&
                                withDrawInfo.isIs_bound() &&
                                withDrawInfo.getWithdraw_info().isHas_province_city_info()){
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            HasBankCardFragment hasBankCardFragment = HasBankCardFragment.newInstance(withDrawInfo);

                            GrowingIO.getInstance().trackFragment(EnchashmentActivity.this, hasBankCardFragment);

                            fragmentTransaction.replace(R.id.layout_fragment_enchashment,
                                    hasBankCardFragment).commitAllowingStateLoss();
                        }else {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                            if (withDrawInfo.isIs_verified() &&
//                                    withDrawInfo.isIs_bound() &&
//                                    !withDrawInfo.getWithdraw_info().isHas_province_city_info()) {
//                                fragmentTransaction.replace(
//                                        R.id.layout_fragment_enchashment,
//                                        NoBankCardFragment.newInstance("您尚未完善省市信息，暂无法提现",
//                                                "去完善")).commit();
//                            }else {
                            NoBankCardFragment bindBank = NoBankCardFragment.newInstance("为保证提现正常进行，您需要先绑定提现银行卡",
                                    "前往绑卡");
                            GrowingIO.getInstance().trackFragment(EnchashmentActivity.this, bindBank);
                            fragmentTransaction.replace(
                                        R.id.layout_fragment_enchashment,
                                    bindBank).commitAllowingStateLoss();
//                            }

                        }

                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(EnchashmentActivity.this,
                                R.string.net_error);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                doBusiness(this);
                break;
        }
    }

    public void showAgreement(int type){
        Bundle args = new Bundle();
        if (type == 0){
            args.putString(JFAgreementDialog.TITLE,"《用户须知及风险提示》");
            args.putString(JFAgreementDialog.CONTENT,"<p>特别提示：本《用户须知及风险提示》是您与上海翼鸿信息科技有限公司及北京畅聚网络科技有限公司之间《返利投用户服务协议》的有效组成部分。您点击确认或勾选本《用户须知及风险提示》，即表明您已仔细阅读并理解本《用户须知及风险提示》并接受其全部条款且承诺并同意受其约束，如果您无法接受，请停止后续操作。</p>\n" +
                    "<p>1、您开启返利宝后，返利投将自动为您申购返利宝信托产品，该产品由鸿积分财宝提供，接入外贸信托提供的\"积分宝集合财产信托计划\"。鸿积分财宝项目，是由上海翼鸿信息科技有限公司与中化集团旗下中国对外经济贸易信托有限公司合作设立，为积分用户以积分受益权参与中化集团外贸信托设立的\"积分宝集合财产信托计划\"的一个通用积分产品。中国对外经济贸易信托有限公司(简称外贸信托,英文简写：FOTIC)成立于1987年9月30日,是世界500强企业中国中化集团旗下重要成员企业，中国信托业协会副会长单位，也是中国信托行业领军企业之一，注册资本22亿元人民币，净资产超过74亿元人民币，资产管理规模超过4000亿元，由中国中化集团公司全资控股。</p>\n" +
                    "<p>2.返利投平台(以下简称为“本平台”)由北京畅聚网络科技有限公司所有并运营。本平台作为一家网络信息中介机构，向您提供产品展示、交易查询、信息交互等网上交易辅助服务，您通过本平台的辅助服务进入鸿积分财宝项目的交易系统进行交易。本平台非交易机构，不参与标的发行，不对标的的发行、销售以及在此过程中您的收益、损失承担任何责任。</p>\n" +
                    "<p>3.本平台为您提供鸿积分财宝平台的开户、申购、查询、绑卡、赎回与提现等辅助投资服务。本平台将为您提供的客户服务及您在使用服务过程中应履行的义务及承诺如下：</p>\n" +
                    "<p>3.1开户</p>\n" +
                    "<p>您理解并同意，本平台为您注册成为鸿积分财宝平台的会员。</p>\n" +
                    "<p>3.2申购</p>\n" +
                    "<p>您理解并同意本平台将您的返利为您申购（包括手动申购和自动申购等申购方式）鸿积分财宝接入的信托产品。本平台非交易机构，不参与标的发行，不对标的发行、销售以及在此过程中您的收益、损失承担任何责任。本平台鸿积分财宝相关的产品由鸿积分财宝提供。返利申购成功后，资金将留在鸿积分财宝平台，并由鸿积分财宝项目为该笔资金提供生息服务。</p>\n" +
                    "<p>3.3查询</p>\n" +
                    "<p>您理解并同意本平台将对您在本平台投资鸿积分财宝的产品的所有操作进行记录，您可以通过本平台用户账户查询通过本平台购买鸿积分财宝信托产品的交易记录。</p>\n" +
                    "<p>3.4绑卡</p>\n" +
                    "<p>进行提现前，您应按照鸿积分财宝与本平台规定的流程，在本平台提交以您本人名义登记的有效银行借记卡等信息，经由本平台审核通过后，本平台会将您的账户与前述银行账户进行绑定，并在提现时为您提交给鸿积分财宝及其合作的第三方平台进行打款。如您未按照本平台与鸿积分财宝及其合作的第三方平台的规定提交相关信息或提交的信息错误、虚假、过时或不完整，或者本平台及鸿积分财宝及其合作的第三方平台有合理的理由怀疑您提交的信息为错误、虚假、过时或不完整，本平台有权拒绝为您提供银行卡认证服务，您因此未能使用赎回、提现等服务而产生的损失自行承担。</p>\n" +
                    "<p>3.5赎回与提现</p>\n" +
                    "<p>您理解并同意本平台只提供提现入口，具体的提现操作，将于收到您的前述指示后，尽快调用与鸿积分财宝对接的赎回接口，发起赎回和提现申请，并由鸿积分财宝及鸿积分财宝对接的第三方平台为您赎回和打款，实际赎回结果由本平台和鸿积分财宝及第三方平台审核通过后返回，打款金额将通过鸿积分财宝及第三方平台账户中的资金转入您经过认证的银行卡账户中。</p>\n" +
                    "<p>您了解，上述开户、申购、查询、绑卡、赎回与提现服务涉及本平台与鸿积分财宝平台的合作。您同意：(a)本平台不对前述服务的资金到账时间做任何承诺，也不承担与此相关的责任，包括但不限于由此产生的利息、货币贬值等损失；(b) 一经您使用前述服务，即表示您不可撤销地授权本平台进行相关操作，且该等操作是不可逆转的，您不能以任何理由拒绝付款或要求取消交易。</p>\n" +
                    "<p>4、在您购买鸿积分财宝提供的产品并与上海翼鸿信息科技有限公司签订《鸿积分财宝积分增值服务协议》及其他相关协议前，请认真阅读所购买的产品的相关文件的内容，并确保开启返利宝服务为您在充分了解各方面信息后作出的判断。</p>\n" +
                    "<p>5、您使用本平台的辅助服务进行产品购买，应受本平台《注册服务协议》的约束。您授权本平台将您的产品购买操作和信息传递、共享予鸿积分财宝，以便于鸿积分财宝向您提供产品销售服务；同时，您同意并授权鸿积分财宝将您通过本平台的辅助服务在鸿积分财宝进行的交易相关信息（包括开户、银行卡认证、代收/代付/快捷支付、自动申购、查询、提现等信息）共享予本平台，以便于您通过本平台查询该等信息。</p>\n" +
                    "<p>6、您充分知道并理解，鸿积分财宝的信托产品，在开启返利宝服务后，将由本平台使用您的返利余额为您申购，申购该产品也可能承担投资所带来的损失。</p>\n" +
                    "<p>7、鸿积分财宝的信托产品公布的七日年化收益率不具有当然的法律约束力，不代表投资人可能获得的最终实际收益，亦不构成上海翼鸿信息科技有限公司、中国对外经济贸易信托有限公司的任何收益承诺，投资人所能获得的最终投资收益以鸿积分财宝根据本协议实际支付的为准。</p>\n" +
                    "<p>8、本平台提供的信息资料仅供参考，一旦开启返利宝将不能关闭。</p>\n" +
                    "<p>9、最后，请您理解并确认，鉴于风险因素存在多样性以及不确定性，本文所述风险事项未包括所有风险，仅供您参考，请您谨慎独立判断并自行承担因购买产品行为所产生的一切风险，本平台不对您因购买产品行为所导致的收益、本金及其他任何损失承担任何责任。</p>");
        }else {
            args.putString(JFAgreementDialog.TITLE,"《鸿积分财宝积分增值服务协议》");
            args.putString(JFAgreementDialog.CONTENT,"<p>鸿积分财宝是上海翼鸿信息科技有限公司（以下简称“本公司”）提供的用户积分权益增值服务平台，本协议是积分权益人接受鸿积分财宝平台提供的相关服务，在平等友好自愿的情况下订立的有效约定，积分权益人通过页面点击或其他方式选择接受本协议全部内容以及本公司网站所提供的其他与鸿积分财宝积分增值服务相关的各项协议、规则。</p>\n" +
                    "<p>积分权益人在使用鸿积分财宝平台提供的各项服务之前，应仔细阅读相关的协议内容。积分权益人确认并同意本公司有权随时对与鸿积分财宝积分增值服务的相关的协议及各条款、规则作出修改，对于有关协议及各条款、规则的修改将以在鸿积分财宝平台上发布公告的形式进行公布，无需另行通知积分权益人。如积分权益人不同意相关协议及/或本公司对相关协议内容作出的修改，可以申请退出鸿积分财宝平台提供的相关服务；积分权益人一旦使用或继续使用鸿积分财宝平台提供的服务，即视为积分权益人已确知并完全同意相关协议各项内容，包括本公司对各项协议随时所做的任何修改。积分权益人在使用相关服务时,应关注并遵守其所适用的相关条款。积分权益人确认积分财宝年化收益会受到市场价格波动的影响，且鸿积分财宝平台所展示的积分财宝年化收益率水平仅为预期收益率，积分权益人的实际收益以到帐金额为准。</p>\n" +
                    "<p>一、定义</p>\n" +
                    "<p>1、积分财宝：指本公司为用户提供的可以通过本公司鸿积分财宝积分增值服务平台系统进行积分增值服务以及资金划转、信息查询的技术服务产品。</p>\n" +
                    "<p>2、《外贸信托-积分宝集合财产信托计划》：指本公司为实现积分权益人积分增值服务，接受积分权益人委托并运用积分权益人的积分的信托计划。</p>\n" +
                    "<p>3、身份认证要素：指在交易中本公司用于识别积分权益人身份的信息要素，包括但不限于积分权益人的消费积分、积分持有人银行账户、密码、数字证书、动态口令、签约时设置的电话号码、手机号码等及本公司认可的其他要素。</p>\n" +
                    "<p>4、积分权益人：指积分用户根据积分发放方的规定以及鸿积分财宝平台有关积分兑换的规定，通过本公司系统进行积分增值服务的鸿积分财宝平台注册用户。</p>\n" +
                    "<p>5、交易申请指令：指由积分权益人通过本公司系统和实名账户向鸿积分财宝平台发出的其真实意思表示的申请或意向。交易申请指令不可撤销。鸿积分财宝平台系统接收积分权益人交易申请指令并依照指令内容进行积分增值服务或付款结算行为。积分权益人确知并同意其交易状况以鸿积分财宝平台交易记录为准。</p>\n" +
                    "<p>二、服务对象范围</p>\n" +
                    "<p>1、鸿积分财宝积分增值服务仅向符合中华人民共和国年满18周岁、具有完全民事权利能力和民事行为能力且持有鸿积分财宝平台实名账户的大陆居民提供。积分权益人必须按照鸿积分财宝平台网站的注册要求提供本人真实、准确及完整的信息，并负责对本人信息及时更新。</p>\n" +
                    "<p>2、积分权益人承诺所提供的信息符合上述各项条件与要求，保证本人身份的合法性，以及所提供信息的真实性、准确性、完整性和时效性。</p>\n" +
                    "<p>3、积分权益人确知并同意一旦积分权益人提供任何不真实、不准确、不完整或过时的身份信息，或者本公司有合理理由怀疑积分权益人所提供的信息为不真实、不准确、不完整或过时、失效的，本公司有权中止（或终止）向其提供服务，并拒绝其现在或未来使用本协议约定的各项或全部服务功能，对此本公司不承担任何责任，因此所产生的任何直接或间接的支出、损失、费用、处罚应当由积分权益人自行承担。若由于积分权益人未及时更新其信息，而导致本公司无法继续向其提供本服务或本服务的有关流程及操作发生任何错误，积分权益人不得以此为由撤销交易申请、拒绝支付交易款项和服务手续费或采取其它不当行为，并将独立承担因此产生的所有责任和后果。</p>\n" +
                    "<p>三、服务内容</p>\n" +
                    "<p>1、鸿积分财宝通过与外贸信托积分宝集合财产信托计划合作实现积分权益人积分权益的保值增值，积分权益人通过自主兑换操作享受鸿积分财宝增值服务即视为同意积分运营平台可以集中处理积分权益，并将积分权益作为财产权参与信托计划，享受专业投资管理带来的价值提升。</p>\n" +
                    "<p>2、积分权益人发出交易申请指令，享受鸿积分财宝增值服务，即视为积分权益人不可撤销地授权本公司及本公司合作的第三方机构代为进行鸿积分财宝增值服务项下有关资金的划转、结算。包括但不限于：</p>\n" +
                    "<p>（1）当积分权益人发出交易指令申请时，即视为积分权益人授权本公司及本公司合作的第三方机构将积分权益人所持积分对应的财产权益划转至外贸信托-积分宝集合财产信托计划项下信托专户。</p>\n" +
                    "<p>（2）当积分权益人申请赎回外贸信托-积分宝集合财产信托计划的份额时，即视为积分权益人授权本公司及本公司合作的第三方机构将相应的信托计划回款划转至积分权益人指定的账户。</p>\n" +
                    "<p>3、资金运用方式：信托资金投资于固定收益类投资工具，包括货币市场基金、国内依法发行、上市的国债、央行票据、金融债、企业债、公司债、债券回购、短期融资券、次级债、资产支持证券以及经法律法规或中国证监会允许投资的其他固定收益类金融工具，包括收益凭证、基金、资产管理计划、信托计划等。</p>\n" +
                    "<p>4、在投资范围、比例要求等方面较货币市场基金更为灵活，因此在满足流动性要求的前提下可以取得较同类货币基金更优的投资回报；投资策略以安全稳健为主，在追求收益的同时兼顾资金流动性要求；</p>\n" +
                    "<p>四、身份信息验证、识别与特别授权</p>\n" +
                    "<p>1、在积分权益人承诺所提供的身份信息合法、真实、准确、有效的前提下，本公司有权采取各种合法方式对积分权益人进行身份验证，并据此确定向积分权益人提供的服务范围，确定为积分权益人开放的服务种类、范围和进行业务操作的权限。</p>\n" +
                    "<p>2、身份认证要素是本公司识别积分权益人的依据，积分权益人必须妥善保管，不得将身份认证要素提供给任何第三方或交于任何第三方使用。使用上述身份要素所发出的一切指令均视为积分权益人本人所为，积分权益人应对此产生的后果负责。对非本公司原因造成的账户、密码等信息被冒用、盗用或非法使用，由此引起的一切风险、责任、损失、费用等应由积分权益人自行承担。</p>\n" +
                    "<p>3、积分权益人确知并同意，为向其提供鸿积分财宝积分增值服务，本公司有权将积分权益人的个人信息（包括但不限于：姓名、身份证号、手机号、邮箱、联系地址、本人银行账号等）提供给相关的金融机构。</p>\n" +
                    "<p>五、积分财宝积分增值产品交易申请</p>\n" +
                    "<p>1、积分权益人确知并同意，其网上提交的资金划转申请，以积分权益人的银行系统与本公司的系统记录为准；积分权益人的银行系统与本公司的系统记录有所差别时，以本公司系统记录为准。积分权益人提交的积分增值服务产品交易申请以相应的鸿积分财宝的系统记录为准。积分权益人在鸿积分财宝平台可以查询到积分权益人持有积分增值产品的净值、收益、交易明细等</p>\n" +
                    "<p>2、特别提示本公司及鸿积分财宝平台不对积分权益人委托本公司提供积分增值服务的收益作出保证。</p>\n" +
                    "<p>六、通知</p>\n" +
                    "<p>积分权益人使用鸿积分财宝积分增值服务的有关通知，本公司将以电子形式通知积分权益人，包括但不限于依据积分权益人向本公司提供的电子邮件地址向其发送电子邮件的方式、于鸿积分财宝平台网站或合作伙伴网站上发布公告、或向积分权益人发送手机短信和电话通知等方式。</p>\n" +
                    "<p>七、服务费用</p>\n" +
                    "<p>积分权益人确知并同意，本公司保留向积分权益人收取服务费的权利。一旦本公司确定收取或调整服务费收取方式及标准，将以在鸿积分财宝平台发布公告的方式通知积分权益人，而不再向积分权益人作个别通知。</p>\n" +
                    "<p>八、一般规定</p>\n" +
                    "<p>1、本协议与鸿积分财宝平台上其他条款和规则的名词解释、意思理解，可互相引用参照，如有不同理解，应以本协议为准。</p>\n" +
                    "<p>2、如果本协议的部分条款无效，不影响其他条款的有效性。</p>\n" +
                    "<p>3、本公司有权制定和单方随时修改包括但不限于上述规则的各项规则。所有规则均构成本协议的有效组成部分。新制定和修改的规则将在鸿积分财宝平台上进行公布，不再向积分权益人做逐一通知。</p>\n" +
                    "<p>4、因本协议引起的或与本协议有关的争议，均适用中华人民共和国法律。</p>\n" +
                    "<p>5、因本协议引起的或与本协议有关的争议，本公司可与积分权益人协商解决。协商不成的，任何一方均有权向上海市人民法院提起诉讼解决。</p>");
        }
        JFAgreementDialog agreementDialog = new JFAgreementDialog();
        agreementDialog.setArguments(args);
        agreementDialog.show(getSupportFragmentManager(),"agreement");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(EnchashmentActivity.class.getSimpleName());
    }
}
