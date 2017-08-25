package f.star.iota.milk.ui.artstation.art;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class ArtStationFragment extends ScrollImageFragment<ArtStationPresenter, ArtStationAdapter> {


    public static ArtStationFragment newInstance(String url) {
        ArtStationFragment fragment = new ArtStationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected ArtStationPresenter getPresenter() {
        return new ArtStationPresenter(this);
    }

    @Override
    protected ArtStationAdapter getAdapter() {
        return new ArtStationAdapter();
    }
}
