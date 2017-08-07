package f.star.iota.milk.ui.miaowu.miao;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class MiaoWuPresenter extends StringPresenter<List<MiaoWuBean>> {


    public MiaoWuPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MiaoWuBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MiaoWuBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > section > div.excerpts-wrapper > div > article");
        for (Element element : select) {
            MiaoWuBean bean = new MiaoWuBean();
            String preview = Net.MIAOWU_BASE + element.select("a.thumbnail > img").attr("data-src");
            bean.setPreview(preview);
            String url = Net.MIAOWU_BASE + element.select("h2 > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("h2 > a").text();
            bean.setDescription(description);
            String date = element.select("footer > time").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("index_1.html")) {
            url = url.replace("index_1.html", "");
        }
        return url;
    }
}
