package f.star.iota.milk.ui.kuvva.va;


import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class VaPresenter extends StringPresenter<List<VaBean>> {
    public VaPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<VaBean> dealResponse(String s, HashMap<String, String> headers) {
        List<VaBean> list = new ArrayList<>();
        VaBean bean = new VaBean();
        bean.setUrl(Jsoup.parse(s).select("body > section.illustration > div > div.image > div > a > img").attr("src"));
        bean.setHeaders(headers);
        list.add(bean);
        return list;
    }
}
