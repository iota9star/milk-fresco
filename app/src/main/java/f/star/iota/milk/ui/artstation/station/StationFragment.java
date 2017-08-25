package f.star.iota.milk.ui.artstation.station;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class StationFragment extends FixedImageFragment<StationPresenter, StationAdapter> {

    public static StationFragment newInstance(String url) {
        StationFragment fragment = new StationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected StationPresenter getPresenter() {
        return new StationPresenter(this);
    }

    @Override
    protected StationAdapter getAdapter() {
        return new StationAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
