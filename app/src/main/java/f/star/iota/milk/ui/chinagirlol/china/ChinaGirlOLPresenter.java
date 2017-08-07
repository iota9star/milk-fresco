package f.star.iota.milk.ui.chinagirlol.china;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class ChinaGirlOLPresenter extends StringPresenter<List<ChinaGirlOLBean>> {


    public ChinaGirlOLPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<ChinaGirlOLBean> dealResponse(String s, HashMap<String, String> headers) {
        List<ChinaGirlOLBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#waterfall > li > div.c.cl > a");
        for (Element element : select) {
            ChinaGirlOLBean bean = new ChinaGirlOLBean();
            String preview = Net.CHINAGIRLOL_BASE + element.select("img").attr("src");
            bean.setPreview(preview);
            String url = element.attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("img").attr("alt");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }
}
