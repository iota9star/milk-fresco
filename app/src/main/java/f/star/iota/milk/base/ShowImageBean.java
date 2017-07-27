package f.star.iota.milk.base;

import java.util.Map;

public class ShowImageBean {
    private String url;
    private String preview;
    private String folder;
    private String description;
    private Map<String, String> headers;

    public ShowImageBean(String url, String preview, String folder, String description, Map<String, String> headers) {
        this.url = url;
        this.description = description;
        this.preview = preview;
        this.folder = folder;
        this.headers = headers;
    }

    public ShowImageBean(String url, String preview, String folder, String description) {
        this.url = url;
        this.description = description;
        this.preview = preview;
        this.folder = folder;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getPreview() {
        return preview;
    }

    public String getFolder() {
        return folder;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
