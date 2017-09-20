# APP框架及工具类说明

## 1.APP框架
### 1.1.网络请求
网络请求使用OkGo框架进行网络加载，关于OkGo使用说明请看这里：

https://github.com/chengww5217/okhttp-OkGo/blob/master/README.md
### 1.2.BaseActivity、BaseFragment的使用
#### 5月7日更新LazyFragment：

新建Fragment请继承LazyFragment，可自行实现获取单例的方法，其他和BaseFragment使用无区别。
```java
public static NewFragment newInstance(boolean isLazyLoad) {

        Bundle args = new Bundle();
        //isLazyLoad Fragment是否懒加载
        args.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, isLazyLoad);
        NewFragment fragment = new NewFragment();
        fragment.setArguments(args);
        return fragment;
    }
```

新建Activity或Fragment请直接继承上述基类，Fragment已实现懒加载。

#### 各重写方法含义:
```java
public void initParams(Bundle params) {}
//接收携带数据的页面跳转的数据
//示例：
String example = params.getString("key",null);
//得到了上个界面传送来的键为"key"的数据
```

```java
public int bindLayout() {
    return R.layout.activity_main;
}
//bindLayout布局
```

```java
public void initView(View view) {}
//findView setOnClick等初始化工作
```

```java
public void doBusiness(Context mContext) {}
//处理业务逻辑
```

```java
public void widgetClick(View v) {
    switch (v.getId()){
        case R.id.example:
        break;
    }
}
//点击事件的处理
```

```java
public void initData() {};
//Fragment在此加载数据以实现懒加载
```

```java
public void startActivity(Class<?> clz) {
    startActivity(clz, null);
}
//页面跳转请直接使用方法startActivity(MainActivity.class);
```

```java
public void startActivity(Class<?> clz, Bundle bundle) {}
//携带数据的页面跳转请使用该方法
```

```java
public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {}
//startActivityForResult 自行重写onActivityResult()方法
```

```java
public void setAllowFullScreen(boolean allowFullScreen) {}
//是否允许全屏，默认允许
```

```java
public void setScreenRotate(boolean isAllowScreenRotate) {}
//是否允许屏幕旋转，默认不允许
```

```java
public void setSteepStatusBar(boolean isSetStatusBar) {}
//是否允许沉浸状态栏，默认允许
```

```java
public <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }
    
//等同于findViewById()，不用进行强制类型转化
```
### 1.3.Json数据解析
新建实体类后直接使用如下方法进行解析
```java
Bean bean = ParseUtils.parseByGson(json,Bean.class);
```

### 1.4.RecyclerView
新建RecyclerView，其Adapter请继承自BaseQuickAdapter<Bean bean, BaseViewHolder>,会重写如下方法。

请在convert方法里面进行数据加载。
```java
public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public QuickAdapter() {
        super(R.layout.tweet, DataServer.getSampleData());
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, Status item) {
        viewHolder.setText(R.id.tweetName, item.getUserName())
                .setText(R.id.tweetText, item.getText())
                .setText(R.id.tweetDate, item.getCreatedAt())
                .setVisible(R.id.tweetRT, item.isRetweet())
                .linkify(R.id.tweetText);
                 Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
    }
}
    
```
#### 使用步骤

1.继承BaseQuickAdapter基类
> 泛型为List的数据类型

2.重写convert方法
> 通过viewHolder点出各种常用方法，如果有自定义控件或者viewHolder没有提供的方法，就可以通过getView方法来实现获取控件，然后进行的操作。

3.如果需要获取当前position
> 可以通过viewHolder.getLayoutPosition()来获取

## [2.utils工具类说明](app/src/main/java/com/changju/fanlitou/util)
### [2.1.ApiUtils](app/src/main/java/com/changju/fanlitou/util/ApiUtils.java)
api位于util--ApiUtils

- 所有api信息存放于该类中，后续添加获取api方法，不要直接在Activity中拼接字符串得到api
- 所有get请求，方法以getXXX()...的形式，在方法内自行拼接所有参数
- 


### [2.2.NormalUtils](app/src/main/java/com/changju/fanlitou/util/NormalUtils.java)
> static int dp2px(Context context, float dp)----dp转换为px

> static int px2dp(Context context, float px)

> static int sp2px(Context context, float spValue)

> static boolean isLogin(Context context)----查询用户是否登录

> static boolean isDouble(String str)----判断是否为浮点数

> static int getStatusBarHeight(Context context)----获取状态栏高度，用于给不希望侵入状态栏的控件setPadding

> static String getDataSize(long size)----返回并格式化byte的数据大小对应的文本

> static void showToast(Context context,CharSequence message)----全局吐司公共方法

> static String getVersion(Context context)----获取版本号:versionName

> static boolean isNeedUpdate(Context context)----判断上次用户更新时点击下次再说是否超过了24小时

> static String getIgnoreVersion/setIgnoreVersion(Context context)----获取/设置用户忽略升级的版本名

> static String bigFloat2String(float d)----float大数据显示不显示为科学计数法

> static void initBidWeb(WebView mWebView,String introduction)----初始化标的详情页面中，下方产品详情webview

### [2.3.ParseUtils](app/src/main/java/com/changju/fanlitou/util/ParseUtils.java)
> static <T> T parseByGson(String s,Class<T> clz)----通用Gson解析工具类

@param s Json字符串

@param clz Bean.class

@param <T> 目标类 Bean

@return 结果对象

### [2.4.CountDownTimerUtil](app/src/main/java/com/changju/fanlitou/util/CountDownTimerUtil.java)
点击获取验证码进行倒计时的工具类

使用：
```
CountDownTimerUtil mCountDownTimerUtils = new CountDownTimerUtil(button, 60000, 1000);
mCountDownTimerUtils.start();
```

使用后如需恢复到初始状态,请使用
```
if (mCountDownTimerUtils != null) {
    mCountDownTimerUtils.cancel();
    mCountDownTimerUtils.onClear();
}
```

### [2.5.FileUtils](app/src/main/java/com/changju/fanlitou/util/FileUtils.java)
文件处理工具类

> static void saveData(Context context,String filename, String content)
----数据保存，可以存json数据以缓存数据。注：手势密码也是利用该类保存于本地。

> static String getData(Context context,String filename)----取数据，只能取缓存的json，不能取保存的密码

> static byte[] getDataByte(Context context,String filename)----取数据，只能取保存的密码，不能取缓存的json

### [2.6.RecordSQLiteOpenHelper](app/src/main/java/com/changju/fanlitou/util/RecordSQLiteOpenHelper.java)
本地搜索历史记录保存帮助类

此帮助类未完成抽象出来，具体操作数据库的放法写到了对应平台/基金搜索里面了

请自行参阅:

[PlatformSearchActivity](app/src/main/java/com/changju/fanlitou/activity/platform/PlatformSearchActivity.java)
[FundSearchActivity](app/src/main/java/com/changju/fanlitou/activity/fund/FundSearchActivity.java)

### [2.7.TextClickable](app/src/main/java/com/changju/fanlitou/util/TextClickable.java)
extends ClickableSpan

该类用于 《协议》 点击的实现

用法：

in xml 
```xml
<FrameLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginEnd="15dp"
                android:layout_marginStart="15dp"
                android:layout_marginTop="-5dp"
                android:orientation="horizontal">
                <TextView
                    android:id="@+id/tv_agreement_info"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:lineSpacingExtra="4dp"
                    android:textColor="@color/colorBidName"
                    android:textSize="12sp" />
                <LinearLayout
                    android:id="@+id/layout_agreement"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_gravity="top"
                    android:orientation="horizontal">
                    <CheckBox
                        android:id="@+id/check_box"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="top"
                        android:background="@android:color/transparent"
                        android:button="@null"
                        android:checked="true"
                        android:drawablePadding="10dp"
                        android:drawableStart="@drawable/selector_check_box"
                        android:gravity="center_vertical"
                        android:text="同意"
                        android:textColor="#5e5e5e"
                        android:textSize="12sp" />
                </LinearLayout>
            </FrameLayout>
```

in java:以[基金开户](app/src/main/java/com/changju/fanlitou/activity/fund/OpenFundActivity.java)为例
```
List<OpenFundAccountBean.AgreementListBean> agreements = info.getAgreement_list();
                        if (agreements == null || agreements.size() < 1) {
                            layout_agreement.setVisibility(View.GONE);
                        } else {
                            layout_agreement.setVisibility(View.VISIBLE);
                            StringBuilder sb = new StringBuilder("        同意");
                            for (int i = 0; i < agreements.size(); i++) {
                                final OpenFundAccountBean.AgreementListBean agree = agreements.get(i);
                                final String title = agree.getTitle() == null ? agree.getName() : agree.getTitle();
                                sb.append("《");
                                sb.append(title);
                                sb.append("》");
                            }

                            SpannableString spanableInfo = new SpannableString(sb.toString());
                            ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(android.R.color.transparent));
                            spanableInfo.setSpan(colorSpan, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                            for (int i = 0; i < agreements.size(); i++) {
                                final OpenFundAccountBean.AgreementListBean agree = agreements.get(i);
                                final String title = agree.getTitle() == null ? agree.getName() : agree.getTitle();
                                spanableInfo.setSpan(new TextClickable(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                /**
                                                 * 协议内容展示类型
                                                 * url：协议内容来自于url字段
                                                 * content：协议内容来自于content字段
                                                 */
                                                Bundle args = new Bundle();
                                                args.putString(AgreementDialog.TITLE, title);
                                                args.putString(AgreementDialog.CONTENT, agree.getContent());
                                                AgreementDialog dialog = new AgreementDialog();
                                                dialog.setArguments(args);
                                                dialog.show(getSupportFragmentManager(), "content");
                                            }
                                        }), sb.indexOf(title) - 1, sb.indexOf(title) + title.length() + 1, //设置需要监听的字符串位置
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            tv_agreement_info.setText(spanableInfo);  //将处理过的数据set到View里
                            tv_agreement_info.setMovementMethod(LinkMovementMethod.getInstance());
                            tv_agreement_info.setHighlightColor(getResources().getColor(android.R.color.transparent));
                        }
```

### [2.8.UserUtils](app/src/main/java/com/changju/fanlitou/util/UserUtils.java)
对用户信息进行操作的工具类
[UserUtils.java](app/src/main/java/com/changju/fanlitou/util/UserUtils.java)
```
    /**
     * 用户是否已登录
     * @param context
     * @return
     */
    public static boolean isLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        return sp.getBoolean(MyApplication.IS_LOGIN,false);
    }

    /**
     * 获取用户名
     * @param context
     * @return
     */
    public static String getUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        if (sp.getBoolean(MyApplication.IS_LOGIN,false))
            return sp.getString(MyApplication.USER_NAME,"");
        return "";
    }

    /**
     * 获取login_token
     * @param context
     * @return
     */
    public static String getLoginToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        if (sp.getBoolean(MyApplication.IS_LOGIN,false))
            return sp.getString(MyApplication.TOKEN,"");
        return "";
    }

    /**
     * 清除登录信息--退出登录
     * @param context
     */
    public static void clearLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean(MyApplication.IS_LOGIN,false).apply();
        sp.edit().putString(MyApplication.TOKEN,"").apply();
        sp.edit().putString(MyApplication.USER_NAME,"").apply();
    }

    /**
     * 保存登录信息
     * @param context
     * @param token
     * @param userName
     */
    public static void setLogin(Context context, String token, String userName){
        SharedPreferences sp = context.getSharedPreferences(MyApplication.USER_INFO, Context.MODE_PRIVATE);
        sp.edit().putBoolean(MyApplication.IS_LOGIN,true).apply();
        if (!TextUtils.isEmpty(token))
            sp.edit().putString(MyApplication.TOKEN,token).apply();
        if (!TextUtils.isEmpty(userName))
            sp.edit().putString(MyApplication.USER_NAME,userName).apply();
    }

    private static final String TAG = "LockCount";
    private static final String SKIP = "isSkip";

    /**
     * 保存用户输入手势密码出错的次数
     * @param context
     * @param num
     * @param userName
     */
    public static void setLockCount(Context context,int num,String userName){
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        sp.edit().putInt(userName,num).apply();
    }

    /**
     * 获取用户输入手势密码出错的次数
     * @param context
     * @param userName
     * @return
     */
    public static int getLockCount(Context context,String userName){
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sp.getInt(userName,5);
    }

    /**
     * 用户是否跳过了手势密码的设置
     * @param context
     * @param userName
     * @return
     */
    public static boolean isSkipGesture(Context context,String userName){
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sp.getBoolean(userName + SKIP,false);
    }

    /**
     * 设置用户跳过手势密码
     * @param context
     * @param userName
     * @param isSkip
     */
    public static void setSkipGesture(Context context, String userName,boolean isSkip){
        SharedPreferences sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(userName + SKIP,isSkip).apply();
    }

```

