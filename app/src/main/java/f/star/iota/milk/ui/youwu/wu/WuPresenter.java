package f.star.iota.milk.ui.youwu.wu;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class WuPresenter extends StringPresenter<List<WuBean>> {
    public WuPresenter(PVContract.View view) {
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
    protected List<WuBean> dealResponse(String s, HashMap<String, String> headers) {
        List<WuBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#big-pic > div.picbox > a img");
        for (Element element : select) {
            WuBean bean = new WuBean();
            String url = element.attr("src");
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
