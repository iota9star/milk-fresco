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
                            sExceptionHandler.handleUIException(Looper.getMainLooper().getThread(), e);
                        }
                    }
                }
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                if (sExceptionHandler != null && throwable != null) {
                    sExceptionHandler.handleOtherException(thread, throwable);
                }
            }
        });
    }

    interface ExceptionHandler {
        void handleOtherException(Thread thread, Throwable e);

        void handleUIException(Thread thread, Throwable e);
    }
}
