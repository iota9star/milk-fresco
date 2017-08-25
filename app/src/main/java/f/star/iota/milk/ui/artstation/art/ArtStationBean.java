package f.star.iota.milk.ui.artstation.art;


import com.google.gson.annotations.SerializedName;

import f.star.iota.milk.base.BaseBean;

class ArtStationBean extends BaseBean {
    @SerializedName("title")
    private String title;
    @SerializedName("cover")
    private Cover cover;
    @SerializedName("permalink")
    private String url;

    public String getTitle() {
        return title;
    }

    public Cover getCover() {
        return cover;
    }

    public String getUrl() {
        return url.replace("artwork", "projects") + ".json";
    }

    public static class Cover {
        @SerializedName("small_image_url")
        private String preview;

        public String getPreview() {
            return preview;
        }
    }
}
