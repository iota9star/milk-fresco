package f.star.iota.milk.ui.akabe.kabe;


import android.os.Bundle;

import f.star.iota.milk.base.FixedImageFragment;


public class KabeFragment extends FixedImageFragment<KabePresenter, KabeAdapter> {

    public static KabeFragment newInstance(String url) {
        KabeFragment fragment = new KabeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected KabePresenter getPresenter() {
        return new KabePresenter(this);
    }

    @Override
    protected KabeAdapter getAdapter() {
        return new KabeAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
