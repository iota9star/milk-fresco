package f.star.iota.milk.ui.alphacoders;


import f.star.iota.milk.base.BaseBean;

class AlphaCodersBean extends BaseBean {

    private String title;
    private String preview;
    private String url;

    public AlphaCodersBean(String title, String preview, String url) {
        this.title = title;
        this.preview = preview;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getPreview() {
        return preview;
    }

    public String getUrl() {
        return url;
    }
}
