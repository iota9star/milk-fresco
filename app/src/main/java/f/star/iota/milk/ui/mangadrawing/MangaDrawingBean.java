package f.star.iota.milk.ui.mangadrawing;

import f.star.iota.milk.base.BaseBean;


class MangaDrawingBean extends BaseBean {
    private String preview;
    private String url;

    public MangaDrawingBean() {
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
