package com.commonsdk.sharedpreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * 对SharedPreference的使用做了建议的封装，对外公布出put，get，remove，clear等等方法；
 * 注意一点，里面所有的commit操作使用了SharedPreferencesCompat
 * .apply进行了替代，目的是尽可能的使用apply代替commit
 * <p>
 * 首先说下为什么，因为commit方法是同步的，并且我们很多时候的commit操作都是UI线程中，毕竟是IO操作，尽可能异步；
 * <p>
 * 所以我们使用apply进行替代，apply异步的进行写入；
 * <p>
 * 但是apply相当于commit来说是new API呢，为了更好的兼容，我们做了适配；
 *
 * @author 蛟
 */
public class SharedPreferenceHelper {
    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context 句柄
     * @param key     key
     * @param object  保存的内容
     */
    @SuppressLint("NewApi")
    public static void put(Context context, String key, Object object) {
        if (object == null || context == null || TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof Set<?>) {
            editor.putStringSet(key, (Set<String>) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context       句柄
     * @param key           key
     * @param defaultObject 默认值
     * @return 保存的内容
     */
    @SuppressLint("NewApi")
    public static Object get(Context context, String key, Object defaultObject) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else if (defaultObject instanceof Set<?>) {
            return sp.getStringSet(key, (Set<String>) defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context 句柄
     * @param key     移除某个key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context 句柄
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context 句柄
     * @param key     被查询的key
     * @return 是否存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context 句柄
     * @return 所有的键值对
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author ztx
     */
    private static class SharedPreferencesCompat {
        /**
         * Method
         */
        private static final Method SAPPLYMETHOD = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return Method
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor SharedPreferences.Editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (SAPPLYMETHOD != null) {
                    SAPPLYMETHOD.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }
}
