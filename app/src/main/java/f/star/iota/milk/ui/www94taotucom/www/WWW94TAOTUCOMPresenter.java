package f.star.iota.milk.ui.www94taotucom.www;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class WWW94TAOTUCOMPresenter extends StringPresenter<List<WWW94TAOTUCOMBean>> {


    public WWW94TAOTUCOMPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("page-1.html")) {
            url = Net.WWW94TAOTUCOM_BASE;
        }
        return url;
    }

    @Override
    protected List<WWW94TAOTUCOMBean> dealResponse(String s, HashMap<String, String> headers) {
        List<WWW94TAOTUCOMBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#bodywrap > table > tbody > tr > td > div > div > div.gallary_wrap > div.gallary_item_album > div.item > div.pic_box");
        for (Element element : select) {
            WWW94TAOTUCOMBean bean = new WWW94TAOTUCOMBean();
            String preview = element.select("table > tbody > tr > td > a > img").attr("src");
            bean.setPreview(preview);
            String url = Net.WWW94TAOTUCOM_BASE + element.select("table > tbody > tr > td > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("table > tbody > tr > td > a > img").attr("alt");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }
}
