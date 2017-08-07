package f.star.iota.milk.ui.mzitu.tu;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class TuFragment extends ScrollImageFragment<TuPresenter, TuAdapter> {

    public static TuFragment newInstance(String url) {
        TuFragment fragment = new TuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected TuPresenter getPresenter() {
        return new TuPresenter(this);
    }

    @Override
    protected TuAdapter getAdapter() {
        return new TuAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
