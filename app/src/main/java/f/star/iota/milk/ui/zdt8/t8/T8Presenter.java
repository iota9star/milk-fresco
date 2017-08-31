package f.star.iota.milk.ui.zdt8.t8;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class T8Presenter extends StringPresenter<List<T8Bean>> {
    public T8Presenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("_1.html")) {
            url = url.replace("_1.html", ".html");
        }
        return url;
    }

    @Override
    protected List<T8Bean> dealResponse(String s, HashMap<String, String> headers) {
        List<T8Bean> list = new ArrayList<>();
        Document document = Jsoup.parse(s);
        String base = document.select("#p center > img").attr("lazysrc");
        T8Bean bean = new T8Bean();
        bean.setUrl(base);
        list.add(bean);
        return list;
    }
}
