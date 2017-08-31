package f.star.iota.milk.ui.girlatlas.girl;


import f.star.iota.milk.base.BaseBean;

class GirlAtlasBean extends BaseBean {
    private String preview;
    private String url;
    private String description;
    private String count;

    GirlAtlasBean() {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
