package f.star.iota.milk.ui.main;


import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import f.star.iota.milk.Contracts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private final MainActivityContract.View view;

    private final CompositeDisposable mCompositeDisposable;

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void get() {
        int random = (int) (Math.random() * 4);
        if (random == 0) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Contracts.Url.HITOKOTO_BILIBILIJJ)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    String s = response.body();
                                    s = s.substring(s.indexOf("(", 0) + 1, s.lastIndexOf(")"));
                                    JuZiBean bean = new Gson().fromJson(s, JuZiBean.class);
                                    if (bean == null) {
                                        bean = new JuZiBean();
                                        bean.setHitokoto("解析错误，可能出现未知问题");
                                        bean.setSource("...");
                                    }
                                    return bean;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        } else if (random == 1) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Contracts.Url.YIJU)
                            .headers("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                            .headers("Host", "yiju.ml")
                            .headers("Accept-Encoding", "gzip, deflate")
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    String s = new String(response.body().getBytes("UTF-8"));
                                    if (s.contains("——")) {
                                        String ju = s.substring(0, s.indexOf("——"));
                                        bean.setHitokoto(ju);
                                        String source = s.substring(s.indexOf("——") + 2, s.length());
                                        bean.setSource(source);
                                    } else {
                                        bean.setHitokoto(s);
                                        bean.setSource("...");
                                    }
                                    return bean;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        } else if (random == 2) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Contracts.Url.HITOKOTO_IMJAD)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    return new Gson().fromJson(response.body(), JuZiBean.class);
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        } else if (random == 3) {
            mCompositeDisposable.add(
                    OkGo.<String>get(Contracts.Url.HITOKOTO_LOLI)
                            .converter(new StringConvert())
                            .adapt(new ObservableResponse<String>())
                            .subscribeOn(Schedulers.io())
                            .map(new Function<Response<String>, JuZiBean>() {
                                @Override
                                public JuZiBean apply(@NonNull Response<String> response) throws Exception {
                                    return new Gson().fromJson(response.body(), JuZiBean.class);
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<JuZiBean>() {
                                @Override
                                public void accept(@NonNull JuZiBean bean) throws Exception {
                                    view.getSuccess(bean);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    JuZiBean bean = new JuZiBean();
                                    bean.setHitokoto(throwable.getMessage());
                                    bean.setSource("...");
                                    view.getSuccess(bean);
                                }
                            })
            );
        }
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

}
