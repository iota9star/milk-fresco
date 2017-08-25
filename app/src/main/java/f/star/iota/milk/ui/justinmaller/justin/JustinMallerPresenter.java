package f.star.iota.milk.ui.justinmaller.justin;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class JustinMallerPresenter extends StringPresenter<List<JustinMallerBean>> {


    public JustinMallerPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<JustinMallerBean> dealResponse(String s, HashMap<String, String> headers) {
        List<JustinMallerBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#main-container div.project-image > a");
        for (Element element : select) {
            JustinMallerBean bean = new JustinMallerBean();
            String preview = element.select("img").attr("src");
            bean.setPreview(preview);
            String url = Net.JUSTINMALLER_BASE + element.attr("href");
            bean.setUrl(url);
            String description = element.select("img").attr("alt");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }

}
