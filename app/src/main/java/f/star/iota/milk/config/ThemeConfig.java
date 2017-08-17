package f.star.iota.milk.config;


import android.content.Context;
import android.content.SharedPreferences;

import f.star.iota.milk.R;

public class ThemeConfig {
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
}
