package com.changju.fanlitou.fragment.platform;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.bid.BidAbroadActivity;
import com.changju.fanlitou.activity.bid.BidCrowdFundingActivity;
import com.changju.fanlitou.activity.bid.BidCurrentActivity;
import com.changju.fanlitou.activity.bid.BidFixedActivity;
import com.changju.fanlitou.activity.bid.BidGoldActivity;
import com.changju.fanlitou.activity.bid.BidInsuranceActivity;
import com.changju.fanlitou.activity.bid.BidVcActivity;
import com.changju.fanlitou.activity.platform.PlatformDetailActivity;
import com.changju.fanlitou.adapter.BannerAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidAbroadAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidCrowdfundingAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidCurrentAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidFixedAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidFixedAdapter2;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidGoldAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidInsuranceAdapter;
import com.changju.fanlitou.adapter.platformbid.DiscoverPlatformBidWuchouAdapter;
import com.changju.fanlitou.base.LazyFragment;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidAbroad;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidCrowdfunding;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidCurrent;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidFixed;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidGold;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidInsurance;
import com.changju.fanlitou.bean.discover.platformbid.DiscoverPlatformBidWuchou;
import com.changju.fanlitou.bean.discover.platformbid.IDiscoverPlatformBid;

import java.util.ArrayList;
import java.util.List;

public class ListFragment implements BaseQuickAdapter.OnItemClickListener {

    private List<View> views;
    private RecyclerView mRecyclerView;
    private ViewPager mViewPager;
    //    private String title;
    //平台列表adapter
    private DiscoverPlatformBidFixedAdapter mFixedAdapter;
    private DiscoverPlatformBidInsuranceAdapter mInsuranceAdapter;
    private DiscoverPlatformBidAbroadAdapter mAbroadAdapter;
    private DiscoverPlatformBidCrowdfundingAdapter mCrowdfundingAdapter;
    private DiscoverPlatformBidWuchouAdapter mWuchouAdapter;

    //黄金的平台
    private DiscoverPlatformBidGoldAdapter mGoldAdapter;

    //活期的平台
    //活期平台(星火钱包)里面活期产品的adapter
    private DiscoverPlatformBidCurrentAdapter mCurrentAdapter;
    //活期平台(星火钱包)里面固收产品的adapter
    private DiscoverPlatformBidFixedAdapter2 mFixedAdapter2;

    private IDiscoverPlatformBid iDiscoverPlatformBid;

    private List<DiscoverPlatformBidFixed.PlatformBidsBean> platformBidsBeanFixedList;
    private List<DiscoverPlatformBidInsurance.PlatformBidsBean> platformBidsBeanInsuranceList;
    private List<DiscoverPlatformBidAbroad.PlatformBidsBean> platformBidsBeanAbroadList;
    private List<DiscoverPlatformBidCrowdfunding.PlatformBidsBean> platformBidsBeanCrowdfundingList;
    private List<DiscoverPlatformBidWuchou.PlatformBidsBean> platformBidsBeanWuchouList;
    //黄金的平台
    private List<DiscoverPlatformBidGold.PlatformBidsBean> platformBidsBeanGoldList;
    //活期的平台
    private List<DiscoverPlatformBidCurrent.CurrentBidsBean> platformBidsBeanCurrentList;
    private List<DiscoverPlatformBidCurrent.FixedBidsBean> platformBidsBeanFixedList2;

    //热门活动里面的活动列表adapter
//    private HotActivityAdapter hotActivityAdapter;
    //活期里面的第二个列表-活期列表
    private RecyclerView recyclerView_top;

    private FragmentActivity activity;

    public ListFragment() {
    }

    public static ListFragment newInstance(FragmentActivity activity, IDiscoverPlatformBid iDiscoverPlatformBid, List<View> views) {
        ListFragment fragment = new ListFragment();

//        fragment.title = title;
//        fragment.banner = banner;
        fragment.views = views;
        fragment.iDiscoverPlatformBid = iDiscoverPlatformBid;
        fragment.activity = activity;
        fragment.initView();
        return fragment;
    }

    protected void initView() {
        mViewPager = (ViewPager) activity.findViewById(R.id.mViewPager);
        mRecyclerView = new RecyclerView(activity);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(layoutManager);
        addData(iDiscoverPlatformBid, 1);
        List<View> views = new ArrayList<>();
        views.add(mRecyclerView);
        mViewPager.setAdapter(new BannerAdapter(views));
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        PlatformDetailActivity platformDetailActivity = (PlatformDetailActivity) activity;
        //Fixed
        if (adapter instanceof DiscoverPlatformBidFixedAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mFixedAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mFixedAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidFixedActivity.class, args);
        }
        //Insurance
        if (adapter instanceof DiscoverPlatformBidInsuranceAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mInsuranceAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mInsuranceAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidInsuranceActivity.class, args);
        }
        //Abroad
        if (adapter instanceof DiscoverPlatformBidAbroadAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mAbroadAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mAbroadAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidAbroadActivity.class, args);
        }
        //Crowdfunding
        if (adapter instanceof DiscoverPlatformBidCrowdfundingAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mCrowdfundingAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mCrowdfundingAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidCrowdFundingActivity.class, args);
        }
        //Wuchou
        if (adapter instanceof DiscoverPlatformBidWuchouAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mWuchouAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mWuchouAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidVcActivity.class, args);
        }
        //Gold
        if (adapter instanceof DiscoverPlatformBidGoldAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mGoldAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mGoldAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidGoldActivity.class, args);
        }
        //Current
        if (adapter instanceof DiscoverPlatformBidCurrentAdapter) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mCurrentAdapter.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mCurrentAdapter.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidCurrentActivity.class, args);
        }
        if (adapter instanceof DiscoverPlatformBidFixedAdapter2) {
            Bundle args = new Bundle();
            args.putInt(LazyFragment.INTENT_BOOLEAN_LAZYLOAD,
                    mFixedAdapter2.getItem(position).getBid_id());
            args.putString(BidFixedActivity.BID_NAME,
                    mFixedAdapter2.getItem(position).getBid_name());
            platformDetailActivity.startActivity(BidFixedActivity.class, args);
        }


    }

    private void setHearderAndPullToRefresh(BaseQuickAdapter headerAdapter, BaseQuickAdapter footerAdapter) {
//        if (headerAdapter != null && headerAdapter.getItemCount() > 0){
//            this.headerAdapter = headerAdapter;
//        }else if (footerAdapter != null){
//            recyclerView_top.setVisibility(View.GONE);
//            this.headerAdapter = footerAdapter;
//        }
        this.headerAdapter = headerAdapter;
        onPageChange(0);
        final PlatformDetailActivity activity = (PlatformDetailActivity) this.activity;

        if (footerAdapter != null) {
            footerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    activity.fullDate();
                }
            }, mRecyclerView);
        }
    }

    private BaseQuickAdapter headerAdapter;

    private int index = -1;

    public void onPageChange(int index) {
        if (this.index == index)
            return;

        if (headerAdapter != null && views != null && views.size() > index) {
            this.index = index;
            headerAdapter.setHeaderView(views.get(index));
            headerAdapter.setHeaderAndEmpty(true);
            LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            layoutManager.scrollToPosition(0);
        }


    }

    private void loadMoreEnd(final BaseQuickAdapter adapter, final RecyclerView mRecyclerView) {
        if (adapter == null)
            return;

        adapter.loadMoreEnd();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setEnableLoadMore(false);
                        }
                    });
//                    RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
//                    if (manager == null) return;
//                    if (manager instanceof LinearLayoutManager) {
//                        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
//                        mRecyclerView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != adapter.getItemCount()) {
//                                    adapter.setEnableLoadMore(true);
//                                }
//                            }
//                        }, 50);
//                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void addData(IDiscoverPlatformBid iDiscoverPlatformBid, int page) {
        //固收
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidFixed) {

            platformBidsBeanFixedList = ((DiscoverPlatformBidFixed) iDiscoverPlatformBid).getPlatform_bids();
            if (mFixedAdapter == null) {
                mFixedAdapter = new DiscoverPlatformBidFixedAdapter(platformBidsBeanFixedList);
                mFixedAdapter.bindToRecyclerView(mRecyclerView);
                mFixedAdapter.disableLoadMoreIfNotFullPage();
                mFixedAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mFixedAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mFixedAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mFixedAdapter.setOnItemClickListener(this);
                setHearderAndPullToRefresh(mFixedAdapter, mFixedAdapter);
            } else {
                if (page == 1) {
                    mFixedAdapter.setNewData(platformBidsBeanFixedList);
                    mFixedAdapter.setEmptyView(R.layout.view_empty_top);
                    ImageView imageView = (ImageView) mFixedAdapter.getEmptyView().findViewById(R.id.iv_content);
                    imageView.setPadding(0, 160, 0, 0);
                    TextView textView = (TextView) mFixedAdapter.getEmptyView().findViewById(R.id.tv_content);
                    textView.setText("暂无数据");
                } else {
                    if (platformBidsBeanFixedList == null || platformBidsBeanFixedList.size() < 1) {
                        loadMoreEnd(mFixedAdapter, mRecyclerView);
                    } else {
                        mFixedAdapter.addData(platformBidsBeanFixedList);
                        mFixedAdapter.loadMoreComplete();
                    }
                }
            }


        }
        //海外
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidAbroad) {
            platformBidsBeanAbroadList = ((DiscoverPlatformBidAbroad) iDiscoverPlatformBid).getPlatform_bids();
            if (mAbroadAdapter == null) {
                mAbroadAdapter = new DiscoverPlatformBidAbroadAdapter(platformBidsBeanAbroadList);
//            mRecyclerView.setAdapter(mAbroadAdapter);
                mAbroadAdapter.bindToRecyclerView(mRecyclerView);
                mAbroadAdapter.disableLoadMoreIfNotFullPage();
                mAbroadAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mAbroadAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mAbroadAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mAbroadAdapter.setOnItemClickListener(this);
                setHearderAndPullToRefresh(mAbroadAdapter, mAbroadAdapter);
            } else {
                if (page == 1) {
                    mAbroadAdapter.setNewData(platformBidsBeanAbroadList);
                    mAbroadAdapter.setEmptyView(R.layout.view_empty_top);
                    ImageView imageView = (ImageView) mAbroadAdapter.getEmptyView().findViewById(R.id.iv_content);
                    imageView.setPadding(0, 160, 0, 0);
                    TextView textView = (TextView) mAbroadAdapter.getEmptyView().findViewById(R.id.tv_content);
                    textView.setText("暂无数据");
                } else {
                    if (platformBidsBeanAbroadList == null || platformBidsBeanAbroadList.size() < 1) {
                        loadMoreEnd(mAbroadAdapter, mRecyclerView);
                    } else {
                        mAbroadAdapter.addData(platformBidsBeanAbroadList);
                        mAbroadAdapter.loadMoreComplete();
                    }
                }
            }

        }
        // 活期 活期只有一种平台 星火钱包，这个平台包含定期也包含活期，固不分页
        // 一次性展示所有产品，两个列表在一起，分别对应"活期""固收"
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidCurrent) {
            platformBidsBeanFixedList2 = ((DiscoverPlatformBidCurrent) iDiscoverPlatformBid).getFixed_bids();
            platformBidsBeanCurrentList = ((DiscoverPlatformBidCurrent) iDiscoverPlatformBid).getCurrent_bids();

            if (mFixedAdapter2 == null || mCurrentAdapter == null) {
                mFixedAdapter2 = new DiscoverPlatformBidFixedAdapter2(platformBidsBeanFixedList2);
                recyclerView_top = new RecyclerView(activity);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                recyclerView_top.setLayoutParams(params);
                recyclerView_top.setNestedScrollingEnabled(false);
//                recyclerView_top.setNestedScrollingEnabled(false);
                recyclerView_top.setLayoutManager(new WrapContentLinearLayoutManager(activity));
                mFixedAdapter2.bindToRecyclerView(recyclerView_top);
                mFixedAdapter2.setOnItemClickListener(this);


                mCurrentAdapter = new DiscoverPlatformBidCurrentAdapter(platformBidsBeanCurrentList);
                //设置活期的列表emptyView
                mCurrentAdapter.bindToRecyclerView(mRecyclerView);
                mCurrentAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mCurrentAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mCurrentAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mCurrentAdapter.setOnItemClickListener(this);

                mCurrentAdapter.setHeaderView(recyclerView_top);
                //不分页
                setHearderAndPullToRefresh(mFixedAdapter2, null);

            }

        }
        //黄金
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidGold) {
            platformBidsBeanGoldList = ((DiscoverPlatformBidGold) iDiscoverPlatformBid).getPlatform_bids();
            if (mGoldAdapter == null) {
                mGoldAdapter = new DiscoverPlatformBidGoldAdapter(platformBidsBeanGoldList);

                mGoldAdapter.bindToRecyclerView(mRecyclerView);
                mGoldAdapter.disableLoadMoreIfNotFullPage();
                mGoldAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mGoldAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mGoldAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mGoldAdapter.setOnItemClickListener(this);

                setHearderAndPullToRefresh(mGoldAdapter, mGoldAdapter);
            } else {
                if (page == 1) {
                    mGoldAdapter.setNewData(platformBidsBeanGoldList);
                    mGoldAdapter.setEmptyView(R.layout.view_empty_top);
                    ImageView imageView = (ImageView) mGoldAdapter.getEmptyView().findViewById(R.id.iv_content);
                    imageView.setPadding(0, 160, 0, 0);
                    TextView textView = (TextView) mGoldAdapter.getEmptyView().findViewById(R.id.tv_content);
                    textView.setText("暂无数据");
                } else {
                    if (platformBidsBeanGoldList == null || platformBidsBeanGoldList.size() < 1) {
                        loadMoreEnd(mGoldAdapter, mRecyclerView);
                    } else {
                        mGoldAdapter.addData(platformBidsBeanGoldList);
                        mGoldAdapter.loadMoreComplete();
                    }
                }
            }

        }
        //保险
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidInsurance) {
            platformBidsBeanInsuranceList = ((DiscoverPlatformBidInsurance) iDiscoverPlatformBid).getPlatform_bids();
            if (mInsuranceAdapter == null) {
                mInsuranceAdapter = new DiscoverPlatformBidInsuranceAdapter(platformBidsBeanInsuranceList, activity);

                mInsuranceAdapter.bindToRecyclerView(mRecyclerView);
                mInsuranceAdapter.disableLoadMoreIfNotFullPage();
                mInsuranceAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mInsuranceAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mInsuranceAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mInsuranceAdapter.setOnItemClickListener(this);

                setHearderAndPullToRefresh(mInsuranceAdapter, mInsuranceAdapter);
            } else {
                if (page == 1) {
                    mInsuranceAdapter.setNewData(platformBidsBeanInsuranceList);
                    mInsuranceAdapter.setEmptyView(R.layout.view_empty_top);
                    ImageView imageView = (ImageView) mInsuranceAdapter.getEmptyView().findViewById(R.id.iv_content);
                    imageView.setPadding(0, 160, 0, 0);
                    TextView textView = (TextView) mInsuranceAdapter.getEmptyView().findViewById(R.id.tv_content);
                    textView.setText("暂无数据");
                } else {
                    if (platformBidsBeanInsuranceList == null || platformBidsBeanInsuranceList.size() < 1) {
                        loadMoreEnd(mInsuranceAdapter, mRecyclerView);
                    } else {
                        mInsuranceAdapter.addData(platformBidsBeanInsuranceList);
                        mInsuranceAdapter.loadMoreComplete();
                    }
                }
            }

        }

        //多彩投 众筹
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidCrowdfunding) {
            platformBidsBeanCrowdfundingList = ((DiscoverPlatformBidCrowdfunding) iDiscoverPlatformBid).getPlatform_bids();

            if (mCrowdfundingAdapter == null) {
                mCrowdfundingAdapter = new DiscoverPlatformBidCrowdfundingAdapter(platformBidsBeanCrowdfundingList, activity);

                mCrowdfundingAdapter.bindToRecyclerView(mRecyclerView);
                mCrowdfundingAdapter.disableLoadMoreIfNotFullPage();
                mCrowdfundingAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mCrowdfundingAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mCrowdfundingAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mCrowdfundingAdapter.setOnItemClickListener(this);

                setHearderAndPullToRefresh(mCrowdfundingAdapter, mCrowdfundingAdapter);
            } else {
                if (page == 1) {
                    mCrowdfundingAdapter.setNewData(platformBidsBeanCrowdfundingList);
                    mCrowdfundingAdapter.setEmptyView(R.layout.view_empty_top);
                    ImageView imageView = (ImageView) mCrowdfundingAdapter.getEmptyView().findViewById(R.id.iv_content);
                    imageView.setPadding(0, 160, 0, 0);
                    TextView textView = (TextView) mCrowdfundingAdapter.getEmptyView().findViewById(R.id.tv_content);
                    textView.setText("暂无数据");
                } else {
                    if (platformBidsBeanCrowdfundingList == null || platformBidsBeanCrowdfundingList.size() < 1) {
                        loadMoreEnd(mCrowdfundingAdapter, mRecyclerView);
                    } else {
                        mCrowdfundingAdapter.addData(platformBidsBeanCrowdfundingList);
                        mCrowdfundingAdapter.loadMoreComplete();
                    }
                }
            }

        }

        //vc理财 wuchou
        if (iDiscoverPlatformBid instanceof DiscoverPlatformBidWuchou) {
            platformBidsBeanWuchouList = ((DiscoverPlatformBidWuchou) iDiscoverPlatformBid).getPlatform_bids();
            if (mWuchouAdapter == null) {
                mWuchouAdapter = new DiscoverPlatformBidWuchouAdapter(platformBidsBeanWuchouList, activity);

                mWuchouAdapter.bindToRecyclerView(mRecyclerView);
                mWuchouAdapter.disableLoadMoreIfNotFullPage();
                mWuchouAdapter.setEmptyView(R.layout.view_empty_top);
                ImageView imageView = (ImageView) mWuchouAdapter.getEmptyView().findViewById(R.id.iv_content);
                imageView.setPadding(0, 160, 0, 0);
                TextView textView = (TextView) mWuchouAdapter.getEmptyView().findViewById(R.id.tv_content);
                textView.setText("暂无数据");
                mWuchouAdapter.setOnItemClickListener(this);

                setHearderAndPullToRefresh(mWuchouAdapter, mWuchouAdapter);
            } else {
                if (page == 1) {
                    mWuchouAdapter.setNewData(platformBidsBeanWuchouList);
                    mWuchouAdapter.setEmptyView(R.layout.view_empty_top);
                    ImageView imageView = (ImageView) mWuchouAdapter.getEmptyView().findViewById(R.id.iv_content);
                    imageView.setPadding(0, 160, 0, 0);
                    TextView textView = (TextView) mWuchouAdapter.getEmptyView().findViewById(R.id.tv_content);
                    textView.setText("暂无数据");
                } else {
                    if (platformBidsBeanWuchouList == null || platformBidsBeanWuchouList.size() < 1) {
                        loadMoreEnd(mWuchouAdapter, mRecyclerView);
                    } else {
                        mWuchouAdapter.addData(platformBidsBeanWuchouList);
                        mWuchouAdapter.loadMoreComplete();
                    }
                }
            }

        }
    }
}
