package f.star.iota.milk.ui.minitokyo;

import f.star.iota.milk.base.BaseBean;

public class MINITOKYOBean extends BaseBean {
    private String size;
    private String preview;
    private String url;
    private String description;

    public MINITOKYOBean() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
