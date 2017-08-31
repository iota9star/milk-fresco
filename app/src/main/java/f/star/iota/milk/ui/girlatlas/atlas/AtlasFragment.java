package f.star.iota.milk.ui.girlatlas.atlas;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class AtlasFragment extends FixedImageFragment<AtlasPresenter, AtlasAdapter> {

    public static AtlasFragment newInstance(String url) {
        AtlasFragment fragment = new AtlasFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected AtlasPresenter getPresenter() {
        return new AtlasPresenter(this);
    }

    @Override
    protected AtlasAdapter getAdapter() {
        return new AtlasAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
