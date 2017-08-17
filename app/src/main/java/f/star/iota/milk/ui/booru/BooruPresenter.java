package f.star.iota.milk.ui.booru;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import f.star.iota.milk.MyApp;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;
import f.star.iota.milk.config.OtherConfig;

public class BooruPresenter extends StringPresenter<List<BooruBean>> {

    public BooruPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<BooruBean> dealResponse(String s, HashMap<String, String> headers) {
        List<BooruBean> beans = new Gson().fromJson(s, new TypeToken<List<BooruBean>>() {
        }.getType());
        if (OtherConfig.getR(MyApp.mContext)) {
            Iterator<BooruBean> iterator = beans.iterator();
            while (iterator.hasNext()) {
                BooruBean b = iterator.next();
                if (b.getRating() != null && !b.getRating().toLowerCase().contains("s")) {
                    iterator.remove();
                }
            }
        }
        return beans;
    }
}
