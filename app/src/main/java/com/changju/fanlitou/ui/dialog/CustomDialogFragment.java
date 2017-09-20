package com.changju.fanlitou.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.changju.fanlitou.R;
import com.changju.fanlitou.activity.LoginActivity;
import com.changju.fanlitou.adapter.FavoritePlatformAdapter;
import com.changju.fanlitou.base.MyApplication;
import com.changju.fanlitou.base.WrapContentLinearLayoutManager;
import com.changju.fanlitou.base.WrapGridLayoutManager;
import com.changju.fanlitou.bean.LoginBean;
import com.changju.fanlitou.bean.home.FavoritePlatformList;
import com.changju.fanlitou.bean.mine.PlatformDetailsBean;
import com.changju.fanlitou.fragment.home.HomeFragment;
import com.changju.fanlitou.util.ApiUtils;
import com.changju.fanlitou.util.NormalUtils;
import com.changju.fanlitou.util.ParseUtils;
import com.changju.fanlitou.util.UserUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;


import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chengww on 2017/5/16.
 */

public class CustomDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TYPE = "type";    //不同dialog类型

    public int getType() {
        return type;
    }

    private int type = 0;

    private RecyclerView mRecyclerView;

    private Activity context;

    private PlatformDetailsBean banner;
    private int position;
    public static final int FAVORITE_PLATFORM = 1;
    public static final int LOGIN = 2;
    public static final int HUOACTIVITY = 3;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定

        Bundle bundle = getArguments();

        if (bundle != null) {
            type = bundle.getInt(TYPE,0);
            switch (type){
                case FAVORITE_PLATFORM:
                    dialog.setContentView(R.layout.dialog_favorite_layout);
                    initFavoriteView(dialog);
                    break;
                case LOGIN:
                    dialog.setContentView(R.layout.dialog_login_layout);
                    initLoginView(dialog);
                    break;
                case HUOACTIVITY:
                    banner = (PlatformDetailsBean) bundle.getSerializable("banner");
                    position = bundle.getInt("position");
                    dialog.setContentView(R.layout.dialog_huo_activity);
                    initActivityView(dialog);
                    break;
                default:
                    dialog.setContentView(R.layout.dialog_normal_layout);
                    initNormalView(dialog,bundle.getString("title"),bundle.getString("content"));
                    break;
            }
        }

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        return dialog;
    }

    private void initLoginView(Dialog dialog) {
        dialog.findViewById(R.id.nativeButton).setOnClickListener(this);
        dialog.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, LoginActivity.class));
                dismiss();
            }
        });

    }

    private void initActivityView(Dialog dialog) {
        dialog.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        dialog.findViewById(R.id.close_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        ImageView imageView = (ImageView) dialog.findViewById(R.id.iv_huoActivity);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_title_dialog) ;
        Glide.with(getActivity()).load(banner.getPlatform_activity().get(position).getImg_url()).into(imageView);
        textView.setText(banner.getPlatform_activity().get(position).getTitle());
    }

    private void initNormalView(Dialog dialog,String title,String content) {
        dialog.findViewById(R.id.close_dialog).setOnClickListener(this);
        dialog.findViewById(R.id.positiveButton).setOnClickListener(this);
        TextView tv_title_dialog = (TextView) dialog.findViewById(R.id.tv_title_dialog);
        tv_title_dialog.setText(title);
        TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
        tv_content.setText(content);
    }

    private FavoritePlatformAdapter adapter;
    private void initFavoriteView(Dialog dialog) {
        dialog.findViewById(R.id.close_dialog).setOnClickListener(this);
        dialog.findViewById(R.id.positiveButton).setOnClickListener(this);
        mRecyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_content_dialog);
        GridLayoutManager manager = new WrapGridLayoutManager(getActivity(),4);
        mRecyclerView.setLayoutManager(manager);

        final LinearLayout loading_dialog = (LinearLayout) dialog.findViewById(R.id.loading_dialog);
        //请求网络
        OkGo.get(ApiUtils.getFavoritePlatformList(context)).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                final FavoritePlatformList favorite = ParseUtils.parseByGson(s,FavoritePlatformList.class);
                if (favorite != null){
                    adapter = new FavoritePlatformAdapter(favorite.getFavorite_platform());
                    mRecyclerView.setLayoutManager(new GridLayoutManager(context,4));
                    adapter.bindToRecyclerView(mRecyclerView);
                    mRecyclerView.setAdapter(adapter);
                    adapter.setEmptyView(R.layout.view_empty);
                    loading_dialog.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mRecyclerView.setLayoutManager(new WrapContentLinearLayoutManager(context));
                FavoritePlatformAdapter adapter = new FavoritePlatformAdapter(null);
                adapter.bindToRecyclerView(mRecyclerView);
                mRecyclerView.setAdapter(adapter);
                adapter.setEmptyView(R.layout.view_load_error);
                loading_dialog.setVisibility(View.GONE);
            }
        });
    }

    private Map<Integer,Boolean> isCheckedId;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_dialog:
                dismiss();
                break;
            case R.id.positiveButton:

                if (adapter != null)
                    isCheckedId = adapter.getIsChecked();

                if (isCheckedId != null){
                    StringBuilder favorite_platform_ids = new StringBuilder();
                    for (Map.Entry<Integer, Boolean> entry : isCheckedId.entrySet()) {
                        if (entry.getValue()) {
                            favorite_platform_ids.append(entry.getKey()).append(",");
                        }
                    }

                    int index = favorite_platform_ids.length() - 1;
                    if (index > 0){
                        favorite_platform_ids.deleteCharAt(index);
                    }


                    String time = String.valueOf(System.currentTimeMillis());
                    String random = ApiUtils.getRandom();
                    OkGo.post(ApiUtils.postSaveFavoritePlatform())
                            .params("t",time)
                            .params("random",random)
                            .params("sign", NormalUtils.md5(time + random + MyApplication.key))
                            .params("user_name", UserUtils.getUserName(context))
                            .params("login_token",UserUtils.getLoginToken(context))
                            .params("favorite_platform_ids",favorite_platform_ids.toString())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    LoginBean bean = ParseUtils.parseByGson(s,LoginBean.class);
                                    if (bean.isSuccess()){
                                        HomeFragment home = (HomeFragment) getParentFragment();
                                        //刷新
                                        home.initData(context);
                                    }else {
                                        NormalUtils.showToast(context,"平台收藏失败");
                                    }
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    NormalUtils.showToast(context,R.string.net_error);
                                }
                            });
                }
                dismiss();
                break;
            case R.id.nativeButton:
                dismiss();
                break;
        }
    }

}
