package f.star.iota.milk.ui.miaowu.wu;


import android.os.Bundle;

import f.star.iota.milk.base.ScrollImageFragment;


public class WuFragment extends ScrollImageFragment<WuPresenter, WuAdapter> {

    public static WuFragment newInstance(String url) {
        WuFragment fragment = new WuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("base_url", url.replace(".html", "_"));
        bundle.putString("page_suffix", ".html");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected WuPresenter getPresenter() {
        return new WuPresenter(this);
    }

    @Override
    protected WuAdapter getAdapter() {
        return new WuAdapter();
    }

    @Override
    protected boolean isHideFab() {
        return false;
    }
}
