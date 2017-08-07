package f.star.iota.milk.ui.miaowu.wu;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class WuPresenter extends StringPresenter<List<WuBean>> {
    public WuPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<WuBean> dealResponse(String s, HashMap<String, String> headers) {
        List<WuBean> list = new ArrayList<>();
        Element element = Jsoup.parse(s).select("body > section > article > p > a > img").first();
        WuBean bean = new WuBean();
        String url = Net.MIAOWU_BASE + element.attr("src");
        bean.setUrl(url);
        bean.setHeaders(headers);
        list.add(bean);
        return list;
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("_1.html")) {
            url = url.replace("_1.html", ".html");
        }
        return super.dealUrl(url);
    }
}
