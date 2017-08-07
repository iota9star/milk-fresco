package f.star.iota.milk.ui.widget.banner;


public interface BannerContract {
    interface View {
        void getBannerSuccess(String url);

        void getBannerError();
    }

    interface Presenter {
        void getBanner(int type);

        void unsubscribe();
    }
}
