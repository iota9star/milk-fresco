package f.star.iota.milk.ui.girlatlas.girl;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class GirlAtlasPresenter extends StringPresenter<List<GirlAtlasBean>> {


    public GirlAtlasPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<GirlAtlasBean> dealResponse(String s, HashMap<String, String> headers) {
        List<GirlAtlasBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.page-wrapper > section div.album-item > div.col-md-11.col-sm-11");
        for (Element element : select) {
            GirlAtlasBean bean = new GirlAtlasBean();
            String preview = element.select("div > div > a.fillmore").attr("photo");
            bean.setPreview(preview);
            String url = Net.GIRL_ATLAS_BASE + element.select("h2 > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("h2 > a").text();
            bean.setDescription(description);
            String date = element.select("p > code:nth-child(1)").text() + " P";
            bean.setCount(date);
            list.add(bean);
        }
        return list;
    }

}
