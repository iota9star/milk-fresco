package f.star.iota.milk.ui.cosplayla.cosplay;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class CosplayLaPresenter extends StringPresenter<List<CosplayLaBean>> {

    public CosplayLaPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<CosplayLaBean> dealResponse(String s, HashMap<String, String> headers) {
        List<CosplayLaBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.box > div.pics > ul > li.fleft");
        for (Element element : select) {
            CosplayLaBean bean = new CosplayLaBean();
            String preview = element.select("a > img").attr("src");
            bean.setPreview(preview);
            String url = Net.COSPLAY_LA_BASE + element.select("p:nth-child(2) > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("p:nth-child(2) > a").text();
            bean.setDescription(description);
            String date = element.select("p:nth-child(3) > span").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }
}
