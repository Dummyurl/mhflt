package com.changju.fanlitou.activity.classifylist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.homeclassify.Banner;
import com.changju.fanlitou.bean.homeclassify.HomeSilkBag;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SilkBagActivity extends BaseActivity {

    public static final String ARTICLE = "article";
    private LinearLayout linearLayoutHead;
    private TextView head;
    private RecyclerView silkbag_list;
    private SmartRefreshLayout refreshLayout;
//    private SwipeRefreshLayout swipe_silkbag;

    //loading&error
    private View include;
    private View include_load_error;

    private SimpleAdapter adapter;

    //是否刷新
    private boolean isRefresh;

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_classify_list_silkbag;
    }

    @Override
    public void initView(View view) {

        GrowingIO.getInstance().setPageName(this, "理财锦囊二级页");

        findViewById(R.id.iv_back_silkbag).setOnClickListener(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        linearLayoutHead = (LinearLayout) inflater.inflate(R.layout.classify_head_silkbag, null);
        head = (TextView) linearLayoutHead.findViewById(R.id.tv_classify_silkbag_banner);
        silkbag_list = (RecyclerView) findViewById(R.id.silkbag_list);
        silkbag_list.setLayoutManager(new WrapContentLinearLayoutManager(this));

        include = findViewById(R.id.include_loading);
        include_load_error = findViewById(R.id.include_load_error);
        include_load_error.setVisibility(View.GONE);
        include_load_error.setOnClickListener(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.silkbag_refresh_layout);
        refreshLayout.setRefreshHeader(new MaterialHeader(this).setColorSchemeColors(0xfff95353));
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isRefresh = true;
                        page = 1;
                        initHead();
                        initArticle(SilkBagActivity.this);
                    }
                }, 1000);
            }
        });
    }

    private int page = 1;

    @Override
    public void doBusiness(final Context mContext) {
        isRefresh = false;
        initHead();
        initArticle(mContext);

    }

    private void initHead() {
        OkGo.get(ApiUtils.getClassifyBanner(this, ARTICLE)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Banner banner = ParseUtils.parseByGson(s, Banner.class);
                head.setText(banner.getTitle());
                Glide.with(getApplicationContext()).load(banner.getUrl())
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource,
                                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                                head.setBackground(resource);
                            }
                        });
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }
        });
    }

    public void initArticle(final Context mContext) {
        OkGo.get(ApiUtils.getArticleList(page, 10))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        HomeSilkBag bag = ParseUtils.parseByGson(s, HomeSilkBag.class);
                        List<HomeSilkBag.ArticleListBean> articles = bag.getArticle_list();
                        if (adapter == null) {
                            adapter = new SimpleAdapter(articles);
                            adapter.bindToRecyclerView(silkbag_list);
                            adapter.setEmptyView(R.layout.view_empty);
                            adapter.removeAllHeaderView();
                            adapter.setHeaderView(linearLayoutHead);
                            adapter.disableLoadMoreIfNotFullPage();
                            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                @Override
                                public void onLoadMoreRequested() {
                                    isRefresh = false;
                                    initArticle(mContext);
                                }
                            }, silkbag_list);
                        } else {
                            if (page == 1) {
                                adapter.setNewData(articles);
                            } else {
                                if (articles == null || articles.size() < 1) {
                                    adapter.loadMoreEnd();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(500);
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        adapter.setEnableLoadMore(false);
                                                    }
                                                });
                                                RecyclerView.LayoutManager manager = silkbag_list.getLayoutManager();
                                                if (manager == null) return;
                                                if (manager instanceof LinearLayoutManager) {
                                                    final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                                                    silkbag_list.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != adapter.getItemCount()) {
                                                                adapter.setEnableLoadMore(true);
                                                            }
                                                        }
                                                    }, 50);
                                                }

                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }).start();
                                } else {
                                    adapter.addData(articles);
                                    adapter.loadMoreComplete();
                                }
                            }
                        }

                        page++;
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.GONE);

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        NormalUtils.showToast(mContext, R.string.net_error);
                        include.setVisibility(View.GONE);
                        include_load_error.setVisibility(View.VISIBLE);

                        if (page > 1)
                            adapter.loadMoreFail();

                        if (isRefresh) {
                            refreshLayout.finishRefresh();
                        }
                    }
                });
    }

    class SimpleAdapter extends BaseQuickAdapter<HomeSilkBag.ArticleListBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<HomeSilkBag.ArticleListBean> data) {
            super(R.layout.classify_listitem_silkbag, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeSilkBag.ArticleListBean item) {
            ImageView img = helper.getView(R.id.product_img_url);
            Glide.with(SilkBagActivity.this.getApplicationContext()).load(item.getForum_photo())
                    .into(img);
            helper.setText(R.id.bid_name, item.getTitle())
                    .setText(R.id.sub_name, item.getAbstractX())
                    .setText(R.id.author_name, item.getSource() + "  " + item.getPublish_time())
                    .setText(R.id.bonus_interest, "阅读数：" + item.getRead_count());
            View view_root = helper.getView(R.id.view_root);
            final int artical_id = item.getId();
            view_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, artical_id);
                    args.putString(BidFixedActivity.BID_NAME, item.getTitle());
                    SilkBagActivity.this.startActivity(SilkBagDetialActivity.class, args);
                }
            });
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_silkbag:
                finish();
                break;
            case R.id.include_load_error:
                include.setVisibility(View.VISIBLE);
                include_load_error.setVisibility(View.GONE);
                doBusiness(this);
                break;
        }

    }
}
