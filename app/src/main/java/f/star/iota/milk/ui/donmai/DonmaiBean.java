package f.star.iota.milk.ui.donmai;


import com.google.gson.annotations.SerializedName;

import f.star.iota.milk.base.BaseBean;

class DonmaiBean extends BaseBean {

    @SerializedName("file_url")
    private String url;
    @SerializedName("preview_file_url")
    private String preview;
    @SerializedName("rating")
    private String rating;
    @SerializedName("image_width")
    private int width;
    @SerializedName("image_height")
    private int height;
    @SerializedName("tag_string")
    private String tags;
    @SerializedName("uploader_name")
    private String author;
    @SerializedName("source")
    private String source;
    @SerializedName("score")
    private int score;
    @SerializedName("file_size")
    private int fileSize;

    private String size;

    public String getTags() {
        return tags;
    }

    public String getAuthor() {
        return author;
    }

    public String getSource() {
        return source;
    }

    public int getScore() {
        return score;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getUrl() {
        if (url.contains("https:")) return url;
        else if (url.contains("http:")) return url.replace("http:", "https:");
        else return "https:" + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPreview() {
        if (preview.contains("https:")) return preview;
        else if (preview.contains("http:")) return preview.replace("http:", "https:");
        else return "https:" + preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getRating() {
        return rating;
    }

    public String getSize() {
        return width + "*" + height;
    }
}
