package f.star.iota.milk.ui.isujin;


import f.star.iota.milk.base.BaseBean;

class IsujinBean extends BaseBean {

    private String preview;
    private String title;
    private String url;

    public IsujinBean(String preview, String title, String url) {
        this.preview = preview;
        this.title = title;
        this.url = url;
    }

    public String getPreview() {
        return preview;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
