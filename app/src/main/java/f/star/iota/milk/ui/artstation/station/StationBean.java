package f.star.iota.milk.ui.artstation.station;

import com.google.gson.annotations.SerializedName;

import f.star.iota.milk.base.BaseBean;


class StationBean extends BaseBean {
    @SerializedName("image_url")
    private String url;

    public String getUrl() {
        return url;
    }
}
