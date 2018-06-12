package com.brave.common.utils.io;

import android.content.Context;
import android.content.SharedPreferences;

import com.brave.common.CommonConfig;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/12 <br/>
 * <b>description</b> ： SharedPreferences 工具类
 */
public class SPUtils {
    private static final int defaultMode = Context.MODE_PRIVATE;
    private static final String defaultName = "brave_tou"; // 默认 xml 文件名
    private SharedPreferences sharedPreferences = null; // xml 文件
    private SharedPreferences.Editor editor = null; // xml 内容

    private static final Map<String, SPUtils> utilsMap = new LinkedHashMap<>(); // 键值对储存 xml

    private Context getContext() {
        return CommonConfig.getInstance().getContext();
    }

    public static SPUtils getInstance() {
        return getInstance(defaultName, defaultMode);
    }

    public static SPUtils getInstance(String name) {
        return getInstance(name, defaultMode);
    }

    public static SPUtils getInstance(String name, int mode) {
        // 先获取 SharedPreferences 实例
        SPUtils utils = utilsMap.get(name + "_" + mode);
        // 如果获取不到，则重新创建
        if (utils == null) {
            utils = new SPUtils(name, mode);
            utilsMap.put(name + "_" + mode, utils);
        }
        return utils;
    }

    private SPUtils(String name, int mode) {
        // TODO: [record] SharedPreferences储存机制
        Context context = getContext();
        if (sharedPreferences == null) {
            // TODO: [record] 获取SharedPreferences储存机制，若无则建立
            sharedPreferences = context.getSharedPreferences(name, mode);
        }
        if (editor == null) {
            editor = sharedPreferences.edit();
        }
    }

    // TODO: [record] 提交键值对存入SharedPreferences储存机制
    public SPUtils put(String key, Object value) {
        if (null != editor) {
            if (value instanceof Integer) {
                editor.putInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value instanceof Float) {
                editor.putFloat(key, (Float) value);
            } else if (value instanceof Long) {
                editor.putLong(key, (Long) value);
            } else if (value instanceof Set) {
                editor.putStringSet(key, (Set<String>) value);
            } else {//若不能识别或者为String类型全存为String类型
                editor.putString(key, (String) value);
            }
            editor.apply();
        }
        return this;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出String值
    public String getString(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getString(key, "");
        }
        return null;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public int getInt(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getInt(key, -1);
        }
        return -1;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public boolean getBoolean(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public float getFloat(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getFloat(key, -1f);
        }
        return -1f;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出int值
    public long getLong(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getLong(key, -1);
        }
        return -1;
    }

    // TODO: [record] 从SharedPreferences储存机制中取出Set<String>值
    public Set<String> getStringSet(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.getStringSet(key, null);
        }
        return null;
    }

    // TODO: [record] 从SharedPreferences储存机制中移除 key
    public SPUtils remove(String key) {
        if (null != editor) {
            editor.remove(key);
        }
        return this;
    }

    // TODO: [record] 查询SharedPreferences储存机制中存在 key
    public boolean contains(String key) {
        if (null != sharedPreferences) {
            return sharedPreferences.contains(key);
        }
        return false;
    }

    // TODO: [record] 返回SharedPreferences储存机制中所有的键值对
    public Map<String, ?> getAll() {
        if (null != sharedPreferences) {
            return sharedPreferences.getAll();
        }
        return null;
    }

    // TODO: [record] 清除SharedPreferences储存机制中储存的所有值
    public void clear() {
        if (null != editor) {
            editor.clear().commit();
        }
    }
}