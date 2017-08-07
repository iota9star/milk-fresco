package f.star.iota.milk.util;


import android.content.Context;
import android.content.SharedPreferences;

import f.star.iota.milk.LockType;
import f.star.iota.milk.R;
import f.star.iota.milk.SourceType;

public class ConfigUtils {

    public static int getTheme(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt("theme", R.style.ThemeLime);
    }

    public static void saveTheme(Context context, int theme) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("theme", theme);
        edit.apply();
    }

    public static String getBanner(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString("banner", "");
    }

    public static void saveBanner(Context context, String url) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("banner", url);
        edit.apply();
    }

    public static int getCurrentSpanCount(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt("span_count", 2);
    }

    public static void saveCurrentSpanCount(Context context, int spanCount) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("span_count", spanCount);
        edit.apply();
    }

    public static int getSpanCountConfig(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt("span_count_config", 3);
    }

    public static void saveSpanCountConfig(Context context, int spanCount) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("span_count_config", spanCount);
        edit.apply();
    }

    public static int getDownloadCountConfig(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt("download_count_config", 3);
    }

    public static void saveDownloadCountConfig(Context context, int count) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("download_count_config", count);
        edit.apply();
    }

    public static boolean getR(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean("r", true);
    }

    public static void saveR(Context context, boolean r) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("r", r);
        edit.apply();
    }

    public static long getOpenCount(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getLong("open_count", 0);
    }

    public static void saveOpenCount(Context context, long count) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong("open_count", count);
        edit.apply();
    }

    public static String getPin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString("pin", "");
    }

    public static void savePin(Context context, String pin) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("pin", pin);
        edit.apply();
    }

    public static int isLock(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getInt("lock", LockType.NONE);
    }

    public static void setLock(Context context, int lock) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("lock", lock);
        edit.apply();
    }

    public static boolean isOpenFingerprint(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean("fingerprint_open", false);
    }

    public static void openFingerprint(Context context, boolean open) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("fingerprint_open", open);
        edit.apply();
    }

    public static boolean isShowDonation(Context context) {
        SharedPreferences sp = context.getSharedPreferences("donation_config", Context.MODE_PRIVATE);
        return sp.getBoolean("show_donation", true);
    }

    public static void saveDonationStatus(Context context, boolean show) {
        SharedPreferences sp = context.getSharedPreferences("donation_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("show_donation", show);
        edit.apply();
    }

    public static int getTodayInHistroyInterval(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        return sp.getInt("today_in_history_interval", 30);
    }

    public static void saveTodayInHistroyInterval(Context context, int interval) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("today_in_history_interval", interval);
        edit.apply();
    }

    public static int getJuziInterval(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        return sp.getInt("juzi_interval", 30);
    }

    public static void saveJuziInterval(Context context, int interval) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("juzi_interval", interval);
        edit.apply();
    }

    public static int getWidgetBannerSource(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        return sp.getInt("widget_banner_source", SourceType.APIC);
    }

    public static void saveWidgetBannerSource(Context context, int type) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("widget_banner_source", type);
        edit.apply();
    }

    public static int getSplashSource(Context context) {
        SharedPreferences sp = context.getSharedPreferences("splash_config", Context.MODE_PRIVATE);
        return sp.getInt("splash_background_source", SourceType.APIC);
    }

    public static void saveSplashSource(Context context, int type) {
        SharedPreferences sp = context.getSharedPreferences("splash_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("splash_background_source", type);
        edit.apply();
    }
}
