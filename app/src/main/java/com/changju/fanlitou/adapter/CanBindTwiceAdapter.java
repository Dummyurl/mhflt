package com.changju.fanlitou.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by chengww on 2017/6/7.
 * 可以对recyclerView bind两次的adapter
 */

public abstract class CanBindTwiceAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T,K> {
    public CanBindTwiceAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public CanBindTwiceAdapter(@Nullable List<T> data) {
        super(data);
    }

    public CanBindTwiceAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    /**
     * same as recyclerView.setAdapter(), and save the instance of recyclerView
     * 可以bind多次
     */
    public void bindToRecyclerView(RecyclerView recyclerView) {
        Method method = null;
        try {
            method = BaseQuickAdapter.class.getDeclaredMethod("setRecyclerView",
                    RecyclerView.class);
            //暴力破解权限
            method.setAccessible(true);
            method.invoke(this, recyclerView);
            RecyclerView recyclerView1 = getRecyclerView();
            if (recyclerView1 != null)
                recyclerView1.setAdapter(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//            setRecyclerView(recyclerView);
    }
}
