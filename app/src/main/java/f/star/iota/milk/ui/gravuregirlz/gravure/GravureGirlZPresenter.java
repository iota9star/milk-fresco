package f.star.iota.milk.ui.gravuregirlz.gravure;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class GravureGirlZPresenter extends StringPresenter<List<GravureGirlZBean>> {


    public GravureGirlZPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<GravureGirlZBean> dealResponse(String s, HashMap<String, String> headers) {
        List<GravureGirlZBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("div.wp-tiles-tile");
        for (Element element : select) {
            GravureGirlZBean bean = new GravureGirlZBean();
            String preview = element.select("div.wp-tiles-tile-bg img")
                    .attr("src");
            bean.setPreview(preview);
            String url = element.select("a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("a").attr("title");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }

}
