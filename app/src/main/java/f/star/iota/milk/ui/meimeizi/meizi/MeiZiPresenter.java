package f.star.iota.milk.ui.meimeizi.meizi;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class MeiZiPresenter extends StringPresenter<List<MeiZiBean>> {
    public MeiZiPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MeiZiBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MeiZiBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#post_content > p > img");
        for (Element element : select) {
            MeiZiBean bean = new MeiZiBean();
            String url = element.attr("src");
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
