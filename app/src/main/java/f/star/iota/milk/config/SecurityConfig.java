package f.star.iota.milk.config;

import android.content.Context;
import android.content.SharedPreferences;

import f.star.iota.milk.LockType;

public class SecurityConfig {
    public static String getPin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("security_config", Context.MODE_PRIVATE);
        return sp.getString("pin", "");
    }

    public static void savePin(Context context, String pin) {
        SharedPreferences sp = context.getSharedPreferences("security_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("pin", pin);
        edit.apply();
    }

    public static int isLock(Context context) {
        SharedPreferences sp = context.getSharedPreferences("security_config", Context.MODE_PRIVATE);
        return sp.getInt("lock", LockType.NONE);
    }

    public static void setLock(Context context, int lock) {
        SharedPreferences sp = context.getSharedPreferences("security_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("lock", lock);
        edit.apply();
    }

    public static boolean isOpenFingerprint(Context context) {
        SharedPreferences sp = context.getSharedPreferences("security_config", Context.MODE_PRIVATE);
        return sp.getBoolean("fingerprint_open", false);
    }

    public static void openFingerprint(Context context, boolean open) {
        SharedPreferences sp = context.getSharedPreferences("security_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("fingerprint_open", open);
        edit.apply();
    }
}
