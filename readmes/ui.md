# 3.自定义控件

## 1.dialog
除loading动画继承自dialog外，其余dialog均继承自DialogFragment

各dialog含义及用法：
- 1.1.AddBankCardDialog

  用于返利投银行卡管理、提现等可能出现的选择开户银行的地方
- 1.2.AddBankInfoDialog

  用于返利投银行卡管理、提现等可能出现的选择开户省市，已弃用
- 1.3.AddIntelligentBankCardDialog

  用于智能投顾开户、提现等可能出现的选择开户银行的地方
- 1.4.AddIntelligentBankInfoDialog

  用于智能投顾开户、提现等可能出现的选择开户省市，使用中
- 1.5.AddOpenFundCardDialog

  用于基金开户可能出现的选择开户银行的地方
- 1.6.AddOpenFundInfoDialog

  用于基金开户等可能出现的选择开户省市的地方
- 1.7.AgreementDialog

  用于除积分（返利宝）协议外的其他协议弹窗
  ```
  Bundle args = new Bundle();
  args.putString(AgreementDialog.TITLE, agree.getTitle());
  args.putString(AgreementDialog.CONTENT, agree.getContent());
  AgreementDialog dialog = new AgreementDialog();
  dialog.setArguments(args);
  dialog.show(getSupportFragmentManager(), "agreement");
  ```
- 1.8.AnimDialog

  loading显示
  ```
  AnimDialog mAnimDialog = AnimDialog.showDialog(context);
  mAnimDialog.show();
  mAnimDialog.dismiss();
  ```
- 1.9.BuyDialog

  非智能投顾，标的详情页点击立即购买/注册展示的弹窗
  
- 1.10.CustomDialogFragment

  一些常用的弹窗样式：
  
  - TYPE:FAVORITE_PLATFORM 首页收藏平台弹窗
  - TYPE:LOGIN 用户未登录下，点击首页收藏平台提示登录的弹窗
  - TYPE:HUOACTIVITY 平台详情页面展示红包活动的弹窗（只有一张图片的）
  
- 1.11.DialogMainActivity

  首页活动button
  
- 1.12.EvaluateDialog

  基金风险测评提交时展示已提交正在测评的弹窗
  
- 1.13.HomeActivityDialog

  首页有活动时的弹窗：全屏WebView
  
- 1.14.ImageDialog

  图片协议弹窗
  
- 1.15.IntelligentBuyDialog

  智能投顾，标的详情页点击立即购买/注册展示的弹窗
  
- 1.16.JFAgreementDialog

  积分（返利宝）协议弹窗
  
- 1.17.JifenDialogFragment

  提现时提示开启积分（返利宝）时的弹窗
  
- 1.18.NormalWhiteDialog

  通用白色弹窗，用于点击问号图标后显示提示信息的弹窗
  
- 1.19.RechargeBankStorageDialog

  智能投顾跳转存管银行WebView弹窗
  
- 1.20.RechargeDialog

  智能投顾投资时投资金额大于可用余额时提示充值的弹窗
  
- 1.21.RedeemFeeDialog

  基金赎回，赎回费率弹窗
  
- 1.22.RiskHintDialog

  基金申购提示风险弹窗
  
- 1.23.TicketsDialog

  我的--投资记录--使用优惠券弹窗
  
- 1.24.UpdateDialog

  版本升级弹窗

## 2.DrawableAlignedButton

用于 我的--资金明细--带右侧展示箭头的button

## 3.EditTextWithDel、EditTextWithSearchAndDel
点击消除hint、带删除图标的EditText
注：使用setOnFocusChangeListener()，会让上述效果失效，如需实现上述效果请在监听中附上如下代码：
```
        EditText editText = (EditText)v;
        String hint;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);

            if (TextUtils.isEmpty(editText.getHint())){
                return;
            }
            hint = editText.getHint().toString();
            editText.setTag(hint);
            editText.setHint("");
        } else {
            editText.setClearIconVisible(false);

            if (editText.getTag() == null)
                return;
            hint = editText.getTag().toString();
            if (TextUtils.isEmpty(hint))
                return;
            editText.setHint(hint);
        }
```
EditTextWithSearchAndDel：在上述控件基础上左侧增加搜索图标

## 4.FlowLayout
流式布局：用于搜索显示最近搜索、热门搜索
无需设置，View自动换行显示

## 5.FrameAnimation
自定义帧动画播放，未用

## 6.ImageTextButton
上面图片，下面文本的控件

in xml
```xml
<com.changju.fanlitou.ui.ImageTextButton
    android:id="@+id/btn_fixed_home"
    style="@style/ImageTextStyle"
    imageTextButton:img="@mipmap/fixed"
    imageTextButton:text="@string/fixed" />
```
默认图片宽高40dp、默认文本大小12.5sp

使用下述方法设置控件的一些属性：
```
    /**
     * @param resId
     */
    public void setImageResource(int resId) {}

    public void setImageDrawable(Drawable drawable){}

    /**
     * @param text
     */
    public void setTextViewText(String text) {}

    /**
     * @param color
     */
    public void setTextColor(int color) {}

    /**
     * 文字大小
     * @param size 单位sp
     */
    public void setTextSize(float size){}

    /**
     * 是否显示左侧热门标签
     * @param boolean
     */
    public void isHot(boolean isHot){}

    /**
     * 图片宽高
     * @param context
     * @param width 单位dp
     * @param height 单位dp
     */
    public void setImageSize(Context context,float width,float height){}

    public void setLayoutPxSize(int width){}

    public void setTextMargin(int marginTop){}
```

## 7.MyRadioGroup
可换行的RadioGroup，用于平台、固收二级页筛选

## 8.NoScrollViewPager
禁止滑动的ViewPager

## 9.ObservableScrollView
监听滑动距离的ScrollView。用于基金费率等显示回到顶部的外层滑动控件
```
scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if(oldy == 0 || y == 0){
                    iv_backtop.setVisibility(View.GONE);
                }else{
                    iv_backtop.setVisibility(View.VISIBLE);
                }
            }
        });
```

## 10.ResizableImageView
宽度match_parent时，高度可自行计算的ImageView

## 11.RoundProgressBar
环形ProgressBar。仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度

some xml attrs
```
app:roundColor 环颜色,非进度
app:roundProgressColor 环颜色,进度
app:textColor 指示进度值字体颜色
app:textSize 指示进度值字体大小
app:roundWidth 环的宽度：指示环的粗细
app:max 最大进度
app:textIsDisplayable 文字是否显示
app:style 实心或者 空心
```

some java method
```
setProgress(int progress) 设置进度
setAnimProgress(int progress) 设置进度，进度递增动画进行展示
```
## 12.WheelView
使用加息券时的可滚动选择控件

## 13.WrapContentViewPager
自适应高度ViewPager。设置高度wrap_content也可显示的ViewPager

