package com.brave.common.base;

import android.content.Context;
import androidx.annotation.IdRes;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/8 <br/>
 * <b>description</b> ：View 的常用工具
 */
public class ViewCommonUtils {
    private static class SingletonHolder {
        private volatile static ViewCommonUtils mInstance = new ViewCommonUtils();
    }

    public static ViewCommonUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    private ViewCommonUtils() {
    }

    /**
     * 短时间显示提示
     */
    public void showTooltip(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示提示
     */
    public void showLongTooltip(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 设置 Views 是否可以 接收焦点
     */
    public void setViewsFocusable(boolean focusable, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            // 设置这个视图是否可以接收焦点
            v.setFocusable(focusable);
            // 设置这个视图是否可以在触摸模式下接收焦点
            v.setFocusableInTouchMode(focusable);
        }
    }

    /**
     * 打开 Views 接收焦点
     */
    public void openViewsFocusable(View... views) {
        setViewsFocusable(true, views);
    }

    /**
     * 关闭 Views 接收焦点
     */
    public void closeViewsFocusable(View... views) {
        setViewsFocusable(false, views);
    }

    /**
     * 当下一个焦点是 {@link View#FOCUS_LEFT} 时，设置要使用的 Views 的id
     */
    public void setViewsNextFocusLeft(int leftId, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            if (0 != leftId) {
                v.setNextFocusLeftId(leftId);
            } else {
                v.setNextFocusLeftId(v.getId());
            }
        }
    }

    /**
     * 当下一个焦点是 {@link View#FOCUS_UP} 时，设置要使用的 Views 的id
     */
    public void setViewsNextFocusUp(int upId, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            if (0 != upId) {
                v.setNextFocusUpId(upId);
            } else {
                v.setNextFocusUpId(v.getId());
            }
        }
    }

    /**
     * 当下一个焦点是 {@link View#FOCUS_RIGHT} 时，设置要使用的 Views 的id
     */
    public void setViewsNextFocusRight(int rightId, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            if (0 != rightId) {
                v.setNextFocusRightId(rightId);
            } else {
                v.setNextFocusRightId(v.getId());
            }
        }
    }

    /**
     * 当下一个焦点是 {@link View#FOCUS_DOWN} 时，设置要使用的 Views 的id
     */
    public void setViewsNextFocusDown(int downId, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            if (0 != downId) {
                v.setNextFocusDownId(downId);
            } else {
                v.setNextFocusDownId(v.getId());
            }
        }
    }

    /**
     * 设置 Views 的下一焦点位置索引（即：目标Id）（顺序为：左、上、右、下）
     */
    public void setViewsNextFocus(int leftId, int upId, int rightId, int downId, View... views) {
        setViewsNextFocusLeft(leftId, views);
        setViewsNextFocusUp(upId, views);
        setViewsNextFocusRight(rightId, views);
        setViewsNextFocusDown(downId, views);
    }

    /**
     * 取消 Views 的下一焦点位置索引（顺序为：左、上、右、下）《 true => 取消 》
     */
    public void cancelViewsNextFocus(boolean leftFocusable, boolean upFocusable,
                                     boolean rightFocusable, boolean downFocusable, View... views) {
        if (leftFocusable) {
            setViewsNextFocusLeft(0, views);
        }
        if (upFocusable) {
            setViewsNextFocusUp(0, views);
        }
        if (rightFocusable) {
            setViewsNextFocusRight(0, views);
        }
        if (downFocusable) {
            setViewsNextFocusDown(0, views);
        }
    }

    /**
     * 设置 Views 的启用状态
     */
    public void setViewsEnabled(boolean enabled, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            // 设置此视图的启用状态
            views[i].setEnabled(enabled);
        }
    }

    public void toggleViewsEnabled(View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            v.setEnabled(!v.isEnabled());
        }
    }

    /**
     * 设置 Views 的选择状态
     */
    public void setViewsSelected(boolean selected, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            // 改变此视图的选择状态
            views[i].setSelected(selected);
        }
    }

    public void toggleViewsSelected(View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            v.setSelected(!v.isSelected());
        }
    }

    /**
     * 设置 Views 单击事件的启用状态
     */
    public void setViewsClickable(boolean clickable, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            // 启用或禁用此视图的单击事件
            views[i].setClickable(clickable);
        }
    }

    public void toggleViewsClickable(View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            v.setClickable(!v.isClickable());
        }
    }

    /**
     * 设置 Views 长按事件的启用状态
     */
    public void setViewsLongClickable(boolean longClickable, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            // 启用或禁用此视图的长按事件
            views[i].setLongClickable(longClickable);
        }
    }

    public void toggleViewsLongClickable(View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            View v = views[i];
            v.setLongClickable(!v.isLongClickable());
        }
    }

    /**
     * 设置 Views 的可见性状态
     *
     * @param visibility One of {@link View#VISIBLE}, {@link View#INVISIBLE}, or {@link View#GONE}.
     */
    public void setViewsVisibility(int visibility, View... views) {
        int count = null == views ? 0 : views.length;
        for (int i = 0; i < count; i++) {
            // 设置此视图的可见性状态
            views[i].setVisibility(visibility);
        }
    }

    public void showViews(View... views) {
        setViewsVisibility(View.VISIBLE, views);
    }

    public void hideViews(View... views) {
        setViewsVisibility(View.GONE, views);
    }

    /**
     * 移动光标到末尾
     */
    public <T extends EditText> void moveCursorToEnd(T editText) {
        if (editText.isFocused()) {
            Editable editable = editText.getText();
            Selection.setSelection(editable, editable.length());
        }
    }

    /**
     * 刷新 EditText 的 inputType 属性值（实现两种 true => 明文 / false => 密文）
     */
    public <T extends EditText> void flushInputType(boolean selected, T editText) {
        if (selected) {
            // 替换输入框的显示模式为 普通文本
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            // 替换输入框的显示模式为 加密模式
            editText.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        // 移动密码光标到末尾
        moveCursorToEnd(editText);
    }

    /**
     * 根据 ResId 查找 View
     */
    public <T extends View> T findViewById(View rootView, @IdRes int resId) {
        if (null == rootView) {
            return null;
        }
        return rootView.findViewById(resId);
    }
}