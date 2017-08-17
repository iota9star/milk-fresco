package f.star.iota.milk.config;


import android.content.Context;
import android.content.SharedPreferences;

import f.star.iota.milk.SourceType;

public class SplashConfig {
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
