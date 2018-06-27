package com.brave.common.utils.span;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Spannable;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/13 <br/>
 * <b>description</b> ： SpannableString 相关工具类<br/>
 * span >>> ? extends {@link android.text.style.CharacterStyle} & ? implements {@link android.text.style.UpdateAppearance} or {@link android.text.ParcelableSpan}<br/>
 * start >>> 开始位置索引<br/>
 * end >>> 结束位置索引<br/>
 * flag >>>
 * <ul>
 * <li>{@link Spannable#SPAN_EXCLUSIVE_INCLUSIVE}：在 Span前面输入的字符不应用 Span的效果，在后面输入的字符应用Span效果。</li>
 * <li>{@link Spannable#SPAN_INCLUSIVE_EXCLUSIVE}：在 Span前面输入的字符应用 Span 的效果，在后面输入的字符不应用Span效果。</li>
 * <li>{@link Spannable#SPAN_INCLUSIVE_INCLUSIVE}：在 Span前后输入的字符都应用 Span 的效果。</li>
 * <li>{@link Spannable#SPAN_EXCLUSIVE_EXCLUSIVE}：前后都不包括。</li>
 * </ul>
 */
public final class SpanUtils {
    private SpanUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    public static SpanGenerates with() {
        return SpanGenerates.newInstance(CommonConfig.getContext());
    }

    public static SpanGenerates with(Context context) {
        return SpanGenerates.newInstance(context);
    }

    public static SpanGenerates with(@NonNull Activity activity) {
        return SpanGenerates.newInstance(activity);
    }

    public static SpanGenerates with(@NonNull Fragment fragment) {
        return SpanGenerates.newInstance(fragment.getActivity());
    }

    public static SpanGenerates with(@NonNull android.support.v4.app.Fragment fragment) {
        return SpanGenerates.newInstance(fragment.getActivity());
    }
}