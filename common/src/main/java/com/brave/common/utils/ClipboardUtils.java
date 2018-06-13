package com.brave.common.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.brave.common.CommonConfig;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： 剪贴板 相关工具类
 */
public final class ClipboardUtils {
    private ClipboardUtils() {
        throw new RuntimeException("cannot be instantiated");
    }

    private static Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    /**
     * 获取 ClipboardManager 对象
     */
    public static ClipboardManager getClipboardManager() {
        return (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * 复制文本到剪贴板
     *
     * @param text 文本
     */
    public static void copyText(CharSequence text) {
        getClipboardManager().setPrimaryClip(ClipData.newPlainText("text", text));
    }

    /**
     * 获取剪贴板的文本
     *
     * @return 剪贴板的文本
     */
    public static CharSequence getText() {
        ClipData clip = getClipboardManager().getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(getContext());
        }
        return null;
    }

    /**
     * 复制uri到剪贴板
     *
     * @param uri uri
     */
    public static void copyUri(Uri uri) {
        getClipboardManager().setPrimaryClip(ClipData.newUri(getContext().getContentResolver(), "uri", uri));
    }

    /**
     * 获取剪贴板的 uri
     *
     * @return 剪贴板的 uri
     */
    public static Uri getUri() {
        ClipData clip = getClipboardManager().getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * 复制意图到剪贴板
     *
     * @param intent 意图
     */
    public static void copyIntent(final Intent intent) {
        getClipboardManager().setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    /**
     * 获取剪贴板的意图
     *
     * @return 剪贴板的意图
     */
    public static Intent getIntent() {
        ClipData clip = getClipboardManager().getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getIntent();
        }
        return null;
    }
}