package f.star.iota.milk.ui.moeimg.img;

import f.star.iota.milk.base.BaseBean;


class ImgBean extends BaseBean {
    private String url;

    public ImgBean() {
    }

    public String getUrl() {
        if (url.contains("img.moeimg.net")) {
            return url.replace("http:", "https:");
        } else {
            return url;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
