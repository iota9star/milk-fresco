package f.star.iota.milk;

import android.os.Handler;
import android.os.Looper;


final class Crash {

    private static ExceptionHandler sExceptionHandler;

    private Crash() {
    }

    static synchronized void setExceptionHandler(ExceptionHandler exceptionHandler) {
        sExceptionHandler = exceptionHandler;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        if (sExceptionHandler != null) {
                            sExceptionHandler.handleException(Looper.getMainLooper().getThread(), e);
                        }
                    }
                }
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                if (sExceptionHandler != null && throwable != null) {
                    sExceptionHandler.handleException(thread, throwable);
                }
            }
        });
    }

    interface ExceptionHandler {
        void handleException(Thread thread, Throwable e);
    }
}