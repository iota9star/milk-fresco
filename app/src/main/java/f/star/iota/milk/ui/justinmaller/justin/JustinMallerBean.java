package f.star.iota.milk.ui.justinmaller.justin;


import f.star.iota.milk.base.BaseBean;

class JustinMallerBean extends BaseBean {
    private String preview;
    private String url;
    private String description;

    JustinMallerBean() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
