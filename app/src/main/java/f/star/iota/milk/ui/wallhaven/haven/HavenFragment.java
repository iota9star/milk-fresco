package f.star.iota.milk.ui.wallhaven.haven;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class HavenFragment extends FixedImageFragment<HavenPresenter, HavenAdapter> {

    public static HavenFragment newInstance(String url) {
        HavenFragment fragment = new HavenFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected HavenPresenter getPresenter() {
        return new HavenPresenter(this);
    }

    @Override
    protected HavenAdapter getAdapter() {
        return new HavenAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
