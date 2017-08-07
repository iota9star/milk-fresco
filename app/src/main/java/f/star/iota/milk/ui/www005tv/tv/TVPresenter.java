package f.star.iota.milk.ui.www005tv.tv;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class TVPresenter extends StringPresenter<List<TVBean>> {

    public TVPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<TVBean> dealResponse(String s, HashMap<String, String> headers) {
        List<TVBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("div.articleContent img");
        for (Element element : select) {
            TVBean bean = new TVBean();
            String url = element.attr("src");
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
