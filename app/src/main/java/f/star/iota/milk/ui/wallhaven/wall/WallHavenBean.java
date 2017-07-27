package f.star.iota.milk.ui.wallhaven.wall;


import f.star.iota.milk.base.BaseBean;

public class WallHavenBean extends BaseBean {
    private String preview;
    private String url;
    private String size;

    public WallHavenBean() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
