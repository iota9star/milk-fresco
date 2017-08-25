package f.star.iota.milk.ui.magdeleine;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class MagdeleinePresenter extends StringPresenter<List<MagdeleineBean>> {

    public MagdeleinePresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MagdeleineBean> dealResponse(String s, HashMap<String, String> headers) {
        Elements select = Jsoup.parse(s).select("img.attachment-photo-thumb");
        List<MagdeleineBean> beans = new ArrayList<>();
        for (Element element : select) {
            String preview = element.attr("src");
            String url = preview.replace("-500x375", "");
            beans.add(new MagdeleineBean(preview, url));
        }
        return beans;
    }
}
