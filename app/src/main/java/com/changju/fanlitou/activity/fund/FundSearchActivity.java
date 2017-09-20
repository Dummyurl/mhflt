package com.changju.fanlitou.activity.fund;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.fund.FundList;
import com.changju.fanlitou.bean.fund.FundSearchBean;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.FlowLayout;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.RecordSQLiteOpenHelper;
import com.growingio.android.sdk.collection.GrowingIO;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/7/26.
 * 基金搜索页面
 */

public class FundSearchActivity extends BaseActivity {

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_SEARCH){
                //搜索请求
                page = 1;
                doSearch(word);
            }

        }
    };
    private static final int MSG_SEARCH = 1;

    //最近搜索-->清空
    private TextView tv_clear;
    private InputMethodManager imm;

    private FlowLayout recentSearch, hotSearch;
    private SimpleAdapter adapter;

    private LinearLayout layout_recent_search;

    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private RecyclerView mRecyclerView;

    private EditTextWithDel et_search;

    private View layout_search_record;

    private TextView tv_recent_search;

    private List<String> hot_search;

    public static final String HOT_SEARCH = "HOT_SEARCH";

    private int page = 1;
    private String word;
    private TextView empty_loading;

    @Override
    public void initParams(Bundle params) {
        hot_search = params.getStringArrayList(HOT_SEARCH);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fund_search;
    }

    @Override
    public void initView(View view) {
        //不侵入状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            NormalUtils.getStatusBarHeight(this));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        GrowingIO.getInstance().setPageName(this, "基金-基金搜索");

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        findViewById(R.id.tv_cancel).setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        et_search = (EditTextWithDel) findViewById(R.id.et_search);
        et_search.setOnFocusChangeListener(null);

        //采集基金搜索数据
        GrowingIO.getInstance().trackEditText(et_search);

        layout_search_record = findViewById(R.id.layout_search_record);

        //清除最近搜索历史
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);

        //最近搜索
        recentSearch = (FlowLayout) findViewById(R.id.flow_recent_search);
        tv_recent_search = (TextView) findViewById(R.id.tv_recent_search);
        layout_recent_search = (LinearLayout) findViewById(R.id.layout_recent_search);
        //热门搜索
        hotSearch = (FlowLayout) findViewById(R.id.flow_hot_search);

        helper = new RecordSQLiteOpenHelper(this,"fund.db");

        if (adapter == null) {
            mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
            adapter = new SimpleAdapter(null);

            adapter.bindToRecyclerView(mRecyclerView);
            //这里设置点击发现-平台列表跳转到平台详情页面
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

                    Bundle bundle = new Bundle();
                    List<FundSearchBean> FundSearchBean = adapter.getData();

                    int fund_id = FundSearchBean.get(position).getFund_id();

                    bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, fund_id);
                    startActivity(FundDetailActivity.class, bundle);
                }
            });

            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {

                @Override
                public void onLoadMoreRequested() {
                    doSearch(word);
                }
            }, mRecyclerView);

        }

        empty_loading = new TextView(this);
        empty_loading.setText("正在加载中...");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,NormalUtils.dp2px(this,20),0,0);
        empty_loading.setGravity(Gravity.CENTER);
        empty_loading.setLayoutParams(params);
        empty_loading.setTextSize(14);
        empty_loading.setTextColor(getResources().getColor(R.color.colorBidName));

        showSoftInput();

    }

    @Override
    public void doBusiness(Context mContext) {
        queryData("");
        if (recentSearch.getChildCount() > 0) {
            tv_recent_search.setVisibility(View.VISIBLE);
            layout_recent_search.setVisibility(View.VISIBLE);
        } else {
            layout_recent_search.setVisibility(View.GONE);
            tv_recent_search.setVisibility(View.GONE);
        }


        //文字改变时搜索
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                word = editable.toString();

                //文字变动 ， 有未发出的搜索请求，应取消
                if(mHandler.hasMessages(MSG_SEARCH)){
                    mHandler.removeMessages(MSG_SEARCH);
                }
                //如果为空 直接显示搜索历史
                if(TextUtils.isEmpty(editable)){
                    //showHistory();
                    et_search.setClearIconVisible(false);
                    mHandler.sendEmptyMessage(MSG_SEARCH);
                }else {//否则延迟800ms开始搜索
                    mHandler.sendEmptyMessageDelayed(MSG_SEARCH,600); //自动搜索功能 删除
                    et_search.setClearIconVisible(et_search.getText().length() > 0);
                }

            }
        });

        //热门搜索
        if (hot_search != null && hot_search.size() > 0) {
            for (int i = 0; i < hot_search.size(); i++) {
                hotSearch.addView(getSearchTextView(
                        this, hot_search.get(i)));
            }
        }


    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                //隐藏软键盘
                imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                finish();
                break;
            case R.id.tv_clear:
                deleteData();
                layout_recent_search.setVisibility(View.GONE);
                tv_recent_search.setVisibility(View.GONE);
                break;
        }
    }

    public void showSoftInput() {
        //显示软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                et_search.requestFocus();
                imm.showSoftInput(et_search, InputMethodManager.SHOW_FORCED);
            }

        }, 200);

    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
//        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);

        recentSearch.removeAllViews();
        //判断游标是否为空

        //获得记录
        if (cursor.moveToFirst()) {
            do {
                String record = cursor.getString(1);

                if (layout_recent_search.getVisibility() != View.VISIBLE) {
                    tv_recent_search.setVisibility(View.VISIBLE);
                    layout_recent_search.setVisibility(View.VISIBLE);
                }

                if (mRecyclerView.getVisibility() != View.GONE)
                    mRecyclerView.setVisibility(View.GONE);

                recentSearch.addView(getSearchTextView(this, record));
            } while (cursor.moveToNext());

            if (recentSearch.getChildCount() < 1) {
                tv_recent_search.setVisibility(View.GONE);
                layout_recent_search.setVisibility(View.GONE);
            }
        }

        //关游标
        cursor.close();

    }

    /**
     * 检查数据库中是否已经有该条记录并更新ID
     */
    private boolean addDataAndUpdate(String tempName) {
        if (page > 1)
            return true;

        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select id as _id,name from records where name = ?", new String[]{tempName});
        //判断是否有下一个
        boolean hasData = cursor.moveToNext();
        cursor.close();
        if (hasData) {
            deleteWithName(tempName);

            for (int i = 0; i < recentSearch.getChildCount(); i++) {
                TextView textView = (TextView) recentSearch.getChildAt(i);
                if (tempName.equals(textView.getText().toString())) {
                    recentSearch.removeViewAt(i);
                    break;
                }
            }
        } else {
            //4个可以加，5个不行
            if (recentSearch.getChildCount() > 4) {
                TextView textView = (TextView) recentSearch.getChildAt(4);
                deleteWithName(textView.getText().toString());
                recentSearch.removeViewAt(4);
            }
        }

        insertData(tempName);

        if (layout_recent_search.getVisibility() != View.VISIBLE) {
            tv_recent_search.setVisibility(View.VISIBLE);
            layout_recent_search.setVisibility(View.VISIBLE);
        }

        recentSearch.addView(getSearchTextView(this, tempName), 0);
        db.close();

        return hasData;
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        queryData("");
        db.close();
    }

    private void deleteWithName(String record) {
        String sql = "delete from records where name = '" + record + "'";
        db.execSQL(sql);
    }

    @NonNull
    private TextView getSearchTextView(Context context, String s) {
        final TextView textView = new TextView(context);
        textView.setText(s);
        textView.setTextSize(14);
        textView.setTextColor(Color.parseColor("#767676"));

        textView.setPadding(0,NormalUtils.dp2px(context, 5),
                NormalUtils.dp2px(context, 40),NormalUtils.dp2px(context, 5));

        //点击事件
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textView.getText().toString();
                et_search.setText(text);
                et_search.setSelection(text.length());
            }
        });

        return textView;
    }

    /**
     * 执行搜索
     *
     * @param word
     */
    private void doSearch(final String word) {

        if (TextUtils.isEmpty(word)) {

            if (layout_search_record.getVisibility() != View.VISIBLE) {
                layout_search_record.setVisibility(View.VISIBLE);
            }

            adapter.setNewData(null);

            if (mRecyclerView.getVisibility() == View.VISIBLE) {
                adapter.loadMoreComplete();
                mRecyclerView.setVisibility(View.GONE);
            }


        } else {

            if (layout_search_record.getVisibility() == View.VISIBLE) {
                layout_search_record.setVisibility(View.GONE);
            }

            if (mRecyclerView.getVisibility() != View.VISIBLE)
                mRecyclerView.setVisibility(View.VISIBLE);


            adapter.setEmptyView(empty_loading);


            //开始搜索
            OkGo.get(ApiUtils.getFundList("all", "day_price_increase", page, 20, word)).execute(new StringCallback() {

                @Override
                public void onSuccess(String s, Call call, Response response) {
                    FundList fundListbean = ParseUtils.parseByGson(s, FundList.class);

                    List<FundList.ProductsBean> products =
                            fundListbean.getProducts();

                    if (products == null || products.size() < 1) {

                        bindRecyclerData(null);
                        addDataAndUpdate(word);
                        loadMoreEnd(adapter,mRecyclerView);

                    } else {
                        addDataAndUpdate(word);
                        List<FundSearchBean> searchLists = new ArrayList<>();

                        //遍历查询
                        for (int i = 0; i < products.size(); i++) {
                            FundList.ProductsBean bean = products.get(i);
                            FundSearchBean searchBean = new FundSearchBean();
                            searchBean.setFund_id(bean.getFund_id());
                            if (!TextUtils.isEmpty(bean.getFund_name()))
                                searchBean.setName(bean.getFund_name().replace(word,
                                    "<font color='#f42c20'>" + word + "</font>"));
                            else {
                                searchBean.setName("");
                            }

                            if (!TextUtils.isEmpty(bean.getFund_code()))
                                searchBean.setNum(bean.getFund_code().replace(word,
                                    "<font color='#f42c20'>" + word + "</font>"));

                            searchLists.add(searchBean);
                        }
                        //遍历完成，绑定数据到RecyclerView
                        bindRecyclerData(searchLists);

                        adapter.loadMoreComplete();

                    }
                    page++;

                    adapter.setEmptyView(R.layout.view_fund_search_empty);

                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    super.onError(call, response, e);
                    addDataAndUpdate(word);

                    bindRecyclerData(null);

                    adapter.loadMoreFail();

                    adapter.setEmptyView(R.layout.view_fund_search_empty);

                }
            });


        }
    }

    class SimpleAdapter extends BaseQuickAdapter<FundSearchBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<FundSearchBean> data) {
            super(R.layout.recycler_fund_search, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, FundSearchBean item) {
            TextView tv_name = helper.getView(R.id.tv_name);
            tv_name.setText(Html.fromHtml(item.getName()));
            TextView tv_num = helper.getView(R.id.tv_num);
            tv_num.setText(Html.fromHtml(item.getNum()));
        }
    }

    /**
     * 搜索结果展示
     *
     * @param listBean
     */
    private synchronized void bindRecyclerData(List<FundSearchBean> listBean) {
        if (adapter == null) {
            mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this));
            adapter = new SimpleAdapter(listBean);

            adapter.bindToRecyclerView(mRecyclerView);
            //这里设置点击发现-平台列表跳转到平台详情页面
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

                    Bundle bundle = new Bundle();
                    List<FundSearchBean> FundSearchBean = adapter.getData();

                    int fund_id = FundSearchBean.get(position).getFund_id();

                    bundle.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, fund_id);
                    startActivity(FundDetailActivity.class, bundle);
                }
            });
            adapter.setEmptyView(R.layout.view_fund_search_empty);

            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {

                @Override
                public void onLoadMoreRequested() {
                    doSearch(word);
                }
            }, mRecyclerView);

        } else {
            if (page == 1)
                adapter.setNewData(listBean);
            else {
                if(listBean == null || listBean.size() < 1)
                    return;

                adapter.addData(listBean);
            }
        }

    }

    private void loadMoreEnd(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
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
                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
                    if (manager == null) return;
                    if (manager instanceof LinearLayoutManager) {
                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                        mRecyclerView.postDelayed(new Runnable() {
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
    }
}
