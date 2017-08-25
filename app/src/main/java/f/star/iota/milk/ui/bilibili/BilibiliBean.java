package f.star.iota.milk.ui.bilibili;


import com.google.gson.annotations.SerializedName;

import f.star.iota.milk.base.BaseBean;

class BilibiliBean extends BaseBean {

    @SerializedName("il_file")
    private String preview;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private String url;

    public String getPreview() {
        return preview;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return "http://h.bilibili.com/wallpaper?action=download&img_id=" + url;
    }
}
