package f.star.iota.milk.ui.akabe.a;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class AKabePresenter extends StringPresenter<List<AKabeBean>> {


    public AKabePresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("index_1.php")) {
            url = url.replace("index_1.php", "");
        }
        return url;
    }

    @Override
    protected List<AKabeBean> dealResponse(String s, HashMap<String, String> headers) {
        List<AKabeBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#main > article");
        for (Element element : select) {
            AKabeBean bean = new AKabeBean();
            String preview = element.select("figure > a > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("h2 > a").attr("href");
            bean.setUrl(url);
            String description = element.select("h2 > a").text();
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }

}
