package f.star.iota.milk.ui.zdt8.t8;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class T8Fragment extends ScrollImageFragment<T8Presenter, T8Adapter> {

    public static T8Fragment newInstance(String url) {
        T8Fragment fragment = new T8Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url.replace(".html", "_"));
        bundle.putString("page_suffix", ".html");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected T8Presenter getPresenter() {
        return new T8Presenter(this);
    }

    @Override
    protected T8Adapter getAdapter() {
        return new T8Adapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
