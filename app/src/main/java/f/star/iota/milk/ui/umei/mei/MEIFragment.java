package f.star.iota.milk.ui.umei.mei;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class MEIFragment extends ScrollImageFragment<MEIPresenter, MEIAdapter> {

    public static MEIFragment newInstance(String url) {
        MEIFragment fragment = new MEIFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url.replace(".htm", "_"));
        bundle.putString("page_suffix", ".htm");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected MEIPresenter getPresenter() {
        return new MEIPresenter(this);
    }

    @Override
    protected MEIAdapter getAdapter() {
        return new MEIAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
