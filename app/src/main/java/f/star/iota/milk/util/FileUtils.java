package f.star.iota.milk.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FileUtils {
    public static String getFileSize(long size) {
        long KB = 1024;
        long MB = KB * 1024;
        long GB = MB * 1024;
        if (size > GB) {
            return String.format(Locale.US, "%.2f GB", size / (GB * 1.0));
        } else if (size > MB) {
            return String.format(Locale.US, "%.2f MB", size / (MB * 1.0));
        } else if (size > KB) {
            return String.format(Locale.US, "%.2f KB", size / (KB * 1.0));
        } else {
            return size + " Byte";
        }
    }

    public static boolean deleteDirsFiles(File dir) {
        boolean isError = false;
        if (dir != null && dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file != null) {
                    if (file.isFile()) {
                        if (!file.delete()) {
                            isError = true;
                        }
                    } else {
                        deleteDirsFiles(file);
                    }
                } else {
                    isError = true;
                }
            }
        } else {
            isError = true;
        }
        return isError;
    }

    public static long getFolderSize(File file) {
        long size = 0;
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                size = size + getFolderSize(f);
            } else {
                size = size + f.length();
            }
        }
        return size;
    }

    public static File getDiskCacheDir(Context context) {
        File cacheDir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cacheDir = context.getExternalCacheDir();
        } else {
            cacheDir = context.getCacheDir();
        }
        return cacheDir;
    }

    public static String getDownloadDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            return Environment.getExternalStorageDirectory().getPath() + "/Milk/";
        }
        return null;
    }

    public static void saveBitmap(Bitmap bitmap) {
        if (getDownloadDir() == null) return;
        try {
            String dirPath = getDownloadDir() + "Widget/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filePath = dirPath + getCurrentDate() + ".png";
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd EE HH:mm:ss", Locale.US);
        return sdf.format(System.currentTimeMillis());
    }

    //
//    public static String getFileName(String url) {
//        long time = System.currentTimeMillis();
//        url = url.toLowerCase();
//        if (url.contains(".jpg") || url.contains(".jpeg")) {
//            return time + ".jpg";
//        } else if (url.contains(".png")) {
//            return time + ".png";
//        } else if (url.contains(".webp")) {
//            return time + ".webp";
//        } else if (url.contains(".bmp")) {
//            return time + ".bmp";
//        } else if (url.contains(".gif")) {
//            return time + ".gif";
//        } else {
//            return time + "";
//        }
//    }
    public static void saveCrashLog(Thread thread, Throwable throwable) {
        try {
            String dirPath = FileUtils.getDownloadDir() + "Log/";
            String filePath = dirPath + FileUtils.getCurrentDate() + ".log";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            String separator = System.getProperty("line.separator");
            String sb = separator +
                    "#############################" +
                    separator +
                    "# Milk@F Crash Log" +
                    separator +
                    "#############################" +
                    separator +
                    "USER: " + Build.USER +
                    separator +
                    "MANUFACTURER: " + Build.MANUFACTURER +
                    separator +
                    "DEVICE: " + Build.DEVICE +
                    separator +
                    "PRODUCT: " + Build.PRODUCT +
                    separator +
                    "SDK VERSION: " + Build.VERSION.SDK_INT +
                    separator +
                    "RELEASE: " + Build.VERSION.RELEASE +
                    separator +
                    "INCREMENTAL: " + Build.VERSION.INCREMENTAL +
                    separator +
                    "MODEL: " + Build.MODEL +
                    separator +
                    "HARDWARE: " + Build.HARDWARE +
                    separator +
                    "DISPLAY: " + Build.DISPLAY +
                    separator +
                    "BOARD: " + Build.BOARD +
                    separator +
                    "BRAND: " + Build.BRAND +
                    separator + separator +
                    "#############################" +
                    separator +
                    "# Raw Log Info" +
                    separator +
                    "#############################" +
                    separator +
                    "Thread: " + thread +
                    separator +
                    "Throwable: " + throwable;
            bw.write(sb);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
