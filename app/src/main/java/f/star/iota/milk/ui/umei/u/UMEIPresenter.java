package f.star.iota.milk.ui.umei.u;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class UMEIPresenter extends StringPresenter<List<UMEIBean>> {


    public UMEIPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<UMEIBean> dealResponse(String s, HashMap<String, String> headers) {
        List<UMEIBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.wrap > div.TypeList > ul > li");
        for (Element element : select) {
            UMEIBean bean = new UMEIBean();
            String preview = element.select("a > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("a.TypeBigPics").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("a > div").text();
            bean.setDescription(description);
            String date = element.select("div > div.txtInfo.gray > em.IcoTime").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }

}
