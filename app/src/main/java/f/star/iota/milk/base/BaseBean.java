package f.star.iota.milk.base;


import java.util.HashMap;

public class BaseBean {
    private HashMap<String, String> headers = new HashMap<>();

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
}
