package f.star.iota.milk.ui.rosiyy.rosi;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class ROSIYYPresenter extends StringPresenter<List<ROSIYYBean>> {

    public ROSIYYPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<ROSIYYBean> dealResponse(String s, HashMap<String, String> headers) {
        List<ROSIYYBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#content > div.pic > div.photo > a");
        for (Element element : select) {
            ROSIYYBean bean = new ROSIYYBean();
            String preview = element.select("img").attr("src");
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
