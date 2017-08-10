package f.star.iota.milk.ui.simpledesktops;


import f.star.iota.milk.base.BaseBean;

class SimpleDesktopsBean extends BaseBean {
    private String url;
    private String description;
    private String creator;
    private String preview;

    SimpleDesktopsBean() {
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
