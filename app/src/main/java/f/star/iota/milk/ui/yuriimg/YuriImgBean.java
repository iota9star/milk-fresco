package f.star.iota.milk.ui.yuriimg;


import f.star.iota.milk.base.BaseBean;

class YuriImgBean extends BaseBean {
    private String url;
    private String description;
    private String size;

    YuriImgBean() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
