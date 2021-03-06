package f.star.iota.milk.ui.minitokyo;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class MINITOKYOPresenter extends StringPresenter<List<MINITOKYOBean>> {

    public MINITOKYOPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<MINITOKYOBean> dealResponse(String s, HashMap<String, String> headers) {
        List<MINITOKYOBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#content > ul.scans > li > a > img");
        for (Element element : select) {
            MINITOKYOBean bean = new MINITOKYOBean();
            String size = element.attr("title");
            bean.setSize(size);
            String preview = element.attr("src");
            bean.setPreview(preview);
            String url = preview.replace("static3", "static").replace("thumbs", "downloads");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.attr("alt").replace(" Wallpaper", "");
            bean.setDescription(description);
            list.add(bean);
        }
        return list;
    }
}
