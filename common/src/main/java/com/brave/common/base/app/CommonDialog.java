package com.brave.common.base.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brave.common.base.DialogBuilder;
import com.brave.common.base.ViewCommonUtils;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：app 下常用 Dialog
 */
public abstract class CommonDialog<B extends DialogBuilder> extends DialogFragment {
    protected Activity activity;

    public CommonDialog(B builder) {
        if (null == builder.getActivity()) {
            throw new RuntimeException("null pointer.");
        }
        this.activity = builder.getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
    @StyleRes
    protected abstract int loadDialogTheme();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState) => null
        // 外部不可点击
        getDialog().setCanceledOnTouchOutside(false);
        // dialogFragment设置背景 为透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 载入需要显示的布局
        View rootView = inflater.inflate(loadLayoutResId(), container, false);
        return rootView;
    }

    /**
     * 加载布局资源Id（默认0为不加载）
     */
    @LayoutRes
    protected abstract int loadLayoutResId();

    @Override
    public void onDestroyView() {
        // 手动回收 对Activity的引用，防止内存泄漏
        activity = null;
        super.onDestroyView();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        // super.show(manager, tag);
        Fragment fragment = manager.findFragmentByTag(tag); // 查询 FragmentManager 中是否有对应TAG的Fragment存在
        if (fragment == null || !fragment.isAdded()) { // 判断 当前片段 没有被添加到其活动中 或者 为空
            super.show(manager, tag);
        }
    }

    public void show(FragmentManager manager) {
        // getPackageName() => com.brave.common.base.app
        // getClass().getName() => com.brave.common.base.app.CommonDialog
        // getClass().getSimpleName() => CommonDialog
        String tag = activity.getClass().getName() + "$$$" + getClass().getSimpleName(); // com.brave.employ.ui.home.HomeActivity$$$TestDialog
        show(manager, tag);
    }

    public void show() {
        show(activity.getFragmentManager());
    }

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