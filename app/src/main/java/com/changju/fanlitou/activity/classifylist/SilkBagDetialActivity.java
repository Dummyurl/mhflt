package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.bean.homeclassify.ArticleDetial;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.ShareUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.tencent.smtt.sdk.WebView;
import com.umeng.socialize.UMShareAPI;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/6/19.
 */

public class SilkBagDetialActivity extends BaseActivity {

    private int id;
    private WebView webView;
    private TextView tv_article_title, tv_author;
    private ArticleDetial articleDetial;
    private ArticleDetial.ArticleBean bean;
    private TextView tv_read_count;
    private TextView tv_title;
    private String title;

    @Override
    public void initParams(Bundle params) {
        String id_str = params.getString(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        if (!TextUtils.isEmpty(id_str) && NormalUtils.isInteger(id_str)){
            id = Integer.valueOf(id_str);
        }else {
            id = params.getInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
        }

        title = params.getString(BidFixedActivity.BID_NAME);
    }

    @Override
    public int bindLayout() {
        return R.layout.silk_bag_detial;
    }

    //loading&error
    private View include;
    private View include_load_error;

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "理财锦囊文章页面");

        webView = (WebView) findViewById(R.id.web_view);
        tv_article_title = (TextView) findViewById(R.id.tv_article_title);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_read_count = (TextView) findViewById(R.id.tv_read_count);
        tv_title = (TextView) findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        findViewById(R.id.iv_back_my_account).setOnClickListener(this);
        findViewById(R.id.iv_share).setOnClickListener(this);

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);
    }

    @Override
    public void doBusiness(final Context mContext) {
        if (id < 1) {
            NormalUtils.showToast(mContext, "非法访问！\n错误代码0x01");
            finish();
        } else {
            OkGo.get(ApiUtils.getArticleDetial(id))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            articleDetial = ParseUtils.parseByGson(s, ArticleDetial.class);
                            bean = articleDetial.getArticle();
                            tv_article_title.setText(bean.getTitle());
                            tv_author.setText(bean.getSource() + " | " +
                                    bean.getPublish_time());
                            tv_read_count.setText("阅读数：" + bean.getRead_count());
                            initWebView(bean.getContent());

                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            NormalUtils.showToast(mContext, R.string.net_error);
                            include.setVisibility(View.GONE);
                            include_load_error.setVisibility(View.VISIBLE);
                        }
                    });
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
            case R.id.iv_back_my_account:
                finish();
                break;
            case R.id.iv_share:
                if (bean != null) {
                    ArticleDetial.ArticleBean.Share_info share_info = bean.getShare_info();
                    ShareUtils.startShare(this,share_info.getUrl(), share_info.getTitle(), share_info.getDesc(), share_info.getImg());
                }
                break;
        }
    }

    private void initWebView(String content) {
        StringBuilder sb = new StringBuilder();
        //拼接一段HTML代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>多彩投</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(content.replace("<img", "<img style='max-width:100%;height:auto;'"));
        sb.append("</body>");
        sb.append("</html>");
        //使用简单的loadData方法会导致乱码，可能是Android API的Bug
        //show.loadData(sb.toString(), "text/html", "utf-8");
        //加载、并显示HTML代码
        webView.loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
