package f.star.iota.milk.util;


import java.io.File;
import java.io.IOException;

public class MediaUtils {
    public static boolean hasNomediaFile() {
        String[] nomedias = getNomediaFilePath();
        if (nomedias == null || nomedias.length == 0) return false;
        for (String nomedia : nomedias) {
            File file = new File(nomedia);
            return file.exists();
        }
        return false;
    }

    private static String[] getNomediaFilePath() {
        String mainPath = FileUtils.getDownloadDir();
        if (mainPath == null) return null;
        File mainDir = new File(mainPath);
        if (!mainDir.exists() || !mainDir.isDirectory()) return null;
        File[] files = mainDir.listFiles();
        if (files.length == 0) return null;
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            if (file.isDirectory()) {
                sb.append(file.getAbsolutePath()).append("/.nomedia").append(",");
            }
        }
        return sb.toString().split(",");
    }

    private static void createNomediaFile() {
        try {
            String[] nomedias = getNomediaFilePath();
            if (nomedias == null || nomedias.length == 0) return;
            for (String nomedia : nomedias) {
                if (nomedia == null) continue;
                File file = new File(nomedia);
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (IOException ignored) {
        }
    }

    public static void deleteOrCreateNomediaFile(final boolean delete) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (delete) {
                    deleteNomediaFile();
                } else {
                    createNomediaFile();
                }
            }
        }).start();
    }


    private static void deleteNomediaFile() {
        try {
            String[] nomedias = getNomediaFilePath();
            if (nomedias == null || nomedias.length == 0) return;
            for (String nomedia : nomedias) {
                if (nomedia == null) continue;
                File file = new File(nomedia);
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception ignored) {
        }
    }
}
