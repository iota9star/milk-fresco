package f.star.iota.milk.ui.widget.juzi;


import f.star.iota.milk.ui.main.JuZiBean;

public interface JuZiContract {
    interface View {
        void getJuziSuccess(JuZiBean bean);
    }

    interface Presenter {
        void getJuzi();

        void unsubscribe();
    }
}
