package f.star.iota.milk.ui.mmjpg.jpg;


import com.lzy.okgo.request.GetRequest;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class JpgPresenter extends StringPresenter<List<JpgBean>> {
    public JpgPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<JpgBean> dealResponse(String s, HashMap<String, String> headers) {
        List<JpgBean> list = new ArrayList<>();
        String url = Jsoup.parse(s).select("#content > a > img").attr("src");
        Elements pages = Jsoup.parse(s).select("#page > a");
        if (pages.size() < 2) {
            JpgBean bean = new JpgBean();
            bean.setHeaders(headers);
            bean.setUrl(url);
            list.add(bean);
            return list;
        }
        String page = pages.get(pages.size() - 2).text();
        for (Integer i = 0; i < Integer.valueOf(page); i++) {
            JpgBean bean = new JpgBean();
            bean.setHeaders(headers);
            bean.setUrl(url.replace("1.jpg", i + 1 + ".jpg"));
            list.add(bean);
        }
        return list;
    }

    @Override
    protected void addHeaders(GetRequest<String> request) {
        request.headers("Host", "www.mmjpg.com");
        request.headers("Referer", "http://127.0.0.1:8888");
        request.headers("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.headers("Proxy-Connection", "keep-alive");
        request.headers("Upgrade-Insecure-Requests", "1");
        request.headers("Accept-Encoding", "gzip, deflate");
    }
}
