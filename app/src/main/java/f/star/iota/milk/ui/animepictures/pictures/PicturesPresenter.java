package f.star.iota.milk.ui.animepictures.pictures;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class PicturesPresenter extends StringPresenter<List<PicturesBean>> {


    public PicturesPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<PicturesBean> dealResponse(String s, HashMap<String, String> headers) {
        List<PicturesBean> list = new ArrayList<>();
        Element select = Jsoup.parse(s).select("#rating > a.download_icon").first();
        if (select == null) return list;
        PicturesBean bean = new PicturesBean();
        String url = Net.ANIME_PICTURES_BASE + select.attr("href");
        bean.setUrl(url);
        bean.setHeaders(headers);
        list.add(bean);
        return list;
    }
}
