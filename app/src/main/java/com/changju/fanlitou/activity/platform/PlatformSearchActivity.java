package com.changju.fanlitou.activity.platform;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.base.BaseActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.discover.Platform;
import com.changju.fanlitou.bean.discover.PlatformSearchBean;
import com.changju.fanlitou.ui.EditTextWithDel;
import com.changju.fanlitou.ui.FlowLayout;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.RecordSQLiteOpenHelper;
import com.growingio.android.sdk.collection.GrowingIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengww on 2017/8/17.
 * 平台搜索界面，xml同基金搜索
 */

public class PlatformSearchActivity extends BaseActivity {

    private Platform platforms;

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

    private int page = 1;
    private String word;
    private TextView empty_view;
    private List<PlatformSearchBean> searchPlatforms;
    private View footerLine;

    @Override
    public void initParams(Bundle params) {
        platforms = (Platform) params.getSerializable(LazyFragment.INTENT_BOOLEAN_LAZYLOAD);
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

        GrowingIO.getInstance().setPageName(this, "平台搜索");

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        findViewById(R.id.tv_cancel).setOnClickListener(this);

        footerLine = new View(this);
        ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                1);
        footerLine.setLayoutParams(params1);
        footerLine.setBackgroundColor(getResources().getColor(R.color.colorLine));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        et_search = (EditTextWithDel) findViewById(R.id.et_search);
        et_search.setOnFocusChangeListener(null);

        //采集平台搜索数据
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

        helper = new RecordSQLiteOpenHelper(this, "platform.db");

        empty_view = (TextView) LayoutInflater.from(this).inflate(R.layout.view_fund_search_empty, null);

        searchPlatforms = new ArrayList<>();

        et_search.setHint("搜平台");

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
                //如果为空 直接显示搜索历史 不显示删除图标
                if (TextUtils.isEmpty(editable)) {
                    //showHistory();
                    et_search.setClearIconVisible(false);
                } else {//否则显示删除图标
                    et_search.setClearIconVisible(et_search.getText().length() > 0);
                }

                doLocalSearch(word);

            }
        });

        //热门搜索
        if (platforms != null) {
            List<Platform.HotSearchBean> hot_search = platforms.getHot_search();
            for (int i = 0; i < hot_search.size(); i++) {
                hotSearch.addView(getSearchTextView(
                        this, hot_search.get(i).getName()));
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
        et_search.postDelayed(new Runnable() {
            @Override
            public void run() {
                et_search.requestFocus();
                imm.showSoftInput(et_search, InputMethodManager.SHOW_FORCED);
            }
        }, 100);

    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
//        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName.replace("'", "") + "')");
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

        textView.setPadding(0, NormalUtils.dp2px(context, 5),
                NormalUtils.dp2px(context, 40), NormalUtils.dp2px(context, 5));

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

    class SimpleAdapter extends BaseQuickAdapter<PlatformSearchBean, BaseViewHolder> {

        public SimpleAdapter(@Nullable List<PlatformSearchBean> data) {
            super(R.layout.recycler_platform_search, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PlatformSearchBean item) {
            TextView tv_platform_name = helper.getView(R.id.tv_platform_name);
            tv_platform_name.setText(Html.fromHtml(item.getPlatform_name_show()));
            ImageView iv_platform_logo = helper.getView(R.id.iv_platform_logo);
            Glide.with(getApplicationContext()).load(item.getPlatform_logo_url())
                    .into(iv_platform_logo);
        }
    }

    /**
     * 搜索结果展示
     *
     * @param listBean
     */
    private synchronized void bindRecyclerData(List<PlatformSearchBean> listBean) {
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
                    List<PlatformSearchBean> platformSearchBean = adapter.getData();
                    PlatformSearchBean bean = platformSearchBean.get(position);


                    bundle.putInt(PlatformDetailActivity.PLATFORM_ID, bean.getPlatform_id());
                    bundle.putString(PlatformDetailActivity.PLATFORM_NAME, bean.getPlatform_name());
                    startActivity(PlatformDetailActivity.class, bundle);
                }
            });
            empty_view.setText("暂无平台，换个搜索词试试");
            adapter.setEmptyView(empty_view);
            adapter.setFooterView(footerLine);

        } else {
            adapter.setNewData(listBean);

        }

    }

    /**
     * 执行本地搜索
     *
     * @param s
     */
    private void doLocalSearch(String s) {

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

            addDataAndUpdate(word);

            if (layout_search_record.getVisibility() == View.VISIBLE) {
                layout_search_record.setVisibility(View.GONE);
            }

            if (mRecyclerView.getVisibility() != View.VISIBLE)
                mRecyclerView.setVisibility(View.VISIBLE);

            List<Platform.PlatformListBean> list = platforms.getPlatform_list();

            searchPlatforms.clear();

            //遍历查询
            for (int i = 0; i < list.size(); i++) {
                Platform.PlatformListBean bean = list.get(i);
                if (bean.getName().contains(s) ||
                        bean.getName_pinyin_full().contains(s) ||
                        bean.getName_pinyin_abr().contains(s)) {
                    PlatformSearchBean searchBean = new PlatformSearchBean(bean.getName(),
                            bean.getName().replace(s, "<font color='#f42c20'>" + s + "</font>"),
                            bean.getPlatform_id(), bean.getLogo_app());
                    searchPlatforms.add(searchBean);
                }

            }
            bindRecyclerData(searchPlatforms);

        }
    }
}
