package f.star.iota.milk.ui.umei.mei;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class MEIPresenter extends StringPresenter<List<MEIBean>> {
    public MEIPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MEIBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MEIBean> list = new ArrayList<>();
        Element element = Jsoup.parse(s).select("div.ImageBody > p > img").first();
        MEIBean bean = new MEIBean();
        String url = element.attr("src");
        bean.setUrl(url);
        bean.setHeaders(headers);
        list.add(bean);
        return list;
    }
}
