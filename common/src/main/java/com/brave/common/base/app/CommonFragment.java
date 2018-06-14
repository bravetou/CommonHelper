package com.brave.common.base.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brave.common.utils.ViewCommonUtils;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：app 下常用 Fragment
 */
public abstract class CommonFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState) => null
        View rootView = inflater.inflate(loadLayoutResId(), container);
        return rootView;
    }

    /**
     * 加载布局资源Id（默认0为不加载）
     */
    @LayoutRes
    protected abstract int loadLayoutResId();

    protected ViewCommonUtils getViewCommonUtils() {
        return ViewCommonUtils.getInstance();
    }

    public void showTooltip(CharSequence text) {
        getViewCommonUtils().showTooltip(getActivity(), text);
    }

    public void showLongTooltip(CharSequence text) {
        getViewCommonUtils().showLongTooltip(getActivity(), text);
    }

    public void setViewsFocusable(boolean focusable, View... views) {
        getViewCommonUtils().setViewsFocusable(focusable, views);
    }

    public void openViewsFocusable(View... views) {
        getViewCommonUtils().openViewsFocusable(views);
    }

    public void closeViewsFocusable(View... views) {
        getViewCommonUtils().closeViewsFocusable(views);
    }

    public void setViewsNextFocusLeft(int leftId, View... views) {
        getViewCommonUtils().setViewsNextFocusLeft(leftId, views);
    }

    public void setViewsNextFocusUp(int upId, View... views) {
        getViewCommonUtils().setViewsNextFocusUp(upId, views);
    }

    public void setViewsNextFocusRight(int rightId, View... views) {
        getViewCommonUtils().setViewsNextFocusRight(rightId, views);
    }

    public void setViewsNextFocusDown(int downId, View... views) {
        getViewCommonUtils().setViewsNextFocusDown(downId, views);
    }

    public void setViewsNextFocus(int leftId, int upId, int rightId, int downId, View... views) {
        getViewCommonUtils().setViewsNextFocus(leftId, upId, rightId, downId, views);
    }

    public void cancelViewsNextFocus(boolean leftFocusable, boolean upFocusable, boolean rightFocusable, boolean downFocusable, View... views) {
        getViewCommonUtils().cancelViewsNextFocus(leftFocusable, upFocusable, rightFocusable, downFocusable, views);
    }

    public void setViewsEnabled(boolean enabled, View... views) {
        getViewCommonUtils().setViewsEnabled(enabled, views);
    }

    public void toggleViewsEnabled(View... views) {
        getViewCommonUtils().toggleViewsEnabled(views);
    }

    public void setViewsSelected(boolean selected, View... views) {
        getViewCommonUtils().setViewsSelected(selected, views);
    }

    public void toggleViewsSelected(View... views) {
        getViewCommonUtils().toggleViewsSelected(views);
    }

    public void setViewsClickable(boolean clickable, View... views) {
        getViewCommonUtils().setViewsClickable(clickable, views);
    }

    public void toggleViewsClickable(View... views) {
        getViewCommonUtils().toggleViewsClickable(views);
    }

    public void setViewsLongClickable(boolean longClickable, View... views) {
        getViewCommonUtils().setViewsLongClickable(longClickable, views);
    }

    public void toggleViewsLongClickable(View... views) {
        getViewCommonUtils().toggleViewsLongClickable(views);
    }

    public void setViewsVisibility(int visibility, View... views) {
        getViewCommonUtils().setViewsVisibility(visibility, views);
    }

    public void showViews(View... views) {
        getViewCommonUtils().showViews(views);
    }

    public void hideViews(View... views) {
        getViewCommonUtils().hideViews(views);
    }

    public <T extends EditText> void moveCursorToEnd(T editText) {
        getViewCommonUtils().moveCursorToEnd(editText);
    }

    public <T extends EditText> void flushInputType(boolean selected, T editText) {
        getViewCommonUtils().flushInputType(selected, editText);
    }

    public <T extends View> T findViewById(@IdRes int resId) {
        return getViewCommonUtils().findViewById(getView(), resId);
    }
}