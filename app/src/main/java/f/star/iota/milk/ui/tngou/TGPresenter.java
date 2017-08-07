package f.star.iota.milk.ui.tngou;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class TGPresenter extends StringPresenter<List<TGBean.TngouBean>> {

    public TGPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<TGBean.TngouBean> dealResponse(String s, HashMap<String, String> headers) {
        return new Gson().fromJson(s, new TypeToken<List<TGBean.TngouBean>>() {
        }.getType());
    }
}
