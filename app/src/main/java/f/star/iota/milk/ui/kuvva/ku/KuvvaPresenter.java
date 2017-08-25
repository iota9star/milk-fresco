package f.star.iota.milk.ui.kuvva.ku;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class KuvvaPresenter extends StringPresenter<List<KuvvaBean>> {


    public KuvvaPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<KuvvaBean> dealResponse(String s, HashMap<String, String> headers) {
        List<KuvvaBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div > section > ul > li");
        for (Element element : select) {
            KuvvaBean bean = new KuvvaBean();
            String preview = element.select("a > img").attr("src");
            bean.setPreview(preview);
            String url = Net.KUVVA_BASE + element.select("a").attr("href");
            bean.setUrl(url);
            String description = element.select("a > img").attr("alt");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }

}
