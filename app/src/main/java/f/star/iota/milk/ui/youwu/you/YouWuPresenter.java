package f.star.iota.milk.ui.youwu.you;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class YouWuPresenter extends StringPresenter<List<YouWuBean>> {


    public YouWuPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<YouWuBean> dealResponse(String s, HashMap<String, String> headers) {
        List<YouWuBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#infinite_scroll > div.item");
        for (Element element : select) {
            YouWuBean bean = new YouWuBean();
            String preview = element.select("div.item_t > div > a > img").attr("src");
            bean.setPreview(preview);
            String url = Net.YOUWU_BASE + element.select("div.item_b > div.title > span > a").attr("href");
            System.out.println(url);
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("div.item_t > div > a > img").attr("title");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }
}
