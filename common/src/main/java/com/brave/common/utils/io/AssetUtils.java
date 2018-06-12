package com.brave.common.utils.io;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;

import com.brave.common.CommonConfig;

import java.io.IOException;
import java.io.InputStream;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： assets 工具类
 */
public final class AssetUtils {
    private static Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    private static InputStream getInputStream(String fileName) {
        return getContext().getClass().getClassLoader().getResourceAsStream("assets/" + fileName);
    }

    /**
     * 获取 assets 目录下的图片
     *
     * @param fileName 图片资源全名
     * @return bitmap
     */
    public static Bitmap getImageBitmap(@NonNull String fileName) {
        Bitmap image = null;
        try {
            InputStream is = getInputStream(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 获取 assets 目录下文件的文本内容
     *
     * @param fileName 文本资源全名
     * @return 文本内容
     */
    public static String getFileText(@NonNull String fileName) {
        String result = null;
        try {
            InputStream is = getInputStream(fileName);
            int lenght = is.available();
            byte[] buffer = new byte[lenght];
            is.read(buffer);
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取 assets 目录下文件的音频资源
     *
     * @param fileName 音频资源全名
     * @return
     */
    public static MediaPlayer getMediaPlayer(@NonNull String fileName) {
        MediaPlayer player = new MediaPlayer();
        try {
            // 打开指定音乐文件,获取assets目录下指定文件的AssetFileDescriptor对象
            AssetFileDescriptor fileDescriptor = getContext().getResources().getAssets().openFd(fileName);
            // 使用MediaPlayer加载指定的声音文件。
            player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
                    fileDescriptor.getStartOffset());
            // 准备声音
            player.prepare();
            // 播放
            // player.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    /**
     * 获取 assets 目录下文件的可使用路径
     *
     * @param fileName 文件全名
     * @return 可用路径
     */
    public static String getFilePath(@NonNull String fileName) {
        return "file:///android_asset/" + fileName;
    }
}