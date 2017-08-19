package f.star.iota.milk.base;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.Request;

public abstract class StringPresenter<T> implements PVContract.Presenter {

    private final PVContract.View<T> view;

    private final CompositeDisposable mCompositeDisposable;

    @SuppressWarnings("unchecked")
    protected StringPresenter(PVContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void get(String url) {
        url = dealUrl(url);
        final String finalUrl = url;
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, T>() {
                            @Override
                            public T apply(@NonNull Response<String> s) throws Exception {
                                Request request = s.getRawResponse().networkResponse().request();
                                HashMap<String, String> headers = new HashMap<>();
                                headers.put("Referer", finalUrl);
                                if (request != null) {
                                    Headers rawHeaders = request.headers();
                                    int requestHeadersLength = rawHeaders.size();
                                    for (int i = 0; i < requestHeadersLength; i++) {
                                        String key = rawHeaders.name(i);
                                        String value = rawHeaders.get(key);
                                        headers.put(key, value);
                                    }
                                }
                                Headers response = s.headers();
                                if (response != null) {
                                    int size = response.size();
                                    for (int i = 0; i < size; i++) {
                                        String key = response.name(i);
                                        if (key.toLowerCase().contains("etag")) {
                                            headers.put("If-None-Match", response.value(i));
                                        }
                                    }
                                }
                                return dealResponse(s.body(), headers);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<T>() {
                            @Override
                            public void accept(@NonNull T result) throws Exception {
                                view.success(result);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.error(throwable.getMessage());
                            }
                        })
        );
    }

    protected String dealUrl(String url) {
        return url;
    }

    protected abstract T dealResponse(String s, HashMap<String, String> headers);
}
