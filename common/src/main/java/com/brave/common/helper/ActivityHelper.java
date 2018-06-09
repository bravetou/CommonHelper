package com.brave.common.helper;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>author</b> ： brave tou <br/>
 * <b>createTime</b> ： 2018/6/9 <br/>
 * <b>description</b> ： Activity 管理帮助类
 */
public class ActivityHelper {
    /**
     * 储存还存活的 Activity 的集合
     */
    private List<Activity> aList;

    private static class SingletonHolder {
        private volatile static ActivityHelper mInstance = new ActivityHelper();
    }

    public static ActivityHelper getInstance() {
        return SingletonHolder.mInstance;
    }

    private ActivityHelper() {
        aList = new ArrayList<>();
    }

    /**
     * 是否存在该 Activity
     */
    public boolean hasActivity(Activity activity) {
        return aList.contains(activity);
    }

    /**
     * 把指定 Activity 加入存活集合中
     */
    public void addActivity(Activity activity) {
        if (!hasActivity(activity)) {
            aList.add(activity);
        }
    }

    /**
     * 把指定 Activity 从存活集合中移除并杀死
     */
    public void removeActivity(Activity activity) {
        if (aList.isEmpty()) {
            return;
        }
        if (hasActivity(activity)) {
            aList.remove(activity); // 把当前Activity从集合中移除
            if (!activity.isFinishing()) { // 当前Activity不在销毁过程中
                activity.finish(); // 销毁当前Activity
            }
        }
    }

    /**
     * 清空并杀死所有存活 Activity
     */
    public void clearActivity() {
        if (aList.isEmpty()) {
            return;
        }
        int count = null == aList ? 0 : aList.size();
        for (int i = 0; i < count; i++) {
            Activity activity = aList.get(i);
            if (!activity.isFinishing()) { // 当前Activity不在销毁过程中
                activity.finish(); // 销毁当前Activity
            }
        }
        aList.clear();
    }

    /**
     * 保留指定 Activity ，并从存活集合中移除并杀死其他 Activity
     */
    public void keepActivity(Activity a) {
        if (aList.isEmpty()) {
            return;
        }
        int count = null == aList ? 0 : aList.size();
        for (int i = 0; i < count; i++) {
            Activity activity = aList.get(i);
            if (a != activity) { // 如果不是需要保活的 Activity
                if (!activity.isFinishing()) { // 当前Activity不在销毁过程中
                    activity.finish(); // 销毁当前Activity
                }
            }
        }
        aList.clear();
        aList.add(a);
    }

    /**
     * 保留指定的首个Class<? extends Activity> & 清空其他Activity
     */
    public void keepActivity(Class<? extends Activity> cls) {
        if (aList.isEmpty()) {
            return;
        }
        Activity a = null;
        int count = null == aList ? 0 : aList.size();
        for (int i = 0; i < count; i++) {
            Activity activity = aList.get(i);
            if (null == a && activity.getClass().equals(cls)) {
                a = activity;
            } else if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        aList.clear();
        if (null != a) {
            aList.add(a);
        }
    }

    /**
     * 回退指定数量的存活 Activity 栈
     */
    public void rollbackActivity(int num) {
        if (num <= 0) {
            return;
        }
        int count = null == aList ? 0 : aList.size();
        if (num > count) {
            clearActivity();
            return;
        }
        for (int i = 0; i < num; i++) {
            Activity activity = aList.get((count - 1) - i);
            aList.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}