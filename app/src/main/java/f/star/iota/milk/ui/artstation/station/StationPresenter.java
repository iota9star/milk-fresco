package f.star.iota.milk.ui.artstation.station;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class StationPresenter extends StringPresenter<List<StationBean>> {
    public StationPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<StationBean> dealResponse(String s, HashMap<String, String> headers) {
        Pattern pattern = Pattern.compile("\"assets\":\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(s);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group(1)).append(",");
        }
        String result = "[" + sb.substring(0, sb.lastIndexOf(",")) + "]";
        return new Gson().fromJson(result, new TypeToken<List<StationBean>>() {
        }.getType());
    }
}
