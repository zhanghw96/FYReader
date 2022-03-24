package xyz.fycz.myreader.util;

import android.content.Context;
import android.content.SharedPreferences;

import xyz.fycz.myreader.application.App;

import java.util.Map;

/**
 * SharedPreferences工具类
 */

public class SharedPreUtils {
    public static final String SHARED_NAME = "FYReader_pref";
    public static final String AD_SHARED_NAME = "FYReader_ad_pref";
    private static SharedPreUtils sInstance;
    private final SharedPreferences[] sharedReadables;
    private SharedPreferences sharedReadable;
    private SharedPreferences.Editor sharedWritable;

    private SharedPreUtils() {
        sharedReadables = new SharedPreferences[]{
                App.getmContext().getSharedPreferences(SHARED_NAME, Context.MODE_MULTI_PROCESS),
                App.getmContext().getSharedPreferences(AD_SHARED_NAME, Context.MODE_MULTI_PROCESS)
        };
    }

    //不能
    public static SharedPreUtils getInstance() {
        return getInstance(false);
    }

    public static SharedPreUtils getInstance(boolean ad) {
        if (sInstance == null) {
            synchronized (SharedPreUtils.class) {
                if (sInstance == null) {
                    sInstance = new SharedPreUtils();
                }
            }
        }
        sInstance.sharedReadable = sInstance.sharedReadables[!ad ? 0 : 1];
        sInstance.sharedWritable = sInstance.sharedReadable.edit();
        return sInstance;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedReadable;
    }

    public void putString(String key, String value) {
        sharedWritable.putString(key, value);
        sharedWritable.apply();
    }

    public void putInt(String key, int value) {
        sharedWritable.putInt(key, value);
        sharedWritable.apply();
    }

    public void putBoolean(String key, boolean value) {
        sharedWritable.putBoolean(key, value);
        sharedWritable.apply();
    }

    public void putFloat(String key, float value) {
        sharedWritable.putFloat(key, value);
        sharedWritable.apply();
    }

    public void putLong(String key, long value) {
        sharedWritable.putLong(key, value);
        sharedWritable.apply();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String def) {
        return sharedReadable.getString(key, def);
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return sharedReadable.getInt(key, def);
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedReadable.getBoolean(key, def);
    }

    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    public float getFloat(String key, float def) {
        return sharedReadable.getFloat(key, def);
    }

    public long getLong(String key) {
        return getLong(key, 0);
    }

    public long getLong(String key, long def) {
        return sharedReadable.getLong(key, def);
    }

    public void remove(String key) {
        sharedWritable.remove(key).apply();
    }

    public Map<String, ?> getAll() {
        return sharedReadable.getAll();
    }

}
