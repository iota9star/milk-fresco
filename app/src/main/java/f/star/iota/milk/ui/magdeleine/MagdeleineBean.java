package f.star.iota.milk.ui.magdeleine;


import f.star.iota.milk.base.BaseBean;

class MagdeleineBean extends BaseBean {

    private String preview;

    private String url;

    public MagdeleineBean(String preview, String url) {
        this.preview = preview;
        this.url = url;
    }

    public String getPreview() {
        return preview;
    }

    public String getUrl() {
        return url;
    }
}
