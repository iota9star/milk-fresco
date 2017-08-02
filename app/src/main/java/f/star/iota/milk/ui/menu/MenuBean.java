package f.star.iota.milk.ui.menu;

class MenuBean {
    private final int id;
    private final String name;
    private final String banner;
    private final String url;
    private final String loginUrl;

    public MenuBean(int id, String name, String url, String banner, String loginUrl) {
        this.id = id;
        this.name = name;
        this.banner = banner;
        this.url = url;
        this.loginUrl = loginUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBanner() {
        return banner;
    }

    public String getUrl() {
        return url;
    }

    public String getLoginUrl() {
        return loginUrl;
    }
}
