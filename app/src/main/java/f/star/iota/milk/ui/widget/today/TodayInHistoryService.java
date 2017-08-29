package f.star.iota.milk.ui.widget.today;

import android.app.PendingIntent;
import android.app.Service;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.Timer;
import java.util.TimerTask;

import f.star.iota.milk.R;
import f.star.iota.milk.config.WidgetConfig;
import f.star.iota.milk.ui.splash.HistoryBean;
import f.star.iota.milk.ui.widget.banner.BannerContract;
import f.star.iota.milk.ui.widget.banner.BannerPresenter;
import f.star.iota.milk.util.FastBlur;
import f.star.iota.milk.util.FileUtils;
import f.star.iota.milk.util.NetUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class TodayInHistoryService extends Service implements TodayInHistoryContract.View, BannerContract.View {

    private static final String ACTION_REFRESH = "star.iota.widget.today.in.history.refresh";
    private TodayInHistoryPresenter mTodayInHistoryPresenter;
    private BannerPresenter mBannerPresenter;
    private Timer mTimer;
    private boolean todayIsRunning = false;
    private boolean bannerIsRunning = false;
    private Context mContext;
    private final TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            if (WidgetConfig.isPauseRefresh(mContext)) return;
            getToday();
            getBanner();
        }
    };
    private RefreshReceiver mRefreshReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void getToday() {
        if (todayIsRunning) return;
        todayIsRunning = true;
        mTodayInHistoryPresenter.getToday();
    }

    private void getBanner() {
        if (bannerIsRunning) return;
        if (WidgetConfig.isOnlyWiFiLoad(mContext)) {
            if (NetUtils.isWiFi(mContext)) {
                bannerIsRunning = true;
                mBannerPresenter.getBanner(WidgetConfig.getWidgetBannerSource(mContext));
            }
        } else {
            bannerIsRunning = true;
            mBannerPresenter.getBanner(WidgetConfig.getWidgetBannerSource(mContext));
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Context aContext = getApplicationContext();
        IntentFilter filter = new IntentFilter(ACTION_REFRESH);
        registerReceiver(mRefreshReceiver = new RefreshReceiver(), filter);
        mTodayInHistoryPresenter = new TodayInHistoryPresenter(this);
        mBannerPresenter = new BannerPresenter(this);
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(mTask, 0, 1000 * 60 * WidgetConfig.getTodayInHistroyInterval(aContext));
    }

    @Override
    public void getSuccess(HistoryBean bean) {
        todayIsRunning = false;
        updateToday(bean);
    }

    private void updateToday(HistoryBean bean) {
        try {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_today_in_history_with_banner);
            if (bean.getYear().trim().replaceAll(" ", "").length() < 3) {
                views.setTextViewText(R.id.text_view_history, "今天天气应该不错吧");
            } else {
                views.setTextViewText(R.id.text_view_history, bean.getEvent());
                views.setTextViewText(R.id.text_view_today, bean.getDay() + " # " + bean.getYear());
            }
            Intent playIntent = new Intent(ACTION_REFRESH);
            PendingIntent refresh = PendingIntent.getBroadcast(mContext, 0, playIntent, 0);
            views.setOnClickPendingIntent(R.id.frame_layout_today_container, refresh);
            ComponentName componentName = new ComponentName(mContext, TodayInHistoryWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, views);
            startService(new Intent(mContext, TodayInHistoryService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getError() {
        todayIsRunning = false;
    }

    @Override
    public void getBannerSuccess(String url) {
        try {
            if (url != null) {
                Uri uri = Uri.parse(url);
                if (uri != null) {
                    ImageRequest imageRequest = ImageRequestBuilder
                            .newBuilderWithSource(uri)
                            .build();
                    ImagePipeline imagePipeline = Fresco.getImagePipeline();
                    DataSource<CloseableReference<CloseableImage>>
                            dataSource = imagePipeline.fetchDecodedImage(imageRequest, null);
                    dataSource.subscribe(new BaseBitmapDataSubscriber() {
                                             @Override
                                             public void onNewResultImpl(@Nullable Bitmap bitmap) {
                                                 bannerIsRunning = false;
                                                 if (bitmap == null) return;
                                                 try {
                                                     updateBanner(bitmap);
                                                     if (WidgetConfig.isSaveWidgetBanner(mContext)) {
                                                         saveBitmap(bitmap);
                                                     }
                                                     if (!WidgetConfig.isBlurWallpaper(mContext) && WidgetConfig.isSetWallpaper(mContext)) {
                                                         WallpaperManager manager = WallpaperManager.getInstance(mContext);
                                                         manager.setBitmap(bitmap);
                                                     }
                                                     if (WidgetConfig.isBlurWidgetBanner(mContext) || WidgetConfig.isBlurWallpaper(mContext)) {
                                                         Observable.just(FastBlur.doBlur(bitmap, WidgetConfig.getWidgetBlurValue(mContext), false))
                                                                 .subscribeOn(Schedulers.computation())
                                                                 .observeOn(AndroidSchedulers.mainThread())
                                                                 .subscribe(new Consumer<Bitmap>() {
                                                                     @Override
                                                                     public void accept(Bitmap bitmap) throws Exception {
                                                                         if (bitmap == null) return;
                                                                         if (WidgetConfig.isBlurWidgetBanner(mContext)) {
                                                                             updateBackground(bitmap);
                                                                         } else {
                                                                             updateBackground(null);
                                                                         }
                                                                         if (WidgetConfig.isBlurWallpaper(mContext) && WidgetConfig.isSetWallpaper(mContext)) {
                                                                             WallpaperManager manager = WallpaperManager.getInstance(mContext);
                                                                             manager.setBitmap(bitmap);
                                                                         }
                                                                     }
                                                                 }, new Consumer<Throwable>() {
                                                                     @Override
                                                                     public void accept(Throwable throwable) throws Exception {
                                                                     }
                                                                 });
                                                     }
                                                 } catch (Exception e) {
                                                     e.printStackTrace();
                                                 }
                                             }

                                             @Override
                                             public void onFailureImpl(DataSource dataSource) {
                                                 bannerIsRunning = false;
                                             }
                                         },
                            UiThreadImmediateExecutorService.getInstance());
                } else {
                    updateBackground(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBitmap(Bitmap bitmap) {
        Observable.just(bitmap)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        FileUtils.saveBitmap(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void updateBanner(Bitmap resource) {
        try {
            if (resource == null) return;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_today_in_history_with_banner);
            views.setImageViewBitmap(R.id.image_view_banner, resource);
            Intent intent = new Intent(ACTION_REFRESH);
            PendingIntent refresh = PendingIntent.getBroadcast(mContext, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.frame_layout_today_container, refresh);
            ComponentName componentName = new ComponentName(mContext, TodayInHistoryWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, views);
            startService(new Intent(mContext, TodayInHistoryService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBackground(Bitmap blur) {
        try {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_today_in_history_with_banner);
            views.setImageViewBitmap(R.id.image_view_background, blur);
            Intent intent = new Intent(ACTION_REFRESH);
            PendingIntent refresh = PendingIntent.getBroadcast(mContext, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.frame_layout_today_container, refresh);
            ComponentName componentName = new ComponentName(mContext, TodayInHistoryWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, views);
            startService(new Intent(mContext, TodayInHistoryService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getBannerError() {
        bannerIsRunning = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTodayInHistoryPresenter != null) {
            mTodayInHistoryPresenter.unsubscribe();
        }
        if (mBannerPresenter != null) {
            mBannerPresenter.unsubscribe();
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        unregisterReceiver(mRefreshReceiver);
        startService(new Intent(mContext, TodayInHistoryService.class));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY_COMPATIBILITY;
    }

    public class RefreshReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WidgetConfig.isPauseRefresh(mContext)) return;
            if (intent != null && intent.getAction().equals(ACTION_REFRESH)) {
                getToday();
                getBanner();
            }
        }
    }
}
