package f.star.iota.milk.ui.widget.today;


import f.star.iota.milk.ui.splash.HistoryBean;

public interface TodayInHistoryContract {
    interface View {
        void getSuccess(HistoryBean bean);

        void getError();
    }

    interface Presenter {
        void getToday();

        void unsubscribe();
    }
}
