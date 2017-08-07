package f.star.iota.milk.ui.wallhaven.haven;


import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class HavenPresenter extends StringPresenter<List<HavenBean>> {
    public HavenPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<HavenBean> dealResponse(String s, HashMap<String, String> headers) {
        List<HavenBean> list = new ArrayList<>();
        String url = "https:" + Jsoup.parse(s)
                .select("#wallpaper")
                .first()
                .attr("src");
        HavenBean bean = new HavenBean();
        bean.setUrl(url);
        bean.setHeaders(headers);
        list.add(bean);
        return list;
    }
}
