package f.star.iota.milk.ui.mmonly.only;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class OnlyPresenter extends StringPresenter<List<OnlyBean>> {

    public OnlyPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<OnlyBean> dealResponse(String s, HashMap<String, String> headers) {
        List<OnlyBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#big-pic > p > a > img");
        for (Element element : select) {
            OnlyBean bean = new OnlyBean();
            String url = element.attr("src");
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
