package f.star.iota.milk.ui.widget.banner;


import android.net.Uri;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import f.star.iota.milk.Net;
import f.star.iota.milk.SourceType;
import f.star.iota.milk.fresco.MyOkHttpNetworkFetcher;
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
            case SourceType.SIMPLEDESKTOPS:
                getSimpleDesktops();
                break;
            case SourceType.YURIIMG:
                getYuriimg();
                break;
            case SourceType.KUVVA:
                getKuvva();
                break;
            case SourceType.GAMERSKY:
                getGamersky();
                break;
        }
    }

    private void getGamersky() {
        String url = "http://db2.gamersky.com/LabelJsonpAjax.aspx?jsondata={\"type\":\"updatenodelabel\",\"isCache\":true,\"cacheTime\":60,\"nodeId\":\"20117\",\"isNodeId\":\"true\",\"page\":" + (new Random().nextInt(23) + 1) + "}";
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parseBodyFragment(s.body().replaceAll("\\\\", "")).select(".img > a");
                                for (Element element : select) {
                                    String url = element.attr("href");
                                    list.add(url);
                                }
                                return list.get(new Random().nextInt(list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                getGamerskyRaw(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getGamerskyRaw(String url) {
        url = url.replace(".shtml", "_" + (new Random().nextInt(5) + 1) + ".shtml");
        if (url.contains("_1.shtml")) {
            url = url.replace("_1.shtml", ".shtml");
        }
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("div > div > div > p > a");
                                for (Element element : select) {
                                    String url = element.attr("href");
                                    if (!url.contains(".shtml?http")) continue;
                                    url = url.substring(url.indexOf(".shtml?http") + 7, url.length());
                                    list.add(url);
                                }
                                return list.get(new Random().nextInt(list.size()));
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

    private void getKuvva() {
        String url = "https://www.kuvva.com/wallpapers?page=" + (new Random().nextInt(42) + 1);
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("body > div > section > ul > li > a");
                                for (Element element : select) {
                                    String url = Net.KUVVA_BASE + element.attr("href");
                                    list.add(url);
                                }
                                return list.get(new Random().nextInt(list.size()));
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                getKuvvaRaw(s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                view.getBannerError();
                            }
                        })
        );
    }

    private void getKuvvaRaw(String s) {
        mCompositeDisposable.add(
                OkGo.<String>get(s)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                return Jsoup.parse(s.body()).select("body > section.illustration > div > div.image > div > a > img").attr("src");
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

    private void getYuriimg() {
        String url = "http://yuriimg.com/post/" + (new Random().nextInt(560) + 1) + ".html";
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("#image-box div.image-list");
                                for (Element element : select) {
                                    String url = Net.YURIIMG_BASE + element.select("div.image img").attr("data-viewersss");
                                    list.add(url);
                                }
                                HashMap<String, String> headers = new HashMap<>();
                                headers.put("Referer", "http://yuriimg.com/");
                                headers.put("Host", "yuriimg.com");
                                headers.put("Accept-Encoding", "gzip, deflate");
                                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                                String url = list.get(new Random().nextInt(list.size()));
                                MyOkHttpNetworkFetcher.Headers.put(Uri.parse(url), headers);
                                return url;
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

    private void getSimpleDesktops() {
        String url = "http://simpledesktops.com/browse/" + (new Random().nextInt(49) + 1);
        mCompositeDisposable.add(
                OkGo.<String>get(url)
                        .converter(new StringConvert())
                        .adapt(new ObservableResponse<String>())
                        .subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                        .map(new Function<Response<String>, String>() {
                            @Override
                            public String apply(@NonNull Response<String> s) throws Exception {
                                List<String> list = new ArrayList<>();
                                Elements select = Jsoup.parse(s.body()).select("body > div > div.container > div > div.desktops> div.edge > div.desktop > a > img");
                                for (Element element : select) {
                                    String url = element.attr("src").replace(".295x184_q100.png", "");
                                    list.add(url);
                                }
                                return list.get(new Random().nextInt(list.size()));
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
                                Iterator<String> iterator = list.iterator();
                                while (iterator.hasNext()) {
                                    if (iterator.next().contains("Aqutips.png")) {
                                        iterator.remove();
                                    }
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
