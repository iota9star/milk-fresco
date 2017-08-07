package f.star.iota.milk.ui.gravuregirlz.girlz;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class GirlZPresenter extends StringPresenter<List<GirlZBean>> {
    public GirlZPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<GirlZBean> dealResponse(String s, HashMap<String, String> headers) {
        List<GirlZBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("section.entry-content > p > img");
        for (Element element : select) {
            GirlZBean bean = new GirlZBean();
            String url = element.attr("src");
            url = url.substring(0, url.lastIndexOf("?"));
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
