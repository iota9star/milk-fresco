package f.star.iota.milk.ui.yuriimg;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class YuriImgPresenter extends StringPresenter<List<YuriImgBean>> {


    public YuriImgPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<YuriImgBean> dealResponse(String s, HashMap<String, String> headers) {
        List<YuriImgBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#image-box div.image-list");
        for (Element element : select) {
            YuriImgBean bean = new YuriImgBean();
            String url = Net.YURIIMG_BASE + element.select("div.image img").attr("data-viewersss");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("div.image img").attr("alt");
            bean.setDescription(description);
            String size = element.select("div.img-control > div.like > a > span").text();
            bean.setSize(size);
            list.add(bean);
        }
        return list;
    }

}
