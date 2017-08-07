package f.star.iota.milk;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okserver.OkDownload;
import com.nightfarmer.themer.Themer;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.concurrent.TimeUnit;

import f.star.iota.milk.fresco.MyOkHttpNetworkFetcher;
import f.star.iota.milk.util.ConfigUtils;
import f.star.iota.milk.util.CrashUtils;
import f.star.iota.milk.util.FileUtils;
import f.star.iota.milk.util.Utils;
import okhttp3.OkHttpClient;


public class MyApp extends Application {

    private static final String FRESCO_BASE_CACHE_DIR = "fresco_main_cache";
    private static final String FRESCO_SMALL_IMAGE_CACHE_DIR = "fresco_small_image_cache";
    private static final long MAX_DISK_CACHE_SIZE = 1024 * ByteConstants.MB;
    private static final long MAX_DISK_CACHE_LOW_SIZE = 300 * ByteConstants.MB;
    private static final long MAX_DISK_CACHE_VERY_LOW_SIZE = 100 * ByteConstants.MB;
    private static final int MAX_CACHE_ENTRIES = 16;

    @SuppressLint("StaticFieldLeak")
    public static Context mContext;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new DeliveryHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setEnableAutoLoadmore(true);
                return new ClassicsFooter(context);
            }
        });
    }

    private static OkHttpClient makeOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//        loggingInterceptor.setColorLevel(Level.INFO);
//        builder.addInterceptor(loggingInterceptor);
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(mContext)));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return builder.build();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Fresco.getImagePipeline().clearMemoryCaches();
        System.gc();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Fresco.getImagePipeline().clearMemoryCaches();
            System.gc();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Themer.INSTANCE.init(this, ConfigUtils.getTheme(mContext));
//        LeakCanary.install(this);
        Utils.init(this);
        CrashUtils.init(FileUtils.getDownloadDir() + "Log");
        addCount();
        initOkGo();
        initFresco();
    }

    private void initFresco() {
        MemoryTrimmableRegistry memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        memoryTrimmableRegistry.registerMemoryTrimmable(new MemoryTrimmable() {
            @Override
            public void trim(MemoryTrimType trimType) {
                final double suggestedTrimRatio = trimType.getSuggestedTrimRatio();
                if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio
                        ) {
                    Fresco.getImagePipeline().clearMemoryCaches();
                }
            }
        });
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(mContext)
                .setBaseDirectoryPath(FileUtils.getDiskCacheDir(mContext))
                .setBaseDirectoryName(FRESCO_BASE_CACHE_DIR)
                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERY_LOW_SIZE)
                .setDiskTrimmableRegistry(NoOpDiskTrimmableRegistry.getInstance())
                .build();
        DiskCacheConfig diskSmallCacheConfig = DiskCacheConfig.newBuilder(mContext)
                .setBaseDirectoryPath(FileUtils.getDiskCacheDir(mContext))
                .setBaseDirectoryName(FRESCO_SMALL_IMAGE_CACHE_DIR)
                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERY_LOW_SIZE)
                .setDiskTrimmableRegistry(NoOpDiskTrimmableRegistry.getInstance())
                .build();
        Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return new MemoryCacheParams(
                        (int) (Runtime.getRuntime().maxMemory() / 4),
                        MAX_CACHE_ENTRIES,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE);

            }
        };
        ProgressiveJpegConfig progressiveJpegConfig = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber + 2;
            }

            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber >= 5);
                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig
                .newBuilder(mContext)
                .setDownsampleEnabled(true)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setResizeAndRotateEnabledForNetwork(true)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setSmallImageDiskCacheConfig(diskSmallCacheConfig)
                .setBitmapMemoryCacheParamsSupplier(bitmapMemoryCacheParamsSupplier)
                .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
                .setProgressiveJpegConfig(progressiveJpegConfig)
                .setNetworkFetcher(new MyOkHttpNetworkFetcher(makeOkHttpClient()))
                .build();
        Fresco.initialize(mContext, imagePipelineConfig);
    }

    private void addCount() {
        long openCount = ConfigUtils.getOpenCount(this);
        openCount++;
        ConfigUtils.saveOpenCount(this, openCount);
    }

    private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("User-Agent", Net.USER_AGENT);
        OkGo.getInstance().init(this)
                .setOkHttpClient(makeOkHttpClient())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3)
                .addCommonHeaders(headers);
        OkDownload.getInstance().getThreadPool().setCorePoolSize(ConfigUtils.getDownloadCountConfig(this));
    }
}
