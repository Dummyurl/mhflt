package com.changju.fanlitou.fragment.platform;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.MainActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.adapter.PlatformSimpleAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.discover.Platform;
import com.changju.fanlitou.fragment.discover.PlatformFragment;
import com.changju.fanlitou.ui.EditTextWithSearchAndDel;
import com.changju.fanlitou.ui.FlowLayout;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.RecordSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengww on 2017/6/10.
 * 搜索页面
 */

public class PlatformSearchFragment extends LazyFragment {

//    public static final String RECENT_SEARCH = "recent_search.list";

    private static Platform platforms;

    private List<Platform.PlatformListBean> searchPlatforms;

    //显示平台信息
    private RecyclerView mRecyclerView;

    private EditTextWithSearchAndDel et_search;

    private View layout_search_record, item_recent_search_empty;

    //最近搜索-->清空
    private TextView tv_clear;
    private InputMethodManager imm;

    private FlowLayout recentSearch, hotSearch;
    private PlatformSimpleAdapter adapter;

    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public static PlatformSearchFragment newInstance(Platform platforms) {
        PlatformSearchFragment.platforms = platforms;
        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        PlatformSearchFragment fragment = new PlatformSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        //取消--> 回到平台页面
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_platform);
        et_search = (EditTextWithSearchAndDel) findViewById(R.id.et_search);

        layout_search_record = findViewById(R.id.layout_search_record);
        searchPlatforms = new ArrayList<>();

        item_recent_search_empty = findViewById(R.id.item_recent_search_empty);
        //清除最近搜索历史
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);

        //最近搜索
        recentSearch = (FlowLayout) findViewById(R.id.flow_recent_search);
        //热门搜索
        hotSearch = (FlowLayout) findViewById(R.id.flow_hot_search);


    }

    @Override
    protected void doBusiness(Context context) {
        helper = new RecordSQLiteOpenHelper(context,"platform.db");
        queryData("");
        if (recentSearch.getChildCount() > 0) {
            tv_clear.setVisibility(View.VISIBLE);
            item_recent_search_empty.setVisibility(View.GONE);
        } else {
            tv_clear.setVisibility(View.GONE);
            item_recent_search_empty.setVisibility(View.VISIBLE);
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
                doSearch(editable.toString());
            }
        });

        //热门搜索
        if (platforms != null) {
            List<Platform.HotSearchBean> hot_search = platforms.getHot_search();
            for (int i = 0; i < hot_search.size(); i++) {
                hotSearch.addView(getSearchTextView(
                        context, hot_search.get(i).getName()));
            }
        }
    }

    @NonNull
    private TextView getSearchTextView(Context context, String s) {
        final TextView textView = new TextView(context);
        textView.setText(s);
        textView.setTextSize(12);
        textView.setTextColor(getResources().getColor(R.color.colorBidName));
        textView.setPadding(NormalUtils.dp2px(context, 8),
                NormalUtils.dp2px(context, 7),
                NormalUtils.dp2px(context, 8),
                NormalUtils.dp2px(context, 7));

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(NormalUtils.dp2px(context, 10),
                0, 0, NormalUtils.dp2px(context, 10));
        textView.setLayoutParams(params);

        textView.setBackgroundResource(R.drawable.shape_tag_search_word_solid);

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
     * @param s
     */
    private void doSearch(String s) {
        if (TextUtils.isEmpty(s)) {
            if (layout_search_record.getVisibility() != View.VISIBLE)
                layout_search_record.setVisibility(View.VISIBLE);
            if (mRecyclerView.getVisibility() == View.VISIBLE)
                mRecyclerView.setVisibility(View.GONE);
        } else {
            //显示清空
            if (tv_clear.getVisibility() != View.VISIBLE)
                tv_clear.setVisibility(View.VISIBLE);

            //隐藏空搜索历史画面
            if (item_recent_search_empty.getVisibility() == View.VISIBLE)
                item_recent_search_empty.setVisibility(View.GONE);

            if (layout_search_record.getVisibility() == View.VISIBLE)
                layout_search_record.setVisibility(View.GONE);

            if (mRecyclerView.getVisibility() != View.VISIBLE)
                mRecyclerView.setVisibility(View.VISIBLE);
            //开始搜索
            if (platforms == null || platforms.getPlatform_list() == null) {
                bindRecyclerData(null);
                addDataAndUpdate(s);
            } else {
                addDataAndUpdate(s);
                //清空搜索结果
                searchPlatforms.clear();
                List<Platform.PlatformListBean> list = platforms.getPlatform_list();
                //遍历查询
                for (int i = 0; i < list.size(); i++) {
                    Platform.PlatformListBean bean = list.get(i);
                    if (bean.getName().contains(s) ||
                            bean.getName_pinyin_full().contains(s) ||
                            bean.getName_pinyin_abr().contains(s)) {
                        searchPlatforms.add(list.get(i));
                    }
                }
                //遍历完成，绑定数据到RecyclerView
                bindRecyclerData(searchPlatforms);
            }


        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                //隐藏软键盘
                imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
                PlatformFragment platformFragment = (PlatformFragment) getParentFragment();
//                platformFragment.hideSearchFragment();
                et_search.setText("");
                break;
            case R.id.tv_clear:
                deleteData();
                tv_clear.setVisibility(View.GONE);
                item_recent_search_empty.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 搜索结果展示
     *
     * @param listBean
     */
    private void bindRecyclerData(List<Platform.PlatformListBean> listBean) {
        if (adapter == null) {
            mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
            adapter = new PlatformSimpleAdapter(listBean);
            //这里设置点击发现-平台列表跳转到平台详情页面
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    Bundle bundle = new Bundle();
                    List<Platform.PlatformListBean> platformListBeen = adapter.getData();

                    //平台名字 "抱财网"等    平台类型"固收"等  平台id 数字
                    String name = platformListBeen.get(position).getName();
                    int platform_id = platformListBeen.get(position).getPlatform_id();

                    bundle.putInt(PlatformDetailActivity.PLATFORM_ID, platform_id);
                    bundle.putString(PlatformDetailActivity.PLATFORM_NAME, name);
                    MainActivity activity = (MainActivity) getActivity();
                    activity.startActivity(PlatformDetailActivity.class, bundle);
                }
            });
            adapter.bindToRecyclerView(mRecyclerView);
            adapter.setEmptyView(R.layout.view_search_empty);
            mRecyclerView.setAdapter(adapter);
        } else {
            adapter.setNewData(listBean);
        }
    }

    public void showSoftInput() {
        //显示软键盘
        et_search.requestFocus();
        imm.showSoftInput(et_search, 0);
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
                recentSearch.addView(getSearchTextView(getActivity(), record));
            } while (cursor.moveToNext());
        }

        //关游标
        cursor.close();

    }

    /**
     * 检查数据库中是否已经有该条记录并更新ID
     */
    private boolean addDataAndUpdate(String tempName) {
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
        recentSearch.addView(getSearchTextView(getActivity(), tempName), 0);
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

}
