package f.star.iota.milk.ui.taotutt.tao;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class TAOTUTTPresenter extends StringPresenter<List<TAOTUTTBean>> {


    public TAOTUTTPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<TAOTUTTBean> dealResponse(String s, HashMap<String, String> headers) {
        List<TAOTUTTBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.update_area > div > ul > li");
        for (Element element : select) {
            TAOTUTTBean bean = new TAOTUTTBean();
            String preview = element.select("a > img").attr("data-original");
            bean.setPreview(preview);
            String url = Net.TAOTUTT_BASE + element.select("a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("div > div.meta-title").text();
            bean.setDescription(description);
            element.select("div > div.meta-post > span").remove();
            String date = element.select("div > div.meta-post").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("/page_1.html")) {
            url = url.replace("/page_1.html", ".html");
        }
        return url;
    }
}
