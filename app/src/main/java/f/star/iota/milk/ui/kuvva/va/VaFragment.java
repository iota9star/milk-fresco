package f.star.iota.milk.ui.kuvva.va;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class VaFragment extends FixedImageFragment<VaPresenter, VaAdapter> {

    public static VaFragment newInstance(String url) {
        VaFragment fragment = new VaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected VaPresenter getPresenter() {
        return new VaPresenter(this);
    }

    @Override
    protected VaAdapter getAdapter() {
        return new VaAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
