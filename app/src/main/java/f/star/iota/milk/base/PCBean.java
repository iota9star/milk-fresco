package f.star.iota.milk.base;

import java.util.HashMap;

public class PCBean {
    private final String url;
    private final String preview;
    private final String folder;
    private final String description;
    private HashMap<String, String> headers;

    public PCBean(String url, String preview, String folder, String description, HashMap<String, String> headers) {
        this.url = url;
        this.description = description;
        this.preview = preview;
        this.folder = folder;
        this.headers = headers;
    }

    public PCBean(String url, String preview, String folder, String description) {
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

    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
