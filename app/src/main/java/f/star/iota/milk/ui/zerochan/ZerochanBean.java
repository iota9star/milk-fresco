package f.star.iota.milk.ui.zerochan;


import f.star.iota.milk.base.BaseBean;

public class ZerochanBean extends BaseBean {
    private String preview;
    private String url;
    private String info;
    private String description;

    public ZerochanBean() {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
