package f.star.iota.milk.config;

import android.content.Context;
import android.content.SharedPreferences;

import f.star.iota.milk.SourceType;

public class WidgetConfig {
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

    public static boolean isOnlyWiFiLoad(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        return sp.getBoolean("is_wifi_load", false);
    }

    public static void isOnlyWiFiLoad(Context context, boolean isLoad) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("is_wifi_load", isLoad);
        edit.apply();
    }

    public static boolean isPauseRefresh(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        return sp.getBoolean("is_pause_refresh", false);
    }

    public static void isPauseRefresh(Context context, boolean refresh) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("is_pause_refresh", refresh);
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
        SharedPreferences sp = context.getSharedPreferences("widget_banner_config", Context.MODE_PRIVATE);
        return sp.getInt("widget_banner_source", SourceType.APIC);
    }

    public static void saveWidgetBannerSource(Context context, int type) {
        SharedPreferences sp = context.getSharedPreferences("widget_banner_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("widget_banner_source", type);
        edit.apply();
    }

    public static int getWidgetBlurValue(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_blur_config", Context.MODE_PRIVATE);
        return sp.getInt("widget_blur_value", 96);
    }

    public static void saveWidgetBlurValue(Context context, int value) {
        SharedPreferences sp = context.getSharedPreferences("widget_blur_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("widget_blur_value", value);
        edit.apply();
    }

    public static void isBlurWallpaper(Context context, boolean isBlur) {
        SharedPreferences sp = context.getSharedPreferences("widget_wallpaper_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("widget_wallpaper_is_blur", isBlur);
        edit.apply();
    }

    public static boolean isBlurWallpaper(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_wallpaper_config", Context.MODE_PRIVATE);
        return sp.getBoolean("widget_wallpaper_is_blur", true);
    }

    public static void isSetWallpaper(Context context, boolean isSet) {
        SharedPreferences sp = context.getSharedPreferences("widget_wallpaper_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("widget_wallpaper_is_set", isSet);
        edit.apply();
    }

    public static boolean isSetWallpaper(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_wallpaper_config", Context.MODE_PRIVATE);
        return sp.getBoolean("widget_wallpaper_is_set", false);
    }

    public static void isBlurWidgetBanner(Context context, boolean isBlur) {
        SharedPreferences sp = context.getSharedPreferences("widget_blur_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("widget_banner_is_blur", isBlur);
        edit.apply();
    }

    public static boolean isBlurWidgetBanner(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_blur_config", Context.MODE_PRIVATE);
        return sp.getBoolean("widget_banner_is_blur", false);
    }

    public static void isSaveWidgetBanner(Context context, boolean isSave) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("widget_banner_is_save", isSave);
        edit.apply();
    }

    public static boolean isSaveWidgetBanner(Context context) {
        SharedPreferences sp = context.getSharedPreferences("widget_config", Context.MODE_PRIVATE);
        return sp.getBoolean("widget_banner_is_save", false);
    }
}
