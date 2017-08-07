package f.star.iota.milk.ui.tngou;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class TGClassifyPresenter extends StringPresenter<List<ClassifyBean.TngouBean>> {

    public TGClassifyPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<ClassifyBean.TngouBean> dealResponse(String s, HashMap<String, String> headers) {
        return new Gson().fromJson(s, new TypeToken<List<ClassifyBean.TngouBean>>() {
        }.getType());
    }
}
