package f.star.iota.milk.ui.meimeizi.meizi;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class MeiZiFragment extends ScrollImageFragment<MeiZiPresenter, MeiZiAdapter> {

    public static MeiZiFragment newInstance(String url) {
        MeiZiFragment fragment = new MeiZiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MeiZiPresenter getPresenter() {
        return new MeiZiPresenter(this);
    }

    @Override
    protected MeiZiAdapter getAdapter() {
        return new MeiZiAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
