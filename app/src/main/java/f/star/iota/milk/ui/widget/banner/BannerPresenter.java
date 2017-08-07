package f.star.iota.milk.ui.widget.banner;


import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import f.star.iota.milk.Net;
import f.star.iota.milk.SourceType;
import f.star.iota.milk.ui.bing.BingBean;
import f.star.iota.milk.ui.gank.GankBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class BannerPresenter implements BannerContract.Presenter {

    private final BannerContract.View view;

    private final CompositeDisposable mCompositeDisposable;

    public BannerPresenter(BannerContract.View view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBanner(int type) {
        switch (type) {
            case SourceType.MOEIMG:
                getMoeImg();
                break;
            case SourceType.APIC:
                getApic();
                break;
            case SourceType.BING:
                getBing();
                break;
            case SourceType.GANK:
                getGank();
                break;
            case SourceType.WALLHAVEN:
                getWallHaven();
                break;
        }
    }

    private void getWallHaven() {
        String url = "https://alpha.wallhaven.cc/latest?page=" + (new Random().nextInt(12000) + 1);
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("#thumbs > section > ul > li > figure");
                                for (Element element : select) {
                                    String url = element.select("a.preview").attr("href");
                                    list.add(url);
                                }
                                return list.get(new Random().nextInt(list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                getWallHavenRaw(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getWallHavenRaw(String s) {
        mCompositeDisposable.add(
                OkGo.<String>get(s)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                return "https:" + Jsoup.parse(s.body())
                                        .select("#wallpaper")
                                        .first()
                                        .attr("src");
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                view.getBannerSuccess(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getGank() {
        String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/24/" + (new Random().nextInt(20) + 1);
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<GankBean.ResultsBean> results = new Gson().fromJson(s.body(), GankBean.class).getResults();
                                return results.get(new Random().nextInt(results.size())).getUrl();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                view.getBannerSuccess(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getBing() {
        String url = "http://cn.bing.com/HPImageArchive.aspx?format=js&n=24";
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                BingBean bean = new Gson().fromJson(s.body(), BingBean.class);
                                return bean.getImages().get(new Random().nextInt(bean.getImages().size())).getUrl();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                view.getBannerSuccess(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getApic() {
        final String url = "http://www.apic.in/page/" + (new Random().nextInt(64) + 1);
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("#main > div.loop");
                                if (select.size() < 1) {
                                    select = Jsoup.parse(s.body()).select("#primary > div > article.angela--post-home");
                                    for (Element element : select) {
                                        String url = element.select("h2.angela-title > a").attr("href");
                                        list.add(url);
                                    }
                                } else {
                                    for (Element element : select) {
                                        String url = element.select("h2 > a").attr("href");
                                        list.add(url);
                                    }
                                }
                                return list.get((int) (Math.random() * list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                if (url.contains("http://www.apic.in/wp-content/uploads/2016/06/cilacila.png")) {
                                    view.getBannerError();
                                } else {
                                    getApicRaw(s);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getApicRaw(String url) {
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("#post > div.post > p img");
                                for (Element element : select) {
                                    String url = element.attr("src").replace("!origin", "");
                                    list.add(url);
                                }
                                return list.get((int) (Math.random() * list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                view.getBannerSuccess(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getMoeImg() {
        final String url = Net.MOEIMG + (int) (Math.random() * 100);
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("#main-2 > div.post > div.more-field > a");
                                for (Element element : select) {
                                    String url = element.attr("href");
                                    list.add(url);
                                }
                                return list.get((int) (Math.random() * list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                if (s.contains("moeimg.net/9734.html") ||
                                        s.contains("moeimg.net/9713.html") ||
                                        s.contains("moeimg.net/9554.html")) {
                                    view.getBannerError();
                                } else {
                                    getMoeImgRaw(s);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getMoeImgRaw(String url) {
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("#main-2 > div.post > div.box > a > img");
                                for (Element element : select) {
                                    String url = element.attr("src");
                                    list.add(url);
                                }
                                return list.get((int) (Math.random() * list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                view.getBannerSuccess(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

}
