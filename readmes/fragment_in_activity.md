# 4.Activity嵌套Fragment的层级说明

## 4.1 [MainActivity](app/src/main/java/com/changju/fanlitou/activity/MainActivity.java)

MainActivity为app首页，包含下方四个（有活动时为5个）button在内的所有页面

如图：MainActivity包含整个窗口的布局。下方button上为ViewPager+Fragment组合

下方各button index对应Fragment名称如下：

|index|名称|Fragment|下辖一级Fragment|package|
|----|----|----|----|----|
|0|首页|HomeFragment|BidFragment|fragment/home|
|1|发现|DiscoverFragment|BidFragment/FundListFragment|fragment/discover|
|2|账本|AccountFragment|InvestedFragment/InvestReportFragment/MoneyBackFragment|fragment/account|
|3|我的|MineFragment|UserFragment/UserNotLoginFragment|fragment/mine|
|4|活动|WebFragment|--|fragment|

### [4.1.1.HomeFragment](app/src/main/java/com/changju/fanlitou/fragment/home/HomeFragment.java) 
如图
首页黑色框体部分为[HomeFragment](app/src/main/java/com/changju/fanlitou/fragment/home/HomeFragment.java)布局位置，绿色框体为其下辖[BidFragment](app/src/main/java/com/changju/fanlitou/fragment/home/BidFragment.java)布局位置

下方推荐标的可能有多种情况，已重构进BidFragment进行维护

![HomeFragment](outputs/HomeFragment.png)

### [4.1.2.DiscoverFragment](app/src/main/java/com/changju/fanlitou/fragment/discover/DiscoverFragment.java)
如图
首页红色框体部分为[DiscoverFragment](app/src/main/java/com/changju/fanlitou/fragment/discover/DiscoverFragment.java)布局位置，绿色框体为其下辖[PlatformFragment](app/src/main/java/com/changju/fanlitou/fragment/discover/PlatformFragment.java) 和 [FundListFragment](app/src/main/java/com/changju/fanlitou/fragment/discover/FundListFragment.java)布局位置

![DiscoverFragment.png](outputs/DiscoverFragment.png)

上方tab为com.flyco.tablayout.SegmentTabLayout
使用说明：https://github.com/H07000223/FlycoTabLayout/blob/master/README_CN.md

同使用ViewPager+Fragment实现

### [4.1.3.AccountFragment](app/src/main/java/com/changju/fanlitou/fragment/account/AccountFragment.java)
- 账本页面实现同上

其中已投项目有回款中和已完成两种状态，这两种状态使用两个适配器但共用一个RecyclerView

传送门：
[回款中Adapter](app/src/main/java/com/changju/fanlitou/adapter/RePayingAdapter.java)
[已完成Adapter](app/src/main/java/com/changju/fanlitou/adapter/RePayingCompleteAdapter.java)

- 回款日历依赖于[calendar](calendar) module

该module中，[ScheduleLayout](calendar/src/main/java/com/jeek/calendar/widget/calendar/schedule/ScheduleLayout.java)
为包含日历月视图、周视图、和下方回款列表

[MonthView](calendar/src/main/java/com/jeek/calendar/widget/calendar/month/MonthView.java)
为月视图，处理月视图的绘制，已回款和待回款圆点绘制等

[WeekView](calendar/src/main/java/com/jeek/calendar/widget/calendar/week/WeekView.java)
为周视图，周视图已取消已回款和待回款圆点绘制

- 投资报表
投资报表饼状图以及基金等折线图依赖[MPChartLib](MPChartLib)

查看使用文档:https://github.com/PhilJay/MPAndroidChart

对原module源码进行了修改，请不要将该module修改成远程依赖

### [4.1.4.MineFragment](app/src/main/java/com/changju/fanlitou/fragment/mine/MineFragment.java)
