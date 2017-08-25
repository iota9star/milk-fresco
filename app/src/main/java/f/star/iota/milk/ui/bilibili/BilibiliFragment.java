package f.star.iota.milk.ui.bilibili;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class BilibiliFragment extends ScrollImageFragment<BilibiliPresenter, BilibiliAdapter> {

    public static BilibiliFragment newInstance(String url) {
        BilibiliFragment fragment = new BilibiliFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected BilibiliPresenter getPresenter() {
        return new BilibiliPresenter(this);
    }

    @Override
    protected BilibiliAdapter getAdapter() {
        return new BilibiliAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
