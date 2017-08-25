package f.star.iota.milk.ui.isujin;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class IsujinPresenter extends StringPresenter<List<IsujinBean>> {

    public IsujinPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<IsujinBean> dealResponse(String s, HashMap<String, String> headers) {
        List<IsujinBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("div.post");
        for (Element element : select) {
            String preview = element.select("a > img").attr("src");
            String url = preview.substring(preview.lastIndexOf("http"), preview.length());
            String title = element.select("a").attr("title");
            list.add(new IsujinBean(preview, title, url));
        }
        return list;
    }
}
