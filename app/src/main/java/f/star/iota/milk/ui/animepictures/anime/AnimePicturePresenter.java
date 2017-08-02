package f.star.iota.milk.ui.animepictures.anime;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Url;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class AnimePicturePresenter extends StringPresenter<List<AnimePictureBean>> {


    public AnimePicturePresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<AnimePictureBean> dealResponse(String s) {
        List<AnimePictureBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#posts > div.posts_block > span.img_block_big");
        for (Element element : select) {
            AnimePictureBean bean = new AnimePictureBean();
            String preview = element.select("a > picture > source").attr("srcset");
            bean.setPreview(preview);
            String url = Url.ANIME_PICTURES_BASE + element.select("a").attr("href");
            bean.setUrl(url);
            String size = element.select("div.img_block_text > a").text();
            bean.setSize(size);
            list.add(bean);
        }
        return list;
    }

}
