package f.star.iota.milk.fresco;

import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.ProducerContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

import f.star.iota.milk.Net;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MyOkHttpNetworkFetcher extends BaseNetworkFetcher<MyOkHttpNetworkFetcher.OkHttpNetworkFetchState> {
    private static final String QUEUE_TIME = "queue_time";
    private static final String FETCH_TIME = "fetch_time";
    private static final String TOTAL_TIME = "total_time";
    private static final String IMAGE_SIZE = "image_size";
    public static WeakHashMap<Uri, HashMap<String, String>> Headers = new WeakHashMap<>();
    private final OkHttpClient mClient;
    private Executor mCancellationExecutor;

    public MyOkHttpNetworkFetcher(OkHttpClient client) {
        mClient = client;
        mCancellationExecutor = mClient.dispatcher().executorService();
    }

    @Override
    public OkHttpNetworkFetchState createFetchState(
            Consumer<EncodedImage> consumer,
            ProducerContext context) {
        return new OkHttpNetworkFetchState(consumer, context);
    }

    @Override
    public void fetch(final OkHttpNetworkFetchState fetchState, final Callback callback) {
        fetchState.submitTime = SystemClock.elapsedRealtime();
        Uri uri = fetchState.getUri();
        Request.Builder builder = new Request.Builder();
        HashMap<String, String> headers = MyOkHttpNetworkFetcher.Headers.get(uri);
        if (headers != null && headers.size() > 0) {
            for (HashMap.Entry<String, String> entry : headers.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
        final Request request = builder
                .cacheControl(new CacheControl.Builder().noStore().build())
                .header("User-Agent", Net.USER_AGENT)
                .url(uri.toString())
                .get()
                .build();
        final Call call = mClient.newCall(request);
        fetchState.getContext().addCallbacks(
                new BaseProducerContextCallbacks() {
                    @Override
                    public void onCancellationRequested() {
                        if (Looper.myLooper() != Looper.getMainLooper()) {
                            call.cancel();
                        } else {
                            mCancellationExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    call.cancel();
                                }
                            });
                        }
                    }
                });
        call.enqueue(
                new okhttp3.Callback() {
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        fetchState.responseTime = SystemClock.elapsedRealtime();
                        final ResponseBody body = response.body();
                        try {
                            if (!response.isSuccessful()) {
                                handleException(call, new IOException("Unexpected HTTP code " + response), callback);
                                return;
                            }
                            long contentLength = 0;
                            if (body != null) {
                                contentLength = body.contentLength();
                            }
                            if (contentLength < 0) {
                                contentLength = 0;
                            }
                            if (body != null) {
                                callback.onResponse(body.byteStream(), (int) contentLength);
                            }
                        } catch (Exception e) {
                            handleException(call, e, callback);
                        } finally {
                            try {
                                if (body != null) {
                                    body.close();
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        handleException(call, e, callback);
                    }
                });
    }

    @Override
    public void onFetchCompletion(OkHttpNetworkFetchState fetchState, int byteSize) {
        fetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    @Override
    public Map<String, String> getExtraMap(OkHttpNetworkFetchState fetchState, int byteSize) {
        Map<String, String> extraMap = new HashMap<>(4);
        extraMap.put(QUEUE_TIME, Long.toString(fetchState.responseTime - fetchState.submitTime));
        extraMap.put(FETCH_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.responseTime));
        extraMap.put(TOTAL_TIME, Long.toString(fetchState.fetchCompleteTime - fetchState.submitTime));
        extraMap.put(IMAGE_SIZE, Integer.toString(byteSize));
        return extraMap;
    }

    private void handleException(final Call call, final Exception e, final Callback callback) {
        if (call.isCanceled()) {
            callback.onCancellation();
        } else {
            callback.onFailure(e);
        }
    }

    static class OkHttpNetworkFetchState extends FetchState {
        long submitTime;
        long responseTime;
        long fetchCompleteTime;

        OkHttpNetworkFetchState(
                Consumer<EncodedImage> consumer,
                ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }
}


