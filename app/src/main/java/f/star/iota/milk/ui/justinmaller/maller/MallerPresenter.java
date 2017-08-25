package f.star.iota.milk.ui.justinmaller.maller;


import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class MallerPresenter extends StringPresenter<List<MallerBean>> {
    public MallerPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MallerBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MallerBean> list = new ArrayList<>();
        MallerBean bean = new MallerBean();
        String url = Jsoup.parse(s).select("#wallwindow > img").attr("src");
        bean.setUrl(url);
        list.add(bean);
        return list;
    }
}
