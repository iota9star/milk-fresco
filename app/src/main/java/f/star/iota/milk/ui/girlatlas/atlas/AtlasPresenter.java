package f.star.iota.milk.ui.girlatlas.atlas;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class AtlasPresenter extends StringPresenter<List<AtlasBean>> {
    public AtlasPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<AtlasBean> dealResponse(String s, HashMap<String, String> headers) {
        List<AtlasBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("div.photos > ul.gridview > li > a");
        for (Element element : select) {
            AtlasBean bean = new AtlasBean();
            String url = element.attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
