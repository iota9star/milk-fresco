package f.star.iota.milk.ui.alphacoders;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class AlphaCodersPresenter extends StringPresenter<List<AlphaCodersBean>> {

    public AlphaCodersPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<AlphaCodersBean> dealResponse(String s, HashMap<String, String> headers) {
        List<AlphaCodersBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("div.center > div.thumb-container-big");
        for (Element element : select) {
            String preview = element.select("div.thumb-container div.boxgrid > a > img").attr("src");
            String url = element.select("div.thumb-container div.boxcaption > div.overlay > div:nth-child(3) > span.download-button").attr("data-href");
            String title = element.select("div.extra-info").text();
            if (title == null || title.length() < 1) {
                title = "...";
            }
            list.add(new AlphaCodersBean(title, preview, url));
        }
        return list;
    }
}
