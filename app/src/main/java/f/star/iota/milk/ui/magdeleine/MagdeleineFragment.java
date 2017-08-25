package f.star.iota.milk.ui.magdeleine;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class MagdeleineFragment extends ScrollImageFragment<MagdeleinePresenter, MagdeleineAdapter> {

    public static MagdeleineFragment newInstance(String url) {
        MagdeleineFragment fragment = new MagdeleineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MagdeleinePresenter getPresenter() {
        return new MagdeleinePresenter(this);
    }

    @Override
    protected MagdeleineAdapter getAdapter() {
        return new MagdeleineAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
