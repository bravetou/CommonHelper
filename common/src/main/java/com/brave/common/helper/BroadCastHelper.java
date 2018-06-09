package com.brave.common.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;

import com.brave.common.base.CommonApplication;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ： Broadcast 管理帮助类
 */
public class BroadCastHelper {
    public static final String DATA = "BROAD_CAST_HELPER_BRAVE"; // 广播数据传递键
    private volatile static BroadCastHelper mInstance;
    private Map<String, Map<String, BroadcastReceiver>> receiverMap;

    public static BroadCastHelper getInstance() {
        if (null == mInstance) {
            synchronized (BroadCastHelper.class) {
                if (null == mInstance) {
                    mInstance = new BroadCastHelper();
                }
            }
        }
        return mInstance;
    }

    private BroadCastHelper() {
        receiverMap = new LinkedHashMap<>();
    }

    public Context getContext() {
        return CommonApplication.getContext();
    }

    /**
     * 为指定 action 发送动态广播消息
     */
    public void sendBroadcast(String action, Object obj) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (obj instanceof Integer) {
            intent.putExtra(DATA, (Integer) obj);
        } else if (obj instanceof Float) {
            intent.putExtra(DATA, (Float) obj);
        } else if (obj instanceof Double) {
            intent.putExtra(DATA, (Double) obj);
        } else if (obj instanceof Character) {
            intent.putExtra(DATA, (Character) obj);
        } else if (obj instanceof Byte) {
            intent.putExtra(DATA, (Byte) obj);
        } else if (obj instanceof Boolean) {
            intent.putExtra(DATA, (Boolean) obj);
        } else if (obj instanceof String) {
            intent.putExtra(DATA, (String) obj);
        } else if (obj instanceof Long) {
            intent.putExtra(DATA, (Long) obj);
        } else if (obj instanceof Short) {
            intent.putExtra(DATA, (Short) obj);
        } else if (obj instanceof Serializable) {
            intent.putExtra(DATA, (Serializable) obj);
        } else if (obj instanceof Parcelable) {
            intent.putExtra(DATA, (Parcelable) obj);
        } else if (obj instanceof Bundle) {
            intent.putExtra(DATA, (Bundle) obj);
        } else if (obj instanceof Parcelable[]) {
            intent.putExtra(DATA, (Parcelable[]) obj);
        } else if (obj instanceof boolean[]) {
            intent.putExtra(DATA, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            intent.putExtra(DATA, (byte[]) obj);
        } else if (obj instanceof char[]) {
            intent.putExtra(DATA, (char[]) obj);
        } else if (obj instanceof double[]) {
            intent.putExtra(DATA, (double[]) obj);
        } else if (obj instanceof float[]) {
            intent.putExtra(DATA, (float[]) obj);
        } else if (obj instanceof int[]) {
            intent.putExtra(DATA, (int[]) obj);
        } else if (obj instanceof String[]) {
            intent.putExtra(DATA, (String[]) obj);
        } else if (obj instanceof long[]) {
            intent.putExtra(DATA, (long[]) obj);
        } else if (obj instanceof short[]) {
            intent.putExtra(DATA, (short[]) obj);
        } else {
            intent.putExtra(DATA, String.valueOf(obj));
        }
        getContext().sendBroadcast(intent);
    }

    /**
     * 动态注册广播并监听
     */
    public void addAction(String action, BroadcastReceiver receiver) {
        Map<String, BroadcastReceiver> map;
        if (receiverMap.containsKey(action)) {
            map = this.receiverMap.get(action);
        } else {
            map = new LinkedHashMap<>();
        }
        String name = receiver.getClass().getName(); // com.brave.common.helper.BroadCastHelper$BroadcastReceiver
        // 获取当前广播所在类的全类名
        String qualifiedName = name.substring(0, name.indexOf("$"));
        if (map.containsKey(qualifiedName)) {
            // 一个类中有且只能注册同一 Action 一次
            return;
        }
        // 常规步奏动态注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(action);
        getContext().registerReceiver(receiver, filter);
        // 动态注册广播后，将其加入广播集合
        map.put(qualifiedName, receiver);
        receiverMap.put(action, map);
    }

    /**
     * 销毁动态注册的广播
     */
    public void destroy(Object cls, String action) {
        if (receiverMap.containsKey(action)) {
            Map<String, BroadcastReceiver> map = this.receiverMap.get(action);
            String qualifiedName = cls.getClass().getName();
            if (map.containsKey(qualifiedName)) {
                BroadcastReceiver receiver = map.remove(qualifiedName);
                if (null != receiver) {
                    getContext().unregisterReceiver(receiver);
                }
            }
        }
    }
}