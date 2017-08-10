package f.star.iota.milk.ui.simpledesktops;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class SimpleDesktopsFragment extends ScrollImageFragment<SimpleDesktopsPresenter, SimpleDesktopsAdapter> {


    public static SimpleDesktopsFragment newInstance(String url) {
        SimpleDesktopsFragment fragment = new SimpleDesktopsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected SimpleDesktopsPresenter getPresenter() {
        return new SimpleDesktopsPresenter(this);
    }

    @Override
    protected SimpleDesktopsAdapter getAdapter() {
        return new SimpleDesktopsAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
