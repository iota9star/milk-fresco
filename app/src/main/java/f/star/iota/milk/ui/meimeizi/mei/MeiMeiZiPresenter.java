package f.star.iota.milk.ui.meimeizi.mei;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class MeiMeiZiPresenter extends StringPresenter<List<MeiMeiZiBean>> {


    public MeiMeiZiPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MeiMeiZiBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MeiMeiZiBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#post_container > li.post");
        for (Element element : select) {
            MeiMeiZiBean bean = new MeiMeiZiBean();
            String preview = element.select("div.thumbnail > a > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("div.thumbnail > a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("div.article > h2 > a").text();
            bean.setDescription(description);
            String date = element.select("div.info > span.info_date.info_ico").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }

}
