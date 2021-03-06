package f.star.iota.milk.ui.lesmao.les;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class LesmaoPresenter extends StringPresenter<List<LesmaoBean>> {

    public LesmaoPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<LesmaoBean> dealResponse(String s, HashMap<String, String> headers) {
        List<LesmaoBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#show > div > div.group");
        for (Element element : select) {
            LesmaoBean bean = new LesmaoBean();
            String preview = element.select("div.photo > a > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("div.photo > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("div.photo > a > img").attr("alt");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }
}
