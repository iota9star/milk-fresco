package f.star.iota.milk.ui.mmjpg.mm;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class MmjpgPresenter extends StringPresenter<List<MmjpgBean>> {


    public MmjpgPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MmjpgBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MmjpgBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.main > div.pic > ul > li");
        for (Element element : select) {
            MmjpgBean bean = new MmjpgBean();
            String preview = element.select("a > img").attr("src");
            bean.setPreview(preview);
            String url = element.select("a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("a > img").attr("alt");
            bean.setDescription(description);
            String date = element.select("span:nth-child(3)").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }

}
