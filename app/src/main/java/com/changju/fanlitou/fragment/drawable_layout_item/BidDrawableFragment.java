package com.changju.fanlitou.fragment.drawable_layout_item;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.classifylist.FixedActivity;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapGridLayoutManager;
import com.changju.fanlitou.bean.homeclassify.HomeFixed;
import com.changju.fanlitou.ui.CustomRecyclerView;
import com.changju.fanlitou.util.NormalUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengww on 2017/5/24.
 */

public class BidDrawableFragment extends LazyFragment {

    private LinearLayout layout_filter_root;
    private List<HomeFixed.FilterListBean> filters;

    private List<SimpleAdapter> adapters;

    public static BidDrawableFragment newInstance(List<HomeFixed.FilterListBean> filterListBean) {
        BidDrawableFragment fragment = new BidDrawableFragment();
        Bundle args = new Bundle();
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, false);
        fragment.setArguments(args);
        fragment.filters = filterListBean;
        return fragment;
    }

    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected void initView() {
        //不侵入状态栏 + 10dp 上下padding
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, NormalUtils.getStatusBarHeight(context));
            findViewById(R.id.tv_status_bar).setLayoutParams(params);
        }

        layout_filter_root = (LinearLayout) findViewById(R.id.layout_filter_root);

        findViewById(R.id.btn_ok_platform).setOnClickListener(this);
        findViewById(R.id.btn_clear_platform).setOnClickListener(this);

        adapters = new ArrayList<>();

    }

    private CustomRecyclerView initRecyclerView(final List<HomeFixed.FilterListBean.ContentListBean> filter_contents, boolean multiple_choice) {
        final SimpleAdapter mAdapter = new SimpleAdapter(null, multiple_choice);
        //超过9个，默认只显示9个
        final List<HomeFixed.FilterListBean.ContentListBean> list_cut;
        if (filter_contents != null && filter_contents.size() > 9) {
            LinearLayout footer = (LinearLayout) LayoutInflater.from(context).
                    inflate(R.layout.item_check_box_show_more, null, false);
            CheckBox isShowMore = (CheckBox) footer.findViewById(R.id.cb_is_show_more);
            isShowMore.setText("查看更多");
            mAdapter.setFooterView(footer);
            mAdapter.setFooterViewAsFlow(true);
            list_cut = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                list_cut.add(filter_contents.get(i));
            }
            isShowMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mAdapter.setNewData(filter_contents);
                        buttonView.setText("一键收起");
                    } else {
                        mAdapter.setNewData(list_cut);
                        buttonView.setText("查看更多");
                    }
                }
            });
        } else {
            list_cut = filter_contents;
        }

        CustomRecyclerView mRecyclerView = new CustomRecyclerView(context);
        LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView.setLayoutParams(paras);
        mRecyclerView.setLayoutManager(
                new WrapGridLayoutManager(context, 3));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter.bindToRecyclerView(mRecyclerView);

        mAdapter.setNewData(list_cut);

        adapters.add(mAdapter);

        return mRecyclerView;
    }


    @Override
    protected void doBusiness(final Context context) {
        //标的列表页面请求
        if (filters != null && filters.size() > 0) {
            for (HomeFixed.FilterListBean filter : filters) {
                if (filter != null) {
                    TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.text_filter, null);
                    textView.setText(filter.getTitle());
                    if (filter.isIs_title_red())
                        textView.setTextColor(Color.parseColor("#f94529"));

                    layout_filter_root.addView(textView);

                    List<HomeFixed.FilterListBean.ContentListBean> filter_contents = filter.getContent_list();
                    if (filter_contents != null && filter_contents.size() > 0) {
                        layout_filter_root.addView(
                                initRecyclerView(filter_contents, filter.isMultiple_choice()));
                    }

                }
            }
        }

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_bid_drawable;
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear_platform:
                for (int i = 0; i < adapters.size(); i++) {
                    SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
                    sparseBooleanArray.put(0,true);
                    adapters.get(i).setSelectArray(sparseBooleanArray);
                }

                selectedMap.clear();
                break;
            case R.id.btn_ok_platform:

                    FixedActivity activity = (FixedActivity) context;
                    DrawerLayout drawer_layout_platform = activity.getDrawableLayout();
                    drawer_layout_platform.closeDrawer(Gravity.END);
                    activity.initData(false);
                break;
        }
    }


    private Map<String, String> selectedMap = new HashMap<>();

    public Map<String, String> getSelectedMap() {
        return selectedMap;
    }

    class SimpleAdapter extends BaseQuickAdapter<HomeFixed.FilterListBean.ContentListBean, BaseViewHolder> {

        private SparseBooleanArray mSelectArray;

        private boolean enable = true;
        private boolean multiple_choice = false;

        void setSelectArray(SparseBooleanArray selectArray) {
            mSelectArray = selectArray;
            notifyDataSetChanged();
        }

        SparseBooleanArray getSelectArray() {
            return mSelectArray;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isEnable() {
            return enable;
        }

        public SimpleAdapter(List<HomeFixed.FilterListBean.ContentListBean> data, boolean multiple_choice) {
            super(R.layout.recycler_item_bid_drawable, data);
            mSelectArray = new SparseBooleanArray();
            mSelectArray.put(0, true);
            this.multiple_choice = multiple_choice;
        }

        @Override
        protected void convert(final BaseViewHolder helper,
                               final HomeFixed.FilterListBean.ContentListBean item) {
            final RadioButton radioButton = helper.getView(R.id.rb_bid_drawable);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (multiple_choice) {
                        if (mSelectArray.get(0) || helper.getAdapterPosition() == 0) {
                            mSelectArray.clear();
                        }
                        mSelectArray.put(helper.getAdapterPosition(),
                                !mSelectArray.get(helper.getAdapterPosition(), false));
                        boolean isAllFalse = true;

                        for (int i = 0; i < mSelectArray.size(); i++) {
                            if (mSelectArray.get(mSelectArray.keyAt(i))) {
                                isAllFalse = false;
                                break;
                            }
                        }

                        if (isAllFalse) {
                            mSelectArray.put(0, true);
                        }
                    } else {
                        mSelectArray.clear();
                        mSelectArray.put(helper.getAdapterPosition(), true);
                    }


                    notifyDataSetChanged();

                    List<HomeFixed.FilterListBean.ContentListBean> data = getData();

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("&").append(data.get(0).getKey()).append("=");

                    for (int i = 0; i < mSelectArray.size(); i++) {
                        if (mSelectArray.valueAt(i)){
                            stringBuilder.append(data.get(mSelectArray.keyAt(i)).getValue()).append(",");
                        }
                    }

                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);

                    selectedMap.put(SimpleAdapter.this.toString(), stringBuilder.toString());
                }
            });

            radioButton.setText(item.getName());

            radioButton.setChecked(mSelectArray.get(
                    helper.getAdapterPosition(), false));

            radioButton.setEnabled(enable);
        }
    }


}
