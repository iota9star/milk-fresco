package f.star.iota.milk.ui.zdt8.zd;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class ZDT8Presenter extends StringPresenter<List<ZDT8Bean>> {


    public ZDT8Presenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("listinfo-1-0.html")) {
            url = "http://www.zdt8.com/new/";
        }
        return url;
    }

    @Override
    protected List<ZDT8Bean> dealResponse(String s, HashMap<String, String> headers) {
        List<ZDT8Bean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.mainer > div.piclist > ul > li");
        for (Element element : select) {
            ZDT8Bean bean = new ZDT8Bean();
            String preview = element.select("a > img").attr("lazysrc");
            bean.setPreview(preview);
            String url = element.select("a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("a > span").text();
            bean.setDescription(description);
            String date = element.select("b.b1").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }

}
