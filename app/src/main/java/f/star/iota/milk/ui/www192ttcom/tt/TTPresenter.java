package f.star.iota.milk.ui.www192ttcom.tt;


import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class TTPresenter extends StringPresenter<List<TTBean>> {

    public TTPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("_1.html")) {
            url = url.replace("_1.html", ".html");
        }
        if (!url.contains("192tt")) {
            url = Net.WWW192TTCOM_BASE + url;
        }
        return url;
    }

    @Override
    protected List<TTBean> dealResponse(String s, HashMap<String, String> headers) {
        List<TTBean> list = new ArrayList<>();
        TTBean bean = new TTBean();
        String url = Jsoup.parse(s).select("#p > center > img").first().attr("lazysrc");
        bean.setUrl(url);
        bean.setHeaders(headers);
        list.add(bean);
        return list;
    }
}
