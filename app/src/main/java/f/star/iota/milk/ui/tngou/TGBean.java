package f.star.iota.milk.ui.tngou;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import f.star.iota.milk.Net;
import f.star.iota.milk.base.BaseBean;


class TGBean {

    @SerializedName("status")
    private boolean status;
    @SerializedName("tngou")
    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean extends BaseBean {
        @SerializedName("img")
        private String img;
        @SerializedName("title")
        private String title;

        public String getImg() {
            return Net.TNGOU_BASE + img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
