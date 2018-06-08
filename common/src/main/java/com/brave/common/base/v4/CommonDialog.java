package com.brave.common.base.v4;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brave.common.utils.ViewCommonUtils;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：v4 下常用 Dialog
 */
public abstract class CommonDialog<B extends CommonDialog.DialogBuilder> extends DialogFragment {
    protected FragmentActivity activity;

    protected CommonDialog(B builder) {
        this.activity = builder.activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int dialogStyle = loadDialogStyle();
        if (0 == dialogStyle) {
            dialogStyle = DialogFragment.STYLE_NORMAL;
        }
        int dialogTheme = loadDialogTheme();
        if (0 == dialogTheme) {
            dialogTheme = android.R.style.Theme_Holo_Dialog_NoActionBar;
        }
        setStyle(dialogStyle, dialogTheme);
    }

    /**
     * 加载 DialogStyle
     */
    protected int loadDialogStyle() {
        return 0;
    }

    /**
     * 加载 Dialog 主题（默认 0 为 加载无标题主题）
     */
    protected abstract @StyleRes
    int loadDialogTheme();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState) => null
        // 外部不可点击
        getDialog().setCanceledOnTouchOutside(false);
        // dialogFragment设置背景 为透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 载入需要显示的布局
        View rootView = inflater.inflate(loadLayoutResId(), container);
        return rootView;
    }

    /**
     * 加载布局资源Id（默认0为不加载）
     */
    protected abstract @LayoutRes
    int loadLayoutResId();

    @Override
    public void show(FragmentManager manager, String tag) {
        // super.show(manager, tag);
        Fragment fragment = manager.findFragmentByTag(tag); // 查询 FragmentManager 中是否有对应TAG的Fragment存在
        if (fragment == null || !fragment.isAdded()) { // 判断 当前片段 没有被添加到其活动中 或者 为空
            super.show(manager, tag);
        }
    }

    public void show(FragmentManager manager) {
        // getPackageName() => com.brave.common.base.v4
        // getClass().getName() => com.brave.common.base.v4.CommonDialog
        // getClass().getSimpleName() => CommonDialog
        String tag = activity.getClass().getName() + "$$" + getClass().getSimpleName();
        show(manager, tag);
    }

    public void show() {
        show(activity.getSupportFragmentManager());
    }

    public static class DialogBuilder {
        protected FragmentActivity activity;

        public DialogBuilder(FragmentActivity activity) {
            this.activity = activity;
        }
    }

    protected ViewCommonUtils getViewCommonUtils() {
        return ViewCommonUtils.getInstance();
    }

    public void showTooltip(CharSequence text) {
        getViewCommonUtils().showTooltip(getContext(), text);
    }

    public void showLongTooltip(CharSequence text) {
        getViewCommonUtils().showLongTooltip(getContext(), text);
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