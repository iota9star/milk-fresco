package f.star.iota.milk.ui.nijieroch.nijiero;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class NijieroCHPresenter extends StringPresenter<List<NijieroCHBean>> {

    public NijieroCHPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<NijieroCHBean> dealResponse(String s, HashMap<String, String> headers) {
        List<NijieroCHBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#mainContent > div.post");
        for (Element element : select) {
            NijieroCHBean bean = new NijieroCHBean();
            String preview = element.select("a > div > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("section > h1 > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("section > h1 > a").text();
            bean.setDescription(description);
            String date = element.select("section > div > dl:nth-child(2) > dd").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }
}
