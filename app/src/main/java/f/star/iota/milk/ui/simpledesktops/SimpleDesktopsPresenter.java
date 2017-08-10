package f.star.iota.milk.ui.simpledesktops;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class SimpleDesktopsPresenter extends StringPresenter<List<SimpleDesktopsBean>> {


    public SimpleDesktopsPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<SimpleDesktopsBean> dealResponse(String s, HashMap<String, String> headers) {
        List<SimpleDesktopsBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div > div.container > div > div.desktops> div.edge > div.desktop");
        for (Element element : select) {
            SimpleDesktopsBean bean = new SimpleDesktopsBean();
            String preview = element.select("a > img").attr("src");
            bean.setPreview(preview);
            String url = preview.replace(".295x184_q100.png", "");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("h2 > a").text();
            bean.setDescription(description);
            String creator = element.select("span.creator").text();
            bean.setCreator(creator);
            list.add(bean);
        }
        return list;
    }

}
