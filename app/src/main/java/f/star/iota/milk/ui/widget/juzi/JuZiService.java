package f.star.iota.milk.ui.widget.juzi;

import android.app.PendingIntent;
import android.app.Service;
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
import f.star.iota.milk.ui.main.JuZiBean;
import f.star.iota.milk.ui.widget.banner.BannerContract;
import f.star.iota.milk.ui.widget.banner.BannerPresenter;
import f.star.iota.milk.util.ConfigUtils;
import f.star.iota.milk.util.FastBlur;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class JuZiService extends Service implements JuZiContract.View, BannerContract.View {

    private static final String ACTION_REFRESH = "star.iota.widget.juzi.refresh";
    private JuZiPresenter mJuziJuZiPresenter;
    private BannerPresenter mBannerPresenter;
    private Timer mTimer;
    private boolean juziIsRunning = false;
    private boolean bannerIsRunning = false;
    private Context mContext;
    private final TimerTask mTask = new TimerTask() {
        @Override
        public void run() {
            juzi();
            banner();
        }
    };
    private RefreshReceiver mRefreshReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void juzi() {
        if (juziIsRunning) return;
        juziIsRunning = true;
        mJuziJuZiPresenter.getJuzi();
    }

    private void banner() {
        if (bannerIsRunning) return;
        bannerIsRunning = true;
        mBannerPresenter.getBanner(ConfigUtils.getWidgetBannerSource(mContext));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Context aContext = getApplicationContext();
        IntentFilter filter = new IntentFilter(ACTION_REFRESH);
        registerReceiver(mRefreshReceiver = new RefreshReceiver(), filter);
        mJuziJuZiPresenter = new JuZiPresenter(this);
        mBannerPresenter = new BannerPresenter(this);
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(mTask, 0, 1000 * 60 * ConfigUtils.getJuziInterval(aContext));
    }

    @Override
    public void getJuziSuccess(JuZiBean bean) {
        juziIsRunning = false;
        updateJuzi(bean);
    }

    private void updateJuzi(JuZiBean bean) {
        if (bean.getHitokoto() == null || bean.getHitokoto().length() < 3) return;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_juzi_with_banner);
        views.setTextViewText(R.id.text_view_juzi_content, bean.getHitokoto());
        views.setTextViewText(R.id.text_view_juzi_source, bean.getSource());
        Intent intent = new Intent(ACTION_REFRESH);
        PendingIntent refresh = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.frame_layout_juzi_container, refresh);
        ComponentName componentName = new ComponentName(mContext, JuZiWidgetProvider.class);
        appWidgetManager.updateAppWidget(componentName, views);
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
                                                     Observable.just(FastBlur.doBlur(bitmap, 24, false))
                                                             .subscribeOn(Schedulers.computation())
                                                             .observeOn(AndroidSchedulers.mainThread())
                                                             .subscribe(new Consumer<Bitmap>() {
                                                                 @Override
                                                                 public void accept(Bitmap bitmap) throws Exception {
                                                                     updateBackground(bitmap);
                                                                 }
                                                             }, new Consumer<Throwable>() {
                                                                 @Override
                                                                 public void accept(Throwable throwable) throws Exception {
                                                                 }
                                                             });
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBanner(Bitmap resource) {
        try {
            if (resource == null) return;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_juzi_with_banner);
            views.setImageViewBitmap(R.id.image_view_banner, resource);
            Intent intent = new Intent(ACTION_REFRESH);
            PendingIntent refresh = PendingIntent.getBroadcast(mContext, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.frame_layout_juzi_container, refresh);
            ComponentName componentName = new ComponentName(mContext, JuZiWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, views);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBackground(Bitmap blur) {
        try {
            if (blur == null) return;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(mContext);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_juzi_with_banner);
            views.setImageViewBitmap(R.id.image_view_background, blur);
            Intent intent = new Intent(ACTION_REFRESH);
            PendingIntent refresh = PendingIntent.getBroadcast(mContext, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.frame_layout_juzi_container, refresh);
            ComponentName componentName = new ComponentName(mContext, JuZiWidgetProvider.class);
            appWidgetManager.updateAppWidget(componentName, views);
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
        if (mJuziJuZiPresenter != null) {
            mJuziJuZiPresenter.unsubscribe();
        }
        if (mBannerPresenter != null) {
            mBannerPresenter.unsubscribe();
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        unregisterReceiver(mRefreshReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY_COMPATIBILITY;
    }

    public class RefreshReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ACTION_REFRESH)) {
                juzi();
                banner();
            }
        }
    }
}
