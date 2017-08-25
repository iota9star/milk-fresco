package f.star.iota.milk.ui.isujin;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class IsujinFragment extends ScrollImageFragment<IsujinPresenter, IsujinAdapter> {

    public static IsujinFragment newInstance(String url) {
        IsujinFragment fragment = new IsujinFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected IsujinPresenter getPresenter() {
        return new IsujinPresenter(this);
    }

    @Override
    protected IsujinAdapter getAdapter() {
        return new IsujinAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
