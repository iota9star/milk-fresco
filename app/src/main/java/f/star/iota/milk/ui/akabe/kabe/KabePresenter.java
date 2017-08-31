package f.star.iota.milk.ui.akabe.kabe;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class KabePresenter extends StringPresenter<List<KabeBean>> {
    public KabePresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<KabeBean> dealResponse(String s, HashMap<String, String> headers) {
        List<KabeBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#main > main > article > div.entryBody > ul > li");
        for (Element element : select) {
            KabeBean bean = new KabeBean();
            String url = element.attr("data-src");
            bean.setUrl(url);
            list.add(bean);
        }
        return list;
    }
}
