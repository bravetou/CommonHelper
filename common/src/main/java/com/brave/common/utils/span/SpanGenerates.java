package com.brave.common.utils.span;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuggestionSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/27 <br/>
 * <b>description</b> ：SpannableString 生成
 */
public class SpanGenerates {
    /* 默认标识 >>> 前后都不包括 */
    private final int DEFAULT_SPAN_FLAG = Spannable.SPAN_EXCLUSIVE_INCLUSIVE;
    private final Context context;
    private SpannableStringBuilder stringBuilder; // 创建文本类，它的内容和标记都可以被更改
    @ColorInt
    private int highlightColor = Color.TRANSPARENT; // 点击之后的颜色
    private boolean isClickMovement = false; // 文本内容是否有点击活动

    public static SpanGenerates newInstance(@NonNull Context context) {
        return new SpanGenerates(context);
    }

    private SpanGenerates(@NonNull Context context) {
        this.context = context;
        if (null == stringBuilder) {
            stringBuilder = new SpannableStringBuilder();
        }
    }

    public Context getContext() {
        return context;
    }

    private Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 设置 文本
     */
    public SpanGenerates setText(CharSequence text) {
        if (null != stringBuilder) {
            this.stringBuilder.clear();
            this.stringBuilder.append(text);
        }
        return this;
    }

    /**
     * 追加 文本
     */
    public SpanGenerates appendText(CharSequence text) {
        if (null != stringBuilder) {
            this.stringBuilder.append(text);
        }
        return this;
    }

    /**
     * 设置 点击之后 文本的背景
     *
     * @param color 颜色
     */
    public SpanGenerates setHighlightColor(@ColorInt int color) {
        this.highlightColor = color;
        return this;
    }

    /**
     * 设置 点击之后 文本的背景
     *
     * @param colorRes 颜色资源
     */
    public SpanGenerates setHighlightColorRes(@ColorRes int colorRes) {
        return setHighlightColor(getResources().getColor(colorRes));
    }

    /**
     * 设置 文本内容是否具有 点击事件
     *
     * @param clickMovement {@code true} : 有<br/>{@code false} : 反之
     */
    public SpanGenerates setClickMovement(boolean clickMovement) {
        isClickMovement = clickMovement;
        return this;
    }

    /**
     * 用指定的对象标记指定的文本范围
     *
     * @param what  对象
     * @param start 开始位置
     * @param end   结束位置
     * @param flags 标记<ul>
     *              <li>{@link Spannable#SPAN_EXCLUSIVE_INCLUSIVE}：在 Span前面输入的字符不应用 Span的效果，在后面输入的字符应用Span效果。</li>
     *              <li>{@link Spannable#SPAN_INCLUSIVE_EXCLUSIVE}：在 Span前面输入的字符应用 Span 的效果，在后面输入的字符不应用Span效果。</li>
     *              <li>{@link Spannable#SPAN_INCLUSIVE_INCLUSIVE}：在 Span前后输入的字符都应用 Span 的效果。</li>
     *              <li>{@link Spannable#SPAN_EXCLUSIVE_EXCLUSIVE}：前后都不包括。</li>
     *              </ul>
     */
    public SpanGenerates setTextSpan(Object what, int start, int end, int flags) {
        this.stringBuilder.setSpan(what, start, end, flags);
        return this;
    }

    /**
     * 用指定的对象标记指定的文本范围
     *
     * @param what  对象
     * @param start 开始位置
     * @param end   结束位置
     */
    public SpanGenerates setTextSpan(Object what, int start, int end) {
        this.stringBuilder.setSpan(what, start, end, DEFAULT_SPAN_FLAG);
        return this;
    }

    /**
     * 用指定的对象标记 指定位置关键字
     *
     * @param what    对象
     * @param keyword 关键字
     * @param index   第几个关键字 索引
     * @param flags   标记
     */
    public SpanGenerates setTextSpan(Object what, String keyword, int index, int flags) {
        List<int[]> ints = searchKeywordIndexs(keyword);
        int count = null == ints ? 0 : ints.size();
        if (count > index) {
            if (index < 0) {
                for (int i = 0; i < count; i++) {
                    int[] indexs = ints.get(i);
                    if (what instanceof ForegroundColorSpan) {
                        ForegroundColorSpan span = (ForegroundColorSpan) what;
                        setTextSpan(new ForegroundColorSpan(span.getForegroundColor()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof BackgroundColorSpan) {
                        BackgroundColorSpan span = (BackgroundColorSpan) what;
                        setTextSpan(new BackgroundColorSpan(span.getBackgroundColor()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof MaskFilterSpan) {
                        MaskFilterSpan span = (MaskFilterSpan) what;
                        setTextSpan(new MaskFilterSpan(span.getMaskFilter()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof StrikethroughSpan) {
                        // StrikethroughSpan span = (StrikethroughSpan) what;
                        setTextSpan(new StrikethroughSpan(),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof SuggestionSpan) {
                        SuggestionSpan span = (SuggestionSpan) what;
                        setTextSpan(new SuggestionSpan(getContext(), span.getSuggestions(),
                                span.getFlags()), indexs[0], indexs[1], flags);
                    } else if (what instanceof UnderlineSpan) {
                        // UnderlineSpan span = (UnderlineSpan) what;
                        setTextSpan(new UnderlineSpan(), indexs[0], indexs[1], flags);
                    } else if (what instanceof AbsoluteSizeSpan) {
                        AbsoluteSizeSpan span = (AbsoluteSizeSpan) what;
                        setTextSpan(new AbsoluteSizeSpan(span.getSize(), span.getDip()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof DynamicDrawableSpan) {
                        final DynamicDrawableSpan span = (DynamicDrawableSpan) what;
                        setTextSpan(new DynamicDrawableSpan() {
                            @Override
                            public Drawable getDrawable() {
                                return span.getDrawable();
                            }
                        }, indexs[0], indexs[1], flags);
                    } else if (what instanceof ImageSpan) {
                        ImageSpan span = (ImageSpan) what;
                        setTextSpan(new ImageSpan(span.getDrawable(), span.getSource(), span.getVerticalAlignment()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof RelativeSizeSpan) {
                        RelativeSizeSpan span = (RelativeSizeSpan) what;
                        setTextSpan(new RelativeSizeSpan(span.getSizeChange()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof ScaleXSpan) {
                        ScaleXSpan span = (ScaleXSpan) what;
                        setTextSpan(new ScaleXSpan(span.getScaleX()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof StyleSpan) {
                        StyleSpan span = (StyleSpan) what;
                        setTextSpan(new StyleSpan(span.getStyle()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof SubscriptSpan) {
                        // SubscriptSpan span = (SubscriptSpan) what;
                        setTextSpan(new SubscriptSpan(),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof SuperscriptSpan) {
                        // SuperscriptSpan span = (SuperscriptSpan) what;
                        setTextSpan(new SuperscriptSpan(),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof TextAppearanceSpan) {
                        TextAppearanceSpan span = (TextAppearanceSpan) what;
                        setTextSpan(new TextAppearanceSpan(span.getFamily(), span.getTextStyle(), span.getTextSize(), span.getTextColor(), span.getLinkTextColor()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof TypefaceSpan) {
                        TypefaceSpan span = (TypefaceSpan) what;
                        setTextSpan(new TypefaceSpan(span.getFamily()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof URLSpan) {
                        URLSpan span = (URLSpan) what;
                        setTextSpan(new URLSpan(span.getURL()),
                                indexs[0], indexs[1], flags);
                    } else if (what instanceof ClickableSpan) {
                        final ClickableSpan span = (ClickableSpan) what;
                        setTextSpan(new ClickableSpan() {
                            @Override
                            public void onClick(View widget) {
                                span.onClick(widget);
                            }

                            @Override
                            public void updateDrawState(TextPaint ds) {
                                span.updateDrawState(ds);
                            }
                        }, indexs[0], indexs[1], flags);
                    }
                }
            } else {
                int[] indexs = ints.get(index);
                setTextSpan(what, indexs[0], indexs[1], flags);
            }
        }
        return this;
    }

    /**
     * 用指定的对象标记 指定位置关键字
     *
     * @param what    对象
     * @param keyword 关键字
     * @param index   第几个关键字 索引
     */
    public SpanGenerates setTextSpan(Object what, String keyword, int index) {
        return setTextSpan(what, keyword, index, DEFAULT_SPAN_FLAG);
    }

    /**
     * 用指定的对象标记 所有位置关键字
     *
     * @param what    对象
     * @param keyword 关键字
     */
    public SpanGenerates setTextSpan(Object what, String keyword) {
        return setTextSpan(what, keyword, -1);
    }

    /**
     * 用 ForegroundColorSpan 对象标记指定的文本范围（设置文本颜色）
     *
     * @param span  ForegroundColorSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setForegroundColorSpan(ForegroundColorSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setForegroundColor(@ColorInt int color, int start, int end, int flags) {
        return setForegroundColorSpan(new ForegroundColorSpan(color), start, end, flags);
    }

    public SpanGenerates setForegroundColorRes(@ColorRes int colorRes, int start, int end, int flags) {
        return setForegroundColor(getResources().getColor(colorRes), start, end, flags);
    }

    /**
     * 用 ForegroundColorSpan 对象标记指定的文本范围（设置文本颜色）
     *
     * @param span  ForegroundColorSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setForegroundColorSpan(ForegroundColorSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setForegroundColor(@ColorInt int color, int start, int end) {
        return setForegroundColorSpan(new ForegroundColorSpan(color), start, end);
    }

    public SpanGenerates setForegroundColorRes(@ColorRes int colorRes, int start, int end) {
        return setForegroundColor(getResources().getColor(colorRes), start, end);
    }

    public SpanGenerates setForegroundColor(@ColorInt int color, String keyword, int index, int flags) {
        return setTextSpan(new ForegroundColorSpan(color), keyword, index, flags);
    }

    public SpanGenerates setForegroundColorRes(@ColorRes int colorRes, String keyword, int index, int flags) {
        return setForegroundColor(getResources().getColor(colorRes), keyword, index, flags);
    }

    public SpanGenerates setForegroundColor(@ColorInt int color, String keyword, int index) {
        return setTextSpan(new ForegroundColorSpan(color), keyword, index);
    }

    public SpanGenerates setForegroundColorRes(@ColorRes int colorRes, String keyword, int index) {
        return setForegroundColor(getResources().getColor(colorRes), keyword, index);
    }

    public SpanGenerates setForegroundColor(@ColorInt int color, String keyword) {
        return setTextSpan(new ForegroundColorSpan(color), keyword);
    }

    public SpanGenerates setForegroundColorRes(@ColorRes int colorRes, String keyword) {
        return setForegroundColor(getResources().getColor(colorRes), keyword);
    }

    /**
     * 用 BackgroundColorSpan 对象标记指定的文本范围（设置文本背景色）
     *
     * @param span  BackgroundColorSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setBackgroundColorSpan(BackgroundColorSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setBackgroundColor(@ColorInt int color, int start, int end, int flags) {
        return setBackgroundColorSpan(new BackgroundColorSpan(color), start, end, flags);
    }

    public SpanGenerates setBackgroundColorRes(@ColorRes int colorRes, int start, int end, int flags) {
        return setBackgroundColor(getResources().getColor(colorRes), start, end, flags);
    }

    /**
     * 用 BackgroundColorSpan 对象标记指定的文本范围（设置文本背景色）
     *
     * @param span  BackgroundColorSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setBackgroundColorSpan(BackgroundColorSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setBackgroundColor(@ColorInt int color, int start, int end) {
        return setBackgroundColorSpan(new BackgroundColorSpan(color), start, end);
    }

    public SpanGenerates setBackgroundColorRes(@ColorRes int colorRes, int start, int end) {
        return setBackgroundColor(getResources().getColor(colorRes), start, end);
    }

    public SpanGenerates setBackgroundColor(@ColorInt int color, String keyword, int index, int flags) {
        return setTextSpan(new BackgroundColorSpan(color), keyword, index, flags);
    }

    public SpanGenerates setBackgroundColorRes(@ColorRes int colorRes, String keyword, int index, int flags) {
        return setBackgroundColor(getResources().getColor(colorRes), keyword, index, flags);
    }

    public SpanGenerates setBackgroundColor(@ColorInt int color, String keyword, int index) {
        return setTextSpan(new BackgroundColorSpan(color), keyword, index);
    }

    public SpanGenerates setBackgroundColorRes(@ColorRes int colorRes, String keyword, int index) {
        return setBackgroundColor(getResources().getColor(colorRes), keyword, index);
    }

    public SpanGenerates setBackgroundColor(@ColorInt int color, String keyword) {
        return setTextSpan(new BackgroundColorSpan(color), keyword);
    }

    public SpanGenerates setBackgroundColorRes(@ColorRes int colorRes, String keyword) {
        return setBackgroundColor(getResources().getColor(colorRes), keyword);
    }

    /**
     * 用 MaskFilterSpan 对象标记指定的文本范围（设置修饰效果，如模糊(BlurMaskFilter)浮雕）
     *
     * @param span  MaskFilterSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setMaskFilterSpan(MaskFilterSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setMaskFilter(MaskFilter filter, int start, int end, int flags) {
        return setMaskFilterSpan(new MaskFilterSpan(filter), start, end, flags);
    }

    /**
     * 用 MaskFilterSpan 对象标记指定的文本范围（设置修饰效果，如模糊(BlurMaskFilter)浮雕）
     *
     * @param span  MaskFilterSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setMaskFilterSpan(MaskFilterSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setMaskFilter(MaskFilter filter, int start, int end) {
        return setMaskFilterSpan(new MaskFilterSpan(filter), start, end);
    }

    public SpanGenerates setMaskFilter(MaskFilter filter, String keyword, int index, int flags) {
        return setTextSpan(new MaskFilterSpan(filter), keyword, index, flags);
    }

    public SpanGenerates setMaskFilter(MaskFilter filter, String keyword, int index) {
        return setTextSpan(new MaskFilterSpan(filter), keyword, index);
    }

    public SpanGenerates setMaskFilter(MaskFilter filter, String keyword) {
        return setTextSpan(new MaskFilterSpan(filter), keyword);
    }

    /**
     * 用 StrikethroughSpan 对象标记指定的文本范围（设置删除线）
     *
     * @param span  StrikethroughSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setStrikethroughSpan(StrikethroughSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setStrikethrough(int start, int end, int flags) {
        return setStrikethroughSpan(new StrikethroughSpan(), start, end, flags);
    }

    /**
     * 用 StrikethroughSpan 对象标记指定的文本范围
     *
     * @param span  StrikethroughSpan 对象（设置删除线）
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setStrikethroughSpan(StrikethroughSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setStrikethrough(int start, int end) {
        return setStrikethroughSpan(new StrikethroughSpan(), start, end);
    }

    public SpanGenerates setStrikethrough(String keyword, int index, int flags) {
        return setTextSpan(new StrikethroughSpan(), keyword, index, flags);
    }

    public SpanGenerates setStrikethrough(String keyword, int index) {
        return setTextSpan(new StrikethroughSpan(), keyword, index);
    }

    public SpanGenerates setStrikethrough(String keyword) {
        return setTextSpan(new StrikethroughSpan(), keyword);
    }

    /**
     * 用 SuggestionSpan 对象标记指定的文本范围（设置占位符）
     *
     * @param span  SuggestionSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setSuggestionSpan(SuggestionSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    /**
     * 用 SuggestionSpan 对象标记指定的文本范围（设置占位符）
     *
     * @param span  SuggestionSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setSuggestionSpan(SuggestionSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    /**
     * 用 UnderlineSpan 对象标记指定的文本范围（设置下划线）
     *
     * @param span  UnderlineSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setUnderlineSpan(UnderlineSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setUnderline(int start, int end, int flags) {
        return setUnderlineSpan(new UnderlineSpan(), start, end, flags);
    }

    /**
     * 用 UnderlineSpan 对象标记指定的文本范围（设置下划线）
     *
     * @param span  UnderlineSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setUnderlineSpan(UnderlineSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setUnderline(int start, int end) {
        return setUnderlineSpan(new UnderlineSpan(), start, end);
    }

    public SpanGenerates setUnderline(String keyword, int index, int flag) {
        return setTextSpan(new UnderlineSpan(), keyword, index, flag);
    }

    public SpanGenerates setUnderline(String keyword, int index) {
        return setTextSpan(new UnderlineSpan(), keyword, index);
    }

    public SpanGenerates setUnderline(String keyword) {
        return setTextSpan(new UnderlineSpan(), keyword);
    }

    /**
     * 用 AbsoluteSizeSpan 对象标记指定的文本范围（设置文本字体（绝对大小））
     *
     * @param span  AbsoluteSizeSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setAbsoluteSizeSpan(AbsoluteSizeSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setAbsoluteSize(int size, int start, int end, int flags) {
        return setAbsoluteSizeSpan(new AbsoluteSizeSpan(size), start, end, flags);
    }

    /**
     * 用 AbsoluteSizeSpan 对象标记指定的文本范围（设置文本字体（绝对大小））
     *
     * @param span  AbsoluteSizeSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setAbsoluteSizeSpan(AbsoluteSizeSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setAbsoluteSize(int size, int start, int end) {
        return setAbsoluteSizeSpan(new AbsoluteSizeSpan(size), start, end);
    }

    public SpanGenerates setAbsoluteSize(int size, String keyword, int index, int falgs) {
        return setTextSpan(new AbsoluteSizeSpan(size), keyword, index, falgs);
    }

    public SpanGenerates setAbsoluteSize(int size, String keyword, int index) {
        return setTextSpan(new AbsoluteSizeSpan(size), keyword, index);
    }

    public SpanGenerates setAbsoluteSize(int size, String keyword) {
        return setTextSpan(new AbsoluteSizeSpan(size), keyword);
    }

    /**
     * 用 DynamicDrawableSpan 对象标记指定的文本范围（设置图片，基于文本基线或底部对齐）
     *
     * @param span  DynamicDrawableSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setDynamicDrawableSpan(DynamicDrawableSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    /**
     * 用 DynamicDrawableSpan 对象标记指定的文本范围（设置图片，基于文本基线或底部对齐）
     *
     * @param span  DynamicDrawableSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setDynamicDrawableSpan(DynamicDrawableSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    /**
     * 用 ImageSpan 对象标记指定的文本范围（设置图片）
     *
     * @param span  ImageSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setImageSpan(ImageSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    /**
     * 用 ImageSpan 对象标记指定的文本范围（设置图片）
     *
     * @param span  ImageSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setImageSpan(ImageSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    /**
     * 用 RelativeSizeSpan  对象标记指定的文本范围（设置文本字体（相对大小））
     *
     * @param span  RelativeSizeSpan  对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setRelativeSizeSpan(RelativeSizeSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setRelativeSize(float textSize, int start, int end, int flags) {
        return setRelativeSizeSpan(new RelativeSizeSpan(textSize), start, end, flags);
    }

    /**
     * 用 RelativeSizeSpan  对象标记指定的文本范围（设置文本字体（相对大小））
     *
     * @param span  RelativeSizeSpan  对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setRelativeSizeSpan(RelativeSizeSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setRelativeSize(float textSize, int start, int end) {
        return setRelativeSizeSpan(new RelativeSizeSpan(textSize), start, end);
    }

    public SpanGenerates setRelativeSize(float textSize, String keyword, int index, int flags) {
        return setTextSpan(new RelativeSizeSpan(textSize), keyword, index, flags);
    }

    public SpanGenerates setRelativeSize(float textSize, String keyword, int index) {
        return setTextSpan(new RelativeSizeSpan(textSize), keyword, index);
    }

    public SpanGenerates setRelativeSize(float textSize, String keyword) {
        return setTextSpan(new RelativeSizeSpan(textSize), keyword);
    }

    /**
     * 用 ScaleXSpan 对象标记指定的文本范围（基于x轴缩放）
     *
     * @param span  ScaleXSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setScaleXSpan(ScaleXSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setScaleX(float scaleX, int start, int end, int flags) {
        return setScaleXSpan(new ScaleXSpan(scaleX), start, end, flags);
    }

    /**
     * 用 ScaleXSpan 对象标记指定的文本范围（基于x轴缩放）
     *
     * @param span  ScaleXSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setScaleXSpan(ScaleXSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setScaleX(float scaleX, int start, int end) {
        return setScaleXSpan(new ScaleXSpan(scaleX), start, end);
    }

    public SpanGenerates setScaleX(float scaleX, String keyword, int index, int flags) {
        return setTextSpan(new ScaleXSpan(scaleX), keyword, index, flags);
    }

    public SpanGenerates setScaleX(float scaleX, String keyword, int index) {
        return setTextSpan(new ScaleXSpan(scaleX), keyword, index);
    }

    public SpanGenerates setScaleX(float scaleX, String keyword) {
        return setTextSpan(new ScaleXSpan(scaleX), keyword);
    }

    /**
     * 用 StyleSpan 对象标记指定的文本范围（设置字体样式：粗体、斜体等）
     *
     * @param span  StyleSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setStyleSpan(StyleSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setStyle(int style, int start, int end, int flags) {
        return setTextSpan(new StyleSpan(style), start, end, flags);
    }

    /**
     * 用 StyleSpan 对象标记指定的文本范围（设置字体样式：粗体、斜体等）
     *
     * @param span  StyleSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setStyleSpan(StyleSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setStyle(int style, int start, int end) {
        return setTextSpan(new StyleSpan(style), start, end);
    }

    public SpanGenerates setStyle(int style, String keyword, int index, int flags) {
        return setTextSpan(new StyleSpan(style), keyword, index, flags);
    }

    public SpanGenerates setStyle(int style, String keyword, int index) {
        return setTextSpan(new StyleSpan(style), keyword, index);
    }

    public SpanGenerates setStyle(int style, String keyword) {
        return setTextSpan(new StyleSpan(style), keyword);
    }

    /**
     * 用 SubscriptSpan 对象标记指定的文本范围（设置下标（数学公式会用到））
     *
     * @param span  SubscriptSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setSubscriptSpan(SubscriptSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setSubscript(int start, int end, int flags) {
        return setSubscriptSpan(new SubscriptSpan(), start, end, flags);
    }

    /**
     * 用 SubscriptSpan 对象标记指定的文本范围（设置下标（数学公式会用到））
     *
     * @param span  SubscriptSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setSubscriptSpan(SubscriptSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setSubscript(int start, int end) {
        return setSubscriptSpan(new SubscriptSpan(), start, end);
    }

    public SpanGenerates setSubscript(String keyword, int index, int flags) {
        return setTextSpan(new SubscriptSpan(), keyword, index, flags);
    }

    public SpanGenerates setSubscript(String keyword, int index) {
        return setTextSpan(new SubscriptSpan(), keyword, index);
    }

    public SpanGenerates setSubscript(String keyword) {
        return setTextSpan(new SubscriptSpan(), keyword);
    }

    /**
     * 用 SuperscriptSpan 对象标记指定的文本范围（设置上标（数学公式会用到））
     *
     * @param span  SuperscriptSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setSuperscriptSpan(SuperscriptSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setSuperscript(int start, int end, int flags) {
        return setSuperscriptSpan(new SuperscriptSpan(), start, end, flags);
    }

    /**
     * 用 SuperscriptSpan 对象标记指定的文本范围（设置上标（数学公式会用到））
     *
     * @param span  SuperscriptSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setSuperscriptSpan(SuperscriptSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setSuperscript(int start, int end) {
        return setSuperscriptSpan(new SuperscriptSpan(), start, end);
    }

    public SpanGenerates setSuperscript(String keyword, int index, int flags) {
        return setTextSpan(new SuperscriptSpan(), keyword, index, flags);
    }

    public SpanGenerates setSuperscript(String keyword, int index) {
        return setTextSpan(new SuperscriptSpan(), keyword, index);
    }

    public SpanGenerates setSuperscript(String keyword) {
        return setTextSpan(new SuperscriptSpan(), keyword);
    }

    /**
     * 用 TextAppearanceSpan 对象标记指定的文本范围（设置文本外貌（包括字体、大小、样式和颜色））
     *
     * @param span  TextAppearanceSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setTextAppearanceSpan(TextAppearanceSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    /**
     * 用 TextAppearanceSpan 对象标记指定的文本范围（设置文本外貌（包括字体、大小、样式和颜色））
     *
     * @param span  TextAppearanceSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setTextAppearanceSpan(TextAppearanceSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    /**
     * 用 TypefaceSpan 对象标记指定的文本范围（设置文本字体）
     *
     * @param span  TypefaceSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setTypefaceSpan(TypefaceSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setTypeface(String family, int start, int end, int flags) {
        return setTypefaceSpan(new TypefaceSpan(family), start, end, flags);
    }

    /**
     * 用 TypefaceSpan 对象标记指定的文本范围（设置文本字体）
     *
     * @param span  TypefaceSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setTypefaceSpan(TypefaceSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setTypeface(String family, int start, int end) {
        return setTypefaceSpan(new TypefaceSpan(family), start, end);
    }

    public SpanGenerates setTypeface(String family, String keyword, int index, int flags) {
        return setTextSpan(new TypefaceSpan(family), keyword, index, flags);
    }

    public SpanGenerates setTypeface(String family, String keyword, int index) {
        return setTextSpan(new TypefaceSpan(family), keyword, index);
    }

    public SpanGenerates setTypeface(String family, String keyword) {
        return setTextSpan(new TypefaceSpan(family), keyword);
    }

    /**
     * 用 URLSpan 对象标记指定的文本范围（设置文本超链接）
     *
     * @param span  URLSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setURLSpan(URLSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    public SpanGenerates setURL(String url, int start, int end, int flags) {
        return setURLSpan(new URLSpan(url), start, end, flags);
    }

    /**
     * 用 URLSpan 对象标记指定的文本范围（设置文本超链接）
     *
     * @param span  URLSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setURLSpan(URLSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public SpanGenerates setURL(String url, int start, int end) {
        return setURLSpan(new URLSpan(url), start, end);
    }

    public SpanGenerates setURL(String url, String keyword, int index, int flags) {
        return setTextSpan(new URLSpan(url), keyword, index, flags);
    }

    public SpanGenerates setURL(String url, String keyword, int index) {
        return setTextSpan(new URLSpan(url), keyword, index);
    }

    public SpanGenerates setURL(String url, String keyword) {
        return setTextSpan(new URLSpan(url), keyword);
    }

    /**
     * 用 ClickableSpan 对象标记指定的文本范围（设置文本点击事件）
     *
     * @param span  ClickableSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     * @param flags 标记
     */
    public SpanGenerates setClickableSpan(ClickableSpan span, int start, int end, int flags) {
        return setTextSpan(span, start, end, flags);
    }

    /**
     * 用 ClickableSpan 对象标记指定的文本范围（设置文本点击事件）
     *
     * @param span  ClickableSpan 对象
     * @param start 开始位置索引
     * @param end   结束位置索引
     */
    public SpanGenerates setClickableSpan(ClickableSpan span, int start, int end) {
        return setTextSpan(span, start, end);
    }

    public <V extends TextView> void into(V textView) {
        textView.setHighlightColor(highlightColor);
        if (isClickMovement) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        textView.setText(stringBuilder);
    }

    /**
     * 查询字符串中的所有关键字的位置
     *
     * @param keyword 关键字
     * @return 关键字位置的集合 >>> int[2]
     */
    public List<int[]> searchKeywordIndexs(String keyword) {
        List<int[]> indexList = new ArrayList<>();
        String text = stringBuilder.toString();
        if (TextUtils.isEmpty(text)) {
            return indexList;
        }
        // 获取第一个 keyword 的起始位置
        int index = text.indexOf(keyword);
        // 获取 keyword 总长度
        int length = keyword.length();
        while (index != -1) {
            // 加入 索引 集合 int[0] >>> start ; int[1]  >>>  end
            indexList.add(new int[]{index, index + length});
            // 获取下一个 keyword 起始位置
            index = text.indexOf(keyword, index + 1);
        }
        return indexList;
    }
}