package f.star.iota.milk.ui.girlatlas.girl;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class GirlAtlasFragment extends ScrollImageFragment<GirlAtlasPresenter, GirlAtlasAdapter> {


    public static GirlAtlasFragment newInstance(String url) {
        GirlAtlasFragment fragment = new GirlAtlasFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected GirlAtlasPresenter getPresenter() {
        return new GirlAtlasPresenter(this);
    }

    @Override
    protected GirlAtlasAdapter getAdapter() {
        return new GirlAtlasAdapter();
    }
}
